package com.mradking.mradkingshop.kirana_store.franchige.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class Payment_page extends Activity {

    String plan_name,name,plane_amt,email_id,phone;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data_set();



    }

    private void data_set() {

        progressDialog.setMessage("Please Wait..........");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        plan_name=getIntent().getExtras().getString("plane");

        plane_amt=getIntent().getExtras().getString("amt");
        email_id=getIntent().getExtras().getString("name");
        name=getIntent().getExtras().getString("phone");
        phone=getIntent().getExtras().getString("email");



        progressDialog.dismiss();



    }
}
