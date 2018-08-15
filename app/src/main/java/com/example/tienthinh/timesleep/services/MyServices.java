package com.example.tienthinh.timesleep.services;

import android.app.AlarmManager;
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
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.tienthinh.timesleep.MainActivity;
import com.example.tienthinh.timesleep.R;
import com.example.tienthinh.timesleep.recevers.AlarmReceverWakeUp;
import com.example.tienthinh.timesleep.recevers.ListenTurnOffScreen;
import com.example.tienthinh.timesleep.models.SharedPreferencesManager;

import java.util.Calendar;


public class MyServices extends Service {
    ListenTurnOffScreen screen;
    private String text;
    CountDownTimer countDownTimer;
    Handler handler;
    Runnable runnable;
    private long timeSong;
    private long fiveMinute = 300000;
    private AlarmManager alarmManager;
    private Uri alarmSound;
    boolean wakeUp, sleep;
    private static final String TAG = "debug";
    private int notificationId = 2000;
    private int notificationIdWU = 1998;
    private NotificationCompat.Builder builder;
    private NotificationCompat.Builder builderWU;
    private RemoteViews remoteViews;
    private RemoteViews remoteViewsSleep;
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
        wakeUp = intent.getBooleanExtra("wakeup", false);
        sleep = intent.getBooleanExtra("sleep", false);


        if (wakeUp == true) {
            wakeUp = false;

            screen = new ListenTurnOffScreen();
            IntentFilter filterScreen = new IntentFilter(Intent.ACTION_SCREEN_OFF);
            filterScreen.addAction(Intent.ACTION_SCREEN_OFF);
            registerReceiver(screen,filterScreen);


            khoiTaoNoification1();

            startForeground(notificationIdWU, notificationWU);
            turnOnScreen();
            //stopSelf();
            switch (i) {
                case 0:
                    playSong(R.raw.littlecomfort);
                    timeSong = mediaPlayer.getDuration();
                    createCountDownt();
                    break;
                case 1:
                    playSong(R.raw.annieswonderland);
                    timeSong = mediaPlayer.getDuration();
                    createCountDownt();
                    break;
                case 2:
                    playSong(R.raw.havana);
                    timeSong = mediaPlayer.getDuration();
                    createCountDownt();
                    break;
                case 3:
                    playSong(R.raw.thisgame);
                    timeSong = mediaPlayer.getDuration();
                    createCountDownt();
                    break;
                case 4:
                    playSong(R.raw.griefandsorrow);
                    timeSong = mediaPlayer.getDuration();
                    createCountDownt();
                    break;
                case 5:
                    playSong(R.raw.frenchkiss);
                    timeSong = mediaPlayer.getDuration();
                    createCountDownt();
                    break;
                case 6:
                    playSong(R.raw.beautifulgirl);
                    timeSong = mediaPlayer.getDuration();
                    createCountDownt();
                    break;
            }

            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    int i =0;
                    cancelTurnOnScreen();
                    stopForeground(false);
                    stopSelf();
                    mediaPlayer.stop();
                    mediaPlayer.release();

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.MILLISECOND, 0);
                    calendar.set(Calendar.SECOND, 0);
                    long time = calendar.getTimeInMillis();

//                    if (i<=3) {
////                        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
////                        Intent intent = new Intent(MyServices.this, AlarmReceverWakeUp.class);
////                        intent.putExtra("KEYWU", 1998);
////                        intent.putExtra("TimeWakeUp", true);
////                        PendingIntent pendingIntent = PendingIntent.getBroadcast(MyServices.this, 1998, intent, PendingIntent.FLAG_UPDATE_CURRENT);
////                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time + fiveMinute, pendingIntent);
////                    }

                }

            };
            handler.postDelayed(runnable, 5 * 60 * 1000);
        } else {

        }
        if (sleep == true) {
            khoiTaoNoification();
            turnOnScreen();
//            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
////                startForeground(notificationId, notification);
////            }
            Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    //stopForeground(false);
                    cancelTurnOnScreen();
//                    stopSelf();
//                    khoiTaoNoification();
                }
            };
            handler.postDelayed(runnable, 4000);
        } else {

        }


        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case "replace":
                        notificationManagerWU.cancel(notificationIdWU);
                        mediaPlayer.release();
                        handler.removeCallbacks(runnable);
                        handler = null;
                        countDownTimer.cancel();

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.MILLISECOND, 0);
                        calendar.set(Calendar.SECOND, 0);
                        long time = calendar.getTimeInMillis();

                        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                        intent = new Intent(MyServices.this, AlarmReceverWakeUp.class);
                        intent.putExtra("KEYWU", 1998);
                        intent.putExtra("TimeWakeUp", true);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1998, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time + fiveMinute, pendingIntent);
                        stopSelf();
                        break;
                    case "cancel":
                        countDownTimer.cancel();
                        try {
                            handler.removeCallbacks(runnable);
                            handler = null;
                        }catch (Exception e){

                        }

                        mediaPlayer.stop();
                        mediaPlayer.release();
