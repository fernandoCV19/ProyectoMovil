package com.example.macchiato;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class PerfilSesionFragment extends Fragment {

    private FirebaseAuth auth;
    //Button logOut_btn;

    public PerfilSesionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_sesion, container, false);
        auth=FirebaseAuth.getInstance();

        Button btnLanzarActivity = (Button) view.findViewById(R.id.buttonCerrarSesion);
        btnLanzarActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auth!=null){
                    auth.signOut();
                    Intent intent = new Intent(getActivity(),Navigation_bottom.class);
                    startActivity(intent);
                }else{
                    //Toast.makeText(PerfilSesionFragment.this, "error", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

}