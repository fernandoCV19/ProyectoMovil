package com.example.macchiato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CambiarPerfilActivity extends AppCompatActivity {
    EditText contAct,contNueva,contConfir;
    private FirebaseUser user;
    //private DatabaseReference reference;
    //private String thisUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_perfil);
        asignarId();
        inicializarUser();
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
        user.updatePassword(contNuevaText).addOnSuccessListener(new OnSuccessListener<Void>() {
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
        contAct = findViewById(R.id.cc_contrActual_id);
        contNueva = findViewById(R.id.cc_contrNueva_id);
        contConfir = findViewById(R.id.cc_repetir_contrNueva_id);
    }
    private void inicializarUser(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        //reference= FirebaseDatabase.getInstance().getReference("User");
        //thisUserId=user.getUid();
    }
    private void mensajeError(EditText cont,String texto){
        cont.setError(texto);
        cont.requestFocus();
    }

}