//                        SharedPreferencesManager.setDK(MyServices.this, true);
                        cancelTurnOnScreen();
                        stopSelf();
                        break;
                    case "turnoffScreen":
                        if (sleep==true){}
                        else {
                        countDownTimer.cancel();
                        try {
                            handler.removeCallbacks(runnable);
                            handler = null;
                        }catch (Exception e){

                        }

                        mediaPlayer.stop();
                        mediaPlayer.release();
//                        SharedPreferencesManager.setDK(MyServices.this, true);
                        cancelTurnOnScreen();
                        stopSelf();
                        }
                        break;
                }
            }
        };
        filter = new IntentFilter();
        filter.addAction("replace");
        filter.addAction("cancel");
        filter.addAction("turnoffScreen");
        getBaseContext().registerReceiver(receiver, filter);

        return START_NOT_STICKY;


    }

    @Override
    public void onDestroy() {
        try {
            getBaseContext().unregisterReceiver(receiver);
        } catch (Exception e) {

        }
        try {
            unregisterReceiver(screen);
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
        checkTime();
        String channelId = "channel-01";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        } else {
            alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setChannelId(channelId)
                .setAutoCancel(false)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSound(alarmSound)
                .setContentTitle(this.getResources().getString(R.string.nhac_nho))
                .setContentText(text);


        notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
//        notification.flags |= Notification.FLAG_ONGOING_EVENT;

        remoteViewsSleep = new RemoteViews(getPackageName(),R.layout.custom_notification_sleep);
        notification.contentView = remoteViewsSleep;


        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;



        remoteViewsSleep.setTextViewText(R.id.tv_noti_sleep,text);

        int hourSleep = SharedPreferencesManager.getHourSleep(this);
        int minuteSleep = SharedPreferencesManager.getMinuteSleep(this);
        String hourSleep1 = hourSleep+"";
        String minuteSleep1 = minuteSleep + "";
        if (hourSleep<10){
            hourSleep1 = "0"+hourSleep;
        }
        if (minuteSleep<10){
            minuteSleep1 = "0"+minuteSleep;
        }
        String Hm = hourSleep1+":"+minuteSleep1;
        remoteViewsSleep.setTextViewText(R.id.time_sleep_noti,Hm);

        String s = "go_app";
        Intent go_app = new Intent(getBaseContext(), MainActivity.class);
        go_app.setFlags(go_app.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,1234,go_app,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.addAction(R.id.go_app,s,pendingIntent);
        remoteViewsSleep.setOnClickPendingIntent(R.id.go_app,pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
            notificationManager.notify(notificationId, notification);
        } else {
            notificationManager.notify(notificationId, notification);
        }

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





    }

    private void checkTime() {
        if (SharedPreferencesManager.get5p(this)==true){
            text = getResources().getString(R.string.den_gio_di_ngu_5);
        }else if (SharedPreferencesManager.get15p(this)==true){
            text =getResources().getString( R.string.den_gio_di_ngu_15);
        }else if (SharedPreferencesManager.get1hour(this)==true){
            text =getResources().getString( R.string.den_gio_di_ngu_1h);
        }else if (SharedPreferencesManager.get30p(this)==true){
            text = getResources().getString(R.string.den_gio_di_ngu_30);
        }else if (SharedPreferencesManager.getSleepNow(this)==true){
            text = getResources().getString(R.string.den_gio_di_ngu);
        }
    }

    private void khoiTaoNoification1() {
        String channelId = "channelWU";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        } else {
            alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        builderWU = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setChannelId(channelId)
                .setAutoCancel(false)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSound(alarmSound)
                .setContentTitle(this.getResources().getString(R.string.nhac_nho))
                .setContentText(this.getResources().getString(R.string.den_gio_thuc_day));


        //builderWU.setDefaults(0);
        notificationWU = builderWU.build();
        notificationWU.flags = Notification.FLAG_AUTO_CANCEL;


//        String mau = "#5c5c5c";
//
        remoteViews = new RemoteViews(getPackageName(), R.layout.custom_notification_wakeup);
//
//
        notificationWU.contentView = remoteViews;
        notificationWU.flags = Notification.FLAG_NO_CLEAR;
        notificationWU.flags |= Notification.FLAG_ONGOING_EVENT;


        //sự kiện bấm vào replace trên notification
        String replace = "replace";
        Intent replaceIntent = new Intent(replace);
        PendingIntent replacePending = PendingIntent.getBroadcast(this, 2010, replaceIntent, 0);
        builderWU.addAction(R.id.replace, replace, replacePending);
        builderWU.setContentIntent(replacePending);
        remoteViews.setOnClickPendingIntent(R.id.replace, replacePending);

        int hourWakeUp = SharedPreferencesManager.getHourWakeUp(this);
        int minuteWakeUp = SharedPreferencesManager.getMinuteWakeUp(this);
        String timehour = hourWakeUp+"";
        String minutehour = minuteWakeUp + "";
        if (hourWakeUp<10){
            timehour = "0"+hourWakeUp;
        }
        if (minuteWakeUp<10){
            minutehour = "0"+minuteWakeUp;
        }
        String Hm = timehour+":"+minutehour;
        remoteViews.setTextViewText(R.id.time_noti,Hm);



        //sự kiện bấm vào cancel trên notification
        String cancel = "cancel";
        Intent greenIntent = new Intent(cancel);
        PendingIntent cancelPending = PendingIntent.getBroadcast(this, 2011, greenIntent, 0);
        builderWU.addAction(R.id.cancel, cancel, cancelPending);
        builderWU.setContentIntent(cancelPending);
        remoteViews.setOnClickPendingIntent(R.id.cancel, cancelPending);

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
        if (mediaPlayer.isPlaying() == true) {
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(this, uri);
            mediaPlayer.start();
        } else {
            mediaPlayer = MediaPlayer.create(this, uri);
            mediaPlayer.start();
        }
    }

    private void cancelTurnOnScreen() {
        KeyguardManager keyguardManager = (KeyguardManager) getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("TAG");
        keyguardLock.disableKeyguard();
    }

    private void turnOnScreen() {
        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
        wakeLock.acquire();

    }

    private void createCountDownt() {
        countDownTimer = new CountDownTimer(timeSong + 1000, 1000) {
            @Override
            public void onTick(long l) {
                Log.e(TAG, "onTick: " + l);
            }

            @Override
            public void onFinish() {
                mediaPlayer.start();
            }
        };
        countDownTimer.start();
    }

}
