package com.example.macchiato.Models;
import com.example.macchiato.Models.Grupo;

import java.util.ArrayList;

public class Materia {

    private int id;
    private String nombre;
    private char nivel;
    private ArrayList<Grupo> grupos;
    private String color;
    private String codigo;



    public Materia(int id, String nombre, char nivel, ArrayList<Grupo> grupos, String color, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.grupos = grupos;
        this.color = color;
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getNivel() {
        return nivel;
    }

    public void setNivel(char nivel) {
        this.nivel = nivel;
    }

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(ArrayList<Grupo> grupos) {
        this.grupos = grupos;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
