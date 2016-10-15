package edu.itsco.proyectotesis;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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
import java.util.Date;

import edu.itsco.proyectotesis.adapters.GraficaAdapter;
import edu.itsco.proyectotesis.modelos.Lectura;
import edu.itsco.proyectotesis.utils.ChartPopup;
import edu.itsco.proyectotesis.utils.EjeXValueFormatter;
import edu.itsco.proyectotesis.utils.GraficaData;
import edu.itsco.proyectotesis.utils.TipoGrafica;


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

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private GraficaAdapter mAdapter;


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

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<GraficaData> mDataSet = new ArrayList<>();
        mDataSet.add(new GraficaData(TipoGrafica.HORA,getDummyData(5,40)));
        mDataSet.add(new GraficaData(TipoGrafica.SEMANA,getDummyData(7,43)));
        mDataSet.add(new GraficaData(TipoGrafica.MES,getDummyData(15,45)));


        // specify an adapter (see also next example)
        mAdapter = new GraficaAdapter(mDataSet);
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

    private ArrayList<Lectura> getDummyData(int count, float range){
        ArrayList<Lectura> dummyData = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 20;
            dummyData.add(new Lectura(val, new Date()));
        }
        return dummyData;
    }


}
