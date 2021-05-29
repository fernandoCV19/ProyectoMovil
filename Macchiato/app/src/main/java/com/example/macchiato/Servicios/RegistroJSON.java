package com.example.macchiato.Servicios;

import android.content.Context;
import com.example.macchiato.Models.MateriaNota;

import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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
        jo.put("User Name", "");
        jo.put("Materias Aprobadas", new ArrayList<>());
        jo.put("Materias Reprobadas", new ArrayList<>());
        jo.put("Materias por Tomar", new ArrayList<>());
        jo.put("Materias Actuales", new ArrayList<>());

        lf.escribirFichero("registro.json", jo.toString(), context);
    }
    public void registrarUsuario(String email, String password, String uid, String userName, Context context)throws Exception{


        Object obj = new JSONParser().parse(lf.leerFichero(context));
        JSONObject jo = (JSONObject) obj;

        jo.put("email", email);
        jo.put("password", password);
        jo.put("uid", uid);
        jo.put("User Name", userName);

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
            campo = "Materias Aprobadas";
        else
            campo = "Materias Reprobadas";

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

        for(int i=0; i<notasJS.size(); i++){
            j = (JSONObject)notasJS.get(i);
            String m = (String)j.get("materia");
            int n = ((Long)j.get("nota")).intValue();
            notas.add(new MateriaNota(m,n));
        }
        return notas;
    }
    public ArrayList<Integer> getMateriasTomadas(Context context) throws Exception{
        ArrayList<Integer> mats = new ArrayList<>();
        Object obj = new JSONParser().parse(lf.leerFichero(context));

        JSONObject jo = (JSONObject) obj;
        JSONObject j = new JSONObject();
        JSONArray matsJS = (JSONArray) jo.get("Materias Actuales");

        for (int i=0; i<matsJS.size(); i++){
            int m = ((Long)matsJS.get(i)).intValue();
            mats.add(m);
        }
        return mats;
    }

    public void aniadirMateriaTomada(int matID, Context context) throws Exception {
        Object obj = new JSONParser().parse(lf.leerFichero(context));

        JSONObject jo = (JSONObject) obj;
        JSONObject j = new JSONObject();
        JSONArray materias = (JSONArray) jo.get("Materias Actuales");


        if(materias == null) materias = new JSONArray();
        materias.add(matID);
        jo.put("materias tomadas", materias);

        lf.escribirFichero("registro.json", jo.toString(), context);
    }
}
