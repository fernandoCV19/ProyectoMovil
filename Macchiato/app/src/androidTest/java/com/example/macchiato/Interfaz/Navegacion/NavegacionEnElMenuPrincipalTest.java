package com.example.macchiato.Interfaz.Navegacion;

import androidx.test.core.app.ActivityScenario;
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
public class NavegacionEnElMenuPrincipalTest {

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(Navigation_bottom.class);

    @Test
    public void desdeVentanaDeHorarioHaciaVentanaDeInformacionDeMaterias(){
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.nav_materias)).perform(click());
        onView(withId(R.id.fragmentMaterias)).check(matches(isDisplayed()));
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.mostrarHorarioFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void desdeVentanaDeHorarioHaciaVentanaDeAjustes(){
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.nav_ajustes)).perform(click());
        onView(withId(R.id.fragmentAjustes)).check(matches(isDisplayed()));
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.mostrarHorarioFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void desdeVentanaDeHorarioHaciaPerfil(){
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.perfilSinIniciarFragment)).check(matches(isDisplayed()));
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.mostrarHorarioFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void desdeVentanaDeInformacionDeMateriasHaciaAjustes(){
        onView(withId(R.id.nav_materias)).perform(click());
        onView(withId(R.id.nav_ajustes)).perform(click());
        onView(withId(R.id.fragmentAjustes)).check(matches(isDisplayed()));
        onView(withId(R.id.nav_materias)).perform(click());
        onView(withId(R.id.fragmentMaterias)).check(matches(isDisplayed()));
    }

    @Test
    public void desdeVentanaDeInformacionDeMateriasHaciaPerfil(){
        onView(withId(R.id.nav_materias)).perform(click());
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.perfilSinIniciarFragment)).check(matches(isDisplayed()));
        onView(withId(R.id.nav_materias)).perform(click());
        onView(withId(R.id.fragmentMaterias)).check(matches(isDisplayed()));
    }

    @Test
    public void desdeVentanaDeAjustesHaciaVentanaDePerfil(){
        onView(withId(R.id.nav_ajustes)).perform(click());
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.perfilSinIniciarFragment)).check(matches(isDisplayed()));
        onView(withId(R.id.nav_ajustes)).perform(click());
        onView(withId(R.id.fragmentAjustes)).check(matches(isDisplayed()));
    }
}