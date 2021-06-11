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

//@RunWith(AndroidJUnit4.class)
public class RegistroJSONTest {
/*
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

    @Test
    public void dadoElCampoAprobadoCuandoGetMateriaNotaEntoncesDevuelveTodasLasMateriasAprobadas() throws Exception {
        registroJSON.aniadirNota(1, 80, context, nombreArchivo);
        registroJSON.aniadirNota(2, 70, context, nombreArchivo);
        registroJSON.aniadirNota(3, 85, context, nombreArchivo);
        ArrayList<MateriaNota> materias = registroJSON.getMateriaNota("materiasAprobadas", context, nombreArchivo);

        assertThat(materias.get(0).getNota()).isEqualTo(80);
        assertThat(materias.get(1).getNota()).isEqualTo(70);
        assertThat(materias.get(2).getNota()).isEqualTo(85);
        assertThat(materias.get(0).getMateriaId()).isEqualTo("1");
        assertThat(materias.get(1).getMateriaId()).isEqualTo("2");
        assertThat(materias.get(2).getMateriaId()).isEqualTo("3");
    }

    @Test
    public void dadoElCampoReprobadoCuandoGetMateriaNotaEntoncesDevuelveTodasLasMateriasAprobadas() throws Exception {
        registroJSON.aniadirNota(1, 20, context, nombreArchivo);
        registroJSON.aniadirNota(2, 30, context, nombreArchivo);
        registroJSON.aniadirNota(3, 45, context, nombreArchivo);
        ArrayList<MateriaNota> materias = registroJSON.getMateriaNota("materiasReprobadas", context, nombreArchivo);

        assertThat(materias.get(0).getNota()).isEqualTo(20);
        assertThat(materias.get(1).getNota()).isEqualTo(30);
        assertThat(materias.get(2).getNota()).isEqualTo(45);
        assertThat(materias.get(0).getMateriaId()).isEqualTo("1");
        assertThat(materias.get(1).getMateriaId()).isEqualTo("2");
        assertThat(materias.get(2).getMateriaId()).isEqualTo("3");
    }

    @Test
    public void dadoUnIDCuandoAnadirMateriaTomadaEntoncesAnadeElIdAlJsonIndicado() throws Exception {
        registroJSON.aniadirMateriaTomada(1, context, nombreArchivo);
        String contenido = (new LectorFichero()).leerFichero(context,nombreArchivo);
        assertThat(contenido).contains("\"materiasActuales\":[1]");
    }

    @Test
    public void dadoUnIDCuandoAnadirMateriaYaConMateriasPreviasRegistradasEntoncesAnadeElIdAlJsonIndicadoSinEliminarLosAnteriores() throws Exception {
        registroJSON.aniadirMateriaTomada(1, context, nombreArchivo);
        registroJSON.aniadirMateriaTomada(2, context, nombreArchivo);
        String contenido = (new LectorFichero()).leerFichero(context,nombreArchivo);
        assertThat(contenido).contains("\"materiasActuales\":[1,2]");
    }

    @Test
    public void cuandoGetMateriasTomadasEntoncesDevuelveUnaListaConLosIdsDeLasMateriasRegistradasPreviamente() throws Exception {
        registroJSON.aniadirMateriaTomada(1, context, nombreArchivo);
        registroJSON.aniadirMateriaTomada(2, context, nombreArchivo);
        registroJSON.aniadirMateriaTomada(3, context, nombreArchivo);
        registroJSON.aniadirMateriaTomada(4, context, nombreArchivo);

        ArrayList<Integer> ids = registroJSON.getMateriasTomadas(context, nombreArchivo);

        assertThat(ids).contains(1);
        assertThat(ids).contains(2);
        assertThat(ids).contains(3);
        assertThat(ids).contains(4);
    }

    @Test
    public void cuandoGetMateriasTomadasSinHaberRegistradoMateriasAntesEntoncesDevuelveUnaListaVacia() throws Exception {
        ArrayList<Integer> ids = registroJSON.getMateriasTomadas(context, nombreArchivo);

        assertThat(ids).isEmpty();
    }*/
}
