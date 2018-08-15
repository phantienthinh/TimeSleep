package com.example.tienthinh.timesleep.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.tienthinh.timesleep.R;

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
        return PreferenceManager.getDefaultSharedPreferences(context).getString("KEY_BH", "Little Comfortv");
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


    public static void setNhacNho(Context context, String is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString("KEY_NhacNho", is).apply();
    }

    public static String getNhacNho(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("KEY_NhacNho",context.getResources().getString(R.string.at_sleeping));
    }

    public static void set15p(Context context, boolean is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean("KEY_Check15P", is).apply();
    }

    public static boolean get15p(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("KEY_Check15P", false);
    }

    public static void set30p(Context context, boolean is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean("KEY_Check30P", is).apply();
    }

    public static boolean get30p(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("KEY_Check30P", false);
    }

    public static void setSleepNow(Context context, boolean is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean("KEY_CheckSleepNow", is).apply();
    }

    public static boolean getSleepNow(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("KEY_CheckSleepNow", true);
    }

    public static void set5p(Context context, boolean is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean("KEY_Check5P", is).apply();
    }

    public static boolean get5p(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("KEY_Check5P", false);
    }

    public static void set1hour(Context context, boolean is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean("KEY_Check1hour", is).apply();
    }

    public static boolean get1hour(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("KEY_Check1hour", false);
    }

    public static void setDK(Context context, boolean is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean("KEY_DK", is).apply();
    }

    public static boolean getDK(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("KEY_DK", false);
    }

    public static void setT2(Context context, float is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putFloat("KEY_T2", is).apply();
    }

    public static float getT2(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getFloat("KEY_T2", 0);
    }
    public static void setT3(Context context, float is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putFloat("KEY_T3", is).apply();
    }

    public static float getT3(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getFloat("KEY_T3", 0);
    }

    public static void setT4(Context context, float is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putFloat("KEY_T4", is).apply();
    }

    public static float getT4(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getFloat("KEY_T4", 0);
    }
    public static void setT5(Context context, float is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putFloat("KEY_T5", is).apply();
    }

    public static float getT5(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getFloat("KEY_T5", 0);
    }
    public static void setT6(Context context, float is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putFloat("KEY_T6", is).apply();
    }

    public static float getT6(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getFloat("KEY_T6", 0);
    }
    public static void setT7(Context context, float is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putFloat("KEY_T7", is).apply();
    }

    public static float getT7(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getFloat("KEY_T7", 0);
    }
    public static void setCN(Context context, float is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putFloat("KEY_CN", is).apply();
    }

    public static float getCN(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getFloat("KEY_CN", 0);
    }

    public static void setTongHourSleep(Context context, int is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt("KEY_TongHourSleep", is).apply();
    }

    public static int getTongHourSleep(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("KEY_TongHourSleep", 0);
    }

    public static void setTongMinuteSleep(Context context, int is) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt("KEY_TongMinuteSleep", is).apply();
    }

    public static int getTongMinuteSleep(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("KEY_TongMinuteSleep", 0);
    }
}