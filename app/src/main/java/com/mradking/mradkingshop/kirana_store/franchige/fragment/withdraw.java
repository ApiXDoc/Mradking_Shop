package com.mradking.mradkingshop.kirana_store.franchige.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.franchige.activity.withdraw_investment_main;


import java.util.HashMap;
import java.util.Map;

public class withdraw extends Fragment {

    ProgressDialog progressDialog;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    EditText enter_phonepe_number,amount;
    Button withdraw_bt;
    TextView total_earning;
    String total_earning_st;
    long total_coins;
    TextView bottom_text;
    long french_minimum_withdraw_limit,french_amount_tranfer_day;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.withdral_layout, container, false);


        progressDialog=new ProgressDialog(getActivity());
        firebaseFirestore =FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        enter_phonepe_number=root.findViewById(R.id.enter_phonepe_number);
        amount=root.findViewById(R.id.amount);
        withdraw_bt=root.findViewById(R.id.withdraw_bt);
        total_earning=root.findViewById(R.id.total_earning);
        bottom_text=root.findViewById(R.id.bottom_text);


        set_data();



        withdraw_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(amount.getText().toString())){

                    Toast.makeText(getActivity(), "Please Enter Your Amount", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(enter_phonepe_number.getText().toString())){

                    Toast.makeText(getActivity(), "Please Enter Your PhonePe Number", Toast.LENGTH_SHORT).show();
                }else {

                    if(enter_phonepe_number.getText().toString().length()<10){

                        Toast.makeText(getActivity(), "Check Your Number Once Again", Toast.LENGTH_SHORT).show();

                    }else {

                        add_withdraw_request();

                    }


                }

            }
        });




    return root;
    }

    private void add_withdraw_request() {

        if(Integer.parseInt(amount.getText().toString())>=french_minimum_withdraw_limit){

            if(Integer.parseInt(total_earning_st)<Integer.parseInt(amount.getText().toString())){

                Toast.makeText(getActivity(), "Your Amount is Greter Than Your Earning", Toast.LENGTH_SHORT).show();

            }else {

                progressDialog.setMessage("Please Wait........");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                Map<String,Object> map=new HashMap<>();
                long remaing_coins=Integer.parseInt(total_earning_st)- Integer.parseInt(amount.getText().toString());

                map.put("coins", remaing_coins);


                firebaseFirestore.collection("kirana_user_details")
                        .document(firebaseAuth.getCurrentUser().getUid()).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        DatePicker datePicker=new DatePicker(getActivity());
                        String current_date = datePicker.getDayOfMonth() + "/" + datePicker.getMonth()+1 + "/" + datePicker.getYear();

                        Map<String,Object>map1=new HashMap<>();
                        map1.put("withdraw_date_french",current_date);
                        map1.put("Payout_status_french","InProcess");
                        map1.put("withdraing_amount_french",amount.getText().toString());
                        map1.put("user_uid_french",firebaseAuth.getCurrentUser().getUid());
                        map1.put("phone_number_french",enter_phonepe_number.getText().toString());
                        map1.put("timestamp", FieldValue.serverTimestamp());

                        firebaseFirestore.collection("franchige_withdrawing_list").document().set(map1).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                Toast.makeText(getActivity(), "you Withdraw Request have been Summited", Toast.LENGTH_SHORT).show();
                                amount.setText("");
                                enter_phonepe_number.setText("");

                                Intent intent=new Intent(getActivity(), withdraw_investment_main.class);
                                startActivity(intent);
                                getActivity().finish();
                                progressDialog.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(getActivity(), "you Withdraw Request is not Summited", Toast.LENGTH_SHORT).show();


                                progressDialog.dismiss();
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getActivity(), "you Withdraw Request is not Summited", Toast.LENGTH_SHORT).show();


                        progressDialog.dismiss();

                    }
                });




            }


        }else {

            Toast.makeText(getActivity(), "Minimum Withdraw Ammount is 500", Toast.LENGTH_SHORT).show();

        }

    }

    private void set_data() {

        progressDialog.setMessage("Please Wait........");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        firebaseFirestore.collection("kirana_user_details")
                .document(firebaseAuth.getCurrentUser().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                DocumentSnapshot documentSnapshot=task.getResult();

                total_coins=documentSnapshot.getLong("coins");

                total_earning_st=String.valueOf(total_coins);
                total_earning.setText("Total Earning:- "+"₹"+total_earning_st);


                firebaseFirestore.collection("Code").document("1").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                        DocumentSnapshot documentSnapshot1=task.getResult();

                         french_minimum_withdraw_limit=documentSnapshot1.getLong("french_minimum_withdraw_limit");
                        french_amount_tranfer_day=documentSnapshot1.getLong("french_amount_tranfer_day");
                        bottom_text.setText("Minimum withdraw Rs "+ String.valueOf(french_minimum_withdraw_limit) +"\n Your Payout Process Within "+ String.valueOf(french_amount_tranfer_day) +" days");

                        progressDialog.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getActivity(), "data not geted", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });




            }
        });

    }
}
