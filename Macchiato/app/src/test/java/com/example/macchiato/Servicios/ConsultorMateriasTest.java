package com.example.macchiato.Servicios;

import com.example.macchiato.Models.GrupoModelParser;
import com.example.macchiato.Models.Materia;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ConsultorMateriasTest {

    private ConsultorMaterias consultorMaterias;

    @Before
    public void setup(){
        consultorMaterias = new ConsultorMaterias();
    }

    @After
    public void teardown(){
        ArrayList<Materia> aux = ConsultorMaterias.getMaterias();
        aux.clear();

        HashMap<Character, ArrayList<Materia>> aux2 = ConsultorMaterias.getLisClasificada();
        aux2.clear();
    }

    @Test
    public void cuandoGetMateriaYaIniciadaLaClaseEntoncesDevuelveUnaListaYaIniciada(){
        consultorMaterias.clasificarMaterias(new ArrayList<>());
        ArrayList<Materia> aux = ConsultorMaterias.getMaterias();
        assertThat(aux).isNotNull();
    }

    @Test
    public void cuandoGetLisClasificadaYaIniciadaLaClaseEntoncesDevuelveUnHashMapYaIniciado(){
        consultorMaterias.clasificarMaterias(new ArrayList<>());
        HashMap<Character, ArrayList<Materia>> aux = ConsultorMaterias.getLisClasificada();
        assertThat(aux).isNotNull();
    }

    @Test
    public void dadoUnaListaDeIdsCuandoDevolverGruposEntoncesDevuelveUnaListaConLosGruposQueTienenLosIds(){
        GrupoModelParser g1 = mock(GrupoModelParser.class);
        when(g1.getNombre()).thenReturn("Materia1");
        when(g1.getNivel()).thenReturn('A');
        when(g1.getID()).thenReturn(1);
        GrupoModelParser g2 = mock(GrupoModelParser.class);
        when(g2.getNombre()).thenReturn("Materia1");
        when(g2.getNivel()).thenReturn('A');
        when(g2.getID()).thenReturn(2);
        GrupoModelParser g3 = mock(GrupoModelParser.class);
        when(g3.getNombre()).thenReturn("Materia1");
        when(g3.getNivel()).thenReturn('A');
        when(g3.getID()).thenReturn(3);
        GrupoModelParser g4 = mock(GrupoModelParser.class);
        when(g4.getNombre()).thenReturn("Materia1");
        when(g4.getNivel()).thenReturn('A');
        when(g4.getID()).thenReturn(4);
        GrupoModelParser g5 = mock(GrupoModelParser.class);
        when(g5.getNombre()).thenReturn("Materia2");
        when(g5.getNivel()).thenReturn('A');
        when(g5.getID()).thenReturn(5);
        GrupoModelParser g6 = mock(GrupoModelParser.class);
        when(g6.getNombre()).thenReturn("Materia2");
        when(g6.getNivel()).thenReturn('A');
        when(g6.getID()).thenReturn(6);
        GrupoModelParser g7 = mock(GrupoModelParser.class);
        when(g7.getNombre()).thenReturn("Materia2");
        when(g7.getNivel()).thenReturn('A');
        when(g7.getID()).thenReturn(7);
        GrupoModelParser g8 = mock(GrupoModelParser.class);
        when(g8.getNombre()).thenReturn("Materia3");
        when(g8.getNivel()).thenReturn('A');
        when(g8.getID()).thenReturn(8);
        GrupoModelParser g9 = mock(GrupoModelParser.class);
        when(g9.getNombre()).thenReturn("Materia3");
        when(g9.getNivel()).thenReturn('A');
        when(g9.getID()).thenReturn(9);
        GrupoModelParser g10 = mock(GrupoModelParser.class);
        when(g10.getNombre()).thenReturn("Materia4");
        when(g10.getNivel()).thenReturn('A');
        when(g10.getID()).thenReturn(10);

        ArrayList<GrupoModelParser> grupos = new ArrayList<>();
        grupos.add(g1);grupos.add(g2);grupos.add(g3);grupos.add(g4);grupos.add(g5);
        grupos.add(g6);grupos.add(g7);grupos.add(g8);grupos.add(g9);grupos.add(g10);

        consultorMaterias.clasificarMaterias(grupos);

        assertThat(ConsultorMaterias.getMaterias()).hasSize(4);
    }
}