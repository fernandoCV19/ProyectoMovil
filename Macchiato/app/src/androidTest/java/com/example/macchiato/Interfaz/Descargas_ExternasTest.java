package com.example.macchiato.Interfaz;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.macchiato.Descargas_Externas;
import com.example.macchiato.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

public class Descargas_ExternasTest {

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(Descargas_Externas.class);

    @Test
    public void activitySePuedeCrearYVer(){
        onView(withId(R.id.descargaCronogramaActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void botonSePuedeVer(){
        onView(withId(R.id.button3)).check(matches(isDisplayed()));
    }

    @Test
    public void botonDiceLoCorrecto(){
        onView(withId(R.id.button3)).check(matches(withText("DESCARGAR")));
    }

    @Test
    public void botonSePuedeClicar(){
        onView(withId(R.id.button3)).check(matches(isClickable()));
    }
}