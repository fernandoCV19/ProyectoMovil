package com.example.macchiato.CasosDeUsuario;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.macchiato.HistorialAcademicoActivity;
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
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(AndroidJUnit4ClassRunner.class)
public class CrearHorarioAutomaticoTest{

    private boolean sesionIniciada = false;

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(Navigation_bottom.class);

    @After
    public void tearDown(){
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withId(R.id.eliminar_materias)).perform(click());
        onView(withText("ACEPTAR")).perform(click());
        if(sesionIniciada){
            onView(withId(R.id.nav_perfil)).perform(click());
            onView(withId(R.id.buttonIniciarSesion)).perform(click());
        }
    }

    @Test
    public void crearHorarioAutomaticoSinLoggearteParaUnaSesionSinMateriasAprobadas(){
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withText("ACEPTAR")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.recyclerMostrar)).check(matches(hasMinimumChildCount(2)));
    }

    @Test
    public void elHorarioSeMantieneMientrasNavegamosEntreVentanas(){
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withText("ACEPTAR")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.nav_materias)).perform(click());
        onView(withId(R.id.nav_ajustes)).perform(click());
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.recyclerMostrar)).check(matches(hasMinimumChildCount(2)));
    }

    @Test
    public void crearHorarioAutomaticoLoggeadoParaUnaSesionSinMateriasAprobadas() throws InterruptedException {
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("test@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("12345678"));
        onView(withId(R.id.button)).perform(click());
        Thread.sleep(4000);
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withText("ACEPTAR")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.recyclerMostrar)).check(matches(hasMinimumChildCount(2)));
        sesionIniciada = true;
    }

    @Test
    public void elHorarioSeMantieneMientrasNavegamosEntreVentanasLoggeadoParaUnaSesionSinMateriasAprobadas() throws InterruptedException {
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("test@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("12345678"));
        onView(withId(R.id.button)).perform(click());
        Thread.sleep(4000);
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withText("ACEPTAR")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.nav_materias)).perform(click());
        onView(withId(R.id.nav_ajustes)).perform(click());
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.recyclerMostrar)).check(matches(hasMinimumChildCount(2)));
        sesionIniciada = true;
    }

    @Test
    public void elHorarioSeMantieneAPesarDeCerrarSesion() throws InterruptedException {
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("test@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("12345678"));
        onView(withId(R.id.button)).perform(click());
        Thread.sleep(4000);
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withText("ACEPTAR")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.recyclerMostrar)).check(matches(hasMinimumChildCount(2)));
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("test@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("12345678"));
        onView(withId(R.id.button)).perform(click());
        Thread.sleep(4000);
        sesionIniciada = true;
    }

    @Test
    public void elHorarioSeActulizaAlIniciarSesion() throws InterruptedException {
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("test@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("12345678"));
        onView(withId(R.id.button)).perform(click());
        Thread.sleep(4000);
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withText("ACEPTAR")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.recyclerMostrar)).check(matches(hasMinimumChildCount(2)));
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withId(R.id.eliminar_materias)).perform(click());
        onView(withText("ACEPTAR")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.recyclerMostrar)).check(matches(hasMinimumChildCount(0)));
        onView(withId(R.id.nav_perfil)).perform(click());
        onView(withId(R.id.buttonIniciarSesion)).perform(click());
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("test@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("12345678"));
        onView(withId(R.id.button)).perform(click());
        Thread.sleep(4000);
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.recyclerMostrar)).check(matches(hasMinimumChildCount(2)));
        sesionIniciada = true;
    }
@Test
    public void botonParaEliminarElHorarioCreadoFunciona(){
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withText("ACEPTAR")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.recyclerMostrar)).check(matches(hasMinimumChildCount(2)));
        onView(withId(R.id.nav_horario)).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.idHorarioAutomatico)).perform(click());
        onView(withId(R.id.eliminar_materias)).perform(click());
        onView(withText("ACEPTAR")).perform(click());
        onView(withId(R.id.cambiar_a_generar)).perform(click());
        onView(withId(R.id.recyclerMostrar)).check(matches(hasMinimumChildCount(0)));
    }

}
