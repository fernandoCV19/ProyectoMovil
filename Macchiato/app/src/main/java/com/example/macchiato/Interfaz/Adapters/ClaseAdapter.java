package com.example.macchiato.Interfaz.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macchiato.Models.Clase;
import com.example.macchiato.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * esta clase ayuda a adaptar datos recibidos en una lista de clases
 * hacia un recyclerview para poder visualizarlas
 */
public class ClaseAdapter extends RecyclerView.Adapter<ClaseAdapter.ViewHolder> {
    private List<Clase> mData;
    private LayoutInflater mInflater;
    private Context context;
    RecyclerView recyclerView;

    public ClaseAdapter(List<Clase> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }



    @Override
    public int getItemCount(){ return mData.size();}

    @NotNull
    @Override
    public ClaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clase_element, null);

        // create ViewHolder

        ClaseAdapter.ViewHolder viewHolder = new ClaseAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ClaseAdapter.ViewHolder holder, final int position){

        holder.bindData(mData.get(position));

    }

    public void setItems(List<Clase> items){mData=items;}


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView diaClase,horaClase,aulaClase;
        CardView color;
        public ViewHolder(View itemView) {
            super(itemView);
            this.diaClase = itemView.findViewById(R.id.diaClase);
            this.horaClase = itemView.findViewById(R.id.horaClase);
            this.aulaClase =itemView.findViewById(R.id.aulaClase);
            this.color= itemView.findViewById(R.id.cardViewClase);
        }

        public void bindData(final Clase item){
            String horaDeClase=item.getHoraInicio()+"-"+item.getHoraFinal();
            diaClase.setText(item.getDia().toString());
            horaClase.setText(horaDeClase);
            aulaClase.setText(item.getAula());
        }

    }

}
