package com.example.macchiato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.macchiato.Servicios.RegistroJSON;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;

import java.util.ArrayList;

public class Navigation_bottom extends AppCompatActivity {


    BottomNavigationView mBottomNavigation;
    private FirebaseAuth auth;
    FirebaseUser firebaseUser;
    MateriaFragment materiaFragment;
    MostrarHorarioFragment mostrarHorarioFragment;
    AjustesFragment ajustesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        leerMateriasTomadas();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naivigation_bottom);

        showSelectedFragment(mostrarHorarioFragment);
        try {
            materiaFragment= new MateriaFragment();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ajustesFragment = new AjustesFragment();

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth=FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        mBottomNavigation =(BottomNavigationView) findViewById(R.id.bottomNavigationView);
        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()== R.id.nav_perfil){
                    if(firebaseUser==null){
                        //Toast.makeText(Navigation_bottom.this, "ok", Toast.LENGTH_SHORT).show();
                        showSelectedFragment(new PerfilFragment());
                    }else{
                        //Toast.makeText(Navigation_bottom.this, "ya esta loggeado", Toast.LENGTH_SHORT).show();

                        showSelectedFragment(new PerfilSesionFragment());
                    }

                }
                if(item.getItemId()==R.id.nav_horario){
                    leerMateriasTomadas();
                    showSelectedFragment(mostrarHorarioFragment);
                }
                if(item.getItemId()==R.id.nav_materias){
                    showSelectedFragment(materiaFragment);
                }
                if(item.getItemId()==R.id.nav_ajustes){
                    showSelectedFragment(ajustesFragment);
                }
                return true;
            }
        });
    }

    private void showSelectedFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public void leerMateriasTomadas(){
        RegistroJSON registroJSON= new RegistroJSON();
        ArrayList<Integer> tomadas= new ArrayList<>();
        try {
            tomadas= registroJSON.getMateriasTomadas(this,"registro.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        mostrarHorarioFragment=new MostrarHorarioFragment(tomadas);
    }

}