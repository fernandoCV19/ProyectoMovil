package com.example.macchiato;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.squareup.picasso.Picasso;

public class PerfilSesionFragment extends Fragment {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String thisUserId;
    StorageReference storageReference;
    TextView usuarioShow,correoShow;
    ImageView profileImage;
    public PerfilSesionFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_sesion, container, false);
        auth=FirebaseAuth.getInstance();
        usuarioShow= (TextView) view.findViewById(R.id.usuarioActual_id);
        correoShow= (TextView) view.findViewById(R.id.correoActual_id);
        user= FirebaseAuth.getInstance().getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference profileRef = storageReference.child("users/"+auth.getCurrentUser().getUid()+"/profile.jpg");
        reference= FirebaseDatabase.getInstance().getReference("User");
        thisUserId=user.getUid();
        reference.child(thisUserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    //Toast.makeText(InicioActivity2.this, "log in", Toast.LENGTH_SHORT).show();
                    String userUser= userProfile.getUserName();
                    String userEmail= userProfile.getEmail();
                    String userName= userProfile.getFullName();

                    usuarioShow.setText(userUser);
                    correoShow.setText(userEmail);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(InicioActivity2.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

<<<<<<< HEAD
        cambFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGallery,1000);
            }
        });

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
=======
     Button btnLanzarActivity = (Button) view.findViewById(R.id.buttonIniciarSesion);
>>>>>>> cb48527ddb5dc80b6cd72259d3bbb73fdc17bd9a
        btnLanzarActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auth!=null){
                    auth.signOut();
                    Intent intent = new Intent(getActivity(),Navigation_bottom.class);
                    startActivity(intent);
                    getActivity().finishAffinity();
                }else{
                    //Toast.makeText(PerfilSesionFragment.this, "error", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                //profileImage.setImageURI(imageUri);
                uploadImagesToFirebase(imageUri);
            }
        }
    }
    private  void uploadImagesToFirebase(Uri imUri){
        StorageReference fileReference = storageReference.child("users/"+auth.getCurrentUser().getUid()+"/profile.jpg");
        fileReference.putFile(imUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //Toast.makeText(getActivity(), "imagen guardada", Toast.LENGTH_SHORT).show();
                fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profileImage);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "error al subir", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private  void  startDownloading(){
        String url ="http://imagenes.fcyt.umss.edu.bo/Cronograma%20Gestion%201-2021v6.pdf";

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
}