package com.example.macchiato.Parser;

import com.example.macchiato.Enums.Dia;
import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.GrupoModelParser;
import com.google.firebase.database.ThrowOnExtraProperties;

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

        assertThat(objeto.getID()).isEqualTo(1);
        assertThat(objeto.getNombre()).isEqualTo("INGLES I");
        assertThat(objeto.getDocente()).isEqualTo("CESPEDES GUIZADA MARIA BENITA");
        assertThat(objeto.getNivel()).isEqualTo('A');
        assertThat(objeto.getGrupo()).isEqualTo("1");
        assertThat(objeto.getCodigo()).isEqualTo("1803001");

    }

    @Test
    public void parserDeUnSoloGrupoYVerificacionDeCorrectaCreacionDeLasClasesTest(){
        ArrayList<GrupoModelParser> aux = parserMateriaGrupo.parserMateriaGrupo("{\r\n  \"MATERIAS\":[\r\n    {\r\n      \"id\":\"1\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"CESPEDES GUIZADA MARIA BENITA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"1\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"8:15\",\r\n          \"horaFinal\":\"9:45\",\r\n          \"aula\":\"693B\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"8:15\",\r\n          \"horaFinal\":\"9:45\",\r\n          \"aula\":\"691D\"\r\n        }\r\n      ]\r\n}\r\n  ]\r\n}" );
        GrupoModelParser objeto = aux.get(0);
        Clase clase1 = objeto.getClases().get(0);
        Clase clase2 = objeto.getClases().get(1);

        assertThat(clase1.getDia()).isEqualTo(Dia.MARTES);
        assertThat(clase1.getAula()).isEqualTo("693B");
        assertThat(clase1.getHoraInicio()).isEqualTo("8:15");
        assertThat(clase1.getHoraFinal()).isEqualTo("9:45");
        assertThat(clase2.getDia()).isEqualTo(Dia.VIERNES);
        assertThat(clase2.getAula()).isEqualTo("691D");
        assertThat(clase2.getHoraInicio()).isEqualTo("8:15");
        assertThat(clase2.getHoraFinal()).isEqualTo("9:45");
    }

    @Test
    public void parserDeVariasMateriasYComprobacionDeCorrectoLLenadoDeLaListaTest(){
        ArrayList<GrupoModelParser> aux = parserMateriaGrupo.parserMateriaGrupo("{\r\n  \"MATERIAS\":[\r\n    {\r\n      \"id\":\"1\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"CESPEDES GUIZADA MARIA BENITA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"1\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"8:15\",\r\n          \"horaFinal\":\"9:45\",\r\n          \"aula\":\"693B\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"8:15\",\r\n          \"horaFinal\":\"9:45\",\r\n          \"aula\":\"691D\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"2\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"CESPEDES GUIZADA MARIA BENITA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"2\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"11:15\",\r\n          \"horaFinal\":\"12:45\",\r\n          \"aula\":\"690D\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"690D\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"3\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"PEETERS ILONAA MAGDA LENA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"3\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"LUNES\",\r\n          \"horaInicio\":\"6:45\",\r\n          \"horaFinal\":\"8:15\",\r\n          \"aula\":\"655\"\r\n        },\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"691A\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"4\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"GRILO SALVATIERRA MARIA ESTELA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"4\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"15:45\",\r\n          \"horaFinal\":\"17:15\",\r\n          \"aula\":\"684A\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"14:15\",\r\n          \"horaFinal\":\"15:45\",\r\n          \"aula\":\"692E\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"5\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"CESPEDES GUIZADA MARIA BENITA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"5\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"JUEVES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"692F\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"11:15\",\r\n          \"horaFinal\":\"12:45\",\r\n          \"aula\":\"691B\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"6\",\r\n      \"nombreMateria\":\"FISICA GENERAL\",\r\n      \"codigo\":\"2006063\",\r\n      \"docente\":\"VALENZUELA MIRANDA ROBERTO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"B\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"11:15\",\r\n          \"horaFinal\":\"12:45\",\r\n          \"aula\":\"651\"\r\n        },\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"14:15\",\r\n          \"horaFinal\":\"15:45\",\r\n          \"aula\":\"692C\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"7\",\r\n      \"nombreMateria\":\"ALGEBRA I\",\r\n      \"codigo\":\"2008019\",\r\n      \"docente\":\"RODRIGUEZ SEJAS JUAN ANTONIO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"10\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"617C\"\r\n        },\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"8:15\",\r\n          \"horaFinal\":\"9:45\",\r\n          \"aula\":\"692C\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"692C\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"8\",\r\n      \"nombreMateria\":\"ALGEBRA I\",\r\n      \"codigo\":\"2008019\",\r\n      \"docente\":\"CARRASCO CALVO ALVARO HERNANDO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"15\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"LUNES\",\r\n          \"horaInicio\":\"17:15\",\r\n          \"horaFinal\":\"18:45\",\r\n          \"aula\":\"642\"\r\n        },\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"15:45\",\r\n          \"horaFinal\":\"17:15\",\r\n          \"aula\":\"617\"\r\n        },\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"14:15\",\r\n          \"horaFinal\":\"15:45\",\r\n          \"aula\":\"607\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"9\",\r\n      \"nombreMateria\":\"ALGEBRA I\",\r\n      \"codigo\":\"2008019\",\r\n      \"docente\":\"LEON ROMERO GUALBERTO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"8\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"11:15\",\r\n          \"horaFinal\":\"12:45\",\r\n          \"aula\":\"691C\"\r\n        },\r\n        {\r\n          \"dia\":\"JUEVES\",\r\n          \"horaInicio\":\"6:45\",\r\n          \"horaFinal\":\"8:15\",\r\n          \"aula\":\"692B\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"14:15\",\r\n          \"horaFinal\":\"15:45\",\r\n          \"aula\":\"692C\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"10\",\r\n      \"nombreMateria\":\"CALCULO I\",\r\n      \"codigo\":\"2008054\",\r\n      \"docente\":\"GONZALES CARTAGENA LUCIO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"10\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"6:45\",\r\n          \"horaFinal\":\"8:15\",\r\n          \"aula\":\"642\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"6:45\",\r\n          \"horaFinal\":\"8:15\",\r\n          \"aula\":\"642\"\r\n        },\r\n        {\r\n          \"dia\":\"SABADO\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"617C\"\r\n        }\r\n      ]\r\n    }\r\n  ]\r\n}");
        assertThat(aux).hasSize(10);
    }

    @Test
    public void parserDeVariasMateriasYComprobacionDeCorrectoOrdenDeLasMateriasParseadasEnLaListaDeRespuestaTest(){
        ArrayList<GrupoModelParser> aux = parserMateriaGrupo.parserMateriaGrupo("{\r\n  \"MATERIAS\":[\r\n    {\r\n      \"id\":\"1\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"CESPEDES GUIZADA MARIA BENITA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"1\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"8:15\",\r\n          \"horaFinal\":\"9:45\",\r\n          \"aula\":\"693B\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"8:15\",\r\n          \"horaFinal\":\"9:45\",\r\n          \"aula\":\"691D\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"2\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"CESPEDES GUIZADA MARIA BENITA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"2\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"11:15\",\r\n          \"horaFinal\":\"12:45\",\r\n          \"aula\":\"690D\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"690D\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"3\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"PEETERS ILONAA MAGDA LENA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"3\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"LUNES\",\r\n          \"horaInicio\":\"6:45\",\r\n          \"horaFinal\":\"8:15\",\r\n          \"aula\":\"655\"\r\n        },\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"691A\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"4\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"GRILO SALVATIERRA MARIA ESTELA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"4\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"15:45\",\r\n          \"horaFinal\":\"17:15\",\r\n          \"aula\":\"684A\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"14:15\",\r\n          \"horaFinal\":\"15:45\",\r\n          \"aula\":\"692E\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"5\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"CESPEDES GUIZADA MARIA BENITA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"5\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"JUEVES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"692F\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"11:15\",\r\n          \"horaFinal\":\"12:45\",\r\n          \"aula\":\"691B\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"6\",\r\n      \"nombreMateria\":\"FISICA GENERAL\",\r\n      \"codigo\":\"2006063\",\r\n      \"docente\":\"VALENZUELA MIRANDA ROBERTO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"B\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"11:15\",\r\n          \"horaFinal\":\"12:45\",\r\n          \"aula\":\"651\"\r\n        },\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"14:15\",\r\n          \"horaFinal\":\"15:45\",\r\n          \"aula\":\"692C\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"7\",\r\n      \"nombreMateria\":\"ALGEBRA I\",\r\n      \"codigo\":\"2008019\",\r\n      \"docente\":\"RODRIGUEZ SEJAS JUAN ANTONIO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"10\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"617C\"\r\n        },\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"8:15\",\r\n          \"horaFinal\":\"9:45\",\r\n          \"aula\":\"692C\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"692C\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"8\",\r\n      \"nombreMateria\":\"ALGEBRA I\",\r\n      \"codigo\":\"2008019\",\r\n      \"docente\":\"CARRASCO CALVO ALVARO HERNANDO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"15\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"LUNES\",\r\n          \"horaInicio\":\"17:15\",\r\n          \"horaFinal\":\"18:45\",\r\n          \"aula\":\"642\"\r\n        },\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"15:45\",\r\n          \"horaFinal\":\"17:15\",\r\n          \"aula\":\"617\"\r\n        },\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"14:15\",\r\n          \"horaFinal\":\"15:45\",\r\n          \"aula\":\"607\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"9\",\r\n      \"nombreMateria\":\"ALGEBRA I\",\r\n      \"codigo\":\"2008019\",\r\n      \"docente\":\"LEON ROMERO GUALBERTO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"8\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"11:15\",\r\n          \"horaFinal\":\"12:45\",\r\n          \"aula\":\"691C\"\r\n        },\r\n        {\r\n          \"dia\":\"JUEVES\",\r\n          \"horaInicio\":\"6:45\",\r\n          \"horaFinal\":\"8:15\",\r\n          \"aula\":\"692B\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"14:15\",\r\n          \"horaFinal\":\"15:45\",\r\n          \"aula\":\"692C\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"10\",\r\n      \"nombreMateria\":\"CALCULO I\",\r\n      \"codigo\":\"2008054\",\r\n      \"docente\":\"GONZALES CARTAGENA LUCIO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"10\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"6:45\",\r\n          \"horaFinal\":\"8:15\",\r\n          \"aula\":\"642\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"6:45\",\r\n          \"horaFinal\":\"8:15\",\r\n          \"aula\":\"642\"\r\n        },\r\n        {\r\n          \"dia\":\"SABADO\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"617C\"\r\n        }\r\n      ]\r\n    }\r\n  ]\r\n}");
        assertThat(aux.get(0).getNombre()).contains("INGLES I");
        assertThat(aux.get(5).getNombre()).contains("FISICA GENERAL");
        assertThat(aux.get(9).getNombre()).contains("CALCULO I");
    }

    @Test
    public void parserDeVariasMateriasYComprobacionDeCorrectaCreacionDelObejtoDeUnaMateriaTest(){
        ArrayList<GrupoModelParser> aux = parserMateriaGrupo.parserMateriaGrupo("{\r\n  \"MATERIAS\":[\r\n    {\r\n      \"id\":\"1\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"CESPEDES GUIZADA MARIA BENITA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"1\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"8:15\",\r\n          \"horaFinal\":\"9:45\",\r\n          \"aula\":\"693B\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"8:15\",\r\n          \"horaFinal\":\"9:45\",\r\n          \"aula\":\"691D\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"2\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"CESPEDES GUIZADA MARIA BENITA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"2\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"11:15\",\r\n          \"horaFinal\":\"12:45\",\r\n          \"aula\":\"690D\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"690D\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"3\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"PEETERS ILONAA MAGDA LENA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"3\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"LUNES\",\r\n          \"horaInicio\":\"6:45\",\r\n          \"horaFinal\":\"8:15\",\r\n          \"aula\":\"655\"\r\n        },\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"691A\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"4\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"GRILO SALVATIERRA MARIA ESTELA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"4\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"15:45\",\r\n          \"horaFinal\":\"17:15\",\r\n          \"aula\":\"684A\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"14:15\",\r\n          \"horaFinal\":\"15:45\",\r\n          \"aula\":\"692E\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"5\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"CESPEDES GUIZADA MARIA BENITA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"5\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"JUEVES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"692F\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"11:15\",\r\n          \"horaFinal\":\"12:45\",\r\n          \"aula\":\"691B\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"6\",\r\n      \"nombreMateria\":\"FISICA GENERAL\",\r\n      \"codigo\":\"2006063\",\r\n      \"docente\":\"VALENZUELA MIRANDA ROBERTO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"B\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"11:15\",\r\n          \"horaFinal\":\"12:45\",\r\n          \"aula\":\"651\"\r\n        },\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"14:15\",\r\n          \"horaFinal\":\"15:45\",\r\n          \"aula\":\"692C\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"7\",\r\n      \"nombreMateria\":\"ALGEBRA I\",\r\n      \"codigo\":\"2008019\",\r\n      \"docente\":\"RODRIGUEZ SEJAS JUAN ANTONIO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"10\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"617C\"\r\n        },\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"8:15\",\r\n          \"horaFinal\":\"9:45\",\r\n          \"aula\":\"692C\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"692C\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"8\",\r\n      \"nombreMateria\":\"ALGEBRA I\",\r\n      \"codigo\":\"2008019\",\r\n      \"docente\":\"CARRASCO CALVO ALVARO HERNANDO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"15\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"LUNES\",\r\n          \"horaInicio\":\"17:15\",\r\n          \"horaFinal\":\"18:45\",\r\n          \"aula\":\"642\"\r\n        },\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"15:45\",\r\n          \"horaFinal\":\"17:15\",\r\n          \"aula\":\"617\"\r\n        },\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"14:15\",\r\n          \"horaFinal\":\"15:45\",\r\n          \"aula\":\"607\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"9\",\r\n      \"nombreMateria\":\"ALGEBRA I\",\r\n      \"codigo\":\"2008019\",\r\n      \"docente\":\"LEON ROMERO GUALBERTO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"8\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"11:15\",\r\n          \"horaFinal\":\"12:45\",\r\n          \"aula\":\"691C\"\r\n        },\r\n        {\r\n          \"dia\":\"JUEVES\",\r\n          \"horaInicio\":\"6:45\",\r\n          \"horaFinal\":\"8:15\",\r\n          \"aula\":\"692B\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"14:15\",\r\n          \"horaFinal\":\"15:45\",\r\n          \"aula\":\"692C\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"10\",\r\n      \"nombreMateria\":\"CALCULO I\",\r\n      \"codigo\":\"2008054\",\r\n      \"docente\":\"GONZALES CARTAGENA LUCIO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"10\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"6:45\",\r\n          \"horaFinal\":\"8:15\",\r\n          \"aula\":\"642\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"6:45\",\r\n          \"horaFinal\":\"8:15\",\r\n          \"aula\":\"642\"\r\n        },\r\n        {\r\n          \"dia\":\"SABADO\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"617C\"\r\n        }\r\n      ]\r\n    }\r\n  ]\r\n}");

        GrupoModelParser objeto = aux.get(6);

        assertThat(objeto.getID()).isEqualTo(7);
        assertThat(objeto.getNombre()).isEqualTo("ALGEBRA I");
        assertThat(objeto.getDocente()).isEqualTo("RODRIGUEZ SEJAS JUAN ANTONIO");
        assertThat(objeto.getNivel()).isEqualTo('A');
        assertThat(objeto.getGrupo()).isEqualTo("10");
        assertThat(objeto.getCodigo()).isEqualTo("2008019");
    }

    @Test
    public void parserDeVariasMateriasYComprobacionDeCorrectaCreacionDeLasClasesObejtoDeUnaMateriaTest(){
        ArrayList<GrupoModelParser> aux = parserMateriaGrupo.parserMateriaGrupo("{\r\n  \"MATERIAS\":[\r\n    {\r\n      \"id\":\"1\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"CESPEDES GUIZADA MARIA BENITA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"1\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"8:15\",\r\n          \"horaFinal\":\"9:45\",\r\n          \"aula\":\"693B\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"8:15\",\r\n          \"horaFinal\":\"9:45\",\r\n          \"aula\":\"691D\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"2\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"CESPEDES GUIZADA MARIA BENITA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"2\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"11:15\",\r\n          \"horaFinal\":\"12:45\",\r\n          \"aula\":\"690D\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"690D\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"3\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"PEETERS ILONAA MAGDA LENA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"3\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"LUNES\",\r\n          \"horaInicio\":\"6:45\",\r\n          \"horaFinal\":\"8:15\",\r\n          \"aula\":\"655\"\r\n        },\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"691A\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"4\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"GRILO SALVATIERRA MARIA ESTELA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"4\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"15:45\",\r\n          \"horaFinal\":\"17:15\",\r\n          \"aula\":\"684A\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"14:15\",\r\n          \"horaFinal\":\"15:45\",\r\n          \"aula\":\"692E\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"5\",\r\n      \"nombreMateria\":\"INGLES I\",\r\n      \"codigo\":\"1803001\",\r\n      \"docente\":\"CESPEDES GUIZADA MARIA BENITA\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"5\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"JUEVES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"692F\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"11:15\",\r\n          \"horaFinal\":\"12:45\",\r\n          \"aula\":\"691B\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"6\",\r\n      \"nombreMateria\":\"FISICA GENERAL\",\r\n      \"codigo\":\"2006063\",\r\n      \"docente\":\"VALENZUELA MIRANDA ROBERTO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"B\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"11:15\",\r\n          \"horaFinal\":\"12:45\",\r\n          \"aula\":\"651\"\r\n        },\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"14:15\",\r\n          \"horaFinal\":\"15:45\",\r\n          \"aula\":\"692C\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"7\",\r\n      \"nombreMateria\":\"ALGEBRA I\",\r\n      \"codigo\":\"2008019\",\r\n      \"docente\":\"RODRIGUEZ SEJAS JUAN ANTONIO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"10\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"617C\"\r\n        },\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"8:15\",\r\n          \"horaFinal\":\"9:45\",\r\n          \"aula\":\"692C\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"692C\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"8\",\r\n      \"nombreMateria\":\"ALGEBRA I\",\r\n      \"codigo\":\"2008019\",\r\n      \"docente\":\"CARRASCO CALVO ALVARO HERNANDO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"15\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"LUNES\",\r\n          \"horaInicio\":\"17:15\",\r\n          \"horaFinal\":\"18:45\",\r\n          \"aula\":\"642\"\r\n        },\r\n        {\r\n          \"dia\":\"MARTES\",\r\n          \"horaInicio\":\"15:45\",\r\n          \"horaFinal\":\"17:15\",\r\n          \"aula\":\"617\"\r\n        },\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"14:15\",\r\n          \"horaFinal\":\"15:45\",\r\n          \"aula\":\"607\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"9\",\r\n      \"nombreMateria\":\"ALGEBRA I\",\r\n      \"codigo\":\"2008019\",\r\n      \"docente\":\"LEON ROMERO GUALBERTO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"8\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"11:15\",\r\n          \"horaFinal\":\"12:45\",\r\n          \"aula\":\"691C\"\r\n        },\r\n        {\r\n          \"dia\":\"JUEVES\",\r\n          \"horaInicio\":\"6:45\",\r\n          \"horaFinal\":\"8:15\",\r\n          \"aula\":\"692B\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"14:15\",\r\n          \"horaFinal\":\"15:45\",\r\n          \"aula\":\"692C\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"id\":\"10\",\r\n      \"nombreMateria\":\"CALCULO I\",\r\n      \"codigo\":\"2008054\",\r\n      \"docente\":\"GONZALES CARTAGENA LUCIO\",\r\n      \"nivel\":\"A\",\r\n      \"grupo\":\"10\",\r\n      \"clases\":[\r\n        {\r\n          \"dia\":\"MIERCOLES\",\r\n          \"horaInicio\":\"6:45\",\r\n          \"horaFinal\":\"8:15\",\r\n          \"aula\":\"642\"\r\n        },\r\n        {\r\n          \"dia\":\"VIERNES\",\r\n          \"horaInicio\":\"6:45\",\r\n          \"horaFinal\":\"8:15\",\r\n          \"aula\":\"642\"\r\n        },\r\n        {\r\n          \"dia\":\"SABADO\",\r\n          \"horaInicio\":\"9:45\",\r\n          \"horaFinal\":\"11:15\",\r\n          \"aula\":\"617C\"\r\n        }\r\n      ]\r\n    }\r\n  ]\r\n}");

        GrupoModelParser objeto = aux.get(6);
        Clase clase1 = objeto.getClases().get(0);
        Clase clase2 = objeto.getClases().get(1);
        Clase clase3 = objeto.getClases().get(2);

        assertThat(clase1.getDia()).isEqualTo(Dia.MARTES);
        assertThat(clase1.getAula()).isEqualTo("617C");
        assertThat(clase1.getHoraInicio()).isEqualTo("9:45");
        assertThat(clase1.getHoraFinal()).isEqualTo("11:15");
        assertThat(clase2.getDia()).isEqualTo(Dia.MIERCOLES);
        assertThat(clase2.getAula()).isEqualTo("692C");
        assertThat(clase2.getHoraInicio()).isEqualTo("8:15");
        assertThat(clase2.getHoraFinal()).isEqualTo("9:45");
        assertThat(clase3.getDia()).isEqualTo(Dia.VIERNES);
        assertThat(clase3.getAula()).isEqualTo("692C");
        assertThat(clase3.getHoraInicio()).isEqualTo("9:45");
        assertThat(clase3.getHoraFinal()).isEqualTo("11:15");
    }
}