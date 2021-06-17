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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
/**
 * Activity de cambio de perfil
 * */
public class CambiarPerfilActivity extends AppCompatActivity {
    EditText contAct,contNueva,contConfir;

    /**
     * llama al metodo asignarId
     * aplica el contenido del layout activity_cambiar_perfil
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cambiar_perfil);
        asignarId();
    }

    /**
     * verifica los campos de texto para cambiar la contrasena desde la interfaz
     * verifica que ambos campos no sean nulos
     * veirfica que la nueva contrasena sea mayor a 6 caracteres
     * verifica que haya repetido la contrasena correctamente
     */
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

    /**
     * nos permite obtener los elementos del layout y manejarlos desde codigo
     */
    private void asignarId(){
        contNueva = findViewById(R.id.cc_contrNueva_id);
        contConfir = findViewById(R.id.cc_repetir_contrNueva_id);
    }
    /**
     *nos ayudara a mostrar los mensajes de error cuando la validacion de los campos de texto falle
     * recibe como parametro un EditText que es el cual contendra el error y un String que es el mensaje de error
     */
    private void mensajeError(EditText cont,String texto){
        cont.setError(texto);
        cont.requestFocus();
    }

}