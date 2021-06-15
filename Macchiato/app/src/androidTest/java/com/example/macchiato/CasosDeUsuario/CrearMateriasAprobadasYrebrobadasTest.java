package com.example.macchiato.CasosDeUsuario;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.macchiato.HistorialAcademicoActivity;
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
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4ClassRunner.class)

public class CrearMateriasAprobadasYrebrobadasTest {
    @Before
    public void setUp() {
        ActivityScenario.launch(HistorialAcademicoActivity.class);
        onView(withId(R.id.añadir_floating)).perform(click());
        onView(withId(R.id.añadir_floating)).perform(click());
    }

   @BeforeClass
   public static void setupClass() throws InterruptedException {
       ActivityScenario.launch(HistorialAcademicoActivity.class);
        onView(withId(R.id.añadir_floating)).perform(click());
       onView(withId(R.id.añadir_floating)).perform(click());
   }

    @Test
    public void crearMateriaAprobadas() {


        onView(withId(R.id.nivel)).perform(click());
        onView(withText("A")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.materia)).perform(click());
        onView(withText("INGLES I")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.editText)).perform(ViewActions.typeText("100"));
        onView(withText("AÑADIR")).perform(click());
        onView(withId(R.id.añadir_floating)).perform(click());
        onView(withId(R.id.nivel)).perform(click());
        onView(withText("A")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.materia)).perform(click());
        onView(withText("ALGEBRA I")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.editText)).perform(ViewActions.typeText("80"));
        onView(withText("AÑADIR")).perform(click());
        onView(withId(R.id.lista_MateriasAprobadas)).check(matches(hasChildCount(2)));
        onView(allOf(withId(R.id.eliminar), hasSibling(withText("INGLES I")))).perform(click());
        onView(withId(R.id.floating_eliminar)).perform(click());
        onView(allOf(withId(R.id.eliminar), hasSibling(withText("ALGEBRA I")))).perform(click());
        onView(withId(R.id.floating_eliminar)).perform(click());

    }

    @Test
    public void crearMateriasReprobadas() {


        onView(withId(R.id.nivel)).perform(click());
        onView(withText("B")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.materia)).perform(click());
        onView(withText("INGLES II")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.editText)).perform(ViewActions.typeText("20"));
        onView(withText("AÑADIR")).perform(click());
        onView(withId(R.id.añadir_floating)).perform(click());
        onView(withId(R.id.nivel)).perform(click());
        onView(withText("B")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.materia)).perform(click());
        onView(withText("CALCULO II")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.editText)).perform(ViewActions.typeText("10"));
        onView(withText("AÑADIR")).perform(click());
        onView(withId(R.id.list_materiasReprobadas)).check(matches(hasChildCount(2)));
        onView(allOf(withId(R.id.eliminar), hasSibling(withText("INGLES II")))).perform(click());
        onView(withId(R.id.floating_eliminar)).perform(click());
        onView(allOf(withId(R.id.eliminar), hasSibling(withText("CALCULO II")))).perform(click());
        onView(withId(R.id.floating_eliminar)).perform(click());

    }


    @Test
    public void verificarMateriaAprobadaNoSeRepiteEnReprobadas() {


        onView(withId(R.id.nivel)).perform(click());
        onView(withText("A")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.materia)).perform(click());
        onView(withText("FISICA GENERAL")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.editText)).perform(ViewActions.typeText("100"));
        onView(withText("AÑADIR")).perform(click());
        onView(withId(R.id.añadir_floating)).perform(click());
        onView(withId(R.id.nivel)).perform(click());
        onView(withText("A")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.materia)).perform(click());
        onView(withText("FISICA GENERAL")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.editText)).perform(ViewActions.typeText("20"));
        onView(withText("AÑADIR")).perform(click());
        onView(withId(R.id.lista_MateriasAprobadas)).check(matches(hasChildCount(1)));
        onView(allOf(withId(R.id.eliminar), hasSibling(withText("FISICA GENERAL")))).perform(click());
        onView(withId(R.id.floating_eliminar)).perform(click());


    }
    @Test
    public void verificarMateriaReprobadaSiSeRepiteEnReprobadas() {


        onView(withId(R.id.nivel)).perform(click());
        onView(withText("C")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.materia)).perform(click());
        onView(withText("CALCULO NUMERICO")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.editText)).perform(ViewActions.typeText("10"));
        onView(withText("AÑADIR")).perform(click());
        onView(allOf(withId(R.id.eliminar), hasSibling(withText("CALCULO NUMERICO")))).perform(click());
        onView(withId(R.id.floating_eliminar)).perform(click());
        onView(withId(R.id.añadir_floating)).perform(click());
        onView(withId(R.id.nivel)).perform(click());
        onView(withText("C")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.materia)).perform(click());
        onView(withText("CALCULO NUMERICO")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.editText)).perform(ViewActions.typeText("20"));
        onView(withText("AÑADIR")).perform(click());
        onView(withId(R.id.list_materiasReprobadas)).check(matches(hasChildCount(1)));
        onView(allOf(withId(R.id.eliminar), hasSibling(withText("CALCULO NUMERICO")))).perform(click());
        onView(withId(R.id.floating_eliminar)).perform(click());

    }
    @Test
    public void dialogEstadistica(){
        onView(withId(R.id.nivel)).perform(click());
        onView(withText("C")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.materia)).perform(click());
        onView(withText("CALCULO NUMERICO")).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.editText)).perform(ViewActions.typeText("10"));
        onView(withText("AÑADIR")).perform(click());
        onView(withId(R.id.ver_Estadisticas)).perform(click());
        onView(withId(R.id.promedio_nota)).check(matches(withText("10.0")));
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(allOf(withId(R.id.eliminar), hasSibling(withText("CALCULO NUMERICO")))).perform(click());
        onView(withId(R.id.floating_eliminar)).perform(click());


    }
}
