package com.example.macchiato.Parser;

import android.content.Context;

import com.example.macchiato.Enums.Dia;
import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.GrupoModelParser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.Iterator;

public class ParserMateriaGrupo {

    private Context context;

    public ArrayList<GrupoModelParser> parserMateriaGrupo() throws Exception
    {
        LectorJson lectorJson = new LectorJson();
        String json = lectorJson.loadJSONFromAsset("materias.json");
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
}
