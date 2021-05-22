package com.example.macchiato.Servicios;

import android.content.Context;

import com.example.macchiato.Models.GrupoModelParser;
import com.example.macchiato.Parser.ParserMateriaGrupo;
import com.example.macchiato.Parser.ParserMateriaID;

import java.util.ArrayList;

public class Iniciador {

    public void iniciar(Context context) throws Exception {
        if(ConsultorMaterias.getLisClasificada() == null){
            ParserMateriaGrupo parserMateriaGrupo = new ParserMateriaGrupo();

            ArrayList<GrupoModelParser> lista = parserMateriaGrupo.parserMateriaGrupo(context);
            ParserMateriaID parserMateriaID = new ParserMateriaID();
            parserMateriaID.iniciarIDs(context);

            ConsultorMaterias consultorMaterias = new ConsultorMaterias();
            consultorMaterias.clasificarMaterias(lista);
        }

    }

}
