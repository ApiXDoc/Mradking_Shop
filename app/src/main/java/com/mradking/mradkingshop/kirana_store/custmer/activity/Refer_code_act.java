package com.mradking.mradkingshop.kirana_store.custmer.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.intface.Firbase_Doc_Call;
import com.mradking.mradkingshop.kirana_store.custmer.utility.utilityX;
import com.mradking.mradkingshop.kirana_store.franchige.activity.Data_setup_for_stage_1;

import java.util.HashMap;
import java.util.Map;

public class Refer_code_act extends Activity {

    Button refer_bt;
    EditText refer_code_et,conform_refer_code_et;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    TextView no_refer_text;
    utilityX UtilityX;
    String uid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refer_code_inter_act);

        refer_bt=(Button)findViewById(R.id.refer_bt1) ;
        refer_code_et=(EditText)findViewById(R.id.username) ;
        conform_refer_code_et=(EditText)findViewById(R.id.userPhone) ;
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

         UtilityX =new utilityX(Refer_code_act.this);
        uid=firebaseAuth.getCurrentUser().getUid().toString();
        no_refer_text= findViewById(R.id.no_refer_text);

            check();


         no_refer_text.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 progressDialog.setMessage("Please wait......");
                 progressDialog.setCanceledOnTouchOutside(false);
                 progressDialog.show();

                 set_refer_uid("+918130340674");



             }
         });


        refer_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Please wait......");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                if(refer_code_et.getText().toString().contentEquals(conform_refer_code_et.getText().toString())){


                   set_refer_uid("+91"+refer_code_et.getText().toString());





                }else {

                    progressDialog.dismiss();
                    refer_code_et.setText("");
                    conform_refer_code_et.setText("");
                    Toast.makeText(Refer_code_act.this, "your refer code is not match ", Toast.LENGTH_SHORT).show();

                }


            }
        });



    }

    private void check() {

        firebaseFirestore.collection("kirana_user_details").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                DocumentSnapshot documentSnapshot=task.getResult();
                String refer_status_st=documentSnapshot.getString("refer_status");

                if(refer_status_st.contentEquals("yes")){

                    start_new_act();

                }else {

                }




            }
        });

    }

    private void set_refer_uid(String refer_phone_number) {


          UtilityX.get_detalis("kirana_user_Uid_phone",
                refer_phone_number, new Firbase_Doc_Call() {

            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

               String refer_uid=documentSnapshot.getString("refer_uid");
                String refer_message_token=documentSnapshot.getString("user_message_token");
                String refer_level=documentSnapshot.getString("level");

                Map<String,Object>map1=new HashMap<>();
                map1.put("refer_uid",refer_uid);
                map1.put("refer_message_token",refer_message_token);
                firebaseFirestore.collection("kirana_user_details")
                        .document(uid).update(map1).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                if(refer_level.contentEquals("0")){

                                    level_zero_set_up(refer_phone_number);
                                } else if(refer_level.contentEquals("1")){

                                    level_one_set_up(refer_phone_number);

                                }else if(refer_level.contentEquals("2")){


                                    level_two_set_up(refer_phone_number);

                                }else if(refer_level.contentEquals("3")){

                                    level_three_set_up(refer_phone_number);

                                }else if(refer_level.contentEquals("4")){

                                    level_four_set_up(refer_phone_number);

                                }else if(refer_level.contentEquals("5")){

                                    level_five_set_up(refer_phone_number);

                                }else if(refer_level.contentEquals("6")){

                                    level_six_set_up(refer_phone_number);
                                } else if(refer_level.contentEquals("7")){

                                    level_seven_set_up(refer_phone_number);

                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(Refer_code_act.this, "error in data post", Toast.LENGTH_SHORT).show();

                            }
                        });


            }

            @Override
            public void onFailure(String error) {

                progressDialog.dismiss();
                Toast.makeText(Refer_code_act.this, error, Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void level_seven_set_up(String refer_phone_number) {

        firebaseFirestore.collection("kirana_user_details").document(uid).update("level","3").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {


                firebaseFirestore.collection("kirana_user_Uid_phone").document(firebaseAuth.getCurrentUser().getPhoneNumber()).update("level","3").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        final Map<String,Object>map2=new HashMap<String,Object>();
                        map2.put("level_0_id",refer_phone_number);
                        firebaseFirestore.collection("kirana_user_details").document(uid).update(map2).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {



                                firebaseFirestore.collection("kirana_user_Uid_phone").document(refer_phone_number).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete( Task<DocumentSnapshot> task) {


                                        if(task.isComplete()) {

                                            DocumentSnapshot documentSnapshot = task.getResult();
                                            if(documentSnapshot.exists()&&documentSnapshot!=null) {

                                                final String refer_id=documentSnapshot.getString("refer_uid");
                                                firebaseFirestore.collection("kirana_user_details").document(refer_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete( Task<DocumentSnapshot> task) {




                                                        if(task.isComplete()) {

                                                            DocumentSnapshot documentSnapshot = task.getResult();
                                                            if(documentSnapshot.exists()&&documentSnapshot!=null) {


                                                                final String level_3_id_st = documentSnapshot.getString("level_3_id");
                                                                final String level_5_id_st = documentSnapshot.getString("level_5_id");
                                                                final String level_6_id_st = documentSnapshot.getString("level_6_id");


                                                                Map<String,Object>data_map=new HashMap<String,Object>();

                                                                data_map.put("level_1_id",level_6_id_st);
                                                                data_map.put("level_2_id",level_5_id_st);

                                                                firebaseFirestore.collection("kirana_user_details").document(uid).update(data_map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {


                                                                        start_new_act();


                                                                    }
                                                                })  ;



                                                            }}





                                                    }
                                                })  ;




                                            }}


                                    }
                                });






                            }
                        });









                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {

                        progressDialog.dismiss();
                        Toast.makeText(Refer_code_act.this, "level_is no set", Toast.LENGTH_SHORT).show();


                    }
                });




            }
        });







    





}

    private void start_new_act() {

        Intent intent=new Intent(Refer_code_act.this, Data_setup_for_stage_1.class);
        startActivity(intent);
        finish();
        progressDialog.dismiss();

    }


    private void level_six_set_up(String refer_phone_number) {
        firebaseFirestore.collection("kirana_user_details").document(uid).update("level","7").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {


                firebaseFirestore.collection("kirana_user_Uid_phone").document(firebaseAuth.getCurrentUser().getPhoneNumber()).update("level","7").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        final Map<String,Object>map2=new HashMap<String,Object>();
                        map2.put("level_6_id",refer_phone_number);
                        firebaseFirestore.collection("kirana_user_details").document(uid).update(map2).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {



                                firebaseFirestore.collection("kirana_user_Uid_phone").document(refer_phone_number).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete( Task<DocumentSnapshot> task) {


                                        if(task.isComplete()) {

                                            DocumentSnapshot documentSnapshot = task.getResult();
                                            if(documentSnapshot.exists()&&documentSnapshot!=null) {

                                                final String refer_id=documentSnapshot.getString("refer_uid");
                                                firebaseFirestore.collection("kirana_user_details").document(refer_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete( Task<DocumentSnapshot> task) {




                                                        if(task.isComplete()) {

                                                            DocumentSnapshot documentSnapshot = task.getResult();
                                                            if(documentSnapshot.exists()&&documentSnapshot!=null) {


                                                                final String level_0_id_st = documentSnapshot.getString("level_0_id");
                                                                final String level_1_id_st = documentSnapshot.getString("level_1_id");
                                                                final String level_2_id_st = documentSnapshot.getString("level_2_id");
                                                                final String level_3_id_st = documentSnapshot.getString("level_3_id");
                                                                final String level_4_id_st = documentSnapshot.getString("level_4_id");
                                                                final String level_5_id_st = documentSnapshot.getString("level_5_id");


                                                                Map<String,Object>data_map=new HashMap<String,Object>();

                                                                data_map.put("level_0_id",level_0_id_st);
                                                                data_map.put("level_1_id",level_1_id_st);
                                                                data_map.put("level_2_id",level_2_id_st);
                                                                data_map.put("level_3_id",level_3_id_st);
                                                                data_map.put("level_4_id",level_4_id_st);
                                                                data_map.put("level_5_id",level_5_id_st);

                                                                firebaseFirestore.collection("kirana_user_details").document(uid).update(data_map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {


                                                                        start_new_act();



                                                                    }
                                                                })  ;



                                                            }}





                                                    }
                                                })  ;




                                            }}


                                    }
                                });






                            }
                        });









                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {

                        progressDialog.dismiss();
                        Toast.makeText(Refer_code_act.this, "level_is no set", Toast.LENGTH_SHORT).show();


                    }
                });




            }
        });



    }

    private void level_five_set_up(String refer_phone_number) {

        firebaseFirestore.collection("kirana_user_details").document(uid).update("level","6").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {


                firebaseFirestore.collection("kirana_user_Uid_phone").document(firebaseAuth.getCurrentUser().getPhoneNumber()).update("level","6").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        final Map<String,Object>map2=new HashMap<String,Object>();
                        map2.put("level_5_id",refer_phone_number);
                        firebaseFirestore.collection("kirana_user_details").document(uid).update(map2).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {



                                firebaseFirestore.collection("kirana_user_Uid_phone").document(refer_phone_number).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete( Task<DocumentSnapshot> task) {


                                        if(task.isComplete()) {

                                            DocumentSnapshot documentSnapshot = task.getResult();
                                            if(documentSnapshot.exists()&&documentSnapshot!=null) {

                                                final String refer_id=documentSnapshot.getString("refer_uid");
                                                firebaseFirestore.collection("kirana_user_details").document(refer_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete( Task<DocumentSnapshot> task) {




                                                        if(task.isComplete()) {

                                                            DocumentSnapshot documentSnapshot = task.getResult();
                                                            if(documentSnapshot.exists()&&documentSnapshot!=null) {


                                                                final String level_0_id_st = documentSnapshot.getString("level_0_id");
                                                                final String level_1_id_st = documentSnapshot.getString("level_1_id");
                                                                final String level_2_id_st = documentSnapshot.getString("level_2_id");
                                                                final String level_3_id_st = documentSnapshot.getString("level_3_id");
                                                                final String level_4_id_st = documentSnapshot.getString("level_4_id");


                                                                Map<String,Object>data_map=new HashMap<String,Object>();

                                                                data_map.put("level_0_id",level_0_id_st);
                                                                data_map.put("level_1_id",level_1_id_st);
                                                                data_map.put("level_2_id",level_2_id_st);
                                                                data_map.put("level_3_id",level_3_id_st);
                                                                data_map.put("level_4_id",level_4_id_st);

                                                                firebaseFirestore.collection("kirana_user_details").document(uid).update(data_map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {

                                                                        start_new_act();



                                                                    }
                                                                })  ;



                                                            }}





                                                    }
                                                })  ;




                                            }}


                                    }
                                });






                            }
                        });









                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {

                        progressDialog.dismiss();
                        Toast.makeText(Refer_code_act.this, "level_is no set", Toast.LENGTH_SHORT).show();


                    }
                });




            }
        });


    }

    private void level_four_set_up(String refer_phone_number) {

        firebaseFirestore.collection("kirana_user_details").document(uid).update("level","5").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {


                firebaseFirestore.collection("kirana_user_Uid_phone").document(firebaseAuth.getCurrentUser().getPhoneNumber()).update("level","5").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        final Map<String,Object>map2=new HashMap<String,Object>();
                        map2.put("level_4_id",refer_phone_number);
                        firebaseFirestore.collection("kirana_user_details").document(uid).update(map2).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {



                                firebaseFirestore.collection("kirana_user_Uid_phone").document(refer_phone_number).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete( Task<DocumentSnapshot> task) {


                                        if(task.isComplete()) {

                                            DocumentSnapshot documentSnapshot = task.getResult();
                                            if(documentSnapshot.exists()&&documentSnapshot!=null) {

                                                final String refer_id=documentSnapshot.getString("refer_uid");
                                                firebaseFirestore.collection("kirana_user_details").document(refer_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete( Task<DocumentSnapshot> task) {




                                                        if(task.isComplete()) {

                                                            DocumentSnapshot documentSnapshot = task.getResult();
                                                            if(documentSnapshot.exists()&&documentSnapshot!=null) {


                                                                final String level_0_id_st = documentSnapshot.getString("level_0_id");
                                                                final String level_1_id_st = documentSnapshot.getString("level_1_id");
                                                                final String level_2_id_st = documentSnapshot.getString("level_2_id");
                                                                final String level_3_id_st = documentSnapshot.getString("level_3_id");


                                                                Map<String,Object>data_map=new HashMap<String,Object>();

                                                                data_map.put("level_0_id",level_0_id_st);
                                                                data_map.put("level_1_id",level_1_id_st);
                                                                data_map.put("level_2_id",level_2_id_st);
                                                                data_map.put("level_3_id",level_3_id_st);

                                                                firebaseFirestore.collection("kirana_user_details").document(uid).update(data_map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {


                                                                        start_new_act();



                                                                    }
                                                                })  ;



                                                            }}





                                                    }
                                                })  ;




                                            }}


                                    }
                                });






                            }
                        });









                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {

                        progressDialog.dismiss();
                        Toast.makeText(Refer_code_act.this, "level_is no set", Toast.LENGTH_SHORT).show();


                    }
                });




            }
        });

    }

    private void level_three_set_up(String refer_phone_number) {

        firebaseFirestore.collection("kirana_user_details").document(uid).update("level","4").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {


                firebaseFirestore.collection("kirana_user_Uid_phone").document(firebaseAuth.getCurrentUser().getPhoneNumber()).update("level","4").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        final Map<String,Object>map2=new HashMap<String,Object>();
                        map2.put("level_3_id",refer_phone_number);
                        firebaseFirestore.collection("kirana_user_details").document(uid).update(map2).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {



                                firebaseFirestore.collection("kirana_user_Uid_phone").document(refer_phone_number).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete( Task<DocumentSnapshot> task) {


                                        if(task.isComplete()) {

                                            DocumentSnapshot documentSnapshot = task.getResult();
                                            if(documentSnapshot.exists()&&documentSnapshot!=null) {

                                                final String refer_id=documentSnapshot.getString("refer_uid");
                                                firebaseFirestore.collection("kirana_user_details").document(refer_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete( Task<DocumentSnapshot> task) {




                                                        if(task.isComplete()) {

                                                            DocumentSnapshot documentSnapshot = task.getResult();
                                                            if(documentSnapshot.exists()&&documentSnapshot!=null) {


                                                                final String level_0_id_st = documentSnapshot.getString("level_0_id");
                                                                final String level_1_id_st = documentSnapshot.getString("level_1_id");
                                                                final String level_2_id_st = documentSnapshot.getString("level_2_id");


                                                                Map<String,Object>data_map=new HashMap<String,Object>();

                                                                data_map.put("level_0_id",level_0_id_st);
                                                                data_map.put("level_1_id",level_1_id_st);
                                                                data_map.put("level_2_id",level_2_id_st);

                                                                firebaseFirestore.collection("kirana_user_details").document(uid).update(data_map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {


                                                                        start_new_act();



                                                                    }
                                                                })  ;



                                                            }}





                                                    }
                                                })  ;




                                            }}


                                    }
                                });






                            }
                        });









                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {

                        progressDialog.dismiss();
                        Toast.makeText(Refer_code_act.this, "level_is no set", Toast.LENGTH_SHORT).show();


                    }
                });




            }
        });

    }

    private void level_two_set_up(String refer_phone_number) {

        firebaseFirestore.collection("kirana_user_details").document(uid).update("level","3").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {


                firebaseFirestore.collection("kirana_user_Uid_phone").document(firebaseAuth.getCurrentUser().getPhoneNumber()).update("level","3").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Map<String,Object>map2=new HashMap<String,Object>();
                        map2.put("level_2_id",refer_phone_number);
                        firebaseFirestore.collection("kirana_user_details").document(uid).update(map2).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {



                                firebaseFirestore.collection("kirana_user_Uid_phone").document(refer_phone_number).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete( Task<DocumentSnapshot> task) {


                                        if(task.isComplete()) {

                                            DocumentSnapshot documentSnapshot = task.getResult();
                                            if(documentSnapshot.exists()&&documentSnapshot!=null) {

                                                final String refer_id=documentSnapshot.getString("refer_uid");
                                                firebaseFirestore.collection("kirana_user_details").document(refer_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete( Task<DocumentSnapshot> task) {




                                                        if(task.isComplete()) {

                                                            DocumentSnapshot documentSnapshot = task.getResult();
                                                            if(documentSnapshot.exists()&&documentSnapshot!=null) {


                                                                final String level_0_id_st = documentSnapshot.getString("level_0_id");
                                                                final String level_1_id_st = documentSnapshot.getString("level_1_id");


                                                                Map<String,Object>data_map=new HashMap<String,Object>();

                                                                data_map.put("level_0_id",level_0_id_st);
                                                                data_map.put("level_1_id",level_1_id_st);

                                                                firebaseFirestore.collection("kirana_user_details").document(uid).update(data_map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {


                                                                        start_new_act();



                                                                    }
                                                                })  ;



                                                            }}





                                                    }
                                                })  ;




                                            }}


                                    }
                                });






                            }
                        });









                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {

                        progressDialog.dismiss();
                        Toast.makeText(Refer_code_act.this, "level_is no set", Toast.LENGTH_SHORT).show();


                    }
                });




            }
        });



    }

    private void level_zero_set_up(String refer_phone_number) {

        firebaseFirestore.collection("kirana_user_details").document(uid)
                .update("level","1").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        firebaseFirestore.collection("kirana_user_Uid_phone")
                                .document(firebaseAuth.getCurrentUser().getPhoneNumber())
                                .update("level","1").addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                        firebaseFirestore.collection("kirana_user_details").document(uid)
                                                .update("level_0_id",refer_phone_number).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {


                                                        start_new_act();

                                                    }
                                                });



                                    }
                                });


                    }
                });




    }

    private void level_one_set_up(String refer_phone_number) {

        firebaseFirestore.collection("kirana_user_details").document(uid).update("level","2").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {



                firebaseFirestore.collection("kirana_user_Uid_phone").document(firebaseAuth.getCurrentUser().getPhoneNumber()).update("level","2").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Map<String,Object> map2=new HashMap<String,Object>();
                        map2.put("level_1_id",refer_phone_number);



                        firebaseFirestore.collection("kirana_user_details").document(uid).update(map2).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                firebaseFirestore.collection("kirana_user_Uid_phone").document(refer_phone_number).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete( Task<DocumentSnapshot> task) {
                                        if(task.isComplete()) {

                                            DocumentSnapshot documentSnapshot = task.getResult();
                                            if(documentSnapshot.exists()&&documentSnapshot!=null) {

                                                final String refer_id=documentSnapshot.getString("refer_uid");

                                                firebaseFirestore.collection("kirana_user_details").document(refer_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete( Task<DocumentSnapshot> task) {

                                                        if(task.isComplete()) {

                                                            DocumentSnapshot documentSnapshot = task.getResult();
                                                            if(documentSnapshot.exists()&&documentSnapshot!=null) {


                                                                final String level_0_id_st = documentSnapshot.getString("level_0_id");


                                                                firebaseFirestore.collection("kirana_user_details").document(uid).update("level_0_id",level_0_id_st).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {


                                                                        start_new_act();

                                                                    }
                                                                })  ;




                                                            }}

                                                    }
                                                });






                                            }}



                                    }
                                });






                            }
                        });




                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {

                        progressDialog.dismiss();
                        Toast.makeText(Refer_code_act.this, "level_is no set", Toast.LENGTH_SHORT).show();


                    }
                });




            }
        });



    }



}
