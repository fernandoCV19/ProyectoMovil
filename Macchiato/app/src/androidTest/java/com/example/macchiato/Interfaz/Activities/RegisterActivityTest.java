package com.example.macchiato.Interfaz.Activities;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.macchiato.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class RegisterActivityTest {

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(RegisterActivity.class);

    @Test
    public void activitySePuedeCrearYVer(){
        onView(withId(R.id.registerActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void linearLayoutPrincipalSePuedeVer(){
        onView(withId(R.id.linearLayoutRegisterActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewUsuarioSePuedeVer(){
        onView(withId(R.id.textView3)).check(matches(isDisplayed()));
    }

    @Test
    public void editTextUsuarioSePuedeVer(){
        onView(withId(R.id.editTextTextPersonName)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewContrasenaSePuedeVer(){
        onView(withId(R.id.textView4)).check(matches(isDisplayed()));
    }

    @Test
    public void editTextContrasenaSePuedeVer(){
        onView(withId(R.id.editTextTextPassword2)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewRepetirContrasenaSePuedeVer(){
        onView(withId(R.id.textView5)).check(matches(isDisplayed()));
    }

    @Test
    public void editTextRepetirContrasenaSePuedeVer(){
        onView(withId(R.id.editTextTextPassword3)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewEmailSePuedeVer(){
        onView(withId(R.id.textView7)).check(matches(isDisplayed()));
    }

    @Test
    public void editTextEmailSePuedeVer(){
        onView(withId(R.id.editTextTextEmailAddress2)).check(matches(isDisplayed()));
    }

    @Test
    public void botonSePuedeVer(){
        onView(withId(R.id.register_btn)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewUsuarioTieneElTextoCorrecto(){
        onView(withId(R.id.textView3)).check(matches(withText("Usuario:")));
    }

    @Test
    public void textViewContrasenaTieneElTextoCorrecto(){
        onView(withId(R.id.textView4)).check(matches(withText("Contraseña:")));
    }

    @Test
    public void textViewRepetirContrasenaTieneElTextoCorrecto(){
        onView(withId(R.id.textView5)).check(matches(withText("Repetir Contraseña:")));
    }

    @Test
    public void textViewEmailTieneElTextoCorrecto(){
        onView(withId(R.id.textView7)).check(matches(withText("Email:")));
    }

    @Test
    public void botonTieneElTextoCorrecot(){
        onView(withId(R.id.register_btn)).check(matches(withText("LISTO")));
    }

    @Test
    public void camposDeTextoSonEditables(){
        onView(withId(R.id.editTextTextPersonName)).check(matches(isFocusable()));
        onView(withId(R.id.editTextTextPassword2)).check(matches(isFocusable()));
        onView(withId(R.id.editTextTextPassword3)).check(matches(isFocusable()));
        onView(withId(R.id.editTextTextEmailAddress2)).check(matches(isFocusable()));
    }

    @Test
    public void botonEsClicable(){
        onView(withId(R.id.register_btn)).check(matches(isClickable()));
    }
}