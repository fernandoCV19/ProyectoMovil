package com.example.macchiato.Servicios;

import android.content.Context;

import com.example.macchiato.Models.GrupoModelParser;
import com.example.macchiato.Parser.LectorJson;
import com.example.macchiato.Parser.ParserMateriaGrupo;
import com.example.macchiato.Parser.ParserMateriaID;

import java.util.ArrayList;

public class Iniciador {

    public void iniciar(Context context) throws Exception {
        if(ConsultorMaterias.getLisClasificada() == null){
            LectorJson lectorJson = new LectorJson();

            String json = lectorJson.loadJSONFromAsset("materias.json", context);
            ParserMateriaGrupo parserMateriaGrupo = new ParserMateriaGrupo();
            ArrayList<GrupoModelParser> lista = parserMateriaGrupo.parserMateriaGrupo(json);


            json = lectorJson.loadJSONFromAsset("materiasID.json",context);
            ParserMateriaID parserMateriaID = new ParserMateriaID();
            parserMateriaID.iniciarIDs(json);

            ConsultorMaterias consultorMaterias = new ConsultorMaterias();
            consultorMaterias.clasificarMaterias(lista);
        }

    }

}
