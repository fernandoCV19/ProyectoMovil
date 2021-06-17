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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;


import com.example.macchiato.Models.Grupo;
import com.example.macchiato.Models.Materia;
import com.example.macchiato.Servicios.ConsultorMaterias;
import com.example.macchiato.Servicios.HorarioAutomatico;
import com.example.macchiato.Servicios.Iniciador;

import java.util.ArrayList;

/**
 *es el fragment donde se muestra el horario generado en horario fragment
 */
public class MostrarHorarioFragment extends Fragment {
    Toolbar toolbar;
    ArrayList<Integer> list;
    RecyclerView recyclerView;

    /**
     * constructores
     */
    public MostrarHorarioFragment(){
        list = new ArrayList<>();
    }
    public MostrarHorarioFragment(ArrayList<Integer> list){
        this.list=list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mostrar_horario, container, false);
        toolbar = view.findViewById(R.id.toolbar);

        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        Iniciador iniciador=new Iniciador();

        if(list==null||list.isEmpty()){
            Toast.makeText(getActivity().getApplicationContext(), "vacia", Toast.LENGTH_SHORT).show();
        }

        try {
            iniciador.iniciar(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Materia> materias=ConsultorMaterias.getMaterias();
        if(list!=null) {
            ArrayList<Grupo> grupos = new ArrayList<>();
            for (Integer in : list) {
                for (Materia mat : materias) {
                    for (Grupo grup : mat.getGrupos()) {
                        if (grup.getID() == in) {
                            grupos.add(grup);
                        }
                    }
                }
            }
            MostrarHorarioAdapter mostrarHorarioAdapter = new MostrarHorarioAdapter(grupos, getContext());
            recyclerView = view.findViewById(R.id.recyclerMostrar);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(mostrarHorarioAdapter);
        }
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.botones_navegacion_generar, menu);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        ArrayList<Materia> n;
        switch(id) {
            case R.id.cambiar_a_generar:

                HorarioFragment mostrarHorarioFragment=new HorarioFragment(list);

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