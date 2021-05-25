package com.example.macchiato.Servicios;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.macchiato.Parser.ParserMateriaID;
import com.google.firebase.database.ThrowOnExtraProperties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.ConnectException;

import static com.google.common.truth.Truth.assertThat;

@RunWith(AndroidJUnit4.class)
public class IniciadorTest {

    private Iniciador iniciador;
    private Context context;

    @Before
    public void setup(){
        iniciador = new Iniciador();
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Test
    public void dadoElContextoCuandoEsLlamadoElMetodoIniciarEntoncesDeberiaIniciarLaEstructuraEstaticaDondeEstaElHashMapDeMaterias() throws Exception {
        iniciador.iniciar(context);
        assertThat(ParserMateriaID.getIds()).isNotNull();
    }

    @Test
    public void dadoElContextoCuandoEsLlamadoElMetodoIniciarEntoncesDeberiaIniciarLaEstructuraEstaticaDondeEstaLaListaDeMaterias() throws Exception {
        iniciador.iniciar(context);
        assertThat(ConsultorMaterias.getLisClasificada()).isNotNull();
    }

    @Test
    public void dadoElContextoCuandoEsLlamadoElMetodoIniciarEntoncesDeberiaIniciarLaEstructuraEstaticaDondeEstaLaListaDeIdsMaterias() throws Exception {
        iniciador.iniciar(context);
        assertThat(ConsultorMaterias.getMaterias()).isNotNull();
    }

    @Test
    public void dadoElContextoCuandoEsLlamadoElMetodoIniciarEntoncesDeberiaLlenarLaEstructuraEstaticaDondeEstaElHashMapDeMaterias() throws Exception {
        iniciador.iniciar(context);
        assertThat(ParserMateriaID.getIds()).isNotEmpty();
    }

    @Test
    public void dadoElContextoCuandoEsLlamadoElMetodoIniciarEntoncesDeberiaLlenarLaEstructuraEstaticaDondeEstaLaListaDeMaterias() throws Exception {
        iniciador.iniciar(context);
        assertThat(ConsultorMaterias.getLisClasificada()).isNotEmpty();
    }

    @Test
    public void dadoElContextoCuandoEsLlamadoElMetodoIniciarEntoncesDeberiaLLenarLaEstructuraEstaticaDondeEstaLaListaDeIdsMaterias() throws Exception {
        iniciador.iniciar(context);
        assertThat(ConsultorMaterias.getMaterias()).isNotEmpty();
    }

    @Test
    public void dadoElContextoCuandoEsLlamadoElMetodoIniciarDosVecesEntoncesNoDeberiaEjecutarseLaSegundaVez() throws Exception{
        iniciador.iniciar(context);
        Boolean respuesta = iniciador.iniciar(context);
        assertThat(respuesta).isFalse();
    }
}