package com.mradking.mradkingshop.kirana_store.custmer.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.mradking.mradkingshop.kirana_store.custmer.intface.Firbase_Query_Call;
import com.mradking.mradkingshop.kirana_store.custmer.intface.RecyclerView_home_list;
import com.mradking.mradkingshop.kirana_store.custmer.utility.utilityX;

import java.util.ArrayList;
import java.util.List;

public class Cart_list extends Activity implements RecyclerView_home_list {
    String near_address_st, total_amt_st, delivery_amt_st, net_amt_st, phone_st, house_no_st, colony_st;

    @Override
    public void onBackPressed() {
       Intent intent=new Intent(Cart_list.this,kirana_home_main_act.class);
        intent.putExtra("store_id",getIntent().getExtras().getString("key"));

        startActivity(intent);
       finish();


    }

    private RecyclerView cart_recycler_view;
    private List<CommonModal> productList;
    private Activity activity = Cart_list.this;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public Cart_page_adapter latestGovtJobRecyclerAdapter;

    public DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;
    TextView total_amount, add_more_items, total_amout_payment_slip,
            total_pay_txt, delivery_fee_payment_slip, net_payment_amount_payment_slip;
    ProgressDialog progressDialog;
    MaterialRippleLayout checkout_bt;
    LinearLayoutManager lm1;
    utilityX utilityX;
    ListModal listModal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_cart_page_list);
        insilise();

        utilityX=new utilityX(this);

        data_set_up();

        button_click();

        cart_list();




    }

    private void cart_list() {
        Query firstQuery = firebaseFirestore.collection("kirna_Add_cart")
                    .document(firebaseAuth.getUid().toString()).collection("cart")
                    .whereEqualTo("order_status", "cart");
        ListModal listModal=new ListModal();
        listModal.get_list("yes",true,firstQuery,productList,cart_recycler_view,latestGovtJobRecyclerAdapter,this);

    }

    private void button_click() {

        checkout_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(activity, check_out_address.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                intent.putExtra("total_amt", total_amt_st);
                intent.putExtra("delivery", delivery_amt_st);
                intent.putExtra("net_payment", net_amt_st);
                startActivity(intent);


            }
        });

    }


    private void data_set_up() {
      Query firstQuery = firebaseFirestore.collection("kirna_Add_cart")
                .document(firebaseAuth.getUid().toString()).collection("cart")
                .whereEqualTo("order_status", "cart").limit(10);

        utilityX.get_doc_query(firstQuery, new Firbase_Query_Call() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshot) {

                int total_sale=0;
                for(int i = 0; i < documentSnapshot.getDocuments().size(); i++){


                    total_sale +=  Integer.parseInt((String) documentSnapshot.getDocuments().get(i).get("total_amount"));

                }
                    set_all_data(total_sale);

            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(activity, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void set_all_data(int total_sale) {

        total_amout_payment_slip.setText(String.valueOf(total_sale));

        if (total_sale > 1000) {

            delivery_fee_payment_slip.setText("Free");
            total_amout_payment_slip.setText(String.valueOf(total_sale));
            total_amount.setText(String.valueOf(total_sale));

            net_payment_amount_payment_slip.setText(String.valueOf(total_sale ));

            total_amt_st = String.valueOf(total_sale);
            delivery_amt_st = "Free";
            net_amt_st = String.valueOf(total_sale);


            progressDialog.dismiss();

        } else {

            firebaseFirestore.collection("kirana_delivery_chareges").document("1").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    DocumentSnapshot documentSnapshot = task.getResult();

                    String delivery_charges = documentSnapshot.getString("charges");

                    delivery_fee_payment_slip.setText("Rs " + delivery_charges);

                    total_amout_payment_slip.setText(String.valueOf(total_sale));
                    net_payment_amount_payment_slip.setText(String.valueOf(total_sale + Integer.parseInt(delivery_charges)));
                    total_amount.setText(String.valueOf(total_sale + Integer.parseInt(delivery_charges)));


                    total_amt_st = String.valueOf(total_sale);
                    delivery_amt_st = "Rs" + delivery_charges;
                    net_amt_st = String.valueOf(total_sale + Integer.parseInt(delivery_charges));


                    progressDialog.dismiss();

                }
            });

        }




        }

    private void insilise() {


        productList = new ArrayList<CommonModal>();


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        total_amount = findViewById(R.id.amt);
        add_more_items = findViewById(R.id.add_more_items);
        latestGovtJobRecyclerAdapter = new Cart_page_adapter(activity, productList, this);

        progressDialog = new ProgressDialog(activity);
        cart_recycler_view = findViewById(R.id.cart_list);
        total_amout_payment_slip = findViewById(R.id.total_amout_payment_slip);
        delivery_fee_payment_slip = findViewById(R.id.delivery_fee_payment_slip);
        net_payment_amount_payment_slip = findViewById(R.id.net_payment_amount_payment_slip);
        total_pay_txt = findViewById(R.id.total_pay_txt);



        checkout_bt = findViewById(R.id.checkout_bt);




    }

    @Override
    public void total_amount(int total_amt) {

       data_set_up();


    }

    @Override
    public void visblity_cart_bar(int posion) {

    }

    @Override
    public void total_saving(int amt) {

    }

    @Override
    public void restart_act() {

        productList.clear();

      cart_list();
    }



}
