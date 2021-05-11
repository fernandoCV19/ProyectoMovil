package com.example.macchiato.Parser;

import com.example.macchiato.Enums.Dia;
import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.GrupoParser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.Iterator;

public class Parser {

    public ArrayList<GrupoParser> parser(String json) throws Exception
    {
        JSONParser parser = new JSONParser();
        JSONObject jo =(JSONObject) parser.parse(json);;
        JSONObject joc;

        JSONArray mats = (JSONArray) jo.get("MATERIAS");
        JSONArray clas;
        Iterator itr = mats.iterator();
        Iterator itr2;
        ArrayList<GrupoParser> materias = new ArrayList<GrupoParser>();
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
            materias.add(new GrupoParser(id,nombre,docente,nivel,grupo,clases));
        }
        return materias;
    }

    public int getID(String nombreMat) throws Exception {
        /*int id = 0;
        Object obj = new JSONParser().parse(new FileReader("materiasID.json"));
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
        return id;*/
        return 0;
    }
}
