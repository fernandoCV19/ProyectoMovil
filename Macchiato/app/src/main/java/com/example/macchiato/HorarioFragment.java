package com.example.macchiato;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class HorarioFragment extends Fragment {
    Spinner spinnerNivel;
    Spinner spinnerMateria;
    public HorarioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_horario, container, false);
        spinnerNivel= view.findViewById(R.id.spinnerNivel);
        spinnerMateria= view.findViewById(R.id.spinnerMateria);

        spinnerNivel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String selectedClass = parent.getItemAtPosition(position).toString();
                switch (selectedClass)
                {
                    case "A":

                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelA)));
                        break;

                    case "B":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelB)));
                        break;

                    case "C":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelC)));
                        break;

                    case "D":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelD)));
                        break;
                    case "E":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelE)));
                        break;
                    case "F":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelF)));
                        break;
                    case "G":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelG)));
                        break;
                    case "H":
                        spinnerMateria.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelH)));
                        break;

                }


                spinnerMateria.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        
        
        
        return view;
    }
}
