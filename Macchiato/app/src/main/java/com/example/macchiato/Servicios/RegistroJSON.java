package com.example.macchiato.Servicios;

import android.content.Context;
import android.widget.Toast;

import com.example.macchiato.HistorialAcademicoActivity;
import com.example.macchiato.Models.Materia;
import com.example.macchiato.Models.MateriaNota;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class RegistroJSON {
    public LectorFichero lf;

    public RegistroJSON(){
        lf = new LectorFichero();
    }

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

    /**
    * Recibe un id de materia, una nota, nombrearchivo
    * Si es aprobada lo registra en aprobados (Si la materia estaba reprobada se elimina de ahi)
    * Si es reprobada lo registra en reprobados
    * Si es aprobada lo elimina de materias por tomar
    * */
    public void aniadirNota(int materiaID, int nota, Context context, String nombreArchivo) throws Exception {
        Object obj = new JSONParser().parse(lf.leerFichero(context, nombreArchivo));

        JSONObject jo = (JSONObject) obj;

        JSONObject j = new JSONObject();
        j.put("materiaID", materiaID);
        j.put("nota", nota);

        JSONArray notas;
        String campo = "";
        if(nota >50) {
                campo = "materiasAprobadas";

        }
        else {
            campo = "materiasReprobadas";
        }
        notas = (JSONArray) jo.get(campo);
        if(notas == null) notas = new JSONArray();
        notas.add(j);

        jo.put(campo, notas);

        lf.escribirFichero(nombreArchivo, jo.toString(), context);
        if(campo.equals("materiasAprobadas")){
            quitarMateria(materiaID,"materiasPorTomar",context,nombreArchivo);
        }

        if(nombreArchivo.equals("registro.json")) {
            actualizarFirebase(campo, context);
            //actualizarFirebase("materiasPorTomar",context);

        }
    }

    /**
    * Recibe un materia nota y lo elimina del campo ingresado
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

            if(m.contains(quitar.getMateriaId()) && n == quitar.getNota()) {
                notasJS.remove(i);
                aniadirMateria(Integer.parseInt(quitar.getMateriaId()),"materiasPorTomar",context,nombreArchivo);
                break;
            }

            }

        jo.put(campo, notasJS);

        lf.escribirFichero(nombreArchivo, jo.toString(), context);
        if(nombreArchivo.equals("registro.json")) {
            actualizarFirebase(campo, context);

        }
    }

    /**
    * Borra el id de la materia del campo que recibe, metodo especifico para materias tomadas
    * */
    public void quitarMateria(int id,String campo, Context context, String nombreArchivo)throws Exception{
        Object obj = new JSONParser().parse(lf.leerFichero(context, nombreArchivo));

        JSONObject jo = (JSONObject) obj;
        //JSONObject j = new JSONObject();
        JSONArray notasJS = (JSONArray) jo.get(campo);
        for (int i=0; i<notasJS.size(); i++){
            int m = ((Long)notasJS.get(i)).intValue();
            //int m = (Integer) matsJS.get(i);
            if (id==m){
                notasJS.remove(i);
            }
        }
        jo.put(campo, notasJS);

        lf.escribirFichero(nombreArchivo, jo.toString(), context);
        if(nombreArchivo.equals("registro.json")) {
            actualizarFirebase(campo, context);
        }
    }

    /**
    * Devuelve un array de las materias segun el campo (materias aprobadas o reprobadas)
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

    /**
    * Devuelve las materias actuales o por tomar segun el campo
    * */
    public ArrayList<Integer> getMaterias(String campo,Context context, String nombreArchivo) throws Exception{
        ArrayList<Integer> mats = new ArrayList<>();
        Object obj = new JSONParser().parse(lf.leerFichero(context, nombreArchivo));

        JSONObject jo = (JSONObject) obj;
        JSONObject j = new JSONObject();
        JSONArray matsJS = (JSONArray) jo.get(campo);

        for (int i=0; i<matsJS.size(); i++){
            int m = ((Long)matsJS.get(i)).intValue();
            //int m = (Integer) matsJS.get(i);
            mats.add(m);
        }
        return mats;
    }

    /**
    * Anade a materias actuales o por tomar
    * */
    public void aniadirMateria(int matID,String campo, Context context, String nombreArchivo) throws Exception {
        Object obj = new JSONParser().parse(lf.leerFichero(context, nombreArchivo));

        JSONObject jo = (JSONObject) obj;
        JSONObject j = new JSONObject();
        JSONArray materias = (JSONArray) jo.get(campo);

        if (materias == null) materias = new JSONArray();
        boolean contiene = false;
        for (int i = 0; i < materias.size(); i++) {
            int m = (((Long) materias.get(i))).intValue();
            //int m = (Integer)materias.get(i);
            if (m == matID) {
                contiene = true;
                break;
            }
        }
        if (!contiene)
            materias.add(matID);

        jo.put(campo, materias);

        lf.escribirFichero(nombreArchivo, jo.toString(), context);
        if (nombreArchivo.equals("registro.json")) {
            actualizarFirebase(campo, context);
        }
    }

    /**
    * Actualizacion de firebase si no se ha iniciado sesion no se actualiza
    * */
    private void actualizarFirebase(String campo,Context context){
        FirebaseUser us= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        if(us != null) {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            try {
                if (campo.equals("materiasActuales") || campo.equals("materiasPorTomar")) {
                    rootRef.child("Usuarios").child(uid).child(campo).setValue(getMaterias(campo, context, "registro.json"));
                } else {
                    rootRef.child("Usuarios").child(uid).child(campo).setValue(getMateriaNota(campo, context, "registro.json"));
                }

            } catch (Exception e) {
                Toast.makeText(context, "error al sincronizar", Toast.LENGTH_SHORT).show();
            }
        }
    }
    /**
     * Vacia un campo dado
     * */
    public void limpiarCampo(String campo, Context context, String nombreFichero) throws FileNotFoundException, JSONException, ParseException {
        Object obj = new JSONParser().parse(lf.leerFichero(context, nombreFichero));

        JSONObject jo = (JSONObject) obj;
        jo.put(campo, new ArrayList<>());

        lf.escribirFichero(nombreFichero, jo.toString(), context);



        if (nombreFichero.equals("registro.json")) {
            actualizarFirebase(campo, context);
            //actualizarFirebase("materiasPorTomar",context);
        }

    }
}
