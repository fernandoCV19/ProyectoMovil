package com.example.macchiato.Interfaz.Fragments;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
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

import android.widget.TextView;


import com.example.macchiato.Interfaz.Adapters.GrupoHorarioAdapter;
import com.example.macchiato.Models.Grupo;

import android.widget.Button;

import com.example.macchiato.R;
import com.example.macchiato.Servicios.HorarioAutomatico;
import com.example.macchiato.Servicios.RegistroJSON;
import com.getbase.floatingactionbutton.FloatingActionButton;
//import com.google.gson.Gson;


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
    ArrayList<Integer> seleccionados;
    GrupoHorarioAdapter materiaHorarioAdapter;
    ArrayList<Materia> materias;
    ArrayList<Grupo>   grupos;

    Button guardar;
    FloatingActionButton automatico;
    FloatingActionButton eliminarSeleccionadas;
    RegistroJSON registroJSON;
    public HorarioFragment() {
        seleccionados= new ArrayList<>();
    }
    public HorarioFragment(ArrayList<Integer> seleccionados) {
       this.seleccionados=seleccionados;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        registroJSON=new RegistroJSON();

        grupos=new ArrayList<>();
        materiaHorarioAdapter=new GrupoHorarioAdapter(grupos,getContext(),seleccionados);
        View view = inflater.inflate(R.layout.fragment_horario, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerCheckbox);
        spinnerNivel = view.findViewById(R.id.spinnerNivel);
        spinnerMateria = view.findViewById(R.id.spinnerMateria);
        automatico = view.findViewById(R.id.idHorarioAutomatico);

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
        String [] nomNiveles =new String[10];
        nomNiveles[0]="Seleccionar nivel";

        int j = 1;
        for (Character nivel : list.keySet()) {

            nomNiveles[j] = nivel+"";
            j++;
        }

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), R.layout.simple_spinner, nomNiveles);

       


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
                    case "I":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                R.layout.simple_spinner,
                                getResources().getStringArray(R.array.nivelI)
                                ));

                }
                spinnerMateria.setVisibility(View.VISIBLE);
                if(select!=null) {
                    try {
                        cambiarRecycler();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerMateria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select = spinnerMateria.getSelectedItem().toString();
                try {
                    cambiarRecycler();
                } catch (Exception e) {
                    e.printStackTrace();
                }

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
                break;
            }
        }
        materiaHorarioAdapter= new GrupoHorarioAdapter(grupos,getContext(),seleccionados);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(materiaHorarioAdapter);

        automatico.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String adAutomatico="Esto eliminará todo lo seleccionado y creará un horario respecto al Historial Académico";
                HorarioAutomatico horarioAutomatico = new HorarioAutomatico(getContext());
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                View view = inflater.inflate(R.layout.automatico_dialog, null);
                TextView contenido = view.findViewById(R.id.contenido_texto);
                contenido.setText(adAutomatico);
                builder.setNegativeButton("cancelar ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        try {
                            seleccionados.clear();
                            seleccionados = horarioAutomatico.generarAutomatico();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }
                });
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        eliminarSeleccionadas=view.findViewById(R.id.eliminar_materias);
        eliminarSeleccionadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adEliminar="Esto eliminará todas las materias seleccionadas";
                HorarioAutomatico horarioAutomatico = new HorarioAutomatico(getContext());
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                View view = inflater.inflate(R.layout.automatico_dialog, null);
                TextView contenido = view.findViewById(R.id.contenido_texto);
                contenido.setText(adEliminar);
                builder.setNegativeButton("cancelar ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        try {
                            seleccionados.clear();
                            registroJSON.limpiarCampo("materiasActuales",getContext(),"registro.json");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }
                });
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }
    void cambiarRecycler() throws Exception {
        ArrayList<Materia> materias = ConsultorMaterias.getMaterias();
        ArrayList<Grupo>   grupos = new ArrayList<>();
        ArrayList<Integer> selecs=registroJSON.getMaterias("materiasActuales",getContext(), "registro.json");
        for(Materia materia: materias){
            if(materia.getNombre().equals(select)){
                grupos = materia.getGrupos();
                /*seleccionados.addAll(materiaHorarioAdapter.getSeleccionados());
                Context context=getContext();
                /*for(Integer integer: seleccionados){
                    if(integer>0) {
                        try {
                            registroJSON.aniadirMateriaTomada(integer, context, "registro.json");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        try {
                            registroJSON.quitarMateria(integer*(-1),"materiasActuales", context, "registro.json");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }*/
                //break;
            }
        }
        //seleccionados.clear();
        materiaHorarioAdapter= new GrupoHorarioAdapter(grupos,getContext(),seleccionados);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(materiaHorarioAdapter);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);;
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
                MostrarHorarioFragment mostrarHorarioFragment=new MostrarHorarioFragment(seleccionados);
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


