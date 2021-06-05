package com.example.macchiato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.Materia;
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

        SwitchCompat aswitch;
        TextView diaAlarma,horaAlarma,nomMateria;
        public ViewHolder(View itemView) {
            super(itemView);
            context=itemView.getContext();
            this.aswitch=itemView.findViewById(R.id.switchAlarma);
            this.diaAlarma=itemView.findViewById(R.id.diaAlarma);
            this.horaAlarma=itemView.findViewById(R.id.horaAlarma);
            this.nomMateria=itemView.findViewById(R.id.nomMateriaAlarma);
        }

        public void bindData(final Clase item){
           nomMateria.setText(item.getNomMateria());
           String hora=item.getHoraInicio()+"-"+item.getHoraFinal();
           horaAlarma.setText(hora);
           diaAlarma.setText(item.getDia().toString());
        }

    }
}

