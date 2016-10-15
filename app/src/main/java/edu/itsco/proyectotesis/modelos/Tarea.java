package edu.itsco.proyectotesis.modelos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Paco on 10/02/2016.
 */
public class Tarea implements Parcelable {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Date fechaRealizacion;
    private String imagenPath;
    private boolean realizada;

    public Tarea(){

    }

    public Tarea(Integer id, String nombre, boolean realizada){

        this.id = id;
        this.nombre=nombre;
        this.realizada=realizada;
    }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenPath() {
        return imagenPath;
    }

    public void setImagenPath(String imagenPath) {
        this.imagenPath = imagenPath;
    }

    public Date getFechaRealizacion(){
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion){
        this.fechaRealizacion = fechaRealizacion;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(1);
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeString(imagenPath);
        dest.writeLong(fechaRealizacion.getTime());
        dest.writeByte((byte) (realizada ? 1 : 0));
    }

    public static final Parcelable.Creator<Tarea> CREATOR = new Parcelable.Creator<Tarea>(){


        @Override
        public Tarea createFromParcel(Parcel source) {
            return new Tarea(source);
        }

        @Override
        public Tarea[] newArray(int size) {
            return new Tarea[size];
        }
    };

    private Tarea(Parcel source){
        this.id = source.readInt();
        this.nombre = source.readString();
        this.descripcion = source.readString();
        this.imagenPath = source.readString();
        this.fechaRealizacion = new Date(source.readLong());
        this.realizada = source.readByte() == 1;
    }

    @Override
    public String toString(){
        return this.nombre;
    }

}
