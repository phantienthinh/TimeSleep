package com.example.tienthinh.timesleep.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesManager {

    public static void setTimeSleep(Context context, String is){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString("KEY_TimeSleep", is).apply();
    }

    public static String getTimeSleep(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getString("KEY_TimeSleep", "00:00");
    }

    public static void setTimeWakeUp(Context context, String is){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString("KEY_TimeWakeUp", is).apply();
    }

    public static String getTimeWakeUp(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getString("KEY_TimeWakeUp", "00:00");
    }

    public static void setTongTime(Context context, String is){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString("KEY_TongTime", is).apply();
    }

    public static String getTongTime(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getString("KEY_TongTime", "00:00");
    }
}
