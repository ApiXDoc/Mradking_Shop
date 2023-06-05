package com.mradking.mradkingshop.kirana_store.dilivery.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;

import java.util.List;

public class Delivered_Order_adapter extends RecyclerView.Adapter <Delivered_Order_adapter.ViewHolder>{
    public Context context;
    private List<CommonModal> pd;
    private FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    public Delivered_Order_adapter(Context context, List<CommonModal> pd) {
        this.context = context;
        this.pd = pd;
    }


    @NonNull
    @Override
    public Delivered_Order_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kirana_dilivery_order_pickup_row, parent, false);
        context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(context);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Delivered_Order_adapter.ViewHolder holder, int position) {
        holder.order_amt.setText(pd.get(position).getTotal_amt());
        holder.order_data.setText(pd.get(position).getOrder_date());
        holder.payment_mode.setText(pd.get(position).getPayment_method());

        holder.shop_address.setText(pd.get(position).getStore_address());
        holder.custmer_address_layout.setVisibility(View.VISIBLE);
        holder.cutmer_address.setText(pd.get(position).getHouse_no()+" " +
                ""+pd.get(position).getColony()+" "+pd.get(position).getState()+"" +
                " zin code:-"+pd.get(position).getPin());
        holder.call_bt.setVisibility(View.VISIBLE);
        holder.direction_bt.setVisibility(View.VISIBLE);
        holder.pick_up_button.setVisibility(View.GONE);




    }

    @Override
    public int getItemCount() {
        return pd.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView direction_bt,pick_up_button,call_bt;
        TextView order_amt,order_data,payment_mode,shop_address,cutmer_address;
        LinearLayout custmer_address_layout;
        View mView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            order_amt=itemView.findViewById(R.id.order_amt);
            order_data=itemView.findViewById(R.id.order_date);
            payment_mode=itemView.findViewById(R.id.payment_mode);
            shop_address=itemView.findViewById(R.id.shop_address);
            cutmer_address=itemView.findViewById(R.id.cutmer_address);

            custmer_address_layout=itemView.findViewById(R.id.custmer_address_layout);

            direction_bt=mView.findViewById(R.id.direction);
            pick_up_button=itemView.findViewById(R.id.pick_up_button);
            call_bt=itemView.findViewById(R.id.call_bt);

        }
    }
}
