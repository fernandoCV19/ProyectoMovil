package com.example.macchiato.Servicios;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.AlarmClock;

import androidx.annotation.RequiresApi;

import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.Grupo;

import java.util.ArrayList;

public class Alarma {

    private String mensaje;
    private int hora;
    private int minutos;
    private ArrayList<Integer> dias;

    public Alarma(String mensaje, int hora, int minutos, String dia){
        this.mensaje = mensaje;
        this.hora = hora;
        this.minutos = minutos;
        dias = new ArrayList<Integer>(getDia(dia));
    }
    public void establerAlarma(Context context) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, mensaje)
                .putExtra(AlarmClock.EXTRA_HOUR, hora)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutos)
                .putExtra(AlarmClock.EXTRA_DAYS, dias)
                .putExtra(AlarmClock.EXTRA_SKIP_UI,true);
        context.startActivity(intent);
    }
    public void cancelarAlarma(Context context){
        Intent alarmIntent = new Intent(AlarmClock.ACTION_DISMISS_ALARM);
        alarmIntent.putExtra(AlarmClock.ALARM_SEARCH_MODE_LABEL,mensaje).putExtra(AlarmClock.EXTRA_SKIP_UI,true);
        context.startActivity(alarmIntent);
    }
    private int getDia(String dia){
        if(dia.contains("LUNES")) return 2;
        else if(dia.contains("MARTES")) return 3;
        else if(dia.contains("MIERCOLES")) return 4;
        else if(dia.contains("JUEVES")) return 5;
        else return 6;
    }
}
