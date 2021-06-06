package com.example.macchiato;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;


import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macchiato.Models.Materia;
import com.example.macchiato.Models.MateriaNota;
import com.example.macchiato.Parser.ParserMateriaID;
import com.example.macchiato.Servicios.ConsultorMaterias;
import com.example.macchiato.Servicios.EstadisticaHA;
import com.example.macchiato.Servicios.Iniciador;
import com.example.macchiato.Servicios.RegistroJSON;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.HashMap;

public class HistorialAcademicoActivity extends AppCompatActivity {


    Spinner mSpinner;
    Spinner nSpinner;
    EditText editText;
    ArrayList<MateriaNota> mostrarAprobadas;
    ArrayList<MateriaNota> mostrarReprobadas;
    ArrayList<MateriaNota> listaAprobadasID;
    ArrayList<MateriaNota> listaRebrobadasID;
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
    MateriaNotaAdapter adapterReprobadas;

    ConsultorMaterias consultorMaterias;


    public HistorialAcademicoActivity() {

        mostrarAprobadas = new ArrayList<>();
        mostrarReprobadas = new ArrayList<>();
        listaMaterias = new ArrayList<>();
    }

    public HistorialAcademicoActivity(ArrayList<MateriaNota> mostrarAprobadas, ArrayList<MateriaNota> mostrarReprobadas) {
        this.mostrarAprobadas = mostrarReprobadas;
        this.mostrarReprobadas = mostrarReprobadas;
        listaMaterias = new ArrayList<>();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_academico);
        mostrarAprobadas = new ArrayList<>();
        mostrarReprobadas = new ArrayList<>();
        listaAprobadasID = new ArrayList<>();
        listaRebrobadasID = new ArrayList<>();
        adapterReprobadas = new MateriaNotaAdapter(mostrarReprobadas, HistorialAcademicoActivity.this);
        adapter = new MateriaNotaAdapter(mostrarAprobadas, HistorialAcademicoActivity.this);
        RegistroJSON rj = new RegistroJSON();

