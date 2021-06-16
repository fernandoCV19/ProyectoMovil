package com.example.macchiato.Models;

import com.example.macchiato.Enums.Dia;

/**
 * Clase que modela el formato de una clase
 */
public class Clase {
    private Dia dia;
    private String horaInicio;
    private String horaFinal;
    private String aula;
    private String nomMateria;
    private int hora;
    private int minuto;

    /**
     *
     */
    public Clase(Dia dia, String horaInicio, String horaFinal, String aula) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.aula = aula;
    }


    /**
     * @return
     */
    public String getNomMateria() {
        return nomMateria;
    }

    /**
     * @param nomMateria
     */
    public void setNomMateria(String nomMateria) {
        this.nomMateria = nomMateria;
    }

    /**
     * @return
     */
    public Dia getDia() {
        return dia;
    }

    /**
     * @return
     */
    public String getHoraInicio() {
        return horaInicio;
    }

    /**
     * @return
     */
    public String getHoraFinal() {
        return horaFinal;
    }

    /**
     * @return
     */
    public String getAula() {
        return aula;
    }

}



