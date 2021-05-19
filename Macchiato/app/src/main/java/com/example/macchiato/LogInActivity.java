package com.example.macchiato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

import java.util.HashMap;
import java.util.Map;

public class LogInActivity extends AppCompatActivity {

    private EditText correo_L;
    private EditText contrasena_L;
    private FirebaseAuth mAuth;
    private DatabaseReference reference;
    private User userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        correo_L= findViewById(R.id.editTextTextEmailAddress);
        contrasena_L= findViewById(R.id.editTextTextPassword);
        mAuth = FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference("User");
    }

    public void register(View view){
        Intent register=new Intent(this,RegisterActivity.class);
        startActivity(register);
    }

    public void session(View view){
        String email_txt= correo_L.getText().toString();
        String password_txt =contrasena_L.getText().toString();
        if(email_txt.isEmpty()){
            correo_L.setError("ingrese su correo");
            correo_L.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email_txt).matches()){
            correo_L.setError("correo invalido");
            correo_L.requestFocus();
            return;
        }
        if(password_txt.isEmpty()){
            contrasena_L.setError("ingrese su contrasena");
            contrasena_L.requestFocus();
            return;
        }
        if(password_txt.length()<6){
            contrasena_L.setError("la contrasena es muy corta");
            contrasena_L.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email_txt,password_txt)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(LogInActivity.this, "accedio a la cuenta con exito",
                                    Toast.LENGTH_SHORT).show();
                            reference.child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    userProfile = snapshot.getValue(User.class);
                                    String myjson = new Gson().toJson(userProfile);
                                    Map<String, Object> jsonMap = new Gson().fromJson(myjson, new TypeToken<HashMap<String, Object>>() {}.getType());
                                    Toast.makeText(getApplicationContext(), jsonMap.toString(), Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
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

}