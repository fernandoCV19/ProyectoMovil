package com.example.macchiato.Servicios.Alarma;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreadorAlarma {
    public CreadorAlarma(){}

    List<Integer> diasNumericList;
    private String alarmName="", tonoName="";
    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";
    public final Calendar c = Calendar.getInstance();
    TinyDB tinyDB;
    Boolean isUpdate;


    public void crearAlarma(Alarma alarma, Context context, TinyDB tinyDB){
        //TODO : hacer validaciones | calcular tiempo faltante para la alarma
            this.tinyDB = tinyDB;
            //si no puede crear el servicio alarma entonces no lo guarde
            try {
                setAlarm(alarma, context);

                List<Alarma> alarmaList = new ArrayList<>(tinyDB.getListAlarm("allAlarmas", Alarma.class));
                alarmaList.add(alarma);
                tinyDB.putListAlarm("allAlarmas", alarmaList);
                Toast.makeText(context, "Alarma Creada", Toast.LENGTH_LONG).show();
            }
            catch (Exception ex){
                Toast.makeText(context, "Error creando la alarma" + ex.getMessage(),
                        Toast.LENGTH_LONG).show();
                return;
            }
    }
    private void setAlarm(Alarma alarma, Context context){

        List<Integer> alarmaIds = new ArrayList<>();
        for (int dia : alarma.getDiasNumeric()) {
            Calendar alarmCalendar = Calendar.getInstance();
            int alarmaId = Integer.parseInt(RandomStringUtils.randomNumeric(5));
            alarmaIds.add(alarmaId);
            alarmCalendar.set(Calendar.DAY_OF_WEEK, dia);
            alarmCalendar.set(Calendar.HOUR_OF_DAY, alarma.getHora());
            alarmCalendar.set(Calendar.MINUTE, alarma.getMinuto());
            alarmCalendar.set(Calendar.SECOND, 0);
            alarmCalendar.set(Calendar.MILLISECOND, 0);
            if(alarmCalendar.before(Calendar.getInstance())) {
                alarmCalendar.add(Calendar.DATE, 7);
            }
            //String leftTime = "AlarmSet  " + alarmCalendar.getTime();
            Intent intent = new Intent(context, AlarmReceiver.class);
            intent.putExtra("nombre", alarma.getTitulo());
            intent.putExtra("mensaje", alarma.getNota());

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarmaId,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmCalendar.getTimeInMillis(), pendingIntent);
        }
        tinyDB.putListInt("alarmasId", alarmaIds);
    }

}
/*
public void crear(Context context, int idGrupo, int clase, boolean crear){
        if(clase <= 3) {
            ConsultorMaterias consultorMaterias = new ConsultorMaterias();
            ArrayList<Integer> id = new ArrayList<>();
            id.add(idGrupo);

            ConsultorMaterias.Par par = consultorMaterias.devolverGrupos(id).get(0);
            Grupo g = par.getGrupo();

            Clase c = g.getClases().get(clase);

            String aula = c.getAula() + " - " + par.getMateria();
            String[] hora_minuto = c.getHoraInicio().split(":");
            int hora = Integer.parseInt(hora_minuto[0]);
            int minuto = Integer.parseInt(hora_minuto[1]);

            Alarma alarma = new Alarma(aula, hora, minuto, c.getDia().toString());
            if (crear) alarma.establerAlarma(context);
            else alarma.cancelarAlarma(context);
        }
    }
 */