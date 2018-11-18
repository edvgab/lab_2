package com.example.edlyas.laboras_2;
public class ModelPost {
    int id;
    int userId;
    String title;
    String bodyText;

    public ModelPost() { }

    public ModelPost (int id, int userId, String title, String bodyText){
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.bodyText = bodyText;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }
}
