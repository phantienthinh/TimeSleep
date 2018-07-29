package com.example.tienthinh.timesleep.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesManager {

    public static void setTimeSleep(Context context, String is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString("KEY_TimeSleep", is).apply();
    }

    public static String getTimeSleep(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("KEY_TimeSleep", "00:00");
    }

    public static void setTimeWakeUp(Context context, String is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString("KEY_TimeWakeUp", is).apply();
    }

    public static String getTimeWakeUp(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("KEY_TimeWakeUp", "00:00");
    }

    public static void setTongTime(Context context, String is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString("KEY_TongTime", is).apply();
    }

    public static String getTongTime(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("KEY_TongTime", "00:00");
    }

    public static void setVolume(Context context, int is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt("KEY_volume", is).apply();
    }

    public static int getVolume(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("KEY_volume", 0);
    }

    public static void setTenBaiHat(Context context, String is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString("KEY_BH", is).apply();
    }

    public static String getTenBaiHat(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("KEY_BH", "BaiHat");
    }

    public static void setPositionSound(Context context, int is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt("KEY_Position_Sound", is).apply();
    }

    public static int getPositionSound(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("KEY_Position_Sound", 0);
    }

    public static void setPositionClockSleep(Context context, int is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt("KEY_Position_Clock_Sleep", is).apply();
    }

    public static int getPositionClockSleep(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("KEY_Position_Clock_Sleep", 0);
    }

    public static void setPositionClockWakeUp(Context context, int is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt("KEY_Position_Clock_WakeUp", is).apply();
    }

    public static int getPositionClockWakeUp(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("KEY_Position_Clock_WakeUp", 0);
    }

    public static void setHourWakeUp(Context context, int is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt("KEY_Hour_WakeUp", is).apply();
    }

    public static int getHourWakeUp(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("KEY_Hour_WakeUp", 0);
    }

    public static void setMinuteWakeUp(Context context, int is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt("KEY_Minute_WakeUp", is).apply();
    }

    public static int getMinuteWakeUp(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("KEY_Minute_WakeUp", 0);
    }

    public static void setHourSleep(Context context, int is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt("KEY_HourSleep", is).apply();
    }

    public static int getHourSleep(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("KEY_HourSleep", 0);
    }


    public static void setMinuteSleep(Context context, int is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt("KEY_Minute_Sleep", is).apply();
    }

    public static int getMinuteSleep(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("KEY_Minute_Sleep", 0);
    }

    public static void setToggleOnOff(Context context, boolean is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean("KEY_On/off", is).apply();
    }

    public static boolean getToggleOnOff(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("KEY_On/off", false);
    }
    public static void setMonday(Context context, boolean is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean("KEY_Monday", is).apply();
    }

    public static boolean getMonday(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("KEY_Monday", false);
    }
    public static void setTuesday(Context context, boolean is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean("KEY_Tuesday", is).apply();
    }

    public static boolean getTuesday(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("KEY_Tuesday", false);
    }

    public static void setWednesday(Context context, boolean is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean("KEY_Wednesday", is).apply();
    }

    public static boolean getWednesday(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("KEY_Wednesday", false);
    }

    public static void setThursday(Context context, boolean is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean("KEY_Thursday", is).apply();
    }

    public static boolean getThursday(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("KEY_Thursday", false);
    }

    public static void setFriday(Context context, boolean is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean("KEY_Friday", is).apply();
    }

    public static boolean getFriday(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("KEY_Friday", false);
    }

    public static void setSaturday(Context context, boolean is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean("KEY_Saturday", is).apply();
    }

    public static boolean getSaturday(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("KEY_Saturday", false);
    }

    public static void setSunday(Context context, boolean is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean("KEY_Sunday", is).apply();
    }

    public static boolean getSunday(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("KEY_Sunday", false);
    }


}