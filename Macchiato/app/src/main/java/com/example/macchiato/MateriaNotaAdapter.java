package com.example.macchiato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macchiato.Models.MateriaNota;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * contiene la nota, materia
 * crea las cardview dentro el recyclerview
 */
public class MateriaNotaAdapter extends RecyclerView.Adapter<MateriaNotaAdapter.ViewHolder> {
    private List<MateriaNota> mData;
    private LayoutInflater mInflater;
    private Context context;
    ArrayList<MateriaNota> select;

    public MateriaNotaAdapter(List<MateriaNota> mData, Context context) {
        this.mData = mData;
        this.context = context;
        select = new ArrayList<>();
    }

    public ArrayList<MateriaNota> getSelect() {
        return select;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.materias_element_historial, null);

        // create ViewHolder

           MateriaNotaAdapter.ViewHolder viewHolder = new MateriaNotaAdapter.ViewHolder(itemLayoutView);
      //  ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( MateriaNotaAdapter.ViewHolder holder, int position) {

        holder.bindData(mData.get(position));
        final MateriaNota mn =mData.get(position);
        holder.checkBox.setChecked(mn.esSeleccionado());


        holder.setItemClickListener( new MateriaNotaAdapter.ViewHolder.ItemClickListener() {
            @Override
            public void OnItemClick(View v, int pos) {
                CheckBox checkBox = (CheckBox) v;
                MateriaNota materiaNota = mData.get(pos);

                if (checkBox.isChecked()) {
                    materiaNota.setSeleccionado(true);
                    select.add(materiaNota);


                } else {
                    if (!checkBox.isChecked()) {
                        materiaNota.setSeleccionado(false);

                    }
                }


            }
        });

    }


    public void setItems(List<MateriaNota> items) {
        mData = items;
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nomMateria, notaMateria;
        CardView color;
        CheckBox checkBox;
        ItemClickListener itemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);

            this.nomMateria = itemView.findViewById(R.id.nomMateriaX);
            this.notaMateria = itemView.findViewById(R.id.nota);
            this.color = itemView.findViewById(R.id.cardViewMateria);
            checkBox= itemView.findViewById(R.id.eliminar);

            checkBox.setOnClickListener(this);
        }


        public void bindData(final MateriaNota item) {
            nomMateria.setText(item.getMateriaId());
            int num = item.getNota();
            notaMateria.setText(String.valueOf(num));
            // color.setCardBackgroundColor(Color.parseColor(item.getColor()));
        }

        public void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener = ic;
        }

        public ItemClickListener ItemClickListener() {
            return itemClickListener;
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.OnItemClick(v, getLayoutPosition());

        }

        interface ItemClickListener {
            void OnItemClick(View v, int pos);


        }
    }
}
