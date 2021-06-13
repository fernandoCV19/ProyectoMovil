package com.example.macchiato.Interfaz.RecyclerView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

import com.example.macchiato.Navigation_bottom;
import com.example.macchiato.R;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MostrarHorarioAdapterTest {
    @Before
    public void setUp(){
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_horario)).perform(click());
    }
    @BeforeClass
    public static  void setupClass(){
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.spinnerNivel)).perform(click());
        onView(withText("A")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withText("INGLES I")).perform(click());
        onView(withText("1 - CESPEDES GUIZADA MARIA BENITA")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withText("INTRODUCCION A LA PROGRAMACION")).perform(click());
        onView(withText("1 - SALAZAR SERRUDO CARLA")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());

    }

    @AfterClass
    public static void tearDownClass(){
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.spinnerNivel)).perform(click());
        onView(withText("A")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withText("INGLES I")).perform(click());
        onView(withText("1 - CESPEDES GUIZADA MARIA BENITA")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withText("INTRODUCCION A LA PROGRAMACION")).perform(click());
        onView(withText("1 - SALAZAR SERRUDO CARLA")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());

    }

    @Test
    public void primeraMateriaNombreSeCreaYseVe(){
        onView(withText("INGLES I")).check(matches(isDisplayed()));

    }
    @Test
    public void segundaMateriaNombreSeCreaYseVe(){
        onView(withText("INTRODUCCION A LA PROGRAMACION")).check(matches(isDisplayed()));

    }
    @Test
    public void primeraMaterianombreDocenteSecreaYseVe(){
        onView(withText("CESPEDES GUIZADA MARIA BENITA")).check(matches(isDisplayed()));
    }
    @Test
    public void segundaMaterianombreDocenteSecreaYseVe(){
        onView(withText("SALAZAR SERRUDO CARLA")).check(matches(isDisplayed()));
    }
    @Test
    public void primerMateriaTieneLaCantidadDeClasesCorrecta(){
        onView(allOf(withId(R.id.horariosInfo),hasSibling(withText("CESPEDES GUIZADA MARIA BENITA")))).check(matches(hasChildCount(2)));
    }
    @Test
    public void segundaMateriaTieneLaCantidadDeClasesCorrecta(){
        onView(withId(R.id.mostrarHorarioFragment)).perform(swipeUp());
        onView(allOf(withId(R.id.horariosInfo),hasSibling(withText("SALAZAR SERRUDO CARLA")))).check(matches(hasChildCount(3)));
    }
    @Test
    public void recyclerViewMateriasContieneDosElementos(){
        onView(withId(R.id.recyclerMostrar)).check(matches(hasChildCount(2)));

    }
}