package com.example.macchiato.Servicios.Alarma;

import android.app.IntentService;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.macchiato.R;

public class RebootServiceClass extends IntentService {

    /**
     * Re-envia las notificaciones al sistema
     * @param name Used to name the worker thread, important only for debugging.
     */
    public RebootServiceClass(String name) {
        super(name);
        startForeground(1, new Notification());
    }

    public RebootServiceClass() {
        super("RebootServiceClass");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String intentType = intent.getExtras().getString("caller");
        if (intentType == null) return;
        if (intentType.equals("RebootReceiver")) {
            SharedPreferences settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
            CreadorAlarma.setAllAlarms(getApplicationContext());
            //CreadorAlarma.setAlarm(settings.getInt("alarmID", 0), settings.getLong("alarmTime", 0), this, "");
        }
    }
}
