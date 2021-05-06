package com.example.macchiato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MateriaAdapter extends RecyclerView.Adapter<MateriaAdapter.ViewHolder> {

    private List<MateriaElement> mData;
    private LayoutInflater mInflater;
    private Context context;

    public MateriaAdapter(List<MateriaElement> mData, Context context) {
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

    public void setItems(List<MateriaElement> items){mData=items;}


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nomMateria,codMateria;

        public ViewHolder(View itemView) {
            super(itemView);

            this.nomMateria = itemView.findViewById(R.id.nomMateria);
            this.codMateria = itemView.findViewById(R.id.codMateria);
        }

        public void bindData(final MateriaElement item){
            nomMateria.setText(item.getNombreMateria());
            codMateria.setText(item.getCodCarrera());
        }
    }
}