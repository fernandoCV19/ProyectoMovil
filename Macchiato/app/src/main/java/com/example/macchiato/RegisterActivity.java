package com.example.macchiato;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText user_R,email_R,password_R,confirm_R;
    Button register;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user_R= findViewById(R.id.editTextTextPersonName);
        email_R=findViewById(R.id.editTextTextEmailAddress2);
        password_R=findViewById(R.id.editTextTextPassword2);
        confirm_R=findViewById(R.id.editTextTextPassword3);
        register=(Button)findViewById(R.id.register_btn);

        inicializarFirebase();


    }


    public void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
    }
}