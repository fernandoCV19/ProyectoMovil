package com.example.macchiato;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macchiato.Models.MateriaNivel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MateriaAdapter extends RecyclerView.Adapter<MateriaAdapter.ViewHolder> {

    private List<MateriaNivel> mData;
    private LayoutInflater mInflater;
    private Context context;

    public MateriaAdapter(List<MateriaNivel> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public int getItemCount(){ return mData.size();}

    @NotNull
    @Override
    public MateriaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.materia_element, null);

        // create ViewHolder

        MateriaAdapter.ViewHolder viewHolder = new MateriaAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MateriaAdapter.ViewHolder holder, final int position){

        holder.bindData(mData.get(position));
    }

    public void setItems(List<MateriaNivel> items){mData=items;}


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nomMateria,codMateria;
        CardView color;

        public ViewHolder(View itemView) {
            super(itemView);

            this.nomMateria = itemView.findViewById(R.id.nomMateria);
            this.codMateria = itemView.findViewById(R.id.codMateria);
            this.color= itemView.findViewById(R.id.cardViewMateria);
        }

        public void bindData(final MateriaNivel item){
            nomMateria.setText(item.getNombreMateria());
            codMateria.setText(item.getCodMateria()+"");
            color.setCardBackgroundColor(Color.parseColor(item.getColor()));
        }
    }

}