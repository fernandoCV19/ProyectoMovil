package com.example.macchiato.Servicios;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.AlarmClock;

import androidx.annotation.RequiresApi;

import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.Grupo;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class Alarma {

    private String alarmaId;
    private String titulo;
    private int hora;
    private int minuto;
    private String tono;
    private List<String> dias;
    private List<Integer> diasNumeric;
    private String nota;
    private String uriTonePath;
    private boolean activado;

    public String getAlarmaId() {
        return alarmaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Alarma() {
    }

    public Alarma(String titulo, int hora, int minuto, String tono, List<String> dias, String nota, boolean activado, String tonoPath) {
        alarmaId = RandomStringUtils.randomNumeric(5);
        this.titulo = titulo;
        this.hora = hora;
        this.minuto = minuto;
        this.tono = tono;
        this.dias = dias;
        this.nota = nota;
        this.activado = activado;
        this.uriTonePath = tonoPath;
    }

    public void setAlarmaId(String alarmaId) {
        this.alarmaId = alarmaId;
    }

    public List<Integer> getDiasNumeric() {
        return diasNumeric;
    }

    public void setDiasNumeric(List<Integer> diasNumeric) {
        this.diasNumeric = diasNumeric;
    }

    public String getUriTonePath() {
        return uriTonePath;
    }

    public void setUriTonePath(String uriTonePath) {
        this.uriTonePath = uriTonePath;
    }

    public boolean isActivado() {
        return activado;
    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public String getTono() {
        return tono;
    }

    public void setTono(String tono) {
        this.tono = tono;
    }

    public List<String> getDias() {
        return dias;
    }

    public void setDias(List<String> dias) {
        this.dias = dias;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
/*{

    private String mensaje;
    private int hora;
    private int minutos;
    private ArrayList<Integer> dias;

    public Alarma(String mensaje, int hora, int minutos, String dia){
        this.mensaje = mensaje;
        this.hora = hora;
        this.minutos = minutos;
        dias = new ArrayList<Integer>();
        dias.add(getDia(dia));
    }
    public void establerAlarma(Context context) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, mensaje)
                .putExtra(AlarmClock.EXTRA_HOUR, hora)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutos)
                .putExtra(AlarmClock.EXTRA_DAYS, dias)
                .putExtra(AlarmClock.EXTRA_SKIP_UI,true);
        context.startActivity(intent);
    }
    public void cancelarAlarma(Context context){
        Intent alarmIntent = new Intent(AlarmClock.ACTION_DISMISS_ALARM);
        alarmIntent.putExtra(AlarmClock.ALARM_SEARCH_MODE_LABEL,mensaje)
                   .putExtra(AlarmClock.EXTRA_SKIP_UI,true);
        context.startActivity(alarmIntent);
    }
    private int getDia(String dia){
        if(dia.contains("LUNES")) return 2;
        else if(dia.contains("MARTES")) return 3;
        else if(dia.contains("MIERCOLES")) return 4;
        else if(dia.contains("JUEVES")) return 5;
        else return 6;
    }
}
*/