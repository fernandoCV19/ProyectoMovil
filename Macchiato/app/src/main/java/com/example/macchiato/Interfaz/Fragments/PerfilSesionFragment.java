package com.example.macchiato.Interfaz.Fragments;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.macchiato.Interfaz.Activities.CambiarPerfilActivity;
import com.example.macchiato.Interfaz.Activities.HistorialAcademicoActivity;
import com.example.macchiato.Interfaz.Activities.Navigation_bottom;
import com.example.macchiato.Models.User;
import com.example.macchiato.R;
import com.example.macchiato.Servicios.LectorFichero;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class PerfilSesionFragment extends Fragment {

    private FirebaseAuth auth;
    private DatabaseReference reference;
    private User userProfile;
    TextView usuarioShow,correoShow;
    Intent intentHistorial;

    public PerfilSesionFragment() {
    }

    /**
     *inicializa las variables y asigna las funciones a los botones al crearse
     * asigna el contenido del layout fragmet_perfil_sesion
     * obtiene los elementos del layout y los guarda para poder trabajar con ellos desde codigo
     * inicializa la Firebase, obteniendo una instancia y una referencia de su estado actual
     * crea un lectorFichero para obtener los datos del usuario actual que mostrara en este fragment
     * verifica que el email y username del usuario no sean nulos
     * asigna la funcion de descargar cronograma al boton descargar
     * solicitara permiso de acceder a la carpeta de descargas en caso que aun no lo tenga autorizado
     * asigna la funcion de cerrar cuenta al boton de cerrar sesion
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil_sesion, container, false);
        auth=FirebaseAuth.getInstance();
        reference=FirebaseDatabase.getInstance().getReference();
        usuarioShow= (TextView) view.findViewById(R.id.usuarioActual_id);
        correoShow= (TextView) view.findViewById(R.id.correoActual_id);
        intentHistorial=new Intent(getActivity(), HistorialAcademicoActivity.class);


        LectorFichero lectorFichero = new LectorFichero();
        Map<String,Object> map = lectorFichero.devolverMapa(getContext(), "registro.json");
        if(map.get("userName")!=null && map.get("email")!=null) {
            usuarioShow.setText(map.get("userName").toString());
            correoShow.setText(map.get("email").toString());
        }
        Button btnDes = (Button) view.findViewById(R.id.id_descargas);
        btnDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                            PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

                        requestPermissions(permissions,1000);

                    }else {
                        startDownloading();
                    }
                }
                else {
                    startDownloading();
                }       
            }
        });


        Button btnLanzarActivity = (Button) view.findViewById(R.id.buttonIniciarSesion);

        btnLanzarActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auth!=null){
                    auth.signOut();
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
                Intent intent = new Intent(getActivity(), CambiarPerfilActivity.class);
                startActivity(intent);
            }
        });


        Button historial= (Button) view.findViewById(R.id.buttonHistorial);
        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentHistorial);
            }
        });
        return view;
    }

    /**
     * obtiene el link del cronograma universitario desde la Firebase
     * en caso de obtenerlo correctamente empieza a descargarlo y lo guarda en la carpeta de "Descargas"
     * permite realizar la descarga ya sea con senal WIFI o con datos moviles
     */
    private  void  startDownloading(){
        reference.child("UMSS").child("cronograma").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                String url= (String) snapshot.getValue().toString();
                DownloadManager.Request request= new DownloadManager.Request(Uri.parse(url));
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                request.setTitle("Download");
                request.setDescription("Descargando archivo");
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,""+System.currentTimeMillis());
                DownloadManager downloadManager = (DownloadManager)getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });
    }





}