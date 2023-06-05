package com.mradking.mradkingshop.kirana_store.dilivery.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;
import com.mradking.mradkingshop.kirana_store.custmer.activity.Order_Item_list;
import com.mradking.mradkingshop.kirana_store.custmer.activity.check_out_address;
import com.mradking.mradkingshop.kirana_store.custmer.adapter.Home_page_product_adapter;
import com.mradking.mradkingshop.kirana_store.custmer.intface.Get_loc_Calll;
import com.mradking.mradkingshop.kirana_store.custmer.intface.RecyclerView_home_list;
import com.mradking.mradkingshop.kirana_store.custmer.utility.utilityX;
import com.mradking.mradkingshop.kirana_store.dilivery.activity.Get_drection_map;
import com.mradking.mradkingshop.kirana_store.dilivery.activity.Order_list_act;
import com.mradking.mradkingshop.kirana_store.dilivery.list.pickup_list;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order_list_adapter extends RecyclerView.Adapter<Order_list_adapter.ViewHolder> {
    public Context context;
    private List<CommonModal> pd;
    private FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    utilityX UtilityX;

    public Order_list_adapter(Context context, List<CommonModal> pd) {
        this.context = context;
        this.pd = pd;
    }

    @NonNull
    @Override
    public Order_list_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kirana_dilivery_order_pickup_row, parent, false);
        context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(context);
        UtilityX=new utilityX(context);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Order_list_adapter.ViewHolder holder, int position) {


            holder.order_amt.setText(pd.get(position).getTotal_amt());
            holder.order_data.setText(pd.get(position).getOrder_date());
            holder.payment_mode.setText(pd.get(position).getPayment_method());

            holder.shop_address.setText(pd.get(position).getStore_address());



        holder.pick_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Please Wait......");
//
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                DatePicker datePicker = new DatePicker(context);
                String current_date = datePicker.getDayOfMonth() + "/" + datePicker.getMonth() + "/" + datePicker.getYear();

                Map<String,Object> map=new HashMap<>();
                map.put("pickup_status","accept_pickup");
               map.put("pickup_date",current_date);
               map.put("pickup_timeStamp", FieldValue.serverTimestamp());
                map.put("dilivery_partner_uid",firebaseAuth.getCurrentUser().getUid());


                firebaseFirestore.collection("kirana_order_list")
                        .document(pd.get(position).getItemId())
                        .update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
//

                                UtilityX.notification_send(pd.get(position).getStore_message_token()
                                        ,"Pick Up Accept");

                                pd.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, pd.size());
                                progressDialog.dismiss();

                                Toast.makeText(context, "pickup accepted", Toast.LENGTH_SHORT).show();

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
        CardView direction_bt,pick_up_button,call_bt;
        TextView order_amt,order_data,payment_mode,shop_address,cutmer_address;
        LinearLayout custmer_address_layout;


        View mView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
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
