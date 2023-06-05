package com.mradking.mradkingshop.kirana_store.franchige.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.franchige.fragment.franchige_home_dassborad;

public class Frenchige_dasboard extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    String store_uid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_frechige_dasboard);

        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);


        getSupportFragmentManager().beginTransaction().replace(R.id.container,new franchige_home_dassborad()).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_1:


                        getSupportFragmentManager().beginTransaction().replace(R.id.container,new franchige_home_dassborad()).commit();
                        return true;
                    case R.id.navigation_2:

                       Intent intent=new Intent(Frenchige_dasboard.this,team_list_act.class);
                       startActivity(intent);

                        return true;
                    case R.id.navigation_3:

                        Intent intent1=new Intent(Frenchige_dasboard.this,withdraw_investment_main.class);
                        startActivity(intent1);

                        return true;

                    case R.id.navigation_4:

                        Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                        Uri data = Uri.parse("mailto:?subject=" + "I Need Help"+ "&body=" + "&to=" + "adking4l7@gmail.com");
                        mailIntent.setData(data);
                        startActivity(Intent.createChooser(mailIntent, "Send mail..."));

                        return true;


                    case R.id.navigation_5:
                        Intent intent2=new Intent(Frenchige_dasboard.this,account.class);
                        startActivity(intent2);
                        finish();
                        return true;

                }

                return true;
            }
        });



    }


}
