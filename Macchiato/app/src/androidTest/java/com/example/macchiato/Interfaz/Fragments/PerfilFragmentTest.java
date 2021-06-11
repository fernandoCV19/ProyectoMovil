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
public class PerfilFragmentTest {

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(Navigation_bottom.class);

    @Before
    public void setup(){
        onView(withId(R.id.nav_perfil)).perform(click());
    }

    @Test
    public void fragmentSeCreaYSeVe(){
        onView(withId(R.id.perfilSinIniciarFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void toolbarPrincipalSeVe(){
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewTituloSeVe(){
        onView(withId(R.id.textView15)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewMensajeSeVe(){
        onView(withId(R.id.textView18)).check(matches(isDisplayed()));
    }

    @Test
    public void imageViewSeVe(){
        onView(withId(R.id.imageView3)).check(matches(isDisplayed()));
    }

    @Test
    public void buttonIniciarSesionSeVe(){
        onView(withId(R.id.buttonIniciarSesion)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewTituloTieneElTextoCorrecto(){
        onView(withId(R.id.textView15)).check(matches(withText("Perfil")));
    }

    @Test
    public void textViewMensajeTieneElTextoCorrecto(){
        onView(withId(R.id.textView18)).check(matches(withText("Para guardar sus datos, inicie sesion")));
    }

    @Test
    public void buttonIniciarSesionTieneElTextoCorrecto(){
        onView(withId(R.id.buttonIniciarSesion)).check(matches(withText(R.string.textIniciar_sesion)));
    }
}