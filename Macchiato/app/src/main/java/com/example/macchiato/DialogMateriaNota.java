package com.example.macchiato;

import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.macchiato.Models.MateriaNota;

public class DialogMateriaNota extends AppCompatDialogFragment {

    Spinner mSpinner;
    Spinner nSpinner;
    EditText editText;
    int numero ;
    String select;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        // setContentView(R.layout.file_not_found_dialog);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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

                        mSpinner.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelA)));
                        break;

                    case "B":
                        mSpinner.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelB)));
                        break;

                    case "C":
                        mSpinner.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelC)));
                        break;

                    case "D":
                        mSpinner.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelD)));
                        break;
                    case "E":
                        mSpinner.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelE)));
                        break;
                    case "F":
                        mSpinner.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelF)));
                        break;
                    case "G":
                        mSpinner.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.nivelG)));
                        break;
                    case "H":
                        mSpinner.setAdapter(new ArrayAdapter<String>(getContext(),
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
        builder.setView(view1)
                .setTitle("Añadir materia")
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


               // .setOnShowListener(new DialogInterface.OnShowListener() {
                 //   @Override
                   // public void onShow(DialogInterface dialogInterface) {
                     //   Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                       // button.setOnClickListener(new View.OnClickListener() {
                         //   @Override
                           // public void onClick(View view) {


                    //}
               // });
        return builder.create();
    }
    public int getNumero (){

        return numero;
    }
    public String getSelect (){

        return select;
    }
    public EditText getEditText() {
        return editText;
    }

    public Spinner getmSpinner() {
        return mSpinner;
    }
    // dialog.show();



        //



    }







