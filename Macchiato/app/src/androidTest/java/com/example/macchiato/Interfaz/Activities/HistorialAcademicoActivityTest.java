package com.example.macchiato.Interfaz.Activities;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.macchiato.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class HistorialAcademicoActivityTest {

    @Rule
    public ActivityScenarioRule activityScenario = new ActivityScenarioRule(HistorialAcademicoActivity.class);

    @Test
    public void activitySePuedeVerYSeCrea(){
        onView(withId(R.id.historialAcademicoActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void barraSuperiorSePuedeVer(){
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
    }

    @Test
    public void tituloPrincipalSePuedeVer(){
        onView(withId(R.id.textView15)).check(matches(isDisplayed()));
    }

    @Test
    public void tituloParaMateriasAprobadasSePuedeVer(){
        onView(withId(R.id.textView2)).check(matches(isDisplayed()));
    }

    @Test
    public void tituloParaMateriasReprobadasSePuedeVer(){
        onView(withId(R.id.textView14)).check(matches(isDisplayed()));
    }

    @Test
    public void botonParaEstadisticasSePuedeVer(){
        onView(withId(R.id.ver_Estadisticas)).check(matches(isDisplayed()));
    }

    @Test
    public void botonParaAnadirMateriasSePuedeVer(){
        onView(withId(R.id.aniadir_floating)).check(matches(isDisplayed()));
    }

    @Test
    public void botonParaEliminarMateriasSePuedeVer(){
        onView(withId(R.id.floating_eliminar)).check(matches(isDisplayed()));
    }

    @Test
    public void listaDeMateriasAprobadasSePuedeVer(){
        onView(withId(R.id.list_materiasReprobadas)).check(matches(isDisplayed()));
    }

    @Test
    public void listaDeMateriasReprobadasSePuedeVer(){
        onView(withId(R.id.list_materiasReprobadas)).check(matches(isDisplayed()));
    }

    @Test
    public void menuFloatinActionButtonSePuedeVer(){
        onView(withId(R.id.floatingHistorialAcademico)).check(matches(isDisplayed()));
    }

    @Test
    public void botonesSePuedenClicar(){
        onView(withId(R.id.ver_Estadisticas)).check(matches(isClickable()));
        onView(withId(R.id.floating_eliminar)).check(matches(isClickable()));
        onView(withId(R.id.aniadir_floating)).check(matches(isClickable()));
    }

    @Test
    public void textViewPrincipalTieneElTextoCorrecto(){
        onView(withId(R.id.textView15)).check(matches(withText("Historial Academico")));
    }

    @Test
    public void textViewParaMateriasAprobadasTieneElTextoCorrecto(){
        onView(withId(R.id.textView2)).check(matches(withText("Materias Aprobadas")));
    }

    @Test
    public void textViewParaMateriasReprobadasTieneElTextoCorrecto(){
        onView(withId(R.id.textView14)).check(matches(withText("Materias Reprobadas")));
    }
}