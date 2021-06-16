package com.example.macchiato.CasosDeUsuario;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

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
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4ClassRunner.class)

public class IniciarSesionTest {
    @Before
    public void setUp() {
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_perfil)).perform(click());
    }

    @BeforeClass
    public static void setupClass() throws InterruptedException {
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_perfil)).perform(click());

    }

    @Test
    public void IniciarSesionCrearHorario () throws InterruptedException {
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("test@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("12345678"));
        onView(withId(R.id.button)).perform(click());
        Thread.sleep(4000);
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.spinnerNivel)).perform(click());
        onView(withText("A")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withText("INGLES I")).perform(click());
        onView(withText("1 - CESPEDES GUIZADA MARIA BENITA")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withText("INTRODUCCION A LA PROGRAMACION")).perform(click());
        onView(withText("1 - SALAZAR SERRUDO CARLA")).perform(click());
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withText("INGLES I")).check(matches(isDisplayed()));
        onView(withText("INTRODUCCION A LA PROGRAMACION")).check(matches(isDisplayed()));
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.spinnerNivel)).perform(click());
        onView(withText("A")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withText("INGLES I")).perform(click());
        onView(withText("1 - CESPEDES GUIZADA MARIA BENITA")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withText("INTRODUCCION A LA PROGRAMACION")).perform(click());
        onView(withText("1 - SALAZAR SERRUDO CARLA")).perform(click());


    }
    @Test
    public void IniciarSecionHistorialAcademico() throws InterruptedException {
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("test@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("12345678"));
        onView(withId(R.id.button)).perform(click());
        Thread.sleep(4000);
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonHistorial)).perform(click());
        onView(withId(R.id.añadir_floating)).perform(click());
        onView(withId(R.id.añadir_floating)).perform(click());
        onView(withId(R.id.nivel)).perform(click());
        onView(withText("A")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.materia)).perform(click());
        onView(withText("INGLES I")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.editText)).perform(ViewActions.typeText("100"));
        onView(withText("AÑADIR")).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("test@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("12345678"));
        onView(withId(R.id.button)).perform(click());
        Thread.sleep(4000);
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonHistorial)).perform(click());
        onView(withText("INGLES I")).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.eliminar),hasSibling(withText("INGLES I")))).perform(click());
        onView(withId(R.id.floating_eliminar)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());


    }

}
