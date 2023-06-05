package com.mradking.mradkingshop.kirana_store.shopkeer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.other.Account_act_frg;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.other.Shop_dassboard;
import com.mradking.mradkingshop.kirana_store.custmer.Fragment.SlideshowFragment;

public class shop_dassboard_main extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    Shop_dassboard shop_dassboard = new Shop_dassboard();
    Account_act_frg account_act_frg=new Account_act_frg();
    SlideshowFragment slideshowFragment  = new SlideshowFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_shop_dasssboard_main);
        bottomNavigationView  = findViewById(R.id.bottom_navigation);



        getSupportFragmentManager().beginTransaction().replace(R.id.container, shop_dassboard).commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,shop_dassboard).commit();
                        return true;
                    case R.id.navigation_2:
                        Intent intent2=new Intent(shop_dassboard_main.this, Order_Main_list.class);
                        startActivity(intent2);
                        return true;

                    case R.id.navigation_3:

                        Intent intent=new Intent(shop_dassboard_main.this, Withraw_Main_act.class);
                        startActivity(intent);
                        return true;

                    case R.id.navigation_4:
                        Intent intent1=new Intent(shop_dassboard_main.this, Add_prodcut_category.class);
                        startActivity(intent1);
                        return true;
                    case R.id.navigation_5:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,account_act_frg).commit();
                        return true;


                }

                return true;
            }
        });

    }


}