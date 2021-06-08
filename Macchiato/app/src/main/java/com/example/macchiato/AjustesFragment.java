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
import android.widget.Switch;

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
    ArrayList<Alarma> alarmasList;
    AlarmaAdapter alarmaAdapter;
    ArrayList<Integer> tomadas;
    Switch activarNotificaciones;
    public AjustesFragment() {
        alarmasList= new ArrayList<>();
        tomadas = new ArrayList<>();
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        tinydb = new TinyDB(getActivity().getApplicationContext());
        View viewAjustes = inflater.inflate(R.layout.fragment_ajustes, container, false);

        recyclerView = viewAjustes.findViewById(R.id.recyclerAlarmas);
        activarNotificaciones =(Switch)viewAjustes.findViewById(R.id.switch2);

        Iniciador iniciador = new Iniciador();
        RegistroJSON registroJSON = new RegistroJSON();

        try {
            tomadas = registroJSON.getMateriasTomadas(getContext(), "registro.json");
        } catch (Exception e) {
            e.printStackTrace();
        }


        alarmaAdapter= new AlarmaAdapter(alarmasList,getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(alarmaAdapter);

        getAllAlarmas();

        return viewAjustes;

    }

    private void setAllAlarms(){
        if(tomadas != null){
            ConsultorMaterias consultorMaterias = new ConsultorMaterias();
            ArrayList<ConsultorMaterias.Par> pars = consultorMaterias.devolverGrupos(tomadas);
            for(ConsultorMaterias.Par par : pars){
                String nomMateria = par.getMateria();
                Grupo grupo = par.getGrupo();
                for(Clase clase: grupo.getClases()){
                    Alarma alarma ;
                    clase.setNomMateria(nomMateria);
                    alarma = new Alarma(clase, "", "",true, "");

                    if(!alarmasList.contains(alarma)){
                        CreadorAlarma creadorAlarma = new CreadorAlarma();
                        creadorAlarma.crearAlarma(alarma,getContext(),tinydb);
                        alarmasList.add(alarma);
                    }
                }
            }
        }
    }
    public void getAllAlarmas() {
        alarmasList.clear();
        alarmasList.addAll(tinydb.getListAlarm("allAlarmas", Alarma.class));
        alarmaAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onResume() {
        super.onResume();
        getAllAlarmas();
    }
}