package com.example.macchiato.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String userName="usuario vacio";
    private String email="email vacio";
    private ArrayList<HashMap<String,Object>> materiasAprobadas;
    private ArrayList<HashMap<String,Object>> materiasReprobadas;
    private ArrayList<Integer> materiasPorTomar;
    private ArrayList<Integer> materiasActuales;

    public User(){
        materiasAprobadas = new ArrayList<>();
        materiasReprobadas = new ArrayList<>();
        materiasPorTomar = new ArrayList<>();
        materiasActuales = new ArrayList<>();
        for (int i=0;i<54;i++){
            materiasPorTomar.add(i);
        }

    }
    public User(String userName,String email) {
        this.email = email;
        this.userName = userName;
        materiasAprobadas = new ArrayList<>();
        materiasReprobadas = new ArrayList<>();
        materiasPorTomar = new ArrayList<>();
        materiasActuales = new ArrayList<>();
        for (int i=0;i<54;i++){
            materiasPorTomar.add(i);
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


    public ArrayList<HashMap<String,Object>> getMateriasAprobadas(){ return materiasAprobadas; }
    public void setMateriasAprobadas(ArrayList<HashMap<String,Object>> materiasAprobadas){ this.materiasAprobadas=materiasAprobadas; }

    public ArrayList<HashMap<String,Object>> getMateriasReprobadas(){ return materiasReprobadas; }
    public void setMateriasReprobadas(ArrayList<HashMap<String,Object>> materiasReprobadas){ this.materiasReprobadas=materiasReprobadas; }

    public ArrayList<Integer> getMateriasActuales(){ return materiasActuales; }
    public void setMateriasActuales(ArrayList<Integer> materiasActuales){ this.materiasActuales=materiasActuales; }

    public ArrayList<Integer> getMateriasPorTomar(){ return materiasPorTomar; }
    public void setMateriasPorTomar(ArrayList<Integer> materiasPorTomar){ this.materiasPorTomar=materiasPorTomar; }


    @Override
    public String toString() {
        return userName;
    }


}
