package com.example.macchiato.Interfaz.RecyclerView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.runner.RunWith;

import com.example.macchiato.Navigation_bottom;
import com.example.macchiato.R;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.configuration.injection.filter.NameBasedCandidateFilter;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;

public class InfoGrupoAdapterTest {
    @Before
    public void setUp(){
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_materias)).perform(click());
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Nivel A")).perform(click());
        onView(allOf(withId(R.id.botonDetalles), withParent(withParent(withChild(withText("FISICA GENERAL")))))).perform(click());
    }
    @BeforeClass
    public static  void setupClass(){
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_materias)).perform(click());
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Nivel A")).perform(click());
        onView(allOf(withId(R.id.botonDetalles), withParent(withParent(withChild(withText("FISICA GENERAL")))))).perform(click());
        }

    @AfterClass
    public static void tearDownClass(){
        ActivityScenario.launch(Navigation_bottom.class);
        onView(isRoot()).perform(ViewActions.pressBack());

    }
    @Test
    public void primeraMateriaNombreSeCreaYseVe(){
        onView(withId(R.id.numGrupoInfo)).check(matches(isDisplayed()));

    }
    @Test
    public void segundaMateriaNombreSeCreaYseVe(){
        onView(withId(R.id.nomDocenteInfo)).check(matches(isDisplayed()));
    }
    @Test
    public void primerMateriaTieneLaCantidadDeClasesCorrecta(){
        onView(allOf(withId(R.id.horariosInfo),hasSibling(withText("VALENZUELA MIRANDA ROBERTO")))).check(matches(hasChildCount(2)));
    }



}



