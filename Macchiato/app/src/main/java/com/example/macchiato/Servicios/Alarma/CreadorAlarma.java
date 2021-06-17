package com.example.macchiato.Servicios.Alarma;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.lang3.RandomStringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/**
 * Clase destinada especificamente para la creacion de notificaciones
 * */
public class CreadorAlarma  {
    public CreadorAlarma(){}

    public static void setAlarm(int i, Long timestamp, Context ctx, String nombre, int hora, int minuto, int dia, boolean activado){

        AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(ctx, AlarmReceiver.class);
        alarmIntent.putExtra("nombre", nombre);
        alarmIntent.putExtra("hora", hora);
        alarmIntent.putExtra("minuto", minuto);
        alarmIntent.putExtra("dia", dia);
        alarmIntent.putExtra("id", i);
        alarmIntent.putExtra("activado", activado);

        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getBroadcast(ctx, i, alarmIntent, PendingIntent.FLAG_ONE_SHOT);
        alarmIntent.setData((Uri.parse("custom://" + System.currentTimeMillis())));
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timestamp, pendingIntent);
    }
    public static void setAllAlarms(Context context){
        TinyDB tinyDB = new TinyDB(context);
        List<Alarma> alarmasList = new ArrayList<>(tinyDB.getListAlarm("allAlarmas",Alarma.class));
        for(Alarma a : alarmasList) {
            a.setActivado(true);
            Calendar alarmCalendar = Calendar.getInstance();
            alarmCalendar.set(Calendar.DAY_OF_WEEK, a.getDiasNumeric().get(0));
            alarmCalendar.set(Calendar.HOUR_OF_DAY, a.getHora());
            alarmCalendar.set(Calendar.MINUTE, a.getMinuto());

            if (alarmCalendar.before(Calendar.getInstance())) {
                alarmCalendar.add(Calendar.DATE, 7);
            }
            int alarmaId = Integer.parseInt(a.getAlarmaId());
            setAlarm(alarmaId, alarmCalendar.getTimeInMillis(), context, a.getTitulo(),
                     a.getHora(), a.getMinuto(), a.getDiasNumeric().get(0), a.isActivado());
        }
    }
    public static boolean cancelAllAlarm(Context context){
        try {
            TinyDB tinyDB = new TinyDB(context);
            ArrayList<Alarma> alarmas = new ArrayList<>(tinyDB.getListAlarm("allAlarmas", Alarma.class));
            for (Alarma a : alarmas){
                cancelOneAlarm(context, Integer.parseInt(a.getAlarmaId()));
                a.setActivado(false);
            }
            tinyDB.putListAlarm("allAlarmas", alarmas);
            return true;
        }catch (Exception e){
            return false;
        }

    }
    public static boolean cancelOneAlarm(Context context, int id){
        try{
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(context, AlarmReceiver.class);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id,
                    intent, 0);
            alarmManager.cancel(pendingIntent);
        return true;
        }catch (Exception e){
            return false;
        }
    }
}
    /*
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


            }
            catch (Exception ex){
                Toast.makeText(context, "Error creando la alarma" + ex.getMessage(),
                        Toast.LENGTH_LONG).show();
                return;
            }
        List<Alarma> alarmaList = new ArrayList<>(tinyDB.getListAlarm("allAlarmas", Alarma.class));
        alarmaList.add(alarma);
        tinyDB.putListAlarm("allAlarmas", alarmaList);
        Toast.makeText(context, "Alarma Creada", Toast.LENGTH_LONG).show();
        finish();
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
            intent.putExtra("sonido", "/external_primary/audio/media/33");
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