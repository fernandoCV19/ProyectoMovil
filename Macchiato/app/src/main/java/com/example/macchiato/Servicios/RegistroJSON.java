package com.example.macchiato.Servicios;

import android.content.Context;

import com.example.macchiato.Models.Materia;
import com.example.macchiato.Models.MateriaNota;

import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class RegistroJSON {
    public LectorFichero lf;
    public RegistroJSON(){
        lf = new LectorFichero();
    }
    public void genararVacio(Context context) throws FileNotFoundException, JSONException {
        JSONObject jo = new JSONObject();
        jo.put("email", "");
        jo.put("password", "");
        jo.put("uid", "");
        jo.put("userName", "");
        jo.put("materiasAprobadas", new HashMap<>());
        jo.put("materiasReprobadas", new HashMap<>());
        jo.put("materiasPorTomar", new ArrayList<>());
        jo.put("materiasActuales", new ArrayList<>());

        lf.escribirFichero("registro.json", jo.toString(), context);
    }
    public void registrarUsuario(String email, String password, String uid, String userName, Context context)throws Exception{


        Object obj = new JSONParser().parse(lf.leerFichero(context));
        JSONObject jo = (JSONObject) obj;

        jo.put("email", email);
        jo.put("password", password);
        jo.put("uid", uid);
        jo.put("userName", userName);

        lf.escribirFichero("registro.json", jo.toString(), context);
    }
    public void aniadirNota(int materiaID, int nota, Context context) throws Exception {
        Object obj = new JSONParser().parse(lf.leerFichero(context));

        JSONObject jo = (JSONObject) obj;

        JSONObject j = new JSONObject();
        j.put("materiaID", materiaID);
        j.put("nota", nota);

        JSONArray notas;
        String campo = "";
        if(nota >50)
            campo = "materiasAprobadas";
        else
            campo = "materiasReprobadas";

        notas = (JSONArray) jo.get(campo);
        if(notas == null) notas = new JSONArray();
        notas.add(j);

        jo.put(campo, notas);

        lf.escribirFichero("registro.json", jo.toString(), context);
    }

    public void quitarMateria(String campo, MateriaNota quitar, Context context)throws Exception{
        Object obj = new JSONParser().parse(lf.leerFichero(context));

        JSONObject jo = (JSONObject) obj;
        JSONObject j = new JSONObject();
        JSONArray notasJS = (JSONArray) jo.get(campo);

        for(int i=0; i<notasJS.size(); i++){
            j = (JSONObject)notasJS.get(i);
            String m = (String)j.get("materia");
            int n = ((Long)j.get("nota")).intValue();

            if(m.contains(quitar.getMateria()) && n == quitar.getNota())
                notasJS.remove(i);

            break;
        }
        jo.put(campo, notasJS);

        lf.escribirFichero("registro.json", jo.toString(), context);
    }
    public ArrayList<MateriaNota> getMateriaNota(String campo, Context context) throws Exception{
        ArrayList<MateriaNota> notas = new ArrayList<>();
        Object obj = new JSONParser().parse(lf.leerFichero(context));

        JSONObject jo = (JSONObject) obj;
        JSONObject j = new JSONObject();
        JSONArray notasJS = (JSONArray) jo.get(campo);

/*<<<<<<< HEAD
        if(notasJS!=null) {
            for (int i = 0; i < notasJS.size(); i++) {
                j = (JSONObject) notasJS.get(i);
                String m = (String) j.get("materia");
                int n = ((Long) j.get("nota")).intValue();
                notas.add(new MateriaNota(m, n));
            }
=======*/
        for(int i=0; i<notasJS.size(); i++){
            j = (JSONObject)notasJS.get(i);

            ArrayList<Integer>ides=new ArrayList<>();
            String m = ((Long)j.get("materiaID")).intValue()+"";
            ides.add(Integer.parseInt(m));
            ArrayList<Materia>materias=new ConsultorMaterias().getListaMaterias(ides);
            Materia materia =materias.get(0);
            m =materia.getNombre();
            int n = ((Long)j.get("nota")).intValue();
            notas.add(new MateriaNota(m,n));
//>>>>>>> HistorialAcademicoNuevo
        }
        return notas;
    }

    public ArrayList<Integer> getMateriasTomadas(Context context) throws Exception{
        ArrayList<Integer> mats = new ArrayList<>();
        Object obj = new JSONParser().parse(lf.leerFichero(context));

        JSONObject jo = (JSONObject) obj;
        JSONObject j = new JSONObject();
        JSONArray matsJS = (JSONArray) jo.get("materiasActuales");

        for (int i=0; i<matsJS.size(); i++){
            int m = ((Long)matsJS.get(i)).intValue();
            //int m = (Integer) matsJS.get(i);
            mats.add(m);
        }
        return mats;
    }


    public void aniadirMateriaTomada(int matID, Context context) throws Exception {
        Object obj = new JSONParser().parse(lf.leerFichero(context));

        JSONObject jo = (JSONObject) obj;
        JSONObject j = new JSONObject();
        JSONArray materias = (JSONArray) jo.get("materiasActuales");


        if(materias == null) materias = new JSONArray();
        boolean contiene = false;
        for (int i=0; i<materias.size(); i++){
            int m = (((Long)materias.get(i))).intValue();
            //int m = (Integer)materias.get(i);
            if(m == matID) {
                contiene = true;
                break;
            }
        }
        if(!contiene)
            materias.add(matID);

        jo.put("materiasActuales", materias);

        lf.escribirFichero("registro.json", jo.toString(), context);
    }
}
