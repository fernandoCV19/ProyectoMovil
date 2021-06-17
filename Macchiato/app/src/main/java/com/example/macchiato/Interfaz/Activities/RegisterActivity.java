package com.example.macchiato.Interfaz.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.macchiato.Models.User;
import com.example.macchiato.R;
import com.example.macchiato.Servicios.LectorFichero;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class  RegisterActivity extends AppCompatActivity {

    private EditText user_R,email_R,password_R,confirm_R;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    /**
     *llama a los metodos "asignarId" e "InicializarFirebase" al crearse
     * aplica el contenido del layout activity_register
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        asignarId();
        inicializarFirebase();
        firebaseAuth = FirebaseAuth.getInstance();

    }

    /**
     *se encarga de guardar los datos obtenidos en los campos de texto
     * verifica que estos no sean vacios
     * verifica que el email sea un correo valido (que tenga @ y un . despues del dominio)
     * una vez verificados los datos, crea un User con estos
     * haremos uso de este User para guardarlo en el json
     * haremos uso de este User para mandarlo a la Firebase y guardarlo
     */
    public void registrar(View view) {

        String u = user_R.getText().toString().trim();
        String e = email_R.getText().toString().trim();
        String p = password_R.getText().toString().trim();
        String pp =confirm_R.getText().toString().trim();

        if(u.isEmpty()){
            mensajeError(user_R,"ingrese su usuario.");
            return;
        }
        if(e.isEmpty()){
            mensajeError(email_R,"ingrese su correo");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(e).matches()){
            mensajeError(email_R,"correo invalido");
            return;
        }
        if(p.isEmpty()){
            mensajeError(password_R,"ingrese su contrasena");
            return;
        }
        if(p.length()<6){
            mensajeError(password_R,"la contrasena es muy corta");
            return;
        }
        if(pp.isEmpty()){
            mensajeError(confirm_R,"ingrese la contrasena de nuevo");
            return;
        }
        if (!pp.equals(p)){
            mensajeError(confirm_R,"la contrasena no coincide con la anterior");
            return;
        }

        User user = new User(u,e);
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(),p)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            FirebaseUser us= FirebaseAuth.getInstance().getCurrentUser();
                            //user.setUid(us.getUid());
                            LectorFichero lector = new LectorFichero();
                            lector.crearJson(getApplicationContext(),user,"registro.json");
                            databaseReference.child("Usuarios").child(us.getUid()).setValue(lector.devolverMapa(getApplicationContext(),"registro.json")).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){

                                        databaseReference.child("Usuarios").child(us.getUid()).child("materiasPorTomar").setValue(user.getMateriasPorTomar());
                                        Toast.makeText(RegisterActivity.this, "exito", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterActivity.this, Navigation_bottom.class));
                                        finishAffinity();
                                    }
                                    else{
                                        Toast.makeText(RegisterActivity.this, "fail", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });
    }

    /**
     *inicializamos la Firebase, creando una instancia, y obteniendo una referencia
     * del estado actual de la Firebase
     */
    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    /**
     *nos ayudara a mostrar los mensajes de error cuando la validacion de los campos de texto falle
     * recibe como parametro un EditText que es el cual contendra el error y un String que es el mensaje de error
     */
    private void mensajeError(EditText cont,String texto){
        cont.setError(texto);
        cont.requestFocus();
    }

    /**
     * nos permite obtener los elementos del layout y manejarlos desde codigo
     */
    private void asignarId(){
        user_R= findViewById(R.id.editTextTextPersonName);
        email_R=findViewById(R.id.editTextTextEmailAddress2);
        password_R=findViewById(R.id.editTextTextPassword2);
        confirm_R=findViewById(R.id.editTextTextPassword3);
    }
}