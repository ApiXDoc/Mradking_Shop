package com.mradking.mradkingshop.kirana_store.shopkeer.adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.OrderListModal;
import com.mradking.mradkingshop.kirana_store.custmer.Model.cart_list_modal;
import com.mradking.mradkingshop.kirana_store.custmer.activity.Order_Item_list;
import com.mradking.mradkingshop.kirana_store.custmer.adapter.Order_item_list_adapter;
import com.mradking.mradkingshop.kirana_store.custmer.intface.RecyclerView_home_list;
import com.mradking.mradkingshop.kirana_store.shopkeer.activity.Order_item_list_shop;

import java.util.List;

public class OrderListAdapter  extends RecyclerView.Adapter<OrderListAdapter.VeiwHolder> {


    public Context context;
    private List<OrderListModal> pd;
    private FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    String order_status;
    public OrderListAdapter(Context context, List<OrderListModal> pd,String order_status) {
        this.context = context;
        this.pd = pd;
        this.order_status=order_status;

    }



    @NonNull
    @Override
    public OrderListAdapter.VeiwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kirana_orderlist_row_shop, parent, false);
        context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(context);
        return new VeiwHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderListAdapter.VeiwHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.order_amt.setText("₹"+pd.get(position).getNet_payment());
        holder.no_of_item.setText(pd.get(position).getNo_of_item());
        holder.order_date.setText(pd.get(position).getOrder_date());
        holder.payment_mode.setText(pd.get(position).getPayment_method());

        if(order_status.contentEquals("inprocess")){


            holder.tarcking_panel.setVisibility(View.VISIBLE);
            holder.pickup_code_layout.setVisibility(View.VISIBLE);
            holder.pickup_code.setText(pd.get(position).getOrder_id());
            holder.image_packed.setImageResource(R.drawable.round_shape_primary);
           holder.line_first.setBackgroundColor(Color.parseColor("#FF1012"));

        }else if(order_status.contentEquals("delivered")){

            holder.tarcking_panel.setVisibility(View.VISIBLE);
            holder.pickup_code_layout.setVisibility(View.VISIBLE);
            holder.pickup_code.setText(pd.get(position).getOrder_id());
            holder.image_packed.setImageResource(R.drawable.round_shape_primary);
            holder.image_pick_up.setImageResource(R.drawable.round_shape_primary);
            holder.line_first.setBackgroundColor(Color.parseColor("#FF1012"));
            holder.line_second.setBackgroundColor(Color.parseColor("#FF1012"));


        }





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

    public class VeiwHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView order_amt,no_of_item,order_date,payment_mode,pickup_code;
        LinearLayout tarcking_panel,pickup_code_layout;

        ImageView image_packed, image_pick_up, image_delivery;
        View line_first, line_second;

        public VeiwHolder(@NonNull View itemView) {
            super(itemView);


            mView = itemView;

            order_amt=mView.findViewById(R.id.order_amt);
            no_of_item=mView.findViewById(R.id.no_of_item);
            order_date=mView.findViewById(R.id.order_date);
            payment_mode=mView.findViewById(R.id.payment_mode);
            tarcking_panel=mView.findViewById(R.id.tarcking_panel);
            pickup_code_layout=mView.findViewById(R.id.pick_up_code_layout);
            pickup_code=mView.findViewById(R.id.pick_up_code);


            image_packed = mView.findViewById(R.id.image_shipping);
            image_pick_up = mView.findViewById(R.id.image_payment);
            image_delivery =mView. findViewById(R.id.image_confirm);
            line_first = mView.findViewById(R.id.line_first);
            line_second = mView.findViewById(R.id.line_second);

        }
    }
}
