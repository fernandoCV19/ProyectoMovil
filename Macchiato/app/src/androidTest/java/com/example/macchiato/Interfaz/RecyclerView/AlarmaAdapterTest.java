package com.example.macchiato.Interfaz.RecyclerView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

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
public class AlarmaAdapterTest {

    @BeforeClass
    public static void setUpClass() throws InterruptedException {
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.spinnerNivel)).perform(click());
        onView(withText("A")).perform(click());
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
        onView(withText("1 - CESPEDES GUIZADA MARIA BENITA")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.nav_ajustes)).perform(click());
    }

    @Before
    public void setup(){
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_ajustes)).perform(click());
    }

    @Test
    public void recyclerViewSeCreaYSeVe(){
        onView(withId(R.id.recyclerAlarmas)).check(matches(isDisplayed()));
    }

    @Test
    public void cardViewSeVe(){
        onView(withId(R.id.cardViewClase)).check(matches(isDisplayed()));
    }

    @Test
    public void nombreMateriaSeMuestra(){
        onView(withText("INGLES I")).check(matches(isDisplayed()));
    }
}