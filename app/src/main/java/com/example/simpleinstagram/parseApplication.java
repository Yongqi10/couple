package com.example.simpleinstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class parseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        ParseObject.registerSubclass(Message.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("SYBNCVrcWPu3JK2dCo1lmlZv6F2n6ZGpNcrMuYCG")
                .clientKey("RO5J6MEh1eJoXawfmW1AG3J1c1IkzeHAbw8arZ5u")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
