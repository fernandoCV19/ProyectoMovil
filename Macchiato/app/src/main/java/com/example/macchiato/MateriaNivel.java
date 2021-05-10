package com.example.macchiato;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.Iterator;

public class MateriaNivel {
    private int codMateria;
    private char nivel;
    private String nombreMateria;
    private String color;

    public MateriaNivel(){

    }

    public MateriaNivel(int codMateria, char nivel, String nombreMateria) {
        this.codMateria = codMateria;
        this.nivel = nivel;
        this.nombreMateria = nombreMateria;
        this.color="#ff70c6";
    }

    public int getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(int codMateria) {
        this.codMateria = codMateria;
    }

    public char getNivel() {
        return nivel;
    }

    public void setNivel(char nivel) {
        this.nivel = nivel;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ArrayList<MateriaNivel> parser(String json) throws Exception
    {
        Object obj = new JSONParser().parse(json);
        //Object obj = new JSONParser().parse(new FileReader("materias.json"));
        JSONObject jo = (JSONObject) obj;
        JSONObject joc;

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

    public MateriaNivel onClick(){
        return new MateriaNivel(codMateria,nivel,nombreMateria);
    }
}
