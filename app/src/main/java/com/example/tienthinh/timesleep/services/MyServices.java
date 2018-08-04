package com.example.tienthinh.timesleep.services;

import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.tienthinh.timesleep.R;
import com.example.tienthinh.timesleep.model.SharedPreferencesManager;


public class MyServices extends Service {
    private Uri alarmSound;
    boolean wakeUp, sleep;
    private static final String TAG = "debug";
    private int notificationId = 2000;
    private int notificationIdWU = 1998;
    private NotificationCompat.Builder builder;
    private NotificationCompat.Builder builderWU;
    private RemoteViews remoteViews;
    private NotificationManager notificationManager;
    private NotificationManager notificationManagerWU;
    private Notification notification;
    private Notification notificationWU;
    private BroadcastReceiver receiver;
    private IntentFilter filter;
    private MediaPlayer mediaPlayer;
    private int i;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer = MediaPlayer.create(this, R.raw.annieswonderland);
        i = SharedPreferencesManager.getPositionSound(this);
//        mediaPlayer.getDuration();
//        khoiTaoNoification();
        wakeUp = intent.getBooleanExtra("wakeup", false);
        sleep = intent.getBooleanExtra("sleep", false);

        if (wakeUp == true) {
            wakeUp = false;
            khoiTaoNoification1();
            startForeground(notificationId, notification);
            //stopSelf();
            PowerManager pm = (PowerManager)getApplicationContext().getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
            wakeLock.acquire();
            switch (i) {
                case 0:
                 playSong(R.raw.littlecomfort);
                    break;
                case 1:
                    playSong(R.raw.annieswonderland);
                    break;
                case 2:
                    playSong(R.raw.havana);
                    break;
                case 3:
                    playSong(R.raw.thisgame);
                    break;
                case 4:
                    playSong(R.raw.griefandsorrow);
                    break;
                case 5:
                    playSong(R.raw.chungtakhonggiongnhau);
                    break;
                case 6:
                    playSong(R.raw.beautifulgirl);
                    break;
            }

            Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    KeyguardManager keyguardManager = (KeyguardManager) getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);
                    KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("TAG");
                    keyguardLock.disableKeyguard();
                    stopForeground(false);
                    stopSelf();
                }
            };
            handler.postDelayed(runnable, 5 * 60 * 1000);
        } else {

        }
        if (sleep == true) {
            khoiTaoNoification();
//            startForeground(notificationIdWU, notificationWU);
            Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // stopForeground(false);
                    stopSelf();
                }
            };
            handler.postDelayed(runnable, 6000);
        } else {

        }


//
//        receiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                switch (intent.getAction()) {
//                    case "sendTimeSleep":
//                        khoiTaoNoification();
//                        startForeground(notificationId, notification);
//                        Log.e(TAG, "sendTimeSleep = ok" );
//                        //stopSelf();
//                        Toast.makeText(context, "sendTimeSleep", Toast.LENGTH_SHORT).show();
//                        break;
//                    case "sendTimeWakeUp":
//                        khoiTaoNoification1();
//                        startForeground(notificationIdWU,notificationWU);
//                        Log.e(TAG, "sendTimeWakeUp = ok" );
//                        Toast.makeText(context, "sendTimeWakeUp", Toast.LENGTH_SHORT).show();
//                       // stopSelf();
//                        break;
//                }
//            }
//        };
//        filter = new IntentFilter();
//        filter.addAction("sendTimeSleep");
//        filter.addAction("sendTimeWakeUp");
//        getBaseContext().registerReceiver(receiver, filter);

        return START_NOT_STICKY;


    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Cancle", Toast.LENGTH_SHORT).show();
        try {
            getBaseContext().unregisterReceiver(receiver);
        } catch (Exception e) {

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        } else {
            alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setChannelId(channelId)
                .setAutoCancel(false)
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
            notificationManager.notify(notificationId, notification);
        }


    }

    private void khoiTaoNoification1() {
        String channelId = "channelWU";
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//        } else {
//           // alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        }
        builderWU = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setChannelId(channelId)
                .setAutoCancel(false)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSound(alarmSound)
                .setContentTitle("Nhắc Nhở")
                .setContentText("Đến giờ thức dậy rồi bây bề");


        notificationWU = builderWU.build();
        notificationWU.flags = Notification.FLAG_AUTO_CANCEL;


//        String mau = "#5c5c5c";
//
        remoteViews = new RemoteViews(getPackageName(), R.layout.custom_notification);
//
//
        notificationWU.contentView = remoteViews;
        notificationWU.flags = Notification.FLAG_NO_CLEAR;
        notificationWU.flags |= Notification.FLAG_ONGOING_EVENT;


        //sự kiện bấm vào replace trên notification
        String replace = "replace";
        Intent pinkIntent = new Intent(replace);
        PendingIntent replacePending = PendingIntent.getBroadcast(this, 2010, pinkIntent, 0);
        builderWU.addAction(R.id.replace, replace, replacePending);
        builderWU.setContentIntent(replacePending);
        remoteViews.setOnClickPendingIntent(R.id.replace, replacePending);


        //sự kiện bấm vào green trên notification
        String cancel = "cancel";
        Intent greenIntent = new Intent(cancel);
        PendingIntent cancelPending = PendingIntent.getBroadcast(this, 2011, greenIntent, 0);
        builder.addAction(R.id.cancel, cancel, cancelPending);
        builder.setContentIntent(cancelPending);
        remoteViews.setOnClickPendingIntent(R.id.cancel, cancelPending);
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


        notificationManagerWU = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        String channelName = "TimeSleep";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(channelId, channelName, importance);
            notificationManagerWU.createNotificationChannel(mChannel);
        } else {
            notificationManagerWU.notify(notificationIdWU, notificationWU);
        }

//        startForeground(1, notification);
    }

    private void playSong(int uri) {
        if (mediaPlayer.isPlaying()==true){
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(this,uri);
            mediaPlayer.start();
        }else {
            mediaPlayer = MediaPlayer.create(this,uri);
            mediaPlayer.start();
        }
    }

    ;

}
