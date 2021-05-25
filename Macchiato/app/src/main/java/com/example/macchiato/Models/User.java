package com.example.macchiato.Models;

import java.util.ArrayList;

public class User {
    private String userName="usuario vacio";
    private String email="email vacio";
    private String uid="";
    private String password="";
    private ArrayList<Materia> materiasAprobadas;
    private ArrayList<Materia> materiasReprobadas;
    private ArrayList<Materia> materiasPorTomar;
    private ArrayList<Materia> materiasActuales;


    public User(){}
    public User(String userName,String email,String password){
        this.email=email;
        this.userName=userName;
        this.password=password;
        materiasActuales = new ArrayList<>();
        materiasAprobadas = new ArrayList<>();
        materiasPorTomar = new ArrayList<>();
        materiasReprobadas = new ArrayList<>();
        ArrayList<Grupo> grupos = new ArrayList<>();
        materiasActuales.add(new Materia(2,"ingles 2",'B',grupos,"azul","cod1"));
        materiasActuales.add(new Materia(2,"calculo",'A',grupos,"blanco","cod2"));
        materiasAprobadas.add(new Materia(2,"logica",'C',grupos,"verde","cod3"));
        materiasPorTomar.add(new Materia(2,"base 3",'F',grupos,"azul","cod10"));
        materiasPorTomar.add(new Materia(2,"agentes ",'G',grupos,"amarillo","cod1"));


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
