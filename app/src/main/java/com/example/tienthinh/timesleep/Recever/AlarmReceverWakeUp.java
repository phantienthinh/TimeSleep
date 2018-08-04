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

public class AlarmReceverWakeUp extends BroadcastReceiver {
    private boolean checkOnOff, checkMonday, checkTuesday, checkWednesday, checkThursday, checkFriday, checkSatuday, checkSunday;
    private static final long oneweek = 604800000;
    boolean mTimeWakeUp = false;
    private static final String TAG = "Debug";
    private long timeWakeUpt2, timeWakeUpt3, timeWakeUpt4, timeWakeUpt5, timeWakeUpt6, timeWakeUpt7, timeWakeUpCN;
    private int id;

    @Override
    public void onReceive(Context context, Intent intent) {
        mTimeWakeUp = intent.getBooleanExtra("TimeWakeUp", false);
        id = intent.getIntExtra("KEYWU", 0);
        getIntentTimeWakeUp(intent);
        getBooleanDay(context);


        switch (id) {
            case 22:
                Log.e(TAG, "case = 22");

                checkNgayThangWakeUp(context, intent, checkMonday);
                AlarmManager mALMondayWakeUp = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 22);
                intent.putExtra("TimeWakeUp", true);
                intent.putExtra("KEYTIME_T2_WU", timeWakeUpt2 + oneweek);
                PendingIntent mPDMondayWakeUp = PendingIntent.getBroadcast(context, 22, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                mALMondayWakeUp.setExact(AlarmManager.RTC_WAKEUP, timeWakeUpCN + oneweek, mPDMondayWakeUp);
                break;
            case 23:
                Log.e(TAG, "case = 23");

                checkNgayThangWakeUp(context, intent, checkTuesday);
                AlarmManager mALTuesdayWakeUp = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 23);
                intent.putExtra("TimeWakeUp", true);
                intent.putExtra("KEYTIME_T3_WU", timeWakeUpt3 + oneweek);
                PendingIntent mPDTuesdayWakeUp = PendingIntent.getBroadcast(context, 23, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                mALTuesdayWakeUp.setExact(AlarmManager.RTC_WAKEUP, timeWakeUpt3 + oneweek, mPDTuesdayWakeUp);
                break;
            case 24:
                Log.e(TAG, "case = 24");

                checkNgayThangWakeUp(context, intent, checkWednesday);
                AlarmManager mALWednesdayWakeUp = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 24);
                intent.putExtra("TimeWakeUp", true);
                intent.putExtra("KEYTIME_T4_WU", timeWakeUpt4 + oneweek);
                PendingIntent mPDWednesdayWakeUp = PendingIntent.getBroadcast(context, 24, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                mALWednesdayWakeUp.setExact(AlarmManager.RTC_WAKEUP, timeWakeUpt4 + oneweek, mPDWednesdayWakeUp);
                break;
            case 25:
                Log.e(TAG, "case = 25");

                checkNgayThangWakeUp(context, intent, checkThursday);
                AlarmManager mALThursdayWakeUp = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 25);
                intent.putExtra("TimeWakeUp", true);
                intent.putExtra("KEYTIME_T5_WU", timeWakeUpt5 + oneweek);
                PendingIntent mPDThursdayWakeUp = PendingIntent.getBroadcast(context, 25, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                mALThursdayWakeUp.setExact(AlarmManager.RTC_WAKEUP, timeWakeUpt5 + oneweek, mPDThursdayWakeUp);
                break;
            case 26:
                Log.e(TAG, "case = 26");

                checkNgayThangWakeUp(context, intent, checkFriday);
                AlarmManager mALFridayWakeUp = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 26);
                intent.putExtra("TimeWakeUp", true);
                intent.putExtra("KEYTIME_T6_WU", timeWakeUpt6 + oneweek);
                PendingIntent mPDFridayWakeUp = PendingIntent.getBroadcast(context, 26, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                mALFridayWakeUp.setExact(AlarmManager.RTC_WAKEUP, timeWakeUpt6 + oneweek, mPDFridayWakeUp);
                break;
            case 27:
                Log.e(TAG, "case = 27");

                checkNgayThangWakeUp(context, intent, checkSatuday);
                AlarmManager mALSatudayWakeUp = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmRecever.class);
                intent.putExtra("KEY", 27);
                intent.putExtra("TimeWakeUp", true);
                intent.putExtra("KEYTIME_T7_WU", timeWakeUpt7 + oneweek);
                PendingIntent mPDSatudayWakeUp = PendingIntent.getBroadcast(context, 27, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                mALSatudayWakeUp.setExact(AlarmManager.RTC_WAKEUP, timeWakeUpt7 + oneweek, mPDSatudayWakeUp);
                break;
            case 28:
                Log.e(TAG, "case = 28");
                checkNgayThangWakeUp(context, intent, checkSunday);
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

    private void checkNgayThangWakeUp(Context context, Intent intent, boolean b) {
        if (b == true) {
            if (mTimeWakeUp == true) {
                mTimeWakeUp = false;
                startServiceWakeUpCall(context, intent);
                Log.e(TAG, "vào wake up");
            } else {
            }
        } else {

        }
    }

    private void startServiceWakeUpCall(Context context, Intent intent) {
        if (checkOnOff == true) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                intent = new Intent(context,MyServices.class);
                intent.putExtra("wakeup",true);
                context.startForegroundService(intent);
            } else {
                intent = new Intent(context,MyServices.class);
                intent.putExtra("wakeup",true);
                context.startService(intent);
            }
//            intent = new Intent("sendTimeWakeUp");
//            context.sendBroadcast(intent);
            Log.e(TAG, "mTimeWakeUp=true");
        } else {

        }

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
}
