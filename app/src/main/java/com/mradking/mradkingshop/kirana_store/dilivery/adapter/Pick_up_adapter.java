package com.mradking.mradkingshop.kirana_store.dilivery.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;
import com.mradking.mradkingshop.kirana_store.custmer.activity.Order_Item_list;
import com.mradking.mradkingshop.kirana_store.dilivery.list.pickup_list;

import java.util.List;

public class Pick_up_adapter extends RecyclerView.Adapter<Pick_up_adapter.ViewHolder> {
    public Context context;
    private List<CommonModal> pd;
    private FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;


    public Pick_up_adapter(Context context, List<CommonModal> pd) {
        this.context = context;
        this.pd = pd;
    }

    @NonNull
    @Override
    public Pick_up_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kirana_dilivery_order_pickup_row, parent, false);
        context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(context);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Pick_up_adapter.ViewHolder holder, int position) {


        holder.order_amt.setText(pd.get(position).getTotal_amt());
        holder.order_data.setText(pd.get(position).getOrder_date());
        holder.payment_mode.setText(pd.get(position).getPayment_method());

        holder.shop_address.setText(pd.get(position).getStore_address());

        holder.call_bt.setVisibility(View.VISIBLE);
        holder.direction_bt.setVisibility(View.VISIBLE);
        holder.pick_up_button.setVisibility(View.GONE);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, pickup_list.class);
                intent.putExtra("uid",pd.get(position).getCustmer_uid());
                intent.putExtra("order_id",pd.get(position).getOrder_id());
                intent.putExtra("payment_method",pd.get(position).getPayment_method());
                intent.putExtra("total_amount",pd.get(position).getTotal_amt());
                intent.putExtra("delivery_charge",pd.get(position).getDelivery());
                intent.putExtra("net_pay",pd.get(position).getNet_payment());
                intent.putExtra("advance_amount","60");
                intent.putExtra("opt",pd.get(position).getPickup_otp());
                intent.putExtra("remaing_amount",pd.get(position).getRemaing_amount());

                context.startActivity(intent);

            }
        });

        holder.call_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri u = Uri.parse("tel:" + pd.get(position).getStore_phone());

                Intent i = new Intent(Intent.ACTION_DIAL, u);
                try
                {
                   context.startActivity(i);
                }
                catch (SecurityException s)
                {

                    Toast.makeText(context, "An error occurred", Toast.LENGTH_LONG)
                            .show();
                }


            }
        });

        holder.direction_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setMessage("Please Wait......");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                firebaseFirestore.collection("ShoprKeeper_detail")
                        .document(pd.get(position).getStore_uid())
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                DocumentSnapshot documentSnapshot=task.getResult();

                                String latitude=documentSnapshot.getString("lat");
                                String longitude=documentSnapshot.getString("lon");


                                String uri = "http://maps.google.com/maps?daddr=" + latitude + "," + longitude;
                                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                                context.startActivity(intent);
                                progressDialog.dismiss();




                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {


                                Toast.makeText(context, "location is not geting try again", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });




    }

    @Override
    public int getItemCount() {
        return pd.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
