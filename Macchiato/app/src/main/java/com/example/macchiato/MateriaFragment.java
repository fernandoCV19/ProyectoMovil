package com.example.macchiato;

import android.annotation.SuppressLint;
import android.os.Bundle;

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

import com.example.macchiato.Models.Materia;
import com.example.macchiato.Models.MateriaNivel;
import com.example.macchiato.Parser.MateriaNivelParser;
import com.example.macchiato.Parser.ParserMateriaGrupo;
import com.example.macchiato.Servicios.ConsultorMaterias;
import com.example.macchiato.Servicios.Iniciador;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class MateriaFragment extends Fragment {
    ArrayList<Materia> mv;
    ArrayList<Materia> mostrar;
    RecyclerView recyclerView;
    HashMap<Character, ArrayList<Materia>> materias;
    MateriaAdapter materiaAdapter;
    private static final String TAG = MateriaFragment.class.getSimpleName();

    public MateriaFragment() throws JSONException {

    }

    public void setMostrar(ArrayList<Materia> mostrar) {
        this.mostrar = mostrar;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Iniciador iniciador = new Iniciador();
        try {
            iniciador.iniciar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        materias= ConsultorMaterias.getLisClasificada();
        mv = materias.get('A');
        assert mv != null;
        if(mv.equals(mostrar)){
            materiaAdapter= new MateriaAdapter(mv,this.getContext());
        }else if(mostrar==null){
            materiaAdapter= new MateriaAdapter(mv,this.getContext());
        }else{
            materiaAdapter= new MateriaAdapter(mostrar,this.getContext());
        }

        View rootView=inflater.inflate(R.layout.fragment_materia,container,false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.listRecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(materiaAdapter);

        return rootView;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.nivel_menu, menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        ArrayList<Materia> n;
        switch(id) {
            case R.id.menu_nivelB:
                n=materias.get('B');
                moverFragment(n);
                break;
            case R.id.menu_nivelA:
                n=materias.get('A');
                moverFragment(n);
                break;
            case R.id.menu_nivelC:
                n=materias.get('C');
                moverFragment(n);
                break;
            case R.id.menu_nivelD:
                n=materias.get('D');
                moverFragment(n);
                break;
            case R.id.menu_nivelE:
                n=materias.get('E');
                moverFragment(n);
                break;
            case R.id.menu_nivelF:
                n=materias.get('F');
                moverFragment(n);
                break;
            case R.id.menu_nivelG:
                n=materias.get('G');
                moverFragment(n);
                break;
            case R.id.menu_nivelH:
                n=materias.get('H');
                moverFragment(n);
                break;
            case R.id.menu_nivelI:
                n=materias.get('I');
                moverFragment(n);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    private void moverFragment(ArrayList<Materia> lista){
        MateriaFragment mt=null;
        try {
            mt = new MateriaFragment();
            mt.setMostrar(lista);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,mt);
        fragmentTransaction.disallowAddToBackStack();
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commit();
    }



}