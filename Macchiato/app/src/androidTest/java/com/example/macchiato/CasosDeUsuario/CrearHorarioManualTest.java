package com.example.macchiato.CasosDeUsuario;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.macchiato.Navigation_bottom;
import com.example.macchiato.R;

import net.bytebuddy.matcher.ElementMatcher;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static net.bytebuddy.matcher.ElementMatchers.hasChild;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.iterableWithSize;

@RunWith(AndroidJUnit4ClassRunner.class)
public class CrearHorarioManualTest {

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(Navigation_bottom.class);

    @Before
    public void setUp(){
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
    }

    @After
    public void tearDown(){
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withId(R.id.eliminar_materias)).perform(click());
        onView(withSubstring("ACEPTAR")).perform(click());
    }

    @Test
    public void anadirUnaMateria(){
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withSubstring("PROBABILIDAD")).perform(click());
        onView(withSubstring("3")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.recyclerMostrar)).check(matches(hasChildCount(1)));
        onView(withSubstring("PRO")).check(matches(isDisplayed()));
    }

    @Test
    public void anadirDosGruposDeLaMismaMateria(){
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withSubstring("PROBABILIDAD")).perform(click());
        onView(withSubstring("3")).perform(click());
        onView(withSubstring("4")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.recyclerMostrar)).check(matches(hasChildCount(2)));
        onView(withSubstring("DELGA")).check(matches(isDisplayed()));
        onView(withSubstring("OMONTE")).check(matches(isDisplayed()));
    }

    @Test
    public void anadirDosGruposDeDiferentesMaterias(){
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withSubstring("PROBABILIDAD")).perform(click());
        onView(withSubstring("3")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withSubstring("TALLER")).perform(click());
        onView(withSubstring("1")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.recyclerMostrar)).check(matches(hasChildCount(2)));
        onView(withSubstring("PROBA")).check(matches(isDisplayed()));
        onView(withSubstring("TALLER")).check(matches(isDisplayed()));
    }

    @Test
    public void eliminaTodasLasMateriasSeleccionadas(){
        onView(withId(R.id.spinnerNivel)).perform(click());
        onView(withText("A")).perform(click());
        onView(withId(R.id.spinnerMateria)).perform(click());
        onView(withSubstring("INGLES")).perform(click());
        onView(withSubstring("1")).perform(click());
        onView(withSubstring("2")).perform(click());
        onView(withSubstring("3")).perform(click());
        onView(withSubstring("4")).perform(click());
        onView(withSubstring("5")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.recyclerMostrar)).check(matches(hasMinimumChildCount(2)));
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withId(R.id.eliminar_materias)).perform(click());
        onView(withSubstring("ACEPTAR")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.recyclerMostrar)).check(matches(hasChildCount(0)));
    }
}
