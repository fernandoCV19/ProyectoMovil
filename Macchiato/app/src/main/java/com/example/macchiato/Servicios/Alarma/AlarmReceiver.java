package com.example.macchiato.Servicios.Alarma;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.macchiato.AjustesFragment;

import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

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
/*
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onReceive(Context context, Intent intent) {
        String titulo = "", mensaje = "", tonoUri = "/external_primary/audio/media/33";

        titulo = intent.getStringExtra("nombre");
        mensaje = intent.getStringExtra("mensaje");
        tonoUri = intent.getStringExtra("sonido");

        int notiID = ThreadLocalRandom.current().nextInt(0, 1000 + 1);

        Intent home = new Intent(context, AjustesFragment.class);
        home.putExtra("notiId", notiID);
        home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                notiID, home, PendingIntent.FLAG_ONE_SHOT);
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification(titulo,
                mensaje, tonoUri);
        nb.setContentIntent(pendingIntent);

        Notification mNotification = nb.build();
        mNotification.flags |= Notification.FLAG_INSISTENT;
        notificationHelper.getNotificationManager().notify(notiID, mNotification);
        //Toast.makeText(context, tonoUri.getPath(), Toast.LENGTH_LONG).show();
        Log.d("TONO2", tonoUri);
    }*/