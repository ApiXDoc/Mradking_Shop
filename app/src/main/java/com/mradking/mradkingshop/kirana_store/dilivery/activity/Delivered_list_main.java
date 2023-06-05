package com.mradking.mradkingshop.kirana_store.dilivery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.dilivery.fragment.dilivery_home_frg;

public class Delivered_list_main extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;



    dilivery_home_frg diliveryHomeFrg  = new dilivery_home_frg();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_delivery_list_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

//        String store_uid=getIntent().getExtras().getString("store_id");
//


        getSupportFragmentManager().beginTransaction().replace(R.id.container, new dilivery_home_frg()).commit();

//
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_1:

                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new dilivery_home_frg()).commit();

                        return true;

                    case R.id.navigation_2:


                        return true;

                }

                return true;
            }
        });

    }}

