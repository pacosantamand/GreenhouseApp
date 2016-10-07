package edu.itsco.proyectotesis;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import edu.itsco.proyectotesis.utils.ChartPopup;
import edu.itsco.proyectotesis.utils.EjeXValueFormatter;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LecturaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LecturaFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String INDEX_LECTURA = "lectura";
    private static final String TITLE = "title";

    // TODO: Rename and change types of parameters
    private int index;
    private String title;
    private LineChart mChart;

    private Button btnDia;
    private Button btnSemana;
    private Button btnMes;

    public LecturaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param index Parameter 1.
     * @return A new instance of fragment LecturaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LecturaFragment newInstance(int index,String title) {
        LecturaFragment fragment = new LecturaFragment();
        Bundle args = new Bundle();
        args.putInt(INDEX_LECTURA,index);
        args.putString(TITLE,title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(INDEX_LECTURA);
            title = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lectura, container, false);

        mChart = (LineChart) view.findViewById(R.id.chart);

        configurarGrafica();

        btnDia = (Button) view.findViewById(R.id.btn_dia);
        btnSemana = (Button) view.findViewById(R.id.btn_semana);
        btnMes = (Button) view.findViewById(R.id.btn_mes);

        btnDia.setOnClickListener(this);
        btnSemana.setOnClickListener(this);
        btnMes.setOnClickListener(this);

        return view;
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
        xAxis.setDrawAxisLine(true);
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
        ChartPopup myPopup = new ChartPopup(getActivity(),R.layout.popup_layout,"°C");
        mChart.setMarkerView(myPopup);

        //Generando datos y su formato
        setData(5,40);



    }

    private void setData(int count, float range) {

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
            set1 = new LineDataSet(values, title);

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

    @Override
    public void onClick(View v) {
        resetZoom();
        if(v.getId()==btnDia.getId()){
            mChart.getXAxis().setValueFormatter(
                    new EjeXValueFormatter(EjeXValueFormatter.FORMATO_DIA));
            setData(15,45f);
        }else if(v.getId()==btnSemana.getId()){

            mChart.getXAxis().setValueFormatter(
                    new EjeXValueFormatter(EjeXValueFormatter.FORMATO_SEMANA));
            setData(7,40f);
        }else if(v.getId()==btnMes.getId()){

            mChart.getXAxis().setValueFormatter(
                    new EjeXValueFormatter(EjeXValueFormatter.FORMATO_MES));
            setData(22,45f);
        }

       // mChart.notifyDataSetChanged();

    }

    private void resetZoom(){
        mChart.zoom(1f,1f,1f,1f);
        mChart.zoomOut();
    }
}
