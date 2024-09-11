package com.example.learningnotification;


import static java.lang.Integer.getInteger;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button buttonTriggerNotification,cancelNotification,updateNotification;
    private final static String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        askPermission();
        //createNotification part done on onCreate method of Application
        buttonTriggerNotification = findViewById(R.id.button);
        cancelNotification=findViewById(R.id.button2);
        updateNotification=findViewById(R.id.button3);
        buttonTriggerNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //triggerNotification();
                ((MyApplication)getApplication()).triggerNotification(NotificationDetailsActivity.class,
                        getString(R.string.NEWS_CHANNEL_ID),
                        "this is title",
                        "this is main text",
                        NotificationCompat.PRIORITY_DEFAULT,
                        true,
                        getResources().getInteger(R.integer.notificationId)
                        );
            }
        });
        cancelNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MyApplication)getApplication()).cancelNotification(getResources().getInteger(R.integer.notificationId));
            }
        });
        updateNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MyApplication)getApplication()).updateImageInNotification(
                        NotificationDetailsActivity.class,
                        getString(R.string.NEWS_CHANNEL_ID),
                        "update Notification",
                        "this is update text",
                        getResources().getInteger(R.integer.notificationId),
                        "this i sa updated information for bigpicture String"
                        );
            }
        });

    }

//    private void createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel notificationChannel = new NotificationChannel(getString(R.string.NEWS_CHANNEL_ID), getString(R.string.CHANNEL_NEWS), NotificationManager.IMPORTANCE_DEFAULT);
//            notificationChannel.setDescription(getString(R.string.CHANNEL_DESCRIPTION));
//            notificationChannel.setShowBadge(true);
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            //NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(notificationChannel);
//            Log.v(TAG,"create Notification channel");
//        }
//    }
//
//    private void triggerNotification() {
//        Intent intent = new Intent(this, NotificationDetailsActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
//        Log.v(TAG,"run before building the Notification");
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, getString(R.string.NEWS_CHANNEL_ID))
//                .setSmallIcon(R.drawable.ic_notification)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_icon_large))
//                .setContentTitle("Notification title")
//                .setContentText("this is text,that will be shown as part of notifcation")
//                .setStyle(new NotificationCompat.BigTextStyle().bigText("hello "))
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setContentIntent(pendingIntent)
//                .setChannelId(getString(R.string.NEWS_CHANNEL_ID))
//                .setAutoCancel(true);
//        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
//            String [] permission={android.Manifest.permission.POST_NOTIFICATIONS};
//            requestPermissions(permission,1);
//        }
//        notificationManagerCompat.notify(getResources().getInteger(R.integer.notificationId), builder.build());
//        //Log.v(TAG,"triggered method is at end");
//    }
    private void askPermission() {
        String[] permission={android.Manifest.permission.POST_NOTIFICATIONS};
        requestPermissions(permission,1);
    }
}