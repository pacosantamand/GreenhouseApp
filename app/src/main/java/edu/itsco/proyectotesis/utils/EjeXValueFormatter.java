package edu.itsco.proyectotesis.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.AxisValueFormatter;

/**
 * Created by paco on 4/09/16.
 */
public class EjeXValueFormatter implements AxisValueFormatter{

    public static final int FORMATO_DIA = 1;
    public static final int FORMATO_SEMANA = 2;
    public static final int FORMATO_MES = 3;

    private String[] values;

    private String[] diasSemana = new String[]{"Do","Lu","Ma","Mi","Ju","Vi","Sa"};

    public EjeXValueFormatter(int formato){
       switch(formato){
           case FORMATO_DIA:
               values = new String[24];
               for(int i=0;i<values.length;i++){
                   values[i] = String.format("%d:00",i);
               }
               break;
           case FORMATO_SEMANA:
               values = diasSemana;
               //values = new String[7];
//               for(int i=0;i<values.length;i++){
//                   values[i] = diasSemana[i];
//               }
               break;
           case FORMATO_MES:
               values = new String[31];
               for(int i=0;i<values.length;i++){
                   values[i] = String.format("%d",i+1);
               }
               break;
       }
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        int index = (int) value;
        if(value>=values.length)
            return values[values.length-1];
        return values[(int)value];
    }

    @Override
    public int getDecimalDigits() {
        return 0;
    }
}