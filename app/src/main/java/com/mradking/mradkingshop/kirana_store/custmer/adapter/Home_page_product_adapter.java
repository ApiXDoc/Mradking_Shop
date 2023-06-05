package com.mradking.mradkingshop.kirana_store.custmer.adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;
import com.mradking.mradkingshop.kirana_store.custmer.intface.RecyclerView_home_list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Home_page_product_adapter extends RecyclerView.Adapter<Home_page_product_adapter.ViewHolder>{

    public Context context;
    private List<CommonModal> pd;
    private FirebaseFirestore firebaseFirestore;

    FirebaseAuth firebaseAuth;
    RecyclerView_home_list recyclerView_home_list;


    private List<CommonModal> productListFull;


    //getting the context and product list with constructor
    public Home_page_product_adapter(Context mCtx, List<CommonModal> pd,RecyclerView_home_list recyclerView_home_list) {
        this.context = mCtx;
        this.pd = pd;
        productListFull = new ArrayList<CommonModal>(pd);
        this.recyclerView_home_list=recyclerView_home_list;

    }



    @NonNull
    @Override
    public Home_page_product_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kirana_home_product_row, parent, false);
        context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Home_page_product_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        String item_id_st=getRandomString(5);

        holder.glide.with(context).load( pd.get(position).getProduct_image_url()).into(holder.product_img);
        holder.product_name.setText( pd.get(position).getName_product());
        holder.product_price.setText("Rs "+ pd.get(position).getSalling_price());
        holder.market_price.setText("Rs"+ pd.get(position).getMarket_price());



        firebaseFirestore.collection("kirna_Add_cart")
                .document(holder.firebaseAuth.getUid().toString())
                .collection("cart")
                .document(pd.get(position).getItemId()).get().
                addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        DocumentSnapshot documentSnapshot=task.getResult();
                        String quantity_st = documentSnapshot.getString("quantity");
                        String order_status=documentSnapshot.getString("order_status");

                        if(documentSnapshot.exists()){


                            if(order_status.contentEquals("cart")){

                                holder.count=Integer.parseInt(quantity_st);

                                holder.add_cart_quantity_bt.setVisibility(View.VISIBLE);
                                holder.add_cart_bt.setVisibility(View.GONE);
                                holder.quantiy.setText(String.valueOf(holder.count));



                            }


                        }

                    }
                });




        holder.add_cart_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                holder.progressDialog.setMessage("Please Wait......");
                holder.progressDialog.setCancelable(false);
                holder.progressDialog.setCanceledOnTouchOutside(false);
                holder.progressDialog.show();

                recyclerView_home_list.visblity_cart_bar(0);



                Map<String, Object> map = new HashMap<>();
                map.put("name_product", pd.get(position).getName_product());
                map.put("market_price", pd.get(position).getMarket_price());
                map.put("salling_price", pd.get(position).getSalling_price());
                map.put("product_image_url", pd.get(position).getProduct_image_url());
                map.put("item_id", item_id_st);
                map.put("product_id",pd.get(position).getItemId());
                map.put("quantity",String.valueOf( holder.count));
                map.put("total_amount",String.valueOf(Integer.parseInt(pd.get(position).getSalling_price())* holder.count));
                map.put("order_status", "cart");



                firebaseFirestore.collection("kirna_Add_cart").document(holder.firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {



                        holder.add_cart_quantity_bt.setVisibility(View.VISIBLE);
                        holder.add_cart_bt.setVisibility(View.GONE);

                        recyclerView_home_list.total_amount(0);


                        holder.progressDialog.dismiss();




                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                        holder.progressDialog.dismiss();

                    }
                });







            }
        });


        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.progressDialog.setMessage("Please Wait......");
                holder.progressDialog.setCancelable(false);
                holder.progressDialog.setCanceledOnTouchOutside(false);
                holder.progressDialog.show();


                recyclerView_home_list.visblity_cart_bar(0);
                holder.quantiy.setText(String.valueOf( ++holder.count));

                Map<String, Object> map = new HashMap<>();


                map.put("quantity",String.valueOf( holder.count));
                map.put("total_amount",String.valueOf(Integer.parseInt(pd.get(position).getSalling_price())* holder.count));


                firebaseFirestore.collection("kirna_Add_cart").document(holder.firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                        recyclerView_home_list.total_amount(0);

                        holder.progressDialog.dismiss();



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                        holder.progressDialog.dismiss();

                    }
                });






            }
        });

        holder.minnus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.progressDialog.setMessage("Please Wait......");
                holder.progressDialog.setCancelable(false);
                holder.progressDialog.setCanceledOnTouchOutside(false);
                holder.progressDialog.show();

                recyclerView_home_list.visblity_cart_bar(1);


                if(holder.count<=0){

                    holder.quantiy.setText(String.valueOf( holder.count));

                    holder.count=1;

                    holder.add_cart_quantity_bt.setVisibility(View.GONE);
                    holder.add_cart_bt.setVisibility(View.VISIBLE);

                }if(holder.count==1){
                    holder.add_cart_quantity_bt.setVisibility(View.GONE);
                    holder.add_cart_bt.setVisibility(View.VISIBLE) ;

                    firebaseFirestore.collection("kirna_Add_cart").document(holder.firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {


                                    recyclerView_home_list.total_amount(0);

                                    holder.progressDialog.dismiss();




                                }
                            });



                }else {

                    Map<String, Object> map = new HashMap<>();

                    holder.quantiy.setText(String.valueOf( --holder.count));


                    map.put("quantity",String.valueOf( holder.count));
                    map.put("total_amount",String.valueOf(Integer.parseInt(pd.get(position).getSalling_price())* holder.count));


                    firebaseFirestore.collection("kirna_Add_cart").document(holder.firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {


                                    recyclerView_home_list.total_amount(0);
                                    holder.progressDialog.dismiss();




                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {

                                    Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                    holder.progressDialog.dismiss();


                                }
                            });
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
        ImageView product_img;
        Glide glide;
        TextView product_name,product_price,market_price,add,minnus,quantiy;
        LinearLayout add_cart_bt,add_cart_quantity_bt;
        FirebaseFirestore firebaseFirestore;
        FirebaseAuth firebaseAuth;
        ProgressDialog progressDialog;
        int count=1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
            product_img=(ImageView)mView.findViewById(R.id.product_img);
            product_name=(TextView)mView.findViewById(R.id.product_name);
            product_price=(TextView)mView.findViewById(R.id.salling_price);
            market_price=(TextView)mView.findViewById(R.id.marketing_price);

            add=(TextView)mView.findViewById(R.id.add);
            minnus=(TextView)mView.findViewById(R.id.minnu);
            quantiy=(TextView)mView.findViewById(R.id.quantity);

            firebaseAuth=FirebaseAuth.getInstance();
            firebaseFirestore=FirebaseFirestore.getInstance();

            progressDialog=new ProgressDialog(context);


            add_cart_bt=mView.findViewById(R.id.add_cart_bt);
            add_cart_quantity_bt=mView.findViewById(R.id.add_cart_quantity_bt);


        }
    }
    public void total_amount_calculation() {
        int total_saving;
        for (int i = 0; i < pd.size(); i++) {

             total_saving=Integer.parseInt(pd.get(i).getMarket_price())-Integer.parseInt(pd.get(i).getSalling_price());

            Toast.makeText(context, String.valueOf(total_saving), Toast.LENGTH_SHORT).show();
        }


    }

    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";

    private static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

}
