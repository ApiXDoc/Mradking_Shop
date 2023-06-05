package com.mradking.mradkingshop.kirana_store.custmer.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.kirana_store.franchige.activity.Frenchige_dasboard;
import com.mradking.mradkingshop.kirana_store.franchige.activity.Plan_choess_act;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.franchige.activity.Renew_account_act;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SlideshowFragment extends Fragment {

    CardView start_bt,more_bt;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    Calendar startDate,endDate;

    String currentDate_st;
    TextView no_busiess_partner;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    { View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

    start_bt=(CardView)root.findViewById(R.id.start_bt);
    firebaseFirestore=FirebaseFirestore.getInstance();
    firebaseAuth=FirebaseAuth.getInstance();
    progressDialog=new ProgressDialog(getActivity());


    no_busiess_partner=(TextView)root.findViewById(R.id.no_of_bussiness_partner) ;
    more_bt=(CardView)root.findViewById(R.id.more_bt);



    start_bt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            data_check();

        }
    });



    return root;
    }

    private void data_check() {

        progressDialog.setMessage("Please Wait.......");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

            DocumentSnapshot documentSnapshot= task.getResult();

            String buying_status =documentSnapshot.getString("buying_status");

            if(buying_status.contentEquals("yes")){

                Intent intent=new Intent(getActivity(), Frenchige_dasboard.class);
                startActivity(intent);
                getActivity().finish();
                progressDialog.dismiss();

            }else if(buying_status.contentEquals("no")) {

                Intent intent=new Intent(getActivity(), Plan_choess_act.class);
                startActivity(intent);
                progressDialog.dismiss();

            }else if(buying_status.contentEquals("renew")) {

                Intent intent=new Intent(getActivity(), Renew_account_act.class);
                startActivity(intent);
                progressDialog.dismiss();

            }


            }
        });


    }


}
