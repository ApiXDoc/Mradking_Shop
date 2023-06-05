package com.mradking.mradkingshop.kirana_store.custmer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Fragment.Home_fragment;
import com.mradking.mradkingshop.kirana_store.custmer.intface.RecyclerView_home_list;
import com.mradking.mradkingshop.kirana_store.custmer.Fragment.SlideshowFragment;
import com.mradking.mradkingshop.kirana_store.franchige.activity.account;

public class kirana_home_main_act extends AppCompatActivity implements RecyclerView_home_list {

    BottomNavigationView bottomNavigationView;



    SlideshowFragment slideshowFragment  = new SlideshowFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_home_main_layout);
        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        String store_uid=getIntent().getExtras().getString("store_id");






        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Home_fragment(store_uid,"no")).commit();

//
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_1:


                        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Home_fragment(store_uid,"no")).commit();
                        return true;
                    case R.id.navigation_2:

                        Intent intent1=new Intent(kirana_home_main_act.this,Order_list_main_class.class);
                        startActivity(intent1);
                        return true;
                    case R.id.navigation_3:

                        Intent intent=new Intent(kirana_home_main_act.this,Order_list_main_class.class);
                        startActivity(intent);
                        return true;

                    case R.id.navigation_4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,slideshowFragment).commit();
                        return true;


                    case R.id.navigation_5:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Home_fragment(store_uid,"no")).commit();
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