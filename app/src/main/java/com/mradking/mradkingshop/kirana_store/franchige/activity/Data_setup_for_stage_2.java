package com.mradking.mradkingshop.kirana_store.franchige.activity;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.deeplabstudio.fcmsend.FCMSend;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.kirana_store.custmer.intface.Get_future_date_Call;
import com.mradking.mradkingshop.kirana_store.custmer.utility.utilityX;

import java.util.HashMap;
import java.util.Map;

public class Data_setup_for_stage_2 extends Activity {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    String curret_date_st;
    utilityX UtilityX;
    String uid;
    public   static final String CHANNEL_ID="androidxChannel";
    private static final String CHANNEL_NAME= "status saver";
    private static final String CHANNEL_DESC= "status saver notification";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UtilityX=new utilityX(Data_setup_for_stage_2.this);

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
         uid = firebaseAuth.getCurrentUser().getUid().toString();



        progressDialog.setMessage("Please wait .........");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();



        data_set1();



    }



    private void data_set1() {



        if(getIntent().getExtras().getString("plane")
                .contentEquals("Starter - 3 Month Membership")){

            UtilityX.sub_end_date("3", this, new Get_future_date_Call() {
                @Override
                public void on_susess(String date) {

                    Map<String,Object>map=new HashMap<>();
                    map.put("starting_date_sub",UtilityX.current_date(Data_setup_for_stage_2.this));
                    map.put("end_date_sub",date);
                    map.put("buying_status","yes");

                    firebaseFirestore.collection("kirana_user_details").document(uid).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            data_set();


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

                }

                @Override
                public void on_fail(String error) {

                    Toast.makeText(Data_setup_for_stage_2.this, "date not geted try again", Toast.LENGTH_SHORT).show();
                }
            });



        }else if(getIntent().getExtras().getString("plane")
                .contentEquals("Business - 6 Month Membership")){


            UtilityX.sub_end_date("6", this, new Get_future_date_Call() {
                @Override
                public void on_susess(String date) {

                    Map<String,Object>map=new HashMap<>();
                    map.put("starting_date_sub",UtilityX.current_date(Data_setup_for_stage_2.this));
                    map.put("end_date_sub",date);
                    map.put("buying_status","yes");

                    firebaseFirestore.collection("kirana_user_details").document(uid).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            data_set();


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

                }

                @Override
                public void on_fail(String error) {

                    Toast.makeText(Data_setup_for_stage_2.this, "date not geted try again", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    private void data_set() {
        firebaseFirestore.collection("kirana_user_details").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {



                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {

                        String level_st=documentSnapshot.getString("level");



                        if(level_st.contentEquals("0")){

                            level_0_data_set_up();


                        }else if(level_st.contentEquals("1")){

                            level_1_data_set_up();


                        }else if(level_st.contentEquals("2")){

                            level_2_data_set_up();


                        }else if(level_st.contentEquals("3")){
                            level_3_data_set_up();



                        }else if(level_st.contentEquals("4")){
                            level_4_data_set_up();



                        }else if(level_st.contentEquals("5")){

                            level_5_data_set_up();


                        }else if(level_st.contentEquals("6")){


                            level_6_data_set_up();

                        }else {

                            Toast.makeText(Data_setup_for_stage_2.this, "level not found", Toast.LENGTH_SHORT).show();


                        }







                    }}







            }
        });



    }


    private void level_0_data_set_up() {


        progressDialog.dismiss();
        Toast.makeText(this, "its level 0", Toast.LENGTH_SHORT).show();

    }

    private void level_1_data_set_up() {
        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_1_id_st = documentSnapshot.getString("level_0_id");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_1_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_1_uid = documentSnapshot.getString("refer_uid");
                                        final String message_token_st = documentSnapshot.getString("user_message_token");


                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins", FieldValue.increment(100));
                                        map.put("partners",FieldValue.increment(1));
                                        firebaseFirestore.collection("kirana_user_details").document(level_1_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {

                                                UtilityX.notification_send(message_token_st,"100");
                                                start_new_act();


                                            }
                                        });



                                    }}


                            }
                        });





                    }}
            }
        });





    }

    private void start_new_act() {
        progressDialog.dismiss();

        Intent intent=new Intent(Data_setup_for_stage_2.this,Sub_cribe_act_2.class);
        startActivity(intent);
        finish();

    }

    private void level_2_data_set_up() {



        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_1_id_st = documentSnapshot.getString("level_1_id");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_1_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_1_uid = documentSnapshot.getString("refer_uid");
                                        final String message_token_st = documentSnapshot.getString("user_message_token");


                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins", FieldValue.increment(100));
                                        map.put("partners",FieldValue.increment(1));



                                        firebaseFirestore.collection("kirana_user_details").document(level_1_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {



                                                UtilityX.notification_send(message_token_st,"100");
                                                data_set_up_level_2_sub_1();





                                            }
                                        });






                                    }}


                            }
                        });





                    }}
            }
        });






    }

    private void data_set_up_level_2_sub_1() {

        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_0_id_st = documentSnapshot.getString("level_0_id");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_0_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_0_uid = documentSnapshot.getString("refer_uid");

                                        final String message_token_st = documentSnapshot.getString("user_message_token");


                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins", FieldValue.increment(20));




                                        firebaseFirestore.collection("kirana_user_details").document(level_0_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {



                                                UtilityX.notification_send(message_token_st,"20");
                                                start_new_act();




                                            }
                                        });


                                    }}


                            }
                        });





                    }}
            }
        });








    }

    private void level_3_data_set_up() {


        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_2_id_st = documentSnapshot.getString("level_2_id");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_2_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_2_uid = documentSnapshot.getString("refer_uid");
                                        final String message_token_st = documentSnapshot.getString("user_message_token");

                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins", FieldValue.increment(100));
                                        map.put("partners",FieldValue.increment(1));

                                        firebaseFirestore.collection("kirana_user_details").document(level_2_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {


                                                UtilityX.notification_send(message_token_st,"100");

                                                level_3_data_set_up_sub_1();


                                            }
                                        });






                                    }}


                            }
                        });





                    }}
            }
        });







    }

    private void level_3_data_set_up_sub_1() {

        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_1_id_st = documentSnapshot.getString("level_1_id");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_1_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_1_uid = documentSnapshot.getString("refer_uid");
                                        final String message_token_st = documentSnapshot.getString("user_message_token");

                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins", FieldValue.increment(20));




                                        firebaseFirestore.collection("kirana_user_details").document(level_1_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {


                                                UtilityX.notification_send(message_token_st,"20");

                                                level_3_data_set_up_sub_0();


                                            }
                                        });





                                    }}


                            }
                        });





                    }}
            }
        });








    }

    private void level_3_data_set_up_sub_0() {

        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_0_id_st = documentSnapshot.getString("level_0_id");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_0_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_0_uid = documentSnapshot.getString("refer_uid");
                                        final String message_token_st = documentSnapshot.getString("user_message_token");

                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins", FieldValue.increment(20));





                                        firebaseFirestore.collection("kirana_user_details").document(level_0_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {

                                                UtilityX.notification_send(message_token_st,"20");
                                                start_new_act();

                                            }
                                        });
                                    }}


                            }
                        });





                    }}
            }
        });






    }

    private void level_4_data_set_up() {

        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_3_id_st = documentSnapshot.getString("level_3_id");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");
                                        final String message_token_st = documentSnapshot.getString("user_message_token");



                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins", FieldValue.increment(100));
                                        map.put("partners",FieldValue.increment(1));

                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {


                                                UtilityX.notification_send(message_token_st,"100");

                                                level_4_data_set_up_sub_2();



                                            }
                                        });




                                    }}


                            }
                        });





                    }}
            }
        });




    }

    private void level_4_data_set_up_sub_2() {


        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_3_id_st = documentSnapshot.getString("level_2_id");
                        final String message_token_st = documentSnapshot.getString("user_message_token");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");

                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins", FieldValue.increment(20));




                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {


                                                UtilityX.notification_send(message_token_st,"20");

                                                level_4_data_set_up_sub_1();



                                            }
                                        });







                                    }}


                            }
                        });





                    }}
            }
        });


    }

    private void level_4_data_set_up_sub_1() {


        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_3_id_st = documentSnapshot.getString("level_1_id");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");
                                        final String message_token_st = documentSnapshot.getString("user_message_token");

                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins", FieldValue.increment(20));
                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {


                                                UtilityX.notification_send(message_token_st,"20");

                                                level_4_data_set_up_sub_0();



                                            }
                                        });



                                    }}


                            }
                        });





                    }}
            }
        });



    }

    private void level_4_data_set_up_sub_0() {

        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_3_id_st = documentSnapshot.getString("level_0_id");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");
                                        final String message_token_st = documentSnapshot.getString("user_message_token");


                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins", FieldValue.increment(20));




                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {

                                                UtilityX.notification_send(message_token_st,"20");

                                                start_new_act();


                                            }
                                        });







                                    }}


                            }
                        });





                    }}
            }
        });





    }

    private void level_5_data_set_up() {

        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_3_id_st = documentSnapshot.getString("level_4_id");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");
                                        final String message_token_st = documentSnapshot.getString("user_message_token");

                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins", FieldValue.increment(100));
                                        map.put("partners",FieldValue.increment(1));

                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {


                                                UtilityX.notification_send(message_token_st,"100");

                                                level_5_data_set_up_sub_3();

                                            }
                                        });


                                    }}


                            }
                        });





                    }}
            }
        });







    }

    private void level_5_data_set_up_sub_3() {

        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_3_id_st = documentSnapshot.getString("level_3_id");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");
                                        final String message_token_st = documentSnapshot.getString("user_message_token");

                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins", FieldValue.increment(20));



                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {


                                                UtilityX.notification_send(message_token_st,"20");

                                                level_5_data_set_up_sub_2();

                                            }
                                        });





                                    }}


                            }
                        });





                    }}
            }
        });



    }

    private void level_5_data_set_up_sub_2() {

        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_3_id_st = documentSnapshot.getString("level_2_id");
                        final String message_token_st = documentSnapshot.getString("user_message_token");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");

                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins",FieldValue.increment(20));



                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {


                                                UtilityX.notification_send(message_token_st,"20");

                                                level_5_data_set_up_sub_1();

                                            }
                                        });







                                    }}


                            }
                        });





                    }}
            }
        });







    }

    private void level_5_data_set_up_sub_1() {

        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_3_id_st = documentSnapshot.getString("level_1_id");
                        final String message_token_st = documentSnapshot.getString("user_message_token");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");


                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins",FieldValue.increment(20));


                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {


                                                UtilityX.notification_send(message_token_st,"20");

                                                level_5_data_set_up_sub_0();

                                            }
                                        });




                                    }}


                            }
                        });





                    }}
            }
        });






    }

    private void level_5_data_set_up_sub_0() {

        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_3_id_st = documentSnapshot.getString("level_0_id");
                        final String message_token_st = documentSnapshot.getString("user_message_token");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");

                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins", FieldValue.increment(20));



                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {
                                                UtilityX.notification_send(message_token_st,"20");

                                                start_new_act();

                                            }
                                        });


                                    }}


                            }
                        });





                    }}
            }
        });





    }

    private void level_6_data_set_up() {


        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_3_id_st = documentSnapshot.getString("level_5_id");
                        final String message_token_st = documentSnapshot.getString("user_message_token");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");

                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins", FieldValue.increment(100));
                                        map.put("partners",FieldValue.increment(1));

                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {


                                                UtilityX.notification_send(message_token_st,"100");

                                                level_6_data_set_up_sub_4();

                                            }
                                        });





                                    }}


                            }
                        });





                    }}
            }
        });












    }

    private void level_6_data_set_up_sub_4() {


        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_3_id_st = documentSnapshot.getString("level_4_id");
                        final String message_token_st = documentSnapshot.getString("user_message_token");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");

                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins", FieldValue.increment(20));



                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {


                                                UtilityX.notification_send(message_token_st,"20");

                                                level_6_data_set_up_sub_3();

                                            }
                                        });




                                    }}


                            }
                        });





                    }}
            }
        });




    }

    private void level_6_data_set_up_sub_3() {



        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_3_id_st = documentSnapshot.getString("level_3_id");
                        final String message_token_st = documentSnapshot.getString("user_message_token");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");

                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins", FieldValue.increment(20));





                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {

                                                UtilityX.notification_send(message_token_st,"20");


                                                level_6_data_set_up_sub_2();

                                            }
                                        });







                                    }}


                            }
                        });





                    }}
            }
        });



    }

    private void level_6_data_set_up_sub_2() {



        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_3_id_st = documentSnapshot.getString("level_2_id");
                        final String message_token_st = documentSnapshot.getString("user_message_token");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");

                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins", FieldValue.increment(20));




                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {


                                                UtilityX.notification_send(message_token_st,"20");

                                                level_6_data_set_up_sub_1();

                                            }
                                        });


                                    }}


                            }
                        });





                    }}
            }
        });




    }

    private void level_6_data_set_up_sub_1() {



        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_3_id_st = documentSnapshot.getString("level_1_id");
                        final String message_token_st = documentSnapshot.getString("user_message_token");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");
                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins", FieldValue.increment(20));




                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {


                                                UtilityX.notification_send(message_token_st,"20");

                                                level_6_data_set_up_sub_0();

                                            }
                                        });


                                    }}


                            }
                        });





                    }}
            }
        });






    }

    private void level_6_data_set_up_sub_0() {


        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete( Task<DocumentSnapshot> task) {

                if(task.isComplete()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()&&documentSnapshot!=null) {


                        final String level_3_id_st = documentSnapshot.getString("level_0_id");
                        final String message_token_st = documentSnapshot.getString("user_message_token");



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");

                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("coins", FieldValue.increment(20));



                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {



                                                firebaseFirestore.collection("user").document(firebaseAuth.getUid().toString()).update("bussines_account_status","stage2").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        UtilityX.notification_send(message_token_st,"20");

                                                        start_new_act();

                                                    }
                                                });



                                            }
                                        });




                                    }}


                            }
                        });





                    }}
            }
        });






    }






}
