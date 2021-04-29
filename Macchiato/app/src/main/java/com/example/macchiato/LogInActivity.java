package com.example.macchiato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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