package com.example.macchiato.Servicios;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.*;

public class MallaCurricularTest {

    private MallaCurricular mallaCurricular;

    @Before
    public void setUp() throws Exception {
        mallaCurricular = new MallaCurricular();
    }

    @After
    public void tearDown() throws Exception {
        mallaCurricular = null;
    }

    @Test
    public void cuandoGetSiguientesEntoncesDevuelveUnaListaConLasMateriasQueDeberiamosTomar(){
        ArrayList<Integer> ids = mallaCurricular.getSiguientes();
        assertThat(ids).hasSize(5);
        assertThat(ids).contains(1);
        assertThat(ids).contains(2);
        assertThat(ids).contains(3);
        assertThat(ids).contains(4);
        assertThat(ids).contains(5);
    }

}