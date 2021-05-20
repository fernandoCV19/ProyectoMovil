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

public class GlobalApplication extends Application {
    private static Context appContext;

    public static FirebaseAuth auth;
    public static FirebaseUser user;
    public static DatabaseReference reference;
    public static User userProfile;


    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        auth=FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference("User");
        user= FirebaseAuth.getInstance().getCurrentUser();
        userProfile= new User();
        if(user!=null){
            reference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    userProfile = snapshot.getValue(User.class);
                    if(userProfile != null){

                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }
    public static Context getAppContext() {
        return appContext;
    }

}