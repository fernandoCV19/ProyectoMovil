package com.example.macchiato.Models;

import android.app.Application;
import android.content.Context;

import com.example.macchiato.EditJson;
import com.example.macchiato.Servicios.LectorFichero;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class GlobalApplication extends Application {
    private static Context appContext;

    //public static FirebaseAuth auth;
    //public static FirebaseUser user;
    //public static DatabaseReference reference;
    //public static User userProfile;
    //public static Map<String, Object> jsonMap;
    //public static String userAct;
    //public static String emailAct;
    //public static EditJson editJson;


    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        LectorFichero lectorFichero = new LectorFichero();
        try {
            lectorFichero.leerFichero(getApplicationContext(), "registro.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //auth=FirebaseAuth.getInstance();
        //reference= FirebaseDatabase.getInstance().getReference("User");
        //user= FirebaseAuth.getInstance().getCurrentUser();
        //userProfile= new User();
        //editJson= new EditJson();
        //editJson.crearJson();

        /*try {
            //jsonMap = new Gson().fromJson(editJson.leerFichero(), new TypeToken<HashMap<String, Object>>() {}.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        //userAct = (String) jsonMap.get("userName");
        //emailAct = (String) jsonMap.get("email");
    }

    public static Context getAppContext() {
        return appContext;
    }

}