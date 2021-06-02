package com.example.macchiato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macchiato.Models.GlobalApplication;
import com.example.macchiato.Models.User;
import com.example.macchiato.Servicios.LectorFichero;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class LogInActivity extends AppCompatActivity {

    private EditText correo_L;
    private EditText contrasena_L;
    private TextView olvide_contrasena;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        correo_L= findViewById(R.id.editTextTextEmailAddress);
        contrasena_L= findViewById(R.id.editTextTextPassword);
        firebaseAuth = FirebaseAuth.getInstance();
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        olvide_contrasena=findViewById(R.id.cont_olvidada_id);
        inicializarFirebase();

        olvide_contrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reiniciar tu contrasena?");
                passwordResetDialog.setMessage("ingresa tu email para recibir el link");
                passwordResetDialog.setView(resetMail);
                passwordResetDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String email = resetMail.getText().toString().trim();

                        firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(LogInActivity.this, "el link fue enviado a tu email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(LogInActivity.this, "email invalido", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passwordResetDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                passwordResetDialog.create().show();
            }
        });


    }

    public void register(View view){
        Intent register=new Intent(this,RegisterActivity.class);
        startActivity(register);
    }

    public void session(View view){
        String email_txt= correo_L.getText().toString();
        String password_txt =contrasena_L.getText().toString();
        if(email_txt.isEmpty()){
            mensajeError(correo_L,"ingrese su correo");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email_txt).matches()){
            mensajeError(correo_L,"correo invalido");
            return;
        }
        if(password_txt.isEmpty()){
            mensajeError(contrasena_L,"ingrese su contrasena");
            return;
        }
        if(password_txt.length()<6){
            mensajeError(contrasena_L,"la contrasena es muy corta");
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email_txt,password_txt)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LogInActivity.this, "accedio a la cuenta con exito",
                                    Toast.LENGTH_SHORT).show();
                            databaseReference.child("Usuarios").child(firebaseAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    User userProfile = snapshot.getValue(User.class);
                                    LectorFichero lector = new LectorFichero();
                                    lector.crearJson(getApplicationContext(),userProfile);
                                    try {
                                        lector.leerFichero(getApplicationContext(), "registro.json");
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) { }
                            });
                            startActivity(new Intent(LogInActivity.this,Navigation_bottom.class));
                            finishAffinity();
                        } else {
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void mensajeError(EditText cont,String texto){
        cont.setError(texto);
        cont.requestFocus();
    }
    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
    }

}