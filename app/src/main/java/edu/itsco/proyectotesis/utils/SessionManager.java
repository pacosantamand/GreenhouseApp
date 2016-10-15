package edu.itsco.proyectotesis.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import edu.itsco.proyectotesis.modelos.User;

/**
 * Created by Paco on 16/02/2016.
 */
public class SessionManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    private static SessionManager mSessionManager;

    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "MyGreenLogData";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_USER_ID = "id";
    public static final String KEY_USER_NAME = "name";
    public static final String KEY_USER_EMAIL = "email";
    public static final String KEY_INVERNADERO_ID ="invernadero_id";
    public static final String KEY_INVERNADERO_NOMBRE ="invernadero_nombre";

    // Constructor
    private SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();

    }

    public static SessionManager getInstance(Context context){
        if(mSessionManager==null){
            mSessionManager = new SessionManager(context);
        }
        return mSessionManager;
    }

    public void setUserDataSession(int id, String name, String email, int invernaderoID, String invernaderoNombre){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        editor.putInt(KEY_USER_ID, id);
        editor.putString(KEY_USER_NAME, name);
        editor.putString(KEY_USER_EMAIL, email);
        editor.putInt(KEY_INVERNADERO_ID, invernaderoID);
        editor.putString(KEY_INVERNADERO_NOMBRE,invernaderoNombre);

        // commit changes
        editor.commit();
    }


    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_USER_ID, Integer.toString(pref.getInt(KEY_USER_ID, 0)));
        user.put(KEY_USER_NAME, pref.getString(KEY_USER_NAME, null));
        user.put(KEY_USER_EMAIL, pref.getString(KEY_USER_EMAIL, null));
        user.put(KEY_INVERNADERO_NOMBRE, pref.getString(KEY_INVERNADERO_NOMBRE, "Invernadero de Alta"));
        user.put(KEY_INVERNADERO_ID, Integer.toString(pref.getInt(KEY_INVERNADERO_ID, 0)));
        // return user
        return user;
    }

    public User getUserDataSession(){
        User user = new User();
        user.setId(pref.getInt(KEY_USER_ID, 0));
        user.setNombre(pref.getString(KEY_USER_NAME, null));
        user.setEmail(pref.getString(KEY_USER_EMAIL, null));
        user.setInvernaderoId(pref.getInt(KEY_INVERNADERO_ID, 0));
        user.setInvernaderoString(pref.getString(KEY_INVERNADERO_NOMBRE, null));
        return user;
    }



//    public void logoutUser(){
//
//        editor.clear();
//        editor.commit();
//        Intent i = new Intent(context, LoginActivity.class);
//
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        context.startActivity(i);
//    }

    public boolean isLogged(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
