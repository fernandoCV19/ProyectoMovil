package com.example.macchiato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.macchiato.Models.Clase;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AlarmaAdapter extends RecyclerView.Adapter<AlarmaAdapter.ViewHolder> {
    private List<Clase> mData;
    private ArrayList<Integer> seleccionados;
    private LayoutInflater mInflater;
    private Context context;
    RecyclerView recyclerView;
    View itemLayoutView;
    private  ArrayList<Integer> selecs;

    public AlarmaAdapter(List<Clase> mData, Context context,ArrayList<Integer> selecs) {
        this.mData = mData;
        this.context = context;
        this.selecs=selecs;
        seleccionados=new ArrayList<>();
    }

    public ArrayList<Integer> getSeleccionados() {
        return seleccionados;
    }



    public List<Clase> getmData() {
        return mData;
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

        Switch aswitch;
        TextView diaAlarma,docente,horaAlarma;
        public ViewHolder(View itemView) {
            super(itemView);
            context=itemView.getContext();
            this.aswitch=itemView.findViewById(R.id.switchAlarma);
            this.docente=itemView.findViewById(R.id.nomDocenteAlarma);
            this.diaAlarma=itemView.findViewById(R.id.diaAlarma);
            this.horaAlarma=itemView.findViewById(R.id.horaAlarma);
        }

        public void bindData(final Clase item){

        }

    }
}

