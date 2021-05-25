package com.example.macchiato.Servicios;
import android.view.MenuItem;

import com.example.macchiato.Models.Grupo;
import com.example.macchiato.Models.Materia;
import com.example.macchiato.Models.GrupoModelParser;
import com.example.macchiato.Parser.ParserMateriaID;
import com.example.macchiato.R;

import java.util.HashMap;
import java.util.ArrayList;
public class ConsultorMaterias {

    private static HashMap <Character, ArrayList<Materia>> lisClasificada;

    private static ArrayList<Materia> materias;


    public   ArrayList<Grupo> devolverGrupos(ArrayList<Integer>ides ){
        ArrayList<Grupo>res=new ArrayList();
        for (Integer i : ides){

            for(Materia materia: materias) {

                for (Grupo grupo : materia.getGrupos()) {
                    int id = grupo.getID();
                    if (id == i) {
                        res.add(grupo);

                    }

                }
            }
        }
        return res;
    }

    public  void clasificarMaterias (ArrayList<GrupoModelParser>listaGrupos){
        iniciarHashMap();
        String materiaActual = "";
        Materia actual = new Materia(0, "", '1', null,"", "A", null);
        boolean primero = true;
        int contador = 0;

        for(GrupoModelParser grupoActual: listaGrupos){
            if(grupoActual.getNombre().equals(materiaActual)){
                Grupo grupo = new Grupo(grupoActual.getID(),grupoActual.getGrupo(), grupoActual.getDocente(), grupoActual.getClases());
                actual.getGrupos().add(grupo);
            }else{
                //anadir al hashmap y a la lista de materias
                if(!primero){
                    lisClasificada.get(actual.getNivel()).add(actual);
                    materias.add(actual);
                }else{
                    primero = false;
                }
                //Actualiza actual
                try{
                    actual = new Materia((new ParserMateriaID()).getID(grupoActual.getNombre()), grupoActual.getNombre(), grupoActual.getNivel(), new ArrayList<Grupo>(),getColorNivel(grupoActual.getNivel()), grupoActual.getCodigo(),(new ParserMateriaID()).getRequisitos(grupoActual.getNombre()));
                    materiaActual = actual.getNombre();
                }catch(Exception e){
                    e.printStackTrace();
                    System.out.println(actual.getNombre());
                }
                Grupo grupo = new Grupo(grupoActual.getID(),grupoActual.getGrupo(), grupoActual.getDocente(), grupoActual.getClases());
                actual.getGrupos().add(grupo);
            }
        }
        lisClasificada.get(actual.getNivel()).add(actual);
        materias.add(actual);
        System.out.println(lisClasificada);
        System.out.println(materias);
        System.out.println(contador);
    }

    private void iniciarHashMap (){
        lisClasificada = new HashMap();
        materias = new ArrayList<>();
        lisClasificada.put('A',new ArrayList<Materia>());
        lisClasificada.put('B',new ArrayList<Materia>());
        lisClasificada.put('C',new ArrayList<Materia>());
        lisClasificada.put('D',new ArrayList<Materia>());
        lisClasificada.put('E',new ArrayList<Materia>());
        lisClasificada.put('F',new ArrayList<Materia>());
        lisClasificada.put('G',new ArrayList<Materia>());
        lisClasificada.put('H',new ArrayList<Materia>());
        lisClasificada.put('I',new ArrayList<Materia>());
    }

    public ArrayList<Materia> getListaMaterias(ArrayList<Integer> ids){
        ArrayList<Materia>materiasElegidas = new ArrayList<>();

        for(Integer i: ids){
            materiasElegidas.add(materias.get(i - 1));
        }

        return materiasElegidas;
    }

    public String getColorNivel(char nivel) {
        String respuesta = "";
        switch(nivel) {
            case 'B':
                respuesta = "#48a259";
                break;
            case 'A':
                respuesta = "#00e25f";
                break;
            case 'C':
                respuesta = "#99e801";
                break;
            case 'D':
                respuesta = "#48a259";
                break;
            case 'E':
                respuesta = "#48a259";
                break;
            case 'F':
                respuesta = "#48a259";
                break;
            case 'G':
                respuesta = "#48a259";
                break;
            case 'H':
                respuesta = "#48a259";
                break;
            case 'I':
                respuesta = "#48a259";
                break;
            default:
                respuesta = "#ffffff";
        }
        return respuesta;
    }


    public static HashMap <Character, ArrayList<Materia>> getLisClasificada(){
        return lisClasificada;
    }

    public static ArrayList<Materia> getMaterias(){
        return materias;
    }
}
