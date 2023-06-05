package com.mradking.mradkingshop.kirana_store.franchige.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;

public class Sub_cribe_act_2 extends Activity {

    CardView sub_bt,start_bt;
    String key;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    String curret_date_st;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mr_adking_bussiness_subcribe_act);

        sub_bt=(CardView)findViewById(R.id.sub_bt);
        start_bt=(CardView)findViewById(R.id.start_bt);
         firebaseFirestore= FirebaseFirestore.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);


        sub_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setMessage("please wait..........");
                progressDialog.show();
                progressDialog.setCanceledOnTouchOutside(false);
                firebaseFirestore.collection("Code").document("1").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete( Task<DocumentSnapshot> task) {


                        if (task.isComplete()) {

                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists() && documentSnapshot != null) {

                                String downloading_url=documentSnapshot.getString("chanel_link");

                                sub_bt.setVisibility(View.GONE);
                                start_bt.setVisibility(View.VISIBLE);
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(downloading_url));
                                startActivity(i);
                                progressDialog.dismiss();



                            }}


                    }
                });






            }
        });


        start_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progressDialog.setMessage("please wait..........");
                progressDialog.show();
                progressDialog.setCanceledOnTouchOutside(false);
                firebaseFirestore.collection("user").document(firebaseAuth.getUid().toString()).update("bussines_account_status","add_fill").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                            Intent intent=new Intent(Sub_cribe_act_2.this,Frenchige_dasboard.class);
                            startActivity(intent);
                             finish();
                        progressDialog.dismiss();

                    }
                });




            }
        });




    }
}
