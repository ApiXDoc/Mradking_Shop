package com.mradking.mradkingshop.kirana_store.Admin_panel;

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
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import com.google.firebase.storage.UploadTask;

import com.mradking.mradkingshop.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class kirana_product_adding_form extends Activity {
    EditText name_of_product_et, product_code_et, makert_price_et, salling_price_et, margin_et, stock_et, cost_price_of_product_et,
            index_et, product_discreption_et, size1_et, size2_et, size3_et, size4_et, size5_et, color_1_et, color_2_et, color_3_et,
            color_4_et, no_of_images_et;
    Button ok_bt;
    ImageView product_image, second_product_image, third_productimage, fouth_productImage, first_pro_duct_image;
    Spinner main_cetegory_spin, sub_cetegory_spin;
    LinearLayout size_panel_lin, color_panel_lin;
    CheckBox product_size_check_box, product_color_check_box;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    FirebaseStorage storage;
    StorageReference storageReference;
    private Uri filePath, second_filePath, third_filePath, fouth_filePath;
    Spinner product_type;
    String color_yes;
    private final int PICK_IMAGE_REQUEST = 71;
    int image_int = 0;

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


        ok_bt = findViewById(R.id.summit_bt);
        product_image = (ImageView) findViewById(R.id.product_image);
        main_cetegory_spin = (Spinner) findViewById(R.id.product_main_cetegory_spinner);
        sub_cetegory_spin = (Spinner) findViewById(R.id.product_sub_cetegory_spinner);

        progressDialog = new ProgressDialog(this);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();


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

            Toast.makeText(kirana_product_adding_form.this, "please fill the product name", Toast.LENGTH_SHORT).show();

        } else if (cost_price_of_product_et.getText().toString().contentEquals("")) {

            Toast.makeText(kirana_product_adding_form.this, "please fill the cost price product", Toast.LENGTH_SHORT).show();


        } else if (salling_price_et.getText().toString().contentEquals("")) {

            Toast.makeText(kirana_product_adding_form.this, "please fill the salling price of product", Toast.LENGTH_SHORT).show();


        } else if (makert_price_et.getText().toString().contentEquals("")) {

            Toast.makeText(kirana_product_adding_form.this, "please fill the Market Price of product", Toast.LENGTH_SHORT).show();


        } else if (margin_et.getText().toString().contentEquals("")) {

            Toast.makeText(kirana_product_adding_form.this, "please fill the Margin product", Toast.LENGTH_SHORT).show();


        } else if (stock_et.getText().toString().contentEquals("")) {

            Toast.makeText(kirana_product_adding_form.this, "please fill the stock of product", Toast.LENGTH_SHORT).show();


        } else if (main_cetegory_spin.getSelectedItem().toString().contentEquals("Select_your_Main_cetegory")) {

            Toast.makeText(kirana_product_adding_form.this, "please select your main category", Toast.LENGTH_SHORT).show();


        } else if (sub_cetegory_spin.getSelectedItem().toString().contentEquals("Select_your_sub_cetegory")) {

            Toast.makeText(kirana_product_adding_form.this, "please select your sub category", Toast.LENGTH_SHORT).show();


        } else {


            progressDialog.setMessage("please wait......");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
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

                    Map<String, Object> map = new HashMap<>();

                    map.put("name_product", name_product_st);
                    map.put("market_price", market_price_st);
                    map.put("salling_price", salling_price_st);
                    map.put("margin", marging_st);
                    map.put("stock", stock_st);
                    map.put("cost_price", cost_of_product_st);
                    map.put("main_category", main_category_st);
                    map.put("sub_category", sub_category_st);
                    map.put("product_image_url", main_product_image1_url1);
                    map.put("timestamp", FieldValue.serverTimestamp());

                    firebaseFirestore.collection("kirna_products").document().set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            progressDialog.dismiss();

                            margin_et.setText("");
                            makert_price_et.setText("");
                            stock_et.setText("");


                            image_int = 0;


                            Toast.makeText(kirana_product_adding_form.this, "file  uploaded", Toast.LENGTH_SHORT).show();


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {

                            Toast.makeText(kirana_product_adding_form.this, "file not uploaded", Toast.LENGTH_SHORT).show();


                        }
                    });


                } else {

                    Toast.makeText(kirana_product_adding_form.this, "image_not_uploaded", Toast.LENGTH_SHORT).show();

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









        main_cetegory_spin.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                        Object item = parent.getItemAtPosition(pos);

                        if(item.toString().contentEquals("kirana_product_grossery")){


                            sub_cetegory_spin.setAdapter(kirana_product_grossery_adapter);



                        }else if(item.toString().contentEquals("kirana_home_hygience_product")){

                            sub_cetegory_spin.setAdapter(kirana_home_hygience_product_adapter);


                        }else if(item.toString().contentEquals("kirana_beverages_product")){

                            sub_cetegory_spin.setAdapter(kirana_beverages_product_adapter);


                        }else if(item.toString().contentEquals("kirana_persnoal_care_product")){

                            sub_cetegory_spin.setAdapter(kirana_persnoal_care_product_adapter);


                        }else if(item.toString().contentEquals("kirana_snacks_product")){

                            sub_cetegory_spin.setAdapter(kirana_snacks_product);


                        }else {


                        }



                    }
                    public void onNothingSelected(AdapterView<?> parent) {
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
}
