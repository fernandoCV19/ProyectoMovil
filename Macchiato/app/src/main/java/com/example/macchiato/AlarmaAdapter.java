package com.example.macchiato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.macchiato.Servicios.Alarma.Alarma;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/**
 * Vista de las CardView
 * */
public class  AlarmaAdapter extends RecyclerView.Adapter<AlarmaAdapter.MyViewHolder> {
    private List<Alarma> alarmaList;

    public AlarmaAdapter(ArrayList<Alarma> alarmaList) {
        this.alarmaList = alarmaList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView diaAlarma, horaAlarma, nomMateria;
        Context context;


        public MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            this.diaAlarma = itemView.findViewById(R.id.diaAlarma);
            this.horaAlarma = itemView.findViewById(R.id.horaAlarma);
            this.nomMateria = itemView.findViewById(R.id.nomMateriaAlarma);


        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alarma_element, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Alarma alarma = alarmaList.get(position);
        String horaFormateada = (alarma.getHora() < 10) ? ("0" + alarma.getHora()) : String.valueOf(alarma.getHora());
        String minutoFormateado = (alarma.getMinuto() < 10) ? ("0" + alarma.getMinuto()) : String.valueOf(alarma.getMinuto());
        holder.horaAlarma.setText(horaFormateada + ":" + minutoFormateado);
        holder.diaAlarma.setText(showDiasFromList(alarma.getDias()));
        holder.nomMateria.setText(alarma.getTitulo());
    }
    @Override
    public int getItemCount() {
        return alarmaList.size();
    }
    private String showDiasFromList(List<String> diasAlarma) {
        if (diasAlarma.size() == 7)
            return "Todos los días";
        return StringUtils.join(diasAlarma, " • ");
    }
}
