package com.example.macchiato.Servicios;

import com.example.macchiato.Enums.Dia;
import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.Grupo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.*;

public class CreadorHorariosTest {

    private CreadorHorarios creadorHorarios;

    @Before
    public void setup(){
        creadorHorarios = new CreadorHorarios();
    }

    @Test
    public void dadoDosGruposConLasMismasHorasPeroDiferentesDiasCuandoLlamamosHayChoqueEntoncesDevuelveFalse(){
        Clase clase1 = mock(Clase.class);
        Clase clase2 = mock(Clase.class);
        Clase clase3 = mock(Clase.class);
        Clase clase4 = mock(Clase.class);

        when(clase1.getDia()).thenReturn(Dia.LUNES);
        when(clase2.getDia()).thenReturn(Dia.MARTES);
        when(clase3.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase4.getDia()).thenReturn(Dia.JUEVES);

        when(clase1.getHoraInicio()).thenReturn("9:45");
        when(clase2.getHoraInicio()).thenReturn("9:45");
        when(clase3.getHoraInicio()).thenReturn("9:45");
        when(clase4.getHoraInicio()).thenReturn("9:45");

        Grupo grupo1 = mock(Grupo.class);
        Grupo grupo2 = mock(Grupo.class);

        ArrayList<Clase> l = new ArrayList<>();
        l.add(clase1);
        l.add(clase2);

        when(grupo1.getClases()).thenReturn(l);

        ArrayList<Clase> l2 = new ArrayList<>();
        l2.add(clase3);
        l2.add(clase4);

        when(grupo2.getClases()).thenReturn(l2);

        assertThat(creadorHorarios.hayChoque(grupo1,grupo2)).isFalse();
    }

    @Test
    public void dadoDosGruposConLasMismasHorasYDiasIgualesCuandoLlamamosHayChoqueEntoncesDevuelveTrue(){
        Clase clase1 = mock(Clase.class);
        Clase clase2 = mock(Clase.class);
        Clase clase3 = mock(Clase.class);
        Clase clase4 = mock(Clase.class);

        when(clase1.getDia()).thenReturn(Dia.LUNES);
        when(clase2.getDia()).thenReturn(Dia.MARTES);
        when(clase3.getDia()).thenReturn(Dia.LUNES);
        when(clase4.getDia()).thenReturn(Dia.MARTES);

        when(clase1.getHoraInicio()).thenReturn("9:45");
        when(clase2.getHoraInicio()).thenReturn("9:45");
        when(clase3.getHoraInicio()).thenReturn("9:45");
        when(clase4.getHoraInicio()).thenReturn("9:45");

        Grupo grupo1 = mock(Grupo.class);
        Grupo grupo2 = mock(Grupo.class);

        ArrayList<Clase> l = new ArrayList<>();
        l.add(clase1);
        l.add(clase2);

        when(grupo1.getClases()).thenReturn(l);

        ArrayList<Clase> l2 = new ArrayList<>();
        l2.add(clase3);
        l2.add(clase4);

        when(grupo2.getClases()).thenReturn(l2);

        assertThat(creadorHorarios.hayChoque(grupo1,grupo2)).isTrue();
    }

    @Test
    public void dadosDosGruposConHorasYDiasDiferentesCuandoLlamamosHayChoqueEntoncesDevuelveFalse(){
        Clase clase1 = mock(Clase.class);
        Clase clase2 = mock(Clase.class);
        Clase clase3 = mock(Clase.class);
        Clase clase4 = mock(Clase.class);

        when(clase1.getDia()).thenReturn(Dia.LUNES);
        when(clase2.getDia()).thenReturn(Dia.MARTES);
        when(clase3.getDia()).thenReturn(Dia.LUNES);
        when(clase4.getDia()).thenReturn(Dia.MARTES);

        when(clase1.getHoraInicio()).thenReturn("9:45");
        when(clase2.getHoraInicio()).thenReturn("11:15");
        when(clase3.getHoraInicio()).thenReturn("12:45");
        when(clase4.getHoraInicio()).thenReturn("14:15");

        Grupo grupo1 = mock(Grupo.class);
        Grupo grupo2 = mock(Grupo.class);

        ArrayList<Clase> l = new ArrayList<>();
        l.add(clase1);
        l.add(clase2);

        when(grupo1.getClases()).thenReturn(l);

        ArrayList<Clase> l2 = new ArrayList<>();
        l2.add(clase3);
        l2.add(clase4);

        when(grupo2.getClases()).thenReturn(l2);

        assertThat(creadorHorarios.hayChoque(grupo1,grupo2)).isFalse();
    }

    @Test
    public void dadasMateriasSinChoquesCuandoLlamamosCrearHorarioEntoncesDevuelveUnHorarioConTodasLasMaterias(){

    }
}