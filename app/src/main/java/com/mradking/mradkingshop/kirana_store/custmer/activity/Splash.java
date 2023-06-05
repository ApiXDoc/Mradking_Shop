package com.mradking.mradkingshop.kirana_store.custmer.activity;
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
import com.mradking.mradkingshop.kirana_store.custmer.intface.Firbase_Doc_Call;
import com.mradking.mradkingshop.kirana_store.custmer.utility.utilityX;
import com.mradking.mradkingshop.kirana_store.dilivery.activity.dilivery_Splash;


import java.util.List;

public class Splash extends Activity {
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    utilityX UtilityX;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);




        data_set_1();



    }

    private void data_set_1() {

        UtilityX=new utilityX(Splash.this);
        firebaseAuth=FirebaseAuth.getInstance();
        progressBar=(ProgressBar)findViewById(R.id.progres_bar);
        firebaseFirestore=FirebaseFirestore.getInstance();
        progressDialog =new ProgressDialog(this);


        SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        if (preferences.contains("sign_status")) {
            // the "username" key exists in shared preferences
            String username = preferences.getString("sign_status", "");

            if(username.contentEquals("login")){


                requestStoragePermission();


            }else {
                Intent intent=new Intent(Splash.this, Otp_loging_act.class);
                startActivity(intent);
                finish();
            }


            // do something with the username
        }

        else {

            requestStoragePermission();

            // the "username" key does not exist in shared preferences
        }





    }


    private void data_check() {

        DatePicker datePicker=new DatePicker(getApplicationContext());
        String date=datePicker.getDayOfMonth()+"/"+String.valueOf(datePicker.getDayOfMonth()+1)+"/"+datePicker.getYear();

        if(firebaseAuth.getCurrentUser()==null){

            Intent intent=new Intent(Splash.this, Otp_loging_act.class);

            startActivity(intent);
            finish();

        }else {

            MyThread thread = new MyThread();
            thread.start();





        }
    }

    private void data_set() {



        UtilityX.get_detalis("kirana_user_details", firebaseAuth.getCurrentUser().getUid(), new Firbase_Doc_Call() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String shop_selection_status =documentSnapshot.getString("shop_selection_status");


                if(shop_selection_status.contentEquals("no")){

                    Intent intent=new Intent(Splash.this, Kiranan_shopes_list.class);

                    startActivity(intent);
                    finish();

                }else if(shop_selection_status.contentEquals("yes")){

                    String store_uid =documentSnapshot.getString("store_uid");
                    Intent intent=new Intent(Splash.this, kirana_home_main_act.class);
                    intent.putExtra("store_id",store_uid);
                    startActivity(intent);
                    finish();

                }


            }

            @Override
            public void onFailure(String error) {

            }
        });

    }


    private void requestStoragePermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE
                        ,Manifest.permission.ACCESS_FINE_LOCATION

                )
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {

                            data_check();

                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }




    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Splash.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    public class MyThread extends Thread {
        @Override
        public void run() {
            data_set();


        }
    }



}
