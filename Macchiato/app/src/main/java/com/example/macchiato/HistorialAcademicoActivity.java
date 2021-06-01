package com.example.macchiato;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;


import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macchiato.Models.MateriaNota;
import com.example.macchiato.Parser.ParserMateriaID;
import com.example.macchiato.Servicios.EstadisticaHA;
import com.example.macchiato.Servicios.RegistroJSON;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HistorialAcademicoActivity extends AppCompatActivity {


    Spinner mSpinner;
    Spinner nSpinner;
    EditText editText;
    ArrayList<MateriaNota> mostrarAprobadas;
    ArrayList<MateriaNota> mostrarReprobadas;
    RecyclerView recyclerViewApro;
    RecyclerView recyclerViewRepro;
    Button button;
    ArrayList<MateriaNota> listaMaterias;
    TextView promedioGen;
    TextView promedioMateriasApr;
    TextView numMateriasApro;
    TextView numMateriasRepro;
    TextView numMateriasCursa;
    MateriaNotaAdapter adapter;
    MateriaNotaAdapter adapter3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_academico);
        mostrarAprobadas = new ArrayList<>();
        mostrarReprobadas = new ArrayList<>();
        listaMaterias = new ArrayList<>();






            FloatingActionButton fab = findViewById(R.id.añadir_floating);
            fab.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(HistorialAcademicoActivity.this);
            View view1 = getLayoutInflater().inflate(R.layout.layout_dialog, null);
            //spinners
            mSpinner = (Spinner) view1.findViewById(R.id.materia);
            nSpinner = (Spinner) view1.findViewById(R.id.nivel);
            editText = (EditText) view1.findViewById(R.id.editText);

            nSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    String selectedClass = parent.getItemAtPosition(position).toString();
                    switch (selectedClass) {
                        case "A":

                            mSpinner.setAdapter(new ArrayAdapter<>(HistorialAcademicoActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    getResources().getStringArray(R.array.nivelA)));
                            break;

                        case "B":
                            mSpinner.setAdapter(new ArrayAdapter<>(HistorialAcademicoActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    getResources().getStringArray(R.array.nivelB)));
                            break;

                        case "C":
                            mSpinner.setAdapter(new ArrayAdapter<>(HistorialAcademicoActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    getResources().getStringArray(R.array.nivelC)));
                            break;

                        case "D":
                            mSpinner.setAdapter(new ArrayAdapter<>(HistorialAcademicoActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    getResources().getStringArray(R.array.nivelD)));
                            break;
                        case "E":
                            mSpinner.setAdapter(new ArrayAdapter<>(HistorialAcademicoActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    getResources().getStringArray(R.array.nivelE)));
                            break;
                        case "F":
                            mSpinner.setAdapter(new ArrayAdapter<>(HistorialAcademicoActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    getResources().getStringArray(R.array.nivelF)));
                            break;
                        case "G":
                            mSpinner.setAdapter(new ArrayAdapter<>(HistorialAcademicoActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    getResources().getStringArray(R.array.nivelG)));
                            break;
                        case "H":
                            mSpinner.setAdapter(new ArrayAdapter<>(HistorialAcademicoActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    getResources().getStringArray(R.array.nivelH)));
                            break;

                    }


                    mSpinner.setVisibility(View.VISIBLE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            builder.setTitle("Añadir materia")
                    .setPositiveButton("añadir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            // dialog.dismiss();

                        }
                    });
            builder.setNegativeButton("cancelar ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();

                }
            });
            builder.setView(view1);
            AlertDialog dialog = builder.create();
            // dialog.show();

            dialog.setOnShowListener(new DialogInterface.OnShowListener() {

                @Override
                public void onShow(DialogInterface dialogInterface) {

                    Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            String select = mSpinner.getSelectedItem().toString();
                            editText.setError(null);
                            String num = editText.getText().toString();

                            if ("".equals(num)) {
                                editText.setError("Introduce un número");
                                editText.requestFocus();
                                return;
                            }

                            int numero = Integer.parseInt(num);
                            if (numero >= 0 && numero <= 100) {
                                dialog.dismiss();
                            } else {
                                editText.setError("Número fuera de rango");
                                editText.requestFocus();
                                return;
                            }
                            MateriaNota materiaNota = new MateriaNota(select, numero);

                            try {
                                RegistroJSON rj = new RegistroJSON();
                                int idMat = new ParserMateriaID().getID(select);
                                rj.aniadirNota(idMat, numero, getApplicationContext());
                              //  new RegistroJSON().aniadirNota(new ParserMateriaID().getID(select), numero, getApplicationContext());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //  try {
                            //    new RegistroJSON().aniadirNota(new ParserMateriaID().getID(select), numero, getApplicationContext());
                            //  } catch (Exception e) {
                            //       e.printStackTrace();
                            //    }
                            if (!mostrarAprobadas.contains(materiaNota)) {
                                Toast.makeText(HistorialAcademicoActivity.this, "Añadido", Toast.LENGTH_SHORT).show();



                                recyclerViewApro = (RecyclerView) findViewById(R.id.list_materiasAprobadas);
                                recyclerViewRepro = (RecyclerView) findViewById(R.id.lista_MateriasReprobadas);
                                listaMaterias.add(materiaNota);
                                adapter3 = new MateriaNotaAdapter(mostrarReprobadas, HistorialAcademicoActivity.this);
                                adapter = new MateriaNotaAdapter(mostrarAprobadas, HistorialAcademicoActivity.this);

                                if (numero >= 51) {
                                    recyclerViewApro.setLayoutManager(new LinearLayoutManager(HistorialAcademicoActivity.this));//getContext()

                                    recyclerViewApro.setItemAnimator(new DefaultItemAnimator());
                                    recyclerViewApro.setAdapter(adapter);
                                    recyclerViewApro.setHasFixedSize(true);
                                    mostrarAprobadas.add(materiaNota);
                                } else {

                                    recyclerViewRepro.setLayoutManager(new LinearLayoutManager(HistorialAcademicoActivity.this));//getContext()
                                    adapter3 = new MateriaNotaAdapter(mostrarReprobadas, HistorialAcademicoActivity.this);
                                    recyclerViewApro.setItemAnimator(new DefaultItemAnimator());
                                    recyclerViewRepro.setAdapter(adapter3);
                                   recyclerViewRepro.setHasFixedSize(true);
                                 //   getDisplaySize();

                                    mostrarReprobadas.add(materiaNota);
                                }
                            }
                        }

                            //if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("Elige un nivel")) {
                            //  }



                        });
                }
            });
            dialog.show();
        });
        FloatingActionButton remove = findViewById(R.id.floating_eliminar);
        remove.setOnClickListener(vie -> {


            if(adapter.getSelect()!=null) {
                ArrayList<MateriaNota> selecionadas = adapter.getSelect();
                    for (MateriaNota materiaNota : selecionadas) {
                        if (mostrarAprobadas.contains(materiaNota) && adapter != null) {
                            mostrarAprobadas.remove(materiaNota);
                            listaMaterias.remove(materiaNota);
                            adapter = new MateriaNotaAdapter(mostrarAprobadas, HistorialAcademicoActivity.this);
                            // recyclerViewApro.setItemAnimator(new DefaultItemAnimator());
                            recyclerViewApro.setAdapter(adapter);
                            //  calcularPromedios();

                        }
                    }
                }

            if(adapter3.getSelect()!=null) {
                ArrayList<MateriaNota> selecionadasRepro = adapter3.getSelect();
                    for (MateriaNota materiaNotaR : selecionadasRepro) {
                        if (mostrarReprobadas.contains(materiaNotaR) && adapter3 != null) {
                            mostrarReprobadas.remove(materiaNotaR);
                            listaMaterias.remove(materiaNotaR);
                            adapter3 = new MateriaNotaAdapter(mostrarReprobadas, HistorialAcademicoActivity.this);
                            //recyclerViewApro.setItemAnimator(new DefaultItemAnimator());
                            recyclerViewRepro.setAdapter(adapter3);
                            // calcularPromedios();

                        }
                    }
                }



        });


        button = findViewById(R.id.ver_Estadisticas);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(HistorialAcademicoActivity.this);
                View view2 = getLayoutInflater().inflate(R.layout.dialog_estadisticas, null);
                promedioGen = view2.findViewById(R.id.promedio_nota);
                promedioMateriasApr = view2.findViewById(R.id.promedio_notamateriasApro);
                numMateriasApro = view2.findViewById(R.id.num_materiasApro);
                numMateriasRepro = view2.findViewById(R.id.materiasRep);
                numMateriasCursa = view2.findViewById(R.id.num_materiasCursadas);


                promedioMateriasApr =view2.findViewById(R.id.promedio_notamateriasApro);
                EstadisticaHA estadisticaHA = new EstadisticaHA(listaMaterias,mostrarAprobadas);
                promedioGen.setText(String.valueOf(estadisticaHA.calcularPromedioGeneral()));
                promedioMateriasApr.setText(String.valueOf(estadisticaHA.calcularPromedioMateriasA()));

                numMateriasApro.setText(String.valueOf(mostrarAprobadas.size()));
                numMateriasRepro.setText(String.valueOf(mostrarReprobadas.size()));
                numMateriasCursa.setText(String.valueOf(listaMaterias.size()));

                builder.setView(view2);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }
    public void getDisplaySize (){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        (HistorialAcademicoActivity.this).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        //if you need three fix imageview in width
        int devicewidth = displaymetrics.widthPixels / 3;

        //if you need 4-5-6 anything fix imageview in height
        int deviceheight = displaymetrics.heightPixels / 4;

      //  ViewGroup.LayoutParams params=recyclerViewRepro.getLayoutParams();
       // params.height=deviceheight;
      //  params.width=devicewidth;
     //   recyclerViewRepro.setLayoutParams(params);



    }
}



















