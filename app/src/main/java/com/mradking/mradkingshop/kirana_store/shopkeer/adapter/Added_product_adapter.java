package com.mradking.mradkingshop.kirana_store.shopkeer.adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.shopkeer.inface.product_list_interface;
import com.mradking.mradkingshop.kirana_store.shopkeer.modal.AddedProductModel;


import java.util.List;

public class Added_product_adapter extends RecyclerView.Adapter<Added_product_adapter.ViewHolder> {
    public Context context;

    private FirebaseFirestore firebaseFirestore;

    FirebaseAuth firebaseAuth;

    private List<AddedProductModel> pd;
    private List<AddedProductModel> productListFull;
    ProgressDialog progressDialog;
    product_list_interface productListInterface;

    public Added_product_adapter(Context context, List<AddedProductModel> pd, product_list_interface productListInterface) {
        this.context = context;
        this.pd = pd;
        this.productListInterface = productListInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kirana_added_product_row, parent, false);
        context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(context);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.glide.with(context).load(pd.get(position).getProduct_image_url()).into(holder.product_image);

        holder.mrp.setText("₹"+pd.get(position).getMarket_price());
        holder.salling_price.setText("₹"+pd.get(position).getSalling_price());
        holder.stock.setText(pd.get(position).getStock());
        holder.weight.setText(pd.get(position).getQuantity());
        holder.product_name.setText(pd.get(position).getName_product());
        holder.varition.setText(pd.get(position).getVarition_status());
        holder.tableLayout.setVisibility(View.VISIBLE);

        if(pd.get(position).getVarition_status().contentEquals("no")){


            holder.tableLayout.setVisibility(View.GONE);
        }else if(pd.get(position).getVarition_status().contentEquals("yes")){

            if(pd.get(position).getNumber_of_varition().contentEquals("1")){

                holder.row1.setVisibility(View.VISIBLE);
                holder.qt1.setText(pd.get(position).getVar_1_qt());
                holder.mrp1.setText(pd.get(position).getVar_1_mrp());
                holder.selling1.setText(pd.get(position).getVar_1_salling_price());
                holder.stock1.setText(pd.get(position).getVar_1_stock());



            }else if(pd.get(position).getNumber_of_varition().contentEquals("2")){

                holder.row1.setVisibility(View.VISIBLE);
                holder.qt1.setText(pd.get(position).getVar_1_qt());
                holder.mrp1.setText(pd.get(position).getVar_1_mrp());
                holder.selling1.setText(pd.get(position).getVar_1_salling_price());
                holder.stock1.setText(pd.get(position).getVar_1_stock());

                holder.row2.setVisibility(View.VISIBLE);
                holder.qt2.setText(pd.get(position).getVar_2_qt());
                holder.mrp2.setText(pd.get(position).getVar_2_mrp());
                holder.selling2.setText(pd.get(position).getVar_2_salling_price());
                holder.stock2.setText(pd.get(position).getVar_2_stock());


            }else if(pd.get(position).getNumber_of_varition().contentEquals("3")){

                holder.row1.setVisibility(View.VISIBLE);
                holder.qt1.setText(pd.get(position).getVar_1_qt());
                holder.mrp1.setText(pd.get(position).getVar_1_mrp());
                holder.selling1.setText(pd.get(position).getVar_1_salling_price());
                holder.stock1.setText(pd.get(position).getVar_1_stock());

                holder.row2.setVisibility(View.VISIBLE);
                holder.qt2.setText(pd.get(position).getVar_2_qt());
                holder.mrp2.setText(pd.get(position).getVar_2_mrp());
                holder.selling2.setText(pd.get(position).getVar_2_salling_price());
                holder.stock2.setText(pd.get(position).getVar_2_stock());

                holder.row3.setVisibility(View.VISIBLE);
                holder.qt3.setText(pd.get(position).getVar_3_qt());
                holder.mrp3.setText(pd.get(position).getVar_3_mrp());
                holder.selling3.setText(pd.get(position).getVar_3_salling_price());
                holder.stock3.setText(pd.get(position).getVar_3_stock());


            }else if(pd.get(position).getNumber_of_varition().contentEquals("4")){

                holder.row1.setVisibility(View.VISIBLE);
                holder.qt1.setText(pd.get(position).getVar_1_qt());
                holder.mrp1.setText(pd.get(position).getVar_1_mrp());
                holder.selling1.setText(pd.get(position).getVar_1_salling_price());
                holder.stock1.setText(pd.get(position).getVar_1_stock());

                holder.row2.setVisibility(View.VISIBLE);
                holder.qt2.setText(pd.get(position).getVar_2_qt());
                holder.mrp2.setText(pd.get(position).getVar_2_mrp());
                holder.selling2.setText(pd.get(position).getVar_2_salling_price());
                holder.stock2.setText(pd.get(position).getVar_2_stock());

                holder.row3.setVisibility(View.VISIBLE);
                holder.qt3.setText(pd.get(position).getVar_3_qt());
                holder.mrp3.setText(pd.get(position).getVar_3_mrp());
                holder.selling3.setText(pd.get(position).getVar_3_salling_price());
                holder.stock3.setText(pd.get(position).getVar_3_stock());

                holder.row4.setVisibility(View.VISIBLE);
                holder.qt4.setText(pd.get(position).getVar_4_qt());
                holder.mrp4.setText(pd.get(position).getVar_4_mrp());
                holder.selling4.setText(pd.get(position).getVar_4_salling_price());
                holder.stock4.setText(pd.get(position).getVar_4_stock());


            }

        }




