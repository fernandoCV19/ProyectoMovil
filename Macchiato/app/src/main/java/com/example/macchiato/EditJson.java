package com.example.macchiato;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;

import com.example.macchiato.Models.GlobalApplication;
import com.example.macchiato.Models.User;
import com.example.macchiato.Servicios.RegistroJSON;
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

public class EditJson {
    public EditJson(){}

    public void crearJson(){
        //quitar
        String myjson = new Gson().toJson(new User());
        //
        Map<String, Object> jsonMap = new Gson().fromJson(myjson, new TypeToken<HashMap<String, Object>>() {}.getType());
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(GlobalApplication.getAppContext().openFileOutput("registro.json", Activity.MODE_PRIVATE));
            archivo.write(myjson);
            archivo.flush();
            archivo.close();
            //Toast.makeText(this,"fichero:"+ leerFichero(), Toast.LENGTH_SHORT).show();

        } catch (IOException e ){
            e.printStackTrace();
        }
    }

    public String leerFichero(Context context) throws FileNotFoundException, JSONException {
        FileInputStream fileInputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        String texto ="";
        try {
            fileInputStream = GlobalApplication.getAppContext().openFileInput("registro.json");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while((texto = bufferedReader.readLine())!= null){
                stringBuilder.append(texto);
            }
        }catch (Exception e){
            RegistroJSON rj = new RegistroJSON();
            rj.genararVacio(context, "registro.json");
            leerFichero(context);
        }
        finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                }catch (Exception e){}
            }
        }
        return stringBuilder.toString();
    }
}
