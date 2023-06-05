package com.mradking.mradkingshop.kirana_store.custmer.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
import com.mradking.mradkingshop.kirana_store.shopkeer.adapter.Added_product_adapter;
import com.mradking.mradkingshop.kirana_store.shopkeer.modal.AddedProductModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class product_list_adapter extends RecyclerView.Adapter<product_list_adapter.ViewHolder>{
    public Context context;

    private FirebaseFirestore firebaseFirestore;

    FirebaseAuth firebaseAuth;

    private List<CommonModal> pd;
    RecyclerView_home_list recyclerView_home_list;

    public product_list_adapter(Context context, List<CommonModal> pd, RecyclerView_home_list recyclerView_home_list) {
        this.context = context;
        this.pd = pd;
        this.recyclerView_home_list = recyclerView_home_list;
    }

    @NonNull
    @Override
    public product_list_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kirana_added_product_row, parent, false);
        context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull product_list_adapter.ViewHolder holder, int position) {

        holder.glide.with(context).load(pd.get(position).getProduct_image_url()).into(holder.product_image);
        String item_id_st=getRandomString(5);
        holder.mrp.setText("₹"+pd.get(position).getMarket_price());
        holder.salling_price.setText("₹"+pd.get(position).getSalling_price());
        holder.stock.setText(pd.get(position).getStock());
        holder.weight.setText(pd.get(position).getQuantity());
        holder.product_name.setText(pd.get(position).getName_product());

        holder.tableLayout.setVisibility(View.GONE);
        holder.varition_lay.setVisibility(View.GONE);
        holder.update_bt_text.setText("Add Cart");
        holder.remove_bt_text.setText("Add Month List");
        int saving_int=Integer.parseInt(pd.get(position).getMarket_price())-Integer.parseInt(pd.get(position).getSalling_price());

        holder.saving_price.setText(String.valueOf(saving_int));



        holder.edit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String varition_st=pd.get(position).getVarition_status();

                if(varition_st.contentEquals("no")){

                    holder.progressDialog.setMessage("Please Wait......");
                    holder.progressDialog.setCancelable(false);
                    holder.progressDialog.setCanceledOnTouchOutside(false);
                    holder.progressDialog.show();

                    holder.add_cart_quantity_bt.setVisibility(View.VISIBLE);
                    holder.edit_bt.setVisibility(View.GONE);

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


                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {


                            recyclerView_home_list.total_amount(Integer.parseInt("0"));

                            holder.progressDialog.dismiss();



                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {

                              holder.progressDialog.dismiss();

                        }
                    });




                }else if(varition_st.contentEquals("yes")){
                    Toast.makeText(context, "that", Toast.LENGTH_SHORT).show();

                    recyclerView_home_list.visblity_cart_bar(position);


                }

