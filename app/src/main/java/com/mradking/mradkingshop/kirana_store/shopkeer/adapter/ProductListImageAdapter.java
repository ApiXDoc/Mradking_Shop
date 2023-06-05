package com.mradking.mradkingshop.kirana_store.shopkeer.adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.shopkeer.inface.product_list_interface;
import com.mradking.mradkingshop.kirana_store.shopkeer.modal.ProductListImageModal;

import java.util.List;

public class ProductListImageAdapter  extends RecyclerView.Adapter<ProductListImageAdapter.ViewHolder> {


    public Context context;
    private List<ProductListImageModal> pd;
    private FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    product_list_interface productListInterface;

    public ProductListImageAdapter(Context context, List<ProductListImageModal> pd, product_list_interface productListInterface) {
        this.context = context;
        this.pd = pd;
        this.productListInterface = productListInterface;
    }

    @NonNull
    @Override
    public ProductListImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kirana_product_image_list_row, parent, false);
        context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(context);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListImageAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.mrp.setText("MRP:- "+pd.get(position).getMarket_price());
        holder.product_name.setText(pd.get(position).getName_product());
        holder.glide.with(context).load(pd.get(position).getProduct_image_url()).into(holder.product_image);

        holder.add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productListInterface.aad_button_click(position);

            }
        });



    }

    @Override
    public int getItemCount() {
        return pd.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Glide glide;
        TextView mrp,product_name;
        ImageView product_image;
        EditText brand_et,quantiy_et;
        Button summit;
        CardView add_bt;
        View mView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;

            mrp=mView.findViewById(R.id.mrp);
            product_name=mView.findViewById(R.id.prodct_name);
            product_image=mView.findViewById(R.id.product_image);

            add_bt=mView.findViewById(R.id.add_bt);



        }
    }
}
