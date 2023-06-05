package com.mradking.mradkingshop.kirana_store.custmer.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.chaos.view.PinView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mradking.mradkingshop.R;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Otp_loging_act extends AppCompatActivity {

    private PinView pinView;
    private Button next,verifed_bt;
    private TextView topText,textU,resend_txt;
    private CountDownTimer resendOTPTimer;
    private EditText userName, userPhone;
    private ConstraintLayout first, second;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    FirebaseFirestore firebaseFirestore;
    FusedLocationProviderClient client;
    private String verificationId;
    int count=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_verfication_login_layout);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        client = LocationServices.getFusedLocationProviderClient(getApplicationContext());

        pinView = findViewById(R.id.pinView);
        next = findViewById(R.id.button);
        verifed_bt=findViewById(R.id.verifed_bt);
        userName = findViewById(R.id.username);
        userPhone = findViewById(R.id.userPhone);
        first = findViewById(R.id.first_step);
        second = findViewById(R.id.secondStep);
        textU = findViewById(R.id.textView_noti);
        progressDialog=new ProgressDialog(this);
        resend_txt=findViewById(R.id.resend_txt);





        // setting onclick listener for generate OTP button.
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // below line is for checking whether the user
                // has entered his mobile number or not.
                if (TextUtils.isEmpty(userPhone.getText().toString())) {
                    // when mobile number text field is empty
                    // displaying a toast message.
                    Toast.makeText(Otp_loging_act.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                } else {
                    // if the text field is not empty we are calling our
                    // send OTP method for getting OTP from Firebase.
                    next.setVisibility(View.GONE);
                    first.setVisibility(View.GONE);
                    second.setVisibility(View.VISIBLE);
                    verifed_bt.setVisibility(View.VISIBLE);
                    userPhone.setFocusable(false);
                    String phone = "+91" + userPhone.getText().toString();
                    sendVerificationCode(phone);
                    textU.setText("Enter the 6 digit OTP"+" Number:-"+userPhone.getText().toString());

                    set_timmer();
                    resendOTPTimer.start();

                }
            }
        });

        // initializing on click listener
        // for verify otp button
        verifed_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==1){

                // validating if the OTP text field is empty or not.
                if (TextUtils.isEmpty(pinView.getText().toString())) {
                    // if the OTP text field is empty display
                    // a message to user to enter OTP
                    Toast.makeText(Otp_loging_act.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    // if OTP field is not empty calling
                    // method to verify the OTP.
                    verifyCode(pinView.getText().toString());
                }

                }else if(count==0){
                    next.setVisibility(View.GONE);
                    first.setVisibility(View.GONE);
                    second.setVisibility(View.VISIBLE);
                    verifed_bt.setVisibility(View.VISIBLE);
                    userPhone.setFocusable(false);
                    String phone = "+91" + userPhone.getText().toString();
                    sendVerificationCode(phone);
                    textU.setText("Enter the 6 digit OTP"+" Number:-"+userPhone.getText().toString());

                    set_timmer();
                    resendOTPTimer.start();
           }
            }
        });
    }

    private void set_timmer() {

        resendOTPTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update UI with the remaining time
                int secondsRemaining = (int) (millisUntilFinished / 1000);
                // For example, update a TextView with the remaining time
                resend_txt.setText("Didn't get the OTP? RESEND OTP. \n Wait.." + secondsRemaining + " seconds");
                verifed_bt.setFocusable(false);
            }

            @Override
            public void onFinish() {
                // Enable the resend button or do any other action
//                buttonResendOTP.setEnabled(true);
                resend_txt.setText("RESEND OTP");
                verifed_bt.setText("Verify");
                count=0;
                verifed_bt.setFocusable(true);

            }
        };

    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        // inside this method we are checking if
        // the code entered is correct or not.
        progressDialog.setMessage("Please Wait.......");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();


        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            firebaseFirestore.collection("kirana_user_details").document(mAuth.getUid().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                                    DocumentSnapshot documentSnapshot=task.getResult();


                                    if(documentSnapshot.exists()){

                                        String shop_selection_status =documentSnapshot.getString("shop_selection_status");


                                        if(shop_selection_status.contentEquals("no")){

                                            Intent intent=new Intent(Otp_loging_act.this, Kiranan_shopes_list.class);

                                            startActivity(intent);
                                            finish();

                                        }else if(shop_selection_status.contentEquals("yes")){

                                            String store_uid =documentSnapshot.getString("store_uid");
                                            Intent intent=new Intent(Otp_loging_act.this, kirana_home_main_act.class);
                                            intent.putExtra("store_id",store_uid);
                                            startActivity(intent);
                                            finish();

                                        }




                                        Intent intent=new Intent(Otp_loging_act.this, Kiranan_shopes_list.class);
//                                        intent.putExtra("cr_date",current_date);
                                        startActivity(intent);
                                        finish();


                                        progressDialog.dismiss();

                                    }else {

                                        Toast.makeText(Otp_loging_act.this, "this", Toast.LENGTH_SHORT).show();

                                        Task<Location>location_task=client.getLastLocation();

                                        location_task.addOnSuccessListener(new OnSuccessListener<Location>() {
                                            @Override
                                            public void onSuccess(Location location) {

                                                if(location!=null){



                                                    FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<String> task) {
                                                            if (!task.isSuccessful()) {
                                                                return;
                                                            }
                                                            String token = task.getResult();


                                                            Map<String,Object> map=new HashMap<>();

                                                            map.put("name",userName.getText().toString());
                                                            map.put("phone","+91"+userPhone.getText().toString());
                                                            map.put("uid",mAuth.getUid().toString());
                                                            map.put("store_uid","no");
                                                            map.put("lat",String.valueOf(location.getLatitude()));
                                                            map.put("lon",String.valueOf(location.getLongitude()));
                                                            map.put("user_message_token",token);
                                                            map.put("month_list_status","no");

                                                            map.put("refer_code","+91"+userPhone.getText().toString());
                                                            map.put("refer_uid","");
                                                            map.put("refer_message_token","");

                                                            map.put("level","1");
                                                            map.put("level_0_id","+918130340674");
                                                            map.put("level_1_id","1");
                                                            map.put("level_2_id","2");
                                                            map.put("level_3_id","3");
                                                            map.put("level_4_id","4");
                                                            map.put("level_5_id","5");
                                                            map.put("level_6_id","6");

                                                            map.put("refer_status","no");
                                                            map.put("coins",0);
                                                            map.put("custmers",0);
                                                            map.put("partners",0);
                                                            map.put("starting_date_sub","0");
                                                            map.put("end_date_sub","0");
                                                            map.put("buying_status","no");
                                                            map.put("shop_selection_status","no");



                                                            firebaseFirestore.collection("kirana_user_details").document(mAuth.getUid().toString()).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {

                                                                    Map<String,Object>map1=new HashMap<>();
                                                                    map1.put("refer_uid",mAuth.getCurrentUser().getUid());
                                                                    map1.put("user_message_token",token);
                                                                    map1.put("level","1");

                                                                    firebaseFirestore.collection("kirana_user_Uid_phone")
                                                                            .document(mAuth.getCurrentUser().getPhoneNumber()).set(map1)
                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Void> task) {

                                                                                    SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                                                                    SharedPreferences.Editor editor = preferences.edit();
                                                                                    editor.putString("sign_status", "login");
                                                                                    editor.apply();
                                                                                    editor.commit();



                                                                                    Intent intent=new Intent(Otp_loging_act.this,Refer_code_act.class);
//
                                                                                    startActivity(intent);
                                                                                    finish();
                                                                                    progressDialog.dismiss();

                                                                                }
                                                                            });


                                                                }
                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {

                                                                    Toast.makeText(Otp_loging_act.this, "Login Failed try again", Toast.LENGTH_SHORT).show();


                                                                }
                                                            });


                                                        }
                                                    });





                                                }else {

                                                    Toast.makeText(getApplicationContext(), "location is null first open google map then try aging", Toast.LENGTH_SHORT).show();

                                                }


                                            }
                                        });






                                    }


                                }
                            });



                        } else {
                            // if the code is not correct then we are
                            // displaying an error message to the user.
                            Toast.makeText(Otp_loging_act.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    private void sendVerificationCode(String number) {
        // this method is used for getting
        // OTP on user phone number.
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)		 // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)				 // Activity (for callback binding)
                        .setCallbacks(mCallBack)		 // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    // callback method is called on Phone auth provider.
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks

            // initializing our callbacks for on
            // verification callback method.
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        // below method is used when
        // OTP is sent from Firebase
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            // when we receive the OTP it
            // contains a unique id which
            // we are storing in our string
            // which we have already created.
            verificationId = s;
        }

        // this method is called when user
        // receive OTP from Firebase.
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            // below line is used for getting OTP code
            // which is sent in phone auth credentials.
            final String code = phoneAuthCredential.getSmsCode();

            // checking if the code
            // is null or not.
            if (code != null) {
                // if the code is not null then
                // we are setting that code to
                // our OTP edittext field.
                pinView.setText(code);

                // after setting this code
                // to OTP edittext field we
                // are calling our verifycode method.
                verifyCode(code);
            }
        }

        // this method is called when firebase doesn't
        // sends our OTP code due to any error or issue.
        @Override
        public void onVerificationFailed(FirebaseException e) {
            // displaying error message with firebase exception.
            Toast.makeText(Otp_loging_act.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    // below method is use to verify code from Firebase.
    private void verifyCode(String code) {
        // below line is used for getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithCredential(credential);
    }
}
