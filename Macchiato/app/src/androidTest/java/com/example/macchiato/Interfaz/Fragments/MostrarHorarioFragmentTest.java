package com.example.macchiato.Interfaz.Fragments;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import static org.junit.Assert.*;

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
public class MostrarHorarioFragmentTest {

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(Navigation_bottom.class);

    @Before
    public void setup(){
        onView(withId(R.id.nav_horario)).perform(click());
    }

    @Test
    public void fragmentSeCreaYSeVe(){
        onView(withId(R.id.mostrarHorarioFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void linearLayoutPrincipalSeVe(){
        onView(withId(R.id.linearLayoutMostrarMaterias)).check(matches(isDisplayed()));
    }

    @Test
    public void toolbarSuperiorSeVe(){
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewTitulSeVe(){
        onView(withId(R.id.textView15)).check(matches(isDisplayed()));
    }

    @Test
    public void recyclerViewMateriasSeVe(){
        onView(withId(R.id.recyclerMostrar)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewTituloDiceLoCorrecto(){
        onView(withId(R.id.textView15)).check(matches(withText("Horario")));
    }
}