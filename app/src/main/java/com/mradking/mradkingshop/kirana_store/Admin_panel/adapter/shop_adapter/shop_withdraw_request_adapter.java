package com.mradking.mradkingshop.kirana_store.Admin_panel.adapter.shop_adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.mradking.mradkingshop.kirana_store.Admin_panel.activity.shop.Shop_withdraw_transfer_act;
import com.mradking.mradkingshop.kirana_store.Admin_panel.intarfac.admin_shop_withraw_payment;
import com.mradking.mradkingshop.kirana_store.custmer.Model.OrderListModal;
import com.mradking.mradkingshop.kirana_store.shopkeer.activity.Order_item_list_shop;

import java.util.List;

public class shop_withdraw_request_adapter extends RecyclerView.Adapter<shop_withdraw_request_adapter.ViewHolder> {
    public Context context;
    private List<OrderListModal> pd;
    private FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    private admin_shop_withraw_payment interface_payment;
    String order_status;
    public shop_withdraw_request_adapter(Context context, List<OrderListModal> pd, admin_shop_withraw_payment interface_payment, String order_status) {
        this.context = context;
        this.pd = pd;
        this.interface_payment = interface_payment;
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
        return new ViewHolder(view);
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
        holder.button_txt.setText("TRANSFER");

        if(order_status.contentEquals("requested")){


        }else if(order_status.contentEquals("transfer")){
            holder.button_txt.setText("Get Details");
            holder.requested_date_lay.setVisibility(View.VISIBLE);
            holder.requested_date.setText(pd.get(position).getWithraw_request_date());


        }



        holder.ok_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(order_status.contentEquals("transfer")){
                    Intent intent=new Intent(context, Shop_withdraw_transfer_act.class);


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
                    intent.putExtra("order_amount","₹ "+pd.get(position).getNet_payment());
                    intent.putExtra("comission","- ₹ "+String.valueOf(comission_st));
                    intent.putExtra("tax","- ₹ "+String.valueOf(tax_on_comission));
                    intent.putExtra("net_payout","₹ "+String.valueOf(total_amt-comission_st-tax_on_comission));
                    intent.putExtra("store_uid",pd.get(position).getStore_uid());
                    intent.putExtra("withdraw_screensort_proof",pd.get(position).getWithdraw_screensort_proof());
                    intent.putExtra("withdraw_status",pd.get(position).getWithdraw_status());


                    context.startActivity(intent);


                }else {


                    Intent intent=new Intent(context, Shop_withdraw_transfer_act.class);


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
                    intent.putExtra("order_amount","₹ "+pd.get(position).getNet_payment());
                    intent.putExtra("comission","- ₹ "+String.valueOf(comission_st));
                    intent.putExtra("tax","- ₹ "+String.valueOf(tax_on_comission));
                    intent.putExtra("net_payout","₹ "+String.valueOf(total_amt-comission_st-tax_on_comission));
                    intent.putExtra("store_uid",pd.get(position).getStore_uid());
                    intent.putExtra("withdraw_screensort_proof",pd.get(position).getWithdraw_screensort_proof());
                    intent.putExtra("withdraw_status",pd.get(position).getWithdraw_status());



                    context.startActivity(intent);



                }




            }
        });



    }

    @Override
    public int getItemCount() {
        return pd.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;


        TextView order_annount,commission,tax,delivery_charges,net_payout,requested_date,button_txt;


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
                button_txt=mView.findViewById(R.id.button_txt);
                requested_date_lay=mView.findViewById(R.id.request_date_lay);


            }




        }


}