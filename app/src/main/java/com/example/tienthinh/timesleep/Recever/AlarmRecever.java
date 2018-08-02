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
    private static final long oneweek = 604800000;
    private static final String TAG = "Debug";
    boolean mTimeSleep = false;
    private int id;
    private long timeSleept2,timeSleept3,timeSleept4,timeSleept5,timeSleept6,timeSleept7,timeSleepCN;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive: ");
        mTimeSleep = intent.getBooleanExtra("TimeSleep", false);
        id = intent.getIntExtra("KEY",0);
        timeSleept2 = intent.getLongExtra("KEYTIME_T2",0);
        timeSleept3 = intent.getLongExtra("KEYTIME_T3",0);
        timeSleept4 = intent.getLongExtra("KEYTIME_T4",0);
        timeSleept5 = intent.getLongExtra("KEYTIME_T5",0);
        timeSleept6 = intent.getLongExtra("KEYTIME_T6",0);
        timeSleept7 = intent.getLongExtra("KEYTIME_T7",0);
        timeSleepCN = intent.getLongExtra("KEYTIME_CN",0);


        if (mTimeSleep == true) {
            mTimeSleep = false;
            if (SharedPreferencesManager.getToggleOnOff(context)==true){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(new Intent(context, MyServices.class));
                } else {
                    context.startService(new Intent(context, MyServices.class));
                }
                intent = new Intent("sendTimeSleep");
                context.sendBroadcast(intent);
                Log.e(TAG, "mTimeSleep=true");
            }else {

            }

        } else {
            Log.e(TAG, "nguoc lai");
        }

        switch (id){
            case 2:
                AlarmManager alarmManagerMonday = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context,AlarmRecever.class);
                intent.putExtra("KEY",2);
                intent.putExtra("TimeSleep",true);
                intent.putExtra("KEYTIME_T2",timeSleept2+ oneweek);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context,2,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManagerMonday.setExact(AlarmManager.RTC_WAKEUP,timeSleept2+oneweek,pendingIntent);
                break;
            case 3:
                AlarmManager alarmManagerTuesday= (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context,AlarmRecever.class);
                intent.putExtra("KEY",3);
                intent.putExtra("TimeSleep",true);
                intent.putExtra("KEYTIME_T2",timeSleept3+ oneweek);
                PendingIntent pendingIntentTuesday = PendingIntent.getBroadcast(context,3,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManagerTuesday.setExact(AlarmManager.RTC_WAKEUP,timeSleept3+oneweek,pendingIntentTuesday);
                break;
            case 4:
                AlarmManager alarmManagerWednesday= (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context,AlarmRecever.class);
                intent.putExtra("KEY",4);
                intent.putExtra("TimeSleep",true);
                intent.putExtra("KEYTIME_T2",timeSleept4+ oneweek);
                PendingIntent pendingIntentWednesday = PendingIntent.getBroadcast(context,4,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManagerWednesday.setExact(AlarmManager.RTC_WAKEUP,timeSleept4+oneweek,pendingIntentWednesday);
                break;
            case 5:
                AlarmManager alarmManagerThursday= (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context,AlarmRecever.class);
                intent.putExtra("KEY",5);
                intent.putExtra("TimeSleep",true);
                intent.putExtra("KEYTIME_T2",timeSleept5+ oneweek);
                PendingIntent pendingIntentThursday = PendingIntent.getBroadcast(context,5,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManagerThursday.setExact(AlarmManager.RTC_WAKEUP,timeSleept5+oneweek,pendingIntentThursday);
                break;
            case 6:
                AlarmManager alarmManagerFriday= (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context,AlarmRecever.class);
                intent.putExtra("KEY",6);
                intent.putExtra("TimeSleep",true);
                intent.putExtra("KEYTIME_T2",timeSleept6+ oneweek);
                PendingIntent pendingIntentFriday = PendingIntent.getBroadcast(context,6,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManagerFriday.setExact(AlarmManager.RTC_WAKEUP,timeSleept6+oneweek,pendingIntentFriday);
                break;
            case 7:
                AlarmManager alarmManagerSaturday= (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context,AlarmRecever.class);
                intent.putExtra("KEY",7);
                intent.putExtra("TimeSleep",true);
                intent.putExtra("KEYTIME_T2",timeSleept7+ oneweek);
                PendingIntent pendingIntentSaturday = PendingIntent.getBroadcast(context,7,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManagerSaturday.setExact(AlarmManager.RTC_WAKEUP,timeSleept6+oneweek,pendingIntentSaturday);
                break;
            case 8:
                AlarmManager alarmManagerSunday= (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context,AlarmRecever.class);
                intent.putExtra("KEY",7);
                intent.putExtra("TimeSleep",true);
                intent.putExtra("KEYTIME_T2",timeSleepCN+ oneweek);
                PendingIntent pendingIntentSunday = PendingIntent.getBroadcast(context,7,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManagerSunday.setExact(AlarmManager.RTC_WAKEUP,timeSleepCN+oneweek,pendingIntentSunday);
                break;
        }


    }
}
