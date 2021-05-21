package com.example.macchiato;

import android.app.Activity;

import com.example.macchiato.Models.GlobalApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class EditJson {
    public EditJson(){}

    public void crearJson(){
        String myjson = new Gson().toJson(GlobalApplication.userProfile);
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
        GlobalApplication.userAct = (String) jsonMap.get("userName");
        GlobalApplication.emailAct = (String) jsonMap.get("email");
        //Toast.makeText(getApplicationContext(), jsonMap.toString(), Toast.LENGTH_SHORT).show();
    }


    public String leerFichero(){
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
        }catch (Exception e){}
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
