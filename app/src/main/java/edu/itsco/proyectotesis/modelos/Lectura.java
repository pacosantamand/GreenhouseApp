package edu.itsco.proyectotesis.modelos;

import java.util.Date;

/**
 * Created by paco on 3/09/16.
 */
public class Lectura {

    private double valorLectura;
    private Date fechaLectura;
    private Variable variableLectura;


    public Lectura(double valorLectura,Date fechaLectura, String nombreVariable){
        this(valorLectura,fechaLectura,new Variable(nombreVariable));
    }

    public Lectura(double valorLectura, Date fechaLectura, Variable variableLectura) {
        this.setValorLectura(valorLectura);
        this.setFechaLectura(fechaLectura);
        this.setVariableLectura(variableLectura);
    }


    public double getValorLectura() {
        return valorLectura;
    }

    public void setValorLectura(double valorLectura) {
        this.valorLectura = valorLectura;
    }

    public Date getFechaLectura() {
        return fechaLectura;
    }

    public void setFechaLectura(Date fechaLectura) {
        this.fechaLectura = fechaLectura;
    }

    public Variable getVariableLectura() {
        return variableLectura;
    }

    public void setVariableLectura(Variable variableLectura) {
        this.variableLectura = variableLectura;
    }
}
