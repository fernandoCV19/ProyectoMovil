package com.example.macchiato.Interfaz.Dialog;
import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.macchiato.Interfaz.Activities.HistorialAcademicoActivity;
import com.example.macchiato.R;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4ClassRunner.class)

public class DialogEstadisticaTest {

    @Before
    public void setUp() {
        ActivityScenario.launch(HistorialAcademicoActivity.class);
        onView(withId(R.id.añadir_floating)).perform(click());
        onView(withId(R.id.ver_Estadisticas)).perform(click());

    }

    @BeforeClass
    public static void setupClass() throws InterruptedException {
        ActivityScenario.launch(HistorialAcademicoActivity.class);
        onView(withId(R.id.añadir_floating)).perform(click());
        onView(withId(R.id.ver_Estadisticas)).perform(click());

    }


    @Test
    public void textViewPromedioGeneralSeVe() {
        onView(withId(R.id.promedioGen_text)).check(matches(isDisplayed()));

    }
    @Test
    public void textViewPromedio_materiasAprobadasSeVe() {
        onView(withId(R.id.promedio_materiasApro)).check(matches(isDisplayed()));

    }
    @Test
    public void textViewNum_materiasAprobadasSeVe() {
        onView(withId(R.id.Textnum_materiasApro)).check(matches(isDisplayed()));

    }
    @Test
    public void textViewNum_materiasReprobadasSeVe() {
        onView(withId(R.id.textNum_materiasRep)).check(matches(isDisplayed()));

    }

    @Test
    public void textViewMateriasCursadasSeVe() {
        onView(withId(R.id.text_materiasCursadas)).check(matches(isDisplayed()));

    }
    @Test
    public void textViewpromedio_notaSeVe() {
        onView(withId(R.id.promedio_nota)).check(matches(isDisplayed()));

    }
    @Test
    public void textViewpromedio_notamateriasAproSeVe() {
        onView(withId(R.id.promedio_notamateriasApro)).check(matches(isDisplayed()));

    }
    @Test
    public void textViewNum_materiasAprobadas() {
        onView(withId(R.id.num_materiasApro)).check(matches(isDisplayed()));

    }
    @Test
    public void textViewMateriasReprobadas() {
        onView(withId(R.id.materiasRep)).check(matches(isDisplayed()));

    }
    @Test
    public void textViewnum_materiasCursadas() {
        onView(withId(R.id.num_materiasCursadas)).check(matches(isDisplayed()));

    }
}
