package com.shubham.covid_19track.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.shubham.covid_19track.OnBoardingActivity;
import com.shubham.covid_19track.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static int NOTIFICATION_ID = 1;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        generateNotification(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle());

    }


    private void generateNotification(String body, String title) {

        Intent intent = new Intent(this, OnBoardingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent =PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

         Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent);

           // notificationBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        if (NOTIFICATION_ID > 1073741824){
            NOTIFICATION_ID = 0;
        }

        notificationManager.notify(NOTIFICATION_ID++, notificationBuilder.build() );

    }
}
