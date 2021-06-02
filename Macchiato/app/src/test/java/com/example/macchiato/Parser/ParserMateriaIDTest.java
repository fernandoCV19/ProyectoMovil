package com.example.macchiato.Parser;

import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static com.google.common.truth.Truth.assertThat;


public class ParserMateriaIDTest {

    private ParserMateriaID parserMateriaID;

    @Before
    public void setUp(){
        parserMateriaID = new ParserMateriaID();
    }

    @After
    public void tearDown(){
        parserMateriaID.getIds().clear();
    }

    @Test
    public void cuandoEsLLamadoGetIdsAntesDeParsearEntoncesLaListaDeIdsDeberiaEstarVacia(){
        HashMap<String, ParserMateriaID.Par> ids = parserMateriaID.getIds();
        assertThat(ids).hasSize(0);
    }

    @Test
    public void dadoUnaCadenaParseableQueContieneUnaMateriaCuandoEsLlamadoIniciarIdsEntoncesElHashMapDeIdsDeberiaContenerUnElemento() throws Exception {
        parserMateriaID.iniciarIDs("{\r\n  \"MATERIAS\":[\r\n    {\r\n      \"id\":\"1\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    }\r\n  ]\r\n}");
        assertThat(parserMateriaID.getIds().size()).isEqualTo(1);
    }

    @Test
    public void dadoUnaCadenaParseableQueContieneUnaMateriaCuandoEsLlamadoIniciarEntoncesElHashMapDeberiaTenerElIdYRequisitosDeLaMateriaDeInglesIDadaComoLlave() throws ParseException {
        parserMateriaID.iniciarIDs("{\r\n  \"MATERIAS\":[\r\n    {\r\n      \"id\":\"1\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    }\r\n  ]\r\n}");
        ParserMateriaID.Par aux = null;
        for (ParserMateriaID.Par par: parserMateriaID.getIds().values()) {
            aux = par;
        }
        assertThat(aux.id).isEqualTo(1);
        assertThat(aux.requisitos.get(0)).contains("Examen de Ingreso");
    }

    @Test
    public void dadoUnaCadenaParseableQueContieneDiezMateriasCuandoEsLLamadoIniciarEntoncesElHashMapDeberiaContenerDiezElementos() throws ParseException {
        parserMateriaID.iniciarIDs("{\r\n  \"MATERIAS\":[\r\n    {\r\n      \"id\":\"1\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"2\",\r\n      \"nombreMateria\":\"FISICA GENERAL\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"3\",\r\n      \"nombreMateria\":\"ALGEBRA I\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"4\",\r\n      \"nombreMateria\":\"CALCULO I\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"5\",\r\n      \"nombreMateria\":\"INTRODUCCION A LA PROGRAMACION\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"6\",\r\n      \"nombreMateria\":\"INGLES II\",\r\n      \"requisitos\":[\"INGLES I\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"7\",\r\n      \"nombreMateria\":\"ALGEBRA II\",\r\n      \"requisitos\":[\"ALGEBRA I\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"8\",\r\n      \"nombreMateria\":\"CALCULO II\",\r\n      \"requisitos\":[\"CALCULO I\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"9\",\r\n      \"nombreMateria\":\"ELEM. DE PROGRAMACION Y ESTRUC. DE DATOS\",\r\n      \"requisitos\":[\"INTRODUCCION A LA PROGRAMACION\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"10\",\r\n      \"nombreMateria\":\"ARQUITECTURA DE COMPUTADORAS I\",\r\n      \"requisitos\":[\"FISICA GENERAL\"]\r\n\r\n    }  \r\n  ]\r\n}");
        assertThat(parserMateriaID.getIds().size()).isEqualTo(10);
    }

    @Test
    public void dadoUnaCadenaParseableQueContieneDiezMateriasCuandoEsLLamadoIniciarEntoncesElHashMapDeberiaContenerLosIdsDeLasMateriasParseadas() throws ParseException {
        parserMateriaID.iniciarIDs("{\r\n  \"MATERIAS\":[\r\n    {\r\n      \"id\":\"1\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"2\",\r\n      \"nombreMateria\":\"FISICA GENERAL\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"3\",\r\n      \"nombreMateria\":\"ALGEBRA I\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"4\",\r\n      \"nombreMateria\":\"CALCULO I\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"5\",\r\n      \"nombreMateria\":\"INTRODUCCION A LA PROGRAMACION\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"6\",\r\n      \"nombreMateria\":\"INGLES II\",\r\n      \"requisitos\":[\"INGLES I\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"7\",\r\n      \"nombreMateria\":\"ALGEBRA II\",\r\n      \"requisitos\":[\"ALGEBRA I\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"8\",\r\n      \"nombreMateria\":\"CALCULO II\",\r\n      \"requisitos\":[\"CALCULO I\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"9\",\r\n      \"nombreMateria\":\"ELEM. DE PROGRAMACION Y ESTRUC. DE DATOS\",\r\n      \"requisitos\":[\"INTRODUCCION A LA PROGRAMACION\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"10\",\r\n      \"nombreMateria\":\"ARQUITECTURA DE COMPUTADORAS I\",\r\n      \"requisitos\":[\"FISICA GENERAL\"]\r\n\r\n    }  \r\n  ]\r\n}");
        assertThat(parserMateriaID.getID("INGLES I")).isEqualTo(1);
        assertThat(parserMateriaID.getID("INTRODUCCION A LA PROGRAMACION")).isEqualTo(5);
        assertThat(parserMateriaID.getID("ARQUITECTURA DE COMPUTADORAS I")).isEqualTo(10);
    }

