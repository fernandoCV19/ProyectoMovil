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
    private User userProfile;
    StorageReference storageReference;
    TextView usuarioShow,correoShow;
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
                userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    //Toast.makeText(InicioActivity2.this, "log in", Toast.LENGTH_SHORT).show();
                    String userUser= userProfile.getUserName();
                    String userEmail= userProfile.getEmail();

                    usuarioShow.setText(userUser);
                    correoShow.setText(userEmail);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(InicioActivity2.this, "error", Toast.LENGTH_SHORT).show();
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


}