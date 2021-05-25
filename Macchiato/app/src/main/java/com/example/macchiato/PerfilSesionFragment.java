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
import com.example.macchiato.Models.User;
import com.example.macchiato.Servicios.LectorFichero;
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
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PerfilSesionFragment extends Fragment {

    private FirebaseAuth auth;
    private DatabaseReference reference;
    private User userProfile;
    TextView usuarioShow,correoShow;
    ImageView profileImage;
    public PerfilSesionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_sesion, container, false);
        auth=FirebaseAuth.getInstance();
        usuarioShow= (TextView) view.findViewById(R.id.usuarioActual_id);
        correoShow= (TextView) view.findViewById(R.id.correoActual_id);
        //user= FirebaseAuth.getInstance().getCurrentUser();
        //usuarioShow.setText(GlobalApplication.userAct);
        //correoShow.setText(GlobalApplication.emailAct);

        LectorFichero lectorFichero = new LectorFichero();
        Map<String,Object> map = lectorFichero.devolverMapa(getContext());
        usuarioShow.setText(map.get("userName").toString());
        correoShow.setText(map.get("email").toString());



     Button btnLanzarActivity = (Button) view.findViewById(R.id.buttonIniciarSesion);
        btnLanzarActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auth!=null){
                    auth.signOut();
                    //GlobalApplication.editJson.crearJson();
                    Intent intent = new Intent(getActivity(),Navigation_bottom.class);
                    /*LectorFichero lector = new LectorFichero();
                    lector.crearJson(getApplicationContext(),userProfile);
                    try {
                        lector.leerFichero(getApplicationContext());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/
                    startActivity(intent);
                    getActivity().finishAffinity();
                }

            }
        });

        Button btnEdit= (Button) view.findViewById(R.id.buttonEditar);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getActivity(),CambiarPerfilActivity.class);
                startActivity(intent);*/
               /*LinkedTreeMap aux = (LinkedTreeMap) map.get("materiasAprobadas");
                Toast.makeText(getContext(), aux.size(), Toast.LENGTH_SHORT).show();*/

                String a = map.get("materiasActuales").toString();
                HashMap actuales = new Gson().fromJson(a, new TypeToken<HashMap<String, Object>>() {}.getType());
                Toast.makeText(getContext(), actuales.get("a").toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), actuales.get("b").toString(), Toast.LENGTH_SHORT).show();
            }
        });

        Button historial= (Button) view.findViewById(R.id.buttonHistorial);
        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),HistorialAcademicoActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }




}