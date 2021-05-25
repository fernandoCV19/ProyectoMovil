package com.example.macchiato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.macchiato.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText user_R,email_R,password_R,confirm_R;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        asignarId();
        inicializarFirebase();
    }

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

        User user = new User(u,e,p);
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(),user.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            FirebaseUser us= FirebaseAuth.getInstance().getCurrentUser();
                            user.setUid(us.getUid());
                            databaseReference.child("User").child(us.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "exito", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterActivity.this,Navigation_bottom.class));
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

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();

    }

    private void mensajeError(EditText cont,String texto){
        cont.setError(texto);
        cont.requestFocus();
    }

    private void asignarId(){
        user_R= findViewById(R.id.editTextTextPersonName);
        email_R=findViewById(R.id.editTextTextEmailAddress2);
        password_R=findViewById(R.id.editTextTextPassword2);
        confirm_R=findViewById(R.id.editTextTextPassword3);
    }
}