package edu.itsco.proyectotesis.modelos;

import java.util.Date;

/**
 * Created by paco on 3/09/16.
 */
public class Lectura {

    private double valorLectura;
    private Date fechaLectura;


    public Lectura(double valorLectura, Date fechaLectura) {
        this.setValorLectura(valorLectura);
        this.setFechaLectura(fechaLectura);
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

}
