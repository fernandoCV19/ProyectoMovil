package com.example.macchiato.Models;

public class MateriaNota {
    private String materia;
    private int nota;
    private boolean seleccionado;
    public MateriaNota(String materia, int nota){
        this.materia = materia;
        this.nota = nota;

    }
    public String getMateria(){return materia;}
    public int getNota(){return nota;}
    @Override
    public boolean equals(Object obj) {
        MateriaNota p= (MateriaNota) obj;

        return p.getMateria().equals(this.getMateria());

    }
    public boolean esSeleccionado (){

        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}
