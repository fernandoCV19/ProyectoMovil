package com.example.macchiato.Interfaz;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.macchiato.CambiarPerfilActivity;
import com.example.macchiato.Navigation_bottom;
import com.example.macchiato.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class Navigation_bottomTest {

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(Navigation_bottom.class);

    @Test
    public void activitySePuedeCrearYVer(){
        onView(withId(R.id.activityNavigationBottom)).check(matches(isDisplayed()));
    }

    @Test
    public void frameLayoutPrincipalSePuedeVer(){
        onView(withId(R.id.container)).check(matches(isDisplayed()));
    }

    @Test
    public void bottomNavigationSePuedeVer(){
        onView(withId(R.id.bottomNavigationView)).check(matches(isDisplayed()));
    }

    @Test
    public void iconoHorarioSePuedeVer(){
        onView(withId(R.id.nav_horario)).check(matches(isDisplayed()));
    }

    @Test
    public void iconoMateriasSePuedeVer(){
        onView(withId(R.id.nav_materias)).check(matches(isDisplayed()));
    }

    @Test
    public void iconoAjustesSePuedeVer(){
        onView(withId(R.id.nav_ajustes)).check(matches(isDisplayed()));
    }

    @Test
    public void iconoPerfilSePuedeVer(){
        onView(withId(R.id.nav_perfil)).check(matches(isDisplayed()));
    }

    @Test
    public void iconosSonClicables(){
        onView(withId(R.id.nav_horario)).check(matches(isClickable()));
        onView(withId(R.id.nav_perfil)).check(matches(isClickable()));
        onView(withId(R.id.nav_ajustes)).check(matches(isClickable()));
        onView(withId(R.id.nav_materias)).check(matches(isClickable()));
    }
}