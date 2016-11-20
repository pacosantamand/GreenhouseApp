package edu.itsco.proyectotesis.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.itsco.proyectotesis.R;
import edu.itsco.proyectotesis.modelos.Tarea;

/**
 * Created by paco on 14/10/16.
 */

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.ViewHolder> {

    private List<Tarea> tareaList;

    public TareaAdapter(List<Tarea> tareaList){
        this.tareaList = tareaList;
    }

    @Override
    public TareaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grafica_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TareaAdapter.ViewHolder holder, int position) {
        holder.lbTarea.setText(tareaList.get(position).getNombre());
        holder.lbRealizada.setText(
                tareaList.get(position).isRealizada() ? "Realizada":"En espera");
    }

    @Override
    public int getItemCount() {
        return tareaList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView lbTarea;
        TextView lbRealizada;
        ImageView imgTarea;

        public ViewHolder(View v){
            super(v);
            lbTarea = (TextView) v.findViewById(R.id.lb_tarea_titulo);
            lbRealizada = (TextView) v.findViewById(R.id.lb_realizada);
            imgTarea = (ImageView) v.findViewById(R.id.img_tarea);
        }
    }
}
