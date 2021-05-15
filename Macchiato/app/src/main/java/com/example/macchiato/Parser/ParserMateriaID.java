package com.example.macchiato.Parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Iterator;

public class ParserMateriaID {
    public int getID(String nombreMat) throws Exception {
        LectorJson lectorJson = new LectorJson();
        int id = 0;
        Object obj = new JSONParser().parse(lectorJson.loadJSONFromAsset("materiasID.json"));
        JSONObject joMaterias = (JSONObject) obj;
        JSONArray materias = (JSONArray) joMaterias.get("MATERIAS");
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
