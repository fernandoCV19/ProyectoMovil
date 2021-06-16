package com.example.macchiato.Interfaz.Navegacion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.macchiato.Interfaz.Activities.Navigation_bottom;
import com.example.macchiato.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class NavegacionEnLaVentanaDeInicioDeSesion {

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(Navigation_bottom.class);

    @Test
    public void deVentanaSinInicarSesionAIniciarSesion() {
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
        onView(withId(R.id.logInActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void deVentanaIniciarSesionRetrocederASinIniciarSesion(){
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
        onView(withId(R.id.logInActivity)).check(matches(isDisplayed()));
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.perfilSinIniciarFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void deVentanaDeInicioDeSesionARegistrarse(){
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.registerActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void deRegistrarseAVentnaDeInicioDeSesion(){
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.registerActivity)).check(matches(isDisplayed()));
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.logInActivity)).check(matches(isDisplayed()));
    }
}
