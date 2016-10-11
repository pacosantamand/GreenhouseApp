package edu.itsco.proyectotesis.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import edu.itsco.proyectotesis.modelos.Lectura;

/**
 * Created by Paco on 11/10/16.
 */

public class GraficaData {

    private ArrayList<Lectura> lecturas;
    private String tipo;
    public GraficaData(String tipo, ArrayList<Lectura> lecturas){
        this.tipo = tipo;
        this.lecturas = lecturas;
    }

    public ArrayList<Double> getValores(){
        ArrayList<Double> valores = new ArrayList<>();
        for(Lectura lectura: lecturas) {
            valores.add(lectura.getValorLectura());
        }
        return valores;
    }

    public ArrayList<Double> getFechas(){
        ArrayList<String> fechas = new ArrayList<>();
        for(Lectura lectura: lecturas) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
            fechas.add(sdf.format(lectura.getFechaLectura()));
        }
        return getValores();
    }
}
