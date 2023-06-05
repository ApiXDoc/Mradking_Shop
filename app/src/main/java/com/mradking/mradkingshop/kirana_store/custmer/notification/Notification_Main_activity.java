package com.mradking.mradkingshop.kirana_store.custmer.notification;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.deeplabstudio.fcmsend.FCMSend;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.ForwardingConcurrentMap;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mradking.mradkingshop.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class Notification_Main_activity extends Activity {

    public   static final String CHANNEL_ID="androidxChannel";
    private static final String CHANNEL_NAME= "status saver";
    private static final String CHANNEL_DESC= "status saver notification";
    private EditText mTitle, mMessage;
    Button button;


    private static String serverKey = "AAAAjLqtavI:APA91bFHptGvx-u3tV-dIv7QUAk8QOxuVX3CffNCDp2N3o8CjFsrcf0xsFpoGUEPFO1xKZhXHvz_k5Y0PgvhHuCsKy0WaHlkxP47WztC_Npk45Iq8MElk3ganG0NIP7zgvBJdA_Sswi5";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirana_notification_test);

        mTitle=findViewById(R.id.mTitle);
        mMessage=findViewById(R.id.mMessage);
        button=findViewById(R.id.mSend);


        FCMSend.SetServerKey(serverKey);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                FirebaseMessaging.getInstance().getToken()
                        .addOnCompleteListener(new OnCompleteListener<String>() {
                            @Override
                            public void onComplete(@NonNull Task<String> task) {
                                if (!task.isSuccessful()) {
                                    return;
                                }
                                String token = task.getResult();

                                notification_send(token);


                            }
                        });

                // Subscribe To Topic




            }
        });






    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription(CHANNEL_DESC);

            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

        }



        // Get Device Token

    }

    private void notification_send(String token) {

        FirebaseMessaging.getInstance().subscribeToTopic(token).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                System.out.println("Subscription successful");
            }
        });

        HashMap<String, String> data = new HashMap<>();
        data.put("key1", "data 1");
        data.put("key2", "data 2");
        data.put("key3", "data 3");

        FCMSend.Builder build1 = new FCMSend.Builder(token)
                .setTitle(mTitle.getText().toString())
                .setBody(mMessage.getText().toString())
                .setClickAction("<Action>"); // Optional// Optional
        build1.send();


        String result2 = build1.send().Result();

        ChekSuccess( result2);

        Toast.makeText(Notification_Main_activity.this, ChekSuccess(result2), Toast.LENGTH_SHORT).show();

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
}