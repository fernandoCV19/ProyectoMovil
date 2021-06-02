package com.example.macchiato.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String userName="usuario vacio";
    private String email="email vacio";
    private String uid="";
    private String password="";
    /*public HashMap<String,Integer> materiasAprobadas;
    private HashMap<String,Integer> materiasReprobadas;
    private HashMap<String,Integer> materiasPorTomar;
    public ArrayList<Integer> materiasActuales;*/
    private HashMap<String,Integer> materiasAprobadas;
    private HashMap<String,Integer> materiasReprobadas;
    private ArrayList<Integer> materiasPorTomar;
    public ArrayList<Integer> materiasActuales;


    public User(){
        materiasAprobadas = new HashMap<>();
        materiasReprobadas = new HashMap<>();
        materiasPorTomar = new ArrayList<>();
        materiasActuales = new ArrayList<>();

    }
    public User(String userName,String email,String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        /*materiasActuales = new ArrayList<Integer>();
        materiasAprobadas = new ArrayList<Integer>();
        materiasPorTomar = new ArrayList<Integer>();
        materiasReprobadas = new ArrayList<Integer>();*/
        materiasAprobadas = new HashMap<>();
        materiasReprobadas = new HashMap<>();
        materiasPorTomar = new ArrayList<>();
        materiasActuales = new ArrayList<>();


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

    @Override
    public String toString() {
        return userName;
    }


}
