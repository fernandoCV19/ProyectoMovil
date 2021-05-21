package com.example.macchiato;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macchiato.Models.GlobalApplication;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class PerfilSesionFragment extends Fragment {

    //private FirebaseAuth auth;
    //private DatabaseReference reference;
    //private User userProfile;
    TextView usuarioShow,correoShow;
    public PerfilSesionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_sesion, container, false);
       // auth=FirebaseAuth.getInstance();
        usuarioShow= (TextView) view.findViewById(R.id.usuarioActual_id);
        correoShow= (TextView) view.findViewById(R.id.correoActual_id);
        //user= FirebaseAuth.getInstance().getCurrentUser();
        usuarioShow.setText(GlobalApplication.userAct);
        correoShow.setText(GlobalApplication.emailAct);




     Button btnLanzarActivity = (Button) view.findViewById(R.id.buttonIniciarSesion);
        btnLanzarActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GlobalApplication.auth!=null){
                    GlobalApplication.auth.signOut();
                    crearJson();
                    Intent intent = new Intent(getActivity(),Navigation_bottom.class);
                    startActivity(intent);
                    getActivity().finishAffinity();
                }

            }
        });

        Button btnEdit= (Button) view.findViewById(R.id.buttonEditar);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CambiarPerfilActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void crearJson(){
        String myjson = new Gson().toJson(new User());
        Map<String, Object> jsonMap = new Gson().fromJson(myjson, new TypeToken<HashMap<String, Object>>() {}.getType());
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(getActivity().openFileOutput("registro.json", Activity.MODE_PRIVATE));
            archivo.write(myjson);
            archivo.flush();
            archivo.close();
        } catch (IOException e ){
            e.printStackTrace();
        }
    }


}