        RegistroJSON registroJSON = new RegistroJSON();
        try {
            listaAprobadasID = registroJSON.getMateriaNota("materiasAprobadas", this, "registro.json");
            listaRebrobadasID = registroJSON.getMateriaNota("materiasReprobadas", this, "registro.json");
            consultorMaterias = new ConsultorMaterias();
            for (MateriaNota materiaNota : listaAprobadasID) {
                String nombre = consultorMaterias.getNombreMateria(materiaNota.getMateriaId());
                MateriaNota materiaNota1 = new MateriaNota(nombre, materiaNota.getNota());
                mostrarAprobadas.add(materiaNota1);
                listaMaterias.add(materiaNota1);
            }
            for (MateriaNota materiaNota : listaRebrobadasID) {
                String nombre = consultorMaterias.getNombreMateria(materiaNota.getMateriaId());
                MateriaNota materiaNota1 = new MateriaNota(nombre, materiaNota.getNota());
                mostrarReprobadas.add(materiaNota1);
                listaMaterias.add(materiaNota1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        EstadisticaHA estadisticaHA = new EstadisticaHA(listaMaterias, mostrarAprobadas,mostrarReprobadas);
        estadisticaHA.calcularPromedioGeneral();
        estadisticaHA.calcularPromedioMateriasA();
        recyclerViewApro = (RecyclerView) findViewById(R.id.lista_MateriasAprobadas);
        recyclerViewRepro = (RecyclerView) findViewById(R.id.list_materiasReprobadas);

        if (!mostrarAprobadas.isEmpty() ) {
            adapter = new MateriaNotaAdapter(mostrarAprobadas, HistorialAcademicoActivity.this);
            recyclerViewApro.setLayoutManager(new LinearLayoutManager(HistorialAcademicoActivity.this));//getContext()
            recyclerViewApro.setAdapter(adapter);
            recyclerViewApro.setHasFixedSize(true);
        }
        if (!mostrarReprobadas.isEmpty()) {
            adapterReprobadas = new MateriaNotaAdapter(mostrarReprobadas, HistorialAcademicoActivity.this);
            recyclerViewRepro.setLayoutManager(new LinearLayoutManager(HistorialAcademicoActivity.this));//getContext()
            recyclerViewRepro.setAdapter(adapterReprobadas);
            recyclerViewRepro.setHasFixedSize(true);
        }

        FloatingActionButton fab = findViewById(R.id.añadir_floating);
        fab.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(HistorialAcademicoActivity.this);
            View view1 = getLayoutInflater().inflate(R.layout.layout_dialog, null);
            //spinners
            mSpinner = (Spinner) view1.findViewById(R.id.materia);
            nSpinner = (Spinner) view1.findViewById(R.id.nivel);
            editText = (EditText) view1.findViewById(R.id.editText);

            Iniciador iniciador=new Iniciador();


            try {
                iniciador.iniciar(this);
            } catch (Exception e) {
                e.printStackTrace();
            }

            ConsultorMaterias cs =new ConsultorMaterias();
            HashMap<Character, ArrayList<Materia>> list=cs.getLisClasificada();
            Character [] nomNiveles =new Character[9];

            int j = 0;
            for (Character nivel : list.keySet()) {

                nomNiveles[j] = nivel;
                j++;
            }

            ArrayAdapter<Character> adapter2 = new ArrayAdapter<Character>(this, R.layout.simple_spinner, nomNiveles);
            nSpinner.setAdapter(adapter2);

            nSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    String selectedClass = parent.getItemAtPosition(position).toString();
                    switch (selectedClass) {
                        case "A":

                            mSpinner.setAdapter(new ArrayAdapter<>(HistorialAcademicoActivity.this,
                                    R.layout.simple_spinner,
                                    getResources().getStringArray(R.array.nivelA)));
                            break;

                        case "B":
                            mSpinner.setAdapter(new ArrayAdapter<>(HistorialAcademicoActivity.this,
                                    R.layout.simple_spinner,
                                    getResources().getStringArray(R.array.nivelB)));
                            break;

                        case "C":
                            mSpinner.setAdapter(new ArrayAdapter<>(HistorialAcademicoActivity.this,
                                    R.layout.simple_spinner,
                                    getResources().getStringArray(R.array.nivelC)));
                            break;

                        case "D":
                            mSpinner.setAdapter(new ArrayAdapter<>(HistorialAcademicoActivity.this,
                                    R.layout.simple_spinner,
                                    getResources().getStringArray(R.array.nivelD)));
                            break;
                        case "E":
                            mSpinner.setAdapter(new ArrayAdapter<>(HistorialAcademicoActivity.this,
                                    R.layout.simple_spinner,
                                    getResources().getStringArray(R.array.nivelE)));
                            break;
                        case "F":
                            mSpinner.setAdapter(new ArrayAdapter<>(HistorialAcademicoActivity.this,
                                    R.layout.simple_spinner,
                                    getResources().getStringArray(R.array.nivelF)));
                            break;
                        case "G":
                            mSpinner.setAdapter(new ArrayAdapter<>(HistorialAcademicoActivity.this,
                                    R.layout.simple_spinner,
                                    getResources().getStringArray(R.array.nivelG)));
                            break;
                        case "H":
                            mSpinner.setAdapter(new ArrayAdapter<>(HistorialAcademicoActivity.this,
                                    R.layout.simple_spinner,
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
                            dialog.dismiss();

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


                            if (!mostrarAprobadas.contains(materiaNota)) {
                                Toast.makeText(HistorialAcademicoActivity.this, "Añadido", Toast.LENGTH_SHORT).show();
                                listaMaterias.add(materiaNota);

                                try {

                                    int idMat = new ParserMateriaID().getID(select);
                                    rj.aniadirNota(idMat, numero, getApplicationContext(), "registro.json");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                recyclerViewApro = (RecyclerView) findViewById(R.id.lista_MateriasAprobadas);
                                recyclerViewRepro = (RecyclerView) findViewById(R.id.list_materiasReprobadas);

                                if (numero >= 51) {
                                    recyclerViewApro.setLayoutManager(new LinearLayoutManager(HistorialAcademicoActivity.this));//getContext()
                                    recyclerViewApro.setItemAnimator(new DefaultItemAnimator());
                                    recyclerViewApro.setAdapter(adapter);
                                    recyclerViewApro.setHasFixedSize(true);
                                    mostrarAprobadas.add(materiaNota);
                                } else {
                                    recyclerViewRepro.setLayoutManager(new LinearLayoutManager(HistorialAcademicoActivity.this));//getContext()
                                    adapterReprobadas = new MateriaNotaAdapter(mostrarReprobadas, HistorialAcademicoActivity.this);
                                    recyclerViewRepro.setItemAnimator(new DefaultItemAnimator());
                                    recyclerViewRepro.setAdapter(adapterReprobadas);
                                    recyclerViewRepro.setHasFixedSize(true);
                                    mostrarReprobadas.add(materiaNota);

                                }
                            }
                        }



                    });
                }
            });
            dialog.show();
        });
        FloatingActionButton remove = findViewById(R.id.floating_eliminar);
        remove.setOnClickListener(vie -> {
            if (adapter.getSelect() != null) {
                ArrayList<MateriaNota> selecionadas = adapter.getSelect();
                for (MateriaNota materiaNota : selecionadas) {
                    mostrarAprobadas.remove(materiaNota);
                    listaMaterias.remove(materiaNota);
                    estadisticaHA.calcularPromedioGeneral();
                    estadisticaHA.calcularPromedioMateriasA();
                    try {

                        String nombre=materiaNota.getMateriaId();
                        int idMat = new ParserMateriaID().getID(nombre);
                        String id =idMat+"";
                        MateriaNota materiaNotaID = new MateriaNota(id, materiaNota.getNota());
                        rj.quitarMateria("materiasAprobadas", materiaNotaID, this, "registro.json");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                adapter = new MateriaNotaAdapter(mostrarAprobadas, HistorialAcademicoActivity.this);
                recyclerViewApro.setItemAnimator(new DefaultItemAnimator());
                recyclerViewApro.setAdapter(adapter);
            }
            if (adapterReprobadas.getSelect() != null) {
                ArrayList<MateriaNota> selecionadasRepro = adapterReprobadas.getSelect();

                for (MateriaNota materiaNotaR : selecionadasRepro) {

                    mostrarReprobadas.remove(materiaNotaR);
                    listaMaterias.remove(materiaNotaR);
                    estadisticaHA.calcularPromedioGeneral();
                    estadisticaHA.calcularPromedioMateriasA();
                    try {
                        String nombre=materiaNotaR.getMateriaId();
                        int idMat = new ParserMateriaID().getID(nombre);
                        String id =idMat+"";
                        MateriaNota materiaNotaID =new MateriaNota(id,materiaNotaR.getNota());
                        rj.quitarMateria("materiasReprobadas", materiaNotaID, this, "registro.json");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                adapterReprobadas = new MateriaNotaAdapter(mostrarReprobadas, HistorialAcademicoActivity.this);
                recyclerViewRepro.setItemAnimator(new DefaultItemAnimator());
                recyclerViewRepro.setAdapter(adapterReprobadas);
            }
            Toast.makeText(HistorialAcademicoActivity.this, "Eliminado", Toast.LENGTH_SHORT).show();

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


                promedioMateriasApr = view2.findViewById(R.id.promedio_notamateriasApro);
                EstadisticaHA estadisticaHA = new EstadisticaHA(listaMaterias, mostrarAprobadas,mostrarReprobadas);
                promedioGen.setText(String.valueOf(estadisticaHA.calcularPromedioGeneral()));
                promedioMateriasApr.setText(String.valueOf(estadisticaHA.calcularPromedioMateriasA()));

                numMateriasApro.setText(String.valueOf(estadisticaHA.getMateriasAprobadas()));
                numMateriasRepro.setText(String.valueOf(estadisticaHA.getMateriasReprobadas()));
                numMateriasCursa.setText(String.valueOf(estadisticaHA.getListaMaterias()));

                builder.setView(view2);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }
}



















