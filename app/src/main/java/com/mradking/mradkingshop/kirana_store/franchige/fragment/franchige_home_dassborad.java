package com.mradking.mradkingshop.kirana_store.franchige.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.intface.Firbase_Doc_Call;
import com.mradking.mradkingshop.kirana_store.custmer.intface.Firbase_Query_Call;
import com.mradking.mradkingshop.kirana_store.custmer.intface.Get_future_date_Call;
import com.mradking.mradkingshop.kirana_store.custmer.intface.Get_remaing_days;
import com.mradking.mradkingshop.kirana_store.custmer.utility.utilityX;
import com.mradking.mradkingshop.kirana_store.franchige.activity.Renew_account_act;

import java.util.HashMap;
import java.util.Map;

public class franchige_home_dassborad extends Fragment {

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    utilityX UtilityX;
    TextView earning,team,custmer,withdraw,start_date,end_date,reaming_days;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.kirana_frenchige_dasboard_home, container, false);

        insligze(view);

        dataset();





        return view;
    }

    private void dataset() {

        progressDialog.setMessage("Please Wait.....");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        UtilityX.get_detalis("kirana_user_details", firebaseAuth.getCurrentUser().getUid(), new Firbase_Doc_Call() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                long partners_st=documentSnapshot.getLong("partners");


                long coins = documentSnapshot.getLong("coins");

                long total_cutmer_st=documentSnapshot.getLong("custmers");
                String start_sub=documentSnapshot.getString("starting_date_sub");
                String end_date_sub=documentSnapshot.getString("end_date_sub");

                Toast.makeText(getActivity(), firebaseAuth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();

                earning.setText(String.valueOf(coins));
                team.setText(String.valueOf(partners_st));
                custmer.setText(String.valueOf(total_cutmer_st));
                start_date.setText(start_sub);
                end_date.setText(end_date_sub);

                UtilityX.days_remain(end_date_sub, getActivity(), new Get_remaing_days() {
                    @Override
                    public void sussess(String days) {

                        if(Integer.parseInt(days)<2){

                            renew_now_set();
                            reaming_days.setText(days);

                        }else {

                            reaming_days.setText(days);
                            wtihd_amt_total();
                            progressDialog.dismiss();


                        }


                    }

                    @Override
                    public void fail(String erros) {

                        Toast.makeText(getActivity(), "remaing days not found", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                Log.e("pawan",error);

            }
        });

    }

    private void wtihd_amt_total() {
        Query query=firebaseFirestore.collection("franchige_withdrawing_list")
                .whereEqualTo("user_uid_french",firebaseAuth.getCurrentUser().getUid());
        UtilityX.get_doc_query(query, new Firbase_Query_Call() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshot) {

                int total_sale=0;
                for(int i = 0; i < documentSnapshot.getDocuments().size(); i++){


                    total_sale +=  Integer.parseInt((String) documentSnapshot.getDocuments().get(i).get("total_amt"));

                }

                withdraw.setText(String.valueOf(total_sale));


            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    private void renew_now_set() {

        Map<String,Object>map=new HashMap<>();
        map.put("buying_status","renew");

        firebaseFirestore.collection("kirana_user_details")
                .document(firebaseAuth.getCurrentUser().getUid()).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Intent intent=new Intent(getActivity(), Renew_account_act.class);
                        startActivity(intent);
                        getActivity().finish();
                        progressDialog.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        progressDialog.dismiss();
                    }
                });


    }

    private void insligze(View view) {
         UtilityX=new utilityX(getActivity());
        earning=view.findViewById(R.id.earning);
        team=view.findViewById(R.id.team);
        custmer=view.findViewById(R.id.custmer);
        withdraw=view.findViewById(R.id.withdraw);
        start_date=view.findViewById(R.id.start_date);
        end_date=view.findViewById(R.id.end_date);
        reaming_days=view.findViewById(R.id.reaming_days);
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        progressDialog=new ProgressDialog(getActivity());
    }

}
