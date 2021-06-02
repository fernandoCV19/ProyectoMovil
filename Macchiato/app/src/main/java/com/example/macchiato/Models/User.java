package com.example.macchiato.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String userName="usuario vacio";
    private String email="email vacio";
    private String uid="";
    private String password="";
    public HashMap<String,Integer> materiasAprobadas;
    private HashMap<String,Integer> materiasReprobadas;
    private HashMap<String,Integer> materiasPorTomar;
    public HashMap<String,Integer> materiasActuales;


    public User(){}
    public User(String userName,String email,String password){
        this.email=email;
        this.userName=userName;
        this.password=password;
        materiasActuales = new HashMap() {
        };
        materiasAprobadas = new HashMap();
        materiasPorTomar = new HashMap();
        materiasReprobadas = new HashMap();
        materiasActuales.put("a",123);
        materiasActuales.put("b",444);
        materiasAprobadas.put("c",000);
        materiasPorTomar.put("d",999);
        materiasPorTomar.put("e",100);


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
