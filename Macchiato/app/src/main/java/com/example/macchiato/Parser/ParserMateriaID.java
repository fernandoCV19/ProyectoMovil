package com.example.macchiato.Parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ParserMateriaID {

    private static HashMap<String, Par> ids;

    public void iniciarIDs(String json) throws Exception {
        ids = new HashMap<>();
        Object obj = new JSONParser().parse(json);

        JSONObject joMaterias = (JSONObject) obj;
        JSONArray materias = (JSONArray) joMaterias.get("MATERIAS");
        JSONObject jo;
        Iterator it = materias.iterator();
        while(it.hasNext()){
            jo = (JSONObject)it.next();
            String nombre = (String) jo.get("nombreMateria");
            int id = Integer.parseInt((String)jo.get("id"));

            JSONArray requisitosJson = (JSONArray) jo.get("requisitos");
            Iterator iterator = requisitosJson.iterator();
            ArrayList<String> requisitos = new ArrayList<>();

            while (iterator.hasNext()){
                requisitos.add((String) iterator.next());
            }
            Par par = new Par(id,requisitos);

            ids.put(nombre, par);
        }
    }

    public int getID(String materia){
        return ids.get(materia).id;
    }

    public ArrayList<String> getRequisitos(String materia){
        return ids.get(materia).requisitos;

    }

    public class Par{
        int id;
        ArrayList<String> requisitos;

        public Par(int id, ArrayList<String> requisitos){
           this.id = id;
           this.requisitos = requisitos;
        }

    }
}
