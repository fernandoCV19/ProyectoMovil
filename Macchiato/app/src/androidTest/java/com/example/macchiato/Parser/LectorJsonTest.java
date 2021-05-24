package com.example.macchiato.Parser;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static com.google.common.truth.Truth.assertThat;

@RunWith(AndroidJUnit4.class)
public class LectorJsonTest {

    private LectorJson lectorJson;
    private Context context;

    @Before
    public void setup(){
        lectorJson = new LectorJson();
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Test
    public void archivoNoEncontradoDeberiaDevolverCadenaVaciaTest(){
        String respuesta = lectorJson.loadJSONFromAsset("archivoQueNoExiste.json",context);
        assertThat(respuesta).isEmpty();
    }

    

}