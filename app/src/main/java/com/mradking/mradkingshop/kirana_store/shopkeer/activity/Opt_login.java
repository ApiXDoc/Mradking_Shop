package com.mradking.mradkingshop.kirana_store.shopkeer.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.mradking.mradkingshop.R;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Opt_login extends Activity {

    private PinView pinView;
    private Button next,verifed_bt;
    private TextView topText,textU;
    private EditText userName, userPhone;
    private ConstraintLayout first, second;
    FirebaseAuth mAuth;
    String codeSent;
    ProgressDialog progressDialog;
    FirebaseFirestore firebaseFirestore;
    static String current_date;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_verfication_login_layout);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();


        topText = findViewById(R.id.topText);
        pinView = findViewById(R.id.pinView);
        next = findViewById(R.id.button);
        verifed_bt=findViewById(R.id.verifed_bt);
        userName = findViewById(R.id.username);
        userPhone = findViewById(R.id.userPhone);
        first = findViewById(R.id.first_step);
        second = findViewById(R.id.secondStep);
        textU = findViewById(R.id.textView_noti);
        progressDialog=new ProgressDialog(this);


        current_date=getIntent().getExtras().getString("cr_date");




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String mobileNum = userPhone.getText().toString();

                if (mobileNum.isEmpty()){
                    userPhone.setError("Please enter Mobile number");
                    userPhone.requestFocus();
                    return;
                }else {
                    getVerificationCode();
                }
                next.setVisibility(View.GONE);
                first.setVisibility(View.GONE);
                second.setVisibility(View.VISIBLE);
                verifed_bt.setVisibility(View.VISIBLE);
                userPhone.setFocusable(false);


            }


        });

        verifed_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Please Wait........");
                progressDialog.show();
                progressDialog.setCanceledOnTouchOutside(false);
                String codeText = pinView.getText().toString();
                if (codeText.isEmpty()){
                    pinView.setError("Please enter verification Code");
                    pinView.requestFocus();
                    return;
                }else {
                    codeVerification();
                }

            }
        });


    }

    private void getVerificationCode() {

        String phoneNumber = "+91"+userPhone.getText().toString();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks


    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {


        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

            if (e instanceof FirebaseAuthInvalidCredentialsException) {

                // ...
            } else if (e instanceof FirebaseTooManyRequestsException) {

            }


            // ...
        }@Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            codeSent = verificationId;

        }
    };

    public void codeVerification (){
        String code = pinView.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
        signInWithPhoneAuthCredential(credential);

    } private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            firebaseFirestore.collection("ShoprKeeper_detail").document(mAuth.getUid().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                                    DocumentSnapshot documentSnapshot=task.getResult();

                                    if(documentSnapshot.exists()){

                                        Toast.makeText(Opt_login.this, "Login Successfull"+mAuth.getUid().toString(), Toast.LENGTH_SHORT).show();

                                        Intent intent=new Intent(Opt_login.this, Shop_Details_Form.class);

                                        startActivity(intent);




                                    }else {


                                        Map<String,Object> map=new HashMap<>();

                                        map.put("name",userName.getText().toString());
                                        map.put("phone",userPhone.getText().toString());
                                        map.put("uid",mAuth.getUid().toString());

                                        firebaseFirestore.collection("ShoprKeeper_detail").document(mAuth.getUid().toString()).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                Intent intent=new Intent(Opt_login.this,Shop_Details_Form.class);
                                                startActivity(intent);

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                                Toast.makeText(Opt_login.this, "Login Failed try again", Toast.LENGTH_SHORT).show();


                                            }
                                        });




                                    }


                                }
                            });



                        } else {
                            Toast.makeText(Opt_login.this, "Login Failed", Toast.LENGTH_SHORT).show();

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(Opt_login.this, "You entered a wring code", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}
