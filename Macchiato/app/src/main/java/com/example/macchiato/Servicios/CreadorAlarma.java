package com.example.macchiato.Servicios;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.AlarmClock;

import androidx.annotation.RequiresApi;

import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.Grupo;

import java.util.ArrayList;

public class CreadorAlarma {
    public CreadorAlarma(){}

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void crear(Context context) throws Exception {
        RegistroJSON rj = new RegistroJSON();
        ConsultorMaterias cm = new ConsultorMaterias();
        ArrayList<Integer> lista = rj.getMateriasTomadas(context, "registro.json");
        ArrayList<ConsultorMaterias.Par> gruposList = cm.devolverGrupos(lista);
        for(ConsultorMaterias.Par p: gruposList){

            Grupo g = p.getGrupo();
            Alarma alarma = new Alarma();
            String aula;
            String materia = p.getMateria();
            int hora, minuto;
            ArrayList<Clase> clases = g.getClases();
            for(Clase c: clases){
                ArrayList<Integer> dias = new ArrayList<>();
                dias.add(getDia(c.getDia().toString()));
                aula = c.getAula();

                String[] hora_minuto = c.getHoraInicio().split(":");
                hora = Integer.parseInt(hora_minuto[0]);
                minuto = Integer.parseInt(hora_minuto[1]);

                alarma.establerAlarma(aula+" "+materia,hora, minuto,dias, context);
            }
        }
    }
    public void borrar(Context context) throws Exception {
        RegistroJSON rj = new RegistroJSON();
        ConsultorMaterias cm = new ConsultorMaterias();
        ArrayList<Integer> lista = rj.getMateriasTomadas(context,"registro.json");
        ArrayList<ConsultorMaterias.Par> gruposList = cm.devolverGrupos(lista);

        for(ConsultorMaterias.Par p: gruposList){
            Grupo g = p.getGrupo();
            Alarma alarma = new Alarma();
            String aula;
            String materia = p.getMateria();
            int hora, minuto;
            ArrayList<Clase> clases = g.getClases();
            for(Clase c: clases){
                aula = c.getAula();
                alarma.cancelarAlarma(aula+" "+materia, context);
            }
        }
    }
    private int getDia(String dia){
        if(dia.contains("LUNES")) return 2;
        else if(dia.contains("MARTES")) return 3;
        else if(dia.contains("MIERCOLES")) return 4;
        else if(dia.contains("JUEVES")) return 5;
        else return 6;
    }
}
