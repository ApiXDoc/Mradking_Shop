package com.mradking.mradkingshop.kirana_store.custmer.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;
import com.mradking.mradkingshop.kirana_store.custmer.Model.ListModal;
import com.mradking.mradkingshop.kirana_store.custmer.adapter.product_list_adapter;
import com.mradking.mradkingshop.kirana_store.custmer.intface.Firbase_Doc_Call;
import com.mradking.mradkingshop.kirana_store.custmer.intface.Get_view;
import com.mradking.mradkingshop.kirana_store.custmer.intface.RecyclerView_home_list;
import com.mradking.mradkingshop.kirana_store.custmer.utility.utilityX;

public class product_list extends AppCompatActivity implements RecyclerView_home_list {

    private RecyclerView cart_recycler_view;
    private List<CommonModal> productList;
    private Activity activity = product_list.this;
    private FirebaseFirestore firebaseFirestore;
    public product_list_adapter latestGovtJobRecyclerAdapter;
    BottomNavigationView bottomNavigationView;
    String page_status="home";
    String uid,  category;

    View view;
    private View bottom_sheet;
    utilityX utilityX;
    Glide glide;
    int count=1;
    int count_1=1;

    int cart_count=1;
    int month_count=1;

    int cart_count2=1;
    int month_count2=1;

    int cart_count3=1;
    int month_count3=1;

    int cart_count4=1;
    int month_count4=1;

    int cart_count5=1;
    int month_count5=1;
    int test_count=1;
    private BottomSheetBehavior mBehavior;
    private BottomSheetDialog mBottomSheetDialog;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_product_list);



        inslisation();
        list_set_up();

        set_bottom_nav();


        bottom_sheet = findViewById(R.id.bottom_sheet);
        mBehavior = BottomSheetBehavior.from(bottom_sheet);







    }

    private void set_bottom_nav() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_1:

                        page_status="home";

                        return true;
                    case R.id.navigation_2:

                        page_status="cart";
                        Intent intent=new Intent(product_list.this,Cart_list.class);
                        intent.putExtra("key",uid);
                        startActivity(intent);


                        return true;

                    case R.id.navigation_3:
                        page_status="monthlist";
                        Intent intent1=new Intent(product_list.this,Monthly_order_list.class);
                        startActivity(intent1);

                        return true;

                }

                return true;
            }
        });

    }

    private void inslisation() {

        cart_recycler_view =findViewById(R.id.cart_list);
        firebaseFirestore=FirebaseFirestore.getInstance();
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);
        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        utilityX=new utilityX(this);
