package com.mradking.mradkingshop.kirana_store.shopkeer.adapter;

import android.annotation.SuppressLint;
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
import com.mradking.mradkingshop.kirana_store.shopkeer.inface.brand_interface;
import com.mradking.mradkingshop.kirana_store.shopkeer.modal.BrandModal;

import java.util.List;

public class BrandListAdapter extends RecyclerView.Adapter<BrandListAdapter.ViewHolder> {

    private Context mCtx;

    private List<BrandModal> noteslist;
    brand_interface brandInterface;
    public Context context;

    private FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    public BrandListAdapter(Context mCtx, List<BrandModal> noteslist, brand_interface brandInterface) {
        this.mCtx = mCtx;
        this.noteslist = noteslist;
        this.brandInterface = brandInterface;
    }

    @NonNull
    @Override
    public BrandListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.kirana_brand_row, null);
        context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {












        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                brandInterface.click_brand(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return noteslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView brand_name;
        View mView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView=itemView;

            brand_name=mView.findViewById(R.id.bramd_name);


        }
    }
}
