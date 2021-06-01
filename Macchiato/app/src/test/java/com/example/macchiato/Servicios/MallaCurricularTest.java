package com.example.macchiato.Servicios;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.google.common.truth.Truth.assertThat;

public class MallaCurricularTest {

    private MallaCurricular mallaCurricular;

    @Before
    public void setUp(){
        mallaCurricular = new MallaCurricular();
    }

    @After
    public void tearDown(){
        mallaCurricular = null;
    }

    @Test
    public void dadoUnEstudianteNuevocuandoGetSiguientesEntoncesDevuelveUnaListaConLasMateriasQueDeberiamosTomar(){
        ArrayList<Integer> ids = mallaCurricular.getSiguientes();
        assertThat(ids).hasSize(5);
        assertThat(ids).contains(1);
        assertThat(ids).contains(2);
        assertThat(ids).contains(3);
        assertThat(ids).contains(4);
        assertThat(ids).contains(5);
    }

    @Test
    public void dadoUnEstudianteNuevoCuandoGetSinTomarEntoncesDevuelveUnaListaConTodasLasListasDeLaCarrera(){
        ArrayList<Integer> ids = mallaCurricular.getSinTomar();
        assertThat(ids).hasSize(41);
    }

    @Test
    public void dadoUnEstudianteAproboTodoElPrimerSemestreCuandoQuitarEntoncesAlLlamarGetSiguientesEntoncesDevuelveLasMateriasDelSegundoSemestre(){
        ArrayList<Integer> aprobadas = new ArrayList<>();
        llenarIds(aprobadas, 1, 5);
        mallaCurricular.quitar(aprobadas);

        ArrayList<Integer> ids = mallaCurricular.getSiguientes();
        assertThat(ids).hasSize(6);
        assertThat(ids).contains(6);
        assertThat(ids).contains(7);
        assertThat(ids).contains(8);
        assertThat(ids).contains(9);
        assertThat(ids).contains(10);
        assertThat(ids).contains(11);
    }

    @Test
    public void dadoUnEstudianteAproboTodoElPrimerSemestreCuandoQuitarEntoncesAlLlamarGetSinTomarEntoncesDevuelveLasMateriasQueFaltaPorTomar(){
        ArrayList<Integer> aprobadas = new ArrayList<>();
        llenarIds(aprobadas, 1, 5);
        mallaCurricular.quitar(aprobadas);

        ArrayList<Integer> ids = mallaCurricular.getSinTomar();
        assertThat(ids).hasSize(36);
    }

    @Test
    public void dadoUnEstudianteAproboTodoElSegundoSemestreCuandoGetSiguientesEntoncesDevuelveLasMateriasDelTercerSemestre(){
        ArrayList<Integer> aprobadas = new ArrayList<>();
        llenarIds(aprobadas, 1, 11);
        mallaCurricular.quitarVarios(aprobadas);

        ArrayList<Integer> ids = mallaCurricular.getSiguientes();

        assertThat(ids).hasSize(6);
        assertThat(ids).contains(12);
        assertThat(ids).contains(13);
        assertThat(ids).contains(14);
        assertThat(ids).contains(15);
        assertThat(ids).contains(16);
        assertThat(ids).contains(17);
    }

    @Test
    public void dadoUnEstudianteAproboTodoSegundoSemestreCuandoQuitarEntoncesAlLlamarGetSinTomarEntoncesDevuelveLasMateriasQueFaltaPorTomar(){
        ArrayList<Integer> aprobadas = new ArrayList<>();
        llenarIds(aprobadas, 1, 11);
        mallaCurricular.quitarVarios(aprobadas);

        ArrayList<Integer> ids = mallaCurricular.getSinTomar();
        assertThat(ids).hasSize(30);
    }

    @Test
    public void dadoUnEstudianteAproboHastaQuintoSemestreCuandoGetSiguientesEntoncesDevuelveLasMateriasDelSextoSemestre(){
        ArrayList<Integer> aprobadas = new ArrayList<>();
        llenarIds(aprobadas, 1, 29);

        mallaCurricular.quitarVarios(aprobadas);

        ArrayList<Integer> ids = mallaCurricular.getSiguientes();
        assertThat(ids).hasSize(6);
        assertThat(ids).contains(30);
        assertThat(ids).contains(31);
        assertThat(ids).contains(32);
        assertThat(ids).contains(33);
        assertThat(ids).contains(34);
        assertThat(ids).contains(35);

    }

