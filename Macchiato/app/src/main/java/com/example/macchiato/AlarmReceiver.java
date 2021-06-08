package com.example.macchiato;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.util.concurrent.ThreadLocalRandom;


    public class AlarmReceiver extends BroadcastReceiver {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onReceive(Context context, Intent intent) {
            String titulo="", mensaje="", tonoUri="";
            titulo = intent.getStringExtra("nombre");
            mensaje = intent.getStringExtra("mensaje");
            tonoUri = intent.getStringExtra("sonido");
            int notiID = ThreadLocalRandom.current().nextInt(0, 1000+ 1);

            Intent home = new Intent(context,Navigation_bottom.class);
            home.putExtra("notiId", notiID);
            home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,
                    notiID,home,PendingIntent.FLAG_ONE_SHOT);
            NotificationHelper notificationHelper = new NotificationHelper(context);
            NotificationCompat.Builder nb = notificationHelper.getChannelNotification(titulo,
                    mensaje,tonoUri);
            nb.setContentIntent(pendingIntent);

            Notification mNotification = nb.build();
            mNotification.flags |= Notification.FLAG_INSISTENT;
            notificationHelper.getNotificationManager().notify(notiID, mNotification);
            //Toast.makeText(context, tonoUri.getPath(), Toast.LENGTH_LONG).show();
            Log.d("TONO2", tonoUri);
        }
    }
