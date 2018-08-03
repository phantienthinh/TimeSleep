package com.example.tienthinh.timesleep.Recever;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.example.tienthinh.timesleep.model.SharedPreferencesManager;
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
        mTimeWakeUp = intent.getBooleanExtra("TimeWakeUp",false);
        id = intent.getIntExtra("KEY", 0);

        getIntentTimeSleep(intent);

        getIntentTimeWakeUp(intent);

        getBooleanDay(context);

        Log.e(TAG, "id: "+id );



        switch (id) {
            case 2:
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
                checkNgayThang(context, intent, checkSunday);

                AlarmManager alarmManagerSunday = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 8);
                intent.putExtra("TimeSleep", true);
                intent.putExtra("KEYTIME_CN", timeSleepCN + oneweek);
                PendingIntent pendingIntentSunday = PendingIntent.getBroadcast(context, 8, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManagerSunday.setExact(AlarmManager.RTC_WAKEUP, timeSleepCN + oneweek, pendingIntentSunday);
                break;
            case 22:
                checkNgayThangWakeUp(context,intent,checkMonday);
                AlarmManager mALMondayWakeUp = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 22);
                intent.putExtra("TimeWakeUp", true);
                intent.putExtra("KEYTIME_T2_WU", timeWakeUpt2 + oneweek);
                PendingIntent mPDMondayWakeUp = PendingIntent.getBroadcast(context, 22, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                mALMondayWakeUp.setExact(AlarmManager.RTC_WAKEUP, timeWakeUpCN + oneweek, mPDMondayWakeUp);
                break;
            case 23:
                checkNgayThangWakeUp(context,intent,checkTuesday);
                AlarmManager mALTuesdayWakeUp = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 23);
                intent.putExtra("TimeWakeUp", true);
                intent.putExtra("KEYTIME_T3_WU", timeWakeUpt3 + oneweek);
                PendingIntent mPDTuesdayWakeUp = PendingIntent.getBroadcast(context, 23, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                mALTuesdayWakeUp.setExact(AlarmManager.RTC_WAKEUP, timeWakeUpt3 + oneweek, mPDTuesdayWakeUp);
                break;
            case 24:
                checkNgayThangWakeUp(context,intent,checkWednesday);
                AlarmManager mALWednesdayWakeUp = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 24);
                intent.putExtra("TimeWakeUp", true);
                intent.putExtra("KEYTIME_T4_WU", timeWakeUpt4 + oneweek);
                PendingIntent mPDWednesdayWakeUp = PendingIntent.getBroadcast(context, 24, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                mALWednesdayWakeUp.setExact(AlarmManager.RTC_WAKEUP, timeWakeUpt4 + oneweek, mPDWednesdayWakeUp);
                break;
            case 25:
                checkNgayThangWakeUp(context,intent,checkThursday);
                AlarmManager mALThursdayWakeUp = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 25);
                intent.putExtra("TimeWakeUp", true);
                intent.putExtra("KEYTIME_T5_WU", timeWakeUpt5 + oneweek);
                PendingIntent mPDThursdayWakeUp = PendingIntent.getBroadcast(context, 25, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                mALThursdayWakeUp.setExact(AlarmManager.RTC_WAKEUP, timeWakeUpt5 + oneweek, mPDThursdayWakeUp);
                break;
            case 26:
                checkNgayThangWakeUp(context,intent,checkFriday);
                AlarmManager mALFridayWakeUp = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 26);
                intent.putExtra("TimeWakeUp", true);
                intent.putExtra("KEYTIME_T6_WU", timeWakeUpt6 + oneweek);
                PendingIntent mPDFridayWakeUp = PendingIntent.getBroadcast(context, 26, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                mALFridayWakeUp.setExact(AlarmManager.RTC_WAKEUP, timeWakeUpt6 + oneweek, mPDFridayWakeUp);
                break;
            case 27:
                checkNgayThangWakeUp(context,intent,checkSatuday);
                AlarmManager mALSatudayWakeUp = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 27);
                intent.putExtra("TimeWakeUp", true);
                intent.putExtra("KEYTIME_T7_WU", timeWakeUpt7 + oneweek);
                PendingIntent mPDSatudayWakeUp = PendingIntent.getBroadcast(context, 27, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                mALSatudayWakeUp.setExact(AlarmManager.RTC_WAKEUP, timeWakeUpt7 + oneweek, mPDSatudayWakeUp);
                break;
            case 28:
                checkNgayThangWakeUp(context,intent,checkSunday);
                AlarmManager mALSundayWakeUp = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 28);
                intent.putExtra("TimeWakeUp", true);
                intent.putExtra("KEYTIME_CN_WU", timeWakeUpCN + oneweek);
                PendingIntent mPDSundayWakeUp = PendingIntent.getBroadcast(context, 28, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                mALSundayWakeUp.setExact(AlarmManager.RTC_WAKEUP, timeWakeUpCN + oneweek, mPDSundayWakeUp);
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

    private void getIntentTimeWakeUp(Intent intent) {
        timeWakeUpt2 = intent.getLongExtra("KEYTIME_T2_WU", 0);
        timeWakeUpt3 = intent.getLongExtra("KEYTIME_T3_WU", 0);
        timeWakeUpt4 = intent.getLongExtra("KEYTIME_T4_WU", 0);
        timeWakeUpt5 = intent.getLongExtra("KEYTIME_T5_WU", 0);
        timeWakeUpt6 = intent.getLongExtra("KEYTIME_T6_WU", 0);
        timeWakeUpt7 = intent.getLongExtra("KEYTIME_T7_WU", 0);
        timeWakeUpCN = intent.getLongExtra("KEYTIME_CN_WU", 0);
    }

    private void startServiceSleepCall(Context context, Intent intent) {
        if (checkOnOff == true) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(new Intent(context, MyServices.class));
            } else {
                context.startService(new Intent(context, MyServices.class));
            }
            intent = new Intent("sendTimeSleep");
            context.sendBroadcast(intent);
            Log.e(TAG, "mTimeSleep=true");
        } else {

        }

    }

    private void checkNgayThang(Context context, Intent intent, boolean b) {
        if (b == true) {
            if (mTimeSleep == true) {
                mTimeSleep=false;
                startServiceSleepCall(context, intent);

            } else {
            }
        } else {

        }
    }

    private void checkNgayThangWakeUp(Context context, Intent intent, boolean b) {
        if (b == true) {
            if (mTimeWakeUp == true) {
                mTimeWakeUp=false;
                startServiceWakeUpCall(context, intent);

            } else {
            }
        } else {

        }
    }

    private void startServiceWakeUpCall(Context context, Intent intent) {
        if (checkOnOff == true) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(new Intent(context, MyServices.class));
            } else {
                context.startService(new Intent(context, MyServices.class));
            }
            intent = new Intent("sendTimeWakeUp");
            context.sendBroadcast(intent);
            Log.e(TAG, "mTimeWakeUp=true");
        } else {

        }

    }

}
