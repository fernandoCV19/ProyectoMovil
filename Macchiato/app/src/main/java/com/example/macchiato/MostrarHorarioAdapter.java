package com.example.macchiato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.macchiato.Models.Grupo;
import com.example.macchiato.Models.Materia;
import com.example.macchiato.Servicios.ConsultorMaterias;
import com.example.macchiato.Servicios.Iniciador;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class  MostrarHorarioAdapter extends RecyclerView.Adapter<MostrarHorarioAdapter.ViewHolder> {
    private List<Grupo> mData;
    private ArrayList<Integer> seleccionados;
    private LayoutInflater mInflater;
    private Context context;
    RecyclerView recyclerView;
    View itemLayoutView;

    public MostrarHorarioAdapter(List<Grupo> mData, Context context) {
        this.mData = mData;
        this.context = context;
        seleccionados=new ArrayList<>();
    }



    @Override
    public int getItemCount(){ return mData.size();}

    @NotNull
    @Override
    public MostrarHorarioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horario_element, null);

        // create ViewHolder

        MostrarHorarioAdapter.ViewHolder viewHolder = new MostrarHorarioAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MostrarHorarioAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));

    }

    public void setItems(List<Grupo> items){mData=items;}


    public class ViewHolder extends RecyclerView.ViewHolder {
        Context context;
        TextView nomMateria;
        TextView docente;
        CheckBox checkBoxGrupo;

        public ViewHolder(View itemView) {
            super(itemView);
            context =itemView.getContext();
            nomMateria=itemView.findViewById(R.id.nomMateriaX);
            docente=itemView.findViewById(R.id.docente_grupo_id);
        }

        public void bindData(final Grupo item){
            String nombre=item.getGrupo();
            String doc= item.getDocente();

            Iniciador iniciador = new Iniciador();
            try {
                iniciador.iniciar(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ArrayList<Materia> materias= ConsultorMaterias.getMaterias();
            for(Materia mate: materias){
                if(mate.getGrupos().equals(mData)){
                    nombre=mate.getNombre();
                    break;
                }
            }
            nomMateria.setText(nombre);
            docente.setText(doc);
            ClaseAdapter claseAdapter=new ClaseAdapter(item.getClases(),context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.infodialog, null);
            recyclerView = (RecyclerView) v.findViewById(R.id.recyclerGrupos);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(claseAdapter);
        }

    }
}
