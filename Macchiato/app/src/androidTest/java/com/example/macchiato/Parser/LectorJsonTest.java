package com.example.macchiato.Parser;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static com.google.common.truth.Truth.assertThat;

@RunWith(AndroidJUnit4.class)
public class LectorJsonTest {

    private LectorJson lectorJson;
    private Context context;

    @Before
    public void setup(){
        lectorJson = new LectorJson();
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Test
    public void dadoElNombreDeUnArchivoQueNoExisteYElContextoCuandoEsLLamadoElMetodoLectorEntoncesDeberiaDevolverUnaCadenaVacia(){
        String respuesta = lectorJson.loadJSONFromAsset("archivoQueNoExiste.json",context);
        assertThat(respuesta).isEmpty();
    }


    @Test
    public void dadoElNombreDelJsonDondeEstanTodosLosGruposYElContextoCuandoEsLlamadoElMetodoLectorEntoncesDeberiaDevolverUnaCadenaNoVacia(){
        String respuesta = lectorJson.loadJSONFromAsset("materias.json",context);
        assertThat(respuesta).isNotEmpty();
    }

    @Test
    public void dadoElNombreDelJsonDondeEstanTodosLosGruposYElContextoCuandoEsLlamdoElMetodoLectorEntoncesDeberiaDevolverUnaCadenaDondeSeEncuentrenLosGrupos(){
        String respuesta = lectorJson.loadJSONFromAsset("materias.json",context);
        assertThat(respuesta).contains("\"nombreMateria\":\"INGLES I\"");
        assertThat(respuesta).contains("\"docente\":\"CESPEDES GUIZADA MARIA BENITA\"");
        assertThat(respuesta).contains("\"codigo\":\"2010010\"");
    }

    @Test
    public void dadoElNombreDelJsonDondeEstanTodosLosIdsDeLasMateriasYElContextoCuandoEsLlamadoElMetodoLectorEntoncesDeberiaDevolverUnaCadenaNoVacia(){
        String respuesta = lectorJson.loadJSONFromAsset("materiasID.json",context);
        assertThat(respuesta).isNotEmpty();
    }

    @Test
    public void dadoElNombreDelJsonDondeEstanTodosLosIdsDeLasMateriasYElContextoCuandoEsLlamadoElMetodoLectorEntoncesDeberiaDevolverUnaCadenaDondeSeEncuentranLosIdsDeMateriasYSusRequisitos(){
        String respuesta = lectorJson.loadJSONFromAsset("materiasID.json",context);
        assertThat(respuesta).contains("\"nombreMateria\":\"INGLES I\"");
        assertThat(respuesta).contains("\"requisitos\":[\"Examen de Ingreso\"]");
        assertThat(respuesta).contains("\"requisitos\":[\"PROGRAMACION FUNCIONAL\", \"PROBABILIDAD Y ESTADISTICA\"]");
    }

    @Test
    public void dadoElNombreDelJsonDondeEstanTodosLosCodigosDeLasMaterriasYElContextoCuandoEsLLamadoElMetodoLectorEntoncesDeberiaDevolverUnaCadenaNoVacia(){
        String respuesta = lectorJson.loadJSONFromAsset("materiasNivel.json",context);
        assertThat(respuesta).isNotEmpty();
    }

    @Test
    public void dadoElNombreDelJsonDondeEstanTodosLosCodigosDeLasMaterriasYElContextoCuandoEsLLamadoElMetodoLectorEntoncesDeberiaDevolverUnaCadenaDondeSeEncuentrenLosCodigosDeLasMaterias(){
        String respuesta = lectorJson.loadJSONFromAsset("materiasNivel.json",context);
        assertThat(respuesta).contains("\"codigo\":\"1803001\"");
        assertThat(respuesta).contains("\"nombreMateria\":\" INGLES I\"");
        assertThat(respuesta).contains("\"nivel\":\"A\"");
    }
}