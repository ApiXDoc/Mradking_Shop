package com.mradking.mradkingshop.kirana_store.franchige.adapter;

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
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;
import com.mradking.mradkingshop.kirana_store.custmer.adapter.Home_page_product_adapter;

import java.util.List;

public class team_adatper extends RecyclerView.Adapter<team_adatper.ViewHolder> {

    public Context context;
    private List<CommonModal> pd;

    public team_adatper(Context context, List<CommonModal> pd) {
        this.context = context;
        this.pd = pd;
    }

    @NonNull
    @Override
    public team_adatper.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_user_list_row, parent, false);
        context = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull team_adatper.ViewHolder holder, int position) {

        holder.name_id.setText(pd.get(position).getName());
        holder.phone_id.setText(pd.get(position).getPhone());
        holder.team_mebs_id.setText("Team:-"+String.valueOf(pd.get(position).getPartners()));

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", pd.get(position).getPhone(), null));
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return pd.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        View mView;
        TextView name_id,phone_id,team_mebs_id;
        LinearLayout call;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView=itemView;

            name_id=mView.findViewById(R.id.name_id);
            phone_id=mView.findViewById(R.id.phone_id);
            team_mebs_id=mView.findViewById(R.id.team_mebs_id);
            call=mView.findViewById(R.id.call);




        }
    }
}
