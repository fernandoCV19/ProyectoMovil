package com.example.macchiato;

public class ListElement {
    public String nomMateriaHorario;
    public String nomDocente;
    public String horario;


    public ListElement(String nomMateriaHorario, String nomDocente, String horario) {
        this.nomMateriaHorario = nomMateriaHorario;
        this.nomDocente = nomDocente;
        this.horario = horario;

    }

    public String getNomMateriaHorario() {
        return nomMateriaHorario;
    }

    public void setNomMateriaHorario(String nomMateriaHorario) {
        this.nomMateriaHorario = nomMateriaHorario;
    }

    public String getNomDocente() {
        return nomDocente;
    }

    public void setNomDocente(String nomDocente) {
        this.nomDocente = nomDocente;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }


}
