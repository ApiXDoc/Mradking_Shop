package com.mradking.mradkingshop.kirana_store.custmer.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;
import com.mradking.mradkingshop.kirana_store.custmer.Model.ListModal;
import com.mradking.mradkingshop.kirana_store.custmer.adapter.Cart_page_adapter;
import com.mradking.mradkingshop.kirana_store.custmer.intface.RecyclerView_home_list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mothly_List extends Activity implements RecyclerView_home_list {
    String near_address_st, total_amt_st, delivery_amt_st, net_amt_st, phone_st, house_no_st, colony_st;

    private RecyclerView cart_recycler_view;
    private List<CommonModal> productList;
    private Activity activity = Mothly_List.this;
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
    TextView check_out_bt_txt;
    RelativeLayout date_layout;
    String month_list_status;
    DatePicker date_picker;
    CardView data_set_up_bt;
    String date_st="no";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_cart_page_list);
        insilise();

        cart_list();

        check_out_bt_txt=findViewById(R.id.check_out_bt_txt);
        date_layout=findViewById(R.id.date_layout);
        date_picker=findViewById(R.id.date_picker);
        data_set_up_bt=findViewById(R.id.data_set_up_bt);

        cart_recycler_view.setAdapter(latestGovtJobRecyclerAdapter);

        data_set_up();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date_picker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {

                    date_st=i+"/"+i1+"/"+i2;


                }
            });
        }

        data_set_up_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(date_st.contentEquals("no")){

                    Toast.makeText(activity, "please Selact Date For Your Monthly Order", Toast.LENGTH_SHORT).show();
                }else {

                    progressDialog.setMessage("Please Wait.......");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    Map<String,Object> map=new HashMap<>();
                    map.put("month_list_status","yes");
                    map.put("modthly_order_date",date_st);
                    firebaseFirestore.collection("kirana_user_details")
                            .document(firebaseAuth.getCurrentUser().getUid()).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {


                                    progressDialog.dismiss();
                                    Intent intent=new Intent(Mothly_List.this, Monthly_order_list.class);
                                    startActivity(intent);
                                    finish();


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    progressDialog.dismiss();
                                    Toast.makeText(activity, "data is not update", Toast.LENGTH_SHORT).show();

                                }
                            });



                }


            }
        });


        checkout_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(month_list_status.contentEquals("yes")){

                    Intent intent=new Intent(activity, check_out_address.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    intent.putExtra("total_amt", total_amt_st);
                    intent.putExtra("delivery", delivery_amt_st);
                    intent.putExtra("net_payment", net_amt_st);
                    startActivity(intent);



                }else if(month_list_status.contentEquals("no")){

                    date_layout.setVisibility(View.VISIBLE);
                }else {
                    Toast.makeText(activity, "data not found", Toast.LENGTH_SHORT).show();
                }



            }
        });



    }

    private void cart_list() {


        Query firstQuery = firebaseFirestore.collection("kirna_Add_cart")
                    .document(firebaseAuth.getUid().toString()).collection("cart")
                    .whereEqualTo("order_status", "month").
                whereEqualTo("list_type","month");



            ListModal listModal=new ListModal();
            listModal.get_list("yes",true,firstQuery,productList,cart_recycler_view,latestGovtJobRecyclerAdapter,this);


    }


    private void data_set_up() {


        Query firstQuery = firebaseFirestore.collection("kirna_Add_cart")
                .document(firebaseAuth.getUid().toString()).collection("cart")
                .whereEqualTo("order_status", "month").whereEqualTo("list_type","month").limit(10);

        firstQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot documentSnapshots=task.getResult();
                int total_sale=0;
                for(int i = 0; i < documentSnapshots.getDocuments().size(); i++){


                    total_sale +=  Integer.parseInt((String) documentSnapshots.getDocuments().get(i).get("total_amount"));

                }

                monthly_list_check();
                set_all_data(total_sale);




            }
        });

    }

    private void monthly_list_check() {

        firebaseFirestore.collection("kirana_user_details")
                .document(firebaseAuth.getUid().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        DocumentSnapshot documentSnapshot=task.getResult();

                         month_list_status=documentSnapshot.getString("month_list_status");

                        if(month_list_status.contentEquals("no")){

                            check_out_bt_txt.setText("Set Your Monthly Order Date");

                        }else {



                        }


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
        lm1 = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);

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

