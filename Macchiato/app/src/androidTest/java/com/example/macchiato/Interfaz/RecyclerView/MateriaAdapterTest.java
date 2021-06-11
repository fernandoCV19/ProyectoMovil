package com.example.macchiato.Interfaz.RecyclerView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

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
public class MateriaAdapterTest {

    @Before
    public void setUp(){
        ActivityScenario.launch(Navigation_bottom.class);
        onView(withId(R.id.nav_materias)).perform(click());
    }

    @Test
    public void materiaUnoSeCreaYSeMuestra(){
        onView(withText("INGLES I")).check(matches(isDisplayed()));
        onView(withText("1803001")).check(matches(isDisplayed()));
    }

    @Test
    public void materiaDosSeCreaYSeMuestra(){
        onView(withText("FISICA GENERAL")).check(matches(isDisplayed()));
        onView(withText("2006063")).check(matches(isDisplayed()));
    }

    @Test
    public void materiaTresSeCreaYSeMuestra(){
        onView(withText("ALGEBRA I")).check(matches(isDisplayed()));
        onView(withText("2008019")).check(matches(isDisplayed()));
    }


    @Test
    public void materiaCuatroSeCreaYSeMuestra(){
        onView(withText("CALCULO I")).check(matches(isDisplayed()));
        onView(withText("2008054")).check(matches(isDisplayed()));
    }

    @Test
    public void materiaCincoSeCreaYSeMuestra(){
        onView(withId(R.id.listRecyclerview)).perform(swipeUp());
        onView(withText("INTRODUCCION A LA PROGRAMACION")).check(matches(isDisplayed()));
        onView(withText("2010010")).check(matches(isDisplayed()));
    }

    @Test
    public void nivelAMuestraSusMaterias(){
        onView(withId(R.id.listRecyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void nivelBMuestraSusMaterias(){
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Nivel B")).perform(click());
        onView(withId(R.id.listRecyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void nivelCMuestraSusMaterias(){
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Nivel C")).perform(click());
        onView(withId(R.id.listRecyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void nivelDMuestraSusMaterias(){
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Nivel D")).perform(click());
        onView(withId(R.id.listRecyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void nivelEMuestraSusMaterias(){
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Nivel E")).perform(click());
        onView(withId(R.id.listRecyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void nivelFMuestraSusMaterias(){
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Nivel F")).perform(click());
        onView(withId(R.id.listRecyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void nivelGMuestraSusMaterias(){
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Nivel G")).perform(click());
        onView(withId(R.id.listRecyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void nivelHMuestraSusMaterias(){
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Nivel H")).perform(click());
        onView(withId(R.id.listRecyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void nivelIMuestraSusMaterias(){
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Nivel I")).perform(click());
        onView(withId(R.id.listRecyclerview)).check(matches(hasMinimumChildCount(1)));
    }
}