package com.example.macchiato.Models;

import com.example.macchiato.Enums.Dia;

public class Clase {
    private Dia dia;
    private String horaInicio;
    private String horaFinal;
    private String aula;

    public Clase(Dia dia, String horaInicio, String horaFinal, String aula){
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.aula = aula;
    }

    public Dia getDia(){
        return dia;
    }

    public String getHoraInicio(){
        return horaInicio;
    }

    public String getHoraFinal(){
        return horaFinal;
    }

    public String getAula(){
        return aula;
    }
}
