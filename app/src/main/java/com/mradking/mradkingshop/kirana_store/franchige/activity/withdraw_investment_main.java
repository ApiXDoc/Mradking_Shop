package com.mradking.mradkingshop.kirana_store.franchige.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Fragment.More_product_list_fragments;
import com.mradking.mradkingshop.kirana_store.custmer.activity.More_product_Bt_list_main;
import com.mradking.mradkingshop.kirana_store.franchige.fragment.withdraw;
import com.mradking.mradkingshop.kirana_store.franchige.fragment.withdraw_request_list;


import java.util.ArrayList;
import java.util.List;

public class withdraw_investment_main extends AppCompatActivity {
        FrameLayout simpleFrameLayout;
    TabLayout tabLayout;
    String store_uid;

    private ViewPager2 viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_frenchige_team_main);

        viewPager = (ViewPager2)findViewById(R.id.viewpager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        setupViewPager(viewPager);

    }





    private void setupViewPager(ViewPager2 viewPager) {
        ViewPagerAdapter1 adapter = new ViewPagerAdapter1(this);
        adapter.addFragment(new withdraw(), "Withdraw");
        adapter.addFragment(new withdraw_request_list(), "Withdraw Request");
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager, ((tab, position) ->
                tab.setText(adapter.mFragmentTitleList.get(position)))).attach();
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







