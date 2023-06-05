package com.mradking.mradkingshop.kirana_store.custmer.notification;

import static android.content.Context.MODE_PRIVATE;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.activity.Splash;
import com.mradking.mradkingshop.kirana_store.custmer.activity.kirana_home_main_act;


public class NotificationHelper {

    public static void displayNOtificatiion(Context context,String title,String body){




        Intent intent=new Intent(context, Splash.class);

        intent.putExtra("title",title);









        PendingIntent pendingIntent= PendingIntent.getActivity(
                context,100,intent,PendingIntent.FLAG_CANCEL_CURRENT);


        NotificationCompat.Builder builder=

                new NotificationCompat.Builder(context, Notification_Main_activity.CHANNEL_ID)
                        .setSmallIcon(R.mipmap.app_icon)
                        .setContentTitle(title)
                        .setContentIntent(pendingIntent)
                        .setContentText(body)
                          .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(context);

        notificationManagerCompat.notify(1,builder.build());





    }


}
