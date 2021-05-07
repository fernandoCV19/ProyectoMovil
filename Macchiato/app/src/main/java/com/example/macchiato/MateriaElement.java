package com.example.macchiato;



public class MateriaElement {
    private String nombreMateria;
    private String codCarrera;
    private String color;
    public MateriaElement(String nombreMateria, String codCarrera,String color) {
        this.nombreMateria = nombreMateria;
        this.codCarrera = codCarrera;
        this.color=color;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getCodCarrera() {
        return codCarrera;
    }

    public void setCodCarrera(String codCarrera) {
        this.codCarrera = codCarrera;
    }
}
