package com.mradking.mradkingshop.kirana_store.shopkeer.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.OrderListModal;
import com.mradking.mradkingshop.kirana_store.shopkeer.activity.withdrawing_request_making_act;
import com.mradking.mradkingshop.kirana_store.shopkeer.activity.Order_item_list_shop;

import java.util.List;

public class WithDraw_Transfer_list_adapter extends RecyclerView.Adapter<WithDraw_Transfer_list_adapter.ViewHolder> {
    public Context context;
    private List<OrderListModal> pd;
    private FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    String order_status;
    public WithDraw_Transfer_list_adapter(Context context, List<OrderListModal> pd,String order_status) {
        this.context = context;
        this.pd = pd;
        this.order_status=order_status;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kirana_shop_withraw_raw, parent, false);
        context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(context);
        return new WithDraw_Transfer_list_adapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {




        int total_amt=Integer.parseInt(pd.get(position).getNet_payment());
        int comission_st=Integer.parseInt(pd.get(position).getNet_payment())*10/100;
        int tax_on_comission= comission_st*18/100;
        holder.order_annount.setText("₹ "+pd.get(position).getNet_payment());
        holder.commission.setText("- ₹ "+String.valueOf(comission_st));
        holder.tax.setText("- ₹ "+String.valueOf(tax_on_comission));
        holder.delivery_charges.setText("- ₹ "+"40");
        holder.net_payout.setText("₹ "+String.valueOf(total_amt-comission_st-tax_on_comission));


        if(order_status.contentEquals("no_requested")){


        }else if(order_status.contentEquals("requested")){

            holder.ok_bt.setVisibility(View.GONE);
            holder.requested_date_lay.setVisibility(View.VISIBLE);
            holder.requested_date.setText(pd.get(position).getWithraw_request_date());


        }else if(order_status.contentEquals("transfer")){


        }


        holder.ok_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent intent=new Intent(context, withdrawing_request_making_act.class);

                intent.putExtra("total_amt",total_amt);
                intent.putExtra("comission_st",comission_st);
                intent.putExtra("tax_on_comission",tax_on_comission);
                intent.putExtra("item_id",pd.get(position).getItem_id());
                context.startActivity(intent);
                ((Activity)context).finish();


            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(context, Order_item_list_shop.class);

                intent.putExtra("order_id",pd.get(position).getOrder_id());
                intent.putExtra("payment_method",pd.get(position).getPayment_method());
                intent.putExtra("total_amount",pd.get(position).getTotal_amt());
                intent.putExtra("delivery_charge",pd.get(position).getDelivery());
                intent.putExtra("net_pay",pd.get(position).getNet_payment());
                intent.putExtra("advance_amount","60");
                intent.putExtra("remaing_amount",pd.get(position).getRemaing_amount());
                intent.putExtra("user_uid",pd.get(position).getCustmer_uid());
                intent.putExtra("item_id",pd.get(position).getItem_id());
                intent.putExtra("order_status",pd.get(position).getOrder_status());
                intent.putExtra("order_status_key",order_status);

                context.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return pd.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView order_annount,commission,tax,delivery_charges,net_payout,requested_date;
        View mView;
        CardView ok_bt;
        LinearLayout requested_date_lay;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            order_annount=mView.findViewById(R.id.order_amount);
            commission=mView.findViewById(R.id.commission);
            tax=mView.findViewById(R.id.tax);
            delivery_charges=mView.findViewById(R.id.delivery_charges);
            net_payout=mView.findViewById(R.id.net_payout);
            ok_bt=mView.findViewById(R.id.ok_bt);
            requested_date=mView.findViewById(R.id.request_date);

            requested_date_lay=mView.findViewById(R.id.request_date_lay);


        }
    }
}
