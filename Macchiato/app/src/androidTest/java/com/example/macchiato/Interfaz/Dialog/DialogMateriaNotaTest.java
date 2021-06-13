package com.example.macchiato.Interfaz.Dialog;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.runner.RunWith;

import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

import com.example.macchiato.HistorialAcademicoActivity;
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
    public class DialogMateriaNotaTest {
        @Before
        public void setUp() {
            ActivityScenario.launch(HistorialAcademicoActivity.class);
            onView(withId(R.id.añadir_floating)).perform(click());
            onView(withId(R.id.añadir_floating)).perform(click());

        }

        @BeforeClass
        public static void setupClass() throws InterruptedException {
            ActivityScenario.launch(HistorialAcademicoActivity.class);
            onView(withId(R.id.añadir_floating)).perform(click());
            onView(withId(R.id.añadir_floating)).perform(click());

        }


        @Test
        public void textViewNivelSeVe() {
            onView(withId(R.id.promedio_nota)).check(matches(isDisplayed()));
        }
        @Test
        public void textViewMateriaSeVe() {
            onView(withId(R.id.materia_vista)).check(matches(isDisplayed()));
        }
        @Test
        public void textViewNotaSeVe() {
            onView(withId(R.id.materia_vista)).check(matches(isDisplayed()));
        }

        @Test
        public void EditTextNotaSeVe() {
            onView(withId(R.id.editText)).check(matches(isDisplayed()));
        }
        @Test
        public void botonSpinerNivelSePuedenClicar(){
            onView(withId(R.id.nivel)).check(matches(isClickable()));
        }

        @Test
        public void botoneSpinnerMateriaPuedenClicar(){
            onView(withId(R.id.materia)).check(matches(isClickable()));
        }
        @Test
        public void botonAñadirSeVe() {
            onView(withText("AÑADIR")).check(matches(isDisplayed()));
        }
        @Test
        public void botonCancelarSeVe() {
            onView(withText("CANCELAR")).check(matches(isDisplayed()));
        }
        @Test
        public void botonAñadirPuedenClicar() {
            onView(withText("AÑADIR")).check(matches(isClickable()));
        }
        @Test
        public void botonCancelarPuedenClicar (){
            onView(withText("CANCELAR")).check(matches(isClickable()));
        }
        @Test
        public void SpinnerNivelTieneAlmenosUnElemento (){
            onView(withId(R.id.nivel)).check(matches(hasMinimumChildCount(1)));

        }
        @Test
        public void SpinnerMateriaTieneAlmenosUnElemento (){
            onView(withId(R.id.nivel)).perform(click());
            onView(withText("A")).inRoot(isPlatformPopup()).perform(click());
            onView(withId(R.id.materia)).check(matches(hasMinimumChildCount(1)));

        }
    }