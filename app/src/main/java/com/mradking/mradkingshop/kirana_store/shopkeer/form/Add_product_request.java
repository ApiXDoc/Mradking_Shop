package com.mradking.mradkingshop.kirana_store.shopkeer.form;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mradking.mradkingshop.R;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Add_product_request extends Activity {
    EditText name_of_product_et, product_code_et, makert_price_et, salling_price_et, margin_et, stock_et, cost_price_of_product_et,
            index_et, product_discreption_et, size1_et, size2_et, size3_et, size4_et, size5_et, color_1_et, color_2_et, color_3_et,
            color_4_et, no_of_images_et;
    EditText var_1_selling_et,var_2_selling_et,var_3_selling_et,var_4_selling_et,
            var_1_qt_et, var_2_qt_et, var_3_qt_et, var_4_qt_et,
            var_1_mrp_et, var_2_mrp_et, var_3_mrp_et, var_4_mrp_et;

    FirebaseAuth firebaseAuth;

    Button ok_bt;
    ImageView product_image, second_product_image, third_productimage, fouth_productImage, first_pro_duct_image;
    Spinner main_cetegory_spin, sub_cetegory_spin,
            product_sub_cetegory_type,product_sub_cetegory_type_sub,varition_sppiner;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    FirebaseStorage storage;
    StorageReference storageReference;
    LinearLayout varition_layout,vartion_detail_1_lay,vartion_detail_2_lay,vartion_detail_3_lay,vartion_detail_4_lay;
    CheckBox variton_yes_check_box,variton_no_check_box;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    int image_int = 0;
    EditText var_stock1,var_stock2,var_stock3,var_stock4;
    int count=1;
    String varition_status_st;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_product_adding_form);


        name_of_product_et = (EditText) findViewById(R.id.product_cetegory_name);
        makert_price_et = (EditText) findViewById(R.id.marketing_price);
        salling_price_et = (EditText) findViewById(R.id.salling_price);
        cost_price_of_product_et = (EditText) findViewById(R.id.cost_price);
        margin_et = (EditText) findViewById(R.id.margin);
        stock_et = (EditText) findViewById(R.id.stock);

        product_sub_cetegory_type=(Spinner)findViewById(R.id.product_sub_cetegory_type);
        product_sub_cetegory_type_sub=(Spinner)findViewById(R.id.product_sub_cetegory_type_sub);

        ok_bt = findViewById(R.id.summit_bt);
        product_image = (ImageView) findViewById(R.id.product_image);
        main_cetegory_spin = (Spinner) findViewById(R.id.product_main_cetegory_spinner);
        progressDialog = new ProgressDialog(this);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
        sub_cetegory_spin = (Spinner) findViewById(R.id.product_sub_cetegory_spinner);

        firebaseAuth= FirebaseAuth.getInstance();


        cost_price_of_product_et.setVisibility(View.VISIBLE);
        cost_price_of_product_et.setHint("Brand Name");
        cost_price_of_product_et.setInputType(View.AUTOFILL_TYPE_TEXT);
        stock_et.setVisibility(View.VISIBLE);
        stock_et.setHint("Weight");
        stock_et.setInputType(View.AUTOFILL_TYPE_TEXT);

        margin_et.setVisibility(View.VISIBLE);
        margin_et.setHint("Stock");


        salling_price_et.setVisibility(View.VISIBLE);

        varition_layout=findViewById(R.id.variation_layout);
        variton_yes_check_box=findViewById(R.id.varition_yes);
        variton_no_check_box=findViewById(R.id.varition_no);
        vartion_detail_1_lay=findViewById(R.id.vartion_detail_1_lay);
        vartion_detail_2_lay=findViewById(R.id.vartion_detail_2_lay);
        vartion_detail_3_lay=findViewById(R.id.vartion_detail_3_lay);
        vartion_detail_4_lay=findViewById(R.id.vartion_detail_4_lay);

        var_stock1=findViewById(R.id.stock1);
        var_stock2=findViewById(R.id.stock2);
        var_stock3=findViewById(R.id.stock3);
        var_stock4=findViewById(R.id.stock4);


        var_1_mrp_et=findViewById(R.id.var_1_mrp_et);
        var_2_mrp_et=findViewById(R.id.var_2_mrp_et);
        var_3_mrp_et=findViewById(R.id.var_3_mrp_et);
        var_4_mrp_et=findViewById(R.id.var_4_mrp_et);

        var_1_qt_et=findViewById(R.id.var_1_qt_et);
        var_2_qt_et=findViewById(R.id.var_2_qt_et);
        var_3_qt_et=findViewById(R.id.var_3_qt_et);
        var_4_qt_et=findViewById(R.id.var_4_qt_et);

        var_1_selling_et=findViewById(R.id.var_1_selling_et);
        var_2_selling_et=findViewById(R.id.var_2_selling_et);
        var_3_selling_et=findViewById(R.id.var_3_selling_et);
        var_4_selling_et=findViewById(R.id.var_4_selling_et);
        varition_sppiner=findViewById(R.id.varition_sppiner);



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
                    varition_status_st ="yes";

                }

            }
        });

        variton_no_check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(compoundButton.isChecked()){

                    variton_yes_check_box.setChecked(false);
                    varition_layout.setVisibility(View.GONE);
                    varition_status_st ="no";


                }

            }
        });






        spinner_set_up();

        margin_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String cost_price_st=cost_price_of_product_et.getText().toString();
                String salling_price_st=salling_price_et.getText().toString();
                int marging_int=Integer.parseInt(salling_price_st)-Integer.parseInt(cost_price_st);
                margin_et.setText(String.valueOf(marging_int));


            }
        });



        product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chooseImage();
                image_int++;


            }
        });

        ok_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                uploading_and_check();
            }
        });







    }

    private void uploading_and_check() {


        if (name_of_product_et.getText().toString().contentEquals("")) {

            Toast.makeText(Add_product_request.this, "please fill the product name", Toast.LENGTH_SHORT).show();

        }else if (main_cetegory_spin.getSelectedItem().toString().contentEquals("Select_your_Main_cetegory")) {

            Toast.makeText(Add_product_request.this, "please select your main category", Toast.LENGTH_SHORT).show();


        } else if (sub_cetegory_spin.getSelectedItem().toString().contentEquals("Select_your_sub_cetegory")) {

            Toast.makeText(Add_product_request.this, "please select your sub category", Toast.LENGTH_SHORT).show();


        } else {


            progressDialog.setMessage("please wait......");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            count++;
            uploading();


        }

    }

    private void uploading() {

        Uri filep = filePath;

        final StorageReference riversRef = storageReference.child("kirana_product_images/" + filep.getLastPathSegment());
        UploadTask uploadTask = riversRef.putFile(filep);


        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return riversRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {


                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    final String main_product_image1_url1 = downloadUri.toString();

                    final String name_product_st = name_of_product_et.getText().toString();
                    final String market_price_st = makert_price_et.getText().toString();
                    String salling_price_st = salling_price_et.getText().toString();
                    String marging_st = margin_et.getText().toString();
                    String stock_st = stock_et.getText().toString();
                    String cost_of_product_st = cost_price_of_product_et.getText().toString();




                    String main_category_st = main_cetegory_spin.getSelectedItem().toString();
                    String sub_category_st = sub_cetegory_spin.getSelectedItem().toString();


                    if(product_sub_cetegory_type_sub.getCount()==0){

                        if(varition_status_st.contentEquals("no")) {


                            Map<String, Object> map = new HashMap<>();

                            map.put("name_product", name_product_st);
                            map.put("market_price", market_price_st);
                            map.put("main_category", main_category_st);
                            map.put("sub_category", sub_category_st);
                            map.put("sub_category_type", product_sub_cetegory_type.getSelectedItem().toString());
                            map.put("sub_category_type_sub", "no");
                            map.put("product_image_url", main_product_image1_url1);
                            map.put("quantity", stock_et.getText().toString());
                            map.put("brand", cost_of_product_st);
                            map.put("timestamp", FieldValue.serverTimestamp());
                            map.put("stock_status", "instock");
                            map.put("varition_status", "no");
                            map.put("store_uid", firebaseAuth.getCurrentUser().getUid());
                            map.put("product_for_home_page", "no");
                            map.put("stock", margin_et.getText().toString());
                            map.put("salling_price", salling_price_et.getText().toString());


                            firebaseFirestore.collection("kirana_shopkeeper_Requested_product").document().set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    progressDialog.dismiss();

                                    margin_et.setText("");
                                    makert_price_et.setText("");
                                    stock_et.setText("");


                                    image_int = 0;


                                    Toast.makeText(Add_product_request.this, "file  uploaded", Toast.LENGTH_SHORT).show();


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {

                                    Toast.makeText(Add_product_request.this, "file not uploaded", Toast.LENGTH_SHORT).show();


                                }
                            });


                        }else if(varition_status_st.contentEquals("yes")){

                            String product_sub_cetegory_type_st=product_sub_cetegory_type.getSelectedItem().toString();
                            String sub_category_type_sub_st ="no";
                            varition_yes_data_set_up(
                                    sub_category_type_sub_st,
                                    product_sub_cetegory_type_st
                                    , sub_category_st
                                    ,main_category_st,
                                    market_price_st,
                                    name_product_st,
                                    main_product_image1_url1
                                    ,cost_of_product_st,
                                    varition_sppiner.getSelectedItem().toString()
                                    ,stock_et.getText().toString()
                                    ,salling_price_et.getText().toString()
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
                                    ,var_stock1.getText().toString()
                                    ,var_stock2.getText().toString()
                                    ,var_stock3.getText().toString()
                                    ,var_stock4.getText().toString());

                        }


                    }else {


                        if(varition_status_st.contentEquals("no")){

                            Map<String, Object> map = new HashMap<>();

                            map.put("name_product", name_product_st);
                            map.put("market_price", market_price_st);
                            map.put("main_category", main_category_st);
                            map.put("sub_category", sub_category_st);
                            map.put("sub_category_type",product_sub_cetegory_type.getSelectedItem().toString());
                            map.put("sub_category_type_sub",product_sub_cetegory_type_sub.getSelectedItem().toString());

                            map.put("product_image_url", main_product_image1_url1);
                            map.put("quantity", stock_et.getText().toString());
                            map.put("brand", cost_of_product_st);
                            map.put("timestamp", FieldValue.serverTimestamp());
                            map.put("stock_status", "instock");
                            map.put("varition_status", "no");
                            map.put("store_uid", firebaseAuth.getCurrentUser().getUid());
                            map.put("product_for_home_page", "no");
                            map.put("stock", margin_et.getText().toString());
                            map.put("salling_price", salling_price_et.getText().toString());


                            firebaseFirestore.collection("kirana_shopkeeper_Requested_product").document().set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    progressDialog.dismiss();

                                    margin_et.setText("");
                                    makert_price_et.setText("");
                                    stock_et.setText("");


                                    image_int = 0;


                                    Toast.makeText(Add_product_request.this, "file  uploaded", Toast.LENGTH_SHORT).show();


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {

                                    Toast.makeText(Add_product_request.this, "file not uploaded", Toast.LENGTH_SHORT).show();


                                }
                            });





                        }else if(varition_status_st.contentEquals("yes")){


                           String product_sub_cetegory_type_st=product_sub_cetegory_type.getSelectedItem().toString();
                             String sub_category_type_sub_st =product_sub_cetegory_type_sub.getSelectedItem().toString();

                            varition_yes_data_set_up(
                                    sub_category_type_sub_st,
                                    product_sub_cetegory_type_st
                                    , sub_category_st
                                    ,main_category_st,
                                    market_price_st,
                                    name_product_st,
                                    main_product_image1_url1
                                    ,cost_of_product_st,
                                    varition_sppiner.getSelectedItem().toString()
                                    ,margin_et.getText().toString()
                                    ,salling_price_et.getText().toString()
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
                                    ,var_stock1.getText().toString()
                                    ,var_stock2.getText().toString()
                                    ,var_stock3.getText().toString()
                                    ,var_stock4.getText().toString()

                            );

                        }



                    }



                } else {

                    Toast.makeText(Add_product_request.this, "image_not_uploaded", Toast.LENGTH_SHORT).show();

                }


            }
        });



    }



    private void spinner_set_up() {

        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.kirana_main_categorys));
        main_cetegory_spin.setAdapter(spinnerCountShoesArrayAdapter);


        ArrayAdapter<String> kirana_product_grossery_adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.kirana_product_grossery));

        ArrayAdapter<String> kirana_home_hygience_product_adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.kirana_home_hygience_product));


        ArrayAdapter<String> kirana_beverages_product_adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.kirana_beverages_product));


        ArrayAdapter<String> kirana_persnoal_care_product_adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.kirana_persnoal_care_product));

        ArrayAdapter<String> kirana_snacks_product = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.kirana_snacks_product));


        //////////////////////////




        //////////////////////
        main_cetegory_spin.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                        Object item = parent.getItemAtPosition(pos);

                        if (item.toString().contentEquals("kirana_product_grossery")) {


                            sub_cetegory_spin.setAdapter(kirana_product_grossery_adapter);

                            sub_cetegory_spin_set_up();


                        } else if (item.toString().contentEquals("kirana_home_hygience_product")) {

                            sub_cetegory_spin.setAdapter(kirana_home_hygience_product_adapter);

                            sub_cetegory_spin_set_up();


                        } else if (item.toString().contentEquals("kirana_beverages_product")) {

                            sub_cetegory_spin.setAdapter(kirana_beverages_product_adapter);
                            sub_cetegory_spin_set_up();


                        } else if (item.toString().contentEquals("kirana_persnoal_care_product")) {

                            sub_cetegory_spin.setAdapter(kirana_persnoal_care_product_adapter);
                            sub_cetegory_spin_set_up();


                        } else if (item.toString().contentEquals("kirana_snacks_product")) {

                            sub_cetegory_spin.setAdapter(kirana_snacks_product);
                            sub_cetegory_spin_set_up();


                        } else {


                        }


                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });


    }

    private void sub_cetegory_spin_set_up() {
        ArrayAdapter<String> spinneratta_adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.kirana_product_grossery_atta));


        ArrayAdapter<String> spinner_rich_adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.kirana_product_grossery_rice));



        ArrayAdapter<String> spinner_oil_adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.kirana_product_grossery_oil));

        ArrayAdapter<String> spinner_massale_adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.kirana_product_masalas));

        //////////////////////////////hygience//////////


        ArrayAdapter<String> h1 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.kirana_home_hygience_product_detergents));

        ArrayAdapter<String> h2 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.kirana_home_hygience_product_cleners));
        ArrayAdapter<String> h3 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.kirana_home_hygience_product_dishwhashers));

        ArrayAdapter<String> h4 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.kirana_home_hygience_product_repellents));

        ArrayAdapter<String> h5 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.kirana_home_hygience_product_air_freshners));

        //////////////////////




        sub_cetegory_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                if (item.toString().contentEquals("atta")) {
                    product_sub_cetegory_type.setAdapter(spinneratta_adapter);


                }else if(item.toString().contentEquals("rice")){
                    product_sub_cetegory_type.setAdapter(spinner_rich_adapter);


                }else if(item.toString().contentEquals("oil_ghee")){

                    product_sub_cetegory_type.setAdapter(spinner_oil_adapter);

                }else if(item.toString().contentEquals("masalas")){

                    product_sub_cetegory_type.setAdapter(spinner_massale_adapter);

                    product_sub_cetegory_type_sub_list_set_up();

                }else if(item.toString().contentEquals("sugar")){

                    product_sub_cetegory_type.setAdapter(spinneratta_adapter);

                    product_sub_cetegory_type_sub_list_set_up();

                }else if(item.toString().contentEquals("salt")){

                    product_sub_cetegory_type.setAdapter(spinneratta_adapter);

                    product_sub_cetegory_type_sub_list_set_up();

                }else if(item.toString().contentEquals("dal")){

                    product_sub_cetegory_type.setAdapter(spinneratta_adapter);


                }else if(item.toString().contentEquals("dry_fruit")){

                    product_sub_cetegory_type.setAdapter(spinneratta_adapter);


                }else if(item.toString().contentEquals("basan_sooji_daila")){

                    product_sub_cetegory_type.setAdapter(spinneratta_adapter);


                }else if(item.toString().contentEquals("detergents")){

                    product_sub_cetegory_type.setAdapter(h1);


                }else if(item.toString().contentEquals("cleners")){

                    product_sub_cetegory_type.setAdapter(h2);


                }else if(item.toString().contentEquals("dishwhashers")){

                    product_sub_cetegory_type.setAdapter(h3);


                }else if(item.toString().contentEquals("repellents")){

                    product_sub_cetegory_type.setAdapter(h4);


                }
                else if(item.toString().contentEquals("air_freshners")){

                    product_sub_cetegory_type.setAdapter(h5);


                }else {

                    product_sub_cetegory_type.setAdapter(spinneratta_adapter);


                    product_sub_cetegory_type_sub_list_set_up();

                }





            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    private void product_sub_cetegory_type_sub_list_set_up() {



        ArrayAdapter<String> masale_sub_powder_adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.kirana_product_masalas_powder));

        ArrayAdapter<String> masale_sub_ready_adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.kirana_product_masalas_ready));
        ArrayAdapter<String> masale_sub_unpacked_adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.kirana_product_masalas_unpacked));


        product_sub_cetegory_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                String item_st=item.toString();


                if (item_st.contentEquals("Powder")) {

                    product_sub_cetegory_type_sub.setAdapter(masale_sub_powder_adapter);


                }else if(item.toString().contentEquals("Ready")){
                    product_sub_cetegory_type_sub.setAdapter(masale_sub_ready_adapter);


                }else if(item.toString().contentEquals("Unpacked")){

                    product_sub_cetegory_type_sub.setAdapter(masale_sub_unpacked_adapter);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )

        {

            if(image_int==1){

                filePath = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);


                    product_image.setImageBitmap(bitmap);
                    image_int++;

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }else {


                image_int=0;

            }


        }
    }



    private void varition_yes_data_set_up(
           String sub_category_type_sub_st,
            String product_sub_cetegory_type_st
                          ,String sub_category_st
                           ,String main_category_st
                           ,String market_price_st
                            ,String name_product_st,
                                          String main_product_image1_url1,
                                          String cost_of_product_st
                                          ,String sppiner_st,
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



            map.put("name_product", name_product_st);
            map.put("market_price", market_price_st);
            map.put("main_category",main_category_st);
            map.put("sub_category", sub_category_st);
            map.put("product_image_url",main_product_image1_url1 );
            map.put("quantity",stock_et.getText().toString());
            map.put("brand",cost_of_product_st);
            map.put("varition_status","yes");
            map.put("sub_category_type",product_sub_cetegory_type_st );
            map.put("sub_category_type_sub", sub_category_type_sub_st);

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

            firebaseFirestore.collection("kirana_shopkeeper_Requested_product").document(firebaseAuth.getCurrentUser().getUid().toString())
                    .collection("proudcts").document().set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    Toast.makeText(Add_product_request.this, "Product Added Into Your Stock", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    
                    

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Add_product_request.this, "Productv Not Added Into Your Stock", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    


                }
            });
        }else if(sppiner_st.contentEquals("2")){


            Map<String ,Object> map=new HashMap<>();

            map.put("name_product", name_product_st);
            map.put("market_price", market_price_st);
            map.put("main_category",main_category_st);
            map.put("sub_category", sub_category_st);
            map.put("product_image_url",main_product_image1_url1 );
            map.put("quantity",stock_et.getText().toString());
            map.put("brand",cost_of_product_st);
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

            firebaseFirestore.collection("kirana_shopkeeper_Requested_product").document(firebaseAuth.getCurrentUser().getUid().toString())
                    .collection("proudcts").document().set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    Toast.makeText(Add_product_request.this, "Product Added Into Your Stock", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    
                    

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Add_product_request.this, "Productv Not Added Into Your Stock", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    


                }
            });


        }else if(sppiner_st.contentEquals("3")){

            Map<String ,Object> map=new HashMap<>();

            map.put("name_product", name_product_st);
            map.put("market_price", market_price_st);
            map.put("main_category",main_category_st);
            map.put("sub_category", sub_category_st);
            map.put("product_image_url",main_product_image1_url1 );
            map.put("quantity",stock_et.getText().toString());
            map.put("brand",cost_of_product_st);
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

            firebaseFirestore.collection("kirana_shopkeeper_Requested_product").document(firebaseAuth.getCurrentUser().getUid().toString())
                    .collection("proudcts").document().set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    Toast.makeText(Add_product_request.this, "Product Added Into Your Stock", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    
                    

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Add_product_request.this, "Productv Not Added Into Your Stock", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    


                }
            });


        }else if(sppiner_st.contentEquals("4")){


            Map<String ,Object> map=new HashMap<>();

            map.put("name_product", name_product_st);
            map.put("market_price", market_price_st);
            map.put("main_category",main_category_st);
            map.put("sub_category", sub_category_st);
            map.put("product_image_url",main_product_image1_url1 );
            map.put("quantity",stock_et.getText().toString());
            map.put("brand",cost_of_product_st);
            map.put("varition_status","yes");
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

            firebaseFirestore.collection("kirana_shopkeeper_Requested_product").document(firebaseAuth.getCurrentUser().getUid().toString())
                    .collection("proudcts").document().set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    Toast.makeText(Add_product_request.this, "Product Added Into Your Stock", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    
                    

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Add_product_request.this, "Productv Not Added Into Your Stock", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    


                }
            });

        }


    }


}
