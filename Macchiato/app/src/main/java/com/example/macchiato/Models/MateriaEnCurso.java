package com.example.macchiato.Models;

public class MateriaEnCurso {
    private boolean checked;
    private int id;

    public MateriaEnCurso(){}

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
