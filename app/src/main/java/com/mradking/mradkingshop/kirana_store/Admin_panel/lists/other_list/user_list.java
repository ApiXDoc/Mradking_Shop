package com.mradking.mradkingshop.kirana_store.Admin_panel.lists.other_list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
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
import com.mradking.mradkingshop.kirana_store.Admin_panel.adapter.admin_adapter.user_list_adapter;
import com.mradking.mradkingshop.kirana_store.Admin_panel.adapter.shop_adapter.shop_withdraw_request_adapter;
import com.mradking.mradkingshop.kirana_store.Admin_panel.intarfac.admin_shop_withraw_payment;
import com.mradking.mradkingshop.kirana_store.Admin_panel.lists.shop_list.shop_transfer_list;
import com.mradking.mradkingshop.kirana_store.Admin_panel.modal.Userlist;


import java.util.ArrayList;
import java.util.List;

public class user_list extends Activity  {
    private RecyclerView cart_recycler_view;
    private List<Userlist> productList;
    private Activity activity = user_list.this;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public user_list_adapter latestGovtJobRecyclerAdapter;
    public DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_orderlist_cus);
        productList = new ArrayList<Userlist>();

        cart_recycler_view = findViewById(R.id.cart_list);
        firebaseAuth = FirebaseAuth.getInstance();
        latestGovtJobRecyclerAdapter = new user_list_adapter( activity,productList);
        LinearLayoutManager lm1 = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        lm1.setReverseLayout(true);
        lm1.setStackFromEnd(true);

        cart_recycler_view.setHasFixedSize(true);
        cart_recycler_view.setLayoutManager(lm1);
        cart_recycler_view.setAdapter(latestGovtJobRecyclerAdapter);
        firebaseFirestore=FirebaseFirestore.getInstance();

        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Tranfer List");

        shop_list();

    }

    private void shop_list() {

        if (firebaseAuth.getCurrentUser() != null) {

            firebaseFirestore = FirebaseFirestore.getInstance();

            cart_recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    Boolean reachedBottom = !recyclerView.canScrollVertically(1);

                    if (reachedBottom) {


                        String desc = lastVisible.getString("name");

                        loadMorePost();

                    }

                }
            });

            final String uid = firebaseAuth.getCurrentUser().getUid();

            Query firstQuery =  firebaseFirestore.collection("kirana_user_details")
                   .limit(10);




            firstQuery.addSnapshotListener( new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {


                    Log.d("error", String.valueOf(e));

                    if (isFirstPageFirstLoad) {

                        if (documentSnapshots.isEmpty()) {




                        } else {


                            lastVisible = documentSnapshots.getDocuments().get(documentSnapshots.size() - 1);


                        }


                    }
                    for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                        if (doc.getType() == DocumentChange.Type.ADDED) {

                            Userlist blogPost = doc.getDocument().toObject(Userlist.class).withId(doc.getDocument().getId());
                            String itemId = doc.getDocument().getId();

                            blogPost.setItemId(itemId);


                            if (isFirstPageFirstLoad) {
                                productList.add(0, blogPost);
                            } else {

                                productList.add(0, blogPost);
                            }


                            latestGovtJobRecyclerAdapter.notifyDataSetChanged();

                        }
                    }

                    isFirstPageFirstLoad = false;

                }
            });

        } else {

            Toast.makeText(activity, "no user", Toast.LENGTH_SHORT).show();
        }



    }

    private void loadMorePost() {

        String uid = firebaseAuth.getCurrentUser().getUid();

        Query nextQuery =  firebaseFirestore.collection("kirana_user_details")
               .startAfter(lastVisible).limit(10);




        nextQuery.addSnapshotListener( new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (!documentSnapshots.isEmpty()) {

                    lastVisible = documentSnapshots.getDocuments().get(documentSnapshots.size() - 1);
                    for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                        if (doc.getType() == DocumentChange.Type.ADDED) {

                            Userlist govt_job = doc.getDocument().toObject(Userlist.class);
                            String itemId = doc.getDocument().getId();

                            govt_job.setItemId(itemId);

                            productList.add(govt_job);

                            latestGovtJobRecyclerAdapter.notifyDataSetChanged();

                        }
                    }

                }

            }
        });
    }





}

