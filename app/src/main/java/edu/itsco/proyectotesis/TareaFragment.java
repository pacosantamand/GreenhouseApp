package edu.itsco.proyectotesis;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class TareaFragment extends Fragment implements View.OnClickListener {

    private FloatingActionButton fab;

    public TareaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tarea, container, false);
        fab = (FloatingActionButton) view.findViewById(R.id.fab_nueva_tarea);
        fab.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(),"Add Tarea",Toast.LENGTH_SHORT).show();
    }
}
