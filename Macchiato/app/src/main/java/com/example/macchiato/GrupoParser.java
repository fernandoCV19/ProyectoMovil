package com.example.macchiato;
import java.util.*;
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

    public int getID(){return id;}

    public int getGrupo(){return grupo;}

    public String getNombre(){return nombre;}

    public String getDocente(){return docente;}

    public char getNivel(){return nivel;}

    public ArrayList<Clase> getClases(){return clases;}
}
