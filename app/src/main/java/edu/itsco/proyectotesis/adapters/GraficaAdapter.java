package edu.itsco.proyectotesis.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import edu.itsco.proyectotesis.R;
import edu.itsco.proyectotesis.utils.ChartPopup;
import edu.itsco.proyectotesis.utils.EjeXValueFormatter;
import edu.itsco.proyectotesis.utils.GraficaData;

/**
 * Created by Paco on 07/10/16.
 */

public class GraficaAdapter extends RecyclerView.Adapter<GraficaAdapter.ViewHolder> {


    private ArrayList<GraficaData> graficaDatas;
    private String tipoGrafica;
    public GraficaAdapter(ArrayList<GraficaData> graficaDatas){
        this.graficaDatas = graficaDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grafica_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.lbTipo.setText(graficaDatas.get(position).getTipo());
        holder.mChart.setData(graficaDatas.get(position).getData());
    }

    @Override
    public int getItemCount() {
        return graficaDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public LineChart mChart;
        public TextView lbTipo;
        public ViewHolder(View v){
            super(v);
            mChart = (LineChart) v.findViewById(R.id.chart);
            lbTipo = (TextView) v.findViewById(R.id.lb_tipo);
            configurarGrafica(v);
        }

        private void configurarGrafica(View v) {
            mChart.setDescription("");
            mChart.setNoDataTextDescription("Todavía no hay datos");
            mChart.setTouchEnabled(true);
            mChart.setDragEnabled(true);
            mChart.setScaleXEnabled(true);
            mChart.setScaleYEnabled(false);

            //Configurando el ejeX
            XAxis xAxis = mChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setTextSize(14f);
            xAxis.setTextColor(Color.BLACK);
            xAxis.setDrawAxisLine(false);
            xAxis.setDrawGridLines(false);
            xAxis.setGranularity(1f);
            xAxis.setValueFormatter(new EjeXValueFormatter(EjeXValueFormatter.FORMATO_DIA));
            //deshabilitando el ejeY derecho
            mChart.getAxisRight().setEnabled(false);
            //Configurando el ejeY
            YAxis left = mChart.getAxisLeft();
            left.setDrawLabels(true); // no axis labels
            left.setDrawAxisLine(false); // no axis line
            left.setDrawGridLines(false); // no grid lines
            left.setTextColor(Color.BLACK);
            left.setTextSize(12f);
            left.setLabelCount(5, true);

            //Configurando el Popup
            ChartPopup myPopup = new ChartPopup(v.getContext(), R.layout.popup_layout, "");
            mChart.setMarkerView(myPopup);

        }
    }
}