package com.example.macchiato.Servicios;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.AlarmClock;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class Alarma {
    public Alarma(){}/*
    public Intent getIntents(String mensaje, int hora, int minuto, ArrayList<Integer> dias){
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, mensaje)
                .putExtra(AlarmClock.EXTRA_HOUR, hora)
                .putExtra(AlarmClock.EXTRA_MINUTES, minuto)
                .putExtra(AlarmClock.EXTRA_DAYS, dias)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        return intent;
    }*/
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void establerAlarma(String mensaje, int hora, int minuto, ArrayList<Integer> dias, Context context) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, mensaje)
                .putExtra(AlarmClock.EXTRA_HOUR, hora)
                .putExtra(AlarmClock.EXTRA_MINUTES, minuto)
                .putExtra(AlarmClock.EXTRA_DAYS, dias)
                .putExtra(AlarmClock.EXTRA_SKIP_UI,true);

        context.startActivity(intent);
    }
    public void cancelarAlarma(String mensaje, Context context){
        Intent alarmIntent = new Intent(AlarmClock.ACTION_DISMISS_ALARM);
        alarmIntent.putExtra(AlarmClock.ALARM_SEARCH_MODE_LABEL,mensaje);
        context.startActivity(alarmIntent);
    }
}
