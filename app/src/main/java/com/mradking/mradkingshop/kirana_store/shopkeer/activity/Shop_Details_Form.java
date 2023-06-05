package com.mradking.mradkingshop.kirana_store.shopkeer.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mradking.mradkingshop.R;

import java.util.HashMap;
import java.util.Map;

public class Shop_Details_Form  extends Activity {

EditText shop_name,shop_address,landmark;

CardView ok_bt;
ProgressDialog progressDialog;
FirebaseFirestore firebaseFirestore;
FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_shop_detail_form);

        shop_name=findViewById(R.id.name);
        shop_address=findViewById(R.id.address1);
        landmark=findViewById(R.id.address2);

        ok_bt=findViewById(R.id.ok_bt);


        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();



        ok_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setMessage("Please Wait........");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                FirebaseMessaging.getInstance().getToken()
                        .addOnCompleteListener(new OnCompleteListener<String>() {
                            @Override
                            public void onComplete(@NonNull Task<String> task) {
                                if (!task.isSuccessful()) {
                                    return;
                                }
                                String token = task.getResult();

                                Map<String,Object>map=new HashMap<>();
                                map.put("shop_name",shop_name.getText().toString());
                                map.put("shop_address",shop_address.getText().toString());
                                map.put("shop_landmark",landmark.getText().toString());
                                map.put("shop_message_token",token);

                                firebaseFirestore.collection("ShoprKeeper_detail").document(firebaseAuth.getUid().toString()).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {


                                        progressDialog.dismiss();
                                        Intent intent=new Intent(Shop_Details_Form.this,Get_Location.class);
                                        startActivity(intent);



                                    }
                                });




                            }
                        });





            }
        });






    }
}
