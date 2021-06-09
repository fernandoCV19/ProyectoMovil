package com.example.macchiato.Interfaz.Fragments;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

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
public class MateriaFragmentTest {

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(Navigation_bottom.class);

    @Before
    public void setup(){
        onView(withId(R.id.nav_materias)).perform(click());
    }

    @Test
    public void fragmentSeCreaYSeMuestra(){
        onView(withId(R.id.fragmentMaterias)).check(matches(isDisplayed()));
    }

    @Test
    public void toolBarSuperiorSeVe(){
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewTituloSeVe(){
        onView(withId(R.id.textView15)).check(matches(isDisplayed()));
    }

    @Test
    public void recyclerViewSeVe(){
        onView(withId(R.id.listRecyclerview)).check(matches(isDisplayed()));
    }

    @Test
    public void menuSuperiorYSusOpcionesSeVe(){
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Nivel A")).check(matches(isDisplayed()));
        onView(withText("Nivel B")).check(matches(isDisplayed()));
        onView(withText("Nivel C")).check(matches(isDisplayed()));
        onView(withText("Nivel D")).check(matches(isDisplayed()));
        onView(withText("Nivel E")).check(matches(isDisplayed()));
        onView(withText("Nivel F")).check(matches(isDisplayed()));
        onView(withText("Nivel G")).check(matches(isDisplayed()));
        onView(withText("Nivel H")).check(matches(isDisplayed()));
        onView(withText("Nivel I")).check(matches(isDisplayed()));
    }

    @Test
    public void textViewTituloTieneElTextoCorrecto(){
        onView(withId(R.id.textView15)).check(matches(withText("Materias")));
    }
}