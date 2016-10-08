package edu.itsco.proyectotesis.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;

import edu.itsco.proyectotesis.modelos.Lectura;

/**
 * Created by Paco on 07/10/16.
 */

public class GraficaAdapter extends RecyclerView.Adapter<GraficaAdapter.ViewHolder> {


    ArrayList<Lectura> lecturas;

    public GraficaAdapter(ArrayList<Lectura> lecturas){
        this.lecturas = lecturas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public static LineChart mChart;

        public ViewHolder(View v){
            super(v);
        }
    }
}