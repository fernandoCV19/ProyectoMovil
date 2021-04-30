package com.example.macchiato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {

    private EditText correo_L;
    private EditText contrasena_L;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        correo_L= findViewById(R.id.editTextTextEmailAddress);
        contrasena_L= findViewById(R.id.editTextTextPassword);
        mAuth = FirebaseAuth.getInstance();
    }

    public void register(View view){
        Intent register=new Intent(this,RegisterActivity.class);
        startActivity(register);
    }

    public void session(View view){
        Intent session=new Intent(this,Navigation_bottom.class);

        startActivity(session);
    }

}