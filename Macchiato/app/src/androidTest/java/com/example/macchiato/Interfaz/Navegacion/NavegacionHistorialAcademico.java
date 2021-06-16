package com.example.macchiato.Interfaz.Navegacion;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Root;
import androidx.test.espresso.action.ViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.macchiato.Navigation_bottom;
import com.example.macchiato.R;
import com.google.firebase.storage.internal.SmartHandler;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class NavegacionHistorialAcademico {

    @BeforeClass
    public static void setUpClass() throws InterruptedException {
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("test@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("12345678"));
        onView(withId(R.id.button)).perform(click());
        Thread.sleep(4000);
    }

    @Before
    public void setUp(){
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_perfil)).perform(click());

    }

    @Test
    public void desdeMenuHaciaHistorialAcademico(){
        onView(withId(R.id.buttonHistorial)).perform(click());
        onView(withId(R.id.historialAcademicoActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void desdeHistorialAcademicoHaciaMenu(){
        onView(withId(R.id.buttonHistorial)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.fragmentSesionIniciada)).check(matches(isDisplayed()));
    }

    @Test
    public void desdeHistorialAcademicoHaciaDialogParaAnadirMaterias(){
        onView(withId(R.id.buttonHistorial)).perform(click());
        onView(withId(R.id.aniadir_floating)).perform(click());
        onView(withId(R.id.aniadir_floating)).perform(click());
        onView(withId(R.id.dialogAnadirMateria)).check(matches(isDisplayed()));
    }

    @Test
    public void desdeDialogParaAnadirMateriaHaciaVentanaHistorialAcademico(){
        onView(withId(R.id.buttonHistorial)).perform(click());
        onView(withId(R.id.aniadir_floating)).perform(click());
        onView(withId(R.id.aniadir_floating)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.historialAcademicoActivity)).check(matches(isDisplayed()));
    }

    @AfterClass
    public static void tearDownClass(){
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
    }
}
