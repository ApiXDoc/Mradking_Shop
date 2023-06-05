package com.mradking.mradkingshop.kirana_store.franchige.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;

public class investment_form extends Activity {
    private Object Toolbar;
    Button pay_bt;
    EditText name_et,phone_et,email_id_et,create_password_et,conformed_password_et;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    TextView plane_name_tv;
    String name_st,phone_st,emailId_st;
    DatePicker datePicker;
    String curret_date_st,plane_name_st,plane_amt_st;

    String amt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mr_adking_bussiess_investment_form);
        androidx.appcompat.widget.Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        pay_bt=(Button)findViewById(R.id.pay_bt);

        name_et=(EditText)findViewById(R.id.name);
        phone_et=(EditText)findViewById(R.id.phone);
        email_id_et=(EditText)findViewById(R.id.email_id);
        create_password_et=(EditText)findViewById(R.id.password);
        conformed_password_et=(EditText)findViewById(R.id.conformed_your_password);
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        plane_name_tv=(TextView)findViewById(R.id.plane_name);


        data_set();


        pay_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(! TextUtils.isEmpty(email_id_et.getText().toString())){

                    Intent intent=new Intent(investment_form.this,Data_setup_for_stage_2.class);
                    intent.putExtra("amt",plane_amt_st);
                    intent.putExtra("plane",plane_name_st);
                    intent.putExtra("name",name_st);
                    intent.putExtra("phone",name_st);
                    intent.putExtra("email",name_st);

                    startActivity(intent);
                    finish();
                }else {

                    Toast.makeText(investment_form.this, "Please Enter Your Email Id", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void data_set() {

        plane_name_st= getIntent().getExtras().getString("plan");
        plane_amt_st=getIntent().getExtras().getString("paying_amt");

        plane_name_tv.setText(plane_name_st);
        name_et.setText(firebaseAuth.getCurrentUser().getDisplayName());
        phone_et.setText(firebaseAuth.getCurrentUser().getPhoneNumber());



    }
}
