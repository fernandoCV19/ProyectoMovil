package com.example.macchiato.Servicios;

import com.example.macchiato.Models.GrupoModelParser;
import com.example.macchiato.Parser.ParserMateriaGrupo;

import java.util.ArrayList;

public class Iniciador {

    public void iniciar() throws Exception {
        if(ConsultorMaterias.getLisClasificada() == null){
            ParserMateriaGrupo parserMateriaGrupo = new ParserMateriaGrupo();

            ArrayList<GrupoModelParser> lista = parserMateriaGrupo.parserMateriaGrupo();

            ConsultorMaterias consultorMaterias = new ConsultorMaterias();
            consultorMaterias.clasificarMaterias(lista);
        }

    }

}
