package com.example.macchiato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;


import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.Materia;
import com.example.macchiato.Servicios.Alarma;
import com.example.macchiato.Servicios.ConsultorMaterias;
import com.example.macchiato.Servicios.Iniciador;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AlarmaAdapter extends RecyclerView.Adapter<AlarmaAdapter.ViewHolder> {
    private List<Clase> mData;
    private LayoutInflater mInflater;
    private Context context;
    RecyclerView recyclerView;
    View itemLayoutView;
    private  ArrayList<Integer> selecs;


    public AlarmaAdapter(List<Clase> mData, Context context) {
        this.mData = mData;
        this.context = context;

    }

    @Override
    public int getItemCount(){ return mData.size();}

    @NotNull
    @Override
    public AlarmaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alarma_element, null);

        // create ViewHolder

        AlarmaAdapter.ViewHolder viewHolder = new AlarmaAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AlarmaAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));

    }

    public void setItems(List<Clase> items){mData=items;}


    public class ViewHolder extends RecyclerView.ViewHolder {
        int minutos;
        SwitchCompat aswitch;
        TextView diaAlarma, horaAlarma, nomMateria;
        Spinner spinnerMinutosAntes;
        Context context;
        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            this.aswitch = itemView.findViewById(R.id.switchAlarma);
            this.diaAlarma = itemView.findViewById(R.id.diaAlarma);
            this.horaAlarma = itemView.findViewById(R.id.horaAlarma);
            this.nomMateria = itemView.findViewById(R.id.nomMateriaAlarma);
            this.spinnerMinutosAntes = (Spinner) itemView.findViewById(R.id.spinnerMinutos);
        }

        public void bindData(final Clase item) {
            int min=0;
            nomMateria.setText(item.getNomMateria());
            String hora = item.getHoraInicio() + "-" + item.getHoraFinal();
            horaAlarma.setText(hora);
            diaAlarma.setText(item.getDia().toString());
            spinnerMinutosAntes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String minutosAntes = parent.getItemAtPosition(position).toString();
                    switch(minutosAntes){
                        case "5 minutos antes":
                           minutos=5;
                        break;
                        case "10 minutos antes":
                            minutos=10;
                            break;
                        case "15 minutos antes":
                            minutos=15;
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            String[] hora_minuto = item.getHoraInicio().split(":");
            int horaAlarma = Integer.parseInt(hora_minuto[0]);
            int minutoAlarma = Integer.parseInt(hora_minuto[1])-minutos;

            Alarma alarma= new Alarma(item.getAula()+" - "+item.getNomMateria(),horaAlarma,minutoAlarma,item.getDia().toString());

            aswitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(aswitch.isChecked()){
                       alarma.establerAlarma(context);
                    }else{
                        alarma.cancelarAlarma(context);
                    }
                }
            });
        }

    }


}

