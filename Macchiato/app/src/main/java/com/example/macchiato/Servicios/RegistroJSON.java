package com.example.macchiato.Servicios;

import android.app.Activity;
import android.content.Context;

import com.example.macchiato.Models.GlobalApplication;
import com.example.macchiato.Models.MateriaNota;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class RegistroJSON {
    //public void genararVacio() throws FileNotFoundException, JSONException {}
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
        notas.put(j);

        jo.put(campo, notas);

        lf.escribirFichero("registro.json", jo.toString(), context);
    }

    public ArrayList<MateriaNota> getNotas(Context context) throws Exception{
        ArrayList<MateriaNota> notas = new ArrayList<>();
        Object obj = new JSONParser().parse(lf.leerFichero(context));

        JSONObject jo = (JSONObject) obj;
        JSONObject j = new JSONObject();
        JSONArray notasJS = (JSONArray) jo.get("notas");

        for(int i=0; i<notasJS.length(); i++){
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
        JSONArray matsJS = (JSONArray) jo.get("materias tomadas");

        for (int i=0; i<matsJS.length(); i++){
            int m = ((Long)matsJS.get(i)).intValue();
            mats.add(m);
        }
        return mats;
    }

    public void aniadirMateriaTomada(int matID, Context context) throws Exception {
        Object obj = new JSONParser().parse(lf.leerFichero(context));

        JSONObject jo = (JSONObject) obj;
        JSONObject j = new JSONObject();
        JSONArray materias = (JSONArray) jo.get("materias tomadas");


        if(materias == null) materias = new JSONArray();
        materias.put(matID);
        jo.put("materias tomadas", materias);

        lf.escribirFichero("registro.json", jo.toString(), context);
    }
}
