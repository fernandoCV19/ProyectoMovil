package com.example.macchiato;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.*;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        ImageView iconImage;
        TextView name,city,status;

        public ViewHolder(View itemView) {
            super(itemView);
            this.iconImage = itemView.findViewById(R.id.icono);
            this.name = itemView.findViewById(R.id.nomMateria);
            this.city = itemView.findViewById(R.id.nomDocente);
            this.status = itemView.findViewById(R.id.horas);
        }

        public void bindData(final ListElement item){
            iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            name.setText(item.getName());
            city.setText(item.getCity());
            status.setText(item.getStatus());
        }
    }
}
