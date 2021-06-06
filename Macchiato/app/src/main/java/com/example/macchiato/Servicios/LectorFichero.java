package com.example.macchiato.Servicios;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.macchiato.Models.MateriaNota;
import com.example.macchiato.Models.User;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
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

    /*
    * Recibe un String y lo escribe el fichero indicado, reescribe lo que estaba antes
    * */
    public boolean escribirFichero(String fichero, String contenido, Context context){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(context.openFileOutput(fichero, Activity.MODE_PRIVATE));
            archivo.write(contenido);
            archivo.flush();
            archivo.close();
            return true;
        } catch (IOException e ){
            e.printStackTrace();
            return false;
        }
    }

    public void crearJson(Context context, User user, String nombreArchivo){
        String myjson = new Gson().toJson(user);
        jsonMap = new Gson().fromJson(myjson, new TypeToken<HashMap<String, Object>>() {}.getType());
        ArrayList<Double> aux = (ArrayList<Double>) jsonMap.get("materiasActuales");
        ArrayList<Integer> aux2= new ArrayList<>();
        for(Double d:aux){
            aux2.add(d.intValue());
        }
        jsonMap.put("materiasActuales",aux2);
        editMap("materiasAprobadas");
        editMap("materiasReprobadas");

        myjson = new Gson().toJson(jsonMap);
        escribirFichero(nombreArchivo, myjson, context);
    }


    /*
    Recibe un nombre y devuelve en un String -> Si no existe el fichero crea -> Al crear iniciar todos los campos en vacio
    * */
    public String leerFichero(Context context, String nombreArchivo) throws FileNotFoundException, JSONException {
        FileInputStream fileInputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        String texto ="";
        try {
            fileInputStream = context.openFileInput(nombreArchivo);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while((texto = bufferedReader.readLine())!= null){
                stringBuilder.append(texto);
            }
        }catch (Exception e){
            RegistroJSON rj = new RegistroJSON();
            rj.genararVacio(context, nombreArchivo);
            return leerFichero(context, nombreArchivo);
        }
        //Toast.makeText(context, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
        return stringBuilder.toString();
    }


    public Map<String,Object> devolverMapa(Context context, String nombreArchivo){
        String res="";
        try {
            res=leerFichero(context, nombreArchivo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(res, new TypeToken<HashMap<String, Object>>() {}.getType());
    }

    private void editMap(String nombreCampo){
        ArrayList<LinkedTreeMap<String,Object>> arrayAux = (ArrayList<LinkedTreeMap<String,Object>>) jsonMap.get(nombreCampo);
        for (LinkedTreeMap<String,Object> miniMap : arrayAux){
            miniMap.put("materiaID",(Integer.parseInt((String) miniMap.get("materiaId"))));

            miniMap.put("nota",((Double)(miniMap.get("nota"))).intValue());
        }
        jsonMap.put(nombreCampo,arrayAux);
    }


}
