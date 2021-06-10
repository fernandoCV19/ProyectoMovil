package com.example.macchiato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.Grupo;
import com.example.macchiato.Models.Grupo;
import com.example.macchiato.Servicios.RegistroJSON;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GrupoHorarioAdapter extends RecyclerView.Adapter<GrupoHorarioAdapter.ViewHolder>{
    private List<Grupo> mData;
    private ArrayList<Integer> seleccionados;
    private LayoutInflater mInflater;
    private Context context;
    RecyclerView recyclerView;
    View itemLayoutView;
    private  ArrayList<Integer> selecs;

    public GrupoHorarioAdapter(List<Grupo> mData, Context context,ArrayList<Integer> selecs) {
        this.mData = mData;
        this.context = context;
        this.selecs=selecs;
        seleccionados=new ArrayList<>();
    }

    public ArrayList<Integer> getSeleccionados() {
        return seleccionados;
    }



    public List<Grupo> getmData() {
        return mData;
    }

    @Override
    public int getItemCount(){ return mData.size();}

    @NotNull
    @Override
    public GrupoHorarioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.selected_materia, null);

        // create ViewHolder

        GrupoHorarioAdapter.ViewHolder viewHolder = new GrupoHorarioAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final GrupoHorarioAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));

    }

    public void setItems(List<Grupo> items){mData=items;}


    public class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBoxGrupo;

        public ViewHolder(View itemView) {
            super(itemView);
            context=itemView.getContext();
            this.checkBoxGrupo = itemView.findViewById(R.id.checkBoxMateria);
        }

        public void bindData(final Grupo item){
            String mostrar=item.getGrupo()+" - "+item.getDocente();
            checkBoxGrupo.setText(mostrar);
            if(selecs!=null){
               for(Integer integer : selecs){
                   if(integer == item.getID()){
                       checkBoxGrupo.setChecked(true);
                   }
               }
            }
            checkBoxGrupo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RegistroJSON registroJSON= new RegistroJSON();
                    if (checkBoxGrupo.isChecked()) {
                        if(!selecs.contains(item.getID())) {
                            selecs.add(item.getID());
                            Toast.makeText(context.getApplicationContext(), "añadido", Toast.LENGTH_SHORT).show();
                            try {
                                registroJSON.aniadirMateria(item.getID(),"materiasActuales", context, "registro.json");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    else if (!checkBoxGrupo.isChecked()) {
                        selecs.remove((Object)item.getID());
                        //Toast.makeText(context, "eliminado", Toast.LENGTH_SHORT).show();
                        try {
                            registroJSON.quitarMateria(item.getID(),"materiasActuales", context, "registro.json");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //int quitar =item.getID()*(-1);
                       //if(!seleccionados.contains(quitar)){
                           //seleccionados.add(quitar);

                       //}
                    }

                }
            });



        }

    }
}
