package edu.itsco.proyectotesis.modelos;

import java.util.Date;

/**
 * Created by paco on 3/09/16.
 */
public class Lectura {

    private float valorLectura;
    private Date fechaLectura;


    public Lectura(float valorLectura, Date fechaLectura) {
        this.setValorLectura(valorLectura);
        this.setFechaLectura(fechaLectura);
    }

    public float getValorLectura() {
        return valorLectura;
    }

    public void setValorLectura(float valorLectura) {
        this.valorLectura = valorLectura;
    }

    public Date getFechaLectura() {
        return fechaLectura;
    }

    public void setFechaLectura(Date fechaLectura) {
        this.fechaLectura = fechaLectura;
    }

}
