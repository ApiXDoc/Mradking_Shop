package com.mradking.mradkingshop.kirana_store.dilivery.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;
import com.mradking.mradkingshop.kirana_store.custmer.Model.ListModal;
import com.mradking.mradkingshop.kirana_store.custmer.adapter.Kirana_Orderlist_adapter;
import com.mradking.mradkingshop.kirana_store.dilivery.adapter.Order_list_adapter;

import java.util.ArrayList;
import java.util.List;

public class Order_list_act extends Activity {
    private RecyclerView cart_recycler_view;
    private List<CommonModal> productList;
    private Activity activity = Order_list_act.this;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public Order_list_adapter latestGovtJobRecyclerAdapter;
    public DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_orderlist_cus);

        productList = new ArrayList<CommonModal>();

        cart_recycler_view = findViewById(R.id.cart_list);
        firebaseAuth = FirebaseAuth.getInstance();
        latestGovtJobRecyclerAdapter = new Order_list_adapter(activity, productList);
        firebaseFirestore=FirebaseFirestore.getInstance();
        final String uid = firebaseAuth.getCurrentUser().getUid();

        Query firstQuery =  firebaseFirestore.collection("kirana_order_list")
             .whereEqualTo("order_status","Ordered")
                .whereEqualTo("pickup_status","no_pickup")
                .orderBy("timestamp", Query.Direction.DESCENDING);

        ListModal listModal=new ListModal();
        listModal.get_list("yes",true,firstQuery,
                productList,cart_recycler_view,latestGovtJobRecyclerAdapter,activity);




    }
}
