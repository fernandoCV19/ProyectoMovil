package com.example.macchiato;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macchiato.Models.Materia;
import com.example.macchiato.Models.MateriaNota;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MateriaNotaAdapter extends RecyclerView.Adapter<MateriaNotaAdapter.ViewHolder>{
    private List<MateriaNota> mData;
    private LayoutInflater mInflater;
    private Context context;

    public MateriaNotaAdapter(List<MateriaNota> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public int getItemCount(){ return mData.size();}

    @NotNull
    @Override
    public MateriaNotaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.materias_element_historial, null);

        // create ViewHolder

        MateriaNotaAdapter.ViewHolder viewHolder = new MateriaNotaAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MateriaNotaAdapter.ViewHolder holder, final int position){

        holder.bindData(mData.get(position));
    }

    public void setItems(List<MateriaNota> items){mData=items;}


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nomMateria,notaMateria;
        CardView color;

        public ViewHolder(View itemView) {
            super(itemView);

            this.nomMateria = itemView.findViewById(R.id.nomMateria);
            this.notaMateria = itemView.findViewById(R.id.nota);
            this.color= itemView.findViewById(R.id.cardViewMateria);
        }

        public void bindData(final MateriaNota item){
            nomMateria.setText(item. getMateria());
            int num =item.getNota();
            notaMateria.setText(String.valueOf(num));
          // color.setCardBackgroundColor(Color.parseColor(item.getColor()));
        }
    }
}
