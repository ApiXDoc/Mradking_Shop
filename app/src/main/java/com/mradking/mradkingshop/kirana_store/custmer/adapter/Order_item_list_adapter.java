package com.mradking.mradkingshop.kirana_store.custmer.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;
import com.mradking.mradkingshop.kirana_store.custmer.Model.cart_list_modal;
import com.mradking.mradkingshop.kirana_store.custmer.intface.RecyclerView_home_list;

import java.util.List;

public class Order_item_list_adapter extends RecyclerView.Adapter<Order_item_list_adapter.ViewHolder> {

    public Context context;

    private FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    private List<CommonModal> pd;
    private List<cart_list_modal> productListFull;
    private RecyclerView_home_list recyclerView_home_list;

    public Order_item_list_adapter(Context context, List<CommonModal> pd) {
        this.context = context;
        this.pd = pd;
    }

    @NonNull
    @Override
    public Order_item_list_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kirana_add_cart_row, parent, false);
        context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Order_item_list_adapter.ViewHolder holder, int position) {
        holder.glide.with(context).load(pd.get(position).getProduct_image_url()).into(holder.product_image);
        holder.product_name.setText(pd.get(position).getName_product());
        holder.product_price.setText("Rs " + pd.get(position).getSalling_price());
        holder.quantity_txt.setText(pd.get(position).getQuantity());

    }

    @Override
    public int getItemCount() {
        return pd.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Glide glide;
        ImageView product_image;
        TextView product_name, product_price, add, minus, quantity_txt;
        View mView;
        int count = 1;
        FirebaseFirestore firebaseFirestore;
        FirebaseAuth firebaseAuth;

        ProgressDialog progressDialog;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;

            product_image = mView.findViewById(R.id.product_image);
            product_name = mView.findViewById(R.id.product_name);
            product_price = mView.findViewById(R.id.price);
            add = mView.findViewById(R.id.add);
            quantity_txt = mView.findViewById(R.id.quantity_txt);
            minus = mView.findViewById(R.id.minus);
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseFirestore = FirebaseFirestore.getInstance();
            progressDialog = new ProgressDialog(context);
        }
    }
}
