package com.mradking.mradkingshop.kirana_store.custmer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;
import com.mradking.mradkingshop.kirana_store.custmer.Model.ListModal;
import com.mradking.mradkingshop.kirana_store.custmer.adapter.Kiranan_shopes_list_adapter;
import com.mradking.mradkingshop.kirana_store.custmer.intface.Shop_list_interface;

import java.util.ArrayList;
import java.util.List;

public class Kiranan_shopes_list extends Activity implements Shop_list_interface {
    private RecyclerView cart_recycler_view;
    private List<CommonModal> productList;
    private Activity activity = Kiranan_shopes_list.this;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public Kiranan_shopes_list_adapter latestGovtJobRecyclerAdapter;
    public DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_shop_list_custmer);


        productList = new ArrayList<CommonModal>();

        cart_recycler_view = findViewById(R.id.cart_list);
        firebaseAuth = FirebaseAuth.getInstance();
         latestGovtJobRecyclerAdapter = new Kiranan_shopes_list_adapter(this, productList,this);
        firebaseFirestore = FirebaseFirestore.getInstance();



        Query firstQuery =  firebaseFirestore.collection
                ("ShoprKeeper_detail").orderBy
                ("index", Query.Direction.DESCENDING);

        ListModal listModal=new ListModal();
        listModal.get_list("yes",true,
                firstQuery,productList,cart_recycler_view,latestGovtJobRecyclerAdapter,this);




    }


    @Override
    public void item_click(int position) {

        Intent intent=new Intent(activity, kirana_home_main_act.class);
        intent.putExtra("store_id",productList.get(position).getUid());

       startActivity(intent);


    }
}
