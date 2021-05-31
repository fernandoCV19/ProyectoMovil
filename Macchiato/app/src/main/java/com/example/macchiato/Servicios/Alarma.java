package com.example.macchiato.Servicios;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;

import java.util.ArrayList;

public class Alarma {
    public Alarma(){}
    public Intent getIntents(String mensaje, int hora, int minuto, ArrayList<Integer> dias){
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, mensaje)
                .putExtra(AlarmClock.EXTRA_HOUR, hora)
                .putExtra(AlarmClock.EXTRA_MINUTES, minuto)
                .putExtra(AlarmClock.EXTRA_DAYS, dias);
        return intent;
    }
    public void establerAlarma(String mensaje, int hora, int minuto, ArrayList<Integer> dias,Context context) throws InterruptedException {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, mensaje)
                .putExtra(AlarmClock.EXTRA_HOUR, hora)
                .putExtra(AlarmClock.EXTRA_MINUTES, minuto)
                .putExtra(AlarmClock.EXTRA_DAYS, dias)
                .putExtra(AlarmClock.EXTRA_SKIP_UI,true)
                .addFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT);

        /*if(intent.resolveActivity(context.getPackageManager())==null){
            context.startActivity();
        }*/

        context.startService(intent);


    }
    public void cancelarAlarma(String mensaje, Context context){
        Intent alarmIntent = new Intent(AlarmClock.ACTION_DISMISS_ALARM);
        alarmIntent.putExtra(AlarmClock.ALARM_SEARCH_MODE_LABEL,mensaje);
        context.startActivity(alarmIntent);
    }
}
