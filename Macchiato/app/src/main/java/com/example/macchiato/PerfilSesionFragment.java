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
import android.widget.EditText;
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

import java.net.URI;

public class PerfilSesionFragment extends Fragment {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String thisUserId;
    StorageReference storageReference;
    TextView usuarioShow,correoShow;
    Button cambFoto;
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
        profileImage= (ImageView) view.findViewById(R.id.foto_perfil_id);
        cambFoto= (Button)  view.findViewById(R.id.editPic_btn);
        user= FirebaseAuth.getInstance().getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference profileRef = storageReference.child("profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImage);
            }
        });
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

        cambFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGallery,1000);
            }
        });






        Button btnLanzarActivity = (Button) view.findViewById(R.id.buttonCerrarSesion);
        btnLanzarActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auth!=null){
                    auth.signOut();
                    Intent intent = new Intent(getActivity(),Navigation_bottom.class);
                    startActivity(intent);
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
        StorageReference fileReference = storageReference.child("profile.jpg");
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
}