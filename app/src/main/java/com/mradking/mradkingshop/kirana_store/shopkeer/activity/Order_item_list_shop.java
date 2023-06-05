package com.mradking.mradkingshop.kirana_store.shopkeer.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.mradking.mradkingshop.kirana_store.custmer.Model.cart_list_modal;
import com.mradking.mradkingshop.kirana_store.custmer.activity.Order_Item_list;
import com.mradking.mradkingshop.kirana_store.custmer.adapter.Order_item_list_adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order_item_list_shop extends Activity {
    private RecyclerView cart_recycler_view;
    private List<CommonModal> productList;
    private Activity activity = Order_item_list_shop.this;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public Order_item_list_adapter latestGovtJobRecyclerAdapter;

    public DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;
    TextView add_more_items, total_amout_payment_slip,
            total_pay_txt, delivery_fee_payment_slip, net_payment_amount_payment_slip,
            adavance_amount,remain_amount;
    ProgressDialog progressDialog;
    MaterialRippleLayout conform_bt;
    ScrollView check_out_page;
    RelativeLayout cart_layout;
    LinearLayout payment_layout, advance_amount_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_oder_litem_list_shop);

        productList = new ArrayList<CommonModal>();


        firebaseAuth = FirebaseAuth.getInstance();

        add_more_items = findViewById(R.id.add_more_items);
        latestGovtJobRecyclerAdapter = new Order_item_list_adapter(this, productList);
        LinearLayoutManager lm1 = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);

        progressDialog = new ProgressDialog(this);
        cart_recycler_view = findViewById(R.id.cart_list);
        total_amout_payment_slip = findViewById(R.id.total_amout_payment_slip);
        delivery_fee_payment_slip = findViewById(R.id.delivery_fee_payment_slip);
        net_payment_amount_payment_slip = findViewById(R.id.net_payment_amount_payment_slip);
        total_pay_txt = findViewById(R.id.total_pay_txt);
        advance_amount_layout=findViewById(R.id.advance_amount_layout);
        check_out_page = findViewById(R.id.check_out_page);
        cart_layout = findViewById(R.id.cart_page_layout);

        conform_bt=findViewById(R.id.conform_bt);

        conform_bt.setVisibility(View.VISIBLE);




        adavance_amount=findViewById(R.id.adavance_amount);
        remain_amount=findViewById(R.id.remain_amount);

        cart_recycler_view.setHasFixedSize(true);
        cart_recycler_view.setLayoutManager(lm1);
        cart_recycler_view.setAdapter(latestGovtJobRecyclerAdapter);


        Query firstQuery = firebaseFirestore.collection("kirna_Add_cart")
                .document(getIntent().getExtras().getString("user_uid"))
                .collection("cart")
                .whereEqualTo("order_status",getIntent().getExtras().getString("order_status") )
                .whereEqualTo("order_id", getIntent().getExtras().getString("order_id"));


        ListModal listModal=new ListModal();
        listModal.get_list("yes",true,firstQuery,productList,cart_recycler_view,latestGovtJobRecyclerAdapter,this);




        add_more_items.setText(String.valueOf(productList.size()+1));





        if(getIntent().getExtras().getString("payment_method").contentEquals("online")){


            advance_amount_layout.setVisibility(View.GONE);

            total_amout_payment_slip.setText(getIntent().getExtras().getString("total_amount"));
            delivery_fee_payment_slip.setText(getIntent().getExtras().getString("delivery_charge"));
            net_payment_amount_payment_slip.setText(getIntent().getExtras().getString("net_pay"));

            if(getIntent().getExtras().getString("order_status_key").contentEquals("inprocess")){


                conform_bt.setVisibility(View.GONE);
            }

        }

        total_amout_payment_slip.setText(getIntent().getExtras().getString("total_amount"));
        delivery_fee_payment_slip.setText(getIntent().getExtras().getString("delivery_charge"));
        net_payment_amount_payment_slip.setText(getIntent().getExtras().getString("net_pay"));
        adavance_amount.setText(getIntent().getExtras().getString("advance_amount"));
        remain_amount.setText(getIntent().getExtras().getString("remaing_amount"));


        if(getIntent().getExtras().getString("order_status_key").contentEquals("inprocess")){


            conform_bt.setVisibility(View.GONE);
        }




        conform_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new AlertDialog.Builder(activity)
                        .setTitle("Ready to Ship")
                        .setMessage("Your order is Packed Then Click On Conformed ?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton("CONFORMED", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                progressDialog.setMessage("Please wait........");
                                progressDialog.setCancelable(false);
                                progressDialog.setCanceledOnTouchOutside(false);
                                progressDialog.show();

                                for (int i = 0; i < productList.size(); i++){

                                    Map<String, Object> map = new HashMap<>();


                                    map.put("order_status", "inprocess");

                                    firebaseFirestore.collection("kirna_Add_cart")
                                            .document(getIntent().getExtras().getString("user_uid"))
                                            .collection("cart")
                                            .document(productList.get(i).getItem_id())
                                            .update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {


                                            firebaseFirestore.collection("kirana_order_list")
                                                    .document(getIntent().getExtras().getString("item_id"))
                                                    .update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {

                                                    progressDialog.dismiss();


                                                }
                                            });




                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                            Toast.makeText(getApplicationContext(), "product not Added", Toast.LENGTH_SHORT).show();
                                         progressDialog.dismiss();


                                        }
                                    });



                                }




                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_menu_info_details)
                        .show();




            }
        });



    }



}