//



            }
        });







        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.progressDialog.setMessage("Please Wait......");
                holder.progressDialog.setCancelable(false);
                holder.progressDialog.setCanceledOnTouchOutside(false);
                holder.progressDialog.show();



                holder.quantiy.setText(String.valueOf( ++holder.count));

                Map<String, Object> map = new HashMap<>();


                map.put("quantity",String.valueOf( holder.count));
                map.put("total_amount",String.valueOf(Integer.parseInt(pd.get(position).getSalling_price())* holder.count));


                firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        recyclerView_home_list.total_amount(Integer.parseInt("0"));

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



                if(holder.count<=0){

                    holder.quantiy.setText(String.valueOf( holder.count));

                    holder.count=1;

                    holder.add_cart_quantity_bt.setVisibility(View.GONE);
                    holder.edit_bt.setVisibility(View.VISIBLE);

                }if(holder.count==1){
                    holder.add_cart_quantity_bt.setVisibility(View.GONE);
                    holder.edit_bt.setVisibility(View.VISIBLE) ;

                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    recyclerView_home_list.total_amount(Integer.parseInt("0"));

                                    holder.progressDialog.dismiss();

                                }
                            });



                }else {

                    Map<String, Object> map = new HashMap<>();

                    holder.quantiy.setText(String.valueOf( --holder.count));


                    map.put("quantity",String.valueOf( holder.count));
                    map.put("total_amount",String.valueOf(Integer.parseInt(pd.get(position).getSalling_price())* holder.count));


                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {


                                    recyclerView_home_list.total_amount(Integer.parseInt("0"));
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

        holder.add_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String varition_st=pd.get(position).getVarition_status();

                if(varition_st.contentEquals("no")){



                holder.progressDialog.setMessage("Please Wait......");
                holder.progressDialog.setCancelable(false);
                holder.progressDialog.setCanceledOnTouchOutside(false);
                holder.progressDialog.show();


                holder.quantity_month.setText(String.valueOf( ++holder.count_1));

                Map<String, Object> map = new HashMap<>();


                map.put("quantity",String.valueOf( holder.count_1));
                map.put("total_amount",String.valueOf(Integer.parseInt(pd.get(position).getSalling_price())* holder.count_1));


                firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart").document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        recyclerView_home_list.total_amount(Integer.parseInt("0"));

                        holder.progressDialog.dismiss();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();

                        holder.progressDialog.dismiss();

                    }
                });

                }else if(varition_st.contentEquals("no")){


                    recyclerView_home_list.visblity_cart_bar(0);
                }




            }
        });


        holder.minnu_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                holder.progressDialog.setMessage("Please Wait......");
                holder.progressDialog.setCancelable(false);
                holder.progressDialog.setCanceledOnTouchOutside(false);
                holder.progressDialog.show();



                if(holder.count_1<=0){

                    holder.quantity_month.setText(String.valueOf( holder.count_1));

                    holder.count=1;

                    holder.add_cart_month.setVisibility(View.GONE);
                    holder.remove_bt.setVisibility(View.VISIBLE);

                }if(holder.count_1==1){
                    holder.add_cart_month.setVisibility(View.GONE);
                    holder.remove_bt.setVisibility(View.VISIBLE);

                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    recyclerView_home_list.total_amount(Integer.parseInt("0"));

                                    holder.progressDialog.dismiss();

                                }
                            });



                }else {

                    Map<String, Object> map = new HashMap<>();

                    holder.quantity_month.setText(String.valueOf( --holder.count_1));


                    map.put("quantity",String.valueOf( holder.count_1));
                    map.put("total_amount",String.valueOf(Integer.parseInt(pd.get(position).getSalling_price())* holder.count_1));


                    firebaseFirestore.collection("kirna_Add_cart").document(firebaseAuth.getUid().toString()).collection("cart")
                            .document(item_id_st).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {


                                    recyclerView_home_list.total_amount(Integer.parseInt("0"));
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

        holder.remove_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.add_cart_month.setVisibility(View.VISIBLE);
                holder.remove_bt.setVisibility(View.GONE);
                holder.progressDialog.setMessage("Please Wait......");
                holder.progressDialog.setCancelable(false);
                holder.progressDialog.setCanceledOnTouchOutside(false);
                holder.progressDialog.show();



                Map<String, Object> map = new HashMap<>();
                map.put("name_product", pd.get(position).getName_product());
                map.put("market_price", pd.get(position).getMarket_price());
                map.put("salling_price", pd.get(position).getSalling_price());
                map.put("product_image_url", pd.get(position).getProduct_image_url());
                map.put("item_id", item_id_st);
                map.put("product_id",pd.get(position).getItemId());
                map.put("quantity",String.valueOf( holder.count_1));
                map.put("total_amount",String.valueOf(Integer.parseInt(pd.get(position).getSalling_price())* holder.count));
                map.put("order_status", "month");
                map.put("list_type","month");


                firebaseFirestore.collection("kirna_Add_cart")
                        .document(firebaseAuth.getUid().toString()).collection("cart")
                        .document(item_id_st).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {



                                holder.progressDialog.dismiss();



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(Exception e) {

                                Toast.makeText(context, "Product not Added to Cart", Toast.LENGTH_SHORT).show();
                                holder.progressDialog.dismiss();

                            }
                        });
                ;

            }
        });



    }

    @Override
    public int getItemCount() {
        return pd.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView mrp,salling_price,weight,stock,varition,
                product_name,update_bt_text,remove_bt_text,saving_price;
        ImageView product_image;
        CardView remove_bt,edit_bt;
        LinearLayout varition_lay,add_cart_quantity_bt,add_cart_month;

        Glide glide;
        TextView add,minnus,quantiy,quantity_month,add_month,minnu_month;
        Spinner spinner;
        TableLayout tableLayout;
        ProgressDialog progressDialog;
        int count=1;
        int count_1=1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView=itemView;


            mrp=mView.findViewById(R.id.mrp);
            salling_price=mView.findViewById(R.id.salling_price);
            weight=mView.findViewById(R.id.weight);
            stock=mView.findViewById(R.id.stock);

            product_name=mView.findViewById(R.id.prodct_name);
            edit_bt=mView.findViewById(R.id.edit_bt);
            varition_lay=mView.findViewById(R.id.varition_lay);

            remove_bt=mView.findViewById(R.id.remove_bt);
            product_image=mView.findViewById(R.id.product_image);
            tableLayout=mView.findViewById(R.id.tableLayout);
            update_bt_text=mView.findViewById(R.id.update_bt_text);
            remove_bt_text=mView.findViewById(R.id.remove_bt_text);
            add_cart_quantity_bt=mView.findViewById(R.id.add_cart_quantity_bt);
            progressDialog=new ProgressDialog(context);
            saving_price=mView.findViewById(R.id.saving_price);

            add=(TextView)mView.findViewById(R.id.add);
            minnus=(TextView)mView.findViewById(R.id.minnu);
            quantiy=(TextView)mView.findViewById(R.id.quantity);

            add_cart_month=mView.findViewById(R.id.add_cart_month);
            add_month=mView.findViewById(R.id.add_month);
            minnu_month=mView.findViewById(R.id.minnu_month);
            quantity_month=mView.findViewById(R.id.quantity_month);



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
