package edu.itsco.proyectotesis;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import edu.itsco.proyectotesis.utils.ChartPopup;
import edu.itsco.proyectotesis.utils.EjeXValueFormatter;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VariableFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VariableFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ID = "id";
    private static final String ARG_TITLE = "param2";

    // TODO: Rename and change types of parameters
    private int id;
    private String title;

    private LineChart mChart;
    private TextView lbMaxima;
    private TextView lbMinima;
    private TextView lbUltima;


    public VariableFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id Parameter 1.
     * @param title Parameter 2.
     * @return A new instance of fragment VariableFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VariableFragment newInstance(int id, String title) {
        VariableFragment fragment = new VariableFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.id = getArguments().getInt(ARG_ID);
            this.title = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_variable, container, false);
        lbMaxima = (TextView) rootView.findViewById(R.id.lb_maxima);
        lbMinima = (TextView) rootView.findViewById(R.id.lb_minima);
        lbUltima = (TextView) rootView.findViewById(R.id.lb_ultima);

        lbMaxima.setText("44 ºC");
        lbMinima.setText("32 ºC");
        lbUltima.setText("38 ºC");
        mChart = (LineChart) rootView.findViewById(R.id.chart_hora);
        configurarGrafica();
        return rootView;
    }

    public void configurarGrafica(){
        mChart.setDescription("");
        mChart.setNoDataTextDescription("Todavía no hay lecturas");
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
        left.setLabelCount(3, true);

        //Configurando el Popup
        ChartPopup myPopup = new ChartPopup(getActivity(),R.layout.popup_layout," °C");
        mChart.setMarkerView(myPopup);

        //Generando datos y su formato
        setData(5,40);
    }

    public void setData(int count, int range){
        ArrayList<Entry> values = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range) + 3;
            values.add(new Entry(i, val));
        }

        LineDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "title");

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

            // set data
            mChart.setData(data);

        }

        mChart.invalidate();
        mChart.animateX(250, Easing.EasingOption.Linear);
    }

}
