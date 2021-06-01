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

    public   ArrayList<Par> devolverGrupos(ArrayList<Integer>ides){
        ArrayList<Par>res=new ArrayList();
        for (Integer i : ides){

            for(Materia materia: materias) {

                for (Grupo grupo : materia.getGrupos()) {
                    int id = grupo.getID();
                    if (id == i) {
                        res.add(new Par(grupo,materia.getNombre()));

                    }

                }
            }
        }
        return res;
    }

    public void clasificarMaterias (ArrayList<GrupoModelParser>listaGrupos){
        iniciarHashMap();
        String materiaActual = "";
        Materia actual = new Materia(0, "", 'A', null,"", "A", null);
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
    }

    private void iniciarHashMap (){
        lisClasificada = new HashMap<>();
        materias = new ArrayList<>();
        lisClasificada.put('A',new ArrayList<>());
        lisClasificada.put('B',new ArrayList<>());
        lisClasificada.put('C',new ArrayList<>());
        lisClasificada.put('D',new ArrayList<>());
        lisClasificada.put('E',new ArrayList<>());
        lisClasificada.put('F',new ArrayList<>());
        lisClasificada.put('G',new ArrayList<>());
        lisClasificada.put('H',new ArrayList<>());
        lisClasificada.put('I',new ArrayList<>());
    }

    public ArrayList<Materia> getListaMaterias(ArrayList<Integer> ids){
        ArrayList<Materia>materiasElegidas = new ArrayList<>();

        for(Integer id:ids){
            for(Materia materia: materias){
                if (materia.getId() == id){
                    materiasElegidas.add(materia);
                    break;
                }
            }
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

    public class Par{
        private Grupo grupo;
        private String materia;

        public Grupo getGrupo() {
            return grupo;
        }

        public void setGrupo(Grupo grupo) {
            this.grupo = grupo;
        }

        public String getMateria() {
            return materia;
        }

        public void setMateria(String materia) {
            this.materia = materia;
        }

        public Par(Grupo grupo, String materia){
            this.grupo = grupo;
            this.materia = materia;
        }
    }
}