    @Test
    public void dadoUnEstudianteApruebaHastaQuintoSemestreCuandoGetSinTomarEntoncesDevuelveUnaListaDeLasMateriasSinTomar(){
        ArrayList<Integer> aprobadas = new ArrayList<>();
        llenarIds(aprobadas, 1, 29);

        mallaCurricular.quitarVarios(aprobadas);

        ArrayList<Integer> ids = mallaCurricular.getSinTomar();

        assertThat(ids).hasSize(12);
    }

    @Test
    public void dadoUnEstudianteQueAproboTodasLasMateriasCuandoGetSinTomarEntoncesDevulveProyectoDeGrado2(){
        ArrayList<Integer> aprobadas = new ArrayList<>();
        llenarIds(aprobadas, 1, 35);
        aprobadas.add(37);aprobadas.add(38);
        aprobadas.add(40);aprobadas.add(41);
        aprobadas.add(44);

        mallaCurricular.quitarVarios(aprobadas);

        ArrayList<Integer> ids = mallaCurricular.getSinTomar();

        assertThat(ids).hasSize(1);
        assertThat(ids.get(0)).isEqualTo(53);
    }

    //Falla
    @Test
    public void dadoUnEstudianteQueAproboTodasLasMateriasCuandoGetSiguientesEntoncesDevuelveProyectoDeGrado2(){
        ArrayList<Integer> aprobadas = new ArrayList<>();
        llenarIds(aprobadas, 1, 35);

        mallaCurricular.quitarVarios(aprobadas);

        ArrayList<Integer> ids = mallaCurricular.getSiguientes();

        assertThat(ids).hasSize(4);
    }

    @Test
    public void dadoUnEstudianteConAprobacionesEnDiferentesSemestresCuandoGetSinTomarEntoncesDevuelveLaMateriaQueDeberiaTomar(){
        ArrayList<Integer> aprobadas = new ArrayList<>();
        aprobadas.add(1);aprobadas.add(6); //16
        aprobadas.add(3); //7
        aprobadas.add(4);aprobadas.add(8);aprobadas.add(12);//18
        aprobadas.add(5);//9 y 11
        aprobadas.add(2);aprobadas.add(10);//14

        mallaCurricular.quitarVarios(aprobadas);

        ArrayList<Integer> ids = mallaCurricular.getSiguientes();

        assertThat(ids).hasSize(6);
        assertThat(ids).contains(16);
        assertThat(ids).contains(7);
        assertThat(ids).contains(18);
        assertThat(ids).contains(9);
        assertThat(ids).contains(11);
        assertThat(ids).contains(14);
    }

    @Test
    public void dadoUnAlumnoConMateriasPendientesCuandoGetSiguientesEntonesDevuelveLasMateriasFaltantes(){
        ArrayList<Integer> aprobadas = new ArrayList<>();
        llenarIds(aprobadas,6,11);

        mallaCurricular.quitarVarios(aprobadas);

        ArrayList<Integer> ids = mallaCurricular.getSiguientes();
        assertThat(ids).hasSize(5);
        assertThat(ids).contains(1);
        assertThat(ids).contains(2);
        assertThat(ids).contains(3);
        assertThat(ids).contains(4);
        assertThat(ids).contains(5);
    }

    @Test
    public void dadoUnAlumnoQueAproboProgramacionFuncionalYProbabilidadYEstadisticaCuandoGetSiguientesEntoncesDevuelveIAI(){
        ArrayList<Integer> aprobadas = new ArrayList<>();
        aprobadas.add(3);aprobadas.add(7);aprobadas.add(13);aprobadas.add(22);
        aprobadas.add(4);aprobadas.add(8);aprobadas.add(12);aprobadas.add(18);

        mallaCurricular.quitarVarios(aprobadas);

        ArrayList<Integer> ids = mallaCurricular.getSiguientes();
        assertThat(ids).hasSize(4);
        assertThat(ids).contains(29);
    }

    @Test
    public void dadoUnAlumnoQueAproboTallerDeProgramacionCuandoGetSiguientesEntoncesDevuelveSistemasOperativosYAutomatas(){
        ArrayList<Integer> aprobadas = new ArrayList<>();
        aprobadas.add(2);aprobadas.add(10);aprobadas.add(14);aprobadas.add(19);

        mallaCurricular.quitarVarios(aprobadas);

        ArrayList<Integer> ids = mallaCurricular.getSiguientes();
        assertThat(ids).contains(25);
        assertThat(ids).contains(27);
    }

    private void llenarIds(ArrayList<Integer> ids, int inicio, int fin){
        for (int i = inicio; i <= fin; i++){
            ids.add(i);
        }
    }
}