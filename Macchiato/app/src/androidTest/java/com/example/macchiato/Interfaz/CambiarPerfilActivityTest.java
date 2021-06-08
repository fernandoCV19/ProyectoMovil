package com.example.macchiato.Interfaz;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.macchiato.CambiarPerfilActivity;
import com.example.macchiato.HistorialAcademicoActivity;
import com.example.macchiato.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import static com.google.common.truth.Truth.assertThat;

@RunWith(AndroidJUnit4ClassRunner.class)
public class CambiarPerfilActivityTest {

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(CambiarPerfilActivity.class);

    @Test
    public void activitySePuedeCrearYVer(){
        onView(withId(R.id.cambiarDePerfilActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void linearLayoutSePuedeVer(){
        onView(withId(R.id.linear_layout_cambiar_perfil)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewContrasenaNuevaSePuedeVer(){
        onView(withId(R.id.textView11)).check(matches(isDisplayed()));
    }

    @Test
    public void editTextContrasenaNuevaSePuedeVer(){
        onView(withId(R.id.cc_contrNueva_id)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewRepetirContrasenaSePuedeVer(){
        onView(withId(R.id.textView12)).check(matches(isDisplayed()));
    }

    @Test
    public void editTextRepetirContrasenaSePuedeVer(){
        onView(withId(R.id.cc_repetir_contrNueva_id)).check(matches(isDisplayed()));
    }

    @Test
    public void botonGuardarCambiosSePuedeVer(){
        onView(withId(R.id.button2)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewContrasenaNuevaDiceLoCorrecto(){
        onView(withId(R.id.textView11)).check(matches(withText("Contraseña Nueva:")));
    }

    @Test
    public void textViewRepetirContrasenaDiceLoCorrecto(){
        onView(withId(R.id.textView12)).check(matches(withText("Repetir Contraseña Nueva:")));
    }

    @Test
    public void botonGuardarCambiosDiceLoCorrecto(){
        onView(withId(R.id.button2)).check(matches(withText("LISTO")));
    }

    @Test
    public void camposDeTextoSonLlenables(){
        onView(withId(R.id.cc_contrNueva_id)).check(matches(isFocusable()));
        onView(withId(R.id.cc_repetir_contrNueva_id)).check(matches(isFocusable()));
    }

    @Test
    public void botonEsClicable(){
        onView(withId(R.id.button2)).check(matches(isClickable()));
    }
}