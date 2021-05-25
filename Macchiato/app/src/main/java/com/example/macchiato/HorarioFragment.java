package com.example.macchiato;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.macchiato.Models.Grupo;
import com.example.macchiato.Models.Materia;
import com.example.macchiato.Servicios.ConsultorMaterias;
import com.example.macchiato.Servicios.Iniciador;

import java.util.ArrayList;
import java.util.HashMap;


public class HorarioFragment extends Fragment {
    Spinner spinnerNivel;
    Spinner spinnerMateria;
    RecyclerView recyclerView;
    String selectedClass;
    String select;
    public HorarioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_horario, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerCheckbox);
        spinnerNivel = view.findViewById(R.id.spinnerNivel);
        spinnerMateria = view.findViewById(R.id.spinnerMateria);
        Iniciador iniciador=new Iniciador();
        try {
            iniciador.iniciar(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        spinnerNivel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                selectedClass = parent.getItemAtPosition(position).toString();
                switch (selectedClass) {
                    case "A":

                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelA)));
                        break;

                    case "B":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelB)));
                        break;

                    case "C":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelC)));
                        break;

                    case "D":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelD)));
                        break;
                    case "E":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelE)));
                        break;
                    case "F":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelF)));
                        break;
                    case "G":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelG)));
                        break;
                    case "H":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelH)));
                        break;

                }


                spinnerMateria.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerMateria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select = spinnerMateria.getSelectedItem().toString();
                cambiarRecycler();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        ArrayList<Materia> materias = ConsultorMaterias.getMaterias();
        ArrayList<Grupo>   grupos = new ArrayList<>();
        for(Materia materia: materias){
            if(materia.getNombre().equals(select)){
                grupos = materia.getGrupos();
            }
        }
        GrupoHorarioAdapter materiaHorarioAdapter= new GrupoHorarioAdapter(grupos,this.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(materiaHorarioAdapter);


        return view;
    }
    void cambiarRecycler(){
        ArrayList<Materia> materias = ConsultorMaterias.getMaterias();
        ArrayList<Grupo>   grupos = new ArrayList<>();
        for(Materia materia: materias){
            if(materia.getNombre().equals(select)){
                grupos = materia.getGrupos();
            }
        }
        GrupoHorarioAdapter materiaHorarioAdapter= new GrupoHorarioAdapter(grupos,this.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(materiaHorarioAdapter);
    }

}

