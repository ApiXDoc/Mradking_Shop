package com.mradking.mradkingshop.kirana_store.shopkeer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Fragment.Delivered_list_fragment;
import com.mradking.mradkingshop.kirana_store.custmer.Fragment.Orderlist_frament;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.other.Order_delivered_frg;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.other.Order_frg;
import com.mradking.mradkingshop.kirana_store.shopkeer.fragments.other.Order_inprocess_frg;

import java.util.ArrayList;
import java.util.List;

public class Order_Main_list extends AppCompatActivity {
    FrameLayout simpleFrameLayout;
    TabLayout tabLayout;


    private ViewPager viewPager;
    String key;
    public String myString = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_order_main_list);



        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);




    }

    public String getMyData() {
        return myString;
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new Order_frg(),  "Ordered");
        adapter.addFragment(new Order_inprocess_frg(),  "Inprocess");
        adapter.addFragment(new Order_delivered_frg(),  "Delivered");


        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(adapter);








    }




    class ViewPagerAdapter extends FragmentPagerAdapter{


        private final List<Fragment> mFragmentList = new ArrayList<Fragment>();
        private final List<String> mFragmentTitleList = new ArrayList<String>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {


            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {



            return mFragmentTitleList.get(position);
        }

    }





}







