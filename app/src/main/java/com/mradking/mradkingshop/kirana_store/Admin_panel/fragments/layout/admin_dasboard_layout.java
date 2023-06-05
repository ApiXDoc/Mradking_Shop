package com.mradking.mradkingshop.kirana_store.Admin_panel.fragments.layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.Admin_panel.activity.shop.Shop_more_dasboard;
import com.mradking.mradkingshop.kirana_store.Admin_panel.lists.other_list.user_list;

public class admin_dasboard_layout extends Fragment {

    LinearLayout shop_more_bt;
    CardView user_list_bt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.kirana_admin_dasboard_home, container, false);


        shop_more_bt=root.findViewById(R.id.shop_more_bt);
        user_list_bt=root.findViewById(R.id.user_list_bt);



        shop_more_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(getActivity(), Shop_more_dasboard.class);
                startActivity(intent);
            }
        });

        user_list_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(), user_list.class);
                startActivity(intent);

            }
        });


        return root;

    }

}
