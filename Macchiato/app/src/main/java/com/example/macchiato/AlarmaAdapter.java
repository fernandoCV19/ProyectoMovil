package com.example.macchiato;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.Materia;
import com.example.macchiato.Servicios.Alarma;
import com.example.macchiato.Servicios.ConsultorMaterias;
import com.example.macchiato.Servicios.Iniciador;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AlarmaAdapter extends RecyclerView.Adapter<AlarmaAdapter.MyViewHolder> {
    private Context mContext;
    private List<Alarma> alarmaList;
    private Calendar calendar;
    private Context context;
    TinyDB tinyDB;
    public AlarmaAdapter(List<Alarma> mData, Context context) {
        this.alarmaList = mData;
        this.context = context;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        int minutos;
        SwitchCompat aswitch;
        TextView diaAlarma, horaAlarma, nomMateria;
        Spinner spinnerMinutosAntes;
        Context context;


        public MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            this.aswitch = itemView.findViewById(R.id.switchAlarma);
            this.diaAlarma = itemView.findViewById(R.id.diaAlarma);
            this.horaAlarma = itemView.findViewById(R.id.horaAlarma);
            this.nomMateria = itemView.findViewById(R.id.nomMateriaAlarma);
            this.spinnerMinutosAntes = (Spinner) itemView.findViewById(R.id.spinnerMinutos);


        }
    }

    public AlarmaAdapter(ArrayList<Alarma> mContext, Context alarmaList) {
        this.mContext = mContext;
        this.alarmaList = alarmaList;
        calendar = Calendar.getInstance();
      //  tinyDB = new TinyDB(mContext);
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
        String horaFormateada = (alarma.getHora() < 10) ? String.valueOf("0" + alarma.getHora()) : String.valueOf(alarma.getHora());
        String minutoFormateado = (alarma.getMinuto() < 10) ? String.valueOf("0" + alarma.getMinuto()) : String.valueOf(alarma.getMinuto());

      //  holder.horaAlarma.setText(getHoraYmin(alarma.getHora(), alarma.getMinuto()));
        holder.horaAlarma.setText(horaFormateada + ":" + minutoFormateado);
        holder.diaAlarma.setText(showDiasFromList(alarma.getDias()));
        holder.aswitch.setChecked(alarma.isActivado());
        if (!alarma.isActivado()){
            desPintarTxts(holder.diaAlarma);

            desPintarTxts(holder.horaAlarma);
        }
        // Cambiando el estilo cuando switch == true
        holder.aswitch.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                setAlarm(alarma, alarma.getDiasNumeric(), position);
                pintarTxts(holder.diaAlarma);
                pintarTxts(holder.horaAlarma);

            }
            else {
                cancelAlarm(alarma,false, position);
                desPintarTxts(holder.diaAlarma);

                desPintarTxts(holder.horaAlarma);
            }
        });

       // holder.btnEliminar.setOnClickListener((View v) -> {
           // cancelAlarm(alarma, true,position);
      //  });
    }
    private void pintarTxts(TextView t){
        t.setTextColor(mContext.getResources().getColor(R.color.design_default_color_primary_variant));
    }
    private void desPintarTxts(TextView t){
        t.setTextColor(mContext.getResources().getColor(R.color.design_default_color_primary));
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
    //las elimina de la lista
    private void removerAlarmas(Alarma alarma, int position){
        try {
            alarmaList.remove(position);
            tinyDB.putListAlarm("allAlarmas", alarmaList);
            notifyDataSetChanged();
            Toast.makeText(mContext, "Alarma " + alarma.getTitulo() + " eliminada",
                    Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex) {
            Toast.makeText(mContext, "Error eliminando las alarmas", Toast.LENGTH_LONG).show();
        }
    }
    //Cancela las larmas programadas en el AlarmReceiver
    private void cancelAlarm(Alarma alarma, boolean vaAeliminar, int pos) {
        try {
            AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(mContext, AlarmReceiver.class);
            List<Integer> alarmasId = new ArrayList<>(tinyDB.getListInt("alarmasId"));
            for (int id : alarmasId){
                PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, id,
                        intent, 0);
                alarmManager.cancel(pendingIntent);
            }
            //notifyDataSetChanged();
            //false significa q solo va a desactivar, no lo elimina de la lista
            if(vaAeliminar) {
                removerAlarmas(alarma, pos);
                return;
            }
            //si no lo actuliza
            alarma.setActivado(false);
            alarmaList.set(pos, alarma);
            tinyDB.putListAlarm("allAlarmas", alarmaList);
        } catch (Exception e) {
            Toast.makeText(mContext, "Ocurrió un error cancelando las alarmas",
                    Toast.LENGTH_LONG).show();
        }
    }
    private void setAlarm(Alarma alarma, List<Integer> diasRepeticion, int pos){
        Calendar alarmCalendar = Calendar.getInstance();
        List<Integer> alarmaIds = new ArrayList<>();
        try {
            for (int dia : diasRepeticion) {
                int alarmaId = Integer.parseInt(RandomStringUtils.randomNumeric(5));
                alarmaIds.add(alarmaId);
                alarmCalendar.set(Calendar.DAY_OF_WEEK, dia);
                alarmCalendar.set(Calendar.HOUR_OF_DAY, alarma.getHora());
                alarmCalendar.set(Calendar.MINUTE, alarma.getMinuto());
                alarmCalendar.set(Calendar.SECOND, 0);
                alarmCalendar.set(Calendar.MILLISECOND, 0);
                if(alarmCalendar.before(Calendar.getInstance())) {
                    //q suene el día de la otra semana
                    alarmCalendar.add(Calendar.DATE, 7);
                }
                //String leftTime = "AlarmSet  " + alarmCalendar.getTime();
                Intent intent = new Intent(mContext, AlarmReceiver.class);
                intent.putExtra("nombre", alarma.getTitulo());
                intent.putExtra("mensaje", alarma.getNota());
                intent.putExtra("sonido", alarma.getUriTonePath());
                PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, alarmaId,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmCalendar.getTimeInMillis(), pendingIntent);
            }
            alarma.setActivado(true);
            alarmaList.set(pos, alarma);
            tinyDB.putListAlarm("allAlarmas", alarmaList);
            notifyDataSetChanged();
            tinyDB.putListInt("alarmasId", alarmaIds);
        }
        catch (Exception ex){
            Toast.makeText(mContext, "Error re-creando las alarmas " + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

    }
}
