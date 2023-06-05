package com.mradking.mradkingshop.kirana_store.custmer.adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;
import com.mradking.mradkingshop.kirana_store.custmer.activity.Order_Item_list;

import java.util.List;

public class Kirana_Orderlist_adapter extends RecyclerView.Adapter<Kirana_Orderlist_adapter.ViewHolder> {
    public Context context;
    private List<CommonModal> pd;
    private FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    public Kirana_Orderlist_adapter(Context context, List<CommonModal> pd) {
        this.context = context;
        this.pd = pd;
    }

    @NonNull
    @Override
    public Kirana_Orderlist_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kirana_orderlist_row_cum, parent, false);
        context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(context);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Kirana_Orderlist_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.order_amt.setText("₹"+pd.get(position).getNet_payment());
        holder.no_of_item.setText(pd.get(position).getNo_of_item());
        holder.order_date.setText(pd.get(position).getOrder_date());
        holder.payment_mode.setText(pd.get(position).getPayment_method());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, Order_Item_list.class);
                intent.putExtra("uid",pd.get(position).getCustmer_uid());
                intent.putExtra("order_id",pd.get(position).getOrder_id());
                intent.putExtra("payment_method",pd.get(position).getPayment_method());
                intent.putExtra("total_amount",pd.get(position).getTotal_amt());
                intent.putExtra("delivery_charge",pd.get(position).getDelivery());
                intent.putExtra("net_pay",pd.get(position).getNet_payment());
                intent.putExtra("advance_amount","60");
                intent.putExtra("remaing_amount",pd.get(position).getRemaing_amount());

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return pd.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        View mView;
        TextView order_amt,no_of_item,order_date,payment_mode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            order_amt=mView.findViewById(R.id.order_amt);
            no_of_item=mView.findViewById(R.id.no_of_item);
            order_date=mView.findViewById(R.id.order_date);
            payment_mode=mView.findViewById(R.id.payment_mode);

        }
    }
}
