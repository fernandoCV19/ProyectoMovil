package com.example.macchiato;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.io.FileReader;
import java.util.Iterator;

public class GrupoParser {
    private int id;
    private String nombre;
    private String docente;
    private char nivel;
    private int grupo;
    private ArrayList<Clase> clases;

    public GrupoParser(int id, String nombre, String docente,
                       char nivel, int grupo, ArrayList<Clase> clases){
        this.id = id;
        this.nombre = nombre;
        this.docente = docente;
        this.nivel = nivel;
        this.grupo = grupo;
        this.clases = clases;
    }

    public GrupoParser() {

    }

    public int getID(){return id;}

    public int getGrupo(){return grupo;}

    public String getNombre(){return nombre;}

    public String getDocente(){return docente;}

    public char getNivel(){return nivel;}

    public ArrayList<Clase> getClases(){return clases;}

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
}
