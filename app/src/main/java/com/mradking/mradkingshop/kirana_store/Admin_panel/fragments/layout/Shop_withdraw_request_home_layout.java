package com.mradking.mradkingshop.kirana_store.Admin_panel.fragments.layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.Admin_panel.adapter.shop_adapter.shop_withdraw_request_adapter;
import com.mradking.mradkingshop.kirana_store.Admin_panel.intarfac.admin_shop_withraw_payment;
import com.mradking.mradkingshop.kirana_store.custmer.Model.OrderListModal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Shop_withdraw_request_home_layout extends Fragment implements admin_shop_withraw_payment {

    private RecyclerView cart_recycler_view;
    private List<OrderListModal> productList;
    private Activity activity = getActivity();
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public shop_withdraw_request_adapter latestGovtJobRecyclerAdapter;
    public DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.kirana_orderlist_cus, container, false);

        productList = new ArrayList<OrderListModal>();

        cart_recycler_view = root.findViewById(R.id.cart_list);
        firebaseAuth = FirebaseAuth.getInstance();
        latestGovtJobRecyclerAdapter = new shop_withdraw_request_adapter( getActivity(),productList, this, "request");
        LinearLayoutManager lm1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        lm1.setReverseLayout(true);
        lm1.setStackFromEnd(true);

        cart_recycler_view.setHasFixedSize(true);
        cart_recycler_view.setLayoutManager(lm1);
        cart_recycler_view.setAdapter(latestGovtJobRecyclerAdapter);
        firebaseFirestore=FirebaseFirestore.getInstance();
        Toolbar toolbar=root.findViewById(R.id.toolbar);
        toolbar.setTitle("Requested List");

        shop_list();




        return root;
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

            Query firstQuery =  firebaseFirestore.collection("kirana_order_list")
                    .whereEqualTo("order_status","delivered")
                    .whereEqualTo("withdraw_status","requested")
                    .orderBy("timestamp", Query.Direction.DESCENDING).limit(10);




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

                            OrderListModal blogPost = doc.getDocument().toObject(OrderListModal.class).withId(doc.getDocument().getId());
                            String itemId = doc.getDocument().getId();

                            blogPost.setItem_id(itemId);


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

        Query nextQuery =  firebaseFirestore.collection("kirana_order_list")
                .whereEqualTo("order_status","delivered")
                .whereEqualTo("withdraw_status","requested")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .startAfter(lastVisible).limit(10);




        nextQuery.addSnapshotListener( new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (!documentSnapshots.isEmpty()) {

                    lastVisible = documentSnapshots.getDocuments().get(documentSnapshots.size() - 1);
                    for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                        if (doc.getType() == DocumentChange.Type.ADDED) {

                            OrderListModal govt_job = doc.getDocument().toObject(OrderListModal.class);
                            String itemId = doc.getDocument().getId();

                            govt_job.setItem_id(itemId);

                            productList.add(govt_job);

                            latestGovtJobRecyclerAdapter.notifyDataSetChanged();

                        }
                    }

                }

            }
        });
    }


    @Override
    public void payment(int posion) {

        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText("9667412868");

            Intent launchIntent = activity.getPackageManager().getLaunchIntentForPackage("com.phonepe.app");
            if (launchIntent != null) {
                activity.startActivity(launchIntent);//null pointer check in case package name was not found
            }

        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", "9667412868");
            clipboard.setPrimaryClip(clip);

            Intent launchIntent = activity.getPackageManager().getLaunchIntentForPackage("com.phonepe.app");
            if (launchIntent != null) {
                activity.startActivity(launchIntent);//null pointer check in case package name was not found
            }
        }



    }


}

