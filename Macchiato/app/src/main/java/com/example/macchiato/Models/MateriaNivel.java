package com.example.macchiato.Models;

public class MateriaNivel {
    private int codMateria;
    private char nivel;
    private String nombreMateria;
    private String color;

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



}
