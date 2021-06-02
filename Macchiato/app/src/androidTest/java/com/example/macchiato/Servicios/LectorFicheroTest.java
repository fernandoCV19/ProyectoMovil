package com.example.macchiato.Servicios;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;

@RunWith(AndroidJUnit4.class)
public class LectorFicheroTest {

    private LectorFichero lectorFichero;
    private Context context;

    @Before
    public void setUp(){
        lectorFichero = new LectorFichero();
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @After
    public void tearDown(){
        if((new ArrayList<>(Arrays.asList(context.fileList())).contains("registroPrueba.json"))){
            context.deleteFile("registroPrueba.json");
        }
    }

    @Test
    public void dadoUnNombreDeArchivoYUnContenidoCuandoEscribirFicheroEntoncesEscribreUnFicheroConEseNombreDeArchivo(){
        boolean res = lectorFichero.escribirFichero("registroPrueba.json", "CONTENIDO", context);
        assertThat(res).isTrue();
        ArrayList <String> aux = new ArrayList<String>(Arrays.asList(context.fileList()));
        assertThat(aux).contains("registroPrueba.json");
    }

    @Test
    public void dadoUnNombreDeArchivoYUnContenidoCuandoEscribirFicheroEntoncesEscribreUnFicheroConEseContenido() throws FileNotFoundException, JSONException {
        boolean res = lectorFichero.escribirFichero("registroPrueba.json", "CONTENIDO", context);
        String contenido = lectorFichero.leerFichero(context, "registroPrueba.json");
        assertThat(res).isTrue();
        assertThat(contenido).contains("CONTENIDO");
    }

    @Test
    public void dadoUnNombreDeArchivoYaExistenteCuandoEscribirFicheroEntoncesReemplazaElContenido() throws FileNotFoundException, JSONException {
        lectorFichero.escribirFichero("registroPrueba.json", "CONTENIDO", context);
        lectorFichero.escribirFichero("registroPrueba.json", "contenido", context);
        String contenido = lectorFichero.leerFichero(context, "registroPrueba.json");
        assertThat(contenido).isEqualTo("contenido");
    }

    @Test
    public void dadoUnNombreDeArchivoYaExistenteCuandoLeerFicheroEntoncesDevuelveElContenidoDelArchivo() throws FileNotFoundException, JSONException {
        lectorFichero.escribirFichero("registroPrueba.json", "CONTENIDO", context);
        String contenido = lectorFichero.leerFichero(context, "registroPrueba.json");
        assertThat(contenido).contains("CONTENIDO");
    }

    @Test
    public void dadoUnNombreDeArchivoQueNoExisteCuandoLeerFicheroEntoncesCreaElArchivo() throws FileNotFoundException, JSONException {
        lectorFichero.leerFichero(context, "registroPrueba.json");
        ArrayList <String> aux = new ArrayList<>(Arrays.asList(context.fileList()));
        assertThat(aux).contains("registroPrueba.json");
    }

    @Test
    public void dadoUnNombreDeArchivoQueNoExisteCuandoLeerFicheroEntoncesCreaElArchivoYLoLlenaConUnFormatoPorDefecto() throws FileNotFoundException, JSONException{
        lectorFichero.leerFichero(context, "registroPrueba.json");
        String contenido = lectorFichero.leerFichero(context,"registroPrueba.json");
        assertThat(contenido).isNotEmpty();
    }

    @Test
    public void dadoUnNombreDeArchivoExistenteCuandoLeerFicheroEntoncesNoModificaElContenidoDelArchivo() throws FileNotFoundException, JSONException {
        lectorFichero.escribirFichero("registroPrueba.json", "CONTENIDO", context);
        lectorFichero.leerFichero(context, "registroPrueba.json");
        String contenido = lectorFichero.leerFichero(context, "registroPrueba.json");

        assertThat(contenido).isEqualTo("CONTENIDO");
    }
}