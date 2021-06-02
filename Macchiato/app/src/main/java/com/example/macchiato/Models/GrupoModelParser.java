package com.example.macchiato.Models;

import java.util.ArrayList;

public class GrupoModelParser {
    private int id;
    private String nombre;
    private String docente;
    private char nivel;
    private String grupo;
    private ArrayList<Clase> clases;
    private String codigo;

    public GrupoModelParser(int id, String nombre, String docente,
                            char nivel, String grupo, ArrayList<Clase> clases, String codigo){
        this.id = id;
        this.nombre = nombre;
        this.docente = docente;
        this.nivel = nivel;
        this.grupo = grupo;
        this.clases = clases;
        this.codigo = codigo;
    }

    public int getID(){return id;}

    public String getGrupo(){return grupo;}

    public String getNombre(){return nombre;}

    public String getDocente(){return docente;}

    public char getNivel(){return nivel;}

    public ArrayList<Clase> getClases(){return clases;}

    public String getCodigo() {
        return codigo;
    }
}
