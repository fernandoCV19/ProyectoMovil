package com.example.macchiato.Parser;

import android.content.Context;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.Iterator;

public class ParserMateriaID {

    private static HashMap<String,Integer> ids;
    public void iniciarIDs(Context context) throws Exception {
        ids = new HashMap<>();
        LectorJson lectorJson = new LectorJson();
        Object obj = new JSONParser().parse(lectorJson.loadJSONFromAsset("materiasID.json",context));
        JSONObject joMaterias = (JSONObject) obj;
        JSONArray materias = (JSONArray) joMaterias.get("MATERIAS");
        JSONObject jo;
        Iterator it = materias.iterator();
        while(it.hasNext()){
            jo = (JSONObject)it.next();
            String nombre = (String) jo.get("nombreMateria");
            int id = Integer.parseInt((String)jo.get("id"));
            ids.put(nombre, id);
        }
    }

    public int getID(String materia){
        return ids.get(materia);

    }
}
