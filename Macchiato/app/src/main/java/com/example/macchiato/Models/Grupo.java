package com.example.macchiato.Models;
import com.example.macchiato.Models.Clase;

import java.util.ArrayList;

public class Grupo {
    private int id;
    private int grupo;
    private String docente;
    private ArrayList<Clase> clases;

    public Grupo(int id, int grupo, String docente, ArrayList<Clase> clases){
        this.id = id;
        this.grupo = grupo;
        this.docente = docente;
        this.clases = clases;
    }

    public int getID(){
        return id;
    }

    public ArrayList<Clase> getClases(){
        return clases;
    }

    public int getGrupo(){
        return grupo;
    }

    public String getDocente(){
        return docente;
    }
}
