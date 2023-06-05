package com.mradking.mradkingshop.kirana_store.shopkeer.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.intface.RecyclerView_home_list;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.other.Withdraw_Delivery_order_list;
import com.mradking.mradkingshop.kirana_store.shopkeer.list.Withdraw_request_list;
import com.mradking.mradkingshop.kirana_store.shopkeer.list.Withdraw_transfer_list;

public class Withraw_Main_act extends AppCompatActivity implements RecyclerView_home_list {

    BottomNavigationView bottomNavigationView;

    Withdraw_Delivery_order_list homeFragment = new Withdraw_Delivery_order_list();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_shop_main_withraw_act);
        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        Toolbar toolbar=new Toolbar(this);

        toolbar.setTitle("Delivered List");
        toolbar.setTitleTextColor(Color.BLACK);


        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;
                    case R.id.navigation_2:

                        Intent intent1=new Intent(Withraw_Main_act.this, Withdraw_request_list.class);
                        startActivity(intent1);
                        return true;
                    case R.id.navigation_3:

                        Intent intent=new Intent(Withraw_Main_act.this, Withdraw_transfer_list.class);
                        startActivity(intent);
                        return true;




                }

                return true;
            }
        });

    }

    @Override
    public void total_amount(int total_amt) {



    }

    @Override
    public void visblity_cart_bar(int posion) {

    }

    @Override
    public void total_saving(int amt) {

    }

    @Override
    public void restart_act() {

    }
}