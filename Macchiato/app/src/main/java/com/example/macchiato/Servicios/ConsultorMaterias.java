package com.example.macchiato.Servicios;
import com.example.macchiato.Models.Grupo;
import com.example.macchiato.Models.Materia;
import com.example.macchiato.Models.GrupoModelParser;
import com.example.macchiato.Parser.ParserMateriaID;

import java.util.HashMap;
import java.util.ArrayList;
public class ConsultorMaterias {

    private static HashMap <Character, ArrayList<Materia>> lisClasificada;

    private static ArrayList<Materia> materias;


    public   ArrayList<Grupo> devolverGrupos(ArrayList<Grupo>listaGrupos, ArrayList<Integer>ides ){
        ArrayList<Grupo>res=new ArrayList();
        for (Integer i : ides){

            for(Grupo m :listaGrupos){
                int id =m.getID();
                if(id==i){
                    res.add(m);

                }

            }
        }
        return res;
    }

    public  void clasificarMaterias (ArrayList<GrupoModelParser>listaGrupos){
        lisClasificada = new HashMap();
        materias = new ArrayList<>();

        iniciarHashMap();
        String materiaActual = "";
        Materia actual = new Materia(0, "", '1', null);
        boolean primero = true;

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
                //Actuali actual
                try{
                    actual = new Materia((new ParserMateriaID()).getID(grupoActual.getNombre()), grupoActual.getNombre(), grupoActual.getNivel(), new ArrayList<Grupo>());
                }catch(Exception e){
                    e.printStackTrace();
                }
                Grupo grupo = new Grupo(grupoActual.getID(),grupoActual.getGrupo(), grupoActual.getDocente(), grupoActual.getClases());
                actual.getGrupos().add(grupo);
            }
        }
    }

    private void iniciarHashMap (){
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


    public static HashMap <Character, ArrayList<Materia>> getLisClasificada(){
        return lisClasificada;
    }

    public static ArrayList<Materia> getMaterias(){
        return materias;
    }
}
