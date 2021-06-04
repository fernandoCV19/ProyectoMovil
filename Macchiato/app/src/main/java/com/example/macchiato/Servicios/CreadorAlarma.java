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
    public void crear(Context context, int idGrupo, int clase, boolean crear){
        if(clase <= 3) {
            ConsultorMaterias consultorMaterias = new ConsultorMaterias();
            ArrayList<Integer> id = new ArrayList<>();
            id.add(idGrupo);
            ConsultorMaterias.Par par = consultorMaterias.devolverGrupos(id).get(0);
            Grupo g = par.getGrupo();

            Clase c = g.getClases().get(clase);

            String aula = c.getAula() + " - " + par.getMateria();
            String[] hora_minuto = c.getHoraInicio().split(":");
            int hora = Integer.parseInt(hora_minuto[0]);
            int minuto = Integer.parseInt(hora_minuto[1]);

            Alarma alarma = new Alarma(aula, hora, minuto, c.getDia().toString());
            if (crear) alarma.establerAlarma(context);
            else alarma.cancelarAlarma(context);
        }
    }
}
