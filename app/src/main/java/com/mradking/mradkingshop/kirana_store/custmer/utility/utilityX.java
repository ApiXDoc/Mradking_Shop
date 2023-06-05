package com.mradking.mradkingshop.kirana_store.custmer.utility;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.deeplabstudio.fcmsend.FCMSend;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import com.google.firebase.messaging.FirebaseMessaging;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.intface.Firbase_Doc_Call;
import com.mradking.mradkingshop.kirana_store.custmer.intface.Firbase_Query_Call;
import com.mradking.mradkingshop.kirana_store.custmer.intface.Get_future_date_Call;
import com.mradking.mradkingshop.kirana_store.custmer.intface.Get_loc_Calll;
import com.mradking.mradkingshop.kirana_store.custmer.intface.Get_remaing_days;
import com.mradking.mradkingshop.kirana_store.custmer.intface.Get_view;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class utilityX  {
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    private FusedLocationProviderClient fusedLocationProviderClient;
    double lat1 = 0 , long1;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    public   static final String CHANNEL_ID="androidxChannel";
    private static final String CHANNEL_NAME= "status saver";
    private static final String CHANNEL_DESC= "status saver notification";
    private static String serverKey = "AAAAjLqtavI:APA91bFHptGvx-u3tV-dIv7QUAk8QOxuVX3CffNCDp2N3o8CjFsrcf0xsFpoGUEPFO1xKZhXHvz_k5Y0PgvhHuCsKy0WaHlkxP47WztC_Npk45Iq8MElk3ganG0NIP7zgvBJdA_Sswi5";
    BottomSheetDialog  mBottomSheetDialog;
    Context context_1;
    public utilityX(Context context){

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        mBottomSheetDialog  = new BottomSheetDialog(context);
       firebaseFirestore=FirebaseFirestore.getInstance();
       progressDialog=new ProgressDialog(context);
        context_1=context;
        FCMSend.SetServerKey(serverKey);
        notification_chanal_id_version_check(context);

       currentPage=0;
       NUM_PAGES=0;
   }


  public  void  get_doc_query(Query query, Firbase_Query_Call firbase_query_call){

      query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
          @Override
          public void onComplete(@NonNull Task<QuerySnapshot> task) {

              if(task.isSuccessful()){
                  QuerySnapshot documentSnapshots=task.getResult();
                  firbase_query_call.onSuccess(documentSnapshots);
              }else {

                  firbase_query_call.onFailure("Task failed check utilityX");
              }


          }
      }).addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {

              firbase_query_call.onFailure(e.getMessage().toString());
          }
      });


  }

  public void get_detalis(String collection,String document , Firbase_Doc_Call call){

      firebaseFirestore.collection(collection).document(document).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
          @Override
          public void onComplete(@NonNull Task<DocumentSnapshot> task) {

              DocumentSnapshot documentSnapshot=task.getResult();

              if(documentSnapshot.exists()){

                  call.onSuccess(documentSnapshot);

              }else {

                  call.onFailure("document is not exists");

              }



          }
      }).addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {

              call.onFailure(e.getMessage().toString());


          }
      });

  }



 public void get_product_detail(String store_uid,String item_id, Firbase_Doc_Call callback){


     firebaseFirestore.collection("kirana_shopkeeper_product")
             .document(store_uid)
             .collection("proudcts").document(item_id)
             .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                 @Override
                 public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                     DocumentSnapshot documentSnapshot=task.getResult();

                     if(documentSnapshot.exists()){

                         callback.onSuccess(documentSnapshot);

                     }else {

                         callback.onFailure("document is not exists");

                     }



                 }
             }).addOnFailureListener(new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull Exception e) {

                     callback.onFailure(e.getMessage().toString());


                 }
             });



 }

    public void getCurrentLocation(Context context ,Get_loc_Calll call) {

        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {

                    call.location(location);


                }else {

                    Toast.makeText(context, "location is not found ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public String current_date(Context context){
        DatePicker datePicker=new DatePicker(context);
        int year=datePicker.getYear();
        int months=datePicker.getMonth()+1;
        int day=datePicker.getDayOfMonth();
        String date=year+"-"+months+"-"+day;

        return date;
    }



    public  void sub_end_date( String number_months, Context context, Get_future_date_Call call){
        DatePicker datePicker=new DatePicker(context);
        int year=datePicker.getYear();
        int months=datePicker.getMonth()+1;
        int day=datePicker.getDayOfMonth();
        String date=year+"-"+months+"-"+day;

        String url="http://www.shoppingzin.com/test/example/get_future_date.php?date="+date+"&months="+number_months;

        RequestQueue requestQueue= Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    String date = response.getString("future_date");

                    call.on_susess(date);




                } catch (JSONException e) {
                    e.printStackTrace();

                    call.on_fail(String.valueOf(e));
                }
            }
        }, new Response.ErrorListener() {
            // this is the error listener method which
            // we will call if we get any error from API.
            @Override
            public void onErrorResponse(VolleyError error) {
                // below line is use to display a toast message along with our error.
                Toast.makeText(context, "Fail to get data..", Toast.LENGTH_SHORT).show();
            }
        });
        // at last we are adding our json
        // object request to our request
        // queue to fetch all the json data.
        requestQueue.add(jsonObjectRequest);






    };



    public  void days_remain( String date, Context context, Get_remaing_days call){


        String url="http://www.shoppingzin.com/test/example/get_remaing_day.php?date="+date;

        RequestQueue requestQueue= Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    String days = response.getString("days_between");

                    call.sussess(days);




                } catch (JSONException e) {
                    e.printStackTrace();

                    call.sussess(String.valueOf(e).toString());
                }
            }
        }, new Response.ErrorListener() {
            // this is the error listener method which
            // we will call if we get any error from API.
            @Override
            public void onErrorResponse(VolleyError error) {
                // below line is use to display a toast message along with our error.
                Toast.makeText(context, "Fail to get data..", Toast.LENGTH_SHORT).show();
            }
        });
        // at last we are adding our json
        // object request to our request
        // queue to fetch all the json data.
        requestQueue.add(jsonObjectRequest);






    };


    public void notification_send(String token,String total_amt_st) {

        FirebaseMessaging.getInstance().subscribeToTopic(token).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                System.out.println("Subscription successful");
            }
        });



        if(total_amt_st.contentEquals("Pick Up Accept")){


            FCMSend.Builder build1 = new FCMSend.Builder(token)
                    .setTitle("Your Pick Up Accepted")
                    .setBody("Your Pick Up Is Accepted");

            build1.send();


            String result2 = build1.send().Result();
              if(ChekSuccess( result2).contentEquals("Success")){


            }else {
                Log.e("error_in_notification",result2);
            }

        }else {

            FCMSend.Builder build1 = new FCMSend.Builder(token)
                    .setTitle("Congratulation You Earn Commission")
                    .setBody("Your Comission Amount Rs"+total_amt_st);

            build1.send();


            String result2 = build1.send().Result();

        if(ChekSuccess( result2).contentEquals("Success")){

        }else {
            Log.e("error_in_notification",result2);
        }


        }

    }


    private String ChekSuccess(String result) {
        try {
            JSONObject object = new JSONObject(result);
            String success = object.getString("success");
            if (success.equals("1")) {
                return "Success";
            } else if (success.equals("0")) {
                return "Unsuccessful";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Unsuccessful";
    }

    private void notification_chanal_id_version_check(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription(CHANNEL_DESC);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

        }


    }



    public void showBottomSheetDialog(View main_view, View layout_view_with_infrator,Get_view get_view ) {
        BottomSheetBehavior  mBehavior = BottomSheetBehavior.from(main_view);


        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }


        get_view.get_view(layout_view_with_infrator,context_1);

        mBottomSheetDialog.setContentView(layout_view_with_infrator);
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

}


