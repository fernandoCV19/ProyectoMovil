package com.example.macchiato.Servicios.Alarma;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Receptor de la alarma del sistema que informa que la hora programada ha llegado
 * */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        TinyDB tinyDB = new TinyDB(context.getApplicationContext());

        if(tinyDB.getBoolean("activado")) {
            Calendar alarmCalendar = Calendar.getInstance();
            alarmCalendar.set(Calendar.DAY_OF_WEEK, intent.getIntExtra("dia", 0));
            alarmCalendar.set(Calendar.HOUR_OF_DAY, intent.getIntExtra("hora", 0));
            alarmCalendar.set(Calendar.MINUTE, intent.getIntExtra("minuto", 0));

            if (alarmCalendar.before(Calendar.getInstance())) {
                alarmCalendar.add(Calendar.DATE, 7);
            }
            CreadorAlarma.setAlarm(intent.getIntExtra("id", 0), alarmCalendar.getTimeInMillis(),
                    context, intent.getStringExtra("nombre"),
                    intent.getIntExtra("hora", 0),
                    intent.getIntExtra("minuto", 0),
                    intent.getIntExtra("dia", 0),intent.getBooleanExtra("activado", false));


            Intent service1 = new Intent(context, NotificationService.class);
            service1.putExtra("nombre", intent.getStringExtra("nombre"));
            service1.setData((Uri.parse("custom://" + System.currentTimeMillis())));
            ContextCompat.startForegroundService(context, service1);
            Log.d("WALKIRIA", " ALARM RECEIVED!!!");
        }
    }

}
