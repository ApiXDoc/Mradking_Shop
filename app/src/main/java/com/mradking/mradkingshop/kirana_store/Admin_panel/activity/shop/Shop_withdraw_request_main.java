package com.mradking.mradkingshop.kirana_store.Admin_panel.activity.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.Admin_panel.fragments.layout.Shop_withdraw_request_home_layout;
import com.mradking.mradkingshop.kirana_store.Admin_panel.fragments.layout.admin_dasboard_layout;
import com.mradking.mradkingshop.kirana_store.Admin_panel.lists.shop_list.shop_transfer_list;

public class Shop_withdraw_request_main extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    Shop_withdraw_request_home_layout home_dasboard = new Shop_withdraw_request_home_layout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_admin_shop_withdraw_main);
        bottomNavigationView  = findViewById(R.id.bottom_navigation);



        getSupportFragmentManager().beginTransaction().replace(R.id.container, home_dasboard).commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,home_dasboard).commit();
                        return true;
                    case R.id.navigation_2:

                        Intent intent=new Intent(getApplicationContext(), shop_transfer_list.class);
                        startActivity(intent);




                }

                return true;
            }
        });

    }


}