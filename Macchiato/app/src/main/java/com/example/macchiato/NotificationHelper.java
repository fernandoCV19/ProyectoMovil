package com.example.macchiato;

import android.annotation.TargetApi;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.content.Context;
import android.content.ContextWrapper;

import android.graphics.Color;

import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;


public class NotificationHelper extends ContextWrapper {

    private NotificationManager notificationManager;

    public static final String CHANNEL_ID = "channel1";
    public static final String CHANNEL_NAME = "Channel 1";

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            createChannels();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels() {
        NotificationChannel notificationChannel = new NotificationChannel(
                CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setLightColor(R.color.browser_actions_bg_grey);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

        getNotificationManager().createNotificationChannel(notificationChannel);
    }

    public NotificationManager getNotificationManager() {
        if (notificationManager == null)
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        return notificationManager;
    }

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.N)
    public NotificationCompat.Builder getChannelNotification(String title, String message, String sound) {
        //TODO: hacer q suene fuerte incluso si el volimen del sistema está muteado
        Uri parcialUri = Uri.parse("content://media");
        Uri sonido = Uri.withAppendedPath(parcialUri, "" + Uri.parse(sound));
        //Log.d("TONO1", sonido.getPath());
        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_libro_abierto)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setLights(Color.RED, 3000, 3000)
                .setSound(sonido)
                .setPriority(NotificationManager.IMPORTANCE_HIGH);
    }
    //TODO: hacer q funcione ↓ encender pantalla when device is lock
    public void encenderPantalla() {
        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        assert pm != null;
        boolean isScreenOn = pm.isInteractive();
        if (!isScreenOn) {
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "MyLock");
            wl.acquire(10000);
            PowerManager.WakeLock wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyCpuLock");
            wl_cpu.acquire(10000);
        }
    }

}
