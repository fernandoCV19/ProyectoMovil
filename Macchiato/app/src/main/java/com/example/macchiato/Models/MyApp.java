package com.example.macchiato.Models;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    private Context context;
    public MyApp(){
        onCreate();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public Context getContext(){return context;}
}
