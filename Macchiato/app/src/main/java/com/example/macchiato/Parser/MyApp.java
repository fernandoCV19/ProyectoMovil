package com.example.macchiato.Parser;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    public  Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public  Context getContext(){
        onCreate();
        return context;
    }
}
