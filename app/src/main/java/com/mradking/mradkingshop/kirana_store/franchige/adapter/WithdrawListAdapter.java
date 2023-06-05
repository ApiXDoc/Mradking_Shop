package com.mradking.mradkingshop.kirana_store.franchige.adapter;

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
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;

import java.util.List;

public class WithdrawListAdapter extends RecyclerView.Adapter<WithdrawListAdapter.ViewHolder> {
    private Context mCtx;

    private List<CommonModal> noteslist;
    ProgressDialog progressDialog;
    private FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    public WithdrawListAdapter(Context mCtx, List<CommonModal> noteslist) {
        this.mCtx = mCtx;
        this.noteslist = noteslist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.withdraw_requested_row, parent, false);
        mCtx = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(mCtx);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.status.setText(noteslist.get(position).getPayout_status_french());
        holder.withdraw_amount.setText(noteslist.get(position).getWithdraing_amount_french());
        holder.number.setText(noteslist.get(position).getPhone_number_french());
        holder.date.setText(noteslist.get(position).getWithdraw_date_french());

    }

    @Override
    public int getItemCount() {
        return noteslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView withdraw_amount,number,date,status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;

            withdraw_amount=view.findViewById(R.id.withdraw_amount);
            number=view.findViewById(R.id.number);
            date=view.findViewById(R.id.date);
            status=view.findViewById(R.id.status);




        }
    }
}
