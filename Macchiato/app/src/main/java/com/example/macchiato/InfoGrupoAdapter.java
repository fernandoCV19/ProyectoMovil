package com.example.macchiato;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.Grupo;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class InfoGrupoAdapter extends RecyclerView.Adapter<InfoGrupoAdapter.ViewHolder>{
    private List<Grupo> mData;
    private LayoutInflater mInflater;
    private Context context;
    RecyclerView recyclerView;
    View itemLayoutView;
    public InfoGrupoAdapter(List<Grupo> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }



    @Override
    public int getItemCount(){ return mData.size();}

    @NotNull
    @Override
    public InfoGrupoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.infogrupo_element, null);

        // create ViewHolder

        InfoGrupoAdapter.ViewHolder viewHolder = new InfoGrupoAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final InfoGrupoAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));

    }

    public void setItems(List<Grupo> items){mData=items;}


    public class ViewHolder extends RecyclerView.ViewHolder{

        Context context;
        TextView numGrupoInfo,nomDocenteInfo;
        RecyclerView horariosInfo;
        CardView color;
        Button botonDetalles;

        public ViewHolder(View itemView) {
            super(itemView);
            context=itemView.getContext();
            this.numGrupoInfo= itemView.findViewById(R.id.numGrupoInfo);
            this.nomDocenteInfo = itemView.findViewById(R.id.nomDocenteInfo);
            this.horariosInfo=itemView.findViewById(R.id.horariosInfo);
            this.botonDetalles=itemView.findViewById(R.id.botonDetalles);
            this.color= itemView.findViewById(R.id.cardViewMateriaInfo);
        }

        public void bindData(final Grupo item){
             ArrayList<Clase> clases=item.getClases();
             ClaseAdapter claseAdapter=new ClaseAdapter(clases,context);
             recyclerView = (RecyclerView) itemLayoutView.findViewById(R.id.horariosInfo);
             recyclerView.setHasFixedSize(true);
             recyclerView.setLayoutManager(new LinearLayoutManager(context));
             recyclerView.setAdapter(claseAdapter);
             numGrupoInfo.setText(item.getGrupo());
             nomDocenteInfo.setText(item.getDocente());

        }

    }
}
