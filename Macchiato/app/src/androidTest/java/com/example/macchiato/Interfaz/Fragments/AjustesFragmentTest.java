package com.example.macchiato.Interfaz.Fragments;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.macchiato.AjustesFragment;
import com.example.macchiato.Navigation_bottom;
import com.example.macchiato.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class AjustesFragmentTest {

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(Navigation_bottom.class);

    @Before
    public void setup(){
        onView(withId(R.id.nav_ajustes)).perform(click());
    }

    @Test
    public void elFragmenteSeCreaYSeMuestra(){
        onView(withId(R.id.fragmentAjustes)).check(matches(isDisplayed()));
    }

    @Test
    public void toolBarSuperiorSeMuestra(){
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewTituloSeVe(){
        onView(withId(R.id.textView15)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewNotificacionSeVe(){
        onView(withId(R.id.textView8)).check(matches(isDisplayed()));
    }

    @Test
    public void switchSeVe(){
        onView(withId(R.id.switch2)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewAlarmaseSeVe(){
        onView(withId(R.id.textView6)).check(matches(isDisplayed()));
    }

    @Test
    public void recyclerViewSePuedeVer(){
        onView(withId(R.id.recyclerAlarmas)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewTituloTieneElTextoCorrecto(){
        onView(withId(R.id.textView15)).check(matches(withText("Ajustes")));
    }

    @Test
    public void textViewNotificacionesTieneElTextoCorrecto(){
        onView(withId(R.id.textView8)).check(matches(withText("Notificaciones")));
    }

    @Test
    public void textViewAlarmasTieneElTextoCorrecto(){
        onView(withId(R.id.textView6)).check(matches(withText("Alarmas")));
    }

    @Test
    public void switchEsClicable(){
        onView(withId(R.id.switch2)).check(matches(isClickable()));
    }
}