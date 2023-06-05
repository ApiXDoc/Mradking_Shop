package com.mradking.mradkingshop.kirana_store.Admin_panel.activity.shop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mradking.mradkingshop.R;

public class Shop_more_dasboard extends AppCompatActivity {

    LinearLayout request_more_bt,product_request_see_more,support_see_more;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_admin_shop_more_page);


        request_more_bt=findViewById(R.id.request_more_bt);
        product_request_see_more=findViewById(R.id.product_request_see_more);
        support_see_more=findViewById(R.id.support_see_more);

        request_more_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Shop_more_dasboard.this,Shop_withdraw_request_main.class);
                startActivity(intent);


            }
        });

        product_request_see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Shop_more_dasboard.this,Shop_requested_product_main.class);
                startActivity(intent);

            }
        });

        support_see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Shop_more_dasboard.this,Shop_spport_request_main.class);
                startActivity(intent);

            }
        });

    }
}
