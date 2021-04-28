package com.example.macchiato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Navigation_bottom extends AppCompatActivity {

    BottomNavigationView mBottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naivigation_bottom);

        showSelectedFragment(new HorarioFragment());

        mBottomNavigation = findViewById(R.id.bottomNavigationView);

        mBottomNavigation.setOnNavigationItemSelectedListener(item -> {
            if(item.getItemId()== R.id.nav_perfil){
                showSelectedFragment(new PerfilFragment());
            }
            if(item.getItemId()==R.id.nav_horario){
                showSelectedFragment(new HorarioFragment());
            }
            if(item.getItemId()==R.id.nav_materias){
                showSelectedFragment(new MateriaFragment());
            }
            if(item.getItemId()==R.id.nav_ajustes){
                showSelectedFragment(new ajustesFragment());
            }
            return true;
        });

    }

    private void showSelectedFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}