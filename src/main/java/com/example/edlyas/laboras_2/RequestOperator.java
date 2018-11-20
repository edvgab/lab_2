package com.example.edlyas.laboras_2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RequestOperator extends Thread {


    public interface RequestOperatorListener {
        void success(List<ModelPost> publications);
        void failed(int responseCode);

    }

    private RequestOperatorListener listener;
    private int responseCode;


    public void setListener(RequestOperatorListener listener) {
        this.listener = listener;
    }


    @Override
    public void run() {
        super.run();
        try {
            //ModelPost publication = request();
            List<ModelPost> publications = request();
            if (publications != null)
                success(publications);
            else
                failed(responseCode);
        } catch (IOException e) {
            failed(-1);
        } catch (JSONException e) {
            failed(-2);
        }
    }

    private  List<ModelPost> request() throws IOException, JSONException {
        //URL adress

        //  URL obj = new URL("http://jsonplaceholder.typicode.com/posts/1");
        URL obj = new URL("http://jsonplaceholder.typicode.com/posts/");

        //Executor
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Determined what method will be used (GET, Post, Put, or Delete)
        con.setRequestProperty("Content-Type", "application/json");

        //Make request and receive a response
        responseCode = con.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        InputStreamReader streamReader;

        //If response okay, using InputStream
        //if not, using error stream
        if (responseCode == 200) {
            streamReader = new InputStreamReader(con.getInputStream());
        }else {
            streamReader = new InputStreamReader(con.getErrorStream());
        }

        BufferedReader in = new BufferedReader(streamReader);
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

        if (responseCode == 200) {
            return parsingJsonArray(response.toString());
            //return parsingJsonObject(response.toString());

        } else
        return null;
    }

    public List<ModelPost> parsingJsonArray(String response) throws JSONException {
        JSONArray parentArray = new JSONArray(response);
        List<ModelPost> modelPostList = new ArrayList<ModelPost>();
        for(int i= 0; i<parentArray.length();i++){
            JSONObject finalObject = parentArray.getJSONObject(i);
            ModelPost post = new ModelPost();
            post.setId(finalObject.optInt("id", 0));
            post.setUserId(finalObject.optInt("userId", 0));
            post.setTitle(finalObject.getString("title"));
            post.setBodyText(finalObject.getString("body"));
            modelPostList.add(post);
            //Log.i(">>>>>>",modelPostList.get(i).getTitle());
            Log.i(">>>>>>", String.valueOf(modelPostList.get(i).getId()));


        }
        return modelPostList;
    }

    public ModelPost parsingJsonObject(String response) throws JSONException {

        //atempts to create a json object of achieving a response
        JSONObject object = new JSONObject(response);
        ModelPost post = new ModelPost();


        //because we will not need ID and user ID, they do not necessarily
        //get from a server in the JSON object.

        post.setId(object.optInt("id", 0));
        post.setUserId(object.optInt("userId", 0));

        //If the variables have not been found will be held JSONExeption

        post.setTitle(object.getString("title"));
        post.setBodyText(object.getString("body"));

        return post;
    }

    private void failed(int code) {
        if (listener != null)
            listener.failed(code);
    }

    private void success(List<ModelPost> publications) {
        if (listener != null)
            listener.success(publications);
    }


}



