package com.example.macchiato.Servicios.Alarma;

import androidx.annotation.Nullable;
import com.example.macchiato.Models.Clase;

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

   // public Alarma(String titulo, int hora, int minuto, String tono, List<String> dias, String nota, boolean activado, String tonoPath) {
    public Alarma(Clase clase, String tono, String nota, boolean activado, String tonoPath){
        alarmaId = RandomStringUtils.randomNumeric(5);
        this.titulo = clase.getNomMateria();

        String horaInicio = clase.getHoraInicio().replaceAll(" ","");
        String[] hora_minuto = horaInicio.split(":");

        this.hora = Integer.parseInt(hora_minuto[0]);;
        this.minuto = Integer.parseInt(hora_minuto[1]);
        this.tono = tono;

        this.dias = new ArrayList<>();
        dias.add(clase.getDia().toString());

        this.nota = nota;
        this.activado = activado;
        this.uriTonePath = tonoPath;
        setDiasNumeric();
    }

    public void setAlarmaId(String alarmaId) {
        this.alarmaId = alarmaId;
    }

    public List<Integer> getDiasNumeric() {
        return diasNumeric;
    }

    private void setDiasNumeric() {
        diasNumeric = new ArrayList<>();
        for(String d: dias)
            diasNumeric.add(getDiaNumeric(d));
    }

    @Override
    public boolean equals(@Nullable @org.jetbrains.annotations.Nullable Object obj) {
        Alarma a = (Alarma)obj;
        if(a.getTitulo().contains(titulo))
            if (a.getHora() == hora && a.getMinuto() == minuto)
                if(a.getDias().get(0).contains(dias.get(0)))
                    return true;
                else return false;
            else return false;
        else return false;
    }

    private int getDiaNumeric(String dia){
        if(dia.contains("LUNES")) return 2;
        else if(dia.contains("MARTES")) return 3;
        else if(dia.contains("MIERCOLES")) return 4;
        else if(dia.contains("JUEVES")) return 5;
        else return 6;
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
