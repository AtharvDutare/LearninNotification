package com.example.learningnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;

import androidx.annotation.ColorInt;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyAppNotificationManager {
    private Context context;
    private static MyAppNotificationManager instance;
    private NotificationManagerCompat notificationManagerCompat;
    private NotificationManager notificationManager;

    private MyAppNotificationManager(Context context) {
        this.context = context;
        notificationManagerCompat = NotificationManagerCompat.from(context);
        //doubt
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static MyAppNotificationManager getInstance(Context context) {
        if (instance == null) {
            instance = new MyAppNotificationManager(context);
        }
        return instance;
    }

    public void registerNotificationChannel(String channelId, String channelName, String channelDescription) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(channelDescription);
            //notificationManager =context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void triggerNotification(Class targetNotificationActivity, String channelId, String title, String text, int priority, boolean autoChannel, int notificationId) {
        Intent intent = new Intent(context, targetNotificationActivity);
        intent.putExtra("count", title);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);// due to FLAG_ACTIVITY_CLEAR_TASK we direct come out of screen
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );// go back to previous activity
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_icon_large))
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(autoChannel)
                .setChannelId(channelId)
                .setContentIntent(pendingIntent)
                .setPriority(priority);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManagerCompat.notify(notificationId, builder.build());
    }
    public void triggerNotification(Class targetNotificationActivity, String channelId, String title, String text, String bigText, int priority, boolean autoChannel, int notificationId) {
        Intent intent = new Intent(context, targetNotificationActivity);
        intent.putExtra("count", title);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_icon_large))
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(autoChannel)
                .setChannelId(channelId)
                .setContentIntent(pendingIntent)
                .setPriority(priority)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(bigText));
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManagerCompat.notify(notificationId, builder.build());
    }
    public void updateWithPicture(Class targetNotificationActivity, String channelId, String title, String text,int notificationId,String bigPictureString) {
        Intent intent = new Intent(context, targetNotificationActivity);
        intent.putExtra("count", title);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_icon_large))
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true)
                .setChannelId(channelId)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Bitmap androidImage=BitmapFactory.decodeResource(context.getResources(),R.drawable.big_pic);
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(androidImage).setBigContentTitle(bigPictureString));
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManagerCompat.notify(notificationId, builder.build());
    }
    public void cancelNotification(int notificationId) {
        notificationManager.cancel(notificationId);
    }

}
