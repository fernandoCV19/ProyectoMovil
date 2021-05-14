package com.example.macchiato.Parser;

import androidx.appcompat.app.AppCompatActivity;

import com.example.macchiato.Models.MateriaNivel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.Iterator;

public class MateriaNivelParser extends AppCompatActivity {
    public MateriaNivelParser(){

    }
    public ArrayList<MateriaNivel> parser(String json) throws Exception{

        LectorJson lectorJson = new LectorJson();
        Object obj = new JSONParser().parse(lectorJson.loadJSONFromAsset(this, "materiasNivel.json"));
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
