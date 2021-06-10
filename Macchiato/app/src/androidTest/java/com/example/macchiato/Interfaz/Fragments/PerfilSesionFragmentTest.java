package com.example.macchiato.Interfaz.Fragments;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.macchiato.Navigation_bottom;
import com.example.macchiato.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class PerfilSesionFragmentTest {

    private static boolean sesionIniciada = false;
    private static int contador = 21;

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(Navigation_bottom.class);


    @Before
    public void setUp() throws InterruptedException {
        if(!sesionIniciada) {
            onView(withId(R.id.nav_perfil)).perform(click());
            onView(withId(R.id.buttonIniciarSesion)).perform(click());
            onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("test@gmail.com"));
            onView(withId(R.id.editTextTextPassword)).perform(typeText("12345678"));
            onView(withId(R.id.button)).perform(click());
            Thread.sleep(4000);

            sesionIniciada = true;
        }
        onView(withId(R.id.nav_perfil)).perform(click());
    }

    @After
    public void tearDown(){
        contador--;
        if(contador < 1){
            onView(withId(R.id.buttonIniciarSesion)).perform(click());
        }
    }

    @Test
    public void fragmentEsCreadoYSePuedeVer(){
        onView(withId(R.id.fragmentSesionIniciada)).check(matches(isDisplayed()));
    }

    @Test
    public void toolbarSuperiorSePuedeVer(){
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewTituloSePuedeVer(){
        onView(withId(R.id.textView15)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewNombreActualSePuedeVer() {
        onView(withId(R.id.nombreActual_id)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewNombreDeUsuarioSePuedeVer(){
        onView(withId(R.id.usuarioActual_id)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewCorreoActualSePuedeVer(){
        onView(withId(R.id.textView17)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewCorreoElectronicoSePuedeVer(){
        onView(withId(R.id.correoActual_id)).check(matches(isDisplayed()));
    }

    @Test
    public void buttonCerrarSesionSePuedeVer(){
        onView(withId(R.id.buttonIniciarSesion)).check(matches(isDisplayed()));
    }

    @Test
    public void buttonDescargarSePuedeVer(){
        onView(withId(R.id.id_descargas)).check(matches(isDisplayed()));
    }

    @Test
    public void buttonCambiarContrasenaSePuedeVer(){
        onView(withId(R.id.buttonEditar)).check(matches(isDisplayed()));
    }

    @Test
    public void historialAcademicoSePuedeVer(){
        onView(withId(R.id.buttonHistorial)).check(matches(isDisplayed()));
    }

    @Test
    public void textViewTituloTieneElTextoCorrecto(){
        onView(withId(R.id.textView15)).check(matches(withText("Perfil")));
    }

    @Test
    public void textViewUsuarioTieneElTextoCorrecto(){
        onView(withId(R.id.nombreActual_id)).check(matches(withText("Usuario:")));
    }

    @Test
    public void textViewNombreDeUsuarioTieneElTextoCorrecto(){
        onView(withId(R.id.usuarioActual_id)).check(matches(withText("Test")));
    }

    @Test
    public void textViewCorreoTieneElTextoCorrecto(){
        onView(withId(R.id.textView17)).check(matches(withText("Correo Electrónico:")));
    }

    @Test
    public void textViewCorreoDeUsuarioTieneElTextoCorrecto(){
        onView(withId(R.id.correoActual_id)).check(matches(withText("test@gmail.com")));
    }

    @Test
    public void buttonCerrarSesionTieneElTextoCorrecto(){
        onView(withId(R.id.buttonIniciarSesion)).check(matches(withText("CERRAR SESIÓN")));
    }

    @Test
    public void buttonDescargarTieneElTextoCorrecto(){
        onView(withId(R.id.id_descargas)).check(matches(withText("DESC")));
    }

    @Test
    public void buttonCambiarContrasenaTieneElTextoCorrecto(){
        onView(withId(R.id.buttonEditar)).check(matches(withText("CAMBIAR CONTRASEÑA")));
    }

    @Test
    public void buttonHistorialAcademicoTieneElTextoCorrecto(){
        onView(withId(R.id.buttonHistorial)).check(matches(withText("HISTORIAL ACADEMICO")));
    }

    @Test
    public void botonesSonClicables(){
        onView(withId(R.id.buttonIniciarSesion)).check(matches(isClickable()));
        onView(withId(R.id.id_descargas)).check(matches(isClickable()));
        onView(withId(R.id.buttonEditar)).check(matches(isClickable()));
        onView(withId(R.id.buttonHistorial)).check(matches(isClickable()));
    }
}