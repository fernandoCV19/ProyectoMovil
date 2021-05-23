package com.example.macchiato.Parser;

import com.example.macchiato.Enums.Dia;
import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.GrupoModelParser;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.google.common.truth.Truth.assertThat;

public class ParserMateriaGrupoTest {

    private ParserMateriaGrupo parserMateriaGrupo;

    @Before
    public void setup(){
        parserMateriaGrupo = new ParserMateriaGrupo();
    }

    @Test
    public void cadenaQueNoPuedeSerParseadaDeberiaDevolverNuloTest(){
        ArrayList<GrupoModelParser> aux = parserMateriaGrupo.parserMateriaGrupo("Cadena que no puede ser parseada");
        assertThat(aux).isNull();
    }

    @Test
    public void parserDeUnSoloGrupoYVerificacionDeCorrectoLlenadoDeLaListaTest(){
        ArrayList<GrupoModelParser> aux = parserMateriaGrupo.parserMateriaGrupo("{\r\n  \"MATERIAS\":[\r\n    {\r\n      \"id\":\"1\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"CESPEDES GUIZADA MARIA BENITA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"1\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"8:15\",\r\n          \"horaFinal\":\"9:45\",\r\n          \"aula\":\"693B\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"8:15\",\r\n          \"horaFinal\":\"9:45\",\r\n          \"aula\":\"691D\"\r\n        }\r\n      ]\r\n}\r\n  ]\r\n}" );
        assertThat(aux).hasSize(1);
    }

    @Test
    public void parserDeUnSoloGrupoYVerificacionDeCorrectaCreacionDelObjetoTest(){
        ArrayList<GrupoModelParser> aux = parserMateriaGrupo.parserMateriaGrupo("{\r\n  \"MATERIAS\":[\r\n    {\r\n      \"id\":\"1\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"CESPEDES GUIZADA MARIA BENITA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"1\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"8:15\",\r\n          \"horaFinal\":\"9:45\",\r\n          \"aula\":\"693B\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"8:15\",\r\n          \"horaFinal\":\"9:45\",\r\n          \"aula\":\"691D\"\r\n        }\r\n      ]\r\n}\r\n  ]\r\n}" );
        GrupoModelParser objeto = aux.get(0);
        Clase clase1 = objeto.getClases().get(0);
        Clase clase2 = objeto.getClases().get(1);

        assertThat(objeto.getID()).isEqualTo(1);
        assertThat(objeto.getNombre()).isEqualTo("INGLES I");
        assertThat(objeto.getDocente()).isEqualTo("CESPEDES GUIZADA MARIA BENITA");
        assertThat(objeto.getNivel()).isEqualTo('A');
        assertThat(objeto.getGrupo()).isEqualTo("1");
        assertThat(objeto.getCodigo()).isEqualTo("1803001");
        assertThat(clase1.getDia()).isEqualTo(Dia.MARTES);
        assertThat(clase1.getAula()).isEqualTo("693B");
        assertThat(clase1.getHoraInicio()).isEqualTo("8:15");
        assertThat(clase1.getHoraFinal()).isEqualTo("9:45");
        assertThat(clase2.getDia()).isEqualTo(Dia.VIERNES);
        assertThat(clase2.getAula()).isEqualTo("691D");
        assertThat(clase2.getHoraInicio()).isEqualTo("8:15");
        assertThat(clase2.getHoraFinal()).isEqualTo("9:45");
    }
}