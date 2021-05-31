package com.example.macchiato.Servicios;

import com.example.macchiato.Models.MateriaNota;

import java.util.ArrayList;

public class EstadisticaHistorialAcademico {

    private ArrayList<MateriaNota> listaMaterias;
    public EstadisticaHistorialAcademico(ArrayList<MateriaNota> listaMaterias){
        this.listaMaterias=listaMaterias;
    }

    public double calcularPromedioGeneral(){
        double suma = 0;
        double sumaMateriasA = 0;
        double promedioGeneral = 0;
        double promedioMa = 0;
        for (MateriaNota materiaNota : listaMaterias) {
            suma += materiaNota.getNota();

        }
        if (listaMaterias.size() != 0) {
            promedioGeneral = suma / listaMaterias.size();
        } else {
            promedioGeneral = suma;
        }

        return promedioGeneral;

    }


}
