package com.example.macchiato.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String userName="usuario vacio";
    private String email="email vacio";
    private String uid="";
    private String password="";
    private ArrayList<HashMap<String,Object>> materiasAprobadas;
    private ArrayList<HashMap<String,Object>> materiasReprobadas;
    //private ArrayList<Integer> materiasPorTomar;
    private ArrayList<Integer> materiasActuales;

    public User(){
        materiasAprobadas = new ArrayList<>();
        materiasReprobadas = new ArrayList<>();
        //materiasPorTomar = new ArrayList<>();
        materiasActuales = new ArrayList<>();

         for(int i=0;i<97;i++){
             materiasActuales.add(0);
         }

    }
    public User(String userName,String email,String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        materiasAprobadas = new ArrayList<>();
        materiasReprobadas = new ArrayList<>();
        //materiasPorTomar = new ArrayList<>();
        materiasActuales = new ArrayList<>();
        for(int i=0;i<97;i++){
            materiasActuales.add(0);
        }

    }

        public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid(){
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<HashMap<String,Object>> getMateriasAprobadas(){ return materiasAprobadas; }
    public void setMateriasAprobadas(ArrayList<HashMap<String,Object>> materiasAprobadas){ this.materiasAprobadas=materiasAprobadas; }

    public ArrayList<HashMap<String,Object>> getMateriasReprobadas(){ return materiasReprobadas; }
    public void setMateriasReprobadas(ArrayList<HashMap<String,Object>> materiasReprobadas){ this.materiasReprobadas=materiasReprobadas; }

    public ArrayList<Integer> getMateriasActuales(){ return materiasActuales; }
    public void setMateriasActuales(ArrayList<Integer> materiasActuales){ this.materiasActuales=materiasActuales; }

    //public ArrayList<Integer> getMateriasPorTomar(){ return materiasPorTomar; }
    //public void setMateriasPorTomar(ArrayList<Integer> materiasPorTomar){ this.materiasPorTomar=materiasPorTomar; }


    @Override
    public String toString() {
        return userName;
    }


}
