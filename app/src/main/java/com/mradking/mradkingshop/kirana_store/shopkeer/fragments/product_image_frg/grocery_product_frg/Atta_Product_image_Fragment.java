package com.mradking.mradkingshop.kirana_store.shopkeer.fragments.product_image_frg.grocery_product_frg;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
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

import com.mradking.mradkingshop.kirana_store.Admin_panel.activity.Product_List_Build_From;
import com.mradking.mradkingshop.kirana_store.shopkeer.adapter.BrandListAdapter;
import com.mradking.mradkingshop.kirana_store.shopkeer.adapter.ProductListImageAdapter;
import com.mradking.mradkingshop.kirana_store.shopkeer.inface.brand_interface;
import com.mradking.mradkingshop.kirana_store.shopkeer.inface.product_list_interface;
import com.mradking.mradkingshop.kirana_store.shopkeer.list.Added_product_list;
import com.mradking.mradkingshop.kirana_store.shopkeer.modal.BrandModal;
import com.mradking.mradkingshop.kirana_store.shopkeer.modal.ProductListImageModal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Atta_Product_image_Fragment extends Fragment implements brand_interface, product_list_interface {

    private RecyclerView cart_recycler_view,brand_recycler_view;
    private List<ProductListImageModal> productList;
    private List<BrandModal> brandList;

    private FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    public ProductListImageAdapter latestGovtJobRecyclerAdapter;
    public BrandListAdapter brandListAdapter;
    public DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;
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


    LinearLayout varition_layout,vartion_detail_1_lay,vartion_detail_2_lay,vartion_detail_3_lay,vartion_detail_4_lay;
    CheckBox variton_yes_check_box,variton_no_check_box;
    Spinner varition_sppiner;
    EditText var_1_selling_et,var_2_selling_et,var_3_selling_et,var_4_selling_et,
            var_1_qt_et, var_2_qt_et, var_3_qt_et, var_4_qt_et,
            var_1_mrp_et, var_2_mrp_et, var_3_mrp_et, var_4_mrp_et
            ,var_1_stock_et,var_2_stock_et,var_3_stock_et,var_4_stock_et;






    ////////////////////
    Glide glide;
    ArrayList<String>arrayList;
    FloatingActionButton fab_add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View root = inflater.inflate(R.layout.kirana_product_list_catergory, container, false);

        productList = new ArrayList<ProductListImageModal>();
        brandList=new ArrayList<BrandModal>();

        progressDialog=new ProgressDialog(getActivity());
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();


        latestGovtJobRecyclerAdapter = new ProductListImageAdapter(getActivity(), productList,this);
        brandListAdapter=new BrandListAdapter(getActivity(),brandList,this);

        cart_recycler_view = root.findViewById(R.id.cart_list);
        brand_recycler_view=root.findViewById(R.id.brand_list);
        fab_add=root.findViewById(R.id.fab_add);
        bottom_sheet = root.findViewById(R.id.bottom_sheet);
        mBehavior = BottomSheetBehavior.from(bottom_sheet);

        TabLayout tabLayout=root.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Packed"));
        tabLayout.addTab(tabLayout.newTab().setText("Unpacked"));

        tabLayout.setVisibility(View.GONE);


        number_item_added=root.findViewById(R.id.number_item_txt);
        see_more_bt=root.findViewById(R.id.see_more);
        number_item_added_set_up();



        LinearLayoutManager lm1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);


        cart_recycler_view.setHasFixedSize(true);
        cart_recycler_view.setLayoutManager(lm1);
        cart_recycler_view.setAdapter(latestGovtJobRecyclerAdapter);


        LinearLayoutManager lm2=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);

        brand_recycler_view.setHasFixedSize(true);
        brand_recycler_view.setLayoutManager(lm2);
        brand_recycler_view.setAdapter(brandListAdapter);



        shop_list("atta","all","brand_name");





        see_more_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(getActivity(), Added_product_list.class);
                intent.putExtra("key","atta");
                startActivity(intent);



            }
        });




        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(getActivity(), Product_List_Build_From.class);
                startActivity(intent);

            }
        });


        return root;
    }

    private void number_item_added_set_up() {

        firebaseFirestore.collection("kirana_shopkeeper_product")
                .document(firebaseAuth.getCurrentUser().getUid())
                .collection("proudcts")
                .whereEqualTo("sub_category","atta").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot documentSnapshot=task.getResult();

                number_item_added
                        .setText("Number Of Item Added:-"+String.valueOf(documentSnapshot.getDocuments().size()));



            }
        });



    }


    private void shop_list( String text,String brand,String brand_name) {

        if(brand.contentEquals("all")){

            if (firebaseAuth.getCurrentUser() != null) {

                firebaseFirestore = FirebaseFirestore.getInstance();

                cart_recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                        Boolean reachedBottom = !recyclerView.canScrollVertically(1);

                        if (reachedBottom) {


                            String desc = lastVisible.getString("name");

                            loadMorePost(text,"all",brand_name);

                        }

                    }
                });

                final String uid = firebaseAuth.getCurrentUser().getUid();

                Query firstQuery =  firebaseFirestore.collection("Kirana_Product_Images")
                        .whereEqualTo("sub_category",text)
                        .whereEqualTo("sub_category_type","packed")
                        .limit(10);


                firstQuery.addSnapshotListener( new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                        if (isFirstPageFirstLoad) {

                            if (documentSnapshots.isEmpty()) {


                                Toast.makeText(getActivity(), "there is no payment request till no", Toast.LENGTH_SHORT).show();


                            } else {


                                lastVisible = documentSnapshots.getDocuments().get(documentSnapshots.size() - 1);


                            }


                        }
                        for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                            if (doc.getType() == DocumentChange.Type.ADDED) {

                                ProductListImageModal blogPost = doc.getDocument().toObject(ProductListImageModal.class).withId(doc.getDocument().getId());
                                BrandModal brandModal=doc.getDocument().toObject(BrandModal.class);

                                String itemId = doc.getDocument().getId();

                                blogPost.setItem_id(itemId);



                                if (isFirstPageFirstLoad) {
                                    productList.add(0, blogPost);
                                    brandList.add(0,brandModal);


                                } else {

                                    productList.add(0, blogPost);
                                    brandList.add(0,brandModal);

                                }


                                brandListAdapter.notifyDataSetChanged();;
                                latestGovtJobRecyclerAdapter.notifyDataSetChanged();



                            }
                        }

                        isFirstPageFirstLoad = false;

                    }
                });

            } else {

                Toast.makeText(getActivity(), "no user", Toast.LENGTH_SHORT).show();
            }



        }else if(brand.contentEquals("brand")){



            productList.clear();
            brandList.clear();
            if (firebaseAuth.getCurrentUser() != null) {

                firebaseFirestore = FirebaseFirestore.getInstance();

                cart_recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                        Boolean reachedBottom = !recyclerView.canScrollVertically(1);

                        if (reachedBottom) {


                            String desc = lastVisible.getString("name");

                            loadMorePost(text,"brand",brand_name);

                        }

                    }
                });

                final String uid = firebaseAuth.getCurrentUser().getUid();

                Query firstQuery =  firebaseFirestore.collection("Kirana_Product_Images")
                        .whereEqualTo("sub_category",text)
                        .whereEqualTo("brand",brand_name)
                        .whereEqualTo("sub_category_type","packed")
                        .limit(10);


                firstQuery.addSnapshotListener( new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                        if (isFirstPageFirstLoad) {

                            if (documentSnapshots.isEmpty()) {


                                Toast.makeText(getActivity(), "there is no payment request till no", Toast.LENGTH_SHORT).show();


                            } else {


                                lastVisible = documentSnapshots.getDocuments().get(documentSnapshots.size() - 1);


                            }


                        }
                        for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                            if (doc.getType() == DocumentChange.Type.ADDED) {

                                ProductListImageModal blogPost = doc.getDocument().toObject(ProductListImageModal.class).withId(doc.getDocument().getId());
                                BrandModal brandModal=doc.getDocument().toObject(BrandModal.class);

                                String itemId = doc.getDocument().getId();

                                blogPost.setItem_id(itemId);



                                if (isFirstPageFirstLoad) {
                                    productList.add(0, blogPost);
                                    brandList.add(0,brandModal);


                                } else {

                                    productList.add(0, blogPost);
                                    brandList.add(0,brandModal);

                                }


                                brandListAdapter.notifyDataSetChanged();;
                                latestGovtJobRecyclerAdapter.notifyDataSetChanged();



                            }
                        }

                        isFirstPageFirstLoad = false;

                    }
                });

            } else {

                Toast.makeText(getActivity(), "no user", Toast.LENGTH_SHORT).show();
            }


        }




    }

    private void loadMorePost(String text,String brand,String brand_name) {

        if(brand.contentEquals("all")){

            String uid = firebaseAuth.getCurrentUser().getUid();

            Query nextQuery =  firebaseFirestore.collection("Kirana_Product_Images")
                    .whereEqualTo("sub_category",text)
                    .whereEqualTo("sub_category_type","packed")
                    .startAfter(lastVisible).limit(10);



            nextQuery.addSnapshotListener( new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                    if (!documentSnapshots.isEmpty()) {

                        lastVisible = documentSnapshots.getDocuments().get(documentSnapshots.size() - 1);
                        for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                            if (doc.getType() == DocumentChange.Type.ADDED) {

                                ProductListImageModal govt_job = doc.getDocument().toObject(ProductListImageModal.class);
                                BrandModal brandModal=doc.getDocument().toObject(BrandModal.class);

                                String itemId = doc.getDocument().getId();

                                govt_job.setItem_id(itemId);

                                productList.add(govt_job);

                                brandList.add(brandModal);

                                brandListAdapter.notifyDataSetChanged();
                                latestGovtJobRecyclerAdapter.notifyDataSetChanged();

                            }
                        }

                    }

                }
            });

        }else if(brand.contentEquals("brand")){

            String uid = firebaseAuth.getCurrentUser().getUid();

            Query nextQuery =  firebaseFirestore.collection("Kirana_Product_Images")
                    .whereEqualTo("sub_category",text)
                    .whereEqualTo("sub_category_type","packed")
                    .whereEqualTo("brand",brand_name)
                    .startAfter(lastVisible).limit(10);



            nextQuery.addSnapshotListener( new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                    if (!documentSnapshots.isEmpty()) {

                        lastVisible = documentSnapshots.getDocuments().get(documentSnapshots.size() - 1);
                        for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                            if (doc.getType() == DocumentChange.Type.ADDED) {

                                ProductListImageModal govt_job = doc.getDocument().toObject(ProductListImageModal.class);
                                BrandModal brandModal=doc.getDocument().toObject(BrandModal.class);

                                String itemId = doc.getDocument().getId();

                                govt_job.setItem_id(itemId);

                                productList.add(govt_job);

                                brandList.add(brandModal);

                                brandListAdapter.notifyDataSetChanged();
                                latestGovtJobRecyclerAdapter.notifyDataSetChanged();

                            }
                        }

                    }

                }
            });

        }


    }

    @Override
    public void click_brand(int posion) {

        productList.clear();

        shop_list("atta","brand",brandList.get(posion).getBrand());

        latestGovtJobRecyclerAdapter.notifyDataSetChanged();
        cart_recycler_view.refreshDrawableState();



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
                    .collection("proudcts").document().set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    Toast.makeText(getActivity(), "Product Added Into Your Stock", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    mBottomSheetDialog.dismiss();
                    number_item_added_set_up();

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
                    .collection("proudcts").document().set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    Toast.makeText(getActivity(), "Product Added Into Your Stock", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    mBottomSheetDialog.dismiss();
                    number_item_added_set_up();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "Productv Not Added Into Your Stock", Toast.LENGTH_SHORT).show();
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
                    .collection("proudcts").document().set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    Toast.makeText(getActivity(), "Product Added Into Your Stock", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    mBottomSheetDialog.dismiss();
                    number_item_added_set_up();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "Productv Not Added Into Your Stock", Toast.LENGTH_SHORT).show();
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
                    .collection("proudcts").document().set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    Toast.makeText(getActivity(), "Product Added Into Your Stock", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    mBottomSheetDialog.dismiss();
                    number_item_added_set_up();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "Productv Not Added Into Your Stock", Toast.LENGTH_SHORT).show();
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
                    .collection("proudcts").document().set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    Toast.makeText(getActivity(), "Product Added Into Your Stock", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    mBottomSheetDialog.dismiss();
                    number_item_added_set_up();

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


}
