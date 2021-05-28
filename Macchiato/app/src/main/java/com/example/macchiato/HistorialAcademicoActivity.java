package com.example.macchiato;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
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

import com.example.macchiato.Models.MateriaNota;
import com.example.macchiato.Parser.ParserMateriaID;
import com.example.macchiato.Servicios.Iniciador;
import com.example.macchiato.Servicios.RegistroJSON;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HistorialAcademicoActivity extends AppCompatActivity {

    ArrayList<MateriaNota> mostrarAprobadas;
    ArrayList<MateriaNota> mostrarReprobadas;
    RecyclerView recyclerViewApro;
    RecyclerView recyclerViewRepro;
    Button button;
    ArrayList<MateriaNota>listaMaterias;
    TextView textView;
    Spinner mSpinner;
    Spinner nSpinner;
    EditText editText;


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

        mostrarAprobadas = new ArrayList<>();
        mostrarReprobadas = new ArrayList<>();
        listaMaterias = new ArrayList<>();
        FloatingActionButton fab = findViewById(R.id.añadir_floating);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog( savedInstanceState);
            }
        });
        button = findViewById(R.id.ver_Estadisticas);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int suma=0;
                AlertDialog.Builder builder = new AlertDialog.Builder(HistorialAcademicoActivity.this);
                View view2 = getLayoutInflater().inflate(R.layout.dialog_estadisticas, null);
                textView=view2.findViewById(R.id.promedio_nota);
                for (MateriaNota materiaNota:listaMaterias){
                    suma +=materiaNota.getNota();

                }
                int promedioGeneral=suma/listaMaterias.size();
                textView.setText(String.valueOf(promedioGeneral));

                builder.setView(view2);
                AlertDialog dialog =builder.create();
                dialog.show();
            }
        });
    }
    public void openDialog(Bundle savedInstanceState){
        DialogMateriaNota dialogMateriaNota=new DialogMateriaNota();

     //   AlertDialog dialog =(AlertDialog) dialogMateriaNota.getDialog();
       Dialog dialog1 =( Dialog)dialogMateriaNota.onCreateDialog(savedInstanceState);
        AlertDialog dialog =(AlertDialog) dialog1;
               dialog.setOnShowListener(new DialogInterface.OnShowListener() {

                   @Override
                   public void onShow(DialogInterface dialogInterface) {

                       Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                       button.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               mSpinner = dialogMateriaNota.getmSpinner();
                               editText = dialogMateriaNota.getEditText();
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
                                   new RegistroJSON().aniadirNota(new ParserMateriaID().getID(select), numero, getApplicationContext());
                               } catch (Exception e) {
                                   e.printStackTrace();
                               }
                               if (!mostrarAprobadas.contains(materiaNota)) {
                                   Toast.makeText(HistorialAcademicoActivity.this, "Añadido", Toast.LENGTH_SHORT).show();

                                   recyclerViewApro = (RecyclerView) findViewById(R.id.list_materiasAprobadas);
                                   recyclerViewRepro = (RecyclerView) findViewById(R.id.lista_MateriasReprobadas);
                                   listaMaterias.add(materiaNota);
                                   if (numero >= 51) {
                                       recyclerViewApro.setLayoutManager(new LinearLayoutManager(HistorialAcademicoActivity.this));//getContext()
                                       MateriaNotaAdapter adapter = new MateriaNotaAdapter(mostrarAprobadas, HistorialAcademicoActivity.this);
                                       recyclerViewApro.setAdapter(adapter);
                                       recyclerViewApro.setHasFixedSize(true);
                                       mostrarAprobadas.add(materiaNota);
                                   } else {

                                       recyclerViewRepro.setLayoutManager(new LinearLayoutManager(HistorialAcademicoActivity.this));//getContext()
                                       MateriaNotaAdapter adapter3 = new MateriaNotaAdapter(mostrarReprobadas, HistorialAcademicoActivity.this);
                                       recyclerViewRepro.setAdapter(adapter3);
                                       recyclerViewRepro.setHasFixedSize(true);
                                       mostrarReprobadas.add(materiaNota);
                                   }
                               }


                               //if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("Elige un nivel")) {
                               //  }
                           }
                       });

                   }


               });
        dialogMateriaNota.show(getSupportFragmentManager(),"DIALOG");
    }
}
















