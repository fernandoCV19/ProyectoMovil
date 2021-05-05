package com.example.macchiato;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class MateriaFragment extends Fragment {
    List<ListElement> elementList;
    public MateriaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        elementList=new ArrayList<>();
        elementList.add(new ListElement("#000000","boris","8:15-9:45","xd"));
        elementList.add(new ListElement("#000000","boris","8:15-9:45","xd"));
        elementList.add(new ListElement("#000000","boris","8:15-9:45","xd"));
        elementList.add(new ListElement("#000000","boris","8:15-9:45","xd"));
        elementList.add(new ListElement("#000000","boris","8:15-9:45","xd"));

        View rootView=inflater.inflate(R.layout.fragment_materia,container,false);
        ListAdapter listAdapter = new ListAdapter(elementList,this.getContext());
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.listRecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listAdapter);



        return rootView;
    }

}