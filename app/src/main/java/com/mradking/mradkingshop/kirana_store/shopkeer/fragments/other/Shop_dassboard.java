package com.mradking.mradkingshop.kirana_store.shopkeer.fragments.other;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.OrderListModal;
import com.mradking.mradkingshop.kirana_store.custmer.adapter.Kirana_Orderlist_adapter;
import com.mradking.mradkingshop.kirana_store.shopkeer.activity.Shop_stock_main;

import java.util.List;

public class Shop_dassboard extends Fragment {

    TextView order,sales,delivered_order,total_order,todays_order,inprocess_order;
    TextView c1_stock,c1_outstock,c1_nearstock,c1_total_stock,
            c2_stock,c2_outstock,c2_nearstock,c2_total_stock,
            c3_stock,c3_outstock,c3_nearstock,c3_total_stock,
             c4_stock,c4_outstock,c4_nearstock,c4_total_stock,
              c5_stock,c5_outstock,c5_nearstock,c5_total_stock;

    String ct1_total_stock_st;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    TextView total_product_add_request,accepted_product_add_request
            ,total_resolved_request,total_supprot_request;


    private RecyclerView cart_recycler_view;
    private List<OrderListModal> productList;
    private Activity activity = getActivity();

    public Kirana_Orderlist_adapter latestGovtJobRecyclerAdapter;
    public DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;
    String total_order_st;
    LinearLayout foodgrain_bt,product_request_see_more,see_more_request_solove;
    DatePicker datePicker;
    CardView home_hygiene_care,personal_care,berverage,snack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.kirana_shop_dassboard, container, false);

        datePicker = new DatePicker(getActivity());

        order=root.findViewById(R.id.order);
        sales=root.findViewById(R.id.sales);
        delivered_order=root.findViewById(R.id.delivered_order);
        todays_order=root.findViewById(R.id.todays_order);
        inprocess_order=root.findViewById(R.id.inprogress_order);
        total_order=root.findViewById(R.id.total_order);

        product_request_see_more=root.findViewById(R.id.product_request_see_more);
        total_product_add_request=root.findViewById(R.id.total_product_add_request);
        accepted_product_add_request=root.findViewById(R.id.accepted_product_add_request);

        total_resolved_request=root.findViewById(R.id.total_resolved_request);
        total_supprot_request=root.findViewById(R.id.total_supprot_request);
        see_more_request_solove=root.findViewById(R.id.see_more_request_solove);



        c1_stock=root.findViewById(R.id.ct1_instock);
        c1_nearstock=root.findViewById(R.id.ct1_nearstock);
        c1_outstock=root.findViewById(R.id.ct1_outstock);
        c1_total_stock=root.findViewById(R.id.c1_total_stock);


        c2_stock=root.findViewById(R.id.ct2_instock);
        c2_nearstock=root.findViewById(R.id.ct2_nearstock);
        c2_outstock=root.findViewById(R.id.ct2_outstock);
        c2_total_stock=root.findViewById(R.id.c2_total_stock);


        c3_stock=root.findViewById(R.id.ct3_instock);
        c3_nearstock=root.findViewById(R.id.ct3_nearstock);
        c3_outstock=root.findViewById(R.id.ct3_outstock);
        c3_total_stock=root.findViewById(R.id.c3_total_stock);


        c4_stock=root.findViewById(R.id.ct4_instock);
        c4_nearstock=root.findViewById(R.id.ct4_nearstock);
        c4_outstock=root.findViewById(R.id.ct4_outstock);
        c4_total_stock=root.findViewById(R.id.c4_total_stock);


        c5_stock=root.findViewById(R.id.ct5_instock);
        c5_nearstock=root.findViewById(R.id.ct5_nearstock);
        c5_outstock=root.findViewById(R.id.ct5_outstock);
        c5_total_stock=root.findViewById(R.id.c5_total_stock);

        personal_care=root.findViewById(R.id.personal_care);



        foodgrain_bt=root.findViewById(R.id.foodgrain_bt);
        snack=root.findViewById(R.id.snack);

        progressDialog=new ProgressDialog(getActivity());
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        home_hygiene_care=root.findViewById(R.id.home_hygiene_care);
        berverage=root.findViewById(R.id.berverage);


        data_setup();

        stock_data_set_up();

        support_data_set_up();




        category_data_setup("kirana_product_grossery");

        category_data_setup("kirana_home_hygience_product");

        category_data_setup("kirana_beverages_product");

        category_data_setup("kirana_persnoal_care_product");

        category_data_setup("kirana_snacks_product");

        foodgrain_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(getActivity(), Shop_stock_main.class);
                intent.putExtra("key","kirana_product_grossery");
                startActivity(intent);

            }
        });

        home_hygiene_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(), Shop_stock_main.class);
                intent.putExtra("key","kirana_home_hygience_product");
                startActivity(intent);

            }
        });

        personal_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(), Shop_stock_main.class);
                intent.putExtra("key","kirana_persnoal_care_product");
                startActivity(intent);

            }
        });

        berverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(), Shop_stock_main.class);
                intent.putExtra("key","kirana_beverages_product");
                startActivity(intent);

            }
        });

        snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(), Shop_stock_main.class);
                intent.putExtra("key","kirana_snacks_product");
                startActivity(intent);

            }
        });


    return root;



    }

    private void support_data_set_up() {

        firebaseFirestore.collection("kirana_shops_problem")
                .document(firebaseAuth.getCurrentUser().getUid())
                .collection("problms")
                .whereEqualTo("status","not_soloved").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {


                QuerySnapshot documentSnapshots=task.getResult();

                total_supprot_request.setText(String.valueOf(documentSnapshots.size()));





            }
        });


        firebaseFirestore.collection("kirana_shops_problem")
                .document(firebaseAuth.getCurrentUser().getUid())
                .collection("problms")
                .whereEqualTo("status","soloved").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {


                QuerySnapshot documentSnapshots=task.getResult();

                total_resolved_request.setText(String.valueOf(documentSnapshots.size()));





            }
        });


    }



    private void stock_data_set_up() {

        category_stock_data_set_up("kirana_product_grossery","instock");
        category_stock_data_set_up("kirana_home_hygience_product","instock");
        category_stock_data_set_up("kirana_beverages_product","instock");
        category_stock_data_set_up("kirana_persnoal_care_product","instock");
        category_stock_data_set_up("kirana_snacks_product","instock");


        category_out_stock_data_set_up("kirana_product_grossery","outstock");
        category_out_stock_data_set_up("kirana_home_hygience_product","outstock");
        category_out_stock_data_set_up("kirana_beverages_product","outstock");
        category_out_stock_data_set_up("kirana_persnoal_care_product","outstock");
        category_out_stock_data_set_up("kirana_snacks_product","outstock");


        category_near_out_stock_data_set_up("kirana_product_grossery","near_out_stock");
        category_near_out_stock_data_set_up("kirana_home_hygience_product","near_out_stock");
        category_near_out_stock_data_set_up("kirana_beverages_product","near_out_stock");
        category_near_out_stock_data_set_up("kirana_persnoal_care_product","near_out_stock");
        category_near_out_stock_data_set_up("kirana_snacks_product","near_out_stock");



    }

    private void category_near_out_stock_data_set_up(String kirana_product_grossery, String near_out_stock) {

        firebaseFirestore.collection("kirana_shopkeeper_product")
                .document(firebaseAuth.getCurrentUser().getUid())
                .collection("proudcts")
                .whereEqualTo("main_category",kirana_product_grossery)
                .whereEqualTo("stock_status",near_out_stock)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        QuerySnapshot documentSnapshot=task.getResult();

                        if(kirana_product_grossery.contentEquals("kirana_product_grossery")){

                            c1_nearstock.setText("Near Out\n"+String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }else if(kirana_product_grossery.contentEquals("kirana_home_hygience_product")){

                            c2_nearstock.setText("Near Out\n"+String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }else if(kirana_product_grossery.contentEquals("kirana_persnoal_care_product")){
                            c3_nearstock.setText("Near Out\n"+String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }else if(kirana_product_grossery.contentEquals("kirana_beverages_product")){

                            c4_nearstock.setText("Near Out\n"+String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }else if(kirana_product_grossery.contentEquals("kirana_snacks_product")){

                            c5_nearstock.setText("Near Out\n"+String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }



                    }
                });

    }

    private void category_out_stock_data_set_up(String kirana_product_grossery, String outstock) {

        firebaseFirestore.collection("kirana_shopkeeper_product")
                .document(firebaseAuth.getCurrentUser().getUid())
                .collection("proudcts")
                .whereEqualTo("main_category",kirana_product_grossery)
                .whereEqualTo("stock_status",outstock)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        QuerySnapshot documentSnapshot=task.getResult();

                        if(kirana_product_grossery.contentEquals("kirana_product_grossery")){

                            c1_outstock.setText("Out Of Stock\n"+String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }else if(kirana_product_grossery.contentEquals("kirana_home_hygience_product")){

                            c2_outstock.setText("Out Of Stock\n"+String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }else if(kirana_product_grossery.contentEquals("kirana_persnoal_care_product")){
                            c3_outstock.setText("Out Of Stock\n"+String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }else if(kirana_product_grossery.contentEquals("kirana_beverages_product")){

                            c4_outstock.setText("Out Of Stock\n"+String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }else if(kirana_product_grossery.contentEquals("kirana_snacks_product")){

                            c5_outstock.setText("Out Of Stock\n"+String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }



                    }
                });


    }

    private void category_stock_data_set_up(String kirana_product_grossery, String instock) {
        firebaseFirestore.collection("kirana_shopkeeper_product")
                .document(firebaseAuth.getCurrentUser().getUid())
                .collection("proudcts")
                .whereEqualTo("main_category",kirana_product_grossery)
                .whereEqualTo("stock_status",instock)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        QuerySnapshot documentSnapshot=task.getResult();

                        if(kirana_product_grossery.contentEquals("kirana_product_grossery")){


                            c1_stock.setText("Instock\n"+String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }else if(kirana_product_grossery.contentEquals("kirana_home_hygience_product")){

                            c2_stock.setText("Instock\n"+String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }else if(kirana_product_grossery.contentEquals("kirana_persnoal_care_product")){
                            c3_stock.setText("Instock\n"+String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }else if(kirana_product_grossery.contentEquals("kirana_beverages_product")){

                            c4_stock.setText("Instock\n"+String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }else if(kirana_product_grossery.contentEquals("kirana_snacks_product")){

                            c5_stock.setText("Instock\n"+String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }



                    }
                });

    }

    private void category_data_setup(String kirana_product_grossery) {

        firebaseFirestore.collection("kirana_shopkeeper_product")
                .document(firebaseAuth.getCurrentUser().getUid())
               .collection("proudcts")
                .whereEqualTo("main_category",kirana_product_grossery).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        QuerySnapshot documentSnapshot=task.getResult();

                        if(kirana_product_grossery.contentEquals("kirana_product_grossery")){

                            ct1_total_stock_st=String.valueOf(documentSnapshot.getDocuments().size());
                            c1_total_stock.setText(String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }else if(kirana_product_grossery.contentEquals("kirana_home_hygience_product")){

                            c2_total_stock.setText(String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }else if(kirana_product_grossery.contentEquals("kirana_beverages_product")){
                            c3_total_stock.setText(String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }else if(kirana_product_grossery.contentEquals("kirana_persnoal_care_product")){

                            c4_total_stock.setText(String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }else if(kirana_product_grossery.contentEquals("kirana_snacks_product")){

                            c5_total_stock.setText(String.valueOf(documentSnapshot.getDocuments().size()));
                            progressDialog.dismiss();

                        }



                    }
                });



    }

    private void data_setup() {

        progressDialog.setMessage("Please Wait..............");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        firebaseFirestore.collection("kirana_order_list")
                .whereEqualTo("store_uid",firebaseAuth.getCurrentUser().getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {


                QuerySnapshot documentSnapshot=task.getResult();

                total_order_st=String.valueOf(documentSnapshot.getDocuments().size());
                order.setText(String.valueOf(documentSnapshot.getDocuments().size()));

               delivered_order_data_setup();

            }
        });





    }

    private void delivered_order_data_setup() {

        firebaseFirestore.collection("kirana_order_list")
                .whereEqualTo("store_uid",firebaseAuth.getCurrentUser().getUid())
                .whereEqualTo("order_status","delivered")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        QuerySnapshot documentSnapshot=task.getResult();



                        delivered_order.setText(String.valueOf(documentSnapshot.getDocuments().size()));
                       inprosess_data_setup();

                    }
                });
    }

    private void inprosess_data_setup() {

        firebaseFirestore.collection("kirana_order_list")
                .whereEqualTo("store_uid",firebaseAuth.getCurrentUser().getUid())
                .whereEqualTo("order_status","inprocess")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        QuerySnapshot documentSnapshot=task.getResult();

                        inprocess_order.setText(String.valueOf(documentSnapshot.getDocuments().size()));


                        total_sale_data_setup();


                    }
                });


    }

    private void total_sale_data_setup() {

        firebaseFirestore.collection("kirana_order_list")
                .whereEqualTo("store_uid",firebaseAuth.getCurrentUser().getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        QuerySnapshot documentSnapshot=task.getResult();

                            int total_sale=0;
                        for(int i = 0; i < documentSnapshot.getDocuments().size(); i++){


                          total_sale +=  Integer.parseInt((String) documentSnapshot.getDocuments().get(i).get("total_amt"));

                        }


                        sales.setText(String.valueOf(total_sale));

                        today_number_order();


                    }
                });




    }

    private void today_number_order() {

        String current_date = datePicker.getDayOfMonth() + "/" + datePicker.getMonth() + "/" + datePicker.getYear();

        firebaseFirestore.collection("kirana_order_list")
                .whereEqualTo("store_uid",firebaseAuth.getCurrentUser().getUid())
                .whereEqualTo("order_date",current_date)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        QuerySnapshot documentSnapshot=task.getResult();

                        todays_order.setText(String.valueOf(documentSnapshot.getDocuments().size()));
                        total_order.setText(total_order_st);



                    }
                });


    }
}
