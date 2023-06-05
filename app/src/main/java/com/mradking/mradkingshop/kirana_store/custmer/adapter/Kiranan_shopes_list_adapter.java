package com.mradking.mradkingshop.kirana_store.custmer.adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.maps.android.SphericalUtil;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;
import com.mradking.mradkingshop.kirana_store.custmer.intface.Shop_list_interface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kiranan_shopes_list_adapter extends RecyclerView.Adapter<Kiranan_shopes_list_adapter.ViewHolder> {


    public Context context;
    private List<CommonModal> pd;
    private FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    Shop_list_interface shop_list_interface;
    private List<CommonModal> productListFull;




    public Kiranan_shopes_list_adapter(Context context, List<CommonModal> pd,Shop_list_interface shop_list_interface) {
        this.context = context;
        this.pd = pd;
        this.shop_list_interface = shop_list_interface;
    }

    @NonNull
    @Override
    public Kiranan_shopes_list_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kirana_shop_row, parent, false);
        context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(context);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Kiranan_shopes_list_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        progressDialog.setMessage("Please Wait.......");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);

        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                DocumentSnapshot documentSnapshot=task.getResult();

                String lat_user=documentSnapshot.getString("lat");
                String lon_user=documentSnapshot.getString("lon");

                LatLng user_latlng=new LatLng(Double.parseDouble(lat_user),Double.parseDouble(lon_user));

                LatLng shop_latlng=new LatLng(Double.parseDouble(pd.get(position).getLat()),Double.parseDouble(pd.get(position).getLon()));


                double  distance = SphericalUtil.computeDistanceBetween(user_latlng, shop_latlng);


                holder.shop_name.setText(pd.get(position).getShop_name()+"\n"+"Distance:- "+String.format("%.2f", distance / 1000) + "km");
                progressDialog.dismiss();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                Toast.makeText(context, "error to get shop", Toast.LENGTH_SHORT).show();

            }
        });


        holder.mView.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

                progressDialog.setMessage("Please Wait.......");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(false);
                progressDialog.show();

                Map<String,Object> map=new HashMap<>();
              map.put("store_uid",pd.get(position).getUid());
              map.put("shop_message_token",pd.get(position).getShop_message_token());
             map.put("store_address",pd.get(position).getShop_address());
                map.put("store_phone",pd.get(position).getPhone());
              map.put("shop_selection_status","yes");



                firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid().toString()).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        shop_list_interface.item_click(position);




                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        progressDialog.dismiss();
                        Toast.makeText(context, "store not selected", Toast.LENGTH_SHORT).show();

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
        View mView;
        TextView shop_name,distance;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            shop_name=mView.findViewById(R.id.shop_name);
            distance=mView.findViewById(R.id.distance);


        }
    }
}
