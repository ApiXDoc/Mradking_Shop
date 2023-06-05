package com.mradking.mradkingshop.kirana_store.franchige.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mradking.mradkingshop.kirana_store.custmer.activity.Kiranan_shopes_list;
import com.mradking.mradkingshop.kirana_store.custmer.activity.Refer_code_act;

import java.util.HashMap;
import java.util.Map;

public class Data_setup_for_stage_1 extends Activity {



    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    String curret_date_st;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);


        progressDialog.setMessage("Please wait .........");
        progressDialog.setCanceledOnTouchOutside(false);

        progressDialog.show();


        String uid = firebaseAuth.getUid().toString();



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

                            Toast.makeText(Data_setup_for_stage_1.this, "level not found", Toast.LENGTH_SHORT).show();


                        }







                    }}







            }
        });













    }

    private void start_new_act() {

        Intent intent=new Intent(Data_setup_for_stage_1.this, Kiranan_shopes_list.class);
        startActivity(intent);
        finish();
        progressDialog.dismiss();

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




                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("custmers", FieldValue.increment(1));

                                        firebaseFirestore.collection("kirana_user_details").document(level_1_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {


                                                start_new_act();


                                            }
                                        });


                                    }}


                            }
                        });





                    }}
            }
        });





    } private void level_2_data_set_up() {



        firebaseFirestore.collection("kirana_user_details").document(firebaseAuth.getUid().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
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


                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("custmers", FieldValue.increment(1));




                                        firebaseFirestore.collection("kirana_user_details").document(level_1_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {




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

                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("custmers", FieldValue.increment(1));





                                        firebaseFirestore.collection("kirana_user_details").document(level_0_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {




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

                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("custmers", FieldValue.increment(1));



                                        firebaseFirestore.collection("kirana_user_details").document(level_2_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {



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

                                        Map<String, Object> map=new HashMap<String, Object>();

                                        map.put("custmers", FieldValue.increment(1));





                                        firebaseFirestore.collection("kirana_user_details").document(level_1_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {



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

                                        Map<String, Object> map=new HashMap<String, Object>();

                                        map.put("custmers", FieldValue.increment(1));




                                        firebaseFirestore.collection("kirana_user_details").document(level_0_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {

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

                                        Map<String, Object> map=new HashMap<String, Object>();

                                        map.put("custmers", FieldValue.increment(1));



                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {



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



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");

                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("custmers", FieldValue.increment(1));





                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {



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

                                        Map<String, Object> map=new HashMap<String, Object>();
                                        map.put("custmers", FieldValue.increment(1));





                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {



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

                                        Map<String, Object> map=new HashMap<String, Object>();

                                        map.put("custmers", FieldValue.increment(1));




                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {



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

                                        Map<String, Object> map=new HashMap<String, Object>();

                                        map.put("custmers", FieldValue.increment(1));



                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {



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


                                        Map<String, Object> map=new HashMap<String, Object>();

                                        map.put("custmers", FieldValue.increment(1));




                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {



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



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");

                                        Map<String, Object> map=new HashMap<String, Object>();

                                        map.put("custmers", FieldValue.increment(1));





                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {



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



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");

                                        Map<String, Object> map=new HashMap<String, Object>();

                                        map.put("custmers", FieldValue.increment(1));





                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {



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



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");

                                        Map<String, Object> map=new HashMap<String, Object>();

                                        map.put("custmers", FieldValue.increment(1));




                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {

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



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");

                                        Map<String, Object> map=new HashMap<String, Object>();

                                        map.put("custmers", FieldValue.increment(1));


                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {



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



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");

                                        Map<String, Object> map=new HashMap<String, Object>();

                                        map.put("custmers", FieldValue.increment(1));





                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {



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



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");

                                        Map<String, Object> map=new HashMap<String, Object>();

                                        map.put("custmers", FieldValue.increment(1));


                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {



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



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");

                                        Map<String, Object> map=new HashMap<String, Object>();

                                        map.put("custmers", FieldValue.increment(1));




                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {



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



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");

                                        Map<String, Object> map=new HashMap<String, Object>();

                                        map.put("custmers", FieldValue.increment(1));





                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {



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



                        firebaseFirestore.collection("kirana_user_Uid_phone").document(level_3_id_st).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete( Task<DocumentSnapshot> task) {



                                if(task.isComplete()) {

                                    final DocumentSnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.exists()&&documentSnapshot!=null) {



                                        final String level_3_uid = documentSnapshot.getString("refer_uid");

                                        Map<String, Object> map=new HashMap<String, Object>();

                                        map.put("custmers", FieldValue.increment(1));





                                        firebaseFirestore.collection("kirana_user_details").document(level_3_uid).update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete( Task<Void> task) {



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






}
