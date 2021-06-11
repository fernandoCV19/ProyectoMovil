package com.example.macchiato;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;

public class AjustesFragment extends Fragment {
    Button btn;
    RecyclerView recyclerView;
    Spinner SpinnerMinutosAntes;
    TinyDB tinydb;
    ArrayList<Alarma> alarmasList;
    AlarmaAdapter alarmaAdapter;
    ArrayList<Integer> tomadas;
    SwitchCompat activarNotificaciones;
    boolean activado;
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
        activarNotificaciones = viewAjustes.findViewById(R.id.switch2);

        Iniciador iniciador = new Iniciador();
        RegistroJSON registroJSON = new RegistroJSON();
        activado = tinydb.getBoolean("activado");
        activarNotificaciones.setChecked(activado);


        try {
            tomadas = registroJSON.getMateriasTomadas(getContext(), "registro.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(activado) {
            getAllAlarmas();
            setAllAlarms();
        }
        else alarmasList.clear();
        activarNotificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activarNotificaciones.isChecked()){
                    setAllAlarms();
                    tinydb.putBoolean("activado", true);

                    tinydb.putBoolean("activado", true);
                    setAllAlarms();
                }else{
                    tinydb.putBoolean("activado", false);
                    cancelAllAlarms();
                    alarmasList.clear();
                }
                alarmaAdapter.notifyDataSetChanged();
            }
        });

        alarmaAdapter= new AlarmaAdapter(alarmasList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(alarmaAdapter);

        alarmaAdapter.notifyDataSetChanged();

        return viewAjustes;

    }

    private boolean setAllAlarms(){
        if(tomadas != null){
            ConsultorMaterias consultorMaterias = new ConsultorMaterias();
            ArrayList<ConsultorMaterias.Par> pars = consultorMaterias.devolverGrupos(tomadas);
            ArrayList<Integer> alarmsId = new ArrayList<>();

            for(ConsultorMaterias.Par par : pars){
                String nomMateria = par.getMateria();
                Grupo grupo = par.getGrupo();
                for(Clase clase: grupo.getClases()){
                    Alarma alarma ;
                    clase.setNomMateria(nomMateria);
                    alarma = new Alarma(clase, "", "",true, "");

                    if(!alarmasList.contains(alarma)) {
                        alarmasList.add(alarma);
                    }
                }
            }
            tinydb.putListAlarm("allAlarmas", alarmasList);
            CreadorAlarma.setAllAlarms(getContext());
            return true;
        }
        else return false;
    }
    public boolean cancelAllAlarms(){
        if(tomadas != null && tomadas.size()!= 0){
            CreadorAlarma.cancelAllAlarm(getContext());
            getAllAlarmas();
            return true;
        }
        else return false;
    }
    public void getAllAlarmas() {
        alarmasList.clear();
        alarmasList.addAll(tinydb.getListAlarm("allAlarmas", Alarma.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        if(tinydb.getBoolean("activado"))
            getAllAlarmas();
    }
}
