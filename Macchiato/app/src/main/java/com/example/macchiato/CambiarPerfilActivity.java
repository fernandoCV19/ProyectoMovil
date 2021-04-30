package com.example.macchiato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CambiarPerfilActivity extends AppCompatActivity {
    EditText contAct,contNueva,contConfir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_perfil);
        contAct = findViewById(R.id.cc_contrActual_id);
        contNueva = findViewById(R.id.cc_contrNueva_id);
        contConfir = findViewById(R.id.cc_repetir_contrNueva_id);

    }

    public void guardarCambios(View view){
        String contActText=contAct.getText().toString().trim();
        String contNuevaText=contNueva.getText().toString().trim();
        String contConfirText=contConfir.getText().toString().trim();

        


        startActivity(new Intent(CambiarPerfilActivity.this,Navigation_bottom.class));
    }
}