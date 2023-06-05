package com.mradking.mradkingshop.kirana_store.custmer.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.intface.Firbase_Query_Call;
import com.mradking.mradkingshop.kirana_store.custmer.utility.utilityX;

public class Monthly_order_list extends Activity {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    TextView total_saving,total_amount,order_date,annual_saving;
    RelativeLayout raw;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monthly_order_list);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        progressDialog=new ProgressDialog(this);

        total_saving=findViewById(R.id.total_saving);
        total_amount=findViewById(R.id.total_amount);
        order_date=findViewById(R.id.order_date);
        annual_saving=findViewById(R.id.annual_saving);
        raw=findViewById(R.id.row);

        data_set_up();


        raw.setOnClickListener(view -> {

            Intent intent=new Intent(Monthly_order_list.this, Mothly_List.class);
            startActivity(intent);


        });


    }

    private void data_set_up() {

        progressDialog.setMessage("Please Wait.....");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        utilityX utilityX=new utilityX(this);

        Query firstQuery = firebaseFirestore.collection("kirna_Add_cart")
                .document(firebaseAuth.getUid()).collection("cart")
                .whereEqualTo("order_status", "month").whereEqualTo("list_type","month").limit(10);


        utilityX.get_doc_query(firstQuery, new Firbase_Query_Call() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshot) {

                int total_sale=0;
                int total_mrp=0;
                for(int i = 0; i < documentSnapshot.getDocuments().size(); i++){


                    total_sale +=  Integer.parseInt((String) documentSnapshot.getDocuments().get(i).get("total_amount"));
                    total_mrp +=  Integer.parseInt((String) documentSnapshot.getDocuments().get(i).get("market_price"));


                }


                int saving_int=total_mrp-total_sale;

                total_saving.setText("₹"+String.valueOf(saving_int));
                annual_saving.setText("₹"+String.valueOf(saving_int*12));
                total_amount.setText("₹"+String.valueOf(total_sale));

                order_date_set_up();

            }

            @Override
            public void onFailure(String error) {

                Toast.makeText(Monthly_order_list.this, error, Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void order_date_set_up() {

        firebaseFirestore.collection("kirana_user_details")
                .document(firebaseAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        DocumentSnapshot documentSnapshot=task.getResult();

                        String order_date_st=documentSnapshot.getString("modthly_order_date");




                            order_date.setText(order_date_st);



                        progressDialog.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        progressDialog.dismiss();
                        Toast.makeText(Monthly_order_list.this, "data is not set up", Toast.LENGTH_SHORT).show();

                    }
                });

    }
}
