package com.example.macchiato;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.Grupo;
import com.example.macchiato.Models.Materia;
import com.example.macchiato.Servicios.ConsultorMaterias;
import com.example.macchiato.Servicios.CreadorAlarma;
import com.example.macchiato.Servicios.Iniciador;
import com.example.macchiato.Servicios.RegistroJSON;

import org.json.JSONException;

import java.net.CookieManager;
import java.util.ArrayList;

public class AjustesFragment extends Fragment {
    Button btn;
    RecyclerView recyclerView;
    Spinner SpinnerMinutosAntes;

    public AjustesFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewAjustes = inflater.inflate(R.layout.fragment_ajustes, container, false);
        recyclerView = viewAjustes.findViewById(R.id.recyclerAlarmas);
        Iniciador iniciador = new Iniciador();
        ConsultorMaterias consultorMaterias = new ConsultorMaterias();
        RegistroJSON registroJSON = new RegistroJSON();
        ArrayList<Integer> tomadas = new ArrayList<>();
        ArrayList<Materia> materias = ConsultorMaterias.getMaterias();
        try {
            tomadas = registroJSON.getMateriasTomadas(getContext(), "registro.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Grupo> grupos = new ArrayList<>();
        ArrayList<Clase> clases = new ArrayList<>();
        if (tomadas != null) {
            for (Integer in : tomadas) {
                for (Materia mat : materias) {
                    for (Grupo grup : mat.getGrupos()) {
                        if (grup.getID() == in) {
                            for(Clase clase : grup.getClases()){
                                clase.setNomMateria(mat.getNombre());
                                clases.add(clase);
                            }
                        }
                    }
                }
            }
        }
        AlarmaAdapter alarmaAdapter= new AlarmaAdapter(clases,getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(alarmaAdapter);


        return viewAjustes;

    }
}