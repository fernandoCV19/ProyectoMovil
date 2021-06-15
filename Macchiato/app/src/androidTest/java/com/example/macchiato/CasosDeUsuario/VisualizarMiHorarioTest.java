package com.example.macchiato.CasosDeUsuario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.macchiato.Navigation_bottom;
import com.example.macchiato.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class VisualizarMiHorarioTest{

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(Navigation_bottom.class);

    @Before
    public void setUp(){
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
    }

    @After
    public void tearDown(){
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withId(R.id.eliminar_materias)).perform(click());
        onView(withText("ACEPTAR")).perform(click());
    }

    @Test
    public void visualizarCeroMaterias(){
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.recyclerMostrar)).check(matches(hasChildCount(0)));
    }

    @Test
    public void visualizarUnaMateria(){
        onView(withId(R.id.spinnerNivel)).perform(click());
        onView(withText("A")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withSubstring("INGLES")).perform(click());
        onView(withSubstring("1")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.recyclerMostrar)).check(matches(hasChildCount(1)));
    }

    @Test
    public void visualizarMasDeUnaMateria(){
        onView(withId(R.id.spinnerNivel)).perform(click());
        onView(withText("A")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withSubstring("INGLES")).perform(click());
        onView(withSubstring("1")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withSubstring("ALGEBRA")).perform(click());
        onView(withSubstring("10")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.recyclerMostrar)).check(matches(hasMinimumChildCount(2)));
    }

    @Test
    public void visualizarLasClasesDeUnaMateriaConDosClases(){
        onView(withId(R.id.spinnerNivel)).perform(click());
        onView(withText("A")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withSubstring("INGLES")).perform(click());
        onView(withSubstring("1")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.horariosInfo)).check(matches(hasChildCount(2)));
    }

    @Test
    public void visualizarLasClasesDeUnaMateriaConTresClases(){
        onView(withId(R.id.spinnerNivel)).perform(click());
        onView(withText("A")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withSubstring("ALGEBRA")).perform(click());
        onView(withSubstring("10")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.horariosInfo)).check(matches(hasChildCount(3)));
    }

    @Test
    public void verificarQueSeAnadeCorrectamenteUnGrupo(){
        onView(withId(R.id.spinnerNivel)).perform(click());
        onView(withText("A")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withSubstring("ALGEBRA")).perform(click());
        onView(withSubstring("10 - RODRIGUEZ")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withSubstring("RODRIGUEZ")).check(matches(isDisplayed()));
    }

    @Test
    public void verificarQueSeAnadeCorrectamenteMasDeUnaMateria(){
        onView(withId(R.id.spinnerNivel)).perform(click());
        onView(withText("A")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withSubstring("INGLES")).perform(click());
        onView(withSubstring("1")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withSubstring("ALGEBRA")).perform(click());
        onView(withSubstring("10")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withSubstring("RODRIGUEZ")).check(matches(isDisplayed()));
        onView(withSubstring("CESPEDES")).check(matches(isDisplayed()));
    }
}
