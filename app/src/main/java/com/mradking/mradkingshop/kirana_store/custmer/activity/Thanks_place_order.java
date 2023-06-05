package com.mradking.mradkingshop.kirana_store.custmer.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.mradking.mradkingshop.R;

public class Thanks_place_order extends Activity {

    CardView orderlist_bt;

    @Override
    public void onBackPressed() {
       Intent intent=new Intent(Thanks_place_order.this,kirana_home_main_act.class);
       startActivity(intent);
       finish();


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_after_order_layout);


        orderlist_bt=findViewById(R.id.ok);


        orderlist_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Thanks_place_order.this,Order_list_main_class.class);
                startActivity(intent);
                finish();



            }
        });


    }
}