    @Test
    public void dadoUnaMateriaQueNoExisteCuandoEsLlamadoGetIdEntoncesDeberiaDevolver0() throws ParseException {
        parserMateriaID.iniciarIDs("{\r\n  \"MATERIAS\":[\r\n    {\r\n      \"id\":\"1\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"2\",\r\n      \"nombreMateria\":\"FISICA GENERAL\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"3\",\r\n      \"nombreMateria\":\"ALGEBRA I\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"4\",\r\n      \"nombreMateria\":\"CALCULO I\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"5\",\r\n      \"nombreMateria\":\"INTRODUCCION A LA PROGRAMACION\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"6\",\r\n      \"nombreMateria\":\"INGLES II\",\r\n      \"requisitos\":[\"INGLES I\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"7\",\r\n      \"nombreMateria\":\"ALGEBRA II\",\r\n      \"requisitos\":[\"ALGEBRA I\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"8\",\r\n      \"nombreMateria\":\"CALCULO II\",\r\n      \"requisitos\":[\"CALCULO I\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"9\",\r\n      \"nombreMateria\":\"ELEM. DE PROGRAMACION Y ESTRUC. DE DATOS\",\r\n      \"requisitos\":[\"INTRODUCCION A LA PROGRAMACION\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"10\",\r\n      \"nombreMateria\":\"ARQUITECTURA DE COMPUTADORAS I\",\r\n      \"requisitos\":[\"FISICA GENERAL\"]\r\n\r\n    }  \r\n  ]\r\n}");
        assertThat(parserMateriaID.getID("FISICA II")).isEqualTo(-1);
    }

    @Test
    public void dadoUnaMateriaCuandoEsLlamadoGetRequisitosEntoncesDeberiaDevolverLosRequisitosDeLasMaterias() throws ParseException {
        parserMateriaID.iniciarIDs("{\r\n  \"MATERIAS\":[\r\n    {\r\n      \"id\":\"1\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"2\",\r\n      \"nombreMateria\":\"FISICA GENERAL\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"3\",\r\n      \"nombreMateria\":\"ALGEBRA I\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"4\",\r\n      \"nombreMateria\":\"CALCULO I\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"5\",\r\n      \"nombreMateria\":\"INTRODUCCION A LA PROGRAMACION\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"6\",\r\n      \"nombreMateria\":\"INGLES II\",\r\n      \"requisitos\":[\"INGLES I\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"7\",\r\n      \"nombreMateria\":\"ALGEBRA II\",\r\n      \"requisitos\":[\"ALGEBRA I\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"8\",\r\n      \"nombreMateria\":\"CALCULO II\",\r\n      \"requisitos\":[\"CALCULO I\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"9\",\r\n      \"nombreMateria\":\"ELEM. DE PROGRAMACION Y ESTRUC. DE DATOS\",\r\n      \"requisitos\":[\"INTRODUCCION A LA PROGRAMACION\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"10\",\r\n      \"nombreMateria\":\"ARQUITECTURA DE COMPUTADORAS I\",\r\n      \"requisitos\":[\"FISICA GENERAL\"]\r\n\r\n    }  \r\n  ]\r\n}");
        assertThat(parserMateriaID.getRequisitos("INGLES I").get(0)).isEqualTo("Examen de Ingreso");
        assertThat(parserMateriaID.getRequisitos("CALCULO II").get(0)).isEqualTo("CALCULO I");
    }

    @Test
    public void dadoUnaMateriaQueNoExisteCuandoEsLlamadoGetRequisitosEntoncesDeberiaDevolverUnaCadenaVacia() throws ParseException {
        parserMateriaID.iniciarIDs("{\r\n  \"MATERIAS\":[\r\n    {\r\n      \"id\":\"1\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"2\",\r\n      \"nombreMateria\":\"FISICA GENERAL\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"3\",\r\n      \"nombreMateria\":\"ALGEBRA I\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"4\",\r\n      \"nombreMateria\":\"CALCULO I\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"5\",\r\n      \"nombreMateria\":\"INTRODUCCION A LA PROGRAMACION\",\r\n      \"requisitos\":[\"Examen de Ingreso\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"6\",\r\n      \"nombreMateria\":\"INGLES II\",\r\n      \"requisitos\":[\"INGLES I\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"7\",\r\n      \"nombreMateria\":\"ALGEBRA II\",\r\n      \"requisitos\":[\"ALGEBRA I\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"8\",\r\n      \"nombreMateria\":\"CALCULO II\",\r\n      \"requisitos\":[\"CALCULO I\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"9\",\r\n      \"nombreMateria\":\"ELEM. DE PROGRAMACION Y ESTRUC. DE DATOS\",\r\n      \"requisitos\":[\"INTRODUCCION A LA PROGRAMACION\"]\r\n\r\n    },\r\n    {\r\n      \"id\":\"10\",\r\n      \"nombreMateria\":\"ARQUITECTURA DE COMPUTADORAS I\",\r\n      \"requisitos\":[\"FISICA GENERAL\"]\r\n\r\n    }  \r\n  ]\r\n}");
        assertThat(parserMateriaID.getRequisitos("FISICA II")).isEmpty();
    }
}