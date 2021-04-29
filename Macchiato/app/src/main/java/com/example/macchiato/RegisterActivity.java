package com.example.macchiato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
        firebaseAuth = FirebaseAuth.getInstance();


    }

    public void registrar(View view) {
        User user = new User();

        String u = user_R.getText().toString().trim();
        String e = email_R.getText().toString().trim();
        String p = password_R.getText().toString().trim();
        user.setUserName(u);
        user.setEmail(e);
        user.setPassword(p);
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(),user.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            //Toast.makeText(getApplicationContext(), "lo lograste", Toast.LENGTH_SHORT).show();
                            FirebaseUser us= FirebaseAuth.getInstance().getCurrentUser();
                            user.setUid(us.getUid());
                            databaseReference.child("User").child(us.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "exito", Toast.LENGTH_SHORT).show();
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



    public void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}