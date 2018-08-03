package com.example.tienthinh.timesleep.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.tienthinh.timesleep.R;


public class MyServices extends Service {
    private int notificationId = 05;
    private NotificationCompat.Builder builder;
    private RemoteViews remoteViews;
    private NotificationManager notificationManager;
    private Notification notification;
    private BroadcastReceiver receiver;
    private IntentFilter filter;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        khoiTaoNoification();
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case "sendTimeSleep":
                        khoiTaoNoification();
                        stopSelf();
                        break;
                    case "sendTimeWakeUp":
                        khoiTaoNoification1();
                        stopSelf();
                        break;
                }
            }
        };
        filter = new IntentFilter();
        filter.addAction("sendTimeSleep");
        filter.addAction("sendTimeWakeUp");
        getBaseContext().registerReceiver(receiver, filter);

        return START_NOT_STICKY;


    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Cancle", Toast.LENGTH_SHORT).show();
        try {
            getBaseContext().unregisterReceiver(receiver);
        }catch (Exception e){

        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void khoiTaoNoification() {
        String channelId = "channel-01";
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setChannelId(channelId)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSound(alarmSound)
                .setContentTitle("Nhắc Nhở")
                .setContentText("Đến giờ đi ngủ rồi bây bề");



        notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;


//        String mau = "#5c5c5c";
//
//        remoteViews = new RemoteViews(getPackageName(), R.layout.custom_noification);
//
//
//        notification.contentView = remoteViews;
//        notification.flags = Notification.FLAG_NO_CLEAR;
//        notification.flags |= Notification.FLAG_ONGOING_EVENT;
//
//
//        //sự kiện bấm vào pink trên notification
//        String pink = "img_pink";
//        Intent pinkIntent = new Intent(pink);
//        PendingIntent pinkPendingIntent = PendingIntent.getBroadcast(this, 0, pinkIntent, 0);
//        builder.addAction(R.id.img_pink, pink, pinkPendingIntent);
//        builder.setContentIntent(pinkPendingIntent);
//        remoteViews.setOnClickPendingIntent(R.id.img_pink, pinkPendingIntent);
//
//
//        //sự kiện bấm vào green trên notification
//        String green = "img_green";
//        Intent greenIntent = new Intent(green);
//        PendingIntent greenPendingIntent = PendingIntent.getBroadcast(this, 1, greenIntent, 0);
//        builder.addAction(R.id.img_green, green, greenPendingIntent);
//        builder.setContentIntent(greenPendingIntent);
//        remoteViews.setOnClickPendingIntent(R.id.img_green, greenPendingIntent);
//
//        //sự kiện bấm vào blue trên notification
//        String blue = "img_blue";
//        Intent blueIntent = new Intent(blue);
//        PendingIntent bluePendingIntent = PendingIntent.getBroadcast(this, 2, blueIntent, 0);
//        builder.addAction(R.id.img_blue, blue, bluePendingIntent);
//        builder.setContentIntent(bluePendingIntent);
//        remoteViews.setOnClickPendingIntent(R.id.img_blue, bluePendingIntent);
//
//        //sự kiện bấm vào red trên notification
//        String red = "img_red";
//        Intent redIntent = new Intent(red);
//        PendingIntent redPendingIntent = PendingIntent.getBroadcast(this, 3, redIntent, 0);
//        builder.addAction(R.id.img_red, red, redPendingIntent);
//        builder.setContentIntent(redPendingIntent);
//        remoteViews.setOnClickPendingIntent(R.id.img_red, redPendingIntent);
//
//        //sự kiện bấm vào yellow trên notification
//        String yellow = "img_yellow";
//        Intent yellowIntent = new Intent(yellow);
//        PendingIntent yellowPendingIntent = PendingIntent.getBroadcast(this, 4, yellowIntent, 0);
//        builder.addAction(R.id.img_yellow, yellow, yellowPendingIntent);
//        builder.setContentIntent(yellowPendingIntent);
//        remoteViews.setOnClickPendingIntent(R.id.img_yellow, yellowPendingIntent);
//
//        //sự kiện bấm vào setting trên notification
//        String setting = "img_setting";
//        Intent settingIntent = new Intent(this, MainActivity.class);
//        settingIntent.setFlags(settingIntent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent settingPendingIntent = PendingIntent.getActivity(this, 5, settingIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.addAction(R.id.img_go_app, setting, settingPendingIntent);
//        builder.setContentIntent(greenPendingIntent);
//        remoteViews.setOnClickPendingIntent(R.id.img_go_app, settingPendingIntent);


        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        } else {

        }


        notificationManager.notify(notificationId, notification);
    }
    private void khoiTaoNoification1() {
        String channelId = "channel-02";
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setChannelId(channelId)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSound(alarmSound)
                .setContentTitle("Nhắc Nhở")
                .setContentText("Đến giờ thức dậy rồi bây bề");



        notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;


//        String mau = "#5c5c5c";
//
//        remoteViews = new RemoteViews(getPackageName(), R.layout.custom_noification);
//
//
//        notification.contentView = remoteViews;
//        notification.flags = Notification.FLAG_NO_CLEAR;
//        notification.flags |= Notification.FLAG_ONGOING_EVENT;
//
//
//        //sự kiện bấm vào pink trên notification
//        String pink = "img_pink";
//        Intent pinkIntent = new Intent(pink);
//        PendingIntent pinkPendingIntent = PendingIntent.getBroadcast(this, 0, pinkIntent, 0);
//        builder.addAction(R.id.img_pink, pink, pinkPendingIntent);
//        builder.setContentIntent(pinkPendingIntent);
//        remoteViews.setOnClickPendingIntent(R.id.img_pink, pinkPendingIntent);
//
//
//        //sự kiện bấm vào green trên notification
//        String green = "img_green";
//        Intent greenIntent = new Intent(green);
//        PendingIntent greenPendingIntent = PendingIntent.getBroadcast(this, 1, greenIntent, 0);
//        builder.addAction(R.id.img_green, green, greenPendingIntent);
//        builder.setContentIntent(greenPendingIntent);
//        remoteViews.setOnClickPendingIntent(R.id.img_green, greenPendingIntent);
//
//        //sự kiện bấm vào blue trên notification
//        String blue = "img_blue";
//        Intent blueIntent = new Intent(blue);
//        PendingIntent bluePendingIntent = PendingIntent.getBroadcast(this, 2, blueIntent, 0);
//        builder.addAction(R.id.img_blue, blue, bluePendingIntent);
//        builder.setContentIntent(bluePendingIntent);
//        remoteViews.setOnClickPendingIntent(R.id.img_blue, bluePendingIntent);
//
//        //sự kiện bấm vào red trên notification
//        String red = "img_red";
//        Intent redIntent = new Intent(red);
//        PendingIntent redPendingIntent = PendingIntent.getBroadcast(this, 3, redIntent, 0);
//        builder.addAction(R.id.img_red, red, redPendingIntent);
//        builder.setContentIntent(redPendingIntent);
//        remoteViews.setOnClickPendingIntent(R.id.img_red, redPendingIntent);
//
//        //sự kiện bấm vào yellow trên notification
//        String yellow = "img_yellow";
//        Intent yellowIntent = new Intent(yellow);
//        PendingIntent yellowPendingIntent = PendingIntent.getBroadcast(this, 4, yellowIntent, 0);
//        builder.addAction(R.id.img_yellow, yellow, yellowPendingIntent);
//        builder.setContentIntent(yellowPendingIntent);
//        remoteViews.setOnClickPendingIntent(R.id.img_yellow, yellowPendingIntent);
//
//        //sự kiện bấm vào setting trên notification
//        String setting = "img_setting";
//        Intent settingIntent = new Intent(this, MainActivity.class);
//        settingIntent.setFlags(settingIntent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent settingPendingIntent = PendingIntent.getActivity(this, 5, settingIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.addAction(R.id.img_go_app, setting, settingPendingIntent);
//        builder.setContentIntent(greenPendingIntent);
//        remoteViews.setOnClickPendingIntent(R.id.img_go_app, settingPendingIntent);


        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        String channelName = "Channel Name 1";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        } else {

        }


        notificationManager.notify(notificationId, notification);
    }
}
