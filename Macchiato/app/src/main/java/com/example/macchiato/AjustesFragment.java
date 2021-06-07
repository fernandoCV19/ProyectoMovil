package com.example.macchiato;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.Grupo;
import com.example.macchiato.Models.Materia;
import com.example.macchiato.Servicios.Alarma.Alarma;
import com.example.macchiato.Servicios.Alarma.CreadorAlarma;
import com.example.macchiato.Servicios.Alarma.TinyDB;
import com.example.macchiato.Servicios.ConsultorMaterias;
import com.example.macchiato.Servicios.Iniciador;
import com.example.macchiato.Servicios.RegistroJSON;

import java.util.ArrayList;

public class AjustesFragment extends Fragment {
    Button btn;
    RecyclerView recyclerView;
    Spinner SpinnerMinutosAntes;
    TinyDB tinydb;

    public AjustesFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        tinydb = new TinyDB(getActivity().getApplicationContext());
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
        ArrayList<Alarma> alarmas= new ArrayList<>();

       /*if (tomadas != null) {
            for (Integer in : tomadas) {
                for (Materia mat : materias) {
                    for (Grupo grup : mat.getGrupos()) {
                        if (grup.getID() == in) {
                            for(Clase clase : grup.getClases()){
                                Alarma alarma=new Alarma()
                                clase.setNomMateria(mat.getNombre());
                                alarmas.add(clase);
                            }
                        }
                    }
                }
            }
        }*/
        if(tomadas != null){
            ArrayList<ConsultorMaterias.Par> pars = consultorMaterias.devolverGrupos(tomadas);
            for(ConsultorMaterias.Par par : pars){
                String nomMateria = par.getMateria();
                Grupo grupo = par.getGrupo();
                for(Clase clase: grupo.getClases()){
                    Alarma alarma ;
                    clase.setNomMateria(nomMateria);
                    alarma = new Alarma(clase, "", "",true, "");
                    CreadorAlarma creadorAlarma = new CreadorAlarma();
                    creadorAlarma.crearAlarma(alarma,getContext(),tinydb);
                    alarmas.add(alarma);
                }
            }
        }
        AlarmaAdapter alarmaAdapter= new AlarmaAdapter(alarmas,getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(alarmaAdapter);


        return viewAjustes;

    }
}