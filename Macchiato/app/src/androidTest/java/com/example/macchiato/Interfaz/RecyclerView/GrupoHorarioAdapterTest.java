package com.example.macchiato.Interfaz.RecyclerView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import static org.junit.Assert.*;

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
public class GrupoHorarioAdapterTest {

    @Before
    public void setup(){
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.spinnerNivel)).perform(click());
        onView(withText("A")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withText("INGLES I")).perform(click());
    }

    @Test
    public void primerGrupoSeCrea(){
        onView(withText("1 - CESPEDES GUIZADA MARIA BENITA")).check(matches(isDisplayed()));
    }

    @Test
    public void segundoGrupoSeCrea(){
        onView(withText("2 - CESPEDES GUIZADA MARIA BENITA")).check(matches(isDisplayed()));
    }

    @Test
    public void tercerGrupoSeCrea(){
        onView(withText("3 - PEETERS ILONAA MAGDA LENA")).check(matches(isDisplayed()));
    }

    @Test
    public void cuartoGrupoSeCrea(){
        onView(withText("4 - GRILO SALVATIERRA MARIA ESTELA")).check(matches(isDisplayed()));
    }

    @Test
    public void quintoGrupoSeCrea(){
        onView(withText("5 - CESPEDES GUIZADA MARIA BENITA")).check(matches(isDisplayed()));
    }

    @Test
    public void gruposSonClicables(){
        onView(withText("1 - CESPEDES GUIZADA MARIA BENITA")).check(matches(isClickable()));
        onView(withText("2 - CESPEDES GUIZADA MARIA BENITA")).check(matches(isClickable()));
        onView(withText("3 - PEETERS ILONAA MAGDA LENA")).check(matches(isClickable()));
        onView(withText("4 - GRILO SALVATIERRA MARIA ESTELA")).check(matches(isClickable()));
        onView(withText("5 - CESPEDES GUIZADA MARIA BENITA")).check(matches(isClickable()));
    }
}