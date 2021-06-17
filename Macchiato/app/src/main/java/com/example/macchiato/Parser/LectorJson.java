package com.example.macchiato.Parser;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
/**
 * Lee un archivo json desde los assets
 * */
public class LectorJson {

    public String loadJSONFromAsset(String archivo, Context context) {
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
            return "";
        }
        return json;

    }
}
