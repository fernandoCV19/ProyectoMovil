package com.example.macchiato.Servicios;

import android.content.Context;

import com.example.macchiato.Models.GrupoModelParser;
import com.example.macchiato.Parser.LectorJson;
import com.example.macchiato.Parser.ParserMateriaGrupo;
import com.example.macchiato.Parser.ParserMateriaID;

import java.util.ArrayList;
/**
 * Clase de inicio, crea las clases estaticas y genera informacion necesaria para el correcto
 * funcionamiento de la aplicacion
 * */
public class Iniciador {

    private LectorJson lectorJson;

    public Iniciador(){
        lectorJson = new LectorJson();
    }

    public boolean iniciar(Context context) throws Exception {
        if(ConsultorMaterias.getLisClasificada() == null){
            iniciarObjetosIDMateria(context);
            iniciarObjetosMateria(context);
            return true;
        }else{
            return false;
        }
    }

    private void iniciarObjetosIDMateria(Context context) throws Exception {
        String json = lectorJson.loadJSONFromAsset("materiasID.json",context);
        ParserMateriaID parserMateriaID = new ParserMateriaID();
        parserMateriaID.iniciarIDs(json);
    }

    private void iniciarObjetosMateria(Context context) throws Exception {
        String json = lectorJson.loadJSONFromAsset("materias.json", context);
        ParserMateriaGrupo parserMateriaGrupo = new ParserMateriaGrupo();
        ArrayList<GrupoModelParser> lista = parserMateriaGrupo.parserMateriaGrupo(json);

        ConsultorMaterias consultorMaterias = new ConsultorMaterias();
        consultorMaterias.clasificarMaterias(lista);

    }
}
