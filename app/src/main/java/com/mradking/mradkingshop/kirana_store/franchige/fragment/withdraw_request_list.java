package com.mradking.mradkingshop.kirana_store.franchige.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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
import com.mradking.mradkingshop.kirana_store.franchige.adapter.WithdrawListAdapter;
import com.mradking.mradkingshop.kirana_store.franchige.adapter.team_adatper;

import java.util.ArrayList;
import java.util.List;

public class withdraw_request_list extends Fragment  {

    private RecyclerView cart_recycler_view;
    private List<CommonModal> productList;
    BottomNavigationView bottom_navigation;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public WithdrawListAdapter latestGovtJobRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.kirana_product_list, container, false);
        inslise(root);

        list_set_up();


return root;
}
    private void inslise(View root) {
        Toolbar toolbar = root.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
        productList = new ArrayList<CommonModal>();
        cart_recycler_view = root.findViewById(R.id.cart_list);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        bottom_navigation=root.findViewById(R.id.bottom_navigation);

        bottom_navigation.setVisibility(View.GONE);
        toolbar.setTitle("Withdraw Requests");


    }

    private void list_set_up() {
        Query query=firebaseFirestore
                .collection("franchige_withdrawing_list")
                .whereEqualTo("user_uid_french",firebaseAuth.getCurrentUser().getUid())
                .orderBy("timestamp", Query.Direction.DESCENDING);;

        latestGovtJobRecyclerAdapter = new WithdrawListAdapter(getActivity(), productList);

        ListModal listModal = new ListModal();
        listModal.get_list( "yes", true,query, productList, cart_recycler_view, latestGovtJobRecyclerAdapter, getActivity());

    }

}
