package com.example.macchiato.Models;

public class MateriaNota {
    private String materia;
    private int nota;
    public MateriaNota(String materia, int nota){
        this.materia = materia;
        this.nota = nota;
    }
    public String getMateria(){return materia;}
    public int getNota(){return nota;}
}
