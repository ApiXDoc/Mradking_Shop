package com.mradking.mradkingshop.kirana_store.custmer.activity;

import android.app.Activity;
import android.app.ProgressDialog;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
import com.mradking.mradkingshop.kirana_store.custmer.adapter.Cart_page_adapter;
import com.mradking.mradkingshop.kirana_store.custmer.adapter.Order_item_list_adapter;
import com.mradking.mradkingshop.kirana_store.custmer.utility.utilityX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order_Item_list extends Activity {
    private RecyclerView cart_recycler_view;
    private List<CommonModal> productList;
    private Activity activity = Order_Item_list.this;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public Order_item_list_adapter latestGovtJobRecyclerAdapter;

    public DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;
    TextView  add_more_items, total_amout_payment_slip,
            total_pay_txt, delivery_fee_payment_slip, net_payment_amount_payment_slip,
            adavance_amount,remain_amount;
    ProgressDialog progressDialog;
    MaterialRippleLayout pickup_bt;
    ScrollView check_out_page;
    RelativeLayout cart_layout;
    LinearLayout payment_layout, advance_amount_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_oder_litem_list);

        productList = new ArrayList<CommonModal>();


        firebaseAuth = FirebaseAuth.getInstance();

        add_more_items = findViewById(R.id.add_more_items);
        latestGovtJobRecyclerAdapter = new Order_item_list_adapter(this, productList);

        progressDialog = new ProgressDialog(this);
        cart_recycler_view = findViewById(R.id.cart_list);
        total_amout_payment_slip = findViewById(R.id.total_amout_payment_slip);
        delivery_fee_payment_slip = findViewById(R.id.delivery_fee_payment_slip);
        net_payment_amount_payment_slip = findViewById(R.id.net_payment_amount_payment_slip);
        total_pay_txt = findViewById(R.id.total_pay_txt);
        advance_amount_layout=findViewById(R.id.advance_amount_layout);
        check_out_page = findViewById(R.id.check_out_page);
        cart_layout = findViewById(R.id.cart_page_layout);
        firebaseFirestore =FirebaseFirestore.getInstance();

        adavance_amount=findViewById(R.id.adavance_amount);
        remain_amount=findViewById(R.id.remain_amount);


        pickup_bt=findViewById(R.id.conform_bt);


        delivery_act_check();



        Query firstQuery = firebaseFirestore.collection("kirna_Add_cart")
                .document(getIntent().getExtras().getString("uid")).collection("cart")
                .whereEqualTo("order_status", "Ordered")
                .whereEqualTo("order_id", getIntent().getExtras().getString("order_id"));


        ListModal listModal=new ListModal();
        listModal.get_list("yes",true,firstQuery,productList,cart_recycler_view,latestGovtJobRecyclerAdapter,this);



        if(getIntent().getExtras().getString("payment_method").contentEquals("online")){


            advance_amount_layout.setVisibility(View.GONE);

            total_amout_payment_slip.setText(getIntent().getExtras().getString("total_amount"));
            delivery_fee_payment_slip.setText(getIntent().getExtras().getString("delivery_charge"));
            net_payment_amount_payment_slip.setText(getIntent().getExtras().getString("net_pay"));


        }

        total_amout_payment_slip.setText(getIntent().getExtras().getString("total_amount"));
        delivery_fee_payment_slip.setText(getIntent().getExtras().getString("delivery_charge"));
        net_payment_amount_payment_slip.setText(getIntent().getExtras().getString("net_pay"));
        adavance_amount.setText(getIntent().getExtras().getString("advance_amount"));
        remain_amount.setText(getIntent().getExtras().getString("remaing_amount"));


        pickup_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progressDialog.setMessage("Please Wait......");
//
                 progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                Map<String,Object>map=new HashMap<>();
                map.put("pickup_status","accept_pickup");
                map.put("dilivery_partner_uid",firebaseAuth.getCurrentUser().getUid());


                firebaseFirestore.collection("kirana_order_list")
                        .document(getIntent().getExtras().getString("item_id"))
                        .update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
//
                                utilityX UtilityX=new utilityX(Order_Item_list.this);
                                UtilityX.notification_send(getIntent()
                                                .getExtras()
                                                .getString("shop_message_token")
                                        ,"Pick Up Accept");

                                progressDialog.dismiss();

                            }
                        });




            }
        });




    }

    private void delivery_act_check() {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("delhivery")) {

                pickup_bt.setVisibility(View.VISIBLE);

            } else {
                // the key does not exist in the extras
            }
        }

    }


}
