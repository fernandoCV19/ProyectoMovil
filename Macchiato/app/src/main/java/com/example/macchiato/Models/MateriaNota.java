package com.example.macchiato.Models;

public class MateriaNota {
    private String materiaID;
    private int nota;
    private boolean seleccionado;

    public MateriaNota(String materia, int nota){
        this.materiaID = materia;
        this.nota = nota;

    }
    public String getMateriaId(){return materiaID;}
    public int getNota(){return nota;}
    @Override
    public boolean equals(Object obj) {
        MateriaNota p= (MateriaNota) obj;

        return p.getMateriaId().equals(this.getMateriaId());

    }
    public boolean esSeleccionado (){

        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }


}
