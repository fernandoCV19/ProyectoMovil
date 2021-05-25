package com.example.macchiato;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.Spinner;
import android.widget.Toast;

import com.example.macchiato.Models.MateriaNota;
import com.example.macchiato.Servicios.Iniciador;

import java.util.ArrayList;

public class HistorialAcademicoActivity extends AppCompatActivity {
    ImageButton imageB;

    Spinner mSpinner;
    Spinner  nSpinner;
    EditText editText;
    ArrayList<MateriaNota> mostrar;
    RecyclerView recyclerViewApro;
    RecyclerView recyclerViewRepro;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_academico);
        Iniciador iniciador = new Iniciador();
        try {
            iniciador.iniciar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mostrar = new ArrayList<>();
        imageB = (ImageButton) findViewById(R.id.añadir);
        imageB.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(HistorialAcademicoActivity.this);
            View view1 = getLayoutInflater().inflate(R.layout.layout_dialog, null);
             //spinners
             mSpinner = (Spinner) view1.findViewById(R.id.materia);
             nSpinner =(Spinner) view1. findViewById(R.id.nivel);
             editText=(EditText) view1.findViewById(R.id.editText);



        nSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String selectedClass = parent.getItemAtPosition(position).toString();
                switch (selectedClass)
                {
                    case "A":

                        mSpinner.setAdapter(new ArrayAdapter<String>(HistorialAcademicoActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelA)));
                        break;

                    case "B":
                        mSpinner.setAdapter(new ArrayAdapter<String>(HistorialAcademicoActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelB)));
                        break;

                    case "C":
                        mSpinner.setAdapter(new ArrayAdapter<String>(HistorialAcademicoActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelC)));
                        break;

                    case "D":
                        mSpinner.setAdapter(new ArrayAdapter<String>(HistorialAcademicoActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelD)));
                        break;
                    case "E":
                        mSpinner.setAdapter(new ArrayAdapter<String>(HistorialAcademicoActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelE)));
                        break;
                    case "F":
                        mSpinner.setAdapter(new ArrayAdapter<String>(HistorialAcademicoActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelF)));
                        break;
                    case "G":
                        mSpinner.setAdapter(new ArrayAdapter<String>(HistorialAcademicoActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelG)));
                        break;
                    case "H":
                        mSpinner.setAdapter(new ArrayAdapter<String>(HistorialAcademicoActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelH)));
                        break;

                }


                mSpinner.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

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
                AlertDialog dialog =builder.create();
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
                                        if(!mostrar .contains(materiaNota)) {
                                            Toast.makeText(HistorialAcademicoActivity.this, "Añadido", Toast.LENGTH_SHORT).show();

                                            recyclerViewApro = (RecyclerView) findViewById(R.id.list_materiasAprobadas);
                                            recyclerViewRepro = (RecyclerView) findViewById(R.id.lista_MateriasReprobadas);
                                            if (numero >= 51) {
                                                recyclerViewApro.setLayoutManager(new LinearLayoutManager(HistorialAcademicoActivity.this));//getContext()
                                                MateriaNotaAdapter adapter = new MateriaNotaAdapter(mostrar, HistorialAcademicoActivity.this);
                                                recyclerViewApro.setAdapter(adapter);
                                                recyclerViewApro.setHasFixedSize(true);
                                                mostrar.add(materiaNota);
                                            } else {

                                                recyclerViewRepro.setLayoutManager(new LinearLayoutManager(HistorialAcademicoActivity.this));//getContext()
                                                MateriaNotaAdapter adapter3 = new MateriaNotaAdapter(mostrar, HistorialAcademicoActivity.this);
                                                recyclerViewRepro.setAdapter(adapter3);
                                                recyclerViewRepro.setHasFixedSize(true);
                                            }
                                        }

                                        //if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("Elige un nivel")) {
                                        //  }




                                    }

                                });
                            }
                });
                dialog.show();
        });
    }
}






