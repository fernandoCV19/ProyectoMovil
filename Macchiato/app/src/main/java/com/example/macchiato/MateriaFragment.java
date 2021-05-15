package com.example.macchiato;

import android.annotation.SuppressLint;
import android.content.Context;
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

import com.example.macchiato.Models.GlobalApplication;
import com.example.macchiato.Models.MateriaNivel;
import com.example.macchiato.Parser.MateriaNivelParser;
import com.example.macchiato.Parser.MyApp;
import com.example.macchiato.Parser.ParserMateriaGrupo;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MateriaFragment extends Fragment {
    ArrayList<MateriaNivel> mv;
    ArrayList<MateriaNivel> mostrar;
    RecyclerView recyclerView;
    String materias;
    char nivel;
    String color;
    private static final String TAG = MateriaFragment.class.getSimpleName();

    public MateriaFragment() throws JSONException {
        nivel='A';
        color="#00e25f";
        mv=new ArrayList<>();
        mostrar= new ArrayList<>();
    }

    public void setNivel(char nivel) {
        this.nivel = nivel;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ParserMateriaGrupo p = new ParserMateriaGrupo();
        try {
            p.parserMateriaGrupo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mv=new ArrayList<>();
        materias=readJSONFromAsset();
        MateriaNivelParser materiaNivel = new MateriaNivelParser();
        mostrar= new ArrayList<>();
        try {
           //mv = materiaNivel.parser(materias);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mostrarPorNivel(color,nivel);
        View rootView=inflater.inflate(R.layout.fragment_materia,container,false);

        MateriaAdapter materiaAdapter= new MateriaAdapter(mostrar,this.getContext());
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
        switch(id) {
            case R.id.menu_nivelB:
                moverFragment("#48a259",'B');
                break;
            case R.id.menu_nivelA:
                moverFragment("#00e25f",'A');
                break;
            case R.id.menu_nivelC:
                moverFragment("#99e801",'C');
                break;
            case R.id.menu_nivelD:
                moverFragment("#48a259",'D');
                break;
            case R.id.menu_nivelE:
                moverFragment("#48a259",'E');
                break;
            case R.id.menu_nivelF:
                moverFragment("#48a259",'F');
                break;
            case R.id.menu_nivelG:
                moverFragment("#48a259",'G');
                break;
            case R.id.menu_nivelH:
                moverFragment("#48a259",'H');
                break;
            case R.id.menu_nivelI:
                moverFragment("#48a259",'I');
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("materiasNivel.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void mostrarPorNivel(String color,char nivel){
        for(MateriaNivel mave : mv){
            if (mave.getNivel()==nivel){
                mave.setColor(color);
                mostrar.add(mave);
            }
        }
    }

    private void moverFragment(String color,char nivel){
        MateriaFragment mt=null;
        try {
            mt = new MateriaFragment();
            mt.setColor(color);
            mt.setNivel(nivel);
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