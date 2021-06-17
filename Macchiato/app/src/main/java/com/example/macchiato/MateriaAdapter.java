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

import com.example.macchiato.Models.Grupo;
import com.example.macchiato.Models.Materia;
import com.example.macchiato.Servicios.ConsultorMaterias;
import com.example.macchiato.Servicios.Iniciador;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 *sirve para generar los cardviews dentro de materia fragment
 */
public class MateriaAdapter extends RecyclerView.Adapter<MateriaAdapter.ViewHolder> implements View.OnClickListener{

    private List<Materia> mData;
    private LayoutInflater mInflater;
    private Context context;
    RecyclerView recyclerView;

    public MateriaAdapter(List<Materia> mData, Context context) {
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

        holder.setOnClickListeners();
        holder.bindData(mData.get(position));

    }
    @Override
    public void onClick(View view){

    }


    public void setItems(List<Materia> items){mData=items;}


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Context context;
        TextView nomMateria,codMateria;
        CardView color;
        Button botonDetalles;
        Button requisitos;
        public ViewHolder(View itemView) {
            super(itemView);
            context=itemView.getContext();
            this.botonDetalles= itemView.findViewById(R.id.botonDetalles);
            this.nomMateria = itemView.findViewById(R.id.nomMateriaX);
            this.codMateria = itemView.findViewById(R.id.codMateria);
            this.color= itemView.findViewById(R.id.cardViewMateria);
            this.requisitos=itemView.findViewById(R.id.requisitos);
        }

        public void setOnClickListeners(){
            requisitos.setOnClickListener(this);
            botonDetalles.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            if(view.getId()==R.id.botonDetalles) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                Iniciador iniciador = new Iniciador();
                try {
                    iniciador.iniciar(context);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ArrayList<Materia> materias = ConsultorMaterias.getMaterias();
                ArrayList<Grupo> grupos = new ArrayList<>();

                for (Materia m : materias) {
                    if (m.getNombre().contentEquals(nomMateria.getText())) {
                        grupos = m.getGrupos();
                        break;
                    }
                }

                InfoGrupoAdapter infoGrupoAdapter = new InfoGrupoAdapter(grupos, context);
                LayoutInflater inflater = LayoutInflater.from(context);

                View v = inflater.inflate(R.layout.infodialog, null);
                recyclerView = (RecyclerView) v.findViewById(R.id.recyclerGrupos);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(infoGrupoAdapter);

                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                Iniciador iniciador = new Iniciador();
                try {
                    iniciador.iniciar(context);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ArrayList<Materia> materias = ConsultorMaterias.getMaterias();
                ArrayList<String> requisitos = new ArrayList<>();

                for (Materia m : materias) {
                    if (m.getNombre().contentEquals(nomMateria.getText())) {
                        requisitos = m.getRequisitos();
                        break;
                    }
                }
                StringBuilder requi=null;
                for(String s : requisitos){
                   if(requi==null){
                        requi= new StringBuilder();
                       requi.append(s);
                   }else{
                       requi.append("\n").append(s);
                   }
                }

                LayoutInflater inflater = LayoutInflater.from(context);
                View v = inflater.inflate(R.layout.requisitos_dialog, null);
                TextView requisitosL=v.findViewById(R.id.ListaRequisitos);
                requisitosL.setText(requi);
                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        }

        public void bindData(final Materia item){
            nomMateria.setText(item.getNombre());
            codMateria.setText(item.getCodigo());
            color.setCardBackgroundColor(Color.parseColor(item.getColor()));
        }
        
    }

}