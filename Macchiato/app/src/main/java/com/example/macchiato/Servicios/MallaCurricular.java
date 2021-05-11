package com.example.macchiato.Servicios;
import java.util.ArrayList;
import java.util.Collections;

public class MallaCurricular {
    private ArrayList<Integer> cola1;
    private ArrayList<Integer> cola2;
    private ArrayList<Integer> cola3;
    private ArrayList<Integer> cola4;
    private ArrayList<Integer> cola5;
    private ArrayList<Integer> cola6;
    private ArrayList<Integer> cola7;
    private int requisitos_ia1;
    private int requisitos_TG2;
    public MallaCurricular(){
        cola1 = new ArrayList<>();
        cola2 = new ArrayList<>();
        cola3 = new ArrayList<>();
        cola4 = new ArrayList<>();
        cola5 = new ArrayList<>();
        cola6 = new ArrayList<>();
        cola7 = new ArrayList<>();
        requisitos_ia1 = requisitos_TG2 = 2;
    }
    public ArrayList<Integer> getSiguientes(){
        ArrayList<Integer> sig = new ArrayList<>();


        aniadir(cola1.get(0), cola2.get(0),sig);
        aniadir(cola2.get(0), cola3.get(0),sig);
        aniadir(cola3.get(0), cola4.get(0),sig);
        aniadir(cola4.get(0), cola5.get(0),sig);
        aniadir(cola5.get(0), cola6.get(0),sig);
        aniadir(cola6.get(0), cola7.get(0),sig);

        return sig;
    }
    private void aniadir(int a, int b, ArrayList<Integer> sig){
        int aux = 0;
        if(a == 0) a = b;
        if(a == 0 && b == 0) return;

        if(a == 29 && requisitos_ia1 == 0) sig.add(a);
        else if(a == 53 && requisitos_TG2 == 0) sig.add(a);

        if(b == 0) return;

        if(b == 29 && requisitos_ia1 == 0) sig.add(b);
        else if(b == 53 && requisitos_TG2 == 0) sig.add(b);
    }
    public void quitar(ArrayList<Integer> q){
        if(q.contains(cola1.get(0))) cola1.remove(0);
        if(q.contains(cola2.get(0))) cola2.remove(0);
        if(q.contains(cola3.get(0))) cola3.remove(0);
        if(q.contains(cola4.get(0))) cola4.remove(0);
        if(q.contains(cola5.get(0))) cola5.remove(0);
        if(q.contains(cola6.get(0))) cola6.remove(0);
        if(q.contains(cola7.get(0))) cola7.remove(0);
    }
    public ArrayList<Integer> getSinTomar(){
        ArrayList<Integer> sinTomar = new ArrayList<>();
        sinTomar.addAll(cola1);
        sinTomar.addAll(cola2);
        sinTomar.addAll(cola3);
        sinTomar.addAll(cola4);
        sinTomar.addAll(cola5);
        sinTomar.addAll(cola6);
        sinTomar.addAll(cola7);

        Collections.sort(sinTomar);

        while(sinTomar.get(0) == 0) sinTomar.remove(0);

        return sinTomar;
    }
}
