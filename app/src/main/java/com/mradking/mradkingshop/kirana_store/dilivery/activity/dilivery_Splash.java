package com.mradking.mradkingshop.kirana_store.dilivery.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.activity.Kiranan_shopes_list;
import com.mradking.mradkingshop.kirana_store.custmer.activity.Otp_loging_act;
import com.mradking.mradkingshop.kirana_store.custmer.activity.Splash;
import com.mradking.mradkingshop.kirana_store.custmer.activity.kirana_home_main_act;
import com.mradking.mradkingshop.kirana_store.custmer.intface.Firbase_Doc_Call;
import com.mradking.mradkingshop.kirana_store.custmer.utility.utilityX;

import java.util.List;

public class dilivery_Splash extends Activity {
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    utilityX UtilityX;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        firebaseAuth=FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser()==null){

            Intent intent=new Intent(dilivery_Splash.this, dilivery_loging.class);

            startActivity(intent);
            finish();

        }else {

            Intent intent=new Intent(dilivery_Splash.this, dilivery_dasboard.class);

            startActivity(intent);
            finish();

        }



    }



}
