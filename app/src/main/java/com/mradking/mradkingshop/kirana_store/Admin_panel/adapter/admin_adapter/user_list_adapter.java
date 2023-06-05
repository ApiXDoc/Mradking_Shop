package com.mradking.mradkingshop.kirana_store.Admin_panel.adapter.admin_adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.Admin_panel.adapter.shop_adapter.shop_withdraw_request_adapter;
import com.mradking.mradkingshop.kirana_store.Admin_panel.modal.Userlist;
import com.mradking.mradkingshop.kirana_store.custmer.Model.OrderListModal;

import java.util.List;

public class user_list_adapter extends RecyclerView.Adapter< user_list_adapter.ViewHolder> {

    public Context context;
    private List<Userlist> pd;
    private FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    public user_list_adapter(Context context, List<Userlist> pd) {
        this.context = context;
        this.pd = pd;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kirana_admin_user_list_raw, parent, false);
        context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.name.setText("Name :-"+pd.get(position).getName());
        holder.phone_no.setText("Phone No:- "+pd.get(position).getPhone());


    }

    @Override
    public int getItemCount() {
        return pd.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView name, phone_no;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            name = mView.findViewById(R.id.user_name);
            phone_no = mView.findViewById(R.id.phone_no);


        }
    }
}