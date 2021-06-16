package com.example.macchiato.Interfaz.Adapter;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.macchiato.Enums.Dia;
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

@RunWith(AndroidJUnit4ClassRunner.class)
public class ClaseAdapterTest {

    @BeforeClass
    public static void setUpClass() throws InterruptedException {
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.spinnerNivel)).perform(click());
        onView(withText("A")).perform(click());
        onView(withText("3 - PEETERS ILONAA MAGDA LENA")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
    }

    @AfterClass
    public static void tearDownClass(){
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.spinnerNivel)).perform(click());
        onView(withText("A")).perform(click());
        onView(withText("3 - PEETERS ILONAA MAGDA LENA")).perform(click());
    }

    @Before
    public void setup(){
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_horario)).perform(click());
    }

    @Test
    public void nombrePrimeraClaseSeCreaYSeVe(){
        onView(withText(String.valueOf(Dia.LUNES))).check(matches(isDisplayed()));
    }

    @Test
    public void nombreSegundaClaseSeCreaYSeVe(){
        onView(withText(String.valueOf(Dia.MIERCOLES))).check(matches(isDisplayed()));
    }

    @Test
    public void horaPrimeraClaseSeCreaYSeVe(){
        onView(withText("6:45-8:15")).check(matches(isDisplayed()));
    }

    @Test
    public void horaSegundaClaseSeCreaYSeVe(){
        onView(withText("9:45-11:15")).check(matches(isDisplayed()));
    }

    @Test
    public void aulaPrimeraClaseSeCreaYSeVe(){
        onView(withText("655")).check(matches(isDisplayed()));
    }

    @Test
    public void aulaSegundaClaseSeCreaYSeVe(){
        onView(withText("691A")).check(matches(isDisplayed()));
    }
}