//        mBehavior = BottomSheetBehavior.from(bottom_sheet);



    }

    private void list_set_up() {
        productList = new ArrayList<CommonModal>();
        uid=getIntent().getExtras().getString("store_uid");
        category=getIntent().getExtras().getString("key");

        Query query=firebaseFirestore.collection("kirana_shopkeeper_product")
                .document(uid)
                .collection("proudcts")
                .whereEqualTo("sub_category",category);

        latestGovtJobRecyclerAdapter = new product_list_adapter( activity,productList,this);

        ListModal listModal=new ListModal();
        listModal.get_list("yes",true,query,productList,cart_recycler_view,latestGovtJobRecyclerAdapter,this);


    }


    @Override
    public void total_amount(int total_amt) {


    }

    @Override
    public void visblity_cart_bar(int posion) {



                BottomSheetDialog_set_up_1(posion);

    }

    private void BottomSheetDialog_set_up_1(int posion) {
        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        final View view = getLayoutInflater().inflate(R.layout.kirana_customer_vartion_bottom_row, null);
        mBottomSheetDialog = new BottomSheetDialog(product_list.this);
        mBottomSheetDialog.setContentView(view);
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

        CardView var_ly_1=view.findViewById(R.id.var_ly_1);
        CardView var_ly_2=view.findViewById(R.id.var_ly_2);
        CardView var_ly_3=view.findViewById(R.id.var_ly_3);
        CardView var_ly_4=view.findViewById(R.id.var_ly_4);
        CardView var_ly_5=view.findViewById(R.id.var_ly_5);
        CardView var_ly_6=view.findViewById(R.id.var_ly_6);



        ImageView product_img=view.findViewById(R.id.product_image_btm);
        TextView product_name=view.findViewById(R.id.product_name);
        TextView number_of_varition=view.findViewById(R.id.mrp_btm);
        String number_vartion_st=productList.get(posion).getNumber_of_varition();
        glide.with(getApplicationContext()).load(productList.get(posion).getProduct_image_url()).into(product_img);

        product_name.setText(productList.get(posion).getName_product());
        number_of_varition.setText("Variation Available:- "+number_vartion_st);

        if(number_vartion_st.contentEquals("1")){

            var_ly_1.setVisibility(View.VISIBLE);
            var_ly_2.setVisibility(View.VISIBLE);

            set_vartion_details_0(this,view,posion);
            set_vartion_details_1(this,view,posion);

        }else if(number_vartion_st.contentEquals("2")){
            var_ly_1.setVisibility(View.VISIBLE);
            var_ly_2.setVisibility(View.VISIBLE);
            var_ly_3.setVisibility(View.VISIBLE);

            set_vartion_details_0(this,view,posion);
            set_vartion_details_1(this,view,posion);
            set_vartion_details_2(this,view,posion);


        }else if(number_vartion_st.contentEquals("3")){
            var_ly_1.setVisibility(View.VISIBLE);
            var_ly_2.setVisibility(View.VISIBLE);
            var_ly_3.setVisibility(View.VISIBLE);
            var_ly_4.setVisibility(View.VISIBLE);


            set_vartion_details_0(this,view,posion);
            set_vartion_details_1(this,view,posion);
            set_vartion_details_2(this,view,posion);
            set_vartion_details_3(this,view,posion);


        }else if(number_vartion_st.contentEquals("4")){
            var_ly_1.setVisibility(View.VISIBLE);
            var_ly_2.setVisibility(View.VISIBLE);
            var_ly_3.setVisibility(View.VISIBLE);
            var_ly_4.setVisibility(View.VISIBLE);
            var_ly_5.setVisibility(View.VISIBLE);

            set_vartion_details_0(this,view,posion);
            set_vartion_details_1(this,view,posion);
            set_vartion_details_2(this,view,posion);
            set_vartion_details_3(this,view,posion);
            set_vartion_details_4(this,view,posion);


        }else if(number_vartion_st.contentEquals("5")){

            var_ly_1.setVisibility(View.VISIBLE);
            var_ly_2.setVisibility(View.VISIBLE);
            var_ly_3.setVisibility(View.VISIBLE);
            var_ly_4.setVisibility(View.VISIBLE);
            var_ly_5.setVisibility(View.VISIBLE);
            var_ly_6.setVisibility(View.VISIBLE);

            set_vartion_details_0(this,view,posion);
            set_vartion_details_1(this,view,posion);
            set_vartion_details_2(this,view,posion);
            set_vartion_details_3(this,view,posion);
            set_vartion_details_4(this,view,posion);
            set_vartion_details_5(this,view,posion);


        }

    }


    private void set_vartion_details_0(Context context, View view, int posion) {

        TextView salling_price=view.findViewById(R.id.salling_price);
        TextView mrp=view.findViewById(R.id.stock);
        TextView quentity=view.findViewById(R.id.weight);
        TextView saving=view.findViewById(R.id.saving);

        CardView add_month_list_bt =view.findViewById(R.id.remove_bt);
        CardView add_cart_bt =view.findViewById(R.id.add_cart_bt);

        LinearLayout add_month_quantity_bt=view.findViewById(R.id.add_month_quantity_bt);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        TextView minnu_month=view.findViewById(R.id.minnu_month);
        TextView quantity_month=view.findViewById(R.id.quantity_month);
        TextView add_month=view.findViewById(R.id.add_month);

        LinearLayout add_cart_quantity_bt=view.findViewById(R.id.add_cart_quantity_bt);
        TextView minnu=view.findViewById(R.id.minnu);
        TextView quantity=view.findViewById(R.id.quantity);
        TextView add=view.findViewById(R.id.add);



        salling_price.setText("Selling Price :- ₹"+productList.get(posion).getSalling_price());
        mrp.setText("₹"+productList.get(posion).getMarket_price());
        quentity.setText(productList.get(posion).getQuantity());
        saving.setText("₹"+String.valueOf(Integer.parseInt(productList.get(posion).getMarket_price())-
                Integer.parseInt(productList.get(posion).getSalling_price())));
        String item_id_st=getRandomString(5);



        add_month_list_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               add_month_quantity_bt.setVisibility(View.VISIBLE);
                add_month_list_bt.setVisibility(View.GONE);
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                Map<String, Object> map = new HashMap<>();
                map.put("name_product", productList.get(posion).getName_product());
                map.put("market_price", productList.get(posion).getMarket_price());
                map.put("salling_price", productList.get(posion).getSalling_price());
                map.put("product_image_url", productList.get(posion).getProduct_image_url());
                map.put("item_id", item_id_st);
                map.put("product_id",productList.get(posion).getItemId());
                map.put("quantity",String.valueOf( count_1));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getSalling_price())* count));
                map.put("order_status", "month");
                map.put("list_type","month");


                firebaseFirestore.collection("kirna_Add_cart")
                        .document(firebaseAuth.getUid().toString()).collection("cart")
                        .document(item_id_st).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {



                                progressDialog.dismiss();



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(Exception e) {

                                Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                            }
                        });


            }
        });

        minnu_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                if(count_1<=0){

                   quantity_month.setText(String.valueOf(count_1));

                   count=1;

                   add_month_quantity_bt.setVisibility(View.GONE);
                   add_month_list_bt.setVisibility(View.VISIBLE);

                }if(count_1==1){
                    add_month_quantity_bt.setVisibility(View.GONE);
                    add_month_list_bt.setVisibility(View.VISIBLE);

                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {


                                   progressDialog.dismiss();

                                }
                            });



                }else {

                    Map<String, Object> map = new HashMap<>();

                   quantity_month.setText(String.valueOf( --count_1));


                    map.put("quantity",String.valueOf(count_1));
                    map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getSalling_price())*count_1));


                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {


                                      progressDialog.dismiss();


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {

                                    Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                   progressDialog.dismiss();


                                }
                            });
                }


            }
        });


        add_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();


                 quantity_month.setText(String.valueOf( ++count_1));

                Map<String, Object> map = new HashMap<>();


                map.put("quantity",String.valueOf( count_1));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getSalling_price())* count_1));


                firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                        progressDialog.dismiss();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();

                    }
                });




            }
        });




        minnu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                if(count<=0){

                    quantity.setText(String.valueOf( count));

                    count=1;

                    add_cart_quantity_bt.setVisibility(View.GONE);
                    add_cart_bt.setVisibility(View.VISIBLE);

                }if(count==1){
                    add_cart_quantity_bt.setVisibility(View.GONE);
                    add_cart_bt.setVisibility(View.VISIBLE) ;

                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {


                                    progressDialog.dismiss();

                                }
                            });



                }else {

                    Map<String, Object> map = new HashMap<>();

                    quantity.setText(String.valueOf( --count));


                    map.put("quantity",String.valueOf( count));
                    map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getSalling_price())* count));


                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {


                                           progressDialog.dismiss();


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {

                                    Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();


                                }
                            });
                }


            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                quantity.setText(String.valueOf( ++count));

                Map<String, Object> map = new HashMap<>();


                map.put("quantity",String.valueOf( count));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getSalling_price())* count));


                firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                      progressDialog.dismiss();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                      progressDialog.dismiss();

                    }
                });




            }
        });

          add_cart_bt.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {


                  ProgressDialog progressDialog=new ProgressDialog(context);
                  progressDialog.setMessage("Please Wait......");
                  progressDialog.setCancelable(false);
                  progressDialog.setCanceledOnTouchOutside(false);
                  progressDialog.show();

                  add_cart_quantity_bt.setVisibility(View.VISIBLE);
                  add_cart_bt.setVisibility(View.GONE);

                  Map<String, Object> map = new HashMap<>();
                  map.put("name_product", productList.get(posion).getName_product());
                  map.put("market_price", productList.get(posion).getMarket_price());
                  map.put("salling_price", productList.get(posion).getSalling_price());
                  map.put("product_image_url", productList.get(posion).getProduct_image_url());
                  map.put("item_id", item_id_st);
                  map.put("product_id",productList.get(posion).getItemId());
                  map.put("quantity",String.valueOf(count));
                  map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getSalling_price())*count));
                  map.put("order_status", "cart");


                  firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                      @Override
                      public void onSuccess(Void aVoid) {



                          progressDialog.dismiss();



                      }
                  }).addOnFailureListener(new OnFailureListener() {
                      @Override
                      public void onFailure(Exception e) {

                          Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                      }
                  });

              }
          });      







    }

    private void set_vartion_details_1(Context context, View view, int posion ) {

        TextView salling_price=view.findViewById(R.id.salling_price1);
        TextView mrp=view.findViewById(R.id.stock1);
        TextView quentity=view.findViewById(R.id.weight1);
        TextView saving=view.findViewById(R.id.saving1);

        CardView add_month_list_bt =view.findViewById(R.id.remove_bt1);
        CardView add_cart_bt =view.findViewById(R.id.add_cart_bt1);

        LinearLayout add_month_quantity_bt=view.findViewById(R.id.add_month_quantity_bt1);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        TextView minnu_month=view.findViewById(R.id.minnu_month1);
        TextView quantity_month=view.findViewById(R.id.quantity_month1);
        TextView add_month=view.findViewById(R.id.add_month1);

        LinearLayout add_cart_quantity_bt=view.findViewById(R.id.add_cart_quantity_bt1);
        TextView minnu=view.findViewById(R.id.minnu1);
        TextView quantity=view.findViewById(R.id.quantity1);
        TextView add=view.findViewById(R.id.add1);



        salling_price.setText("Selling Price :- ₹"+productList.get(posion).getVar_1_salling_price());
        mrp.setText("₹"+productList.get(posion).getVar_1_mrp());
        quentity.setText(productList.get(posion).getVar_1_qt());
        saving.setText("₹"+String.valueOf(Integer.parseInt(productList.get(posion).getVar_1_mrp())-
                Integer.parseInt(productList.get(posion).getVar_1_salling_price())));
        String item_id_st=getRandomString(5);



        add_month_list_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_month_quantity_bt.setVisibility(View.VISIBLE);
                add_month_list_bt.setVisibility(View.GONE);
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                Map<String, Object> map = new HashMap<>();
                map.put("name_product", productList.get(posion).getName_product());
                map.put("market_price", productList.get(posion).getVar_1_mrp());
                map.put("salling_price", productList.get(posion).getVar_2_salling_price());
                map.put("product_image_url", productList.get(posion).getProduct_image_url());
                map.put("item_id", item_id_st);
                map.put("product_id",productList.get(posion).getItemId());
                map.put("quantity",String.valueOf( month_count));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_1_salling_price())* month_count));
                map.put("order_status", "month");
                map.put("list_type","month");


                firebaseFirestore.collection("kirna_Add_cart")
                        .document(firebaseAuth.getUid().toString()).collection("cart")
                        .document(item_id_st).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {



                                progressDialog.dismiss();



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(Exception e) {

                                Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                            }
                        });


            }
        });

        minnu_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                if(month_count<=0){

                    quantity_month.setText(String.valueOf(month_count));

                    month_count=1;

                    add_month_quantity_bt.setVisibility(View.GONE);
                    add_month_list_bt.setVisibility(View.VISIBLE);

                }if(month_count==1){
                    add_month_quantity_bt.setVisibility(View.GONE);
                    add_month_list_bt.setVisibility(View.VISIBLE);

                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {


                                    progressDialog.dismiss();

                                }
                            });



                }else {

                    Map<String, Object> map = new HashMap<>();

                    quantity_month.setText(String.valueOf( --month_count));


                    map.put("quantity",String.valueOf(month_count));
                    map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_1_salling_price())*month_count));


                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {


                                    progressDialog.dismiss();


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {

                                    Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();


                                }
                            });
                }


            }
        });


        add_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();


                quantity_month.setText(String.valueOf( ++month_count));

                Map<String, Object> map = new HashMap<>();


                map.put("quantity",String.valueOf( month_count));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_1_salling_price())* month_count));


                firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                        progressDialog.dismiss();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();

                    }
                });




            }
        });




        minnu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                if(cart_count<=0){

                    quantity.setText(String.valueOf( cart_count));

                    cart_count=1;

                    add_cart_quantity_bt.setVisibility(View.GONE);
                    add_cart_bt.setVisibility(View.VISIBLE);

                }if(count==1){
                    add_cart_quantity_bt.setVisibility(View.GONE);
                    add_cart_bt.setVisibility(View.VISIBLE) ;

                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {


                                    progressDialog.dismiss();

                                }
                            });



                }else {

                    Map<String, Object> map = new HashMap<>();

                    quantity.setText(String.valueOf( --cart_count));


                    map.put("quantity",String.valueOf( cart_count));
                    map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_1_salling_price())* cart_count));


                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {


                                    progressDialog.dismiss();


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {

                                    Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();


                                }
                            });
                }


            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                quantity.setText(String.valueOf( ++cart_count));

                Map<String, Object> map = new HashMap<>();


                map.put("quantity",String.valueOf( cart_count));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_1_salling_price())* cart_count));


                firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                        progressDialog.dismiss();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();

                    }
                });




            }
        });

        add_cart_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                add_cart_quantity_bt.setVisibility(View.VISIBLE);
                add_cart_bt.setVisibility(View.GONE);

                Map<String, Object> map = new HashMap<>();
                map.put("name_product", productList.get(posion).getName_product());
                map.put("market_price", productList.get(posion).getVar_1_mrp());
                map.put("salling_price", productList.get(posion).getVar_1_salling_price());
                map.put("product_image_url", productList.get(posion).getProduct_image_url());
                map.put("item_id", item_id_st);
                map.put("product_id",productList.get(posion).getItemId());
                map.put("quantity",String.valueOf(cart_count));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_1_salling_price())*cart_count));
                map.put("order_status", "cart");


                firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {



                        progressDialog.dismiss();



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });







    }

    private void set_vartion_details_2(Context context, View view, int posion) {

        TextView salling_price=view.findViewById(R.id.salling_price2);
        TextView mrp=view.findViewById(R.id.stock2);
        TextView quentity=view.findViewById(R.id.weight2);
        TextView saving=view.findViewById(R.id.saving2);

        CardView add_month_list_bt =view.findViewById(R.id.remove_bt2);
        CardView add_cart_bt =view.findViewById(R.id.add_cart_bt2);

        LinearLayout add_month_quantity_bt=view.findViewById(R.id.add_month_quantity_bt2);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        TextView minnu_month=view.findViewById(R.id.minnu_month2);
        TextView quantity_month=view.findViewById(R.id.quantity_month2);
        TextView add_month=view.findViewById(R.id.add_month2);

        LinearLayout add_cart_quantity_bt=view.findViewById(R.id.add_cart_quantity_bt2);
        TextView minnu=view.findViewById(R.id.minnu2);
        TextView quantity=view.findViewById(R.id.quantity2);
        TextView add=view.findViewById(R.id.add2);



        salling_price.setText("Selling Price :- ₹"+productList.get(posion).getVar_2_salling_price());
        mrp.setText("₹"+productList.get(posion).getVar_2_mrp());
        quentity.setText(productList.get(posion).getVar_2_qt());
        saving.setText("₹"+String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_mrp())-
                Integer.parseInt(productList.get(posion).getVar_2_salling_price())));
        String item_id_st=getRandomString(5);



        add_month_list_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_month_quantity_bt.setVisibility(View.VISIBLE);
                add_month_list_bt.setVisibility(View.GONE);
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                Map<String, Object> map = new HashMap<>();
                map.put("name_product", productList.get(posion).getName_product());
                map.put("market_price", productList.get(posion).getVar_2_mrp());
                map.put("salling_price", productList.get(posion).getVar_2_salling_price());
                map.put("product_image_url", productList.get(posion).getProduct_image_url());
                map.put("item_id", item_id_st);
                map.put("product_id",productList.get(posion).getItemId());
                map.put("quantity",String.valueOf( month_count2));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())* month_count2));
                map.put("order_status", "month");
                map.put("list_type","month");


                firebaseFirestore.collection("kirna_Add_cart")
                        .document(firebaseAuth.getUid().toString()).collection("cart")
                        .document(item_id_st).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {



                                progressDialog.dismiss();



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(Exception e) {

                                Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                            }
                        });


            }
        });

        minnu_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                if(month_count2<=0){

                    quantity_month.setText(String.valueOf(month_count2));

                    month_count2=1;

                    add_month_quantity_bt.setVisibility(View.GONE);
                    add_month_list_bt.setVisibility(View.VISIBLE);

                }if(month_count2==1){
                    add_month_quantity_bt.setVisibility(View.GONE);
                    add_month_list_bt.setVisibility(View.VISIBLE);

                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {


                                    progressDialog.dismiss();

                                }
                            });



                }else {

                    Map<String, Object> map = new HashMap<>();

                    quantity_month.setText(String.valueOf( --month_count2));


                    map.put("quantity",String.valueOf(month_count2));
                    map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())*month_count2));


                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {


                                    progressDialog.dismiss();


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {

                                    Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();


                                }
                            });
                }


            }
        });


        add_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();


                quantity_month.setText(String.valueOf( ++month_count2));

                Map<String, Object> map = new HashMap<>();


                map.put("quantity",String.valueOf( month_count2));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())* month_count2));


                firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                        progressDialog.dismiss();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();

                    }
                });




            }
        });




        minnu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                if(count<=0){

                    quantity.setText(String.valueOf( cart_count2));

                    count=1;

                    add_cart_quantity_bt.setVisibility(View.GONE);
                    add_cart_bt.setVisibility(View.VISIBLE);

                }if(count==1){
                    add_cart_quantity_bt.setVisibility(View.GONE);
                    add_cart_bt.setVisibility(View.VISIBLE) ;

                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {


                                    progressDialog.dismiss();

                                }
                            });



                }else {

                    Map<String, Object> map = new HashMap<>();

                    quantity.setText(String.valueOf( --cart_count2));


                    map.put("quantity",String.valueOf( cart_count2));
                    map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())* cart_count2));


                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {


                                    progressDialog.dismiss();


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {

                                    Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();


                                }
                            });
                }


            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                quantity.setText(String.valueOf( ++cart_count2));

                Map<String, Object> map = new HashMap<>();


                map.put("quantity",String.valueOf( cart_count2));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())* cart_count2));


                firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                        progressDialog.dismiss();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();

                    }
                });




            }
        });

        add_cart_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                add_cart_quantity_bt.setVisibility(View.VISIBLE);
                add_cart_bt.setVisibility(View.GONE);

                Map<String, Object> map = new HashMap<>();
                map.put("name_product", productList.get(posion).getName_product());
                map.put("market_price", productList.get(posion).getVar_2_mrp());
                map.put("salling_price", productList.get(posion).getVar_2_salling_price());
                map.put("product_image_url", productList.get(posion).getProduct_image_url());
                map.put("item_id", item_id_st);
                map.put("product_id",productList.get(posion).getItemId());
                map.put("quantity",String.valueOf(cart_count2));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())*cart_count2));
                map.put("order_status", "cart");


                firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {



                        progressDialog.dismiss();



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });







    }

    private void set_vartion_details_3(Context context, View view, int posion) {

        TextView salling_price=view.findViewById(R.id.salling_price3);
        TextView mrp=view.findViewById(R.id.stock3);
        TextView quentity=view.findViewById(R.id.weight3);
        TextView saving=view.findViewById(R.id.saving3);

        CardView add_month_list_bt =view.findViewById(R.id.remove_bt3);
        CardView add_cart_bt =view.findViewById(R.id.add_cart_bt3);

        LinearLayout add_month_quantity_bt=view.findViewById(R.id.add_month_quantity_bt3);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        TextView minnu_month=view.findViewById(R.id.minnu_month3);
        TextView quantity_month=view.findViewById(R.id.quantity_month3);
        TextView add_month=view.findViewById(R.id.add_month3);

        LinearLayout add_cart_quantity_bt=view.findViewById(R.id.add_cart_quantity_bt3);
        TextView minnu=view.findViewById(R.id.minnu3);
        TextView quantity=view.findViewById(R.id.quantity3);
        TextView add=view.findViewById(R.id.add3);



        salling_price.setText("Selling Price :- ₹"+productList.get(posion).getVar_2_salling_price());
        mrp.setText("₹"+productList.get(posion).getVar_2_mrp());
        quentity.setText(productList.get(posion).getVar_2_qt());
        saving.setText("₹"+String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_mrp())-
                Integer.parseInt(productList.get(posion).getVar_2_salling_price())));
        String item_id_st=getRandomString(5);



        add_month_list_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_month_quantity_bt.setVisibility(View.VISIBLE);
                add_month_list_bt.setVisibility(View.GONE);
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                Map<String, Object> map = new HashMap<>();
                map.put("name_product", productList.get(posion).getName_product());
                map.put("market_price", productList.get(posion).getVar_2_mrp());
                map.put("salling_price", productList.get(posion).getVar_2_salling_price());
                map.put("product_image_url", productList.get(posion).getProduct_image_url());
                map.put("item_id", item_id_st);
                map.put("product_id",productList.get(posion).getItemId());
                map.put("quantity",String.valueOf( month_count3));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())* month_count3));
                map.put("order_status", "month");
                map.put("list_type","month");


                firebaseFirestore.collection("kirna_Add_cart")
                        .document(firebaseAuth.getUid().toString()).collection("cart")
                        .document(item_id_st).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {



                                progressDialog.dismiss();



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(Exception e) {

                                Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                            }
                        });


            }
        });

        minnu_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                if(month_count3<=0){

                    quantity_month.setText(String.valueOf(month_count3));

                    month_count3=1;

                    add_month_quantity_bt.setVisibility(View.GONE);
                    add_month_list_bt.setVisibility(View.VISIBLE);

                }if(month_count3==1){
                    add_month_quantity_bt.setVisibility(View.GONE);
                    add_month_list_bt.setVisibility(View.VISIBLE);

                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {


                                    progressDialog.dismiss();

                                }
                            });



                }else {

                    Map<String, Object> map = new HashMap<>();

                    quantity_month.setText(String.valueOf( --month_count3));


                    map.put("quantity",String.valueOf(month_count3));
                    map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())*month_count3));


                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {


                                    progressDialog.dismiss();


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {

                                    Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();


                                }
                            });
                }


            }
        });


        add_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();


                quantity_month.setText(String.valueOf( ++month_count3));

                Map<String, Object> map = new HashMap<>();


                map.put("quantity",String.valueOf( month_count3));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())* month_count3));


                firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                        progressDialog.dismiss();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();

                    }
                });




            }
        });




        minnu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                if(count<=0){

                    quantity.setText(String.valueOf( cart_count3));

                    count=1;

                    add_cart_quantity_bt.setVisibility(View.GONE);
                    add_cart_bt.setVisibility(View.VISIBLE);

                }if(count==1){
                    add_cart_quantity_bt.setVisibility(View.GONE);
                    add_cart_bt.setVisibility(View.VISIBLE) ;

                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {


                                    progressDialog.dismiss();

                                }
                            });



                }else {

                    Map<String, Object> map = new HashMap<>();

                    quantity.setText(String.valueOf( --cart_count3));


                    map.put("quantity",String.valueOf( cart_count3));
                    map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())* cart_count3));


                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {


                                    progressDialog.dismiss();


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {

                                    Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();


                                }
                            });
                }


            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                quantity.setText(String.valueOf( ++cart_count3));

                Map<String, Object> map = new HashMap<>();


                map.put("quantity",String.valueOf( cart_count3));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())* cart_count3));


                firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                        progressDialog.dismiss();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();

                    }
                });




            }
        });

        add_cart_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                add_cart_quantity_bt.setVisibility(View.VISIBLE);
                add_cart_bt.setVisibility(View.GONE);

                Map<String, Object> map = new HashMap<>();
                map.put("name_product", productList.get(posion).getName_product());
                map.put("market_price", productList.get(posion).getVar_2_mrp());
                map.put("salling_price", productList.get(posion).getVar_2_salling_price());
                map.put("product_image_url", productList.get(posion).getProduct_image_url());
                map.put("item_id", item_id_st);
                map.put("product_id",productList.get(posion).getItemId());
                map.put("quantity",String.valueOf(cart_count3));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())*cart_count3));
                map.put("order_status", "cart");


                firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {



                        progressDialog.dismiss();



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });







    }

    private void set_vartion_details_4(Context context, View view, int posion) {

        TextView salling_price=view.findViewById(R.id.salling_price4);
        TextView mrp=view.findViewById(R.id.stock4);
        TextView quentity=view.findViewById(R.id.weight4);
        TextView saving=view.findViewById(R.id.saving4);

        CardView add_month_list_bt =view.findViewById(R.id.remove_bt4);
        CardView add_cart_bt =view.findViewById(R.id.add_cart_bt4);

        LinearLayout add_month_quantity_bt=view.findViewById(R.id.add_month_quantity_bt4);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        TextView minnu_month=view.findViewById(R.id.minnu_month4);
        TextView quantity_month=view.findViewById(R.id.quantity_month4);
        TextView add_month=view.findViewById(R.id.add_month4);

        LinearLayout add_cart_quantity_bt=view.findViewById(R.id.add_cart_quantity_bt4);
        TextView minnu=view.findViewById(R.id.minnu4);
        TextView quantity=view.findViewById(R.id.quantity4);
        TextView add=view.findViewById(R.id.add4);



        salling_price.setText("Selling Price :- ₹"+productList.get(posion).getVar_2_salling_price());
        mrp.setText("₹"+productList.get(posion).getVar_2_mrp());
        quentity.setText(productList.get(posion).getVar_2_qt());
        saving.setText("₹"+String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_mrp())-
                Integer.parseInt(productList.get(posion).getVar_2_salling_price())));
        String item_id_st=getRandomString(5);



        add_month_list_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_month_quantity_bt.setVisibility(View.VISIBLE);
                add_month_list_bt.setVisibility(View.GONE);
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                Map<String, Object> map = new HashMap<>();
                map.put("name_product", productList.get(posion).getName_product());
                map.put("market_price", productList.get(posion).getVar_2_mrp());
                map.put("salling_price", productList.get(posion).getVar_2_salling_price());
                map.put("product_image_url", productList.get(posion).getProduct_image_url());
                map.put("item_id", item_id_st);
                map.put("product_id",productList.get(posion).getItemId());
                map.put("quantity",String.valueOf( month_count4));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())* month_count4));
                map.put("order_status", "month");
                map.put("list_type","month");


                firebaseFirestore.collection("kirna_Add_cart")
                        .document(firebaseAuth.getUid().toString()).collection("cart")
                        .document(item_id_st).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {



                                progressDialog.dismiss();



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(Exception e) {

                                Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                            }
                        });


            }
        });

        minnu_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                if(month_count4<=0){

                    quantity_month.setText(String.valueOf(month_count4));

                    month_count4=1;

                    add_month_quantity_bt.setVisibility(View.GONE);
                    add_month_list_bt.setVisibility(View.VISIBLE);

                }if(month_count4==1){
                    add_month_quantity_bt.setVisibility(View.GONE);
                    add_month_list_bt.setVisibility(View.VISIBLE);

                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {


                                    progressDialog.dismiss();

                                }
                            });



                }else {

                    Map<String, Object> map = new HashMap<>();

                    quantity_month.setText(String.valueOf( --month_count4));


                    map.put("quantity",String.valueOf(month_count4));
                    map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())*month_count4));


                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {


                                    progressDialog.dismiss();


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {

                                    Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();


                                }
                            });
                }


            }
        });


        add_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();


                quantity_month.setText(String.valueOf( ++month_count4));

                Map<String, Object> map = new HashMap<>();


                map.put("quantity",String.valueOf( month_count4));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())* month_count4));


                firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                        progressDialog.dismiss();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();

                    }
                });




            }
        });




        minnu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                if(cart_count4<=0){

                    quantity.setText(String.valueOf( cart_count4));

                    cart_count4=1;

                    add_cart_quantity_bt.setVisibility(View.GONE);
                    add_cart_bt.setVisibility(View.VISIBLE);

                }if(cart_count4==1){
                    add_cart_quantity_bt.setVisibility(View.GONE);
                    add_cart_bt.setVisibility(View.VISIBLE) ;

                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {


                                    progressDialog.dismiss();

                                }
                            });



                }else {

                    Map<String, Object> map = new HashMap<>();

                    quantity.setText(String.valueOf( --cart_count4));


                    map.put("quantity",String.valueOf( cart_count4));
                    map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())* cart_count4));


                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {


                                    progressDialog.dismiss();


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {

                                    Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();


                                }
                            });
                }


            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                quantity.setText(String.valueOf( ++cart_count4));

                Map<String, Object> map = new HashMap<>();


                map.put("quantity",String.valueOf( cart_count4));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())* cart_count4));


                firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                        progressDialog.dismiss();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();

                    }
                });




            }
        });

        add_cart_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                add_cart_quantity_bt.setVisibility(View.VISIBLE);
                add_cart_bt.setVisibility(View.GONE);

                Map<String, Object> map = new HashMap<>();
                map.put("name_product", productList.get(posion).getName_product());
                map.put("market_price", productList.get(posion).getVar_2_mrp());
                map.put("salling_price", productList.get(posion).getVar_2_salling_price());
                map.put("product_image_url", productList.get(posion).getProduct_image_url());
                map.put("item_id", item_id_st);
                map.put("product_id",productList.get(posion).getItemId());
                map.put("quantity",String.valueOf(cart_count4));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())*cart_count4));
                map.put("order_status", "cart");


                firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {



                        progressDialog.dismiss();



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });







    }

    private void set_vartion_details_5(Context context, View view, int posion) {

        TextView salling_price=view.findViewById(R.id.salling_price5);
        TextView mrp=view.findViewById(R.id.stock5);
        TextView quentity=view.findViewById(R.id.weight5);
        TextView saving=view.findViewById(R.id.saving5);

        CardView add_month_list_bt =view.findViewById(R.id.remove_bt5);
        CardView add_cart_bt =view.findViewById(R.id.add_cart_bt5);

        LinearLayout add_month_quantity_bt=view.findViewById(R.id.add_month_quantity_bt5);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        TextView minnu_month=view.findViewById(R.id.minnu_month5);
        TextView quantity_month=view.findViewById(R.id.quantity_month5);
        TextView add_month=view.findViewById(R.id.add_month5);

        LinearLayout add_cart_quantity_bt=view.findViewById(R.id.add_cart_quantity_bt5);
        TextView minnu=view.findViewById(R.id.minnu5);
        TextView quantity=view.findViewById(R.id.quantity5);
        TextView add=view.findViewById(R.id.add5);



        salling_price.setText("Selling Price :- ₹"+productList.get(posion).getVar_2_salling_price());
        mrp.setText("₹"+productList.get(posion).getVar_2_mrp());
        quentity.setText(productList.get(posion).getVar_2_qt());
        saving.setText("₹"+String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_mrp())-
                Integer.parseInt(productList.get(posion).getVar_2_salling_price())));
        String item_id_st=getRandomString(5);



        add_month_list_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_month_quantity_bt.setVisibility(View.VISIBLE);
                add_month_list_bt.setVisibility(View.GONE);
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                Map<String, Object> map = new HashMap<>();
                map.put("name_product", productList.get(posion).getName_product());
                map.put("market_price", productList.get(posion).getVar_2_mrp());
                map.put("salling_price", productList.get(posion).getVar_2_salling_price());
                map.put("product_image_url", productList.get(posion).getProduct_image_url());
                map.put("item_id", item_id_st);
                map.put("product_id",productList.get(posion).getItemId());
                map.put("quantity",String.valueOf( month_count5));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())* month_count5));
                map.put("order_status", "month");
                map.put("list_type","month");


                firebaseFirestore.collection("kirna_Add_cart")
                        .document(firebaseAuth.getUid().toString()).collection("cart")
                        .document(item_id_st).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {



                                progressDialog.dismiss();



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(Exception e) {

                                Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                            }
                        });


            }
        });

        minnu_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                if(month_count5<=0){

                    quantity_month.setText(String.valueOf(month_count5));

                    month_count5=1;

                    add_month_quantity_bt.setVisibility(View.GONE);
                    add_month_list_bt.setVisibility(View.VISIBLE);

                }if(month_count5==1){
                    add_month_quantity_bt.setVisibility(View.GONE);
                    add_month_list_bt.setVisibility(View.VISIBLE);

                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {


                                    progressDialog.dismiss();

                                }
                            });



                }else {

                    Map<String, Object> map = new HashMap<>();

                    quantity_month.setText(String.valueOf( --month_count5));


                    map.put("quantity",String.valueOf(month_count5));
                    map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())*month_count5));


                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {


                                    progressDialog.dismiss();


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {

                                    Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();


                                }
                            });
                }


            }
        });


        add_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();


                quantity_month.setText(String.valueOf( ++month_count5));

                Map<String, Object> map = new HashMap<>();


                map.put("quantity",String.valueOf( month_count5));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())* month_count5));


                firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                        progressDialog.dismiss();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();

                    }
                });




            }
        });




        minnu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                if(cart_count5<=0){

                    quantity.setText(String.valueOf( cart_count5));

                    cart_count5=1;

                    add_cart_quantity_bt.setVisibility(View.GONE);
                    add_cart_bt.setVisibility(View.VISIBLE);

                }if(cart_count5==1){
                    add_cart_quantity_bt.setVisibility(View.GONE);
                    add_cart_bt.setVisibility(View.VISIBLE) ;

                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {


                                    progressDialog.dismiss();

                                }
                            });



                }else {

                    Map<String, Object> map = new HashMap<>();

                    quantity.setText(String.valueOf( --cart_count5));


                    map.put("quantity",String.valueOf( cart_count5));
                    map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())* cart_count5));


                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {


                                    progressDialog.dismiss();


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {

                                    Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();


                                }
                            });
                }


            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                quantity.setText(String.valueOf( ++cart_count5));

                Map<String, Object> map = new HashMap<>();


                map.put("quantity",String.valueOf( cart_count5));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())* cart_count5));


                firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                        progressDialog.dismiss();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();

                    }
                });




            }
        });

        add_cart_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                add_cart_quantity_bt.setVisibility(View.VISIBLE);
                add_cart_bt.setVisibility(View.GONE);

                Map<String, Object> map = new HashMap<>();
                map.put("name_product", productList.get(posion).getName_product());
                map.put("market_price", productList.get(posion).getVar_2_mrp());
                map.put("salling_price", productList.get(posion).getVar_2_salling_price());
                map.put("product_image_url", productList.get(posion).getProduct_image_url());
                map.put("item_id", item_id_st);
                map.put("product_id",productList.get(posion).getItemId());
                map.put("quantity",String.valueOf(cart_count5));
                map.put("total_amount",String.valueOf(Integer.parseInt(productList.get(posion).getVar_2_salling_price())*cart_count5));
                map.put("order_status", "cart");


                firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {



                        progressDialog.dismiss();



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });







    }



    @Override
    public void total_saving(int amt) {

    }

    @Override
    public void restart_act() {

    }


    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";

    public static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }
//

}


