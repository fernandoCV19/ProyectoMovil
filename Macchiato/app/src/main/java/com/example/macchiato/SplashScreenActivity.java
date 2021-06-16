package com.example.macchiato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class SplashScreenActivity extends AppCompatActivity {

    /**
     * llama al SystemClock.sleep para detener la app por 1.8 segundos y asi los datos de la Firebase se guarden correctamente
     * crea un intent del activity "SplashScreenActivity" y lo inicializa
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemClock.sleep(1800);
        startActivity(new Intent(SplashScreenActivity.this,Navigation_bottom.class));
        finishAffinity();
    }
}