package com.example.macchiato.Parser;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;

import com.example.macchiato.Enums.Dia;
import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.GrupoModelParser;
import com.example.macchiato.Models.MyApp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Parser  {

    private Context context;

    public ArrayList<GrupoModelParser> parser() throws Exception
    {
        AssetManager assetManager = Resources.getSystem().getAssets();

        //Object obj2 = new JSONParser().parse(new FileReader("D:\\Documents\\UMSS\\programacion movil\\ProyectoMovil\\Macchiato\\app\\src\\main\\assets\\materias.json"));


        String json = null;
        try {
            MyApp myApp = new MyApp();
            InputStream is = myApp.getContext().getAssets().open("materias.json");//assetManager.open("D:\\Documents\\UMSS\\programacion movil\\ProyectoMovil\\Macchiato\\app\\src\\main\\assets\\materias.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println(json);

        LectorJson lectorJson = new LectorJson();
        //String json = lectorJson.loadJSONFromAsset(context, "materias.json");
        Object obj = new JSONParser().parse(json);

        JSONObject jo =(JSONObject) obj;
        JSONObject joc;

        JSONArray mats = (JSONArray) jo.get("MATERIAS");
        JSONArray clas;
        Iterator itr = mats.iterator();
        Iterator itr2;
        ArrayList<GrupoModelParser> materias = new ArrayList<GrupoModelParser>();
        int id,grupo;
        String docente, horaInicio, horaFinal, aula;
        String nombre;
        char nivel;
        Dia dia;
        ArrayList<Clase> clases;
        while (itr.hasNext())
        {
            jo = (JSONObject)itr.next();

            id = Integer.parseInt((String)jo.get("id"));
            grupo = Integer.parseInt((String)jo.get("grupo"));
            nombre = (String) jo.get("nombreMateria");
            docente = (String) jo.get("docente");
            nivel = ((String) jo.get("nivel")).charAt(0);

            clas = (JSONArray) jo.get("clases");
            itr2 = clas.iterator();
            clases = new ArrayList<>();
            while(itr2.hasNext())
            {
                joc = (JSONObject)itr2.next();
                dia = Dia.valueOf((String)joc.get("dia"));
                horaInicio = (String) joc.get("horaInicio");
                horaFinal = (String) joc.get("horaFinal");
                aula = (String) joc.get("aula");

                clases.add(new Clase(dia, horaInicio, horaFinal, aula));
            }
            materias.add(new GrupoModelParser(id,nombre,docente,nivel,grupo,clases));
        }
        return materias;
    }

    public int getID(String nombreMat) throws Exception {
        LectorJson lectorJson = new LectorJson();
        int id = 0;
        Object obj = new JSONParser().parse(lectorJson.loadJSONFromAsset(context, "materiasID.json"));
        JSONArray materias = (JSONArray) obj;
        JSONObject jo;
        Iterator it = materias.iterator();
        boolean encontrado = false;
        while(!encontrado && it.hasNext()){
            jo = (JSONObject)it.next();
            String nombre = (String) jo.get("nombreMateria");
            if(nombreMat.equals(nombre)) {
                id = Integer.parseInt((String)jo.get("id"));
                encontrado = true;
            }
        }
        return id;
    }
}
