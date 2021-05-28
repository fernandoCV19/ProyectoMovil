package com.example.macchiato.Servicios;

import com.example.macchiato.Enums.Dia;
import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.Grupo;
import com.example.macchiato.Models.Materia;
import com.google.android.material.datepicker.MaterialTextInputPicker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.regex.MatchResult;

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
        Materia materia1 = mock(Materia.class);
        Grupo grupo1 = mock(Grupo.class);
        Clase clase1 = mock(Clase.class);
        when(clase1.getDia()).thenReturn(Dia.MARTES);
        when(clase1.getHoraInicio()).thenReturn("8:15");
        Clase clase2 = mock(Clase.class);
        when(clase2.getDia()).thenReturn(Dia.VIERNES);
        when(clase2.getHoraInicio()).thenReturn("8:15");

        Grupo grupo2 = mock(Grupo.class);
        Clase clase3 = mock(Clase.class);
        when(clase3.getDia()).thenReturn(Dia.MARTES);
        when(clase3.getHoraInicio()).thenReturn("11:15");
        Clase clase4 = mock(Clase.class);
        when(clase4.getDia()).thenReturn(Dia.VIERNES);
        when(clase4.getHoraInicio()).thenReturn("9:45");

        Grupo grupo3 = mock(Grupo.class);
        Clase clase5 = mock(Clase.class);
        when(clase5.getDia()).thenReturn(Dia.LUNES);
        when(clase5.getHoraInicio()).thenReturn("6:45");
        Clase clase6 = mock(Clase.class);
        when(clase6.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase6.getHoraInicio()).thenReturn("9:45");

        Grupo grupo4 = mock(Grupo.class);
        Clase clase7 = mock(Clase.class);
        when(clase7.getDia()).thenReturn(Dia.MARTES);
        when(clase7.getHoraInicio()).thenReturn("15:45");
        Clase clase8 = mock(Clase.class);
        when(clase8.getDia()).thenReturn(Dia.VIERNES);
        when(clase8.getHoraInicio()).thenReturn("14:15");

        Grupo grupo5 = mock(Grupo.class);
        Clase clase9 = mock(Clase.class);
        when(clase9.getDia()).thenReturn(Dia.JUEVES);
        when(clase9.getHoraInicio()).thenReturn("9:45");
        Clase clase10 = mock(Clase.class);
        when(clase10.getDia()).thenReturn(Dia.VIERNES);
        when(clase10.getHoraInicio()).thenReturn("11:15");


        Materia materia2 = mock(Materia.class);
        Grupo grupo6 = mock(Grupo.class);
        Clase clase11 = mock(Clase.class);
        when(clase11.getDia()).thenReturn(Dia.MARTES);
        when(clase11.getHoraInicio()).thenReturn("11:15");
        Clase clase12 = mock(Clase.class);
        when(clase12.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase12.getHoraInicio()).thenReturn("14:15");


        Materia materia3 = mock(Materia.class);
        Grupo grupo7 = mock(Grupo.class);
        Clase clase13 = mock(Clase.class);
        when(clase13.getDia()).thenReturn(Dia.MARTES);
        when(clase13.getHoraInicio()).thenReturn("9:45");
        Clase clase14 = mock(Clase.class);
        when(clase14.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase14.getHoraInicio()).thenReturn("8:15");
        Clase clase15 = mock(Clase.class);
        when(clase15.getDia()).thenReturn(Dia.VIERNES);
        when(clase15.getHoraInicio()).thenReturn("9:45");

        Grupo grupo8 = mock(Grupo.class);
        Clase clase16 = mock(Clase.class);
        when(clase16.getDia()).thenReturn(Dia.LUNES);
        when(clase16.getHoraInicio()).thenReturn("17:15");
        Clase clase17 = mock(Clase.class);
        when(clase17.getDia()).thenReturn(Dia.MARTES);
        when(clase17.getHoraInicio()).thenReturn("15:45");
        Clase clase18 = mock(Clase.class);
        when(clase18.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase18.getHoraInicio()).thenReturn("14:15");

        Grupo grupo9 = mock(Grupo.class);
        Clase clase19 = mock(Clase.class);
        when(clase19.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase19.getHoraInicio()).thenReturn("11:15");
        Clase clase20 = mock(Clase.class);
        when(clase20.getDia()).thenReturn(Dia.JUEVES);
        when(clase20.getHoraInicio()).thenReturn("6:45");
        Clase clase21 = mock(Clase.class);
        when(clase21.getDia()).thenReturn(Dia.VIERNES);
        when(clase21.getHoraInicio()).thenReturn("14:15");


        Materia materia4 = mock(Materia.class);
        Grupo grupo10 = mock(Grupo.class);
        Clase clase22 = mock(Clase.class);
        when(clase22.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase22.getHoraInicio()).thenReturn("6:45");
        Clase clase23 = mock(Clase.class);
        when(clase23.getDia()).thenReturn(Dia.VIERNES);
        when(clase23.getHoraInicio()).thenReturn("6:45");
        Clase clase24 = mock(Clase.class);
        when(clase24.getDia()).thenReturn(Dia.SABADO);
        when(clase24.getHoraInicio()).thenReturn("9:45");

        Grupo grupo11 = mock(Grupo.class);
        Clase clase25 = mock(Clase.class);
        when(clase25.getDia()).thenReturn(Dia.LUNES);
        when(clase25.getHoraInicio()).thenReturn("6:45");
        Clase clase26 = mock(Clase.class);
        when(clase26.getDia()).thenReturn(Dia.MARTES);
        when(clase26.getHoraInicio()).thenReturn("8:15");
        Clase clase27 = mock(Clase.class);
        when(clase27.getDia()).thenReturn(Dia.JUEVES);
        when(clase27.getHoraInicio()).thenReturn("8:15");

        Materia materia5 = mock(Materia.class);
        Grupo grupo12 = mock(Grupo.class);
        Clase clase28 = mock(Clase.class);
        when(clase28.getDia()).thenReturn(Dia.JUEVES);
        when(clase28.getHoraInicio()).thenReturn("11:15");
        Clase clase29 = mock(Clase.class);
        when(clase29.getDia()).thenReturn(Dia.VIERNES);
        when(clase29.getHoraInicio()).thenReturn("11:15");
        Clase clase30 = mock(Clase.class);
        when(clase30.getDia()).thenReturn(Dia.VIERNES);
        when(clase30.getHoraInicio()).thenReturn("17:15");

        Grupo grupo13 = mock(Grupo.class);
        Clase clase31 = mock(Clase.class);
        when(clase31.getDia()).thenReturn(Dia.MARTES);
        when(clase31.getHoraInicio()).thenReturn("9:45");
        Clase clase32 = mock(Clase.class);
        when(clase32.getDia()).thenReturn(Dia.JUEVES);
        when(clase32.getHoraInicio()).thenReturn("9:45");
        Clase clase33 = mock(Clase.class);
        when(clase33.getDia()).thenReturn(Dia.JUEVES);
        when(clase33.getHoraInicio()).thenReturn("14:15");

        Grupo grupo14= mock(Grupo.class);
        Clase clase34 = mock(Clase.class);
        when(clase34.getDia()).thenReturn(Dia.MARTES);
        when(clase34.getHoraInicio()).thenReturn("17:15");
        Clase clase35 = mock(Clase.class);
        when(clase35.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase35.getHoraInicio()).thenReturn("17:15");
        Clase clase36 = mock(Clase.class);
        when(clase36.getDia()).thenReturn(Dia.JUEVES);
        when(clase36.getHoraInicio()).thenReturn("15:45");

        Grupo grupo15 = mock(Grupo.class);
        Clase clase37 = mock(Clase.class);
        when(clase37.getDia()).thenReturn(Dia.LUNES);
        when(clase37.getHoraInicio()).thenReturn("12:45");
        Clase clase38 = mock(Clase.class);
        when(clase38.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase38.getHoraInicio()).thenReturn("12:45");
        Clase clase39 = mock(Clase.class);
        when(clase39.getDia()).thenReturn(Dia.VIERNES);
        when(clase39.getHoraInicio()).thenReturn("12:45");

        Grupo grupo16 = mock(Grupo.class);
        Clase clase40 = mock(Clase.class);
        when(clase40.getDia()).thenReturn(Dia.LUNES);
        when(clase40.getHoraInicio()).thenReturn("14:15");
        Clase clase41 = mock(Clase.class);
        when(clase41.getDia()).thenReturn(Dia.MARTES);
        when(clase41.getHoraInicio()).thenReturn("12:45");
        Clase clase42 = mock(Clase.class);
        when(clase42.getDia()).thenReturn(Dia.SABADO);
        when(clase42.getHoraInicio()).thenReturn("9:45");

        Grupo grupo17 = mock(Grupo.class);
        Clase clase43 = mock(Clase.class);
        when(clase43.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase43.getHoraInicio()).thenReturn("9:45");
        Clase clase44 = mock(Clase.class);
        when(clase44.getDia()).thenReturn(Dia.JUEVES);
        when(clase44.getHoraInicio()).thenReturn("9:45");
        Clase clase45 = mock(Clase.class);
        when(clase45.getDia()).thenReturn(Dia.SABADO);
        when(clase45.getHoraInicio()).thenReturn("9:45");

        Grupo grupo18 = mock(Grupo.class);
        Clase clase46 = mock(Clase.class);
        when(clase46.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase46.getHoraInicio()).thenReturn("17:15");
        Clase clase47 = mock(Clase.class);
        when(clase47.getDia()).thenReturn(Dia.JUEVES);
        when(clase47.getHoraInicio()).thenReturn("17:15");
        Clase clase48 = mock(Clase.class);
        when(clase48.getDia()).thenReturn(Dia.SABADO);
        when(clase48.getHoraInicio()).thenReturn("8:15");

        Grupo grupo19= mock(Grupo.class);
        Clase clase49 = mock(Clase.class);
        when(clase49.getDia()).thenReturn(Dia.MARTES);
        when(clase49.getHoraInicio()).thenReturn("12:45");
        Clase clase50 = mock(Clase.class);
        when(clase50.getDia()).thenReturn(Dia.JUEVES);
        when(clase50.getHoraInicio()).thenReturn("8:15");
        Clase clase51 = mock(Clase.class);
        when(clase51.getDia()).thenReturn(Dia.VIERNES);
        when(clase51.getHoraInicio()).thenReturn("8:15");

        ArrayList <Materia> materias = new ArrayList<>();
        materias.add(materia1);
        materias.add(materia2);
        materias.add(materia3);
        materias.add(materia4);
        materias.add(materia5);

        ArrayList<Grupo> res = creadorHorarios.crearHorario(materias);
        assertThat(res).hasSize(5);
    }
}