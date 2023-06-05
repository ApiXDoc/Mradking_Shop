package com.mradking.mradkingshop.kirana_store.custmer.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;
import com.mradking.mradkingshop.kirana_store.custmer.Model.ListModal;
import com.mradking.mradkingshop.kirana_store.custmer.activity.Cart_list;
import com.mradking.mradkingshop.kirana_store.custmer.adapter.product_list_adapter;
import com.mradking.mradkingshop.kirana_store.custmer.intface.RecyclerView_home_list;
import com.mradking.mradkingshop.kirana_store.shopkeer.modal.AddedProductModel;

import java.util.ArrayList;
import java.util.List;

public class More_product_list_fragments extends Fragment implements RecyclerView_home_list {

    private String mCategoryName;
    private String store_uid;

    private RecyclerView cart_recycler_view;
    private List<CommonModal> productList;
    private Activity activity = getActivity();

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public product_list_adapter latestGovtJobRecyclerAdapter;
    public DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;
    BottomNavigationView bottomNavigationView;

    Cart_list cart_list = new Cart_list();
    String page_status = "home";

    public More_product_list_fragments(String categoryName, String store_uid_1) {
        mCategoryName = categoryName;
        store_uid = store_uid_1;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.kirana_product_list, container, false);

        inslise(root);
        list_set_up();

     bottom_nav_set();



        return root;
    }

    private void bottom_nav_set() {

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_1:

                        page_status = "home";

                        return true;
                    case R.id.navigation_2:

                        page_status = "cart";
                        Intent intent = new Intent(activity, Cart_list.class);
                        startActivity(intent);


                        return true;

                    case R.id.navigation_3:
                        page_status = "monthlist";
                        Toast.makeText(activity, "click3", Toast.LENGTH_SHORT).show();

                        return true;

                }

                return true;
            }
        });



    }

    private void inslise(View root) {

        productList = new ArrayList<CommonModal>();

        cart_recycler_view = root.findViewById(R.id.cart_list);
        firebaseAuth = FirebaseAuth.getInstance();
        bottomNavigationView = root.findViewById(R.id.bottom_navigation);
        firebaseFirestore = FirebaseFirestore.getInstance();
        Toolbar toolbar = root.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        bottomNavigationView.setVisibility(View.GONE);
    }

    private void list_set_up() {

        Query query=firebaseFirestore.collection("kirana_shopkeeper_product")
                .document(store_uid)
                .collection("proudcts")
                .whereEqualTo("sub_category",mCategoryName);

        latestGovtJobRecyclerAdapter = new product_list_adapter(activity, productList, this);

        ListModal listModal = new ListModal();
        listModal.get_list( "yes", true,query, productList, cart_recycler_view, latestGovtJobRecyclerAdapter, getActivity());



    }


    @Override
    public void total_amount(int total_amt) {

    }

    @Override
    public void visblity_cart_bar(int posion) {

    }

    @Override
    public void total_saving(int amt) {

    }

    @Override
    public void restart_act() {

    }
}
