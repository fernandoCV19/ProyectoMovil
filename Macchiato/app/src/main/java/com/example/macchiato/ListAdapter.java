package com.example.macchiato;

import android.content.Context;
import android.view.*;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<ListElement> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public int getItemCount(){ return mData.size();}

    @NotNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_element, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder,final int position){
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListElement> items){mData=items;}


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nomMateria,nomDocente,horario;

        public ViewHolder(View itemView) {
            super(itemView);

            this.nomMateria = itemView.findViewById(R.id.nomMateriaHorario);
            this.nomDocente = itemView.findViewById(R.id.nomDocenteHorario);
            this.horario= itemView.findViewById(R.id.horas);
        }

        public void bindData(final ListElement item){
            nomDocente.setText(item.getNomDocente());
            nomMateria.setText(item.getNomMateriaHorario());
            horario.setText(item.getHorario());
        }
    }
}
