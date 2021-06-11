package com.example.macchiato.Servicios;

import android.content.Context;

import com.example.macchiato.Models.Grupo;
import com.example.macchiato.Models.Materia;
import com.example.macchiato.Models.MateriaNota;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HorarioAutomatico {
    private Context context;
    public HorarioAutomatico(Context context){
        this.context = context;
    }
    public void generarAutomatico() throws Exception {
        RegistroJSON registroJSON = new RegistroJSON();
        registroJSON.limpiarCampo("materiasActuales", context, "registro.json");

        ArrayList<Integer> horario = getAutomatica();
        for(Integer mat: horario) {
            registroJSON.aniadirMateria(mat, "materiasActuales", context, "registro.json");
        }
    }
    public ArrayList<Integer> getAutomatica() throws Exception {
        ArrayList<Integer> lista;
        MallaCurricular mallaCurricular = new MallaCurricular();
        CreadorHorarios creadorHorarios = new CreadorHorarios();
        ConsultorMaterias consultorMaterias = new ConsultorMaterias();
        
        mallaCurricular.quitarVarios(getAprobados());
        lista = mallaCurricular.getSiguientes();
        ArrayList<Materia> materias = consultorMaterias.getListaMaterias(lista);
        
        ArrayList<Grupo> gruposAutomaticos = new ArrayList<>(creadorHorarios.crearHorario(materias));

        for(Grupo g: gruposAutomaticos){
            lista.add(g.getID());
        }
        return lista;
    }

    private ArrayList<Integer> getAprobados() throws Exception {
        ArrayList<Integer> aprobadosID = new ArrayList<>();
        RegistroJSON registroJSON = new RegistroJSON();

        ArrayList<MateriaNota> listaAprobados = new ArrayList<>(registroJSON.getMateriaNota(
                "materiasAprobadas",context,"registro.json"));
        for (MateriaNota mat: listaAprobados) {
            String id  = mat.getMateriaId();
            id.replaceAll(" ", "");
            aprobadosID.add(Integer.parseInt(id));
        }
        return aprobadosID;
    }
}
