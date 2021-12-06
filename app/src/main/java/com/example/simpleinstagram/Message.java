package com.example.simpleinstagram;


import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Message")
public class Message extends ParseObject {

    public static final String KEY_send = "send";
    public static final String KEY_receive = "receive";
    public static final String KEY_text = "text";

    public String getsend() { return getString(KEY_send); }
    public void setsend(String send) { put(KEY_send,send); }
    public String getreceive() { return getString(KEY_receive); }
    public void setreceive(String receive) { put(KEY_receive,receive); }
    public String gettext(){ return getString(KEY_text); }
    public void settext(String text) { put(KEY_text,text);}

}