        holder.edit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                productListInterface.aad_button_click(position);
            }
        });

        holder.remove_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progressDialog.setMessage("Please Wait......");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(false);
                progressDialog.show();
                firebaseFirestore.collection("kirana_shopkeeper_product")
                        .document(firebaseAuth.getCurrentUser().getUid().toString())
                        .collection("proudcts")
                        .document(pd.get(position).getItemId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        pd.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, pd.size());

                        progressDialog.dismiss();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                      progressDialog.dismiss();

                    }
                });


            }
        });


    }

    @Override
    public int getItemCount() {
        return pd.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View mView;
        TextView mrp,salling_price,weight,stock,varition,product_name;
        ImageView product_image;
        CardView remove_bt,edit_bt;
        Glide glide;
        TextView mrp1,mrp2,mrp3,mrp4,qt1,qt2,qt3,qt4,selling1,selling2,selling3,selling4
                ,stock1,stock2,stock3,stock4;

        TableRow row1,row2,row3,row4;
        TableLayout tableLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView=itemView;


            mrp=mView.findViewById(R.id.mrp);
            salling_price=mView.findViewById(R.id.salling_price);
            weight=mView.findViewById(R.id.weight);
            stock=mView.findViewById(R.id.stock);

            product_name=mView.findViewById(R.id.prodct_name);
            edit_bt=mView.findViewById(R.id.edit_bt);

            mrp1=mView.findViewById(R.id.mrp1);
            mrp2=mView.findViewById(R.id.mrp2);
            mrp3=mView.findViewById(R.id.mrp3);
            mrp4=mView.findViewById(R.id.mrp4);

            selling1=mView.findViewById(R.id.selling1);
            selling2=mView.findViewById(R.id.selling2);
            selling3=mView.findViewById(R.id.selling3);
            selling4=mView.findViewById(R.id.selling4);

            stock1=mView.findViewById(R.id.stock1);
            stock2=mView.findViewById(R.id.stock2);
            stock3=mView.findViewById(R.id.stock3);
            stock4=mView.findViewById(R.id.stock4);

            qt1=mView.findViewById(R.id.weight_1);
            qt2=mView.findViewById(R.id.weight_2);
            qt3=mView.findViewById(R.id.weight_3);
            qt4=mView.findViewById(R.id.weight_4);

            row1=mView.findViewById(R.id.row1);
            row2=mView.findViewById(R.id.row2);
            row3=mView.findViewById(R.id.row3);
            row4=mView.findViewById(R.id.row4);

            tableLayout=mView.findViewById(R.id.tableLayout);






            remove_bt=mView.findViewById(R.id.remove_bt);
            product_image=mView.findViewById(R.id.product_image);





        }
    }
}
