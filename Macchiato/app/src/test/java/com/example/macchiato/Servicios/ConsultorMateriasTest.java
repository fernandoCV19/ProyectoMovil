package com.example.macchiato.Servicios;

import com.example.macchiato.Models.Grupo;
import com.example.macchiato.Models.GrupoModelParser;
import com.example.macchiato.Models.Materia;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ConsultorMateriasTest {

    private ConsultorMaterias consultorMaterias;

    @Before
    public void setUp(){
        consultorMaterias = new ConsultorMaterias();
    }

    @After
    public void tearDown(){
        if(ConsultorMaterias.getMaterias() != null) {
            ArrayList<Materia> aux = ConsultorMaterias.getMaterias();
            aux.clear();

            HashMap<Character, ArrayList<Materia>> aux2 = ConsultorMaterias.getLisClasificada();
            aux2.clear();
        }
    }

    @Test
    public void cuandoGetMateriaYaIniciadaLaClaseEntoncesDevuelveUnaListaYaIniciada(){
        consultorMaterias.clasificarMaterias(new ArrayList<>());
        ArrayList<Materia> aux = ConsultorMaterias.getMaterias();
        assertThat(aux).isNotNull();
    }

    @Test
    public void cuandoGetLisClasificadaYaIniciadaLaClaseEntoncesDevuelveUnHashMapYaIniciado(){
        consultorMaterias.clasificarMaterias(new ArrayList<>());
        HashMap<Character, ArrayList<Materia>> aux = ConsultorMaterias.getLisClasificada();
        assertThat(aux).isNotNull();
    }

    @Test
    public void dadoUnaListaDeIdsCuandoDevolverGruposEntoncesDevuelveUnaListaConLosGruposQueTienenLosIds(){
        GrupoModelParser g1 = mock(GrupoModelParser.class);
        when(g1.getNombre()).thenReturn("Materia1");
        when(g1.getNivel()).thenReturn('A');
        when(g1.getID()).thenReturn(1);
        GrupoModelParser g2 = mock(GrupoModelParser.class);
        when(g2.getNombre()).thenReturn("Materia1");
        when(g2.getNivel()).thenReturn('A');
        when(g2.getID()).thenReturn(2);
        GrupoModelParser g3 = mock(GrupoModelParser.class);
        when(g3.getNombre()).thenReturn("Materia1");
        when(g3.getNivel()).thenReturn('A');
        when(g3.getID()).thenReturn(3);
        GrupoModelParser g4 = mock(GrupoModelParser.class);
        when(g4.getNombre()).thenReturn("Materia1");
        when(g4.getNivel()).thenReturn('A');
        when(g4.getID()).thenReturn(4);
        GrupoModelParser g5 = mock(GrupoModelParser.class);
        when(g5.getNombre()).thenReturn("Materia2");
        when(g5.getNivel()).thenReturn('A');
        when(g5.getID()).thenReturn(5);
        GrupoModelParser g6 = mock(GrupoModelParser.class);
        when(g6.getNombre()).thenReturn("Materia2");
        when(g6.getNivel()).thenReturn('A');
        when(g6.getID()).thenReturn(6);
        GrupoModelParser g7 = mock(GrupoModelParser.class);
        when(g7.getNombre()).thenReturn("Materia2");
        when(g7.getNivel()).thenReturn('A');
        when(g7.getID()).thenReturn(7);
        GrupoModelParser g8 = mock(GrupoModelParser.class);
        when(g8.getNombre()).thenReturn("Materia3");
        when(g8.getNivel()).thenReturn('A');
        when(g8.getID()).thenReturn(8);
        GrupoModelParser g9 = mock(GrupoModelParser.class);
        when(g9.getNombre()).thenReturn("Materia3");
        when(g9.getNivel()).thenReturn('A');
        when(g9.getID()).thenReturn(9);
        GrupoModelParser g10 = mock(GrupoModelParser.class);
        when(g10.getNombre()).thenReturn("Materia4");
        when(g10.getNivel()).thenReturn('A');
        when(g10.getID()).thenReturn(10);

        ArrayList<GrupoModelParser> grupos = new ArrayList<>();
        grupos.add(g1);grupos.add(g2);grupos.add(g3);grupos.add(g4);grupos.add(g5);
        grupos.add(g6);grupos.add(g7);grupos.add(g8);grupos.add(g9);grupos.add(g10);

        consultorMaterias.clasificarMaterias(grupos);

        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(2);ids.add(7);ids.add(9);

        ArrayList<ConsultorMaterias.Par> aux = consultorMaterias.devolverGrupos(ids);

        assertThat(aux).hasSize(3);
        assertThat(aux.get(0).getMateria()).contains("Materia1");
        assertThat(aux.get(0).getGrupo().getID()).isEqualTo(2);
        assertThat(aux.get(1).getMateria()).contains("Materia2");
        assertThat(aux.get(1).getGrupo().getID()).isEqualTo(7);
        assertThat(aux.get(2).getMateria()).contains("Materia3");
        assertThat(aux.get(2).getGrupo().getID()).isEqualTo(9);
    }

    @Test
    public void dadoUnIdCuandoLLamamosDevolverGruposEntoncesDevuelveUnParConElNombreDeLaMateriaYElGrupoDelId(){
        GrupoModelParser g1 = mock(GrupoModelParser.class);
        when(g1.getNombre()).thenReturn("Materia1");
        when(g1.getNivel()).thenReturn('A');
        when(g1.getID()).thenReturn(1);
        GrupoModelParser g2 = mock(GrupoModelParser.class);
        when(g2.getNombre()).thenReturn("Materia1");
        when(g2.getNivel()).thenReturn('A');
        when(g2.getID()).thenReturn(2);
        GrupoModelParser g3 = mock(GrupoModelParser.class);
        when(g3.getNombre()).thenReturn("Materia1");
        when(g3.getNivel()).thenReturn('A');
        when(g3.getID()).thenReturn(3);
        GrupoModelParser g4 = mock(GrupoModelParser.class);
        when(g4.getNombre()).thenReturn("Materia1");
        when(g4.getNivel()).thenReturn('A');
        when(g4.getID()).thenReturn(4);
        GrupoModelParser g5 = mock(GrupoModelParser.class);
        when(g5.getNombre()).thenReturn("Materia2");
        when(g5.getNivel()).thenReturn('A');
        when(g5.getID()).thenReturn(5);

        ArrayList<GrupoModelParser> grupos = new ArrayList<>();
        grupos.add(g1);grupos.add(g2);grupos.add(g3);grupos.add(g4);grupos.add(g5);

        consultorMaterias.clasificarMaterias(grupos);

        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(2);

        ArrayList<ConsultorMaterias.Par> aux = consultorMaterias.devolverGrupos(ids);

        assertThat(aux.get(0).getMateria()).contains("Materia1");
    }

    @Test
    public void dadoNingunIdCuandoDevolverGruposEncontesDevuelveUnaListaVacia(){
        GrupoModelParser g1 = mock(GrupoModelParser.class);
        when(g1.getNombre()).thenReturn("Materia1");
        when(g1.getNivel()).thenReturn('A');
        when(g1.getID()).thenReturn(1);
        GrupoModelParser g2 = mock(GrupoModelParser.class);
        when(g2.getNombre()).thenReturn("Materia1");
        when(g2.getNivel()).thenReturn('A');
        when(g2.getID()).thenReturn(2);
        GrupoModelParser g3 = mock(GrupoModelParser.class);
        when(g3.getNombre()).thenReturn("Materia1");
        when(g3.getNivel()).thenReturn('A');
        when(g3.getID()).thenReturn(3);
        GrupoModelParser g4 = mock(GrupoModelParser.class);
        when(g4.getNombre()).thenReturn("Materia1");
        when(g4.getNivel()).thenReturn('A');
        when(g4.getID()).thenReturn(4);
        GrupoModelParser g5 = mock(GrupoModelParser.class);
        when(g5.getNombre()).thenReturn("Materia2");
        when(g5.getNivel()).thenReturn('A');
        when(g5.getID()).thenReturn(5);

        ArrayList<GrupoModelParser> grupos = new ArrayList<>();
        grupos.add(g1);grupos.add(g2);grupos.add(g3);grupos.add(g4);grupos.add(g5);

        consultorMaterias.clasificarMaterias(grupos);

        ArrayList<ConsultorMaterias.Par> aux = consultorMaterias.devolverGrupos(new ArrayList<>());

        assertThat(aux).isEmpty();
    }

    @Test
    public void dadoUnNivelCuandoGetColorNivelEntoncesDevuelveUnStringConElCodigoDelColor(){
        String respuesta = consultorMaterias.getColorNivel('A');
        assertThat(respuesta).contains("#");
        assertThat(respuesta).hasLength(7);
    }

    @Test
    public void dadoUnNivelQueNoExisteCuandoGetColorNivelEntoncesDevuelveUnStringPorDefecto(){
        String respuesta = consultorMaterias.getColorNivel('Z');
        assertThat(respuesta).contains("#ffffff");
        assertThat(respuesta).hasLength(7);
    }

    @Test
    public void dadoUnaListaDeIdsQueNoExistenGetListaMateriasEntoncesDevuelveUnaListaVacia(){
        GrupoModelParser g1 = mock(GrupoModelParser.class);
        when(g1.getNombre()).thenReturn("Materia1");
        when(g1.getNivel()).thenReturn('A');
        GrupoModelParser g2 = mock(GrupoModelParser.class);
        when(g2.getNombre()).thenReturn("Materia2");
        when(g2.getNivel()).thenReturn('A');
        GrupoModelParser g3 = mock(GrupoModelParser.class);
        when(g3.getNombre()).thenReturn("Materia3");
        when(g3.getNivel()).thenReturn('A');
        GrupoModelParser g4 = mock(GrupoModelParser.class);
        when(g4.getNombre()).thenReturn("Materia4");
        when(g4.getNivel()).thenReturn('A');
        GrupoModelParser g5 = mock(GrupoModelParser.class);
        when(g5.getNombre()).thenReturn("Materia5");
        when(g5.getNivel()).thenReturn('A');

        ArrayList<GrupoModelParser> grupos = new ArrayList<>();
        grupos.add(g1);grupos.add(g2);grupos.add(g3);grupos.add(g4);grupos.add(g5);

        consultorMaterias.clasificarMaterias(grupos);

        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(-100);ids.add(-300);

        ArrayList<Materia>res = consultorMaterias.getListaMaterias(ids);

        assertThat(res).isEmpty();
    }

    @Test
    public void dadoUnaListaConUnObjetoGrupoModelParserCuandoClasificarMateriasEntoncesCrearElCorrespondienteObjetoMateria(){
        GrupoModelParser grupo = mock(GrupoModelParser.class);
        when(grupo.getID()).thenReturn(1);
        when(grupo.getNivel()).thenReturn('A');
        when(grupo.getNombre()).thenReturn("INGLES I");
        when(grupo.getCodigo()).thenReturn("ABC");
        when(grupo.getDocente()).thenReturn("JUAN PEREZ");
        when(grupo.getGrupo()).thenReturn("B");

        ArrayList<GrupoModelParser>grupos = new ArrayList<>();
        grupos.add(grupo);

        consultorMaterias.clasificarMaterias(grupos);

        Materia materia = ConsultorMaterias.getMaterias().get(0);

        assertThat(materia.getNombre()).contains("INGLES I");
        assertThat(materia.getCodigo()).contains("ABC");
        assertThat(materia.getColor()).isNotEmpty();
        assertThat(materia.getNivel()).isEqualTo('A');
        assertThat(materia.getGrupos().get(0).getDocente()).isEqualTo("JUAN PEREZ");
        assertThat(materia.getGrupos().get(0).getGrupo()).isEqualTo("B");
    }

    @Test
    public void dadoUnaListaDeGruposDeLaMismaMateriaCuandoClasificarMateriasEntoncesAsignaTodosLosGruposALaMismaMateria(){
        GrupoModelParser grupo1 = mock(GrupoModelParser.class);
        when(grupo1.getNivel()).thenReturn('A');
        when(grupo1.getNombre()).thenReturn("MATERIA");
        GrupoModelParser grupo2 = mock(GrupoModelParser.class);
        when(grupo2.getNivel()).thenReturn('A');
        when(grupo2.getNombre()).thenReturn("MATERIA");
        GrupoModelParser grupo3 = mock(GrupoModelParser.class);
        when(grupo3.getNivel()).thenReturn('A');
        when(grupo3.getNombre()).thenReturn("MATERIA");
        GrupoModelParser grupo4 = mock(GrupoModelParser.class);
        when(grupo4.getNivel()).thenReturn('A');
        when(grupo4.getNombre()).thenReturn("MATERIA");

        ArrayList<GrupoModelParser> aux = new ArrayList<>();
        aux.add(grupo1);aux.add(grupo2);aux.add(grupo3);aux.add(grupo4);

        consultorMaterias.clasificarMaterias(aux);

        Materia materia = ConsultorMaterias.getMaterias().get(0);

        assertThat(materia.getGrupos()).hasSize(4);
    }

    @Test
    public void dadoUnaListaDeGruposDeDiferentesMateriasDelMismoNivelCuandoClasificarMateriasEntoncesCrearVariosObejtosMateria(){
        GrupoModelParser grupo1 = mock(GrupoModelParser.class);
        when(grupo1.getNivel()).thenReturn('A');
        when(grupo1.getNombre()).thenReturn("MATERIA 1");
        GrupoModelParser grupo2 = mock(GrupoModelParser.class);
        when(grupo2.getNivel()).thenReturn('A');
        when(grupo2.getNombre()).thenReturn("MATERIA 2");
        GrupoModelParser grupo3 = mock(GrupoModelParser.class);
        when(grupo3.getNivel()).thenReturn('A');
        when(grupo3.getNombre()).thenReturn("MATERIA 3");
        GrupoModelParser grupo4 = mock(GrupoModelParser.class);
        when(grupo4.getNivel()).thenReturn('A');
        when(grupo4.getNombre()).thenReturn("MATERIA 4");
        GrupoModelParser grupo5 = mock(GrupoModelParser.class);
        when(grupo5.getNivel()).thenReturn('A');
        when(grupo5.getNombre()).thenReturn("MATERIA 5");

        ArrayList<GrupoModelParser> aux = new ArrayList<>();
        aux.add(grupo1);aux.add(grupo2);aux.add(grupo3);aux.add(grupo4);aux.add(grupo5);

        consultorMaterias.clasificarMaterias(aux);

        assertThat(ConsultorMaterias.getLisClasificada().get('A')).hasSize(5);
    }

    @Test
    public void dadoUnaListaDeGruposDeDiferentesMateriasDeDiferentesNivelesCuandoClasificarMateriasEntoncesClasificaCorrectamenteLasMateriasPorNivel(){
        GrupoModelParser grupo1 = mock(GrupoModelParser.class);
        when(grupo1.getNivel()).thenReturn('A');
        when(grupo1.getNombre()).thenReturn("MATERIA 1");
        GrupoModelParser grupo2 = mock(GrupoModelParser.class);
        when(grupo2.getNivel()).thenReturn('A');
        when(grupo2.getNombre()).thenReturn("MATERIA 2");
        GrupoModelParser grupo3 = mock(GrupoModelParser.class);
        when(grupo3.getNivel()).thenReturn('A');
        when(grupo3.getNombre()).thenReturn("MATERIA 3");
        GrupoModelParser grupo4 = mock(GrupoModelParser.class);
        when(grupo4.getNivel()).thenReturn('B');
        when(grupo4.getNombre()).thenReturn("MATERIA 4");
        GrupoModelParser grupo5 = mock(GrupoModelParser.class);
        when(grupo5.getNivel()).thenReturn('B');
        when(grupo5.getNombre()).thenReturn("MATERIA 5");
        GrupoModelParser grupo6 = mock(GrupoModelParser.class);
        when(grupo6.getNivel()).thenReturn('B');
        when(grupo6.getNombre()).thenReturn("MATERIA 6");
        GrupoModelParser grupo7 = mock(GrupoModelParser.class);
        when(grupo7.getNivel()).thenReturn('C');
        when(grupo7.getNombre()).thenReturn("MATERIA 7");
        GrupoModelParser grupo8 = mock(GrupoModelParser.class);
        when(grupo8.getNivel()).thenReturn('C');
        when(grupo8.getNombre()).thenReturn("MATERIA 8");
        GrupoModelParser grupo9 = mock(GrupoModelParser.class);
        when(grupo9.getNivel()).thenReturn('D');
        when(grupo9.getNombre()).thenReturn("MATERIA 9");
        GrupoModelParser grupo10 = mock(GrupoModelParser.class);
        when(grupo10.getNivel()).thenReturn('E');
        when(grupo10.getNombre()).thenReturn("MATERIA 10");

        ArrayList<GrupoModelParser> aux = new ArrayList<>();
        aux.add(grupo1);aux.add(grupo2);aux.add(grupo3);aux.add(grupo4);aux.add(grupo5);
        aux.add(grupo6);aux.add(grupo7);aux.add(grupo8);aux.add(grupo9);aux.add(grupo10);

        consultorMaterias.clasificarMaterias(aux);

        assertThat(ConsultorMaterias.getLisClasificada().get('A')).hasSize(3);
        assertThat(ConsultorMaterias.getLisClasificada().get('B')).hasSize(3);
        assertThat(ConsultorMaterias.getLisClasificada().get('C')).hasSize(2);
        assertThat(ConsultorMaterias.getLisClasificada().get('D')).hasSize(1);
        assertThat(ConsultorMaterias.getLisClasificada().get('E')).hasSize(1);
    }
}