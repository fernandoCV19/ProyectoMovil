package com.example.macchiato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.Materia;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MateriaHorarioAdapter extends RecyclerView.Adapter<MateriaHorarioAdapter.ViewHolder>{
    private List<Materia> mData;
    private LayoutInflater mInflater;
    private Context context;
    RecyclerView recyclerView;
    View itemLayoutView;
    public MateriaHorarioAdapter(List<Materia> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }



    @Override
    public int getItemCount(){ return mData.size();}

    @NotNull
    @Override
    public MateriaHorarioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.selected_materia, null);

        // create ViewHolder

        MateriaHorarioAdapter.ViewHolder viewHolder = new MateriaHorarioAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MateriaHorarioAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));

    }

    public void setItems(List<Materia> items){mData=items;}


    public class ViewHolder extends RecyclerView.ViewHolder{

        Context context;
        TextView numMateriaInfo,nomDocenteInfo;
        RecyclerView horariosInfo;
        CardView color;
        Button botonDetalles;

        public ViewHolder(View itemView) {
            super(itemView);
            context=itemView.getContext();

            this.nomDocenteInfo = itemView.findViewById(R.id.nomDocenteInfo);
            this.horariosInfo=itemView.findViewById(R.id.horariosInfo);
            this.botonDetalles=itemView.findViewById(R.id.botonDetalles);
            this.color= itemView.findViewById(R.id.cardViewMateriaInfo);
        }

        public void bindData(final Materia item){


        }

    }
}
