package com.mradking.mradkingshop.kirana_store.franchige.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;

public class Plan_choess_act extends Activity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    TextView starter_plane_price,busniess_plane_price,renew_fee_starter,renew_fee_busiess;
    Button starter_bt,busiess_bt;
    String curret_date_st;
    String startein_plane_price_st;
    String business_plane_price_st;
    String renew_fee_starter_st;
    String renew_fee_buniess_st;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.investment_layout_sliver_gold);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        progressDialog=new ProgressDialog(this);
        starter_plane_price=(TextView)findViewById(R.id.start_plane_price);
        busniess_plane_price=(TextView)findViewById(R.id.business_plane_price);
        renew_fee_busiess=(TextView)findViewById(R.id.renew_fee_buniess);
        renew_fee_starter=(TextView)findViewById(R.id.renew_fee_starter);
        starter_bt=(Button)findViewById(R.id.starter_bt) ;
        busiess_bt=(Button)findViewById(R.id.buniess_bt) ;

        data_setUp();

        starter_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(Plan_choess_act.this,investment_form.class);
                intent.putExtra("plan","Starter - 3 Month Membership");
                intent.putExtra("paying_amt",startein_plane_price_st);
                startActivity(intent);

            }
        });

      busiess_bt.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {


              Intent intent=new Intent(Plan_choess_act.this,investment_form.class);
              intent.putExtra("plan","Business - 6 Month Membership");
              intent.putExtra("paying_amt",startein_plane_price_st);
              startActivity(intent);

          }
      });


    }

    private void data_setUp() {
        progressDialog.setMessage("Please wait......");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        firebaseFirestore.collection("Code").document("1").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                DocumentSnapshot documentSnapshot=task.getResult();

                startein_plane_price_st=documentSnapshot.getString("starter_plane_price");
                business_plane_price_st=documentSnapshot.getString("busines_plane_price");



                starter_plane_price.setText("Rs "+startein_plane_price_st+" only ");
                busniess_plane_price.setText("Rs "+business_plane_price_st+" only ");

                progressDialog.dismiss();



            }
        });



    }
}
