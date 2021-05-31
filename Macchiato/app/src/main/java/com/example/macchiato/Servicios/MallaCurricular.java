package com.example.macchiato.Servicios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;

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

        cola1=new ArrayList<>();
        cola1.add(1);
        cola1.add(6);
        cola1.add(16);
        cola1.add(20);
        cola1.add(24);
        cola1.add(33);
        cola1.add(37);
        cola1.add(44);
        cola1.add(53);
        cola2=new ArrayList<>();
        cola2.add(3);
        cola2.add(7);
        cola2.add(13);
        cola2.add(22);
        cola2.add(29);
        cola2.add(34);
        cola2.add(40);
        cola3=new ArrayList<>();
        cola3.add(4);
        cola3.add(8);
        cola3.add(12);
        cola3.add(18);
        cola3.add(29);
        cola3.add(34);
        cola3.add(40);

        cola4=new ArrayList<>();
        cola4.add(5);
        cola4.add(9);
        cola4.add(17);
        cola4.add(21);
        cola4.add(26);
        cola4.add(30);
        cola4.add(37);
        cola4.add(44);
        cola4.add(53);
        cola5=new ArrayList<>();
        cola5.add(5);
        cola5.add(11);
        cola5.add(15);
        cola5.add(23);
        cola5.add(28);
        cola5.add(35);
        cola5.add(38);
        cola5.add(53);
        cola6=new ArrayList<>();
        cola6.add(2);
        cola6.add(10);
        cola6.add(14);
        cola6.add(19);
        cola6.add(25);
        cola6.add(31);
        cola6.add(41);

        cola7=new  ArrayList<>();
        cola7.add(2);
        cola7.add(10);
        cola7.add(14);
        cola7.add(19);
        cola7.add(27);
        cola7.add(32);

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
        aniadir(cola7.get(0), 1,sig);

        Set<String> hs = new HashSet<>();
        hs.addAll((Collection)sig);
        sig.clear();
        sig.addAll((Collection)hs);

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
        else sig.add(a);
    }
    public void quitar(ArrayList<Integer> q){
        if(q.contains(22) || q.contains(18)) requisitos_ia1--;
        if(q.contains(44) || q.contains(43)) requisitos_TG2--;

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

        Set<String> hs = new HashSet<>();
        hs.addAll((Collection)sinTomar);
        sinTomar.clear();
        sinTomar.addAll((Collection)hs);
        return sinTomar;
    }

    public void quitarVarios(ArrayList<Integer> varios){
        if(varios.contains(22) || varios.contains(18)) requisitos_ia1--;
        if(varios.contains(44) || varios.contains(43)) requisitos_TG2--;

        for(int i=0; i<varios.size(); i++){
            if(cola1.contains(varios.get(i))) cola1.remove(varios.get(i));
            if(cola2.contains(varios.get(i))) cola2.remove(varios.get(i));
            if(cola3.contains(varios.get(i))) cola3.remove(varios.get(i));
            if(cola4.contains(varios.get(i))) cola4.remove(varios.get(i));
            if(cola5.contains(varios.get(i))) cola5.remove(varios.get(i));
            if(cola6.contains(varios.get(i))) cola6.remove(varios.get(i));
            if(cola7.contains(varios.get(i))) cola7.remove(varios.get(i));
        }
    }
}
