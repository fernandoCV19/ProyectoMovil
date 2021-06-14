package com.example.macchiato.CasosDeUsuario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

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
import static org.hamcrest.Matchers.instanceOf;

@RunWith(AndroidJUnit4ClassRunner.class)
public class ConsultarInformacionDeMateriasTest {

    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(Navigation_bottom.class);

    @Before
    public void setUp(){
        onView(withId(R.id.nav_materias)).perform(click());
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
    }

    @Test
    public void consultarMateriasDeNivelA(){
        onView(withText("Nivel A")).perform(click());
        onView(withId(R.id.listRecyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void consultarMateriasDeNivelB(){
        onView(withText("Nivel B")).perform(click());
        onView(withId(R.id.listRecyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void consultarMateriasDeNivelC(){
        onView(withText("Nivel C")).perform(click());
        onView(withId(R.id.listRecyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void consultarMateriasDeNivelD(){
        onView(withText("Nivel D")).perform(click());
        onView(withId(R.id.listRecyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void consultarMateriasDeNivelE(){
        onView(withText("Nivel E")).perform(click());
        onView(withId(R.id.listRecyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void consultarMateriasDeNivelF(){
        onView(withText("Nivel F")).perform(click());
        onView(withId(R.id.listRecyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void consultarMateriasDeNivelG(){
        onView(withText("Nivel G")).perform(click());
        onView(withId(R.id.listRecyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void consultarMateriasDeNivelH(){
        onView(withText("Nivel H")).perform(click());
        onView(withId(R.id.listRecyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void consultarMateriasDeNivelI(){
        onView(withText("Nivel I")).perform(click());
        onView(withId(R.id.listRecyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void consultarGruposQueExistenEnUnaMateria(){
        onView(withText("Nivel A")).perform(click());
        onView(allOf(withId(R.id.botonDetalles), withParent(withParent(withChild(withText("INGLES I")))))).perform(click());
        onView(withId(R.id.recyclerGrupos)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void consultarRequisitosDeUnaMateria(){
        onView(withText("Nivel A")).perform(click());
        onView(allOf(withId(R.id.requisitos), withParent(withParent(withChild(withText("INGLES I")))))).perform(click());
        onView(withId(R.id.relativeRequisitos)).check(matches(isDisplayed()));
    }
}
