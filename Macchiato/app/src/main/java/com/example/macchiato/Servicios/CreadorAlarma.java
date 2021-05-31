package com.example.macchiato.Servicios;

import android.content.Context;

import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.Grupo;
import com.example.macchiato.Models.Materia;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class CreadorAlarma {
    public CreadorAlarma(){}

    public void crear(Context context) throws Exception {
        RegistroJSON rj = new RegistroJSON();
        ConsultorMaterias cm = new ConsultorMaterias();
        ArrayList<Integer> lista = rj.getMateriasTomadas(context);
        ArrayList<Grupo> gruposList = cm.devolverGrupos(lista);

        for(Grupo g: gruposList){
            Alarma alarma = new Alarma();
            String aula, materia;
            int hora, minuto;
            ArrayList<Clase> clases = g.getClases();
            for(Clase c: clases){

            }
        }
    }
    private int getDia(String dia){
        if(dia.contains("LUNES")) return 1;
        else if(dia.contains("MARTES")) return 2;
        else if(dia.contains("MIERCOLES")) return 3;
        else if(dia.contains("JUEVES")) return 4;
        else return 5;
    }
}
