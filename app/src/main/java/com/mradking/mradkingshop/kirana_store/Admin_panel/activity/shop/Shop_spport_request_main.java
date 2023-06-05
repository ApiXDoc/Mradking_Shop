package com.mradking.mradkingshop.kirana_store.Admin_panel.activity.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.Admin_panel.fragments.list.shop_product_request_list;

public class Shop_spport_request_main extends AppCompatActivity {


    shop_product_request_list home_dasboard = new shop_product_request_list();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_admin_support_main);
        bottomNavigationView  = findViewById(R.id.bottom_navigation);



        getSupportFragmentManager().beginTransaction().replace(R.id.container, home_dasboard).commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_2:

                        Intent intent=new Intent(getApplicationContext(), Shop_accepted_produdct_request_main.class);
                        startActivity(intent);
                        finish();

                }

                return true;
            }
        });

    }


}
