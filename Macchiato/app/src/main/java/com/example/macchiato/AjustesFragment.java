package com.example.macchiato;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.Toast;

import com.example.macchiato.Servicios.CreadorAlarma;

import java.net.CookieManager;
import java.util.ArrayList;

public class AjustesFragment extends Fragment {
    Button btn;

    public AjustesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        CreadorAlarma ca = new CreadorAlarma();
        try {
            ca.crear(getContext());
            ArrayList<Intent> intents ;
            //for(int i=0; i<)
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inflater.inflate(R.layout.fragment_ajustes, container, false);
    }











}