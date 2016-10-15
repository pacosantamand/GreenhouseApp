package edu.itsco.proyectotesis.modelos;

/**
 * Created by Paco on 17/02/2016.
 */
public class User {

    public Integer id;
    public String nombre;
    public String email;
    public  Integer invernaderoId;
    public String invernaderoString;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getInvernaderoId() {
        return invernaderoId;
    }

    public void setInvernaderoId(Integer invernaderoId) {
        this.invernaderoId = invernaderoId;
    }

    public String getInvernaderoString() {
        return invernaderoString;
    }

    public void setInvernaderoString(String invernaderoString) {
        this.invernaderoString = invernaderoString;
    }
}
