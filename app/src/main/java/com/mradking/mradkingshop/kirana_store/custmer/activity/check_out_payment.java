package com.mradking.mradkingshop.kirana_store.custmer.activity;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.deeplabstudio.fcmsend.FCMSend;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mradking.mradkingshop.R;
import com.shreyaspatil.EasyUpiPayment.EasyUpiPayment;
import com.shreyaspatil.EasyUpiPayment.listener.PaymentStatusListener;
import com.shreyaspatil.EasyUpiPayment.model.TransactionDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class check_out_payment extends Activity implements PaymentStatusListener {
    private static String serverKey = "AAAAjLqtavI:APA91bFHptGvx-u3tV-dIv7QUAk8QOxuVX3CffNCDp2N3o8CjFsrcf0xsFpoGUEPFO1xKZhXHvz_k5Y0PgvhHuCsKy0WaHlkxP47WztC_Npk45Iq8MElk3ganG0NIP7zgvBJdA_Sswi5";

    String near_place,total_amt,delivery,net_payment,phone,house_no,colony,zip,city,state;
    Double lat,log;
    TextView shiping_address;
    CheckBox cash_on_delivery_check_box, online_payment_check_box;
    CardView cod_notice_layout,pay_bt;
    TextView total_amt_pay,total_pay_txt,near_addres,delivery_amt_pay, net_amt_pay, add_itme_pay, advance_amount_pay, remain_amount_pay;
    String payment_method_st="no";
    LinearLayout advance_amount_layout;
    String remaining_amount_st;
    public   static final String CHANNEL_ID="androidxChannel";
    private static final String CHANNEL_NAME= "status saver";
    private static final String CHANNEL_DESC= "status saver notification";
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_check_out_payment);

        near_place=getIntent().getExtras().getString("near_place");
        total_amt=getIntent().getExtras().getString("total_amt");
        delivery=getIntent().getExtras().getString("delivery");
        net_payment=getIntent().getExtras().getString("net_payment");
        phone=getIntent().getExtras().getString("phone");
        house_no=getIntent().getExtras().getString("house_no");
        colony=getIntent().getExtras().getString("colony");
        zip=getIntent().getExtras().getString("zip");
        city=getIntent().getExtras().getString("city");
        state=getIntent().getExtras().getString("state");
        lat=getIntent().getExtras().getDouble("lat");
        log=getIntent().getExtras().getDouble("log");
        near_addres=findViewById(R.id.near_addres);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();



        shiping_address=findViewById(R.id.shiping_address);


        total_amt_pay = findViewById(R.id.total_payment_layout_txt);
        delivery_amt_pay = findViewById(R.id.delivery_payment_layout_txt);
        net_amt_pay = findViewById(R.id.net_pay_amt_payment_layout_txt);
        add_itme_pay = findViewById(R.id.add_more_items1);
        cash_on_delivery_check_box = findViewById(R.id.cash_on_deliver);
        online_payment_check_box = findViewById(R.id.pay_online);
        advance_amount_pay = findViewById(R.id.adavance_amount);
        advance_amount_layout = findViewById(R.id.advance_amount_layout);
        cod_notice_layout = findViewById(R.id.cod_notice);
        remain_amount_pay = findViewById(R.id.remain_amount);
        total_pay_txt = findViewById(R.id.total_pay_txt);
        pay_bt=findViewById(R.id.pay_bt);

        shiping_address.setText(house_no+" "+colony+" "+city+" "+state+" "+" "+zip);

        total_amt_pay.setText(total_amt);
        delivery_amt_pay.setText(delivery);
        net_amt_pay.setText(net_payment);
        near_addres.setText(near_place);

        FCMSend.SetServerKey(serverKey);
        notification_chanal_id_version_check();

        add_itme_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(check_out_payment.this, kirana_home_main_act.class);
                startActivity(intent);
                finish();


            }
        });



        pay_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(payment_method_st.contentEquals("online")){

//                    Date c = Calendar.getInstance().getTime();
//                    SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault());
//                    String transcId = df.format(c);
//
//                    String amount = "1.00";
//                    String upi = "8130652073@ibl";
//                    String name = "neetu";
//                    String desc = "test";
//                    // on below line we are validating our text field.
//                    if (TextUtils.isEmpty(amount) && TextUtils.isEmpty(upi) && TextUtils.isEmpty(name) && TextUtils.isEmpty(desc)) {
//                        Toast.makeText(check_out_payment.this, "Please enter all the details..", Toast.LENGTH_SHORT).show();
//                    } else {
//                        // if the edit text is not empty then
//                        // we are calling method to make payment.
//                        makePayment(amount, upi, name, desc, transcId);
//                    }

                    after_payment();

                }else if(payment_method_st.contentEquals("cod")){

//                    Date c = Calendar.getInstance().getTime();
//                    SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault());
//                    String transcId = df.format(c);
//
//                    String amount = "1.00";
//                    String upi = "8130652073@ibl";
//                    String name = "neetu";
//                    String desc = "test";
//                    // on below line we are validating our text field.
//                    if (TextUtils.isEmpty(amount) && TextUtils.isEmpty(upi) && TextUtils.isEmpty(name) && TextUtils.isEmpty(desc)) {
//                        Toast.makeText(check_out_payment.this, "Please enter all the details..", Toast.LENGTH_SHORT).show();
//                    } else {
//                        // if the edit text is not empty then
//                        // we are calling method to make payment.
//                        makePayment(amount, upi, name, desc, transcId);
//                    }

                    after_payment();

                }else {

                    Toast.makeText(getApplicationContext(), "Please Select Payment Method", Toast.LENGTH_SHORT).show();
                }





            }

        });





        online_payment_check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (online_payment_check_box.isChecked()) {

                    cash_on_delivery_check_box.setChecked(false);
                    payment_method_st = "online";

                    advance_amount_layout.setVisibility(View.GONE);


                    cod_notice_layout.setVisibility(View.GONE);
                    total_amt_pay.setText(total_amt);
                    delivery_amt_pay.setText(delivery);
                    net_amt_pay.setText(net_payment);
                    remaining_amount_st="0";




                }


            }
        });

        cash_on_delivery_check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (cash_on_delivery_check_box.isChecked()) {

                    online_payment_check_box.setChecked(false);

                    payment_method_st = "cod";

                    total_pay_txt.setText("Total Amount");

                    advance_amount_layout.setVisibility(View.VISIBLE);


                    cod_notice_layout.setVisibility(View.VISIBLE);
                    advance_amount_pay.setText("-" + "60");
                    remaining_amount_st=String.valueOf(Integer.parseInt(total_amt) - 60);
                    remain_amount_pay.setText(String.valueOf(Integer.parseInt(total_amt) - 60));


                }


            }
        });













    }







    private void makePayment(String amount, String upi, String name, String desc, String transactionId) {
        // on below line we are calling an easy payment method and passing
        // all parameters to it such as upi id,name, description and others.
        final EasyUpiPayment easyUpiPayment = new EasyUpiPayment.Builder()
                .with(this)
                // on below line we are adding upi id.
                .setPayeeVpa(upi)
                // on below line we are setting name to which we are making payment.
                .setPayeeName(name)
                // on below line we are passing transaction id.
                .setTransactionId(transactionId)
                // on below line we are passing transaction ref id.
                .setTransactionRefId(transactionId)
                // on below line we are adding description to payment.
                .setDescription(desc)
                // on below line we are passing amount which is being paid.
                .setAmount(amount)
                // on below line we are calling a build method to build this ui.
                .build();
        // on below line we are calling a start
        // payment method to start a payment.
        easyUpiPayment.startPayment();
        // on below line we are calling a set payment
        // status listener method to call other payment methods.
        easyUpiPayment.setPaymentStatusListener(this);
    }
    @Override
    public void onTransactionCompleted(TransactionDetails transactionDetails) {
        String transcDetails = transactionDetails.getStatus().toString() + "\n" + "Transaction ID : " + transactionDetails.getTransactionId();
        Toast.makeText(getApplicationContext(), "trangection complete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionSuccess() {
        Toast.makeText(this, "Transaction successfully completed..", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTransactionSubmitted() {

    }

    @Override
    public void onTransactionFailed() {
        Toast.makeText(this, "Failed to complete transaction", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTransactionCancelled() {
        Toast.makeText(this, "Transaction cancelled..", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAppNotFound() {
        Toast.makeText(this, "No app found for making transaction..", Toast.LENGTH_SHORT).show();

    }



    public  void after_payment(){
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait......");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        progressDialog.show();

        Query firstQuery = firebaseFirestore.collection("kirna_Add_cart")
                .document(firebaseAuth.getUid().toString()).collection("cart")
                .whereEqualTo("order_status", "cart");


        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot documentSnapshot=task.getResult();
                String store_uid=documentSnapshot.getString("store_uid");
                String store_address=documentSnapshot.getString("store_address");
                String store_message_token=documentSnapshot.getString("shop_message_token");
                String store_phone=documentSnapshot.getString("store_phone");
                String user_name_st=documentSnapshot.getString("name");

                String order_id_st=getRandomString(5);

                firstQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot querySnapshot=task.getResult();

                if (querySnapshot.isEmpty()) {

                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "cart is empty", Toast.LENGTH_SHORT).show();
                } else {

                    for (int i = 0; i < querySnapshot.size(); i++) {

                        Map<String, Object> map = new HashMap<>();


                        map.put("order_status", "Ordered");
                        map.put("user_uid",firebaseAuth.getUid().toString());
                        map.put("store_uid",store_uid);
                        map.put("order_id",order_id_st);

                        final int in=i;




                        firebaseFirestore.collection("kirana_shopkeeper_product")
                                .document(store_uid)
                                .collection("proudcts")
                                .document((String) querySnapshot.getDocuments().get(in).get("product_id")).get()
                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {



                                        DocumentSnapshot documentSnapshot1=task.getResult();

                                        String stock_st=documentSnapshot1.getString("stock");

                                        if(Integer.parseInt(stock_st)>Integer.parseInt((String) querySnapshot.getDocuments().get(in).get("quantity"))){


                                            firebaseFirestore.collection("kirna_Add_cart")
                                                    .document(firebaseAuth.getUid().toString()).collection("cart")
                                                    .document((String) querySnapshot.getDocuments().get(in).get("item_id")).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {



                                                            firebaseFirestore.collection("kirana_shopkeeper_product")
                                                                    .document(store_uid)
                                                                    .collection("proudcts")
                                                                    .document((String) querySnapshot.getDocuments().get(in).get("product_id"))
                                                                    .update("stock",String.valueOf(Integer.parseInt(stock_st)-Integer.parseInt((String) querySnapshot.getDocuments().get(in).get("quantity")))).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void unused) {




                                                                        }
                                                                    });




                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {

                                                            Toast.makeText(check_out_payment.this, "Failed", Toast.LENGTH_SHORT).show();



                                                        }
                                                    });




                                        }else {

                                            Toast.makeText(getApplicationContext(),
                                                    querySnapshot.getDocuments().get(in).get("name_product")+"You Can Order Only  "+
                                                            "Quantity "+stock_st+"Of this product"
                                                    , Toast.LENGTH_SHORT).show();

                                            return;


                                        }



                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Toast.makeText(check_out_payment.this, "Store Data is not Updated", Toast.LENGTH_SHORT).show();

                                    }

                                });


                        if (i == querySnapshot.size() - 1)  {

                            Map<String, Object> map2= new HashMap<>();


                            map2.put("order_status", "Ordered");
                            map2.put("user_uid",firebaseAuth.getUid().toString());
                            map2.put("store_uid",store_uid);
                            map2.put("order_id",order_id_st);

                            firebaseFirestore.collection("kirna_Add_cart")
                                    .document(firebaseAuth.getUid().toString()).collection("cart")
                                    .document((String) querySnapshot.getDocuments().get(in).get("item_id")).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            DatePicker datePicker = new DatePicker(getApplicationContext());
                                            String current_date = datePicker.getDayOfMonth() + "/" + datePicker.getMonth() + "/" + datePicker.getYear();


                                            if(payment_method_st.contentEquals("online")){


                                                Map<String,Object>map2=new HashMap<>();
                                                map2.put("name",user_name_st);
                                                map2.put("total_amt", total_amt);
                                                map2.put("delivery", delivery);
                                                map2.put("net_payment", net_payment);
                                                map2.put("phone", phone);
                                                map2.put("house_no", house_no);
                                                map2.put("colony", colony);
                                                map2.put("pin",zip);
                                                map2.put("state",state);
                                                map2.put("order_date",current_date);
                                                map2.put("custmer_uid",firebaseAuth.getCurrentUser().getUid().toString());
                                                map2.put("lat",String.valueOf(lat));
                                                map2.put("lon",String.valueOf(log));
                                                map2.put("near_place",near_place);
                                                map2.put("store_uid",store_uid);
                                                map2.put("No_of_item",String.valueOf(querySnapshot.size()));
                                                map2.put("order_status","Ordered");
                                                map2.put("payment_method",payment_method_st);
                                                map2.put("remaing_amount",remaining_amount_st);
                                                map2.put("order_id",order_id_st);
                                                map2.put("cart_id",querySnapshot.getDocuments().get(in).get("item_id"));
                                                map2.put("pickup_otp",getRandomString(4));
                                                map2.put("delivered_otp",getRandomString(4));
                                                map2.put("pickup_status","no_pickup");
                                                map2.put("store_address",store_address);
                                                map2.put("store_message_token",store_message_token);
                                                map2.put("store_phone",store_phone);


                                                map2.put("timestamp", FieldValue.serverTimestamp());

                                                firebaseFirestore.collection("kirana_order_list")
                                                        .document().set(map2).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void unused) {


                                                                notification_send(store_message_token,total_amt);

                                                                Intent intent=new Intent(check_out_payment.this,Thanks_place_order.class);
                                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                                startActivity(intent);
                                                                finish();
                                                                progressDialog.dismiss();


                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {


                                                                Toast.makeText(getApplicationContext(), "task failded", Toast.LENGTH_SHORT).show();


                                                            }
                                                        });


                                            }else if(payment_method_st.contentEquals("cod")){

                                                Map<String,Object>map3=new HashMap<>();
                                                map3.put("name",user_name_st);
                                                map3.put("total_amt", total_amt);
                                                map3.put("delivery", delivery);
                                                map3.put("net_payment", net_payment);
                                                map3.put("phone", phone);
                                                map3.put("house_no", house_no);
                                                map3.put("colony", colony);
                                                map3.put("pin",zip);
                                                map3.put("state",state);
                                                map3.put("order_date",current_date);
                                                map3.put("custmer_uid",firebaseAuth.getCurrentUser().getUid().toString());
                                                map3.put("lat",String.valueOf(lat));
                                                map3.put("lon",String.valueOf(log));
                                                map3.put("near_place",near_place);
                                                map3.put("store_uid",store_uid);
                                                map3.put("No_of_item",String.valueOf(querySnapshot.size()));
                                                map3.put("order_status","Ordered");
                                                map3.put("payment_method",payment_method_st);
                                                map3.put("remaing_amount",String.valueOf(Integer.parseInt(net_payment) - 60));
                                                map3.put("order_id",order_id_st);
                                                map3.put("cart_id",querySnapshot.getDocuments().get(in).get("item_id"));
                                                map3.put("timestamp", FieldValue.serverTimestamp());
                                                map3.put("withdraw_status","no_requested");
                                                map3.put("pickup_otp",getRandomString(4));
                                                map3.put("delivered_otp",getRandomString(4));
                                                map3.put("pickup_status","no_pickup");
                                                map3.put("store_address",store_address);
                                                map3.put("store_message_token",store_message_token);
                                                map3.put("store_phone",store_phone);


                                                firebaseFirestore.collection("kirana_order_list")
                                                        .document().set(map3).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void unused) {




                                                                notification_send(store_message_token,total_amt);

                                                                Intent intent=new Intent(check_out_payment.this,Thanks_place_order.class);

                                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                                startActivity(intent);
                                                                finish();
                                                                progressDialog.dismiss();



                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {


                                                                Toast.makeText(getApplicationContext(), "task failded", Toast.LENGTH_SHORT).show();


                                                            }
                                                        });

                                            }

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                        progressDialog.dismiss();
                                            Toast.makeText(check_out_payment.this, "not get data", Toast.LENGTH_SHORT).show();

                                        }
                                    });





                        }







                    }




                }







                    }
                });





            }
        });


    }


    private static final String ALLOWED_CHARACTERS ="0123456789";

    private static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }


    private void notification_send(String token,String total_amt_st) {

        FirebaseMessaging.getInstance().subscribeToTopic(token).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                System.out.println("Subscription successful");
            }
        });



        FCMSend.Builder build1 = new FCMSend.Builder(token)
                .setTitle("You Have New Order")
                .setBody("You Recive Order Rs"+total_amt_st);

        build1.send();


        String result2 = build1.send().Result();


        if(ChekSuccess( result2).contentEquals("Success")){


        }else {



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

    private void notification_chanal_id_version_check() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription(CHANNEL_DESC);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

        }


    }



    }
