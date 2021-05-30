package com.example.macchiato;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.widget.Toast;


import com.example.macchiato.Models.Grupo;

import android.widget.Button;
import android.widget.Toast;

import com.example.macchiato.Models.GlobalApplication;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;


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
    ArrayList<GrupoHorarioAdapter> grupoHorarioAdapters;
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
        grupoHorarioAdapters=new ArrayList<>();
        grupos=new ArrayList<>();
        materiaHorarioAdapter=new GrupoHorarioAdapter(grupos,getContext());
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

        crearVistas();
        ConsultorMaterias cs =new ConsultorMaterias();
        HashMap<Character, ArrayList<Materia>> list=cs.getLisClasificada();
        Character [] nomNiveles =new Character[9];

        ConsultorMaterias cs = new ConsultorMaterias();
        HashMap<Character, ArrayList<Materia>> list = cs.getLisClasificada();
        Character[] nomNiveles = new Character[9];

        int j = 0;
        for (Character nivel : list.keySet()) {

            nomNiveles[j] = nivel;
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
                if(select!=null)
                cambiarRecycler();
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
                break;
            }
        }
        GrupoHorarioAdapter grupoHorarioAdapter= new GrupoHorarioAdapter(grupos,getContext());
        for(GrupoHorarioAdapter g : grupoHorarioAdapters){
            if(g.getmData().equals(grupoHorarioAdapter.getmData())){
                materiaHorarioAdapter=g;
                break;
            }
        }
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
                seleccionados.addAll(materiaHorarioAdapter.getSeleccionados());
                Toast.makeText(getContext(), "tam: "+seleccionados.size(), Toast.LENGTH_SHORT).show();
                break;
            }
        }
        GrupoHorarioAdapter grupoHorarioAdapter= new GrupoHorarioAdapter(grupos,getContext());
        for(GrupoHorarioAdapter g : grupoHorarioAdapters){
            if(g.getmData().equals(grupoHorarioAdapter.getmData())){
                materiaHorarioAdapter=g;
                break;
            }
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(materiaHorarioAdapter);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

      /*  View view= inflater.inflate(R.layout.fragment_horario, container, false);
        Button btnDes = (Button) view.findViewById(R.id.btn_descargar_id);
        btnDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                            PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions,1000);
                    }else {
                        startDownloading();
                    }
                }
                else {
                    startDownloading();
                }
            }
        });
        return view;
    }


    private  void  startDownloading(){
        FirebaseDatabase.getInstance().getReference().child("UMSS").child("cronograma")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        String url = snapshot.getValue(String.class);
                        DownloadManager.Request request= new DownloadManager.Request(Uri.parse(url));
                        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                        request.setTitle("Download");
                        request.setDescription("Descargando archivo");

                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,""+System.currentTimeMillis());
                        DownloadManager downloadManager = (DownloadManager)getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                        downloadManager.enqueue(request);

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) { }
                });

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
                /*ArrayList<Integer> aux = new ArrayList<>();
                aux.add(1);
                aux.add(2);
                aux.add(3);
                aux.add(5);
                MostrarHorarioFragment mostrarHorarioFragment=new MostrarHorarioFragment(aux);*/
/*
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,mostrarHorarioFragment);
                fragmentTransaction.disallowAddToBackStack();
                fragmentTransaction.setReorderingAllowed(true);
                fragmentTransaction.commit();
        }
        return super.onOptionsItemSelected(item);
    }


    void crearVistas(){
        ArrayList<Materia> materias = ConsultorMaterias.getMaterias();
        for(Materia materia: materias){
            GrupoHorarioAdapter grupoHorarioAdapter=new GrupoHorarioAdapter(materia.getGrupos(),getContext());
            grupoHorarioAdapters.add(grupoHorarioAdapter);
        }


 */

    }
}


