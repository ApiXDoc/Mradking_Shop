package com.mradking.mradkingshop.kirana_store.custmer.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;
import com.mradking.mradkingshop.kirana_store.custmer.adapter.Home_page_product_adapter;
import com.mradking.mradkingshop.kirana_store.custmer.intface.RecyclerView_home_list;

import java.util.ArrayList;
import java.util.List;

public class Bottom_cart_view_fragment extends Fragment implements RecyclerView_home_list {


    TextView total_amount_home;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    private List<CommonModal> productList;

    public Home_page_product_adapter latestGovtJobRecyclerAdapter;
    ProgressDialog progressDialog;
    public DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;
    private RecyclerView grocery_recyler_view;

    String store_uid ,category;
    View root;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        root = inflater.inflate(R.layout.kiran_store_home_cart_bottom_frg, container, false);
        total_amount_home=root.findViewById(R.id.total_home_amt);

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        data_set_up();
    return root;
    }

    public void data_set_up( ) {



        Query firstQuery = firebaseFirestore.collection("kirna_Add_cart")
                .document(firebaseAuth.getUid().toString()).collection("cart")
                .whereEqualTo("order_status", "cart").limit(10);

        firstQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot documentSnapshots=task.getResult();
                int total_sale=0;
                for(int i = 0; i < documentSnapshots.getDocuments().size(); i++){


                    total_sale +=  Integer.parseInt((String) documentSnapshots.getDocuments().get(i).get("total_amount"));

                }




             total_amount_home.setText("₹"+String.valueOf(total_sale));


            }
        });

    }


    @Override
    public void total_amount(int total_amt) {
        Toast.makeText(getActivity(), "this", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void visblity_cart_bar(int posion) {

    }

    @Override
    public void total_saving(int amt) {

    }

    @Override
    public void restart_act() {

    }
}
