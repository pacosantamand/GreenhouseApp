package edu.itsco.proyectotesis.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import edu.itsco.proyectotesis.modelos.Tarea;


/**
 * Created by Paco on 10/02/2016.
 */
public class TareaContract {
    public static final String TABLE_NAME = "Tareas";
    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";
    public static final String DATE_TYPE = "datetime";

    private MyGreenHouseDBHelper dbHelper;
    private SQLiteDatabase database;
    private static TareaContract tareaContract;

    private TareaContract(Context context) {
        dbHelper = new MyGreenHouseDBHelper(context);
        database = dbHelper.getWritableDatabase();
    }


    public static TareaContract getInstance(Context context){
        if(tareaContract==null){
            tareaContract = new TareaContract(context);
        }
        return tareaContract;
    }

    public static abstract class TareaColumn implements BaseColumns {
        public static final String COLUMN_NAME_ID = "_id";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_DESCRIPCION = "descripcion";
        public static final String COLUMN_NAME_REALIZADA = "realizada";
        public static final String COLUMN_NAME_FECHA = "fecha_realizacion";
        public static final String COLUMN_NAME_IMAGEN_PATH = "imagen_path";
    }

    public static String CREATE_TABLE_TAREA = "create table "+TABLE_NAME+"("
            +TareaColumn.COLUMN_NAME_ID+" "+INT_TYPE+" primary key autoincrement, "
            +TareaColumn.COLUMN_NAME_NOMBRE+" "+STRING_TYPE+" not null, "
            +TareaColumn.COLUMN_NAME_DESCRIPCION+" "+STRING_TYPE+" not null, "
            +TareaColumn.COLUMN_NAME_REALIZADA+" "+INT_TYPE+" default 0, "
            +TareaColumn.COLUMN_NAME_FECHA+" "+DATE_TYPE+" default 0, "
            +TareaColumn.COLUMN_NAME_IMAGEN_PATH+" "+STRING_TYPE+");";


    public void saveTareaRow(Tarea tarea){
        ContentValues values = new ContentValues();
        values.put(TareaColumn.COLUMN_NAME_NOMBRE, tarea.getNombre());
        values.put(TareaColumn.COLUMN_NAME_DESCRIPCION, tarea.getDescripcion());
        if(tarea.getImagenPath()!=null){
            values.put(TareaColumn.COLUMN_NAME_IMAGEN_PATH, tarea.getImagenPath());
        }
        values.put(TareaColumn.COLUMN_NAME_FECHA,tarea.getFechaRealizacion().getTime());
        values.put(TareaColumn.COLUMN_NAME_REALIZADA,tarea.isRealizada() ? 1 : 0);
        database.insert(TABLE_NAME,TareaColumn.COLUMN_NAME_IMAGEN_PATH,values);
    }



    public void updateTareaRow(Tarea tarea){
        ContentValues values = new ContentValues();

        values.put(TareaColumn.COLUMN_NAME_NOMBRE, tarea.getNombre());
        values.put(TareaColumn.COLUMN_NAME_DESCRIPCION, tarea.getDescripcion());
        if(tarea.getImagenPath()!=null){
            values.put(TareaColumn.COLUMN_NAME_IMAGEN_PATH, tarea.getImagenPath());
        }
        values.put(TareaColumn.COLUMN_NAME_REALIZADA,tarea.isRealizada() ? 1 : 0);
        String selection = TareaColumn.COLUMN_NAME_ID + " = ?";
        String[] selectionArgs = { Integer.toString(tarea.getId()) };

        database.update(TABLE_NAME, values, selection, selectionArgs);

    }

    public int deleteTareaRow(int id){
        String selection = TareaColumn.COLUMN_NAME_ID + " = ?";
        String[] selectionArgs = { Integer.toString(id) };
       // TareasImagen.eliminarImagen(getTarea(id).getImagenPath());
        int eliminado = database.delete(TABLE_NAME, selection, selectionArgs);
        return  eliminado;
    }

    public Cursor getAllTareas(){
        return database.rawQuery("select * from "+TABLE_NAME,null);
    }

    public List<Tarea> getListTareas(){
        List<Tarea> tareaList = new ArrayList<>();
        Cursor cursor = getAllTareas();
        while(cursor.moveToNext()){
            Tarea t = new Tarea();
            int tareaId =  cursor.getInt(cursor.getColumnIndex
                    (TareaColumn.COLUMN_NAME_ID));
            String nombreTarea = cursor.getString(cursor.getColumnIndex
                    (TareaColumn.COLUMN_NAME_NOMBRE));
            String descripcion = cursor.getString(cursor.getColumnIndex
                    (TareaColumn.COLUMN_NAME_DESCRIPCION));
            String imagenPath = cursor.getString(cursor.getColumnIndex
                    (TareaColumn.COLUMN_NAME_IMAGEN_PATH));
            boolean realizada = cursor.getInt(cursor.getColumnIndex(
                    TareaColumn.COLUMN_NAME_REALIZADA))==1;

            t.setId(tareaId);
            t.setNombre(nombreTarea);
            t.setDescripcion(descripcion);
            t.setImagenPath(imagenPath);
            t.setRealizada(realizada);
            tareaList.add(t);
        }
        return tareaList;
    }

    private Cursor getTareaCursor(int id){
        String query = "select * from "+TABLE_NAME+" WHERE _id = ?";
        return database.rawQuery(query,new String[]{Integer.toString(id)} );
    }

    public Tarea getTarea(int id){
        Cursor cursorTarea = getTareaCursor(id);
        Tarea t = new Tarea();
        while(cursorTarea.moveToNext()){
            int tareaId =  cursorTarea.getInt(cursorTarea.getColumnIndex
                    (TareaColumn.COLUMN_NAME_ID));
            String nombreTarea = cursorTarea.getString(cursorTarea.getColumnIndex
                    (TareaColumn.COLUMN_NAME_NOMBRE));
            String descripcion = cursorTarea.getString(cursorTarea.getColumnIndex
                    (TareaColumn.COLUMN_NAME_DESCRIPCION));
            String imagenPath = cursorTarea.getString(cursorTarea.getColumnIndex
                    (TareaColumn.COLUMN_NAME_IMAGEN_PATH));
            boolean realizada = cursorTarea.getInt(cursorTarea.getColumnIndex(TareaColumn.COLUMN_NAME_REALIZADA))==1;
            t.setId(tareaId);
            t.setNombre(nombreTarea);
            t.setDescripcion(descripcion);
            t.setImagenPath(imagenPath);
            t.setRealizada(realizada);
        }
        return t;
    }
}
