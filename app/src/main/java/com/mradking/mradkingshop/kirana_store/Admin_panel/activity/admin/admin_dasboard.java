package com.mradking.mradkingshop.kirana_store.Admin_panel.activity.admin;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.Admin_panel.fragments.layout.admin_dasboard_layout;

public class admin_dasboard extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    admin_dasboard_layout home_dasboard = new admin_dasboard_layout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_admin_dasboard);
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


                    case R.id.navigation_3:



                    case R.id.navigation_4:

                        return true;
                    case R.id.navigation_5:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,home_dasboard).commit();
                        return true;


                }

                return true;
            }
        });

    }


}