package com.mradking.mradkingshop.kirana_store.shopkeer.list;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.OrderListModal;
import com.mradking.mradkingshop.kirana_store.shopkeer.adapter.WithDraw_Transfer_list_adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Withdraw_request_list extends Activity {
    private RecyclerView cart_recycler_view;
    private List<OrderListModal> productList;
    private Activity activity = Withdraw_request_list.this;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public WithDraw_Transfer_list_adapter latestGovtJobRecyclerAdapter;
    public DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;
    private BottomSheetBehavior mBehavior;
    private BottomSheetDialog mBottomSheetDialog;
    private View bottom_sheet;
    ProgressDialog progressDialog;

    ////////bothom sheet//////

    TextView added_number_bts,your_problem_bts;
    CardView added_bt_bts;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_orderlist_cus);
        productList = new ArrayList<OrderListModal>();

        cart_recycler_view = findViewById(R.id.cart_list);
        firebaseAuth = FirebaseAuth.getInstance();
        latestGovtJobRecyclerAdapter = new WithDraw_Transfer_list_adapter( activity,productList,"requested");
        LinearLayoutManager lm1 = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        lm1.setReverseLayout(true);
        lm1.setStackFromEnd(true);

        cart_recycler_view.setHasFixedSize(true);
        cart_recycler_view.setLayoutManager(lm1);
        cart_recycler_view.setAdapter(latestGovtJobRecyclerAdapter);
        firebaseFirestore=FirebaseFirestore.getInstance();
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Requested List");
        bottom_sheet = findViewById(R.id.bottom_sheet);
        mBehavior = BottomSheetBehavior.from(bottom_sheet);
        progressDialog=new ProgressDialog(this);


        check_phonepe_number_added();



        shop_list();

    }

    private void check_phonepe_number_added() {



        firebaseFirestore.collection("ShoprKeeper_detail")
                .document(firebaseAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                DocumentSnapshot documentSnapshot=task.getResult();


                if(documentSnapshot.getString("phonepe_number")!= null){

                    Toast.makeText(getApplicationContext(), "phonepe number is exits", Toast.LENGTH_SHORT).show();

                }else {

                    showBottomSheetDialog();

                }



            }
        });

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
                    .whereEqualTo("store_uid",uid)
                    .orderBy("timestamp", Query.Direction.DESCENDING).limit(10);



            firstQuery.addSnapshotListener( activity,new EventListener<QuerySnapshot>() {
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
                .whereEqualTo("store_uid",uid)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .startAfter(lastVisible).limit(10);




        nextQuery.addSnapshotListener(activity, new EventListener<QuerySnapshot>() {
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

    private void showBottomSheetDialog() {

        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        final View view = getLayoutInflater().inflate(R.layout.kirana_shop_acount_bothom_sheet, null);


        added_number_bts=view.findViewById(R.id.phone_number);
        your_problem_bts=view.findViewById(R.id.problem);
        added_bt_bts=view.findViewById(R.id.ok_bt);
        added_number_bts.setHint("Your Number");

        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(view);


        added_bt_bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progressDialog.setMessage("Please Wait.......");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(false);

                Map<String,Object>map=new HashMap<>();
                map.put("phonepe_number",added_number_bts.getText().toString());

                firebaseFirestore.collection("ShoprKeeper_detail")
                        .document(firebaseAuth.getCurrentUser().getUid())
                        .update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Phone Number Added", Toast.LENGTH_SHORT).show();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getApplicationContext(), "Phone Number Not Added", Toast.LENGTH_SHORT).show();


                    }
                });


            }
        });









        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });

    }
}

