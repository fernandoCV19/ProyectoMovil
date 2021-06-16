package com.example.macchiato.Parser;

import com.example.macchiato.Models.MateriaNivel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * Parser de el archivo materiasNivel.json, utiliza el modelo MateriaNivel
 * */
public class MateriaNivelParser {
    public MateriaNivelParser(){

    }
    public ArrayList<MateriaNivel> parserMateriaNivel(String json) throws Exception{

        Object obj = new JSONParser().parse(json);

        JSONObject jo = (JSONObject) obj;

        JSONArray materias = (JSONArray) jo.get("MATERIAS");
        Iterator itr = materias.iterator();

        ArrayList<MateriaNivel> materiasList = new ArrayList<MateriaNivel>();
        int id;
        char niv;
        String nombre;
        while (itr.hasNext())
        {
            jo = (JSONObject)itr.next();

            id = Integer.parseInt((String)jo.get("codigo"));
            niv = ((String)jo.get("nivel")).charAt(0);
            nombre = (String) jo.get("nombreMateria");
            materiasList.add(new MateriaNivel(id, niv, nombre));
        }
        return materiasList;
    }
}
