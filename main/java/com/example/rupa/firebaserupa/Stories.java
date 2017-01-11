package com.example.rupa.firebaserupa;

/**
 * Created by Rupa Barua on 4/2/2016.
 */
public class Stories {
    // this class seperates the different entities of the database for retrieval
    private String author;
    private String body;
    private String date;
    private String editable;
    private String le;
    private String title;


    public Stories() {
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }
    public String getLE(){return le;}
    public String getAuthor() {
        return author;
    }
    public String getBody() {
        return body;
    }
    public String getDate() {
        return date;
    }
    public String getEditable() {
        return editable;
    }

    public String getTitle() {
        return title;
    }

}
