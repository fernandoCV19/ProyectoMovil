package com.example.macchiato.Interfaz.Adapter;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static org.hamcrest.Matchers.allOf;

import com.example.macchiato.Interfaz.Activities.Navigation_bottom;
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

public class MateriaNotaAdapterTest {
    @Before
    public void setUp(){
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonHistorial)).perform(click());
    }
    @BeforeClass
    public static  void setupClass() throws InterruptedException {
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("test@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("12345678"));
        onView(withId(R.id.button)).perform(click());
        Thread.sleep(4000);
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonHistorial)).perform(click());
        onView(withId(R.id.aniadir_floating)).perform(click());
        onView(withId(R.id.aniadir_floating)).perform(click());
        onView(withId(R.id.nivel)).perform(click());
        onView(withText("A")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.materia)).perform(click());
        onView(withText("INGLES I")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.editText)).perform(ViewActions.typeText("100"));
        onView(withText("AniADIR")).perform(click());
        onView(withId(R.id.aniadir_floating)).perform(click());
        onView(withId(R.id.nivel)).perform(click());
        onView(withText("A")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.materia)).perform(click());
        onView(withText("ALGEBRA I")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.editText)).perform(ViewActions.typeText("80"));
        onView(withText("AÑADIR")).perform(click());
        onView(withId(R.id.aniadir_floating)).perform(click());
        onView(withId(R.id.nivel)).perform(click());
        onView(withText("A")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.materia)).perform(click());
        onView(withText("FISICA GENERAL")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.editText)).perform(ViewActions.typeText("60"));
        onView(withText("AÑADIR")).perform(click());

    }
    @AfterClass
    public static  void tearDownClass() {
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonHistorial)).perform(click());
        onView(allOf(withId(R.id.eliminar),hasSibling(withText("ALGEBRA I")))).perform(click());
        onView(withId(R.id.floating_eliminar)).perform(click());
        onView(allOf(withId(R.id.eliminar),hasSibling(withText("INGLES I")))).perform(click());
        onView(withId(R.id.floating_eliminar)).perform(click());
        onView(allOf(withId(R.id.eliminar),hasSibling(withText("FISICA GENERAL")))).perform(click());
        onView(withId(R.id.floating_eliminar)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
    }

    @Test
    public void primerMateriaNombreSeCreaYseVe() {
        onView(withText("INGLES I")).check(matches(isDisplayed()));
    }
    @Test
    public void primerNotaSeCreaYseVe() {
    onView(withText("100")).check(matches(isDisplayed()));
}
    @Test
    public void segundMateriaNombreSeCreaYseVe(){
        onView(withText("ALGEBRA I")).check(matches(isDisplayed()));
    }
    @Test
    public void segundaNotaSeCreaYseVe(){
        onView(withText("80")).check(matches(isDisplayed()));
    }
    @Test
    public void tercerMateriaNombreSeCreaYseVe(){
        onView(withText("FISICA GENERAL")).check(matches(isDisplayed()));
    }
    @Test
    public void terceraMaterianotaSeCreaYseVe(){
        onView(withText("60")).check(matches(isDisplayed()));
    }
    @Test
    public void primerCheckBoxEsClickeable(){
        onView(allOf(withId(R.id.eliminar),hasSibling(withText("INGLES I")))).check(matches(isClickable()));
    }
    @Test
    public void segundoCheckBoxEsClickeable(){
        onView(allOf(withId(R.id.eliminar),hasSibling(withText("ALGEBRA I")))).check(matches(isClickable()));
    }
    @Test
    public void tercerCheckBoxEsClickeable(){
        onView(allOf(withId(R.id.eliminar),hasSibling(withText("FISICA GENERAL")))).check(matches(isClickable()));

    }
    @Test
    public void cantidadElementosRecyclerView(){
        onView(withId(R.id.lista_MateriasAprobadas)).check(matches(hasChildCount(3)));
    }
    }


