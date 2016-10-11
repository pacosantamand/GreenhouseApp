package edu.itsco.proyectotesis.utils;

import android.graphics.Color;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

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

    private ArrayList<Entry> getValores(){
        ArrayList<Entry> valores = new ArrayList<>();
        for(int i=0;i<lecturas.size();i++) {
            valores.add(new Entry(i,lecturas.get(i).getValorLectura()));
        }
        return valores;
    }

    public String getTipo(){
        return this.tipo;
    }

    public ArrayList<String> getFechas(){
        ArrayList<String> fechas = new ArrayList<>();
        for(Lectura lectura: lecturas) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
            fechas.add(sdf.format(lectura.getFechaLectura()));
        }
        return fechas;
    }

    public LineData getData() {

        LineDataSet set1;

        set1 = new LineDataSet(getValores(),"");

        set1.setColor(Color.parseColor("#ff6d00"));
        set1.setCircleColor(Color.parseColor("#ff6d00"));
        set1.setLineWidth(3f);
        set1.setCircleRadius(5f);
        set1.setDrawCircleHole(true);
        set1.setDrawValues(false);
        set1.setValueTextSize(12f);
        set1.setDrawFilled(true);
        set1.setFillColor(Color.parseColor("#ffe0b2"));
        set1.setDrawHighlightIndicators(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(dataSets);
        return data;
    }
}
