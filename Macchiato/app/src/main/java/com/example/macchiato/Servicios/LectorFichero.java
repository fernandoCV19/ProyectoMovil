package com.example.macchiato.Servicios;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.macchiato.Models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LectorFichero {
    public Map<String,Object> jsonMap;


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
    public void crearJson(Context context, Map<String,Object> user){
        String myjson = new Gson().toJson(user);
        Toast.makeText(context, myjson, Toast.LENGTH_SHORT).show();
        //Map<String, Object> jsonMap = new Gson().fromJson(myjson, new TypeToken<HashMap<String, Object>>() {}.getType());
        escribirFichero("registro.json", myjson, context);
        jsonMap = devolverMapa(context);
    }
    public void crearJson(Context context, User user){
        String myjson = new Gson().toJson(user);
        Map<String, Object> jsonMap = new Gson().fromJson(myjson, new TypeToken<HashMap<String, Object>>() {}.getType());
        myjson = new Gson().toJson(jsonMap);
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
            return leerFichero(context);
        }
        finally {
           /* if(fileInputStream != null){
                RegistroJSON rj = new RegistroJSON();
                rj.genararVacio(context);
                try {
                    fileInputStream.close();
                }catch (Exception e){}
            }*/
        }
        //Toast.makeText(context, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
        return stringBuilder.toString();
    }

    public Map<String,Object> devolverMapa(Context context){
        String res="";
        try {
            res=leerFichero(context);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(res, new TypeToken<HashMap<String, Object>>() {}.getType());
    }
}
