package com.example.macchiato.Servicios;

import com.example.macchiato.Enums.Dia;
import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.Grupo;
import com.example.macchiato.Models.Materia;
import org.junit.Before;
import org.junit.Test;

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
    public void dadas5MateriasSinChoquesCuandoLlamamosCrearHorarioEntoncesDevuelveUnHorarioConUnGrupoDeCadaMateria(){
        Materia materia1 = mock(Materia.class);
        Grupo grupo1 = mock(Grupo.class);
        when(grupo1.getDocente()).thenReturn("Materia1");
        Clase clase1 = mock(Clase.class);
        when(clase1.getDia()).thenReturn(Dia.MARTES);
        when(clase1.getHoraInicio()).thenReturn("8:15");
        Clase clase2 = mock(Clase.class);
        when(clase2.getDia()).thenReturn(Dia.VIERNES);
        when(clase2.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo1 = new ArrayList<>();
        clasesGrupo1.add(clase1);
        clasesGrupo1.add(clase2);
        when(grupo1.getClases()).thenReturn(clasesGrupo1);

        Grupo grupo2 = mock(Grupo.class);
        when(grupo2.getDocente()).thenReturn("Materia1");
        Clase clase3 = mock(Clase.class);
        when(clase3.getDia()).thenReturn(Dia.MARTES);
        when(clase3.getHoraInicio()).thenReturn("11:15");
        Clase clase4 = mock(Clase.class);
        when(clase4.getDia()).thenReturn(Dia.VIERNES);
        when(clase4.getHoraInicio()).thenReturn("9:45");

        ArrayList<Clase> clasesGrupo2 = new ArrayList<>();
        clasesGrupo2.add(clase3);
        clasesGrupo2.add(clase4);
        when(grupo2.getClases()).thenReturn(clasesGrupo2);

        Grupo grupo3 = mock(Grupo.class);
        when(grupo3.getDocente()).thenReturn("Materia1");
        Clase clase5 = mock(Clase.class);
        when(clase5.getDia()).thenReturn(Dia.LUNES);
        when(clase5.getHoraInicio()).thenReturn("6:45");
        Clase clase6 = mock(Clase.class);
        when(clase6.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase6.getHoraInicio()).thenReturn("9:45");

        ArrayList<Clase> clasesGrupo3 = new ArrayList<>();
        clasesGrupo3.add(clase5);
        clasesGrupo3.add(clase6);
        when(grupo3.getClases()).thenReturn(clasesGrupo3);

        Grupo grupo4 = mock(Grupo.class);
        when(grupo4.getDocente()).thenReturn("Materia1");
        Clase clase7 = mock(Clase.class);
        when(clase7.getDia()).thenReturn(Dia.MARTES);
        when(clase7.getHoraInicio()).thenReturn("15:45");
        Clase clase8 = mock(Clase.class);
        when(clase8.getDia()).thenReturn(Dia.VIERNES);
        when(clase8.getHoraInicio()).thenReturn("14:15");

        ArrayList<Clase> clasesGrupo4 = new ArrayList<>();
        clasesGrupo4.add(clase7);
        clasesGrupo4.add(clase8);
        when(grupo4.getClases()).thenReturn(clasesGrupo4);

        Grupo grupo5 = mock(Grupo.class);
        when(grupo5.getDocente()).thenReturn("Materia1");
        Clase clase9 = mock(Clase.class);
        when(clase9.getDia()).thenReturn(Dia.JUEVES);
        when(clase9.getHoraInicio()).thenReturn("9:45");
        Clase clase10 = mock(Clase.class);
        when(clase10.getDia()).thenReturn(Dia.VIERNES);
        when(clase10.getHoraInicio()).thenReturn("11:15");

        ArrayList<Clase> clasesGrupo5 = new ArrayList<>();
        clasesGrupo5.add(clase9);
        clasesGrupo5.add(clase10);
        when(grupo5.getClases()).thenReturn(clasesGrupo5);

        ArrayList<Grupo> gruposMateria1 = new ArrayList<>();
        gruposMateria1.add(grupo1);
        gruposMateria1.add(grupo2);
        gruposMateria1.add(grupo3);
        gruposMateria1.add(grupo4);
        gruposMateria1.add(grupo5);
        when(materia1.getGrupos()).thenReturn(gruposMateria1);

        Materia materia2 = mock(Materia.class);
        Grupo grupo6 = mock(Grupo.class);
        when(grupo6.getDocente()).thenReturn("Materia2");
        Clase clase11 = mock(Clase.class);
        when(clase11.getDia()).thenReturn(Dia.MARTES);
        when(clase11.getHoraInicio()).thenReturn("11:15");
        Clase clase12 = mock(Clase.class);
        when(clase12.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase12.getHoraInicio()).thenReturn("14:15");

        ArrayList<Clase> clasesGrupo6 = new ArrayList<>();
        clasesGrupo6.add(clase11);
        clasesGrupo6.add(clase12);
        when(grupo6.getClases()).thenReturn(clasesGrupo6);

        ArrayList<Grupo> gruposMateria2 = new ArrayList<>();
        gruposMateria2.add(grupo6);
        when(materia2.getGrupos()).thenReturn(gruposMateria2);

        Materia materia3 = mock(Materia.class);
        Grupo grupo7 = mock(Grupo.class);
        when(grupo7.getDocente()).thenReturn("Materia3");
        Clase clase13 = mock(Clase.class);
        when(clase13.getDia()).thenReturn(Dia.MARTES);
        when(clase13.getHoraInicio()).thenReturn("9:45");
        Clase clase14 = mock(Clase.class);
        when(clase14.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase14.getHoraInicio()).thenReturn("8:15");
        Clase clase15 = mock(Clase.class);
        when(clase15.getDia()).thenReturn(Dia.VIERNES);
        when(clase15.getHoraInicio()).thenReturn("9:45");

        ArrayList<Clase> clasesGrupo7 = new ArrayList<>();
        clasesGrupo7.add(clase13);
        clasesGrupo7.add(clase14);
        clasesGrupo7.add(clase15);
        when(grupo7.getClases()).thenReturn(clasesGrupo7);

        Grupo grupo8 = mock(Grupo.class);
        when(grupo8.getDocente()).thenReturn("Materia3");
        Clase clase16 = mock(Clase.class);
        when(clase16.getDia()).thenReturn(Dia.LUNES);
        when(clase16.getHoraInicio()).thenReturn("17:15");
        Clase clase17 = mock(Clase.class);
        when(clase17.getDia()).thenReturn(Dia.MARTES);
        when(clase17.getHoraInicio()).thenReturn("15:45");
        Clase clase18 = mock(Clase.class);
        when(clase18.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase18.getHoraInicio()).thenReturn("14:15");

        ArrayList<Clase> clasesGrupo8 = new ArrayList<>();
        clasesGrupo8.add(clase16);
        clasesGrupo8.add(clase17);
        clasesGrupo8.add(clase18);
        when(grupo8.getClases()).thenReturn(clasesGrupo8);

        Grupo grupo9 = mock(Grupo.class);
        when(grupo9.getDocente()).thenReturn("Materia3");
        Clase clase19 = mock(Clase.class);
        when(clase19.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase19.getHoraInicio()).thenReturn("11:15");
        Clase clase20 = mock(Clase.class);
        when(clase20.getDia()).thenReturn(Dia.JUEVES);
        when(clase20.getHoraInicio()).thenReturn("6:45");
        Clase clase21 = mock(Clase.class);
        when(clase21.getDia()).thenReturn(Dia.VIERNES);
        when(clase21.getHoraInicio()).thenReturn("14:15");

        ArrayList<Clase> clasesGrupo9 = new ArrayList<>();
        clasesGrupo9.add(clase19);
        clasesGrupo9.add(clase20);
        clasesGrupo9.add(clase21);
        when(grupo9.getClases()).thenReturn(clasesGrupo9);

        ArrayList<Grupo> gruposMateria3 = new ArrayList<>();
        gruposMateria3.add(grupo7);
        gruposMateria3.add(grupo8);
        gruposMateria3.add(grupo9);
        when(materia3.getGrupos()).thenReturn(gruposMateria3);

        Materia materia4 = mock(Materia.class);
        Grupo grupo10 = mock(Grupo.class);
        when(grupo10.getDocente()).thenReturn("Materia4");
        Clase clase22 = mock(Clase.class);
        when(clase22.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase22.getHoraInicio()).thenReturn("6:45");
        Clase clase23 = mock(Clase.class);
        when(clase23.getDia()).thenReturn(Dia.VIERNES);
        when(clase23.getHoraInicio()).thenReturn("6:45");
        Clase clase24 = mock(Clase.class);
        when(clase24.getDia()).thenReturn(Dia.SABADO);
        when(clase24.getHoraInicio()).thenReturn("9:45");

        ArrayList<Clase> clasesGrupo10 = new ArrayList<>();
        clasesGrupo10.add(clase22);
        clasesGrupo10.add(clase23);
        clasesGrupo10.add(clase24);
        when(grupo10.getClases()).thenReturn(clasesGrupo10);

        Grupo grupo11 = mock(Grupo.class);
        when(grupo11.getDocente()).thenReturn("Materia4");
        Clase clase25 = mock(Clase.class);
        when(clase25.getDia()).thenReturn(Dia.LUNES);
        when(clase25.getHoraInicio()).thenReturn("6:45");
        Clase clase26 = mock(Clase.class);
        when(clase26.getDia()).thenReturn(Dia.MARTES);
        when(clase26.getHoraInicio()).thenReturn("8:15");
        Clase clase27 = mock(Clase.class);
        when(clase27.getDia()).thenReturn(Dia.JUEVES);
        when(clase27.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo11 = new ArrayList<>();
        clasesGrupo11.add(clase25);
        clasesGrupo11.add(clase26);
        clasesGrupo11.add(clase27);
        when(grupo11.getClases()).thenReturn(clasesGrupo11);

        ArrayList<Grupo> gruposMateria4 = new ArrayList<>();
        gruposMateria4.add(grupo10);
        gruposMateria4.add(grupo11);
        when(materia4.getGrupos()).thenReturn(gruposMateria4);

        Materia materia5 = mock(Materia.class);
        Grupo grupo12 = mock(Grupo.class);
        when(grupo12.getDocente()).thenReturn("Materia5");
        Clase clase28 = mock(Clase.class);
        when(clase28.getDia()).thenReturn(Dia.JUEVES);
        when(clase28.getHoraInicio()).thenReturn("11:15");
        Clase clase29 = mock(Clase.class);
        when(clase29.getDia()).thenReturn(Dia.VIERNES);
        when(clase29.getHoraInicio()).thenReturn("11:15");
        Clase clase30 = mock(Clase.class);
        when(clase30.getDia()).thenReturn(Dia.VIERNES);
        when(clase30.getHoraInicio()).thenReturn("17:15");

        ArrayList<Clase> clasesGrupo12 = new ArrayList<>();
        clasesGrupo12.add(clase28);
        clasesGrupo12.add(clase29);
        clasesGrupo12.add(clase30);
        when(grupo12.getClases()).thenReturn(clasesGrupo12);

        Grupo grupo13 = mock(Grupo.class);
        when(grupo13.getDocente()).thenReturn("Materia5");
        Clase clase31 = mock(Clase.class);
        when(clase31.getDia()).thenReturn(Dia.MARTES);
        when(clase31.getHoraInicio()).thenReturn("9:45");
        Clase clase32 = mock(Clase.class);
        when(clase32.getDia()).thenReturn(Dia.JUEVES);
        when(clase32.getHoraInicio()).thenReturn("9:45");
        Clase clase33 = mock(Clase.class);
        when(clase33.getDia()).thenReturn(Dia.JUEVES);
        when(clase33.getHoraInicio()).thenReturn("14:15");

        ArrayList<Clase> clasesGrupo13 = new ArrayList<>();
        clasesGrupo13.add(clase31);
        clasesGrupo13.add(clase32);
        clasesGrupo13.add(clase33);
        when(grupo13.getClases()).thenReturn(clasesGrupo13);

        Grupo grupo14= mock(Grupo.class);
        when(grupo14.getDocente()).thenReturn("Materia5");
        Clase clase34 = mock(Clase.class);
        when(clase34.getDia()).thenReturn(Dia.MARTES);
        when(clase34.getHoraInicio()).thenReturn("17:15");
        Clase clase35 = mock(Clase.class);
        when(clase35.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase35.getHoraInicio()).thenReturn("17:15");
        Clase clase36 = mock(Clase.class);
        when(clase36.getDia()).thenReturn(Dia.JUEVES);
        when(clase36.getHoraInicio()).thenReturn("15:45");

        ArrayList<Clase> clasesGrupo14 = new ArrayList<>();
        clasesGrupo14.add(clase34);
        clasesGrupo14.add(clase35);
        clasesGrupo14.add(clase36);
        when(grupo14.getClases()).thenReturn(clasesGrupo14);

        Grupo grupo15 = mock(Grupo.class);
        when(grupo15.getDocente()).thenReturn("Materia5");
        Clase clase37 = mock(Clase.class);
        when(clase37.getDia()).thenReturn(Dia.LUNES);
        when(clase37.getHoraInicio()).thenReturn("12:45");
        Clase clase38 = mock(Clase.class);
        when(clase38.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase38.getHoraInicio()).thenReturn("12:45");
        Clase clase39 = mock(Clase.class);
        when(clase39.getDia()).thenReturn(Dia.VIERNES);
        when(clase39.getHoraInicio()).thenReturn("12:45");

        ArrayList<Clase> clasesGrupo15 = new ArrayList<>();
        clasesGrupo15.add(clase37);
        clasesGrupo15.add(clase38);
        clasesGrupo15.add(clase39);
        when(grupo15.getClases()).thenReturn(clasesGrupo15);

        Grupo grupo16 = mock(Grupo.class);
        when(grupo16.getDocente()).thenReturn("Materia5");
        Clase clase40 = mock(Clase.class);
        when(clase40.getDia()).thenReturn(Dia.LUNES);
        when(clase40.getHoraInicio()).thenReturn("14:15");
        Clase clase41 = mock(Clase.class);
        when(clase41.getDia()).thenReturn(Dia.MARTES);
        when(clase41.getHoraInicio()).thenReturn("12:45");
        Clase clase42 = mock(Clase.class);
        when(clase42.getDia()).thenReturn(Dia.SABADO);
        when(clase42.getHoraInicio()).thenReturn("9:45");

        ArrayList<Clase> clasesGrupo16 = new ArrayList<>();
        clasesGrupo16.add(clase40);
        clasesGrupo16.add(clase41);
        clasesGrupo16.add(clase42);
        when(grupo16.getClases()).thenReturn(clasesGrupo16);

        Grupo grupo17 = mock(Grupo.class);
        when(grupo17.getDocente()).thenReturn("Materia5");
        Clase clase43 = mock(Clase.class);
        when(clase43.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase43.getHoraInicio()).thenReturn("9:45");
        Clase clase44 = mock(Clase.class);
        when(clase44.getDia()).thenReturn(Dia.JUEVES);
        when(clase44.getHoraInicio()).thenReturn("9:45");
        Clase clase45 = mock(Clase.class);
        when(clase45.getDia()).thenReturn(Dia.SABADO);
        when(clase45.getHoraInicio()).thenReturn("9:45");

        ArrayList<Clase> clasesGrupo17 = new ArrayList<>();
        clasesGrupo17.add(clase43);
        clasesGrupo17.add(clase44);
        clasesGrupo17.add(clase45);
        when(grupo17.getClases()).thenReturn(clasesGrupo17);

        Grupo grupo18 = mock(Grupo.class);
        when(grupo18.getDocente()).thenReturn("Materia5");
        Clase clase46 = mock(Clase.class);
        when(clase46.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase46.getHoraInicio()).thenReturn("17:15");
        Clase clase47 = mock(Clase.class);
        when(clase47.getDia()).thenReturn(Dia.JUEVES);
        when(clase47.getHoraInicio()).thenReturn("17:15");
        Clase clase48 = mock(Clase.class);
        when(clase48.getDia()).thenReturn(Dia.SABADO);
        when(clase48.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo18 = new ArrayList<>();
        clasesGrupo18.add(clase46);
        clasesGrupo18.add(clase47);
        clasesGrupo18.add(clase48);
        when(grupo18.getClases()).thenReturn(clasesGrupo18);

        Grupo grupo19= mock(Grupo.class);
        when(grupo19.getDocente()).thenReturn("Materia5");
        Clase clase49 = mock(Clase.class);
        when(clase49.getDia()).thenReturn(Dia.MARTES);
        when(clase49.getHoraInicio()).thenReturn("12:45");
        Clase clase50 = mock(Clase.class);
        when(clase50.getDia()).thenReturn(Dia.JUEVES);
        when(clase50.getHoraInicio()).thenReturn("8:15");
        Clase clase51 = mock(Clase.class);
        when(clase51.getDia()).thenReturn(Dia.VIERNES);
        when(clase51.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo19 = new ArrayList<>();
        clasesGrupo19.add(clase49);
        clasesGrupo19.add(clase50);
        clasesGrupo19.add(clase51);
        when(grupo19.getClases()).thenReturn(clasesGrupo19);

        ArrayList<Grupo> gruposMateria5 = new ArrayList<>();
        gruposMateria5.add(grupo12);
        gruposMateria5.add(grupo13);
        gruposMateria5.add(grupo14);
        gruposMateria5.add(grupo15);
        gruposMateria5.add(grupo16);
        gruposMateria5.add(grupo17);
        gruposMateria5.add(grupo18);
        gruposMateria5.add(grupo19);
        when(materia5.getGrupos()).thenReturn(gruposMateria5);

        ArrayList <Materia> materias = new ArrayList<>();
        materias.add(materia1);
        materias.add(materia2);
        materias.add(materia3);
        materias.add(materia4);
        materias.add(materia5);

        ArrayList<Grupo> res = creadorHorarios.crearHorario(materias);
        assertThat(res).hasSize(5);
        assertThat(res.get(0).getDocente()).contains("Materia1");
        assertThat(res.get(1).getDocente()).contains("Materia2");
        assertThat(res.get(2).getDocente()).contains("Materia3");
        assertThat(res.get(3).getDocente()).contains("Materia4");
        assertThat(res.get(4).getDocente()).contains("Materia5");
    }

    @Test
    public void dadoUnaMateriaCuandoLlamamosCrearHorarioDeberiaDevolverUnaListaConSoloUnGrupo(){
        Materia materia1 = mock(Materia.class);
        Grupo grupo1 = mock(Grupo.class);
        when(grupo1.getDocente()).thenReturn("Materia1");
        Clase clase1 = mock(Clase.class);
        when(clase1.getDia()).thenReturn(Dia.MARTES);
        when(clase1.getHoraInicio()).thenReturn("8:15");
        Clase clase2 = mock(Clase.class);
        when(clase2.getDia()).thenReturn(Dia.VIERNES);
        when(clase2.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo1 = new ArrayList<>();
        clasesGrupo1.add(clase1);
        clasesGrupo1.add(clase2);
        when(grupo1.getClases()).thenReturn(clasesGrupo1);

        Grupo grupo2 = mock(Grupo.class);
        when(grupo2.getDocente()).thenReturn("Materia1");
        Clase clase3 = mock(Clase.class);
        when(clase3.getDia()).thenReturn(Dia.MARTES);
        when(clase3.getHoraInicio()).thenReturn("11:15");
        Clase clase4 = mock(Clase.class);
        when(clase4.getDia()).thenReturn(Dia.VIERNES);
        when(clase4.getHoraInicio()).thenReturn("9:45");

        ArrayList<Clase> clasesGrupo2 = new ArrayList<>();
        clasesGrupo2.add(clase3);
        clasesGrupo2.add(clase4);
        when(grupo2.getClases()).thenReturn(clasesGrupo2);

        Grupo grupo3 = mock(Grupo.class);
        when(grupo3.getDocente()).thenReturn("Materia1");
        Clase clase5 = mock(Clase.class);
        when(clase5.getDia()).thenReturn(Dia.LUNES);
        when(clase5.getHoraInicio()).thenReturn("6:45");
        Clase clase6 = mock(Clase.class);
        when(clase6.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase6.getHoraInicio()).thenReturn("9:45");

        ArrayList<Clase> clasesGrupo3 = new ArrayList<>();
        clasesGrupo3.add(clase5);
        clasesGrupo3.add(clase6);
        when(grupo3.getClases()).thenReturn(clasesGrupo3);

        Grupo grupo4 = mock(Grupo.class);
        when(grupo4.getDocente()).thenReturn("Materia1");
        Clase clase7 = mock(Clase.class);
        when(clase7.getDia()).thenReturn(Dia.MARTES);
        when(clase7.getHoraInicio()).thenReturn("15:45");
        Clase clase8 = mock(Clase.class);
        when(clase8.getDia()).thenReturn(Dia.VIERNES);
        when(clase8.getHoraInicio()).thenReturn("14:15");

        ArrayList<Clase> clasesGrupo4 = new ArrayList<>();
        clasesGrupo4.add(clase7);
        clasesGrupo4.add(clase8);
        when(grupo4.getClases()).thenReturn(clasesGrupo4);

        Grupo grupo5 = mock(Grupo.class);
        when(grupo5.getDocente()).thenReturn("Materia1");
        Clase clase9 = mock(Clase.class);
        when(clase9.getDia()).thenReturn(Dia.JUEVES);
        when(clase9.getHoraInicio()).thenReturn("9:45");
        Clase clase10 = mock(Clase.class);
        when(clase10.getDia()).thenReturn(Dia.VIERNES);
        when(clase10.getHoraInicio()).thenReturn("11:15");

        ArrayList<Clase> clasesGrupo5 = new ArrayList<>();
        clasesGrupo5.add(clase9);
        clasesGrupo5.add(clase10);
        when(grupo5.getClases()).thenReturn(clasesGrupo5);

        ArrayList<Grupo> gruposMateria1 = new ArrayList<>();
        gruposMateria1.add(grupo1);
        gruposMateria1.add(grupo2);
        gruposMateria1.add(grupo3);
        gruposMateria1.add(grupo4);
        gruposMateria1.add(grupo5);
        when(materia1.getGrupos()).thenReturn(gruposMateria1);

        ArrayList <Materia> materias = new ArrayList<>();
        materias.add(materia1);
        ArrayList<Grupo> res = creadorHorarios.crearHorario(materias);
        assertThat(res).hasSize(1);
    }

    @Test
    public void dadoCincoMateriasConChoquesEntreTodosCuandoLlamamosACrearHorarioEntoncesDeberiaDevolverUnaListaConUnSoloGrupo(){
        Materia materia1 = mock(Materia.class);
        Grupo grupo1 = mock(Grupo.class);
        when(grupo1.getDocente()).thenReturn("Materia1");
        Clase clase1 = mock(Clase.class);
        when(clase1.getDia()).thenReturn(Dia.MARTES);
        when(clase1.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo1 = new ArrayList<>();
        clasesGrupo1.add(clase1);
        when(grupo1.getClases()).thenReturn(clasesGrupo1);

        ArrayList<Grupo> gruposMateria1 = new ArrayList<>();
        gruposMateria1.add(grupo1);
        when(materia1.getGrupos()).thenReturn(gruposMateria1);


        Materia materia2 = mock(Materia.class);
        Grupo grupo6 = mock(Grupo.class);
        when(grupo6.getDocente()).thenReturn("Materia2");
        Clase clase11 = mock(Clase.class);
        when(clase11.getDia()).thenReturn(Dia.MARTES);
        when(clase11.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo6 = new ArrayList<>();
        clasesGrupo6.add(clase11);
        when(grupo6.getClases()).thenReturn(clasesGrupo6);

        ArrayList<Grupo> gruposMateria2 = new ArrayList<>();
        gruposMateria2.add(grupo6);
        when(materia2.getGrupos()).thenReturn(gruposMateria2);


        Materia materia3 = mock(Materia.class);
        Grupo grupo7 = mock(Grupo.class);
        when(grupo7.getDocente()).thenReturn("Materia3");
        Clase clase13 = mock(Clase.class);
        when(clase13.getDia()).thenReturn(Dia.MARTES);
        when(clase13.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo7 = new ArrayList<>();
        clasesGrupo7.add(clase13);
        when(grupo7.getClases()).thenReturn(clasesGrupo7);

        ArrayList<Grupo> gruposMateria3 = new ArrayList<>();
        gruposMateria3.add(grupo7);
        when(materia3.getGrupos()).thenReturn(gruposMateria3);


        Materia materia4 = mock(Materia.class);
        Grupo grupo10 = mock(Grupo.class);
        when(grupo10.getDocente()).thenReturn("Materia4");
        Clase clase22 = mock(Clase.class);
        when(clase22.getDia()).thenReturn(Dia.MARTES);
        when(clase22.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo10 = new ArrayList<>();
        clasesGrupo10.add(clase22);
        when(grupo10.getClases()).thenReturn(clasesGrupo10);

        ArrayList<Grupo> gruposMateria4 = new ArrayList<>();
        gruposMateria4.add(grupo10);
        when(materia4.getGrupos()).thenReturn(gruposMateria4);


        Materia materia5 = mock(Materia.class);
        Grupo grupo12 = mock(Grupo.class);
        when(grupo12.getDocente()).thenReturn("Materia5");
        Clase clase28 = mock(Clase.class);
        when(clase28.getDia()).thenReturn(Dia.MARTES);
        when(clase28.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo12 = new ArrayList<>();
        clasesGrupo12.add(clase28);
        when(grupo12.getClases()).thenReturn(clasesGrupo12);

        ArrayList<Grupo> gruposMateria5 = new ArrayList<>();
        gruposMateria5.add(grupo12);
        when(materia5.getGrupos()).thenReturn(gruposMateria5);

        ArrayList <Materia> materias = new ArrayList<>();
        materias.add(materia1);
        materias.add(materia2);
        materias.add(materia3);
        materias.add(materia4);
        materias.add(materia5);


        ArrayList<Grupo> res = creadorHorarios.crearHorario(materias);
        assertThat(res).hasSize(1);
        assertThat(res.get(0).getDocente()).contains("Materia1");
    }

    @Test
    public void dadoCincoMateriasConDosChoquesCuandoLlamamosACrearHorarioEntoncesDeberiaDevolverUnaListaConTresGrupos(){
        Materia materia1 = mock(Materia.class);
        Grupo grupo1 = mock(Grupo.class);
        when(grupo1.getDocente()).thenReturn("Materia1");
        Clase clase1 = mock(Clase.class);
        when(clase1.getDia()).thenReturn(Dia.MARTES);
        when(clase1.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo1 = new ArrayList<>();
        clasesGrupo1.add(clase1);
        when(grupo1.getClases()).thenReturn(clasesGrupo1);

        ArrayList<Grupo> gruposMateria1 = new ArrayList<>();
        gruposMateria1.add(grupo1);
        when(materia1.getGrupos()).thenReturn(gruposMateria1);


        Materia materia2 = mock(Materia.class);
        Grupo grupo6 = mock(Grupo.class);
        when(grupo6.getDocente()).thenReturn("Materia2");
        Clase clase11 = mock(Clase.class);
        when(clase11.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase11.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo6 = new ArrayList<>();
        clasesGrupo6.add(clase11);
        when(grupo6.getClases()).thenReturn(clasesGrupo6);

        ArrayList<Grupo> gruposMateria2 = new ArrayList<>();
        gruposMateria2.add(grupo6);
        when(materia2.getGrupos()).thenReturn(gruposMateria2);


        Materia materia3 = mock(Materia.class);
        Grupo grupo7 = mock(Grupo.class);
        when(grupo7.getDocente()).thenReturn("Materia3");
        Clase clase13 = mock(Clase.class);
        when(clase13.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase13.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo7 = new ArrayList<>();
        clasesGrupo7.add(clase13);
        when(grupo7.getClases()).thenReturn(clasesGrupo7);

        ArrayList<Grupo> gruposMateria3 = new ArrayList<>();
        gruposMateria3.add(grupo7);
        when(materia3.getGrupos()).thenReturn(gruposMateria3);


        Materia materia4 = mock(Materia.class);
        Grupo grupo10 = mock(Grupo.class);
        when(grupo10.getDocente()).thenReturn("Materia4");
        Clase clase22 = mock(Clase.class);
        when(clase22.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase22.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo10 = new ArrayList<>();
        clasesGrupo10.add(clase22);
        when(grupo10.getClases()).thenReturn(clasesGrupo10);

        ArrayList<Grupo> gruposMateria4 = new ArrayList<>();
        gruposMateria4.add(grupo10);
        when(materia4.getGrupos()).thenReturn(gruposMateria4);


        Materia materia5 = mock(Materia.class);
        Grupo grupo12 = mock(Grupo.class);
        when(grupo12.getDocente()).thenReturn("Materia5");
        Clase clase28 = mock(Clase.class);
        when(clase28.getDia()).thenReturn(Dia.VIERNES);
        when(clase28.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo12 = new ArrayList<>();
        clasesGrupo12.add(clase28);
        when(grupo12.getClases()).thenReturn(clasesGrupo12);

        ArrayList<Grupo> gruposMateria5 = new ArrayList<>();
        gruposMateria5.add(grupo12);
        when(materia5.getGrupos()).thenReturn(gruposMateria5);

        ArrayList <Materia> materias = new ArrayList<>();
        materias.add(materia1);
        materias.add(materia2);
        materias.add(materia3);
        materias.add(materia4);
        materias.add(materia5);


        ArrayList<Grupo> res = creadorHorarios.crearHorario(materias);
        assertThat(res).hasSize(3);
    }

    @Test
    public void dadoCincoMateriasDondeLaPrimeraChocaConTodasLasDemasCuandoLlamamosACrearHorarioEntoncesDeberiaDevolverUnaListaConCuatroGrupos(){
        Materia materia1 = mock(Materia.class);
        Grupo grupo1 = mock(Grupo.class);
        when(grupo1.getDocente()).thenReturn("Materia1");
        Clase clase1 = mock(Clase.class);
        when(clase1.getDia()).thenReturn(Dia.LUNES);
        when(clase1.getHoraInicio()).thenReturn("8:15");
        Clase clase2 = mock(Clase.class);
        when(clase2.getDia()).thenReturn(Dia.MARTES);
        when(clase2.getHoraInicio()).thenReturn("8:15");
        Clase clase3 = mock(Clase.class);
        when(clase3.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase3.getHoraInicio()).thenReturn("8:15");
        Clase clase4 = mock(Clase.class);
        when(clase4.getDia()).thenReturn(Dia.JUEVES);
        when(clase4.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo1 = new ArrayList<>();
        clasesGrupo1.add(clase1);
        clasesGrupo1.add(clase2);
        clasesGrupo1.add(clase3);
        clasesGrupo1.add(clase4);
        when(grupo1.getClases()).thenReturn(clasesGrupo1);

        ArrayList<Grupo> gruposMateria1 = new ArrayList<>();
        gruposMateria1.add(grupo1);
        when(materia1.getGrupos()).thenReturn(gruposMateria1);


        Materia materia2 = mock(Materia.class);
        Grupo grupo6 = mock(Grupo.class);
        when(grupo6.getDocente()).thenReturn("Materia2");
        Clase clase11 = mock(Clase.class);
        when(clase11.getDia()).thenReturn(Dia.LUNES);
        when(clase11.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo6 = new ArrayList<>();
        clasesGrupo6.add(clase11);
        when(grupo6.getClases()).thenReturn(clasesGrupo6);

        ArrayList<Grupo> gruposMateria2 = new ArrayList<>();
        gruposMateria2.add(grupo6);
        when(materia2.getGrupos()).thenReturn(gruposMateria2);


        Materia materia3 = mock(Materia.class);
        Grupo grupo7 = mock(Grupo.class);
        when(grupo7.getDocente()).thenReturn("Materia3");
        Clase clase13 = mock(Clase.class);
        when(clase13.getDia()).thenReturn(Dia.MARTES);
        when(clase13.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo7 = new ArrayList<>();
        clasesGrupo7.add(clase13);
        when(grupo7.getClases()).thenReturn(clasesGrupo7);

        ArrayList<Grupo> gruposMateria3 = new ArrayList<>();
        gruposMateria3.add(grupo7);
        when(materia3.getGrupos()).thenReturn(gruposMateria3);


        Materia materia4 = mock(Materia.class);
        Grupo grupo10 = mock(Grupo.class);
        when(grupo10.getDocente()).thenReturn("Materia4");
        Clase clase22 = mock(Clase.class);
        when(clase22.getDia()).thenReturn(Dia.MIERCOLES);
        when(clase22.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo10 = new ArrayList<>();
        clasesGrupo10.add(clase22);
        when(grupo10.getClases()).thenReturn(clasesGrupo10);

        ArrayList<Grupo> gruposMateria4 = new ArrayList<>();
        gruposMateria4.add(grupo10);
        when(materia4.getGrupos()).thenReturn(gruposMateria4);


        Materia materia5 = mock(Materia.class);
        Grupo grupo12 = mock(Grupo.class);
        when(grupo12.getDocente()).thenReturn("Materia5");
        Clase clase28 = mock(Clase.class);
        when(clase28.getDia()).thenReturn(Dia.JUEVES);
        when(clase28.getHoraInicio()).thenReturn("8:15");

        ArrayList<Clase> clasesGrupo12 = new ArrayList<>();
        clasesGrupo12.add(clase28);
        when(grupo12.getClases()).thenReturn(clasesGrupo12);

        ArrayList<Grupo> gruposMateria5 = new ArrayList<>();
        gruposMateria5.add(grupo12);
        when(materia5.getGrupos()).thenReturn(gruposMateria5);

        ArrayList <Materia> materias = new ArrayList<>();
        materias.add(materia1);
        materias.add(materia2);
        materias.add(materia3);
        materias.add(materia4);
        materias.add(materia5);


        ArrayList<Grupo> res = creadorHorarios.crearHorario(materias);
        assertThat(res).hasSize(4);
    }
}