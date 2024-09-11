package com.example.learningnotification;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
    private static final String TAG="Application";
    MyAppNotificationManager myAppNotificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        myAppNotificationManager=MyAppNotificationManager.getInstance(this);
        myAppNotificationManager.registerNotificationChannel(
                getString(R.string.NEWS_CHANNEL_ID),
                getString(R.string.CHANNEL_NEWS),
                getString(R.string.CHANNEL_DESCRIPTION));
        Log.v(TAG,"on Create complete");
    }
    public void triggerNotification(Class targetNotificationActivity, String channelId, String title, String text, int priority, boolean autoChannel, int notificationId) {
        Log.v(TAG,"get Triggered");
        myAppNotificationManager.triggerNotification(targetNotificationActivity,channelId,title,text,priority,autoChannel,notificationId);
    }
    public void triggerNotification(Class targetNotificationActivity, String channelId, String title, String text,String bigText, int priority, boolean autoChannel, int notificationId) {
        myAppNotificationManager.triggerNotification(targetNotificationActivity,channelId,title,text,bigText,priority,autoChannel,notificationId);
    }
    public void updateImageInNotification(Class targetNotificationActivity, String channelId, String title, String text,int notificationId,String bigPictureString) {
        myAppNotificationManager.updateWithPicture(targetNotificationActivity, channelId, title, text, notificationId, bigPictureString);
    }
    public void cancelNotification(int notificationId) {
        myAppNotificationManager.cancelNotification(notificationId);
    }

}
