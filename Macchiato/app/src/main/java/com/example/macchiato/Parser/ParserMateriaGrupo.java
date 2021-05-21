package com.example.macchiato.Parser;

import android.app.Application;
import android.content.Context;

import com.example.macchiato.Enums.Dia;
import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.GlobalApplication;
import com.example.macchiato.Models.GrupoModelParser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ParserMateriaGrupo {

    public ArrayList<GrupoModelParser> parserMateriaGrupo(String json) throws Exception
    {
        //LectorJson lectorJson = new LectorJson();
        //String json = lectorJson.loadJSONFromAsset("materias.json",context);
        Object obj = new JSONParser().parse(json);

        JSONObject jo =(JSONObject) obj;
        JSONObject joc;

        JSONArray mats = (JSONArray) jo.get("MATERIAS");
        JSONArray clas;
        Iterator itr = mats.iterator();
        Iterator itr2;
        ArrayList<GrupoModelParser> materias = new ArrayList<GrupoModelParser>();
        int id;
        String docente, horaInicio, horaFinal, aula, grupo;
        String nombre;
        char nivel;
        Dia dia;
        ArrayList<Clase> clases;
        String codigo;
        while (itr.hasNext())
        {
            jo = (JSONObject)itr.next();

            id = Integer.parseInt((String)jo.get("id"));
            grupo = ((String)jo.get("grupo"));
            nombre = (String) jo.get("nombreMateria");
            docente = (String) jo.get("docente");
            nivel = ((String) jo.get("nivel")).charAt(0);
            codigo = ((String) jo.get("codigo"));

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
            materias.add(new GrupoModelParser(id,nombre,docente,nivel,grupo,clases, codigo));
        }
        return materias;
    }
}
