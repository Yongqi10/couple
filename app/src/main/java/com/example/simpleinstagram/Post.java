package com.example.simpleinstagram;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;


@ParseClassName("Post")
public class Post extends ParseObject{

    public static final String KEY_DESC = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_createdAt = "createdAt";


    public String getDescription() {
        return getString(KEY_DESC);
    }

    public void SetDescription(String description) {
        put(KEY_DESC,description);
    }
    public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);
    }
    public void setImage(ParseFile parseFile) {
        put(KEY_IMAGE,parseFile);
    }

    public ParseUser getUSer(){
        return getParseUser(KEY_USER);
    }
    public void setUser(ParseUser user){
        put(KEY_USER,user);
    }

}
