package com.example.macchiato.Interfaz.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.macchiato.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class CambiarPerfilActivity extends AppCompatActivity {
    EditText contAct,contNueva,contConfir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cambiar_perfil);
        asignarId();
    }

    public void guardarCambios(View view){
        String contNuevaText=contNueva.getText().toString().trim();
        String contConfirText=contConfir.getText().toString().trim();
        if(contNuevaText.isEmpty()){
            mensajeError(contNueva,"ingrese su contrasena");
            return;
        }
        if(contNuevaText.length()<6){
            mensajeError(contNueva,"la contrasena es muy corta");
            return;
        }
        if(!contConfirText.equals(contNuevaText)){
            mensajeError(contConfir,"las contrasenas son diferentes");
            return;
        }
        FirebaseAuth.getInstance().getCurrentUser().updatePassword(contNuevaText).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                startActivity(new Intent(CambiarPerfilActivity.this, Navigation_bottom.class));
                finishAffinity();
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CambiarPerfilActivity.this, "no se pudo cambiar la contrasena", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void asignarId(){
        contNueva = findViewById(R.id.cc_contrNueva_id);
        contConfir = findViewById(R.id.cc_repetir_contrNueva_id);
    }
    private void mensajeError(EditText cont,String texto){
        cont.setError(texto);
        cont.requestFocus();
    }

}