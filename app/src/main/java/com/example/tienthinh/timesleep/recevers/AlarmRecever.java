package com.example.tienthinh.timesleep.recevers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.example.tienthinh.timesleep.models.SharedPreferencesManager;
import com.example.tienthinh.timesleep.services.MyServices;


public class AlarmRecever extends BroadcastReceiver {
    private boolean checkOnOff, checkMonday, checkTuesday, checkWednesday, checkThursday, checkFriday, checkSatuday, checkSunday;
    private static final long oneweek = 604800000;
    private static final String TAG = "Debug";
    boolean mTimeSleep = false;
    boolean mTimeWakeUp = false;
    private int id;
    private long timeSleept2, timeSleept3, timeSleept4, timeSleept5, timeSleept6, timeSleept7, timeSleepCN;
    private long timeWakeUpt2, timeWakeUpt3, timeWakeUpt4, timeWakeUpt5, timeWakeUpt6, timeWakeUpt7, timeWakeUpCN;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive: ");
        mTimeSleep = intent.getBooleanExtra("TimeSleep", false);
        id = intent.getIntExtra("KEY", 0);

        getIntentTimeSleep(intent);



        getBooleanDay(context);

        Log.e(TAG, "id: "+id );



        switch (id) {
            case 2:
                Log.e(TAG,"case = 2" );
                checkNgayThang(context, intent, checkMonday);


                AlarmManager alarmManagerMonday = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 2);
                intent.putExtra("TimeSleep", true);
                intent.putExtra("KEYTIME_T2", timeSleept2 + oneweek);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManagerMonday.setExact(AlarmManager.RTC_WAKEUP, timeSleept2 + oneweek, pendingIntent);
                break;
            case 3:
                Log.e(TAG,"case = 3" );

                checkNgayThang(context, intent, checkTuesday);

                AlarmManager alarmManagerTuesday = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 3);
                intent.putExtra("TimeSleep", true);
                intent.putExtra("KEYTIME_T3", timeSleept3 + oneweek);
                PendingIntent pendingIntentTuesday = PendingIntent.getBroadcast(context, 3, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManagerTuesday.setExact(AlarmManager.RTC_WAKEUP, timeSleept3 + oneweek, pendingIntentTuesday);
                break;
            case 4:
                Log.e(TAG,"case = 4" );

                checkNgayThang(context, intent, checkWednesday);

                AlarmManager alarmManagerWednesday = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 4);
                intent.putExtra("TimeSleep", true);
                intent.putExtra("KEYTIME_T4", timeSleept4 + oneweek);
                PendingIntent pendingIntentWednesday = PendingIntent.getBroadcast(context, 4, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManagerWednesday.setExact(AlarmManager.RTC_WAKEUP, timeSleept4 + oneweek, pendingIntentWednesday);
                break;
            case 5:
                Log.e(TAG,"case = 5" );

                checkNgayThang(context, intent, checkThursday);

                AlarmManager alarmManagerThursday = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 5);
                intent.putExtra("TimeSleep", true);
                intent.putExtra("KEYTIME_T5", timeSleept5 + oneweek);
                PendingIntent pendingIntentThursday = PendingIntent.getBroadcast(context, 5, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManagerThursday.setExact(AlarmManager.RTC_WAKEUP, timeSleept5 + oneweek, pendingIntentThursday);
                break;
            case 6:
                Log.e(TAG,"case = 6" );

                checkNgayThang(context, intent, checkFriday);


                AlarmManager alarmManagerFriday = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 6);
                intent.putExtra("TimeSleep", true);
                intent.putExtra("KEYTIME_T6", timeSleept6 + oneweek);
                PendingIntent pendingIntentFriday = PendingIntent.getBroadcast(context, 6, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManagerFriday.setExact(AlarmManager.RTC_WAKEUP, timeSleept6 + oneweek, pendingIntentFriday);
                break;
            case 7:
                Log.e(TAG,"case = 7" );

                checkNgayThang(context, intent, checkSatuday);


                AlarmManager alarmManagerSaturday = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 7);
                intent.putExtra("TimeSleep", true);
                intent.putExtra("KEYTIME_T7", timeSleept7 + oneweek);
                PendingIntent pendingIntentSaturday = PendingIntent.getBroadcast(context, 7, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManagerSaturday.setExact(AlarmManager.RTC_WAKEUP, timeSleept7 + oneweek, pendingIntentSaturday);
                break;
            case 8:
                Log.e(TAG,"case = 8" );

                checkNgayThang(context, intent, checkSunday);

                AlarmManager alarmManagerSunday = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 8);
                intent.putExtra("TimeSleep", true);
                intent.putExtra("KEYTIME_CN", timeSleepCN + oneweek);
                PendingIntent pendingIntentSunday = PendingIntent.getBroadcast(context, 8, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManagerSunday.setExact(AlarmManager.RTC_WAKEUP, timeSleepCN + oneweek, pendingIntentSunday);
                break;
        }


    }

    private void getBooleanDay(Context context) {
        checkOnOff = SharedPreferencesManager.getToggleOnOff(context);
        checkMonday = SharedPreferencesManager.getMonday(context);
        checkTuesday = SharedPreferencesManager.getTuesday(context);
        checkWednesday = SharedPreferencesManager.getWednesday(context);
        checkThursday = SharedPreferencesManager.getThursday(context);
        checkFriday = SharedPreferencesManager.getFriday(context);
        checkSatuday = SharedPreferencesManager.getSaturday(context);
        checkSunday = SharedPreferencesManager.getSunday(context);
    }

    private void getIntentTimeSleep(Intent intent) {
        timeSleept2 = intent.getLongExtra("KEYTIME_T2", 0);
        timeSleept3 = intent.getLongExtra("KEYTIME_T3", 0);
        timeSleept4 = intent.getLongExtra("KEYTIME_T4", 0);
        timeSleept5 = intent.getLongExtra("KEYTIME_T5", 0);
        timeSleept6 = intent.getLongExtra("KEYTIME_T6", 0);
        timeSleept7 = intent.getLongExtra("KEYTIME_T7", 0);
        timeSleepCN = intent.getLongExtra("KEYTIME_CN", 0);
    }


    private void startServiceSleepCall(Context context, Intent intent) {
        if (checkOnOff == true) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                intent = new Intent(context,MyServices.class);
                intent.putExtra("sleep",true);
                context.startService(intent);

            } else {
                intent = new Intent(context,MyServices.class);
                intent.putExtra("sleep",true);
                context.startService(intent);
            }
//            intent = new Intent("sendTimeSleep");
//            context.sendBroadcast(intent);
//            Log.e(TAG, "mTimeSleep=true");
        } else {

        }

    }

    private void checkNgayThang(Context context, Intent intent, boolean b) {
        if (b == true) {
            if (mTimeSleep == true) {
                mTimeSleep=false;
                startServiceSleepCall(context, intent);
                Log.e(TAG, "vào sleep" );

            } else {
            }
        } else {

        }
    }




}
