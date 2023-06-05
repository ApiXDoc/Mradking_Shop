package com.mradking.mradkingshop.kirana_store.shopkeer.activity;

import android.os.Bundle;
import android.widget.FrameLayout;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.beverage.coffee;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.beverage.health_drink;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.beverage.juices;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.beverage.soft_drink;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.beverage.tea;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.beverage.water;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.grocery_product_frg.Atta_Product_image_Fragment;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.grocery_product_frg.Basan_sooji_daila;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.grocery_product_frg.Dal;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.grocery_product_frg.Dry_fruit;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.grocery_product_frg.Masalas;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.grocery_product_frg.Oil_ghee;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.grocery_product_frg.Rice;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.grocery_product_frg.Salt;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.grocery_product_frg.Sugar;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.home_hygience_product.air_freshners;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.home_hygience_product.cleners;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.home_hygience_product.detergents;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.home_hygience_product.dishwhashers;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.home_hygience_product.repellents;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.personal_care.baby_care;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.personal_care.hair_oil;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.personal_care.shampoo;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.personal_care.skin_care;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.personal_care.soaps;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.personal_care.toothpastes;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.snacks.biscuits;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.snacks.chips;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.snacks.chocolate;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.snacks.ketchup;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.snacks.namkeens;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.snacks.nodles;

public class Add_product_with_list extends AppCompatActivity {
    FrameLayout simpleFrameLayout;
    TabLayout tabLayout;

    private ViewPager viewPager;
    String key;
    public String myString = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_add_product_with_list);



        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    public String getMyData() {
        return myString;
    }

    private void setupViewPager(ViewPager viewPager) {



                if(getIntent().getExtras().getString("key").contentEquals("food_oil")){

                    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


                    adapter.addFragment(new Atta_Product_image_Fragment(),  "Atta");
                    adapter.addFragment(new Rice(),  "Rice");
                    adapter.addFragment(new Oil_ghee(),  "Oile and Ghee");
                    adapter.addFragment(new Masalas(),  "Masalas");
                    adapter.addFragment(new Sugar(),  "Sugar");
                    adapter.addFragment(new Salt(),"Salt");

                    adapter.addFragment(new Dal(),"Dal");
                    adapter.addFragment(new Dry_fruit(),"Dry_fruit");
                    adapter.addFragment(new Basan_sooji_daila(),"Basan_sooji_daila");



                    viewPager.setOffscreenPageLimit(0);
                    viewPager.setAdapter(adapter);


                }else if(getIntent().getExtras().getString("key").contentEquals("hygience")){

                    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

                    adapter.addFragment(new detergents(),  "Detergents");
                    adapter.addFragment(new cleners(),  "Cleners");
                    adapter.addFragment(new dishwhashers(),  "Dishwhashers");
                    adapter.addFragment(new repellents(),  "Repellents");
                    adapter.addFragment(new air_freshners(),  "Rir Freshners");

                    viewPager.setOffscreenPageLimit(0);
                    viewPager.setAdapter(adapter);


                }else if(getIntent().getExtras().getString("key").contentEquals("personal_care")){


                    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

                    adapter.addFragment(new hair_oil(),  "Hair Oil");
                    adapter.addFragment(new shampoo(),  "Shampoo");
                    adapter.addFragment(new soaps(),  "Soaps");
                    adapter.addFragment(new toothpastes(),  "Toothpase");
                    adapter.addFragment(new skin_care(),  "Skin Care");
                    adapter.addFragment(new baby_care(),  "Baby Care");


                    viewPager.setOffscreenPageLimit(0);
                    viewPager.setAdapter(adapter);


                }

                else if(getIntent().getExtras().getString("key").contentEquals("berverage")){


                    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

                    adapter.addFragment(new tea(),  "Tea");
                    adapter.addFragment(new coffee(),  "Coffee");
                    adapter.addFragment(new health_drink(),  "Health Drink");
                    adapter.addFragment(new juices(),  "Juices");
                    adapter.addFragment(new soft_drink(),  "Soft Drink");
                    adapter.addFragment(new water(),  "Water");


                    viewPager.setOffscreenPageLimit(0);
                    viewPager.setAdapter(adapter);


                }
                else if(getIntent().getExtras().getString("key").contentEquals("snacks")){


                    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

                    adapter.addFragment(new biscuits(),  "Biscuits");
                    adapter.addFragment(new namkeens(),  "Namkeen");
                    adapter.addFragment(new nodles(),  "Nodles Pasta");
                    adapter.addFragment(new chocolate(),  "Chocolate");
                    adapter.addFragment(new ketchup(),  "Ketchups");
                    adapter.addFragment(new chips(),"Chips");


                    viewPager.setOffscreenPageLimit(0);
                    viewPager.setAdapter(adapter);


                }


                else {


                    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
                    adapter.addFragment(new Atta_Product_image_Fragment(),  "Atta");
                    adapter.addFragment(new  Atta_Product_image_Fragment(),  "Rice");
                    adapter.addFragment(new Atta_Product_image_Fragment(),  "Oile and Ghee");

                    viewPager.setOffscreenPageLimit(0);
                    viewPager.setAdapter(adapter);


                }







    }
}



