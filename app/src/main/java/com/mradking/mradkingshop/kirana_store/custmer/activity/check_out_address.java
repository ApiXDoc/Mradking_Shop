package com.mradking.mradkingshop.kirana_store.custmer.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import androidx.core.content.ContextCompat;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.tasks.OnSuccessListener;
import com.mradking.mradkingshop.R;

import com.mradking.mradkingshop.kirana_store.custmer.intface.Get_loc_Calll;
import com.mradking.mradkingshop.kirana_store.custmer.utility.utilityX;

public class check_out_address extends AppCompatActivity {
    EditText phone_et,house_number_et,colony_et;
    CardView next_bt;
    Button location_search_button;
    double lat1 = 0 , long1;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_check_out_adress);


            inslise();

            data_send_loction();



    }

    private void data_send_loction() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "permission not granted", Toast.LENGTH_SHORT).show();

        }else {

            getCurrentLocation();
        }



//        location_search_button.setFocusable(false);
        location_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (TextUtils.isEmpty(phone_et.getText())) {

                    Toast.makeText(getApplicationContext(), "Please Enter YOUR Phone Number", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(house_number_et.getText())) {

                    Toast.makeText(getApplicationContext(), "Please Enter YOUR House No", Toast.LENGTH_SHORT).show();


                } else if (TextUtils.isEmpty(colony_et.getText())) {

                    Toast.makeText(getApplicationContext(), "Please Enter YOUR Colony Name", Toast.LENGTH_SHORT).show();


                } else {


                    if (lat1 == 0) {

                        Toast.makeText(check_out_address.this, "try again location permission not granted", Toast.LENGTH_SHORT).show();
                    } else {

                        Intent intent = new Intent(check_out_address.this, Near_Place_location_Map.class);
                        intent.putExtra("total_amt",getIntent().getExtras().getString("total_amt"));
                        intent.putExtra("delivery", getIntent().getExtras().getString("delivery"));
                        intent.putExtra("net_payment", getIntent().getExtras().getString("net_payment"));
                        intent.putExtra("phone", phone_et.getText().toString());
                        intent.putExtra("house_no", house_number_et.getText().toString());
                        intent.putExtra("colony", colony_et.getText().toString());
                        intent.putExtra("lat",lat1);
                        intent.putExtra("log",long1);
                        startActivity(intent);
                        finish();
                    }


                }


            }
        });
    }

    private void inslise() {

        phone_et=findViewById(R.id.phone_custmer);
        house_number_et=findViewById(R.id.address1);
        colony_et=findViewById(R.id.address2);
        next_bt=findViewById(R.id.ok_bt);
        location_search_button=findViewById(R.id.location_search_button);

    }


    private void getCurrentLocation() {

        utilityX  UtilityX = new utilityX(this);
        UtilityX.getCurrentLocation(check_out_address.this, new Get_loc_Calll() {
            @Override
            public void location(Location location) {

                lat1 = location.getLatitude();
                long1 = location.getLongitude();
            }
        });

    }

}
