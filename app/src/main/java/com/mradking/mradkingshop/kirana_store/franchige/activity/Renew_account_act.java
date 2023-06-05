package com.mradking.mradkingshop.kirana_store.franchige.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;

public class Renew_account_act extends Activity {

    CardView renew_bt;
    TextView dis_txt,this_year_earning,below_message_pecentage,renew_fee;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    String dis_txt_st;
    String renew_amt;
    String curret_date_st;
    String earning_in_this_year_st,renew_fee_starter_st,renew_fee_buniess_st,plan_name_st;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.renew_message);

        renew_bt=(CardView)findViewById(R.id.buy_bt);
        firebaseFirestore=FirebaseFirestore.getInstance();
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();

        dis_txt=(TextView)findViewById(R.id.renew_message);


        renew_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(Renew_account_act.this, Plan_choess_act.class);
                startActivity(intent);
                progressDialog.dismiss();


            }
        });






    }
}
