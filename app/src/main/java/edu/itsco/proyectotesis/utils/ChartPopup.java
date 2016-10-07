package edu.itsco.proyectotesis.utils;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

import edu.itsco.proyectotesis.R;

/**
 * Created by paco on 2/09/16.
 */
public class ChartPopup extends MarkerView{
    private TextView txtPopup;
    private String uMedida;

    public ChartPopup(Context context, int layoutResource, String uMedida) {
        super(context, layoutResource);
        txtPopup = (TextView) findViewById(R.id.popup_text);
        this.uMedida = uMedida;
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        //super.refreshContent(e, highlight);
        String valor = String.format("%.2f %s",e.getY(), uMedida);
        txtPopup.setText(valor);
        // this will perform necessary layouting

    }

    @Override
    public int getXOffset(float xpos) {
        //return (int) getX()
        return (-(getWidth() / 2));
    }

    @Override
    public int getYOffset(float ypos) {
        return -getHeight();
    }
}
