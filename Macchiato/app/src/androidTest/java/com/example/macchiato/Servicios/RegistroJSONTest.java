package com.example.macchiato.Servicios;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.macchiato.Models.MateriaNota;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.*;

@RunWith(AndroidJUnit4.class)
public class RegistroJSONTest {

    private RegistroJSON registroJSON;
    private Context context;
    private String nombreArchivo = "registroPrueba.json";

    @Before
    public void setUp(){
        registroJSON = new RegistroJSON();
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @After
    public void tearDown(){
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

    @Test
    public void dadoUnNombreDeArchivoQueYaExisteCuandoGenerarVacioEntoncesSobreEscribreElArchivo() throws FileNotFoundException, JSONException {
        (new LectorFichero()).escribirFichero(nombreArchivo,"CONTENIDO",context);
        registroJSON.genararVacio(context,nombreArchivo);
        String contenido = (new LectorFichero()).leerFichero(context,nombreArchivo);
        assertThat(contenido).isEqualTo("{\"password\":\"\",\"materiasActuales\":[],\"materiasPorTomar\":[],\"materiasAprobadas\":[],\"email\":\"\",\"materiasReprobadas\":[],\"uid\":\"\",\"userName\":\"\"}");
    }

    @Test
    public void dadoLosDatosDeUnUsuarioYUnArchivoQueNoExisteCuandoRegistrarUsuarioEntoncesGuardaLosDatosDelUsuarioEnElArchivo() throws Exception {
        String email = "algo@gmail.com";
        String password = "algo123";
        String uid = "123";
        String userName = "algo";

        registroJSON.registrarUsuario(email, password, uid, userName, context, nombreArchivo);
        String contenido = (new LectorFichero()).leerFichero(context,nombreArchivo);
        assertThat(contenido).contains("\"email\":\"algo@gmail.com\"");
        assertThat(contenido).contains("\"password\":\"algo123\"");
        assertThat(contenido).contains("\"uid\":\"123\"");
        assertThat(contenido).contains("\"userName\":\"algo\"");
    }
    @Test
    public void dadoLosDatosDeUnUsuarioCuandoRegistrarUsuarioEntoncesGuardaLosDatosDelUsuarioEnElArchivo() throws Exception {
        registroJSON.genararVacio(context, nombreArchivo);
        String email = "algo@gmail.com";
        String password = "algo123";
        String uid = "123";
        String userName = "algo";

        registroJSON.registrarUsuario(email, password, uid, userName, context, nombreArchivo);
        String contenido = (new LectorFichero()).leerFichero(context,nombreArchivo);
        assertThat(contenido).contains("\"email\":\"algo@gmail.com\"");
        assertThat(contenido).contains("\"password\":\"algo123\"");
        assertThat(contenido).contains("\"uid\":\"123\"");
        assertThat(contenido).contains("\"userName\":\"algo\"");
    }

    @Test
    public void dadoUnNotaDeUnaMateriaAprobadaCuandoAniadirNotaEntoncesAnadeLaNotaEnMateriasAprobadas() throws Exception {
        registroJSON.aniadirNota(1, 80, context, nombreArchivo);
        String contenido = (new LectorFichero()).leerFichero(context,nombreArchivo);
        assertThat(contenido).contains("\"materiasAprobadas\":[{\"materiaID\":1,\"nota\":80}]");
    }

    @Test
    public void dadoUnaNotaDeUnaMateriaReprobadaCuandoAniadirNotaEntoncesAnadeLaNotaEnMateriasReprobadas() throws Exception {
        registroJSON.aniadirNota(1, 40, context, nombreArchivo);
        String contenido = (new LectorFichero()).leerFichero(context,nombreArchivo);
        assertThat(contenido).contains("\"materiasReprobadas\":[{\"materiaID\":1,\"nota\":40}]");
    }
    @Test
    public void dadoUnaNotaDeUnaMateriaAprobadaCuandoAniadirNotaYYHayNotasRegistradasEntoncesAnadeLaNotaEnMateriasAprobadasSinRescribirLoAnterior() throws Exception {
        registroJSON.aniadirNota(1, 80, context, nombreArchivo);
        registroJSON.aniadirNota(2, 90, context, nombreArchivo);
        String contenido = (new LectorFichero()).leerFichero(context,nombreArchivo);
        assertThat(contenido).contains("\"materiasAprobadas\":[{\"materiaID\":1,\"nota\":80},{\"materiaID\":2,\"nota\":90}]");
    }

    @Test
    public void dadoUnaNotaDeUnaMateriaReprobadaCuandoAniadirNotaYYHayNotasRegistradasEntoncesAnadeLaNotaEnMateriasAprobadasSinRescribirLoAnterior() throws Exception {
        registroJSON.aniadirNota(1, 40, context, nombreArchivo);
        registroJSON.aniadirNota(2, 10, context, nombreArchivo);
        String contenido = (new LectorFichero()).leerFichero(context,nombreArchivo);
        assertThat(contenido).contains("\"materiasReprobadas\":[{\"materiaID\":1,\"nota\":40},{\"materiaID\":2,\"nota\":10}]");
    }

    @Test
    public void dadoUnMateriaNotaAprobadaYSuSectorCuandoQuitarMateriaEntoncesEliminaLaMateria() throws Exception {
        registroJSON.aniadirNota(1, 80, context, nombreArchivo);
        MateriaNota materiaNota = new MateriaNota("1", 80);
        registroJSON.quitarMateria("materiasAprobadas", materiaNota, context, nombreArchivo);
        String contenido = (new LectorFichero()).leerFichero(context,nombreArchivo);
        assertThat(contenido).contains("\"materiasAprobadas\":[]");
    }

    @Test
    public void dadoUnMateriaNotaReprobadaYSuSectorCuandoQuitarMateriaEntoncesEliminaLaMateria() throws Exception {
        registroJSON.aniadirNota(1, 40, context, nombreArchivo);
        MateriaNota materiaNota = new MateriaNota("1", 40);
        registroJSON.quitarMateria("materiasReprobadas", materiaNota, context, nombreArchivo);
        String contenido = (new LectorFichero()).leerFichero(context,nombreArchivo);
        assertThat(contenido).contains("\"materiasReprobadas\":[]");
    }

    @Test
    public void dadoUnaMateriaNotaAprobadaPeroConSectorIncorrectoCuandoQuitarMateriaEntoncesNoEliminaLaMateria() throws Exception {
        registroJSON.aniadirNota(1, 80, context, nombreArchivo);
        MateriaNota materiaNota = new MateriaNota("1", 80);
        registroJSON.quitarMateria("materiasReprobadas", materiaNota, context, nombreArchivo);
        String contenido = (new LectorFichero()).leerFichero(context,nombreArchivo);
        assertThat(contenido).contains("\"materiasAprobadas\":[{\"materiaID\":1,\"nota\":80}]");
    }

    @Test
    public void dadoUnaMateriaNotaReprobadaPeroConSectorIncorrectoCuandoQuitarMateriaEntoncesNoEliminaLaMateria() throws Exception {
        registroJSON.aniadirNota(1, 40, context, nombreArchivo);
        MateriaNota materiaNota = new MateriaNota("1", 40);
        registroJSON.quitarMateria("materiasAprobadas", materiaNota, context, nombreArchivo);
        String contenido = (new LectorFichero()).leerFichero(context,nombreArchivo);
        assertThat(contenido).contains("\"materiasReprobadas\":[{\"materiaID\":1,\"nota\":40}]");
    }

    @Test
    public void dadoUnaMateriaNotaAprobadaConLaNotaQueNoConcuerdaConLaMateriaCuandoQuitarMateriaEntoncesNoEliminaLaMateria() throws Exception {
        registroJSON.aniadirNota(1, 80, context, nombreArchivo);
        MateriaNota materiaNota = new MateriaNota("1", 70);
        registroJSON.quitarMateria("materiasAprobadas", materiaNota, context, nombreArchivo);
        String contenido = (new LectorFichero()).leerFichero(context,nombreArchivo);
        assertThat(contenido).contains("\"materiasAprobadas\":[{\"materiaID\":1,\"nota\":80}]");
    }

    @Test
    public void dadoUnaMateriaReprobadaNotaConLaNotaQueNoConcuerdaConLaMateriaCuandoQuitarMateriaEntoncesNoEliminaLaMateria() throws Exception {
        registroJSON.aniadirNota(1, 40, context, nombreArchivo);
        MateriaNota materiaNota = new MateriaNota("1", 30);
        registroJSON.quitarMateria("materiasReprobadas", materiaNota, context, nombreArchivo);
        String contenido = (new LectorFichero()).leerFichero(context,nombreArchivo);
        assertThat(contenido).contains("\"materiasReprobadas\":[{\"materiaID\":1,\"nota\":40}]");
    }
}
