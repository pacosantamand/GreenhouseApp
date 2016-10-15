package edu.itsco.proyectotesis.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Paco on 10/02/2016.
 */
public class MyGreenHouseDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Mygreenhouse.db";
    public static final int DATABASE_VERSION = 1;

    public MyGreenHouseDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TareaContract.CREATE_TABLE_TAREA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
