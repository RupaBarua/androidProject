package com.example.rupa.firebaserupa;

/**
 * Created by Rupa Barua on 3/26/2016.
 */
public class BlogPost {
    // this class seperates the different entities of the database for retrieval
    private String name;
    private String password;
    public BlogPost() {
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
}

