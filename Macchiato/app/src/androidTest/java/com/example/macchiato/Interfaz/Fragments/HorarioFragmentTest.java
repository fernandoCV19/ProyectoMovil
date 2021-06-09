package com.example.macchiato.Interfaz.Fragments;

import androidx.core.location.GnssStatusCompat;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.macchiato.Navigation_bottom;
import com.example.macchiato.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class HorarioFragmentTest {

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(Navigation_bottom.class);

    @Before
    public void setup(){
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
    }

    @Test
    public void fragmentSeCrearYSePuedeVer(){
        onView(withId(R.id.fragmentHorario)).check(matches(isDisplayed()));
    }

    @Test
    public void toolbarSuperiorSePuedeVer(){
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewTituloSePuedeVer(){
        onView(withId(R.id.textView15)).check(matches(isDisplayed()));
    }

    @Test
    public void linearLayourSePuedeVer(){
        onView(withId(R.id.linearLayoutFragmentHorario)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewNivelSePuedeVer(){
        onView(withId(R.id.textView10)).check(matches(isDisplayed()));
    }

    @Test
    public void spinnerNivelSePuedeVer(){
        onView(withId(R.id.spinnerNivel)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewMateriaSePuedeVer(){
        onView(withId(R.id.textView13)).check(matches(isDisplayed()));
    }

    @Test
    public void spinnerMateriaSePuedeVer(){
        onView(withId(R.id.spinnerMateria)).check(matches(isDisplayed()));
    }

    @Test
    public void recyclerViewDeMateriasSePuedeVer(){
        onView(withId(R.id.recyclerCheckbox)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewTituloTieneTextoCorrecto(){
        onView(withId(R.id.textView15)).check(matches(withText("Horario")));
    }

    @Test
    public void textViewNivelTieneTextoCorrecto(){
        onView(withId(R.id.textView10)).check(matches(withText("Nivel")));
    }

    @Test
    public void textViewMateriaTieneTextoCorrecto(){
        onView(withId(R.id.textView13)).check(matches(withText("Materia")));
    }

    @Test
    public void elementosClicables(){
        onView(withId(R.id.spinnerMateria)).check(matches(isClickable()));
        onView(withId(R.id.spinnerNivel)).check(matches(isClickable()));
    }
}