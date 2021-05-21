package com.example.macchiato.Models;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.macchiato.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class GlobalApplication extends Application {
    private static Context appContext;

    public static FirebaseAuth auth;
    public static FirebaseUser user;
    public static DatabaseReference reference;
    public static User userProfile;
    public static Map<String, Object> jsonMap;
    public static String userAct;
    public static String emailAct;


    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        auth=FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference("User");
        user= FirebaseAuth.getInstance().getCurrentUser();
        userProfile= new User();

        jsonMap = new Gson().fromJson(leerFichero(), new TypeToken<HashMap<String, Object>>() {}.getType());
        userAct = (String) jsonMap.get("userName");
        emailAct = (String) jsonMap.get("email");
        //Toast.makeText(appContext, userAct, Toast.LENGTH_SHORT).show();
        //Toast.makeText(appContext, emailAct, Toast.LENGTH_SHORT).show();
    }
    private String leerFichero(){
        FileInputStream fileInputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        String texto ="";
        try {
            fileInputStream = openFileInput("registro.json");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while((texto = bufferedReader.readLine())!= null){
                stringBuilder.append(texto);
            }
        }catch (Exception e){}
        finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                }catch (Exception e){}
            }
        }
        return stringBuilder.toString();
    }
    public static Context getAppContext() {
        return appContext;
    }

}