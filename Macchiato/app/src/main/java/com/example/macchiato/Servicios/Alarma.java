package com.example.macchiato.Servicios;

import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;

import java.util.ArrayList;

public class Alarma {
    public Alarma(){}

    public void establerAlarma(String mensaje, int hora, int minuto, ArrayList<Integer> dias,Context context){
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, mensaje)
                .putExtra(AlarmClock.EXTRA_HOUR, hora)
                .putExtra(AlarmClock.EXTRA_MINUTES, minuto)
                .putExtra(AlarmClock.EXTRA_DAYS, dias);

        context.startActivity(intent);
    }
    public void cancelarAlarma(String mensaje, Context context){
        Intent alarmIntent = new Intent(AlarmClock.ACTION_DISMISS_ALARM);
        alarmIntent.putExtra(AlarmClock.ALARM_SEARCH_MODE_LABEL,mensaje);
        context.startActivity(alarmIntent);
    }
}
