package com.example.macchiato;

public class MateriaElement {
    private String nombreMateria;
    private String codCarrera;
    
    public MateriaElement(String nombreMateria, String codCarrera) {
        this.nombreMateria = nombreMateria;
        this.codCarrera = codCarrera;
    }

    public String getNombreMateria() {
        return nombreMateria;
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
