package com.example.macchiato.Interfaz.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.macchiato.Interfaz.Activities.LogInActivity;
import com.example.macchiato.R;

public class PerfilFragment extends Fragment {


    public PerfilFragment() {
    }

    /**
     * asigna el layout fragment_perfil
     * asigna la funcion de crear un Intent con el activity "LogInActivity" al boton iniciar sesion
     *
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        Button btnLanzarActivity = (Button) view.findViewById(R.id.buttonIniciarSesion);
        btnLanzarActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LogInActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}