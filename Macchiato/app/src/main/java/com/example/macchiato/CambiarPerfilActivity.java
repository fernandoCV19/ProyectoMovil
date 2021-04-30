package com.example.macchiato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CambiarPerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_perfil);
    }

    public void guardarCambios(View view){
        startActivity(new Intent(CambiarPerfilActivity.this,Navigation_bottom.class));
    }
}