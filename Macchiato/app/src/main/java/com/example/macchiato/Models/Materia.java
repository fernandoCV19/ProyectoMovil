package com.example.macchiato.Models;
import com.example.macchiato.Models.Grupo;

import java.util.ArrayList;
/**
 * Modelo de una materia para el manejo de informacion de los archivos .json
 * */
public class Materia {

    private int id;
    private String nombre;
    private char nivel;
    private ArrayList<Grupo> grupos;
    private String color;
    private String codigo;
    private ArrayList<String> requisitos;

    public Materia(int id, String nombre, char nivel, ArrayList<Grupo> grupos, String color, String codigo, ArrayList<String> requisitos) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.grupos = grupos;
        this.color = color;
        this.codigo = codigo;
        this.requisitos = requisitos;
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
    public ArrayList<String> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(ArrayList<String> requisitos) {
        this.requisitos = requisitos;
    }
}
