package com.mradking.mradkingshop.kirana_store.franchige.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.activity.Splash;

public class account extends Activity {

    LinearLayout support_bt,share_bt,log_out_bt;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    TextView name;
    String reffer_code_st;


    @Override
    public void onBackPressed() {

        Intent intent=new Intent(account.this,Frenchige_dasboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
        finish();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acount);

        support_bt= findViewById(R.id.support_bt);
        name= findViewById(R.id.name);


        share_bt= findViewById(R.id.share_bt);
        log_out_bt= findViewById(R.id.log_out_bt);

        progressDialog=new ProgressDialog(account.this);
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

        name.setText("User Name");


        support_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject=" + "I Need Help"+ "&body=" + "&to=" + "adking4l7@gmail.com");
                mailIntent.setData(data);
                startActivity(Intent.createChooser(mailIntent, "Send mail..."));

            }
        });

        log_out_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

                String status = preferences.getString("sign_status", "sign_status");


                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("sign_status", "logout");
                editor.apply();
                editor.commit();

               Intent intent=new Intent(account.this,Splash.class);
               startActivity(intent);
               finish();


            }
        });



    }



}
