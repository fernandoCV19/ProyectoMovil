package com.example.macchiato;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MateriaFragment extends Fragment {
    private Toolbar toolbar;
    private static final String TAG = MateriaFragment.class.getSimpleName();
    List<ListElement> elementList;
    List<MateriaElement> materiaList;

    public MateriaFragment() throws JSONException {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String materias=readJSONFromAsset();
        GrupoParser grupoParser=new GrupoParser();
        ArrayList<GrupoParser> gp=new ArrayList<>();
        materiaList= new ArrayList<>();
        try {
           gp = grupoParser.main(materias);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(GrupoParser g : gp){
           MateriaElement materiaElement;
           materiaElement=new MateriaElement(g.getNombre(),Integer.toString(g.getID()),"#00aae4");
           materiaList.add(materiaElement);
        }

        View rootView=inflater.inflate(R.layout.fragment_materia,container,false);

        MateriaAdapter materiaAdapter= new MateriaAdapter(materiaList,this.getContext());
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.listRecyclerview);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_nivelA){

            //El código que se ejecutara al hacer click en esa opción

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
            InputStream is = getActivity().getAssets().open("materias-grupos.json");
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


}