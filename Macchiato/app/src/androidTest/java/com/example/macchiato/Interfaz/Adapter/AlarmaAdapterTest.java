package com.example.macchiato.Interfaz.Adapter;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.macchiato.Interfaz.Activities.Navigation_bottom;
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
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4ClassRunner.class)
public class AlarmaAdapterTest {

    @BeforeClass
    public static void setUpClass() throws InterruptedException {
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.spinnerNivel)).perform(click());
        onView(withText("A")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withText("INGLES I")).perform(click());
        onView(withText("1 - CESPEDES GUIZADA MARIA BENITA")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.nav_ajustes)).perform(click());
        onView(withId(R.id.switch2)).perform(click());
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
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.nav_ajustes)).perform(click());
        onView(withId(R.id.switch2)).perform(click());
    }

    @Before
    public void setup(){
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_ajustes)).perform(click());
    }

    @Test
    public void nombreMateriaPrimeraClaseSeCreaYSeVe(){
        onView(allOf(withText("INGLES I"), hasSibling(withText("MARTES")))).check(matches(isDisplayed()));
    }

    @Test
    public void diaPrimeraClaseSeCreaYSeVe(){
        onView(withText("MARTES")).check(matches(isDisplayed()));
    }

    @Test
    public void horaPrimeraClaseSeCreaYSeVe(){
        onView(allOf(withText("08:15"), hasSibling(withText("MARTES")))).check(matches(isDisplayed()));
    }

    @Test
    public void nombreMateriaSegundaClaseSeCreaYSeVe(){
        onView(allOf(withText("INGLES I"), hasSibling(withText("VIERNES")))).check(matches(isDisplayed()));
    }

    @Test
    public void diaSegundaClaseSeCreaYSeVe(){
        onView(withText("VIERNES")).check(matches(isDisplayed()));
    }

    @Test
    public void horaSegundaClaseSeCreaYSeVe(){
        onView(allOf(withText("08:15"), hasSibling(withText("VIERNES")))).check(matches(isDisplayed()));
    }

    @Test
    public void recyclerViewTieneLaCantidadDeAlarmasCorrecta(){
        onView(withId(R.id.recyclerAlarmas)).check(matches(hasChildCount(2)));
    }
}