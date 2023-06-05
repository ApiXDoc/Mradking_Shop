package com.mradking.mradkingshop.kirana_store.custmer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.Admin_panel.fragments.list.shop_product_request_list;
import com.mradking.mradkingshop.kirana_store.custmer.Fragment.More_product_list_fragments;

import java.util.ArrayList;
import java.util.List;

public class More_product_Bt_list_main  extends AppCompatActivity {

    shop_product_request_list home_dasboard = new shop_product_request_list();
    BottomNavigationView bottomNavigationView;
    TabLayout tabLayout;
    String store_uid;

    private ViewPager2 viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_more_product_bt_list_main);
        bottomNavigationView  = findViewById(R.id.bottom_navigation);


        viewPager = (ViewPager2) findViewById(R.id.viewpager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        setupViewPager(viewPager);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_1:


                        return true;
                    case R.id.navigation_2:


                        Intent intent=new Intent(More_product_Bt_list_main.this,Cart_list.class);
                        intent.putExtra("key",store_uid);
                        startActivity(intent);


                        return true;

                    case R.id.navigation_3:

                        Toast.makeText(More_product_Bt_list_main.this, "click3", Toast.LENGTH_SHORT).show();

                        return true;

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

         store_uid=getIntent().getExtras().getString("store_uid");
        if(getIntent().getExtras().getString("key").contentEquals("kirana_product_grossery")) {


           ViewPagerAdapter1 adapter = new ViewPagerAdapter1(this);

            adapter.addFragment(new More_product_list_fragments("atta",store_uid),"Atta");
            adapter.addFragment(new More_product_list_fragments("rice",store_uid),  "Rice");
            adapter.addFragment(new More_product_list_fragments("oil_ghee",store_uid),  "Oile and Ghee");
            adapter.addFragment(new More_product_list_fragments("masalas",store_uid),  "Masalas");
            adapter.addFragment(new More_product_list_fragments("salt",store_uid),  "Sugar");
            adapter.addFragment(new More_product_list_fragments("sugar",store_uid),"Salt");

            adapter.addFragment(new More_product_list_fragments("dal",store_uid),"Dal");
            adapter.addFragment(new More_product_list_fragments("dry_fruit",store_uid),"Dry_fruit");
            adapter.addFragment(new More_product_list_fragments("basan_sooji_daila",store_uid),"Basan_sooji_daila");



            viewPager.setAdapter(adapter);

            new TabLayoutMediator(tabLayout,viewPager,((tab, position) ->
                    tab.setText(adapter.mFragmentTitleList.get(position)))).attach();


        }else if(getIntent().getExtras().getString("key").contentEquals("kirana_home_hygience_product")){


           ViewPagerAdapter1 adapter = new ViewPagerAdapter1(this);

            adapter.addFragment(new More_product_list_fragments("detergents",store_uid),  "Detergents");
            adapter.addFragment(new More_product_list_fragments("cleners",store_uid),  "Cleners");
            adapter.addFragment(new More_product_list_fragments("dishwhashers",store_uid),  "Dishwhashers");
            adapter.addFragment(new More_product_list_fragments("repellents",store_uid),  "Repellents");
            adapter.addFragment(new More_product_list_fragments("air_freshners",store_uid),  "Rir Freshners");

            viewPager.setAdapter(adapter);

            new TabLayoutMediator(tabLayout,viewPager,((tab, position) ->
                    tab.setText(adapter.mFragmentTitleList.get(position)))).attach();


        }else if(getIntent().getExtras().getString("key").contentEquals("kirana_persnoal_care_product")){
           ViewPagerAdapter1 adapter = new ViewPagerAdapter1(this);

            adapter.addFragment(new More_product_list_fragments("hair_oil",store_uid),  "Hair Oil");
            adapter.addFragment(new More_product_list_fragments("shampoo",store_uid),  "Shampoo");
            adapter.addFragment(new More_product_list_fragments("soaps",store_uid),  "Soaps");
            adapter.addFragment(new More_product_list_fragments("toothpastes",store_uid),  "Toothpase");
            adapter.addFragment(new More_product_list_fragments("skins_care",store_uid),  "Skin Care");
            adapter.addFragment(new More_product_list_fragments("baby_care",store_uid),  "Baby Care");

            viewPager.setAdapter(adapter);


            new TabLayoutMediator(tabLayout,viewPager,((tab, position) ->
                    tab.setText(adapter.mFragmentTitleList.get(position)))).attach();

        }else if(getIntent().getExtras().getString("key").contentEquals("kirana_beverages_product")){
           ViewPagerAdapter1 adapter = new ViewPagerAdapter1(this);


            adapter.addFragment(new More_product_list_fragments("tea",store_uid),  "Tea");
            adapter.addFragment(new More_product_list_fragments("coffee",store_uid),  "Coffee");
            adapter.addFragment(new More_product_list_fragments("heath_drink",store_uid),  "Health Drink");
            adapter.addFragment(new More_product_list_fragments("juices",store_uid),  "Juices");
            adapter.addFragment(new More_product_list_fragments("soft_drink",store_uid),  "Soft Drink");



            viewPager.setAdapter(adapter);

            new TabLayoutMediator(tabLayout,viewPager,((tab, position) ->
                    tab.setText(adapter.mFragmentTitleList.get(position)))).attach();

        }else if(getIntent().getExtras().getString("key").contentEquals("kirana_snacks_product")){
           ViewPagerAdapter1 adapter = new ViewPagerAdapter1(this);


            adapter.addFragment(new More_product_list_fragments("biscuits",store_uid),  "Biscuits");
            adapter.addFragment(new More_product_list_fragments("namkeens",store_uid),  "Namkeen");
            adapter.addFragment(new More_product_list_fragments("nodles_pasta",store_uid),  "Nodles Pasta");
            adapter.addFragment(new More_product_list_fragments("chocolates",store_uid),  "Chocolate");
            adapter.addFragment(new More_product_list_fragments("ketchups",store_uid),  "Ketchups");
            adapter.addFragment(new More_product_list_fragments("chips",store_uid),"Chips");



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
