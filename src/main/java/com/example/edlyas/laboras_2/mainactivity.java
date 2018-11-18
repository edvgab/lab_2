package com.example.edlyas.laboras_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class mainactivity extends AppCompatActivity implements RequestOperator.RequestOperatorListener{

    Button sendRequestButton;
    TextView title;
    TextView bodyText;


    private ModelPost publication;
    private List<ModelPost> publications;
    private IndicatingView indicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivitydesign);

        sendRequestButton = (Button) findViewById(R.id.send_request);
        sendRequestButton.setOnClickListener(requestButtonClicked);

        title = (TextView)findViewById(R.id.title);
        bodyText = (TextView) findViewById(R.id.body_text);

        indicator = (IndicatingView)findViewById(R.id.generated_graphic);

    }

    View.OnClickListener requestButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setIndicatorStatus(IndicatingView.EXECUTED);
            sendRequest();
        }
    };



    private void sendRequest(){
        RequestOperator ro = new RequestOperator();
        ro.setListener(this);
        ro.start();
    }

    public void updatePublication(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(publication != null){
                    title.setText(publication.getTitle());
                    bodyText.setText(publication.getBodyText());

                }else if(publications != null){
                    for(ModelPost publication:publications){

                       //bodyText.setText(bodyText.getText()+"\n"+publication.getBodyText());
                      // bodyText.setText(String.valueOf(publication.getId()));

                    }
                }
                else{
                    title.setText("");
                    bodyText.setText("");
                }
            }
        });
    }


    @Override
    public void success(ModelPost publications){
        this.publication = publication;
        setIndicatorStatus(IndicatingView.SUCCESS);
        updatePublication();
    }

    @Override
    public void success(List<ModelPost> publications){
        this.publications = publications;
        setIndicatorStatus(IndicatingView.SUCCESS);
        updatePublication();
    }

    @Override
    public void failed(int responseCode){
        this.publication = null;
        setIndicatorStatus(IndicatingView.FAILED);
        updatePublication();
    }

    public void setIndicatorStatus(final int status){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                indicator.setState(status);
                indicator.invalidate();
            }
        });
    }

    @Override
    public void number(ModelPost publications){
        this.publication = publication;
        setIndicatorStatus(IndicatingView.SUCCESS);
        updatePublication();
    }

}
