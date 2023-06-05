package com.mradking.mradkingshop.kirana_store.shopkeer.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class withdrawing_request_making_act extends Activity {
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog=new ProgressDialog(this);
        firebaseFirestore= FirebaseFirestore.getInstance();
        DatePicker datePicker=new DatePicker(this);
        String current_date = datePicker.getDayOfMonth() + "/" + datePicker.getMonth() + "/" + datePicker.getYear();



        Map<String,Object> map=new HashMap<>();
        map.put("withdraw_status","requested");
        map.put("payout_amount",
                String.valueOf(getIntent().getExtras().getInt("total_amt")
                        -getIntent().getExtras().getInt("comission_st")-
                        getIntent().getExtras().getInt("tax_on_comission")));
        map.put("withraw_request_date",current_date);
        map.put("withdraw_timestamp", FieldValue.serverTimestamp());

        progressDialog.setMessage("Please wait........");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseFirestore.collection("kirana_order_list").document(getIntent().getExtras().getString("item_id")).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {



                Intent intent=new Intent(getApplicationContext(), Withraw_Main_act.class);
                startActivity(intent);
                 finish();
                progressDialog.dismiss();
            }
        });

    }
}