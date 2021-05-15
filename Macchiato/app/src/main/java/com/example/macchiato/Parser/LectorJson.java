package com.example.macchiato.Parser;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class LectorJson extends Application {
    private Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public LectorJson(){
        onCreate();
    }
    public String loadJSONFromAsset(String archivo) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(archivo);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
