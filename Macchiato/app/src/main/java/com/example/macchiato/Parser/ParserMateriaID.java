package com.example.macchiato.Parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ParserMateriaID {

    private static HashMap<String, Par> ids;

    private static int idAuxiliar = 0;

    public static HashMap<String, Par> getIds() {
        return ids;
    }

    public void iniciarIDs(String json) throws ParseException {
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
        if(ids != null && ids.containsKey(materia)){
            return ids.get(materia).id;
        }else {
            idAuxiliar--;
            return idAuxiliar;
        }

    }

    public ArrayList<String> getRequisitos(String materia){
        if(ids != null && ids.containsKey(materia)){
            return ids.get(materia).requisitos;
        }else{
            ArrayList<String> respuesta = new ArrayList<>();
            return respuesta;
        }

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
