package com.example.macchiato.Models;
import com.example.macchiato.Models.Grupo;

import java.util.ArrayList;

public class Materia {

    private int id;
    private String nombre;
    private char nivel;
    private ArrayList<Grupo> grupos;

    public Materia(int id, String nombre, char nivel, ArrayList<Grupo> grupos){
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.grupos = grupos;
    }

    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public char getNivel(){
        return nivel;
    }

    public ArrayList<Grupo> getGrupos(){
        return grupos;
    }
}
