package com.example.macchiato;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class HistorialAcademicoActivity extends AppCompatActivity {
   private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_academico) ;
        String [] opciones={"ingles","algebra","calculo"};
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opciones);
        sp.setAdapter(adapter);
    }

}