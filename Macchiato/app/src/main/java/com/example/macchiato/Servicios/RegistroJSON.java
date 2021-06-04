package com.example.macchiato.Servicios;

import android.content.Context;
import android.widget.Toast;

import com.example.macchiato.HistorialAcademicoActivity;
import com.example.macchiato.Models.Materia;
import com.example.macchiato.Models.MateriaNota;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    /*
    Genera un archivo vacio con los parametros en vacio
    * */
    public void genararVacio(Context context, String nombre) throws FileNotFoundException, JSONException {
        JSONObject jo = new JSONObject();
        jo.put("email", "");
        jo.put("password", "");
        jo.put("uid", "");
        jo.put("userName", "");
        jo.put("materiasAprobadas", new ArrayList<>());
        jo.put("materiasReprobadas", new ArrayList<>());
        jo.put("materiasPorTomar", new ArrayList<>());
        jo.put("materiasActuales", new ArrayList<>());

        lf.escribirFichero(nombre , jo.toString(), context);
    }

    /*
    Le das los atributos del usuario y te los escribe en el fichero.
    * */
    public void registrarUsuario(String email, String password, String uid, String userName, Context context, String nombreArchivo)throws Exception{


        Object obj = new JSONParser().parse(lf.leerFichero(context, nombreArchivo));
        JSONObject jo = (JSONObject) obj;

        jo.put("email", email);
        jo.put("password", password);
        jo.put("uid", uid);
        jo.put("userName", userName);

        lf.escribirFichero(nombreArchivo, jo.toString(), context);
    }

    /*
    Anade una nota al json indicado por parametro
    * */
    public void aniadirNota(int materiaID, int nota, Context context, String nombreArchivo) throws Exception {
        Object obj = new JSONParser().parse(lf.leerFichero(context, nombreArchivo));

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

        lf.escribirFichero(nombreArchivo, jo.toString(), context);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        try {
            rootRef.child("Usuarios").child(uid).child(campo).setValue(getMateriaHash(campo,context,"registro.json"));
        }catch (Exception e){
            Toast.makeText(context, "error al sincronzar", Toast.LENGTH_SHORT).show();
        }
    }

    /*
        Indicar el campo e indicar la materia a quitar
    * */
    public void quitarMateria(String campo, MateriaNota quitar, Context context, String nombreArchivo)throws Exception{
        Object obj = new JSONParser().parse(lf.leerFichero(context, nombreArchivo));

        JSONObject jo = (JSONObject) obj;
        JSONObject j = new JSONObject();
        JSONArray notasJS = (JSONArray) jo.get(campo);

        for(int i=0; i<notasJS.size(); i++){
            j = (JSONObject)notasJS.get(i);
            String m = ((Long)j.get("materiaID")).intValue()+"";
            int n = ((Long)j.get("nota")).intValue();

            if(m.contains(quitar.getMateriaId()) && n == quitar.getNota())
                notasJS.remove(i);

            break;
        }
        jo.put(campo, notasJS);

        lf.escribirFichero(nombreArchivo, jo.toString(), context);
    }

    /*
    Indicas el campo y te devuelve todas las notas
    * */
    public ArrayList<MateriaNota> getMateriaNota(String campo, Context context, String nombreArchivo) throws Exception{
        ArrayList<MateriaNota> notas = new ArrayList<>();
        Object obj = new JSONParser().parse(lf.leerFichero(context, nombreArchivo));

        JSONObject jo = (JSONObject) obj;
        JSONObject j = new JSONObject();
        JSONArray notasJS = (JSONArray) jo.get(campo);
        for(int i=0; i<notasJS.size(); i++){
            j = (JSONObject)notasJS.get(i);
            String m = ((Long)j.get("materiaID")).intValue()+"";
            int n = ((Long)j.get("nota")).intValue();
            notas.add(new MateriaNota(m,n));
        }
        return notas;
    }

    public ArrayList<HashMap<String,Integer>> getMateriaHash(String campo, Context context, String nombreArchivo) throws Exception{
        ArrayList<HashMap<String,Integer>> notas = new ArrayList<>();
        Object obj = new JSONParser().parse(lf.leerFichero(context, nombreArchivo));

        JSONObject jo = (JSONObject) obj;
        JSONObject j = new JSONObject();
        JSONArray notasJS = (JSONArray) jo.get(campo);
        for(int i=0; i<notasJS.size(); i++){
            HashMap auxMap = new HashMap<>();
            j = (JSONObject)notasJS.get(i);
            int m = ((Long)j.get("materiaID")).intValue();
            int n = ((Long)j.get("nota")).intValue();
            auxMap.put("materiaID",m);
            auxMap.put("nota",n);
            notas.add(auxMap);
        }
        return notas;
    }


    /*
    Materias actuales
    * */
    public ArrayList<Integer> getMateriasTomadas(Context context, String nombreArchivo) throws Exception{
        ArrayList<Integer> mats = new ArrayList<>();
        Object obj = new JSONParser().parse(lf.leerFichero(context, nombreArchivo));

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


    /*
    Anadir un grupo
    * */
    public void aniadirMateriaTomada(int matID, Context context, String nombreArchivo) throws Exception {
        Object obj = new JSONParser().parse(lf.leerFichero(context, nombreArchivo));

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

        lf.escribirFichero(nombreArchivo, jo.toString(), context);
    }
}
