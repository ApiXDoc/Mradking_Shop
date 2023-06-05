package com.mradking.mradkingshop.kirana_store.shopkeer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.mradking.mradkingshop.R;

public class Add_prodcut_category extends Activity {

    CardView food_oil,home_hygiene_care,personal_care,berverage,snacks;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_add_product_category);

        food_oil=findViewById(R.id.food_oil);
        home_hygiene_care=findViewById(R.id.home_hygiene_care);
        personal_care=findViewById(R.id.personal_care);
        berverage=findViewById(R.id.berverage);
        snacks=findViewById(R.id.snacks);

        snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Add_prodcut_category.this,Add_product_with_list.class);
                intent.putExtra("key","snacks");
                startActivity(intent);


            }
        });

        food_oil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Add_prodcut_category.this,Add_product_with_list.class);
               intent.putExtra("key","food_oil");
                startActivity(intent);

            }
        });

        home_hygiene_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Add_prodcut_category.this,Add_product_with_list.class);
                intent.putExtra("key","hygience");
                startActivity(intent);

            }
        });

        personal_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Add_prodcut_category.this,Add_product_with_list.class);
                intent.putExtra("key","personal_care");
                startActivity(intent);


            }
        });



        berverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Add_prodcut_category.this,Add_product_with_list.class);
                intent.putExtra("key","berverage");
                startActivity(intent);


            }
        });


    }
}
