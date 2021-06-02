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
public class RegistroJSONTest {

    private RegistroJSON registroJSON;
    private Context context;
    private String nombreArchivo = "registroPrueba.json";

    @Before
    public void setUp() throws Exception {
        registroJSON = new RegistroJSON();
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @After
    public void tearDown() throws Exception {
        if((new ArrayList<>(Arrays.asList(context.fileList())).contains(nombreArchivo))){
            context.deleteFile(nombreArchivo);
        }
    }

    @Test
    public void dadoUnNombreDeArchivoCuandoGenerarVacioEntoncesCreaUnArchivo() throws FileNotFoundException, JSONException {
        registroJSON.genararVacio(context, nombreArchivo);
        ArrayList <String> aux = new ArrayList<String>(Arrays.asList(context.fileList()));
        assertThat(aux).contains(nombreArchivo);
    }

    @Test
    public void dadoUnNombreDeArchivoCuandoGenerarVacioEntoncesCreaUnArchivoYLePoneUnFormatoPredefinido() throws FileNotFoundException, JSONException {
        registroJSON.genararVacio(context, nombreArchivo);
        String contenido = (new LectorFichero()).leerFichero(context,nombreArchivo);
        assertThat(contenido).isEqualTo("{\"password\":\"\",\"materiasActuales\":[],\"materiasPorTomar\":[],\"materiasAprobadas\":[],\"email\":\"\",\"materiasReprobadas\":[],\"uid\":\"\",\"userName\":\"\"}");
    }
}