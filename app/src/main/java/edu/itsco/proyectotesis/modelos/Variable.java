package edu.itsco.proyectotesis.modelos;

/**
 * Created by paco on 3/09/16.
 */
public class Variable {

    private String nombre;
    private String unidadMedida;

    public Variable(){

    }

    public Variable(String nombre){
        this.nombre = nombre;
    }

    public Variable(String nombre, String unidadMedida) {
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
}
