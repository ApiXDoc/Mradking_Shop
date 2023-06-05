package com.mradking.mradkingshop.kirana_store.shopkeer.fragments.other;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.shopkeer.form.Add_product_request;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Account_act_frg extends Fragment {
    LinearLayout log_out_bt,support_bt,delivery_list,add_product_request,add_number;
    private BottomSheetBehavior mBehavior;
    private BottomSheetDialog mBottomSheetDialog;
    private View bottom_sheet;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    ////////bothom sheet//////

    TextView added_number_bts,your_problem_bts;
    CardView added_bt_bts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.kirana_shops_account_option, container, false);

        log_out_bt=root.findViewById(R.id.log_out_bt);
        support_bt=root.findViewById(R.id.support_bt);
        delivery_list=root.findViewById(R.id.delivery_list);
        add_product_request=root.findViewById(R.id.add_product_request);
        add_number=root.findViewById(R.id.add_number);

        bottom_sheet = root.findViewById(R.id.bottom_sheet);
        mBehavior = BottomSheetBehavior.from(bottom_sheet);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        progressDialog=new ProgressDialog(getActivity());


        add_product_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(), Add_product_request.class);
                startActivity(intent);

            }
        });


        support_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             support_bottom_sheet_show();

            }
        });


        add_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showBottomSheetDialog();
            }
        });
        return root;
    }

    private void support_bottom_sheet_show() {

        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        final View view = getLayoutInflater().inflate(R.layout.kirana_shop_acount_bothom_sheet, null);


        added_number_bts=view.findViewById(R.id.phone_number);
        your_problem_bts=view.findViewById(R.id.problem);
        added_bt_bts=view.findViewById(R.id.ok_bt);
        TextView heading_txt=view.findViewById(R.id.heading_txt);
        TextView bt_text=view.findViewById(R.id.bt_text);
        TextView sub_heading=view.findViewById(R.id.sub_heading);
        CardView remaing_layout=view.findViewById(R.id.remaing_layout);
        View view_for_expend=view.findViewById(R.id.view_for_expend);
        View view_top=view.findViewById(R.id.view_top);
        View view_bottom=view.findViewById(R.id.view_bottom);

        mBottomSheetDialog = new BottomSheetDialog(getActivity());
        mBottomSheetDialog.setContentView(view);
        heading_txt.setText("Support");
        bt_text.setText("Send");

        view_for_expend.setVisibility(View.VISIBLE);



        added_number_bts.setHint("Whats Your Problem");
        added_number_bts.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);

        your_problem_bts.setVisibility(View.VISIBLE);

        added_bt_bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setMessage("Please Wait........");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);

                Map<String,Object>map=new HashMap<>();
                map.put("problem_subject",added_number_bts.getText().toString());
                map.put("problem_explain",your_problem_bts.getText().toString());
                map.put("status","not_soloved");

                firebaseFirestore.collection("kirana_shops_problem")
                        .document(firebaseAuth.getCurrentUser().getUid())
                        .collection("problms")
                        .document()
                        .set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        heading_txt.setText("Your Request Submitted Successfully");
                        sub_heading.setText("we will resolve this issue as soon as possible");
                        remaing_layout.setVisibility(View.GONE);
                        view_top.setVisibility(View.VISIBLE);
                        view_bottom.setVisibility(View.VISIBLE);
                        progressDialog.dismiss();


                    }
                });


            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });


    }

    private void showBottomSheetDialog() {

        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        final View view = getLayoutInflater().inflate(R.layout.kirana_shop_acount_bothom_sheet, null);


        added_number_bts=view.findViewById(R.id.phone_number);
        your_problem_bts=view.findViewById(R.id.problem);
        added_bt_bts=view.findViewById(R.id.ok_bt);
        added_number_bts.setHint("Your Number");

        mBottomSheetDialog = new BottomSheetDialog(getActivity());
        mBottomSheetDialog.setContentView(view);
        added_number_bts.setText("123");


        added_bt_bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseFirestore.collection("ShoprKeeper_detail")
                        .document(firebaseAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                        DocumentSnapshot documentSnapshot=task.getResult();

                        String number_st=documentSnapshot.getString("phonepe_number");
                        Toast.makeText(getActivity(), number_st, Toast.LENGTH_SHORT).show();

                        if(documentSnapshot.getString("phonepe_number")!= null){




                            mBottomSheetDialog.show();
                            Toast.makeText(getActivity(), "here", Toast.LENGTH_SHORT).show();

                        }else {

                           phonepe_added();

                        }



                    }
                });




            }
        });









        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });

    }

    private void phonepe_added() {


        progressDialog.setMessage("Please Wait.......");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);

        Map<String,Object> map=new HashMap<>();
        map.put("phonepe_number",added_number_bts.getText().toString());

        firebaseFirestore.collection("ShoprKeeper_detail")
                .document(firebaseAuth.getCurrentUser().getUid())
                .update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Phone Number Added", Toast.LENGTH_SHORT).show();

                mBottomSheetDialog.dismiss();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getActivity(), "Phone Number Not Added", Toast.LENGTH_SHORT).show();

                mBottomSheetDialog.dismiss();

            }
        });


    }
}
