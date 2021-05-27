package com.example.macchiato;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    Toolbar toolbar;
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


        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        Iniciador iniciador=new Iniciador();


        try {
            iniciador.iniciar(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ConsultorMaterias cs =new ConsultorMaterias();
        HashMap<Character, ArrayList<Materia>> list=cs.getLisClasificada();
        Character [] nomNiveles =new Character[9];
        int j = 0;
        for (Character nivel : list.keySet()) {

            nomNiveles[j ]=nivel;
            j++;
        }


        ArrayAdapter<Character> adapter2 = new ArrayAdapter<Character>(getContext(), R.layout.simple_spinner, nomNiveles);
        spinnerNivel.setAdapter(adapter2);

        spinnerNivel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                selectedClass = parent.getItemAtPosition(position).toString();
                switch (selectedClass) {
                    case "A":

                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                R.layout.simple_spinner,
                                getResources().getStringArray(R.array.nivelA)));
                        break;
                    case "B":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                R.layout.simple_spinner,
                                getResources().getStringArray(R.array.nivelB)));
                        break;
                    case "C":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                R.layout.simple_spinner,
                                getResources().getStringArray(R.array.nivelC)));
                        break;
                    case "D":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                R.layout.simple_spinner,
                                getResources().getStringArray(R.array.nivelD)));
                        break;
                    case "E":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                R.layout.simple_spinner,
                                getResources().getStringArray(R.array.nivelE)));
                        break;
                    case "F":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                R.layout.simple_spinner,
                                getResources().getStringArray(R.array.nivelF)));
                        break;
                    case "G":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                R.layout.simple_spinner,
                                getResources().getStringArray(R.array.nivelG)));
                        break;
                    case "H":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                R.layout.simple_spinner,
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.botones_navegacion_mostrar, menu);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        ArrayList<Materia> n;
        switch(id) {
            case R.id.cambiar_a_generar:
                MostrarHorarioFragment mostrarHorarioFragment=new MostrarHorarioFragment();
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,mostrarHorarioFragment);
                fragmentTransaction.disallowAddToBackStack();
                fragmentTransaction.setReorderingAllowed(true);
                fragmentTransaction.commit();
        }
        return super.onOptionsItemSelected(item);
    }

}

