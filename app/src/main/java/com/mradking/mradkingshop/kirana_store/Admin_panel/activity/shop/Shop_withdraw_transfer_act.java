package com.mradking.mradkingshop.kirana_store.Admin_panel.activity.shop;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.deeplabstudio.fcmsend.FCMSend;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.Admin_panel.activity.Product_List_Build_From;
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;
import com.mradking.mradkingshop.kirana_store.custmer.Model.cart_list_modal;
import com.mradking.mradkingshop.kirana_store.custmer.activity.Thanks_place_order;
import com.mradking.mradkingshop.kirana_store.custmer.adapter.Order_item_list_adapter;
import com.mradking.mradkingshop.kirana_store.shopkeer.activity.Order_item_list_shop;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop_withdraw_transfer_act extends AppCompatActivity {
    private static String serverKey = "AAAAjLqtavI:APA91bFHptGvx-u3tV-dIv7QUAk8QOxuVX3CffNCDp2N3o8CjFsrcf0xsFpoGUEPFO1xKZhXHvz_k5Y0PgvhHuCsKy0WaHlkxP47WztC_Npk45Iq8MElk3ganG0NIP7zgvBJdA_Sswi5";

    private RecyclerView cart_recycler_view;
    private List<CommonModal> productList;
    private Activity activity = Shop_withdraw_transfer_act.this;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public Order_item_list_adapter latestGovtJobRecyclerAdapter;
    ProgressDialog progressDialog;
    FirebaseStorage storage;
    StorageReference storageReference;
    private Uri filePath,second_filePath,third_filePath,fouth_filePath;
    Spinner product_type;
    String color_yes ;
    private final int PICK_IMAGE_REQUEST = 71;
    int image_int=0;

    public DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;
    TextView add_more_items, total_amout_payment_slip,
            total_pay_txt, delivery_fee_payment_slip, net_payment_amount_payment_slip,
            adavance_amount,remain_amount,bt_txt,screenshort_txt;

    MaterialRippleLayout conform_bt,complet_bt;
    ScrollView check_out_page;
    RelativeLayout cart_layout;
    ImageView sceenshort_image;
    LinearLayout payment_layout, advance_amount_layout;
    Glide glide;
    ProgressBar progres_bar_screenshort;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_admin_shop_tranfer_act);

        productList = new ArrayList<CommonModal>();


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

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
        bt_txt=findViewById(R.id.bt_txt);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        conform_bt=findViewById(R.id.conform_bt);
        complet_bt=findViewById(R.id.compelte_bt);
        progres_bar_screenshort=findViewById(R.id.progres_bar_screenshort);

        sceenshort_image=findViewById(R.id.sceenshort_image);
        screenshort_txt=findViewById(R.id.screenshort_txt);

        conform_bt.setVisibility(View.VISIBLE);

        bt_txt.setText("TRANSFER AMOUNT");


        FCMSend.SetServerKey(serverKey);

        adavance_amount=findViewById(R.id.adavance_amount);
        remain_amount=findViewById(R.id.remain_amount);

        cart_recycler_view.setHasFixedSize(true);
        cart_recycler_view.setLayoutManager(lm1);
        cart_recycler_view.setAdapter(latestGovtJobRecyclerAdapter);




        add_more_items.setText(String.valueOf(productList.size()+1));






        total_amout_payment_slip.setText(getIntent().getExtras().getString("order_amount"));
        delivery_fee_payment_slip.setText(getIntent().getExtras().getString("comission"));
        net_payment_amount_payment_slip.setText(getIntent().getExtras().getString("tax"));
        adavance_amount.setText(getIntent().getExtras().getString("delivery_charge"));
        remain_amount.setText(getIntent().getExtras().getString("net_payout"));


        if(getIntent().getExtras().getString("withdraw_status").contentEquals("transfer")){

            complet_bt.setVisibility(View.GONE);
            conform_bt.setVisibility(View.GONE);
            screenshort_txt.setText("Payment ScreenShort");

            sceenshort_image.setClickable(false);
            sceenshort_image.setVisibility(View.GONE);

            glide.with(getApplicationContext())
                    .load(getIntent().getExtras().getString("withdraw_screensort_proof"))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            progres_bar_screenshort.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            progres_bar_screenshort.setVisibility(View.GONE);
                            sceenshort_image.setVisibility(View.VISIBLE);
                            return false;
                        }
                    }).into(sceenshort_image);


        }




        sceenshort_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                chooseImage();
                image_int++;

            }
        });



        complet_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploading();

            }
        });



        conform_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText("9667412868");

                    Intent launchIntent = activity.getPackageManager().getLaunchIntentForPackage("com.phonepe.app");
                    if (launchIntent != null) {
                        activity.startActivity(launchIntent);//null pointer check in case package name was not found
                    }

                } else {
                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", "9667412868");
                    clipboard.setPrimaryClip(clip);

                    Intent launchIntent = activity.getPackageManager().getLaunchIntentForPackage("com.phonepe.app");
                    if (launchIntent != null) {
                        activity.startActivity(launchIntent);//null pointer check in case package name was not found
                    }
                }



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


                    sceenshort_image.setImageBitmap(bitmap);
                    image_int++;

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }

        }
    }





    private void uploading() {

        progressDialog.setMessage("please Wait........");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        Uri filep = filePath;

        final StorageReference riversRef = storageReference.child("payment_proof_screenshort/" + filep.getLastPathSegment());
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
                    DatePicker datePicker = new DatePicker(getApplicationContext());
                    int months =datePicker.getMonth()+1;
                    String current_date = datePicker.getDayOfMonth() + "/" + months + "/" + datePicker.getYear();


                    firebaseFirestore.collection("ShoprKeeper_detail")
                            .document(getIntent().getExtras().getString("store_uid"))
                            .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                            DocumentSnapshot documentSnapshot=task.getResult();

                            String shop_message_token=documentSnapshot.getString("shop_message_token");




                            Map<String, Object> map = new HashMap<>();
                            map.put("withdraw_status","transfer");
                            map.put("withraw_request_date",current_date);
                            map.put("withdraw_screensort_proof",main_product_image1_url1);
                            map.put("withdraw_timestamp",FieldValue.serverTimestamp());


                            firebaseFirestore.collection("kirana_order_list")
                                    .document(getIntent().getExtras().getString("item_id"))
                                    .update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    progressDialog.dismiss();



                                    notification_send(shop_message_token,getIntent().getExtras().getString("net_payout"));

                                    Toast.makeText(Shop_withdraw_transfer_act.this, "Done", Toast.LENGTH_SHORT).show();


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {

                                    Toast.makeText(Shop_withdraw_transfer_act.this, "file not uploaded", Toast.LENGTH_SHORT).show();


                                }
                            });





                        }
                    });




                } else {

                    Toast.makeText(Shop_withdraw_transfer_act.this, "image_not_uploaded", Toast.LENGTH_SHORT).show();

                }


            }
        });



    }

    private void notification_send(String token,String total_amt_st) {

        FirebaseMessaging.getInstance().subscribeToTopic(token).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                System.out.println("Subscription successful");
            }
        });



        FCMSend.Builder build1 = new FCMSend.Builder(token)
                .setTitle("You PayOut Received")
                .setBody("You Payout Amount Rs"+total_amt_st);

        build1.send();


        String result2 = build1.send().Result();


        if(ChekSuccess( result2).contentEquals("Success")){




            Toast.makeText(getApplicationContext(), "message send", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            progressDialog.dismiss();


            Log.e("error_in_notification",result2);
        }


    }


    private String ChekSuccess(String result) {
        try {
            JSONObject object = new JSONObject(result);
            String success = object.getString("success");
            if (success.equals("1")) {
                return "Success";
            } else if (success.equals("0")) {
                return "Unsuccessful";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Unsuccessful";
    }


}
