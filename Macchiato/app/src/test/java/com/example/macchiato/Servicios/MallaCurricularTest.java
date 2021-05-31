package com.example.macchiato.Servicios;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.google.common.truth.Truth.assertThat;

public class MallaCurricularTest {

    private MallaCurricular mallaCurricular;

    @Before
    public void setUp(){
        mallaCurricular = new MallaCurricular();
    }

    @After
    public void tearDown(){
        mallaCurricular = null;
    }

    @Test
    public void cuandoGetSiguientesConUnEstudianteNuevoEntoncesDevuelveUnaListaConLasMateriasQueDeberiamosTomar(){
        ArrayList<Integer> ids = mallaCurricular.getSiguientes();
        assertThat(ids).hasSize(5);
        assertThat(ids).contains(1);
        assertThat(ids).contains(2);
        assertThat(ids).contains(3);
        assertThat(ids).contains(4);
        assertThat(ids).contains(5);
    }

    @Test
    public void cuandoGetSinTomarConUnEstudianteNuevoEntoncesDevuelveUnaListaConTodasLasListasDeLaCarrera(){
        ArrayList<Integer> ids = mallaCurricular.getSinTomar();
        assertThat(ids).hasSize(41);
    }

    @Test
    public void cuandoUnEstudianteAproboTodoElPrimerSemestreCuandoQuitarEntoncesAlLlamarGetSiguientesEntoncesDevuelveLasMateriasDelSegundoSemestre(){
        ArrayList<Integer> aprobadas = new ArrayList<>();
        llenarIds(aprobadas, 1, 5);
        aprobadas.add(1);aprobadas.add(2);aprobadas.add(3);aprobadas.add(4);aprobadas.add(5);
        mallaCurricular.quitar(aprobadas);

        ArrayList<Integer> ids = mallaCurricular.getSiguientes();
        assertThat(ids).hasSize(6);
        assertThat(ids).contains(6);
        assertThat(ids).contains(7);
        assertThat(ids).contains(8);
        assertThat(ids).contains(9);
        assertThat(ids).contains(10);
        assertThat(ids).contains(11);
    }

    @Test
    public void cuandoUnEstudianteAproboTodoElPrimerSemestreCuandoQuitarEntoncesAlLlamarGetSinTomarEntoncesDevuelveLasMateriasQueFaltaPorTomar(){
        ArrayList<Integer> aprobadas = new ArrayList<>();
        llenarIds(aprobadas, 1, 5);
        mallaCurricular.quitar(aprobadas);

        ArrayList<Integer> ids = mallaCurricular.getSinTomar();
        assertThat(ids).hasSize(36);
    }

    @Test
    public void cuandoUnEstudianteAproboTodoElSegundoSemestreCuandoGetSiguientesEntoncesDevuelveLasMateriasDelTercerSemestre(){
        ArrayList<Integer> aprobadas = new ArrayList<>();
        llenarIds(aprobadas, 1, 11);
        mallaCurricular.quitarVarios(aprobadas);

        ArrayList<Integer> ids = mallaCurricular.getSiguientes();

        assertThat(ids).hasSize(6);
        assertThat(ids).contains(12);
        assertThat(ids).contains(13);
        assertThat(ids).contains(14);
        assertThat(ids).contains(15);
        assertThat(ids).contains(16);
        assertThat(ids).contains(17);
    }

    @Test
    public void cuandoUnEstudianteAproboTodoSegundoSemestreCuandoQuitarEntoncesAlLlamarGetSinTomarEntoncesDevuelveLasMateriasQueFaltaPorTomar(){
        ArrayList<Integer> aprobadas = new ArrayList<>();
        llenarIds(aprobadas, 1, 11);
        mallaCurricular.quitarVarios(aprobadas);

        ArrayList<Integer> ids = mallaCurricular.getSinTomar();
        assertThat(ids).hasSize(30);
    }

    @Test
    public void cuandoUnEstudianteAproboHastaQuintoSemestreCuandoGetSiguientesEntoncesDevuelveLasMateriasDelSextoSemestre(){
        ArrayList<Integer> aprobadas = new ArrayList<>();
        llenarIds(aprobadas, 1, 29);

        mallaCurricular.quitarVarios(aprobadas);

        ArrayList<Integer> ids = mallaCurricular.getSiguientes();
        assertThat(ids).hasSize(6);
        assertThat(ids).contains(30);
        assertThat(ids).contains(31);
        assertThat(ids).contains(32);
        assertThat(ids).contains(33);
        assertThat(ids).contains(34);
        assertThat(ids).contains(35);

    }

    private void llenarIds(ArrayList<Integer> ids, int inicio, int fin){
        for (int i = inicio; i <= fin; i++){
            ids.add(i);
        }
    }
}