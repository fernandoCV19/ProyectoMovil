package com.example.macchiato.Servicios;

import com.example.macchiato.Models.MateriaNota;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static com.google.common.truth.Truth.assertThat;

import static org.mockito.Mockito.*;

public class EstadisticaHATest {

    private EstadisticaHA estadisticaHA;
    private ArrayList<MateriaNota> listaMaterias;
    private ArrayList<MateriaNota> listaAprobadas;
    private ArrayList<MateriaNota> listaReprobadas;

    @Before
    public void setUp() throws Exception {
        listaMaterias = new ArrayList<>();
        listaReprobadas = new ArrayList<>();
        listaAprobadas = new ArrayList<>();
        estadisticaHA = new EstadisticaHA(listaMaterias, listaAprobadas, listaReprobadas);
    }

    @Test
    public void dadoUnaMateriaEnLaListaDeMateriasCuandoGetPromedioDevuelveLaMismaNota(){
        MateriaNota mat1 = mock(MateriaNota.class);
        listaMaterias.add(mat1);
        when(mat1.getNota()).thenReturn(80);
        assertThat(estadisticaHA.calcularPromedioGeneral()).isEqualTo(80.0);
    }

    @Test
    public void dadoVariasMateriasEnLaListaDeMateriasCuandoGetPromedioDevuelveElPromedio(){
        MateriaNota mat1 = mock(MateriaNota.class);
        when(mat1.getNota()).thenReturn(70);
        MateriaNota mat2 = mock(MateriaNota.class);
        when(mat2.getNota()).thenReturn(65);
        MateriaNota mat3 = mock(MateriaNota.class);
        when(mat3.getNota()).thenReturn(10);
        listaMaterias.add(mat1);
        listaMaterias.add(mat2);
        listaMaterias.add(mat3);
        double n = (70.0 + 65.0 + 10.0)/3.0;
        assertThat(estadisticaHA.calcularPromedioGeneral()).isEqualTo((double) Math.round(n * 100d) / 100d);
    }

    @Test
    public void dadoUnaMateriaEnLaListaDeAprobadosCuandoGetCalcularPromedioMateriasAEntoncesDevuelveElPromedio(){
        MateriaNota mat1 = mock(MateriaNota.class);
        when(mat1.getNota()).thenReturn(20);
        listaAprobadas.add(mat1);
        assertThat(estadisticaHA.calcularPromedioMateriasA()).isEqualTo(20);
    }

    @Test
    public void dadoVariasMateriasEnLaListaDeAprobadosCuandoGetCalcularPromedioMateriasAEntoncesDevuelveElPromedio(){
        MateriaNota mat1 = mock(MateriaNota.class);
        when(mat1.getNota()).thenReturn(20);
        MateriaNota mat2 = mock(MateriaNota.class);
        when(mat2.getNota()).thenReturn(35);
        MateriaNota mat3 = mock(MateriaNota.class);
        when(mat3.getNota()).thenReturn(10);
        listaAprobadas.add(mat1);
        listaAprobadas.add(mat2);
        listaAprobadas.add(mat3);
        double n = (20.0 + 35.0 + 10.0)/3.0;
        assertThat(estadisticaHA.calcularPromedioMateriasA()).isEqualTo((double) Math.round(n * 100d) / 100d);
    }
}