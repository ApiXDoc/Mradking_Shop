package com.mradking.mradkingshop.kirana_store.shopkeer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.Admin_panel.fragments.list.shop_product_request_list;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.Beverage.Coffee;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.Beverage.Health_drink;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.Beverage.Juices;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.Beverage.Soft_drink;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.Beverage.Tea;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.Beverage.Water;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.Personal_Care.Baby_care;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.Personal_Care.Hari_Oil;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.Personal_Care.Shampoo;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.Personal_Care.Skins_care;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.Personal_Care.Soaps;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.Personal_Care.Toothpastes;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.Sancks.Biscuits;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.Sancks.Chips;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.Sancks.Chocolates;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.Sancks.Ketchups;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.Sancks.Namkeen;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.Sancks.Nodles_pasta;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.gorcery.atta;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.gorcery.basen_sooji_dalia;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.gorcery.dal;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.gorcery.dry_fruit;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.gorcery.massale;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.gorcery.oil_ghee;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.gorcery.rice;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.gorcery.salt;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.gorcery.sugar;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.homehygine.Air_freshners;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.homehygine.Cleners;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.homehygine.Detergents;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.homehygine.Dishwashers;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.homehygine.Repellents;

import java.util.ArrayList;
import java.util.List;

public class Shop_stock_main extends AppCompatActivity {


    shop_product_request_list home_dasboard = new shop_product_request_list();
    BottomNavigationView bottomNavigationView;
    TabLayout tabLayout;


    private ViewPager2 viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_shop_stock_hub_main);
        bottomNavigationView  = findViewById(R.id.bottom_navigation);


        viewPager = (ViewPager2) findViewById(R.id.viewpager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        setupViewPager(viewPager);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_2:

                        Intent intent=new Intent(getApplicationContext(), Shop_instock_main.class);
                        intent.putExtra("key",getIntent().getExtras().getString("key"));

                        startActivity(intent);

                        finish();
                        return true;
                    case R.id.navigation_3:

                        Intent intent1=new Intent(getApplicationContext(), Shop_near_out_stock_main.class);
                        intent1.putExtra("key",getIntent().getExtras().getString("key"));

                        startActivity(intent1);

                        finish();
                        return true;
                    case R.id.navigation_1:


                }

                return true;
            }
        });

    }
    @Override
    public void onBackPressed() {

        finish();
    }
    private void setupViewPager(ViewPager2 viewPager) {


        if(getIntent().getExtras().getString("key").contentEquals("kirana_product_grossery")) {


            ViewPagerAdapter1 adapter = new ViewPagerAdapter1(this);

            adapter.addFragment(new atta(),  "Atta");
            adapter.addFragment(new rice(),  "Rice");
            adapter.addFragment(new oil_ghee(),  "Oile and Ghee");
            adapter.addFragment(new massale(),  "Masalas");
            adapter.addFragment(new sugar(),  "Sugar");
            adapter.addFragment(new salt(),"Salt");

            adapter.addFragment(new dal(),"Dal");
            adapter.addFragment(new dry_fruit(),"Dry_fruit");
            adapter.addFragment(new basen_sooji_dalia(),"Basan_sooji_daila");



            viewPager.setAdapter(adapter);

            new TabLayoutMediator(tabLayout,viewPager,((tab, position) ->
                    tab.setText(adapter.mFragmentTitleList.get(position)))).attach();


        }else if(getIntent().getExtras().getString("key").contentEquals("kirana_home_hygience_product")){


            ViewPagerAdapter1 adapter = new ViewPagerAdapter1(this);

            adapter.addFragment(new Detergents(),  "Detergents");
            adapter.addFragment(new Cleners(),  "Cleners");
            adapter.addFragment(new Dishwashers(),  "Dishwhashers");
            adapter.addFragment(new Repellents(),  "Repellents");
            adapter.addFragment(new Air_freshners(),  "Rir Freshners");

            viewPager.setAdapter(adapter);

            new TabLayoutMediator(tabLayout,viewPager,((tab, position) ->
                    tab.setText(adapter.mFragmentTitleList.get(position)))).attach();


        }else if(getIntent().getExtras().getString("key").contentEquals("kirana_persnoal_care_product")){
            ViewPagerAdapter1 adapter = new ViewPagerAdapter1(this);

            adapter.addFragment(new Hari_Oil(),  "Hair Oil");
            adapter.addFragment(new Shampoo(),  "Shampoo");
            adapter.addFragment(new Soaps(),  "Soaps");
            adapter.addFragment(new Toothpastes(),  "Toothpase");
            adapter.addFragment(new Skins_care(),  "Skin Care");
            adapter.addFragment(new Baby_care(),  "Baby Care");

            viewPager.setAdapter(adapter);


            new TabLayoutMediator(tabLayout,viewPager,((tab, position) ->
                    tab.setText(adapter.mFragmentTitleList.get(position)))).attach();

        }else if(getIntent().getExtras().getString("key").contentEquals("kirana_beverages_product")){
            ViewPagerAdapter1 adapter = new ViewPagerAdapter1(this);


            adapter.addFragment(new Tea(),  "Tea");
            adapter.addFragment(new Coffee(),  "Coffee");
            adapter.addFragment(new Health_drink(),  "Health Drink");
            adapter.addFragment(new Juices(),  "Juices");
            adapter.addFragment(new Soft_drink(),  "Soft Drink");
            adapter.addFragment(new Water(),  "Water");



            viewPager.setAdapter(adapter);

            new TabLayoutMediator(tabLayout,viewPager,((tab, position) ->
                    tab.setText(adapter.mFragmentTitleList.get(position)))).attach();

        }else if(getIntent().getExtras().getString("key").contentEquals("kirana_snacks_product")){
            ViewPagerAdapter1 adapter = new ViewPagerAdapter1(this);


            adapter.addFragment(new Biscuits(),  "Biscuits");
            adapter.addFragment(new Namkeen(),  "Namkeen");
            adapter.addFragment(new Nodles_pasta(),  "Nodles Pasta");
            adapter.addFragment(new Chocolates(),  "Chocolate");
            adapter.addFragment(new Ketchups(),  "Ketchups");
            adapter.addFragment(new Chips(),"Chips");



            viewPager.setAdapter(adapter);
            new TabLayoutMediator(tabLayout,viewPager,((tab, position) ->
                    tab.setText(adapter.mFragmentTitleList.get(position)))).attach();


        }












    }


    public class ViewPagerAdapter1 extends FragmentStateAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<Fragment>();
        private final List<String> mFragmentTitleList = new ArrayList<String>();

        public ViewPagerAdapter1(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }


        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return mFragmentTitleList.size();
        }
    }

}
