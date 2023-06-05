package com.mradking.mradkingshop.kirana_store.shopkeer.fragments.Shop_Stock_Product.Personal_Care;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.shopkeer.adapter.Added_product_adapter;
import com.mradking.mradkingshop.kirana_store.shopkeer.inface.brand_interface;
import com.mradking.mradkingshop.kirana_store.shopkeer.inface.product_list_interface;
import com.mradking.mradkingshop.kirana_store.shopkeer.modal.AddedProductModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shampoo extends Fragment implements brand_interface, product_list_interface {
    SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView cart_recycler_view;
    private List<AddedProductModel> productList;
    private Activity activity = getActivity();
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public Added_product_adapter latestGovtJobRecyclerAdapter;
    public DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;
    String key ;
    Glide glide;
    private BottomSheetBehavior mBehavior;
    private BottomSheetDialog mBottomSheetDialog;
    private View bottom_sheet;
    String varition_status_st ="123";
    /////////bottom_sheet//////////
    CardView ok_bt;
    ImageView product_image_btm;
    TextView product_name_btm,mrp_btm;
    EditText product_name_et_btm,mrp_et_btm,salling_et_btm,stock_et_btm,quantity_et_btm;

    TextView number_item_added;
    CardView see_more_bt;
    ProgressDialog progressDialog;

    LinearLayout varition_layout,vartion_detail_1_lay,vartion_detail_2_lay,vartion_detail_3_lay,vartion_detail_4_lay;
    CheckBox variton_yes_check_box,variton_no_check_box;
    Spinner varition_sppiner;
    EditText var_1_selling_et,var_2_selling_et,var_3_selling_et,var_4_selling_et,
            var_1_qt_et, var_2_qt_et, var_3_qt_et, var_4_qt_et,
            var_1_mrp_et, var_2_mrp_et, var_3_mrp_et, var_4_mrp_et
            ,var_1_stock_et,var_2_stock_et,var_3_stock_et,var_4_stock_et;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View root = inflater.inflate(R.layout.kirana_product_added_list, container, false);

        productList = new ArrayList<AddedProductModel>();

        cart_recycler_view =root.findViewById(R.id.cart_list);
        firebaseAuth = FirebaseAuth.getInstance();
        latestGovtJobRecyclerAdapter = new Added_product_adapter( activity,productList,this);
        LinearLayoutManager lm1 = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        lm1.setReverseLayout(true);
        lm1.setStackFromEnd(true);

        cart_recycler_view.setHasFixedSize(true);
        cart_recycler_view.setLayoutManager(lm1);
        cart_recycler_view.setAdapter(latestGovtJobRecyclerAdapter);
        firebaseFirestore=FirebaseFirestore.getInstance();
        Toolbar toolbar=root.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        bottom_sheet = root.findViewById(R.id.bottom_sheet);

        swipeRefreshLayout=root.findViewById(R.id.refreshLayout);
        mBehavior = BottomSheetBehavior.from(bottom_sheet);


        progressDialog=new ProgressDialog(getActivity());


        shop_list("shampoo");
        key="shampoo";


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                productList.clear();

                shop_list(key);

                latestGovtJobRecyclerAdapter.notifyDataSetChanged();

                swipeRefreshLayout.setRefreshing(false);


            }
        });




        return root;
    }


    private void shop_list(String category) {

        if (firebaseAuth.getCurrentUser() != null) {

            firebaseFirestore = FirebaseFirestore.getInstance();

            cart_recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    Boolean reachedBottom = !recyclerView.canScrollVertically(1);

                    if (reachedBottom) {


                        String desc = lastVisible.getString("name");

                        loadMorePost(category);

                    }

                }
            });

            final String uid = firebaseAuth.getCurrentUser().getUid();

            Query firstQuery =  firebaseFirestore.collection("kirana_shopkeeper_product")
                    .document(firebaseAuth.getCurrentUser().getUid())
                    .collection("proudcts")
                    .whereEqualTo("sub_category",category)
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

                            AddedProductModel blogPost = doc.getDocument().toObject(AddedProductModel.class).withId(doc.getDocument().getId());
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

    private void loadMorePost( String category) {

        String uid = firebaseAuth.getCurrentUser().getUid();

        Query nextQuery =  firebaseFirestore.collection("kirana_shopkeeper_product")
                .document(firebaseAuth.getCurrentUser().getUid())
                .collection("proudcts")
                .whereEqualTo("sub_category",category)
                .startAfter(lastVisible).limit(10);




        nextQuery.addSnapshotListener( new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (!documentSnapshots.isEmpty()) {

                    lastVisible = documentSnapshots.getDocuments().get(documentSnapshots.size() - 1);
                    for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                        if (doc.getType() == DocumentChange.Type.ADDED) {

                            AddedProductModel govt_job = doc.getDocument().toObject(AddedProductModel.class);
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

    @Override
    public void click_brand(int posion) {


    }

    @Override
    public void aad_button_click(int posion) {
        showBottomSheetDialog(posion);
    }


    public void showBottomSheetDialog(int posion) {



        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        final View view = getLayoutInflater().inflate(R.layout.kirana_bottom_sheat_for_add_product_shopkeeper, null);

        ok_bt=view.findViewById(R.id.ok_bt);
        product_image_btm=view.findViewById(R.id.product_image_btm);
        mrp_btm=view.findViewById(R.id.mrp_btm);
        product_name_btm=view.findViewById(R.id.product_name);

        product_name_et_btm=view.findViewById(R.id.product_name_et_btm);
        mrp_et_btm=view.findViewById(R.id.mrp_et_btm);
        salling_et_btm=view.findViewById(R.id.salling_et_btm);
        stock_et_btm=view.findViewById(R.id.stock_et_btm);
        quantity_et_btm=view.findViewById(R.id.quantity);



        varition_layout=view.findViewById(R.id.variation_layout);
        variton_yes_check_box=view.findViewById(R.id.varition_yes);
        variton_no_check_box=view.findViewById(R.id.varition_no);
        vartion_detail_1_lay=view.findViewById(R.id.vartion_detail_1_lay);
        vartion_detail_2_lay=view.findViewById(R.id.vartion_detail_2_lay);
        vartion_detail_3_lay=view.findViewById(R.id.vartion_detail_3_lay);
        vartion_detail_4_lay=view.findViewById(R.id.vartion_detail_4_lay);

        var_1_mrp_et=view.findViewById(R.id.var_1_mrp_et);
        var_2_mrp_et=view.findViewById(R.id.var_2_mrp_et);
        var_3_mrp_et=view.findViewById(R.id.var_3_mrp_et);
        var_4_mrp_et=view.findViewById(R.id.var_4_mrp_et);

        var_1_qt_et=view.findViewById(R.id.var_1_qt_et);
        var_2_qt_et=view.findViewById(R.id.var_2_qt_et);
        var_3_qt_et=view.findViewById(R.id.var_3_qt_et);
        var_4_qt_et=view.findViewById(R.id.var_4_qt_et);

        var_1_selling_et=view.findViewById(R.id.var_1_selling_et);
        var_2_selling_et=view.findViewById(R.id.var_2_selling_et);
        var_3_selling_et=view.findViewById(R.id.var_3_selling_et);
        var_4_selling_et=view.findViewById(R.id.var_4_selling_et);
        varition_sppiner=view.findViewById(R.id.varition_sppiner);

        var_1_stock_et=view.findViewById(R.id.var_1_stock_et);
        var_2_stock_et=view.findViewById(R.id.var_2_stock_et);
        var_3_stock_et=view.findViewById(R.id.var_3_stock_et);
        var_4_stock_et=view.findViewById(R.id.var_4_stock_et);


        varition_sppiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Object item = adapterView.getItemAtPosition(i);

                if(item.toString().contentEquals("1")){
                    vartion_detail_1_lay.setVisibility(View.VISIBLE);
                    vartion_detail_2_lay.setVisibility(View.GONE);
                    vartion_detail_3_lay.setVisibility(View.GONE);
                    vartion_detail_4_lay.setVisibility(View.GONE);


                }else if(item.toString().contentEquals("2")){
                    vartion_detail_1_lay.setVisibility(View.VISIBLE);
                    vartion_detail_2_lay.setVisibility(View.VISIBLE);
                    vartion_detail_3_lay.setVisibility(View.GONE);
                    vartion_detail_4_lay.setVisibility(View.GONE);



                }
                else if(item.toString().contentEquals("3")){

                    vartion_detail_1_lay.setVisibility(View.VISIBLE);
                    vartion_detail_2_lay.setVisibility(View.VISIBLE);
                    vartion_detail_3_lay.setVisibility(View.VISIBLE);
                    vartion_detail_4_lay.setVisibility(View.GONE);

                }
                else if(item.toString().contentEquals("4")){

                    vartion_detail_1_lay.setVisibility(View.VISIBLE);
                    vartion_detail_2_lay.setVisibility(View.VISIBLE);
                    vartion_detail_3_lay.setVisibility(View.VISIBLE);
                    vartion_detail_4_lay.setVisibility(View.VISIBLE);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        variton_yes_check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                if(compoundButton.isChecked()){

                    variton_no_check_box.setChecked(false);
                    varition_layout.setVisibility(View.VISIBLE);

                    varition_status_st="yes";


                }

            }
        });

        variton_no_check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(compoundButton.isChecked()){

                    variton_yes_check_box.setChecked(false);
                    varition_layout.setVisibility(View.GONE);
                    var_1_mrp_et.setText("");
                    var_1_qt_et.setText("");
                    var_1_selling_et.setText("");
                    varition_status_st="no";


                }

            }
        });




        mBottomSheetDialog = new BottomSheetDialog(getActivity());
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

        product_name_btm.setText(productList.get(posion).getName_product());
        mrp_btm.setText("MRP:- "+productList.get(posion).getMarket_price());
        product_name_et_btm.setText(productList.get(posion).getName_product());
        mrp_et_btm.setText(productList.get(posion).getMarket_price());
        quantity_et_btm.setText(productList.get(posion).getQuantity());


        glide.with(getActivity()).load(productList.get(posion).getProduct_image_url()).into(product_image_btm);



        salling_et_btm.setText(productList.get(posion).getSalling_price());
        stock_et_btm.setText(productList.get(posion).getStock());



        if(productList.get(posion).getVarition_status().contentEquals("yes")){


            variton_yes_check_box.setChecked(true);
            varition_sppiner.setSelection(Integer.parseInt(productList.get(posion).getNumber_of_varition()));

            varition_layout.setVisibility(View.VISIBLE);
            varition_status_st="yes";



            if(productList.get(posion).getNumber_of_varition().contentEquals("1")){

                var_1_qt_et.setText(productList.get(posion).getVar_1_qt());
                var_1_mrp_et.setText(productList.get(posion).getVar_1_mrp());
                var_1_selling_et.setText(productList.get(posion).getVar_1_salling_price());
                var_1_stock_et.setText(productList.get(posion).getVar_1_stock());

            }else if(productList.get(posion).getNumber_of_varition().contentEquals("2")){

                var_1_qt_et.setText(productList.get(posion).getVar_1_qt());
                var_1_mrp_et.setText(productList.get(posion).getVar_1_mrp());
                var_1_selling_et.setText(productList.get(posion).getVar_1_salling_price());
                var_1_stock_et.setText(productList.get(posion).getVar_1_stock());

                var_2_qt_et.setText(productList.get(posion).getVar_2_qt());
                var_2_mrp_et.setText(productList.get(posion).getVar_2_mrp());
                var_2_selling_et.setText(productList.get(posion).getVar_2_salling_price());
                var_2_stock_et.setText(productList.get(posion).getVar_2_stock());

            }else if(productList.get(posion).getNumber_of_varition().contentEquals("3")){
                var_1_qt_et.setText(productList.get(posion).getVar_1_qt());
                var_1_mrp_et.setText(productList.get(posion).getVar_1_mrp());
                var_1_selling_et.setText(productList.get(posion).getVar_1_salling_price());
                var_1_stock_et.setText(productList.get(posion).getVar_1_stock());

                var_2_qt_et.setText(productList.get(posion).getVar_2_qt());
                var_2_mrp_et.setText(productList.get(posion).getVar_2_mrp());
                var_2_selling_et.setText(productList.get(posion).getVar_2_salling_price());
                var_2_stock_et.setText(productList.get(posion).getVar_2_stock());

                var_3_qt_et.setText(productList.get(posion).getVar_3_qt());
                var_3_mrp_et.setText(productList.get(posion).getVar_3_mrp());
                var_3_selling_et.setText(productList.get(posion).getVar_3_salling_price());
                var_3_stock_et.setText(productList.get(posion).getVar_3_stock());


            }else if(productList.get(posion).getNumber_of_varition().contentEquals("4")){

                var_1_qt_et.setText(productList.get(posion).getVar_1_qt());
                var_1_mrp_et.setText(productList.get(posion).getVar_1_mrp());
                var_1_selling_et.setText(productList.get(posion).getVar_1_salling_price());
                var_1_stock_et.setText(productList.get(posion).getVar_1_stock());

                var_2_qt_et.setText(productList.get(posion).getVar_2_qt());
                var_2_mrp_et.setText(productList.get(posion).getVar_2_mrp());
                var_2_selling_et.setText(productList.get(posion).getVar_2_salling_price());
                var_2_stock_et.setText(productList.get(posion).getVar_2_stock());

                var_3_qt_et.setText(productList.get(posion).getVar_3_qt());
                var_3_mrp_et.setText(productList.get(posion).getVar_3_mrp());
                var_3_selling_et.setText(productList.get(posion).getVar_3_salling_price());
                var_3_stock_et.setText(productList.get(posion).getVar_3_stock());

                var_4_qt_et.setText(productList.get(posion).getVar_4_qt());
                var_4_mrp_et.setText(productList.get(posion).getVar_4_mrp());
                var_4_selling_et.setText(productList.get(posion).getVar_4_salling_price());
                var_4_stock_et.setText(productList.get(posion).getVar_4_stock());


            }else if(productList.get(posion).getVarition_status().contentEquals("no")) {
                variton_no_check_box.setChecked(true);
                varition_status_st="no";



            }


        }





        ok_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(salling_et_btm.getText().toString())) {

                    Toast.makeText(getActivity(), "Please Enter Selling Price", Toast.LENGTH_SHORT).show();

                }else if(TextUtils.isEmpty(stock_et_btm.getText().toString())){
                    Toast.makeText(getActivity(), "Please Enter Selling Price", Toast.LENGTH_SHORT).show();


                }else if(TextUtils.isEmpty(quantity_et_btm.getText().toString())){

                    Toast.makeText(getActivity(), "Please Enter Weight", Toast.LENGTH_SHORT).show();

                }else if(TextUtils.isEmpty(mrp_et_btm.getText().toString())){
                    Toast.makeText(getActivity(), "Please Enter MRP ", Toast.LENGTH_SHORT).show();


                }else if(TextUtils.isEmpty(salling_et_btm.getText().toString())) {

                    Toast.makeText(getActivity(), "Please Enter Selling Price", Toast.LENGTH_SHORT).show();

                }else if(TextUtils.isEmpty(stock_et_btm.getText().toString())){
                    Toast.makeText(getActivity(), "Please Enter Selling Price", Toast.LENGTH_SHORT).show();


                }else if(TextUtils.isEmpty(quantity_et_btm.getText().toString())){

                    Toast.makeText(getActivity(), "Please Enter Weight", Toast.LENGTH_SHORT).show();

                }else if(TextUtils.isEmpty(mrp_et_btm.getText().toString())){
                    Toast.makeText(getActivity(), "Please Enter MRP ", Toast.LENGTH_SHORT).show();


                }else if(varition_status_st.contentEquals("123")){

                    Toast.makeText(getActivity(), "Please Select Variation Checkbox", Toast.LENGTH_SHORT).show();

                }else {
                    set_updata(posion);

                }






            }
        });


    }

    private void set_updata(int posion) {


        progressDialog.setMessage("Please Wait.........");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();



        if(varition_status_st.contentEquals("yes")){

            varition_yes_data_set_up(posion,varition_sppiner.getSelectedItem().toString()
                    ,stock_et_btm.getText().toString()
                    ,salling_et_btm.getText().toString()
                    ,var_1_qt_et.getText().toString()
                    ,var_1_mrp_et.getText().toString()
                    ,var_1_selling_et.getText().toString()
                    ,var_2_qt_et.getText().toString()
                    ,var_2_mrp_et.getText().toString()
                    ,var_2_selling_et.getText().toString()
                    ,var_3_qt_et.getText().toString()
                    ,var_3_mrp_et.getText().toString()
                    ,var_3_selling_et.getText().toString()
                    ,var_4_qt_et.getText().toString()
                    ,var_4_mrp_et.getText().toString()
                    ,var_4_selling_et.getText().toString()
                    ,var_1_stock_et.getText().toString()
                    ,var_2_stock_et.getText().toString()
                    ,var_3_stock_et.getText().toString()
                    ,var_4_stock_et.getText().toString()
            );

        }else if(varition_status_st.contentEquals("no")){
            Map<String ,Object> map=new HashMap<>();

            map.put("name_product", productList.get(posion).getName_product());
            map.put("market_price", productList.get(posion).getMarket_price());
            map.put("main_category",productList.get(posion).getMain_category());
            map.put("sub_category", productList.get(posion).getSub_category());
            map.put("product_image_url", productList.get(posion).getProduct_image_url());
            map.put("quantity",productList.get(posion).getQuantity());
            map.put("brand",productList.get(posion).getBrand());

            map.put("stock",stock_et_btm.getText().toString());
            map.put("salling_price",salling_et_btm.getText().toString());
            map.put("stock_status","instock");
            map.put("varition_status","no");
            map.put("timestamp", FieldValue.serverTimestamp());
            map.put("store_uid",firebaseAuth.getCurrentUser().getUid());
            map.put("product_for_home_page","no");

            firebaseFirestore.collection("kirana_shopkeeper_product").document(firebaseAuth.getCurrentUser().getUid().toString())
                    .collection("proudcts").document(productList.get(posion).getItemId()).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    productList.clear();
                    shop_list(key);
                    latestGovtJobRecyclerAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "Product Updated Into Your Stock", Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss();
                    mBottomSheetDialog.dismiss();




                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "Productv Not Added Into Your Stock", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    mBottomSheetDialog.dismiss();


                }
            });

        }









    }

    private void varition_yes_data_set_up(int posion,String sppiner_st,
                                          String stock,
                                          String selling_price,
                                          String var_qt_1_st,
                                          String var_mrp_1_st,
                                          String var_selling_1_st,
                                          String var_qt_2_st,
                                          String var_mrp_2_st,
                                          String var_selling_2_st,
                                          String var_qt_3_st,
                                          String var_mrp_3_st,
                                          String var_selling_3_st,
                                          String var_qt_4_st,
                                          String var_mrp_4_st,
                                          String var_selling_4_st,
                                          String var_stock_1_st,
                                          String var_stock_2_st,
                                          String var_stock_3_st,
                                          String var_stock_4_st
    ) {


        if(sppiner_st.contentEquals("1")){

            Map<String ,Object> map=new HashMap<>();

            map.put("name_product", productList.get(posion).getName_product());
            map.put("market_price", productList.get(posion).getMarket_price());
            map.put("main_category",productList.get(posion).getMain_category());
            map.put("sub_category", productList.get(posion).getSub_category());
            map.put("product_image_url", productList.get(posion).getProduct_image_url());
            map.put("quantity",productList.get(posion).getQuantity());
            map.put("brand",productList.get(posion).getBrand());
            map.put("varition_status","yes");
            map.put("number_of_varition",varition_sppiner.getSelectedItem().toString());
            map.put("var_1_qt",var_qt_1_st);
            map.put("var_1_mrp",var_mrp_1_st);
            map.put("var_1_salling_price",var_selling_1_st);
            map.put("var_1_stock",var_stock_1_st);

            map.put("stock",stock);
            map.put("salling_price",selling_price);
            map.put("stock_status","instock");
            map.put("timestamp", FieldValue.serverTimestamp());
            map.put("store_uid",firebaseAuth.getCurrentUser().getUid());
            map.put("product_for_home_page","no");

            firebaseFirestore.collection("kirana_shopkeeper_product").document(firebaseAuth.getCurrentUser().getUid().toString())
                    .collection("proudcts").document(productList.get(posion).getItemId()).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    productList.clear();
                    shop_list(key);
                    latestGovtJobRecyclerAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "Product Updated Into Your Stock", Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss();
                    mBottomSheetDialog.dismiss();



                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "Product Not update Into Your Stock", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    mBottomSheetDialog.dismiss();


                }
            });
        }else if(sppiner_st.contentEquals("2")){


            Map<String ,Object> map=new HashMap<>();

            map.put("name_product", productList.get(posion).getName_product());
            map.put("market_price", productList.get(posion).getMarket_price());
            map.put("main_category",productList.get(posion).getMain_category());
            map.put("sub_category", productList.get(posion).getSub_category());
            map.put("product_image_url", productList.get(posion).getProduct_image_url());
            map.put("quantity",productList.get(posion).getQuantity());
            map.put("brand",productList.get(posion).getBrand());
            map.put("varition_status","yes");
            map.put("number_of_varition",varition_sppiner.getSelectedItem().toString());
            map.put("var_1_qt",var_qt_1_st);
            map.put("var_1_mrp",var_mrp_1_st);
            map.put("var_1_salling_price",var_selling_1_st);
            map.put("var_1_stock",var_stock_1_st);

            map.put("var_2_qt",var_qt_2_st);
            map.put("var_2_mrp",var_mrp_2_st);
            map.put("var_2_salling_price",var_selling_2_st);
            map.put("var_2_stock",var_stock_2_st);

            map.put("stock",stock);
            map.put("salling_price",selling_price);
            map.put("stock_status","instock");
            map.put("timestamp", FieldValue.serverTimestamp());
            map.put("store_uid",firebaseAuth.getCurrentUser().getUid());
            map.put("product_for_home_page","no");

            firebaseFirestore.collection("kirana_shopkeeper_product").document(firebaseAuth.getCurrentUser().getUid().toString())
                    .collection("proudcts")
                    .document(productList.get(posion).getItemId())
                    .update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    Toast.makeText(getActivity(), "Product Updated Into Your Stock", Toast.LENGTH_SHORT).show();

                    productList.clear();

                    shop_list(key);

                    latestGovtJobRecyclerAdapter.notifyDataSetChanged();

                    progressDialog.dismiss();
                    mBottomSheetDialog.dismiss();


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "Product not Updated Into Your Stock", Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss();
                    mBottomSheetDialog.dismiss();



                }
            });


        }else if(sppiner_st.contentEquals("3")){

            Map<String ,Object> map=new HashMap<>();

            map.put("name_product", productList.get(posion).getName_product());
            map.put("market_price", productList.get(posion).getMarket_price());
            map.put("main_category",productList.get(posion).getMain_category());
            map.put("sub_category", productList.get(posion).getSub_category());
            map.put("product_image_url", productList.get(posion).getProduct_image_url());
            map.put("quantity",productList.get(posion).getQuantity());
            map.put("brand",productList.get(posion).getBrand());
            map.put("varition_status","yes");
            map.put("number_of_varition",varition_sppiner.getSelectedItem().toString());
            map.put("var_1_qt",var_qt_1_st);
            map.put("var_1_mrp",var_mrp_1_st);
            map.put("var_1_salling_price",var_selling_1_st);
            map.put("var_1_stock",var_stock_1_st);

            map.put("var_2_qt",var_qt_2_st);
            map.put("var_2_mrp",var_mrp_2_st);
            map.put("var_2_salling_price",var_selling_2_st);
            map.put("var_2_stock",var_stock_2_st);

            map.put("var_3_qt",var_qt_3_st);
            map.put("var_3_mrp",var_mrp_3_st);
            map.put("var_3_salling_price",var_selling_3_st);
            map.put("var_3_stock",var_stock_3_st);

            map.put("stock",stock);
            map.put("salling_price",selling_price);
            map.put("stock_status","instock");
            map.put("timestamp", FieldValue.serverTimestamp());
            map.put("store_uid",firebaseAuth.getCurrentUser().getUid());
            map.put("product_for_home_page","no");

            firebaseFirestore.collection("kirana_shopkeeper_product").document(firebaseAuth.getCurrentUser().getUid().toString())
                    .collection("proudcts").document(productList.get(posion).getItemId()).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    productList.clear();
                    shop_list(key);
                    latestGovtJobRecyclerAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "Product Updated Into Your Stock", Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss();
                    mBottomSheetDialog.dismiss();




                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "Productv Not Updated Into Your Stock", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    mBottomSheetDialog.dismiss();


                }
            });


        }else if(sppiner_st.contentEquals("4")){


            Map<String ,Object> map=new HashMap<>();

            map.put("name_product", productList.get(posion).getName_product());
            map.put("market_price", productList.get(posion).getMarket_price());
            map.put("main_category",productList.get(posion).getMain_category());
            map.put("sub_category", productList.get(posion).getSub_category());
            map.put("product_image_url", productList.get(posion).getProduct_image_url());
            map.put("quantity",productList.get(posion).getQuantity());
            map.put("brand",productList.get(posion).getBrand());
            map.put("varition_status","yes");
            map.put("number_of_varition",varition_sppiner.getSelectedItem().toString());
            map.put("var_1_qt",var_qt_1_st);
            map.put("var_1_mrp",var_mrp_1_st);
            map.put("var_1_salling_price",var_selling_1_st);
            map.put("var_1_stock",var_stock_1_st);

            map.put("var_2_qt",var_qt_2_st);
            map.put("var_2_mrp",var_mrp_2_st);
            map.put("var_2_salling_price",var_selling_2_st);
            map.put("var_2_stock",var_stock_2_st);

            map.put("var_3_qt",var_qt_3_st);
            map.put("var_3_mrp",var_mrp_3_st);
            map.put("var_3_salling_price",var_selling_3_st);
            map.put("var_3_stock",var_stock_3_st);

            map.put("var_4_qt",var_qt_4_st);
            map.put("var_4_mrp",var_mrp_4_st);
            map.put("var_4_salling_price",var_selling_4_st);
            map.put("var_4_stock",var_stock_4_st);


            map.put("stock",stock);
            map.put("salling_price",selling_price);
            map.put("stock_status","instock");
            map.put("timestamp", FieldValue.serverTimestamp());
            map.put("store_uid",firebaseAuth.getCurrentUser().getUid());
            map.put("product_for_home_page","no");

            firebaseFirestore.collection("kirana_shopkeeper_product").document(firebaseAuth.getCurrentUser().getUid().toString())
                    .collection("proudcts").document(productList.get(posion).getItemId()).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    productList.clear();
                    shop_list(key);
                    latestGovtJobRecyclerAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "Product Updated Into Your Stock", Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss();
                    mBottomSheetDialog.dismiss();




                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "Product  Not Updated Into Your Stock", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    mBottomSheetDialog.dismiss();


                }
            });

        }


    }

}
