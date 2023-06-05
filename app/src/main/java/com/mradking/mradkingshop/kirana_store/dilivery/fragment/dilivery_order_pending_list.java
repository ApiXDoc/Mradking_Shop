package com.mradking.mradkingshop.kirana_store.dilivery.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;
import com.mradking.mradkingshop.kirana_store.custmer.Model.ListModal;
import com.mradking.mradkingshop.kirana_store.dilivery.activity.Pickup_list_act;
import com.mradking.mradkingshop.kirana_store.dilivery.adapter.Pick_up_adapter;

import java.util.ArrayList;
import java.util.List;

public class dilivery_order_pending_list extends Fragment {
    private RecyclerView cart_recycler_view;
    private List<CommonModal> productList;
    private Activity activity = getActivity();
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public Pick_up_adapter latestGovtJobRecyclerAdapter;
    public DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.kirana_dilivery_dassboard, container, false);


        productList = new ArrayList<CommonModal>();

        cart_recycler_view = root.findViewById(R.id.cart_list);
        firebaseAuth = FirebaseAuth.getInstance();
        latestGovtJobRecyclerAdapter = new Pick_up_adapter(activity, productList);
        firebaseFirestore=FirebaseFirestore.getInstance();
        final String uid = firebaseAuth.getCurrentUser().getUid();

        Query firstQuery =  firebaseFirestore.collection("kirana_order_list")
                .whereEqualTo("order_status","Ordered")
                .whereEqualTo("pickup_status","pickup_recived")
                .orderBy("timestamp", Query.Direction.DESCENDING);

        ListModal listModal=new ListModal();
        listModal.get_list("yes",true,firstQuery,
                productList,cart_recycler_view,latestGovtJobRecyclerAdapter,activity);



        return root;
    }
}
