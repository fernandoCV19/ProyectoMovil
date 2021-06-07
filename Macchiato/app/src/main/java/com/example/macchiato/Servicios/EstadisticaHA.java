package com.example.macchiato.Servicios;

import com.example.macchiato.Models.MateriaNota;

import java.util.ArrayList;

public class EstadisticaHA {

    private ArrayList<MateriaNota> listaMaterias;
    private ArrayList<MateriaNota> mostrarAprobadas;
    private ArrayList<MateriaNota> mostrarReprobadas;

    public EstadisticaHA(ArrayList<MateriaNota> listaMaterias, ArrayList<MateriaNota> mostrarAprobadas,ArrayList<MateriaNota> mostrarReprobadas) {
        this.listaMaterias = listaMaterias;
        this.mostrarAprobadas=mostrarAprobadas;
        this.mostrarReprobadas=mostrarReprobadas;
    }

    public double calcularPromedioGeneral() {
        double suma = 0;

        double promedioGeneral = 0;

        for (MateriaNota materiaNota : listaMaterias) {
            suma += materiaNota.getNota();

        }
        if (listaMaterias.size() != 0) {
            double n = suma / listaMaterias.size();
            promedioGeneral = (double) Math.round(n * 100d) / 100d;
        }


        return promedioGeneral;

    }

    public double calcularPromedioMateriasA() {
        double sumaMateriasA = 0;
        double promedioMa = 0;

        for (MateriaNota materiaNota : mostrarAprobadas) {
            sumaMateriasA += materiaNota.getNota();

        }
        if (mostrarAprobadas.size() != 0) {
            double n = sumaMateriasA / mostrarAprobadas.size();
            promedioMa = (double) Math.round(n * 100d) / 100d;
        }


    return promedioMa;
    }

    public int getMateriasAprobadas (){

        return    mostrarAprobadas.size();

    }
    public int getMateriasReprobadas (){

        return    mostrarReprobadas.size();

    }
    public  int getListaMaterias(){

        return listaMaterias.size();
    }

}
