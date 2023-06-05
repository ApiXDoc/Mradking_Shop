package com.mradking.mradkingshop.kirana_store.custmer.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;
import com.mradking.mradkingshop.kirana_store.custmer.Model.ListModal;
import com.mradking.mradkingshop.kirana_store.custmer.Model.OrderListModal;
import com.mradking.mradkingshop.kirana_store.custmer.adapter.Kirana_Orderlist_adapter;

import java.util.ArrayList;
import java.util.List;

public class Orderlist_frament extends Fragment {
    private RecyclerView cart_recycler_view;
    private List<CommonModal> productList;
    private Activity activity = getActivity();
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public Kirana_Orderlist_adapter latestGovtJobRecyclerAdapter;
    public DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.kirana_orderlist_cus, container, false);

        productList = new ArrayList<CommonModal>();


        cart_recycler_view = root.findViewById(R.id.cart_list);
        firebaseAuth = FirebaseAuth.getInstance();
        latestGovtJobRecyclerAdapter = new Kirana_Orderlist_adapter(getActivity(), productList);


        firebaseFirestore=FirebaseFirestore.getInstance();
        final String uid = firebaseAuth.getCurrentUser().getUid();
        Query firstQuery =  firebaseFirestore.collection("kirana_order_list")
                .whereEqualTo("custmer_uid",uid).whereEqualTo("order_status","Ordered")
                .orderBy("timestamp", Query.Direction.DESCENDING);

        ListModal listModal=new ListModal();
        listModal.get_list("yes",true,firstQuery,
                productList,cart_recycler_view,latestGovtJobRecyclerAdapter,getActivity());



    return root;
    }



}