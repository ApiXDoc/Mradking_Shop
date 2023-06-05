package com.mradking.mradkingshop.kirana_store.franchige.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;
import com.mradking.mradkingshop.kirana_store.custmer.Model.ListModal;
import  com.mradking.mradkingshop.kirana_store.franchige.adapter.team_adatper;

import java.util.ArrayList;
import java.util.List;

public class team_list_act extends AppCompatActivity {


    private RecyclerView cart_recycler_view;
    private List<CommonModal> productList;
BottomNavigationView bottom_navigation;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public team_adatper latestGovtJobRecyclerAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_product_list);
        inslise();

        list_set_up();

    }




    private void inslise() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);
        productList = new ArrayList<CommonModal>();
        cart_recycler_view = findViewById(R.id.cart_list);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        bottom_navigation=findViewById(R.id.bottom_navigation);

        bottom_navigation.setVisibility(View.GONE);
        toolbar.setTitle("Partners");


    }


    private void list_set_up() {
     Query query=firebaseFirestore
                    .collection("kirana_user_details")
                    .whereEqualTo("refer_uid",firebaseAuth.getCurrentUser().getUid())
                    .whereEqualTo("buying_status","yes");
                    ;

            latestGovtJobRecyclerAdapter = new team_adatper(this, productList);

            ListModal listModal = new ListModal();
            listModal.get_list( "yes", true,query, productList, cart_recycler_view, latestGovtJobRecyclerAdapter, this);

    }


}
