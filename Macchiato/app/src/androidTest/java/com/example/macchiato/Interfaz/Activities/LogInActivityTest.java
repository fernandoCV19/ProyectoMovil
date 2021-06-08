package com.example.macchiato.Interfaz.Activities;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.macchiato.LogInActivity;
import com.example.macchiato.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class LogInActivityTest {

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(LogInActivity.class);

    @Test
    public void activitySePuedeCrearYVer(){
        onView(withId(R.id.logInActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void toolBarSuperiorSePuedeVer(){
        onView(withId(R.id.toolbarLogInActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewBienvenidoSePuedeVer(){
        onView(withId(R.id.textView15)).check(matches(isDisplayed()));
    }

    @Test
    public void linearLayoutPrincipalSePuedeVer(){
        onView(withId(R.id.linearLayoutLogInActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewUsuarioSePuedeVer(){
        onView(withId(R.id.textView)).check(matches(isDisplayed()));
    }

    @Test
    public void editTextNombreDeUsuarioSePuedeVer(){
        onView(withId(R.id.editTextTextEmailAddress)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewContrasenaSePuedeVer(){
        onView(withId(R.id.textView2)).check(matches(isDisplayed()));
    }

    @Test
    public void editTextContrasenaSePuedeVer(){
        onView(withId(R.id.editTextTextPassword)).check(matches(isDisplayed()));
    }

    @Test
    public void botonIniciarSesionSePuedeVer(){
        onView(withId(R.id.button)).check(matches(isDisplayed()));
    }

    @Test
    public void botonRegistrarseSePuedeVer(){
        onView(withId(R.id.button3)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewOlvideContrasenaSePuedeVer(){
        onView(withId(R.id.cont_olvidada_id)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewBienvenidoTieneElTextoCorrecto(){
        onView(withId(R.id.textView15)).check(matches(withText("Bienvenido")));
    }

    @Test
    public void textViewUsuarioTieneElTextoCorrecto(){
        onView(withId(R.id.textView)).check(matches(withText("Usuario:")));
    }

    @Test
    public void textViewContrasenaTieneElTextoCorrecto(){
        onView(withId(R.id.textView2)).check(matches(withText("Contraseña:")));
    }

    @Test
    public void botonIniciarSesionTieneElTextoCorrecto(){
        onView(withId(R.id.button)).check(matches(withText("INICIAR SESIÓN")));
    }

    @Test
    public void botonRegistrarseTieneElTextoCorrecto(){
        onView(withId(R.id.button3)).check(matches(withText("REGISTRARSE")));
    }

    @Test
    public void textViewContrasenaOlvidadTieneElTextoCorrecto(){
        onView(withId(R.id.cont_olvidada_id)).check(matches(withText("Olvide mi contraseña")));
    }

    @Test
    public void camposDeTextoSonEditables(){
        onView(withId(R.id.editTextTextEmailAddress)).check(matches(isFocusable()));
        onView(withId(R.id.editTextTextPassword)).check(matches(isFocusable()));
    }

    @Test
    public void botonesSonClicables(){
        onView(withId(R.id.button)).check(matches(isClickable()));
        onView(withId(R.id.button3)).check(matches(isClickable()));
    }
}