package com.example.macchiato.Servicios;

import android.app.Activity;
import android.content.Context;

import com.example.macchiato.Models.GlobalApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class LectorFichero {
    public void escribirFichero(String fichero, String contenido, Context context){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(context.openFileOutput(fichero, Activity.MODE_PRIVATE));
            archivo.write(contenido);
            archivo.flush();
            archivo.close();
        } catch (IOException e ){
            e.printStackTrace();
        }
    }
    public void crearJson(Context context){
        //quitar
        String myjson = new Gson().toJson(GlobalApplication.userProfile);
        //
        Map<String, Object> jsonMap = new Gson().fromJson(myjson, new TypeToken<HashMap<String, Object>>() {}.getType());
        escribirFichero("registro.json", myjson, context);
    }

    public String leerFichero(Context context) throws FileNotFoundException, JSONException {
        FileInputStream fileInputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        String texto ="";
        try {
            fileInputStream = context.openFileInput("registro.json");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while((texto = bufferedReader.readLine())!= null){
                stringBuilder.append(texto);
            }
        }catch (Exception e){
            RegistroJSON rj = new RegistroJSON();
            rj.genararVacio(context);
        }
        finally {
            if(fileInputStream != null){
                RegistroJSON rj = new RegistroJSON();
                rj.genararVacio(context);
                try {
                    fileInputStream.close();
                }catch (Exception e){}
            }
        }
        return stringBuilder.toString();
    }
}
