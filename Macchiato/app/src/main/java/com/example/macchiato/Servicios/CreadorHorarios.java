package com.example.macchiato.Servicios;
import com.example.macchiato.Models.Clase;
import com.example.macchiato.Models.Grupo;
import com.example.macchiato.Models.Materia;

import java.util.ArrayList;

public class CreadorHorarios {
    public ArrayList<Grupo> crearHorario(ArrayList<Materia> materias){
        int materiaActual = 0;
        ArrayList<Grupo> horarioActual = new ArrayList<>();
        ArrayList<Grupo> horarioSeleccionado = new ArrayList<>();

        permutacion(materias, materiaActual, horarioActual, horarioSeleccionado);

        return horarioSeleccionado;
    }

    public void permutacion(ArrayList<Materia> materias, int materiaActual, ArrayList<Grupo> horarioActual, ArrayList<Grupo> horarioSeleccionado){
        if(horarioSeleccionado.size() < horarioActual.size()){
            copiarHorario(horarioActual, horarioSeleccionado);
        }

        if(materiaActual < materias.size() && horarioSeleccionado.size() < materias.size()){
            for(Grupo grupoActual: materias.get(materiaActual).getGrupos()){
                //anadir
                boolean hayChoque = false;
                for(Grupo grupoDelHorario: horarioActual){
                    if(hayChoque(grupoActual, grupoDelHorario)){
                        hayChoque = true;
                        break;
                    }
                }

                if(!hayChoque){
                    horarioActual.add(grupoActual);
                }

                permutacion(materias,materiaActual + 1, horarioActual, horarioSeleccionado);

                //eliminamos para que otro pueda entrar si es que fue anadido
                if(!hayChoque){
                    horarioActual.remove(horarioActual.size() - 1);
                }
            }
        }
    }

    public void copiarHorario(ArrayList<Grupo> horarioActual, ArrayList<Grupo> horarioSeleccionado){
        horarioSeleccionado.clear();

        for(Grupo g: horarioActual){
            horarioSeleccionado.add(g);
        }
    }

    public boolean hayChoque(Grupo g1, Grupo g2){
        boolean respuesta = false;

        for(Clase c: g1.getClases()){
            for(Clase c1: g2.getClases()){
                if(c.getDia() == c1.getDia() && c.getHoraInicio().equals(c1.getHoraInicio())){
                    respuesta = true;
                    break;
                }
            }

            if(respuesta){
                break;
            }
        }

        return respuesta;
    }
}
