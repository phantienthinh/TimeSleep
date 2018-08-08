package com.example.tienthinh.timesleep;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tienthinh.timesleep.Recever.AlarmRecever;
import com.example.tienthinh.timesleep.Recever.AlarmReceverWakeUp;
import com.example.tienthinh.timesleep.fragment.FragmentOne;
import com.example.tienthinh.timesleep.fragment.FragmentThree;
import com.example.tienthinh.timesleep.fragment.FragmentTwo;
import com.example.tienthinh.timesleep.model.SharedPreferencesManager;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    long timeMondayWakeUp, timeTuesdayWakeUp, timeWednesdayWakeUp, timeThursdayWakeUp, timeFridayWakeUp, timeSaturdayWakeUp, timeSundayWakeUp;
    private static final String KEYSLEEP = "KEY";
    private static final String KEYSLEEPWAKEUP = "KEYWU";
    boolean mTruoc5p, mTruoc15p, mTruoc30p, mTruoc1h, mSleepNow;
    private long oneWeek = 604800000;
    long timeSystem, timeSystemWakeUp;
    private long twoDay = 172800000;
    private long threeDay = 259200000;
    private long fourDay = 345600000;
    private long fiveDay = 432000000;
    private long sixDay = 518400000;
    private long oneDay = 86400000;
    private long truoc15p = 900000;
    private long truoc30p = 1800000;
    private long truoc5p = 300000;
    private long truoc1hour = 3600000;
    private int date;
    public static String TAG = "debug";
    long timesleepMonday, timesleepTuesday, timesleepWednesday, timesleepThursday, timesleepFriday, timesleepSaturday, timesleepSunday;
    long t2, t3, t4, t5, t6, t7, cn;
    long t2_WU, t3_WU, t4_WU, t5_WU, t6_WU, t7_WU, cn_WU;
    private Calendar mCalendarMondaySleep, mCalendarTuesdaySleep, mCalendarWednesdaySleep, mCalendarThursdaySleep, mCalendarFridaySleep;
    private Calendar mCalendarSaturdaySleep;
    private Calendar mCalendarSundaySleep;
    private Calendar mCaSundayWakeUp, mCaMondayWakeUp, mCaTuesdayWakeUp, mCaWednesdayWakeUp, mCaThursdayWakeUp, mCaFridayWakeUp, mCaSaturdayWakeUp;
    AlarmManager mAlarmManagerMondaySleep;
    PendingIntent mPendingIntentMondaySleep;
    AlarmManager mAlarmManagerTuesdaySleep;
    PendingIntent mPendingIntentTuesdaySleep;
    AlarmManager mAlarmManagerWednesdaySleep;
    PendingIntent mPendingIntentWednesdaySleep;
    AlarmManager mAlarmManagerThursdaySleep;
    PendingIntent mPendingIntentThursdaySleep;
    AlarmManager mAlarmManagerFridaySleep;
    PendingIntent mPendingIntentFridaySleep;
    AlarmManager mAlarmManagerSaturdaySleep;
    PendingIntent mPendingIntentSaturdaySleep;
    AlarmManager mAlarmManagerSundaySleep;
    PendingIntent mPendingIntentSundaySleep;

    PendingIntent mPdMondayWakeUp, mPdTuesdayWakeUp, mPdWednesdayWakeUp, mPdThursdayWakeUp, mPdFridaydayWakeUp, mPdSaturdayWakeUp, mPdSundayWakeUp;
    AlarmManager mALMondayWakeUp, mALTuesdayWakeUp, mALWednesdayWakeUp, mALThursdayWakeUp, mALFridayWakeUp, mALSaturdayWakeUp, mALSundayWakeUp;
    boolean mBooleanMonday;
    boolean mBooleanTuesday;
    boolean mBooleanWednesday;
    boolean mBooleanThursday;
    boolean mBooleanFriday;
    boolean mBooleanSaturday;
    boolean mBooleanSunday;
    public static int hourSleep;
    public static int minuteSleep;
    public static int hourWakeUp;
    public static int minuteWakeUp;
    private BroadcastReceiver receiver;
    private IntentFilter filter;
    boolean doubleBackToExitPressedOnce = false;
    ImageView imv_setting;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new FragmentOne();
                    break;
                case R.id.navigation_dashboard:
                    selectedFragment = new FragmentTwo();
                    break;
                case R.id.navigation_notifications:
                    selectedFragment = new FragmentThree();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmnet_container, selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onClickImvSetting();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmnet_container
                        , new FragmentOne())
                .commit();
        createCalendar();
        createAlarmService();
        khoiTaoBroadCast();
        createWakeLock();
        //lấy thứ
        Calendar calendar = Calendar.getInstance();
        date = calendar.get(Calendar.DAY_OF_WEEK);


    }

    private void createWakeLock() {

//        PowerManager mgr = (PowerManager) getSystemService(Context.POWER_SERVICE);
//        PowerManager.WakeLock wakeLock = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyWakeLock");
//        wakeLock.acquire();
//        wakeLock.release();
    }

    private void createCalendar() {
        //calendar h đi ngủ
        mCalendarMondaySleep = Calendar.getInstance();
        mCalendarTuesdaySleep = Calendar.getInstance();
        mCalendarWednesdaySleep = Calendar.getInstance();
        mCalendarThursdaySleep = Calendar.getInstance();
        mCalendarFridaySleep = Calendar.getInstance();
        mCalendarSaturdaySleep = Calendar.getInstance();
        mCalendarSundaySleep = Calendar.getInstance();

        //calendar h thức dậy
        mCaMondayWakeUp = Calendar.getInstance();
        mCaTuesdayWakeUp = Calendar.getInstance();
        mCaWednesdayWakeUp = Calendar.getInstance();
        mCaThursdayWakeUp = Calendar.getInstance();
        mCaFridayWakeUp = Calendar.getInstance();
        mCaSaturdayWakeUp = Calendar.getInstance();
        mCaSundayWakeUp = Calendar.getInstance();
    }

    private void createAlarmService() {
        //alarm h ngủ
        mAlarmManagerMondaySleep = (AlarmManager) getSystemService(ALARM_SERVICE);
        mAlarmManagerTuesdaySleep = (AlarmManager) getSystemService(ALARM_SERVICE);
        mAlarmManagerWednesdaySleep = (AlarmManager) getSystemService(ALARM_SERVICE);
        mAlarmManagerThursdaySleep = (AlarmManager) getSystemService(ALARM_SERVICE);
        mAlarmManagerFridaySleep = (AlarmManager) getSystemService(ALARM_SERVICE);
        mAlarmManagerSaturdaySleep = (AlarmManager) getSystemService(ALARM_SERVICE);
        mAlarmManagerSundaySleep = (AlarmManager) getSystemService(ALARM_SERVICE);

        //alarm h thức dậy
        mALMondayWakeUp = (AlarmManager) getSystemService(ALARM_SERVICE);
        mALTuesdayWakeUp = (AlarmManager) getSystemService(ALARM_SERVICE);
        mALWednesdayWakeUp = (AlarmManager) getSystemService(ALARM_SERVICE);
        mALThursdayWakeUp = (AlarmManager) getSystemService(ALARM_SERVICE);
        mALFridayWakeUp = (AlarmManager) getSystemService(ALARM_SERVICE);
        mALSaturdayWakeUp = (AlarmManager) getSystemService(ALARM_SERVICE);
        mALSundayWakeUp = (AlarmManager) getSystemService(ALARM_SERVICE);


    }

    private void khoiTaoBroadCast() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case "TimeSleepActivity":
                        Log.e(TAG, "đã vào TimeSleepActivity ");
                        cancleAlarmManagerSleep();
                        readSharedPreDay();

                        //thứ2
//                        if (mBooleanMonday == true) {
                            setCalendarMondaySleep();
                            soSanh();
                            cancleAlarm(mAlarmManagerMondaySleep, mPendingIntentMondaySleep);
                            intent = new Intent(MainActivity.this, AlarmRecever.class);
                            intent.putExtra(KEYSLEEP, 2);
                            intent.putExtra("KEYTIME_T2", t2);
                            createCalenda(intent, mPendingIntentMondaySleep, mAlarmManagerMondaySleep, t2, 2);
//                        } else {
//                            cancleAlarm(mAlarmManagerMondaySleep, mPendingIntentMondaySleep);
//                        }
                        //thứ 3
//                        if (mBooleanTuesday == true) {
                            setCalendarTuesdaySleep();
                            soSanh();
                            cancleAlarm(mAlarmManagerTuesdaySleep, mPendingIntentTuesdaySleep);
                            intent = new Intent(MainActivity.this, AlarmRecever.class);
                            intent.putExtra(KEYSLEEP, 3);
                            intent.putExtra("KEYTIME_T3", t3);
                            createCalenda(intent, mPendingIntentTuesdaySleep, mAlarmManagerTuesdaySleep, t3, 3);
//                        } else {
//                            cancleAlarm(mAlarmManagerTuesdaySleep, mPendingIntentTuesdaySleep);
//                        }

                        //thứ 4
//                        if (mBooleanWednesday == true) {
                            setCalendarWednesdaySleep();
                            soSanh();
                            cancleAlarm(mAlarmManagerWednesdaySleep, mPendingIntentWednesdaySleep);
                            intent = new Intent(MainActivity.this, AlarmRecever.class);
                            intent.putExtra(KEYSLEEP, 4);
                            intent.putExtra("KEYTIME_T4", t4);
                            createCalenda(intent, mPendingIntentWednesdaySleep, mAlarmManagerWednesdaySleep, t4, 4);

//                        } else {
//                            cancleAlarm(mAlarmManagerWednesdaySleep, mPendingIntentWednesdaySleep);
//                        }

                        //thứ 5
//                        if (mBooleanThursday == true) {
                            setCalendarThursdaySleep();
                            soSanh();

                            cancleAlarm(mAlarmManagerThursdaySleep, mPendingIntentThursdaySleep);
                            intent = new Intent(MainActivity.this, AlarmRecever.class);
                            intent.putExtra(KEYSLEEP, 5);
                            intent.putExtra("KEYTIME_T5", t5);
                            createCalenda(intent, mPendingIntentThursdaySleep, mAlarmManagerThursdaySleep, t5, 5);

//                        } else {
//                            cancleAlarm(mAlarmManagerThursdaySleep, mPendingIntentThursdaySleep);
//                        }

                        //thứ 6
//                        if (mBooleanFriday == true) {
                            setCalendarFridaySleep();
                            soSanh();
                            cancleAlarm(mAlarmManagerFridaySleep, mPendingIntentFridaySleep);
                            intent = new Intent(MainActivity.this, AlarmRecever.class);
                            intent.putExtra(KEYSLEEP, 6);
                            intent.putExtra("KEYTIME_T6", t6);
                            createCalenda(intent, mPendingIntentFridaySleep, mAlarmManagerFridaySleep, t6, 6);
//                        } else {
//                            cancleAlarm(mAlarmManagerFridaySleep, mPendingIntentFridaySleep);
//                        }

//                        thứ 7
//                        if (mBooleanSaturday == true) {
                            setCalendarSaturdaySleep();
                            soSanh();
                            cancleAlarm(mAlarmManagerSaturdaySleep, mPendingIntentSaturdaySleep);
                            intent = new Intent(MainActivity.this, AlarmRecever.class);
                            intent.putExtra(KEYSLEEP, 7);
                            intent.putExtra("KEYTIME_T7", t7);
                            createCalenda(intent, mPendingIntentSaturdaySleep, mAlarmManagerSaturdaySleep, t7, 7);

//                        } else {
//                            cancleAlarm(mAlarmManagerSaturdaySleep, mPendingIntentSaturdaySleep);
//                        }


                        //chủ nhật
//                        if (mBooleanSunday == true) {
                            setCalendarSundaySleep();
                            soSanh();
                            cancleAlarm(mAlarmManagerSundaySleep, mPendingIntentSundaySleep);
                            intent = new Intent(MainActivity.this, AlarmRecever.class);
                            intent.putExtra(KEYSLEEP, 8);
                            intent.putExtra("KEYTIME_CN", cn);
                            createCalenda(intent, mPendingIntentSundaySleep, mAlarmManagerSundaySleep, cn, 8);

//                        } else {
//                            cancleAlarm(mAlarmManagerSundaySleep, mPendingIntentSundaySleep);
//                        }
                        break;

                    case "TimeWakeUpActivity":
                        cancleAlarmManagerWakeUp();
                        readSharedPreDay();
                        Log.e(TAG, "vao TimeWakeUpActivity" );
                        //thứ 2
//                        if (mBooleanMonday == true) {
                            setCalendarMondayWakeUp();
                            soSanhTimeWakeUp();
                            cancleAlarm(mALMondayWakeUp, mPdMondayWakeUp);
                            intent = new Intent(MainActivity.this,AlarmReceverWakeUp.class);
                            intent.putExtra(KEYSLEEPWAKEUP,22);
                            intent.putExtra("KEYTIME_T2_WU",t2_WU);
                            createAlarm(intent,mPdMondayWakeUp,mALMondayWakeUp,t2_WU,22);
//                        } else {
//                            cancleAlarm(mALMondayWakeUp, mPdMondayWakeUp);
//                        }

                        //thứ 3
//                        if (mBooleanTuesday == true) {
                            setCalendarTuesdayWakeUp();
                            soSanhTimeWakeUp();
                            cancleAlarm(mALTuesdayWakeUp, mPdTuesdayWakeUp);
                            intent = new Intent(MainActivity.this,AlarmReceverWakeUp.class);
                            intent.putExtra(KEYSLEEPWAKEUP,23);
                            intent.putExtra("KEYTIME_T3_WU",t3_WU);
                            createAlarm(intent,mPdTuesdayWakeUp,mALTuesdayWakeUp,t3_WU,23);

//                        } else {
//                            cancleAlarm(mALTuesdayWakeUp, mPdTuesdayWakeUp);
//                        }

                        //thứ 4
//                        if (mBooleanWednesday == true) {
                            setmCalendarWednesdayWakeUp();
                            soSanhTimeWakeUp();
                            cancleAlarm(mALWednesdayWakeUp, mPdWednesdayWakeUp);
                            intent = new Intent(MainActivity.this,AlarmReceverWakeUp.class);
                            intent.putExtra(KEYSLEEPWAKEUP,24);
                            intent.putExtra("KEYTIME_T4_WU",t4_WU);
                            createAlarm(intent,mPdWednesdayWakeUp,mALWednesdayWakeUp,t4_WU,24);
//                        } else {
//                            cancleAlarm(mALWednesdayWakeUp, mPdWednesdayWakeUp);
//                        }
                        //thứ 5
//                        if (mBooleanThursday == true) {
                            setmCalendarThursdayWakeUp();
                            soSanhTimeWakeUp();
                            cancleAlarm(mALThursdayWakeUp, mPdThursdayWakeUp);
                            intent = new Intent(MainActivity.this,AlarmReceverWakeUp.class);
                            intent.putExtra(KEYSLEEPWAKEUP,25);
                            intent.putExtra("KEYTIME_T5_WU",t5_WU);
                            createAlarm(intent,mPdThursdayWakeUp,mALThursdayWakeUp,t5_WU,25);
//                        } else {
//                            cancleAlarm(mALThursdayWakeUp, mPdThursdayWakeUp);
//                        }
                        //thứ 6
//                        if (mBooleanFriday == true) {
                            setmCalendarFridayWakeUp();
                            soSanhTimeWakeUp();
                            cancleAlarm(mALFridayWakeUp, mPdFridaydayWakeUp);
                            intent = new Intent(MainActivity.this,AlarmReceverWakeUp.class);
                            intent.putExtra(KEYSLEEPWAKEUP,26);
                            intent.putExtra("KEYTIME_T6_WU",t6_WU);
                            createAlarm(intent,mPdFridaydayWakeUp,mALFridayWakeUp,t6_WU,26);
//                        } else {
//                            cancleAlarm(mALFridayWakeUp, mPdFridaydayWakeUp);
//                        }
                        //thứ 7
//                        if (mBooleanSaturday == true) {
                            setmCalendarSaturdayWakeUp();
                            soSanhTimeWakeUp();
                            cancleAlarm(mALSaturdayWakeUp, mPdSaturdayWakeUp);
                            intent = new Intent(MainActivity.this,AlarmReceverWakeUp.class);
                            intent.putExtra(KEYSLEEPWAKEUP,27);
                            intent.putExtra("KEYTIME_T7_WU",t7_WU);
                            createAlarm(intent,mPdSaturdayWakeUp,mALSaturdayWakeUp,t7_WU,27);
//                        } else {
//                            cancleAlarm(mALSaturdayWakeUp, mPdSaturdayWakeUp);
//                        }
                        //chủ nhật
//                        if (mBooleanSunday == true) {
                            setmCalendarSundayWakeUp();
                            soSanhTimeWakeUp();
                            cancleAlarm(mALSundayWakeUp, mPdSundayWakeUp);
                            intent = new Intent(MainActivity.this,AlarmReceverWakeUp.class);
                            intent.putExtra(KEYSLEEPWAKEUP,28);
                            intent.putExtra("KEYTIME_CN_WU",cn_WU);
                            createAlarm(intent,mPdSundayWakeUp,mALSundayWakeUp,cn_WU,28);
//                        } else {
//                            cancleAlarm(mALSundayWakeUp, mPdSundayWakeUp);
//                        }


                        break;
                }
            }
        };
        filter = new IntentFilter();
        filter.addAction("TimeSleepActivity");
        filter.addAction("TimeWakeUpActivity");
        MainActivity.this.registerReceiver(receiver, filter);
    }

    private void soSanhTimeWakeUp() {
        switch (date) {
            case 1:
                timeWakeUpSunday();
                break;
            case 2:
                timeWakeUpMonday();
                break;
            case 3:
                timeWakeUpTuesday();
                break;
            case 4:
                timeWakeUpWednesday();
                break;
            case 5:
                timeWakeUpThursday();
                break;
            case 6:
                timeWakeUpFriday();
                break;
            case 7:
                timeWakeUpSaturday();
                break;
        }
    }

    private void timeWakeUpSaturday() {
        if (mTruoc30p == true) {
            setTimeWakeupSaturday(truoc30p);
        } else if (mTruoc15p == true) {
            setTimeWakeupSaturday(truoc15p);
        } else if (mTruoc5p == true) {
            setTimeWakeupSaturday(truoc5p);
        } else if (mTruoc1h == true) {
            setTimeWakeupSaturday(truoc1hour);
        } else if (mSleepNow == true) {
            setTimeWakeupSaturday(0);
        } else {
        }
    }

    private void setTimeWakeupSaturday(long mlong) {
        if (timeSystemWakeUp < (timeSaturdayWakeUp - mlong)) {
            t7_WU = timeSaturdayWakeUp - mlong;
        } else {
            t7_WU = timeSaturdayWakeUp - mlong + oneWeek;
        }
        t3_WU = timeTuesdayWakeUp + (threeDay - mlong);
        t4_WU = timeWednesdayWakeUp + (fourDay - mlong);
        t5_WU = timeThursdayWakeUp + (fiveDay - mlong);
        t6_WU = timeFridayWakeUp + (sixDay - mlong);
        cn_WU = timeSundayWakeUp + (oneDay - mlong);
        t2_WU = timeMondayWakeUp + (twoDay - mlong);
    }

    private void timeWakeUpFriday() {
        if (mTruoc30p == true) {
            setTimeWakeupFriday(truoc30p);
        } else if (mTruoc15p == true) {
            setTimeWakeupFriday(truoc15p);
        } else if (mTruoc5p == true) {
            setTimeWakeupFriday(truoc5p);
        } else if (mTruoc1h == true) {
            setTimeWakeupFriday(truoc1hour);
        } else if (mSleepNow == true) {
            setTimeWakeupFriday(0);
        } else {
        }
    }

    private void setTimeWakeupFriday(long mlong) {
        if (timeSystemWakeUp < (timeFridayWakeUp - mlong)) {
            t6_WU = timeFridayWakeUp - mlong;
        } else {
            t6_WU = timeFridayWakeUp - mlong + oneWeek;
        }
        t3_WU = timeTuesdayWakeUp + (fourDay - mlong);
        t4_WU = timeWednesdayWakeUp + (fiveDay - mlong);
        t5_WU = timeThursdayWakeUp + (sixDay - mlong);
        t7_WU = timeSaturdayWakeUp + (oneDay - mlong);
        cn_WU = timeSundayWakeUp + (twoDay - mlong);
        t2_WU = timeMondayWakeUp + (threeDay - mlong);
    }

    private void timeWakeUpThursday() {
        if (mTruoc30p == true) {
            setTimeWakeupThursday(truoc30p);
        } else if (mTruoc15p == true) {
            setTimeWakeupThursday(truoc15p);
        } else if (mTruoc5p == true) {
            setTimeWakeupThursday(truoc5p);
        } else if (mTruoc1h == true) {
            setTimeWakeupThursday(truoc1hour);
        } else if (mSleepNow == true) {
            setTimeWakeupThursday(0);
        } else {
        }

    }

    private void setTimeWakeupThursday(long mlong) {
        if (timeSystemWakeUp < (timeThursdayWakeUp - mlong)) {
            t5_WU = timeThursdayWakeUp - mlong;
        } else {
            t5_WU = timeThursdayWakeUp - mlong + oneWeek;
        }
        t3_WU = timeTuesdayWakeUp + (fiveDay - mlong);
        t4_WU = timeWednesdayWakeUp + (sixDay - mlong);
        t6_WU = timeFridayWakeUp + (oneDay - mlong);
        t7_WU = timeSaturdayWakeUp + (twoDay - mlong);
        cn_WU = timeSundayWakeUp + (threeDay - mlong);
        t2_WU = timeMondayWakeUp + (fourDay - mlong);
    }

    private void timeWakeUpWednesday() {

        if (mTruoc30p == true) {
            setTimeWakeupWednesday(truoc30p);
        } else if (mTruoc15p == true) {
            setTimeWakeupWednesday(truoc15p);
        } else if (mTruoc5p == true) {
            setTimeWakeupWednesday(truoc5p);
        } else if (mTruoc1h == true) {
            setTimeWakeupWednesday(truoc1hour);
        } else if (mSleepNow == true) {
            setTimeWakeupWednesday(0);
        } else {
        }

    }

    private void setTimeWakeupWednesday(long mlong) {
        if (timeSystemWakeUp < (timeWednesdayWakeUp - mlong)) {
            t4_WU = timeWednesdayWakeUp - mlong;
        } else {
            t4_WU = timeWednesdayWakeUp - mlong + oneWeek;
        }
        t3_WU = timeTuesdayWakeUp + (sixDay - mlong);
        t5_WU = timeThursdayWakeUp + (oneDay - mlong);
        t6_WU = timeFridayWakeUp + (twoDay - mlong);
        t7_WU = timeSaturdayWakeUp + (threeDay - mlong);
        cn_WU = timeSundayWakeUp + (fourDay - mlong);
        t2_WU = timeMondayWakeUp + (fiveDay - mlong);
    }

    private void timeWakeUpTuesday() {
        if (mTruoc30p == true) {
            setTimeWakeupTuesday(truoc30p);
        } else if (mTruoc15p == true) {
            setTimeWakeupTuesday(truoc15p);
        } else if (mTruoc5p == true) {
            setTimeWakeupTuesday(truoc5p);
        } else if (mTruoc1h == true) {
            setTimeWakeupTuesday(truoc1hour);
        } else if (mSleepNow == true) {
            setTimeWakeupTuesday(0);
        } else {
        }
    }

    private void setTimeWakeupTuesday(long mlong) {
        if (timeSystemWakeUp < (timeTuesdayWakeUp - mlong)) {
            t3_WU = timeTuesdayWakeUp + (oneDay - mlong);
        } else {
            t3_WU = timeTuesdayWakeUp - mlong + oneWeek;
        }
        t4_WU = timeWednesdayWakeUp + (oneDay - mlong);
        t5_WU = timeThursdayWakeUp + (twoDay - mlong);
        t6_WU = timeFridayWakeUp + (threeDay - mlong);
        t7_WU = timeSaturdayWakeUp + (fourDay - mlong);
        cn_WU = timeSundayWakeUp + (fiveDay - mlong);
        t2_WU = timeMondayWakeUp + (sixDay - mlong);
    }

    private void timeWakeUpMonday() {
        if (mTruoc30p == true) {
            setTimeWakeupMonDay(truoc30p);
        } else if (mTruoc15p == true) {
            setTimeWakeupMonDay(truoc15p);
        } else if (mTruoc5p == true) {
            setTimeWakeupMonDay(truoc5p);
        } else if (mTruoc1h == true) {
            setTimeWakeupMonDay(truoc1hour);
        } else if (mSleepNow == true) {
            setTimeWakeupMonDay(0);
        } else {
        }

    }

    private void setTimeWakeupMonDay(long mlong) {
        t3_WU = timeTuesdayWakeUp + (oneDay - mlong);
        t4_WU = timeWednesdayWakeUp + (twoDay - mlong);
        t5_WU = timeThursdayWakeUp + (threeDay - mlong);
        t6_WU = timeFridayWakeUp + (fourDay - mlong);
        t7_WU = timeSaturdayWakeUp + (fiveDay - mlong);
        cn_WU = timeSundayWakeUp + (sixDay - mlong);
        if (timeSystemWakeUp < (timeMondayWakeUp - mlong)) {
            t2_WU = timeMondayWakeUp - mlong;
        } else {
            t2_WU = timeMondayWakeUp - mlong + oneWeek;
        }
    }

    private void timeWakeUpSunday() {

        if (mTruoc30p == true) {
            setTimeWakeupCN(truoc30p);
        } else if (mTruoc15p == true) {
            setTimeWakeupCN(truoc15p);
        } else if (mTruoc5p == true) {
            setTimeWakeupCN(truoc5p);
        } else if (mTruoc1h == true) {
            setTimeWakeupCN(truoc1hour);
        } else if (mSleepNow == true) {
            setTimeWakeupCN(0);
        } else {
        }


    }

    private void setTimeWakeupCN(long mlong) {
        t2_WU = timeMondayWakeUp + (oneDay - mlong);
        t3_WU = timeTuesdayWakeUp + (twoDay - mlong);
        t4_WU = timeWednesdayWakeUp + (threeDay - mlong);
        t5_WU = timeThursdayWakeUp + (fourDay - mlong);
        t6_WU = timeFridayWakeUp + (fiveDay - mlong);
        t7_WU = timeSaturdayWakeUp + (sixDay - mlong);
        if (timeSystemWakeUp < (timeSundayWakeUp - mlong)) {
            cn_WU = timeSundayWakeUp - mlong;
        } else {
            cn_WU = timeSundayWakeUp - mlong + oneWeek;
        }
    }

    private void setCalendarMondayWakeUp() {
        mCaMondayWakeUp.set(Calendar.HOUR_OF_DAY, hourWakeUp);
        mCaMondayWakeUp.set(Calendar.MINUTE, minuteWakeUp);
        mCaMondayWakeUp.set(Calendar.SECOND, 0);
        mCaMondayWakeUp.set(Calendar.MILLISECOND, 0);
        timeMondayWakeUp = mCaMondayWakeUp.getTimeInMillis();
        timeSystemWakeUp = System.currentTimeMillis();

    }

    private void setCalendarTuesdayWakeUp() {
        mCaTuesdayWakeUp.set(Calendar.HOUR_OF_DAY, hourWakeUp);
        mCaTuesdayWakeUp.set(Calendar.MINUTE, minuteWakeUp);
        mCaTuesdayWakeUp.set(Calendar.SECOND, 0);
        mCaTuesdayWakeUp.set(Calendar.MILLISECOND, 0);
        timeTuesdayWakeUp = mCaTuesdayWakeUp.getTimeInMillis();
        timeSystemWakeUp = System.currentTimeMillis();

    }

    private void setmCalendarWednesdayWakeUp() {
        mCaWednesdayWakeUp.set(Calendar.HOUR_OF_DAY, hourWakeUp);
        mCaWednesdayWakeUp.set(Calendar.MINUTE, minuteWakeUp);
        mCaWednesdayWakeUp.set(Calendar.SECOND, 0);
        mCaWednesdayWakeUp.set(Calendar.MILLISECOND, 0);
        timeWednesdayWakeUp = mCaWednesdayWakeUp.getTimeInMillis();
        timeSystemWakeUp = System.currentTimeMillis();

    }

    private void setmCalendarThursdayWakeUp() {
        mCaThursdayWakeUp.set(Calendar.HOUR_OF_DAY, hourWakeUp);
        mCaThursdayWakeUp.set(Calendar.MINUTE, minuteWakeUp);
        mCaThursdayWakeUp.set(Calendar.SECOND, 0);
        mCaThursdayWakeUp.set(Calendar.MILLISECOND, 0);
        timeThursdayWakeUp = mCaThursdayWakeUp.getTimeInMillis();
        timeSystemWakeUp = System.currentTimeMillis();

    }

    private void setmCalendarFridayWakeUp() {
        mCaFridayWakeUp.set(Calendar.HOUR_OF_DAY, hourWakeUp);
        mCaFridayWakeUp.set(Calendar.MINUTE, minuteWakeUp);
        mCaFridayWakeUp.set(Calendar.SECOND, 0);
        mCaFridayWakeUp.set(Calendar.MILLISECOND, 0);
        timeFridayWakeUp = mCaFridayWakeUp.getTimeInMillis();
        timeSystemWakeUp = System.currentTimeMillis();

    }

    private void setmCalendarSaturdayWakeUp() {
        mCaSaturdayWakeUp.set(Calendar.HOUR_OF_DAY, hourWakeUp);
        mCaSaturdayWakeUp.set(Calendar.MINUTE, minuteWakeUp);
        mCaSaturdayWakeUp.set(Calendar.SECOND, 0);
        mCaSaturdayWakeUp.set(Calendar.MILLISECOND, 0);
        timeSaturdayWakeUp = mCaSaturdayWakeUp.getTimeInMillis();
        timeSystemWakeUp = System.currentTimeMillis();

    }

    private void setmCalendarSundayWakeUp() {
        mCaSundayWakeUp.set(Calendar.HOUR_OF_DAY, hourWakeUp);
        mCaSundayWakeUp.set(Calendar.MINUTE, minuteWakeUp);
        mCaSundayWakeUp.set(Calendar.SECOND, 0);
        mCaSundayWakeUp.set(Calendar.MILLISECOND, 0);
        timeSundayWakeUp = mCaSundayWakeUp.getTimeInMillis();
        timeSystemWakeUp = System.currentTimeMillis();

    }

    private void cancleAlarmManagerWakeUp() {
        cancleAlarm(mALMondayWakeUp, mPdMondayWakeUp);
        cancleAlarm(mALTuesdayWakeUp, mPdTuesdayWakeUp);
        cancleAlarm(mALWednesdayWakeUp, mPdWednesdayWakeUp);
        cancleAlarm(mALThursdayWakeUp, mPdThursdayWakeUp);
        cancleAlarm(mALFridayWakeUp, mPdFridaydayWakeUp);
        cancleAlarm(mALSaturdayWakeUp, mPdSaturdayWakeUp);
        cancleAlarm(mALSundayWakeUp, mPdSundayWakeUp);
    }

    private void soSanh() {
        switch (date) {
            case 1:
                ganTimeSoSanhCN();
                Log.e(TAG, "cn");
                break;
            case 2:
                ganTimeSoSanhT2();

                Log.e(TAG, "T2");
                break;
            case 3:
                ganTimeSoSanhT3();

                Log.e(TAG, "T3");
                break;
            case 4:
                ganTimeSoSanhT4();

                Log.e(TAG, "T4");
                break;
            case 5:
                ganTimeSoSanhT5();
                Log.e(TAG, "T5");
                break;
            case 6:
                ganTimeSoSanhT6();

                Log.e(TAG, "T6");
                break;
            case 7:
                Log.e(TAG, "T7");
                ganTimeSoSanhT7();
                break;
            default:
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                break;


        }

    }

    private void ganTimeSoSanhT7() {
        if (mTruoc30p == true) {
            setTimeT7(truoc30p);
        } else {
            if (mTruoc15p == true) {
                setTimeT7(truoc15p);
            } else {
                if (mTruoc5p == true) {
                    setTimeT7(truoc5p);
                } else {
                    if (mTruoc1h == true) {
                        setTimeT7(truoc1hour);
                    } else {
                        if (mSleepNow == true) {
                            setTimeT7(0);
                        } else {
                        }
                    }
                }
            }
        }
    }

    private void setTimeT7(long mlong) {
        t2 = timesleepMonday + (twoDay - mlong);
        t3 = timesleepTuesday + (threeDay - mlong);
        t4 = timesleepWednesday + (fourDay - mlong);
        t5 = timesleepThursday + (fiveDay - mlong);
        t6 = timesleepFriday + (sixDay - mlong);
        if (timeSystem < (timesleepSaturday - mlong)) {
            t7 = timesleepSaturday - mlong;
        } else {
            t7 = timesleepSaturday - mlong + oneWeek;
        }
        cn = timesleepSunday + (oneDay - mlong);
    }

    private void ganTimeSoSanhT6() {
        if (mTruoc30p == true) {
            setTimeT6(truoc30p);
        } else {
            if (mTruoc15p == true) {
                setTimeT6(truoc15p);
            } else {
                if (mTruoc5p == true) {
                    setTimeT6(truoc5p);
                } else {
                    if (mTruoc1h == true) {
                        setTimeT6(truoc1hour);
                    } else {
                        if (mSleepNow == true) {
                            setTimeT6(0);
                        } else {
                        }
                    }
                }
            }
        }
    }

    private void setTimeT6(long mlong) {
        t2 = timesleepMonday + (threeDay - mlong);
        t3 = timesleepTuesday + (fourDay - mlong);
        t4 = timesleepWednesday + (fiveDay - mlong);
        t5 = timesleepThursday + (sixDay - mlong);
        if (timeSystem < (timesleepFriday - mlong)) {
            t6 = timesleepFriday - mlong;
        } else {
            t6 = timesleepFriday - mlong + oneWeek;
        }
        t7 = timesleepSaturday + (oneDay - mlong);
        cn = timesleepSunday + (twoDay - mlong);
    }

    private void ganTimeSoSanhT5() {
        if (mTruoc30p == true) {
            // setTime30pT5();
            setTimeT5(truoc30p);
        } else {
            if (mTruoc15p == true) {
                // setTime15pT5();
                setTimeT5(truoc15p);
            } else {
                if (mTruoc5p == true) {
                    setTimeT5(truoc5p);
                } else {
                    if (mTruoc1h == true) {
                        setTimeT5(truoc1hour);
                    } else {
                        if (mSleepNow == true) {
                            setTimeT5(0);
                        } else {
                        }
                    }
                }
            }
        }
    }

    private void setTimeT5(long mlong) {
        t2 = timesleepMonday + (fourDay - mlong);
        t3 = timesleepTuesday + (fiveDay - mlong);
        t4 = timesleepWednesday + (sixDay - mlong);
        if (timeSystem < (timesleepThursday - mlong)) {
            t5 = timesleepThursday - mlong;
        } else {
            t5 = timesleepThursday - mlong + oneWeek;
        }
        t6 = timesleepFriday + (oneDay - mlong);
        t7 = timesleepSaturday + (twoDay - mlong);
        cn = timesleepSunday + (threeDay - mlong);
    }

    private void ganTimeSoSanhT4() {
        if (mTruoc30p == true) {
            //  setTime30pT4();
            setTimeT4(truoc30p);
        } else {
            if (mTruoc15p == true) {
                // setTime15pT4();
                setTimeT4(truoc15p);
            } else {
                if (mTruoc5p == true) {
                    //  setTime5pT4();
                    setTimeT4(truoc5p);
                } else {
                    if (mTruoc1h == true) {
                        //  setTime1hourT4();
                        setTimeT4(truoc1hour);
                    } else {
                        if (mSleepNow == true) {
                            //  setTimeNowT4();
                            setTimeT4(0);
                        } else {
                        }
                    }
                }
            }
        }
    }

    private void setTimeT4(long mlong) {
        t2 = timesleepMonday + (fiveDay - mlong);
        t3 = timesleepTuesday + (sixDay - mlong);
        if (timeSystem < (timesleepWednesday - mlong)) {
            t4 = timesleepWednesday - mlong;
        } else {
            t4 = timesleepWednesday - mlong + oneWeek;
        }
        t5 = timesleepThursday + (oneDay - mlong);
        t6 = timesleepFriday + (twoDay - mlong);
        t7 = timesleepSaturday + (threeDay - mlong);
        cn = timesleepSunday + (fourDay - mlong);
    }

    private void ganTimeSoSanhT3() {
        if (mTruoc30p == true) {
            //  setTime30pT3();
            setTimeT3(truoc30p);
        } else {
            if (mTruoc15p == true) {
                //    setTime15pT3();
                setTimeT3(truoc15p);
            } else {
                if (mTruoc5p == true) {
                    //    setTime5pT3();
                    setTimeT3(truoc5p);
                } else {
                    if (mTruoc1h == true) {
                        //   setTime1hourT3();
                        setTimeT3(truoc1hour);
                    } else {
                        if (mSleepNow == true) {
                            // setTimeNowT3();
                            setTimeT3(0);
                        } else {
                        }
                    }
                }
            }
        }
    }

    private void setTimeT3(long mlong) {
        t2 = timesleepMonday + (sixDay - mlong);
        if (timeSystem < (timesleepTuesday - mlong)) {
            t3 = timesleepTuesday - mlong;
        } else {
            t3 = timesleepTuesday - mlong + oneWeek;
        }
        t4 = timesleepWednesday + (oneDay - mlong);
        t5 = timesleepThursday + (twoDay - mlong);
        t6 = timesleepFriday + (threeDay - mlong);
        t7 = timesleepSaturday + (fourDay - mlong);
        cn = timesleepSunday + (fiveDay - mlong);
    }

    private void ganTimeSoSanhT2() {
        if (mTruoc30p == true) {
            // setTime30pT2();
            setTimeT2(truoc30p);
        } else {
            if (mTruoc15p == true) {
                // setTime15pT2();
                setTimeT2(truoc15p);
            } else {
                if (mTruoc5p == true) {
                    // setTime5pT2();
                    setTimeT2(truoc5p);
                } else {
                    if (mTruoc1h == true) {
                        // setTime1hourT2();
                        setTimeT2(truoc1hour);
                    } else {
                        if (mSleepNow == true) {
                            // setTimeNowT2();
                            setTimeT2(0);
                        } else {
                        }
                    }
                }
            }
        }
    }

    private void setTimeT2(long mlong) {
        if (timeSystem < (timesleepMonday - mlong)) {
            t2 = timesleepMonday - mlong;
        } else {
            t2 = timesleepMonday - mlong + oneWeek;
        }
        t3 = timesleepTuesday + (oneDay - mlong);
        t4 = timesleepWednesday + (twoDay - mlong);
        t5 = timesleepThursday + (threeDay - mlong);
        t6 = timesleepFriday + (fourDay - mlong);
        t7 = timesleepSaturday + (fiveDay - mlong);
        cn = timesleepSunday + (sixDay - mlong);
    }


    private void cancleAlarmManagerSleep() {

        cancleAlarm(mAlarmManagerMondaySleep, mPendingIntentMondaySleep);
        cancleAlarm(mAlarmManagerTuesdaySleep, mPendingIntentTuesdaySleep);
        cancleAlarm(mAlarmManagerWednesdaySleep, mPendingIntentWednesdaySleep);
        cancleAlarm(mAlarmManagerThursdaySleep, mPendingIntentThursdaySleep);
        cancleAlarm(mAlarmManagerFridaySleep, mPendingIntentFridaySleep);
        cancleAlarm(mAlarmManagerSaturdaySleep, mPendingIntentSaturdaySleep);
        cancleAlarm(mAlarmManagerSundaySleep, mPendingIntentSundaySleep);


    }

    private void readSharedPreDay() {
        mBooleanMonday = SharedPreferencesManager.getMonday(this);
        mBooleanTuesday = SharedPreferencesManager.getTuesday(this);
        mBooleanWednesday = SharedPreferencesManager.getWednesday(this);
        mBooleanThursday = SharedPreferencesManager.getThursday(this);
        mBooleanFriday = SharedPreferencesManager.getFriday(this);
        mBooleanSaturday = SharedPreferencesManager.getSaturday(this);
        mBooleanSunday = SharedPreferencesManager.getSunday(this);

        mTruoc1h = SharedPreferencesManager.get1hour(MainActivity.this);
        mTruoc15p = SharedPreferencesManager.get15p(MainActivity.this);
        mTruoc30p = SharedPreferencesManager.get30p(MainActivity.this);
        mTruoc5p = SharedPreferencesManager.get5p(MainActivity.this);
        mSleepNow = SharedPreferencesManager.getSleepNow(MainActivity.this);

        hourWakeUp = SharedPreferencesManager.getHourWakeUp(MainActivity.this);
        minuteWakeUp = SharedPreferencesManager.getMinuteWakeUp(MainActivity.this);
        hourSleep = SharedPreferencesManager.getHourSleep(MainActivity.this);
        minuteSleep = SharedPreferencesManager.getMinuteSleep(MainActivity.this);

        Log.e(TAG, "hourWakeUp: "+hourWakeUp);
        Log.e(TAG, "minuteWakeUp: "+minuteWakeUp);
        Log.e(TAG, "hourSleep: "+hourSleep);
        Log.e(TAG, "minuteSleep: "+minuteSleep);


    }

    private void createCalenda(Intent intent, PendingIntent pendingIntent, AlarmManager alarmManager, long time, int id) {
        intent.putExtra("TimeSleep", true);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent);

        //1*24*60*60*1000
    }
    private void createAlarm(Intent intent, PendingIntent pendingIntent, AlarmManager alarmManager, long time, int id) {
        intent.putExtra("TimeWakeUp", true);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent);

        //1*24*60*60*1000
    }
    private void setCalendarMondaySleep() {
        //  mCalendarMondaySleep.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        mCalendarMondaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarMondaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarMondaySleep.set(Calendar.SECOND, 0);
        mCalendarMondaySleep.set(Calendar.MILLISECOND, 0);
        timesleepMonday = mCalendarMondaySleep.getTimeInMillis();
        timeSystem = System.currentTimeMillis();

    }

    private void setCalendarTuesdaySleep() {
        // mCalendarTuesdaySleep.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        mCalendarTuesdaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarTuesdaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarTuesdaySleep.set(Calendar.SECOND, 0);
        mCalendarTuesdaySleep.set(Calendar.MILLISECOND, 0);
        timesleepTuesday = mCalendarTuesdaySleep.getTimeInMillis();
        timeSystem = System.currentTimeMillis();
    }

    private void setCalendarWednesdaySleep() {
        //   mCalendarWednesdaySleep.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        mCalendarWednesdaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarWednesdaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarWednesdaySleep.set(Calendar.SECOND, 0);
        mCalendarWednesdaySleep.set(Calendar.MILLISECOND, 0);
        timesleepWednesday = mCalendarWednesdaySleep.getTimeInMillis();
        timeSystem = System.currentTimeMillis();

    }

    private void setCalendarThursdaySleep() {
        // mCalendarThursdaySleep.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        mCalendarThursdaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarThursdaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarThursdaySleep.set(Calendar.SECOND, 0);
        mCalendarThursdaySleep.set(Calendar.MILLISECOND, 0);
        timesleepThursday = mCalendarThursdaySleep.getTimeInMillis();
        timeSystem = System.currentTimeMillis();
    }

    private void setCalendarFridaySleep() {
        // mCalendarFridaySleep.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        mCalendarFridaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarFridaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarFridaySleep.set(Calendar.SECOND, 0);
        mCalendarFridaySleep.set(Calendar.MILLISECOND, 0);
        timesleepFriday = mCalendarFridaySleep.getTimeInMillis();
        timeSystem = System.currentTimeMillis();
    }

    private void setCalendarSaturdaySleep() {
        //   mCalendarSaturdaySleep.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        mCalendarSaturdaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarSaturdaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarSaturdaySleep.set(Calendar.SECOND, 0);
        mCalendarSaturdaySleep.set(Calendar.MILLISECOND, 0);
        timesleepSaturday = mCalendarSaturdaySleep.getTimeInMillis();
        timeSystem = System.currentTimeMillis();
    }

    private void setCalendarSundaySleep() {
        //     mCalendarSundaySleep.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        mCalendarSundaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarSundaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarSundaySleep.set(Calendar.SECOND, 0);
        mCalendarSundaySleep.set(Calendar.MILLISECOND, 0);
        timesleepSunday = mCalendarSundaySleep.getTimeInMillis();
        timeSystem = System.currentTimeMillis();
    }

    private void onClickImvSetting() {
        imv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
//                finish();
            }
        });
    }

    private void initView() {
        imv_setting = (ImageView) findViewById(R.id.imageViewSetting);
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Nhấn Lần Nữa Để Thoát", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        readSharedPreDay();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        try {
            MainActivity.this.unregisterReceiver(receiver);
        } catch (Exception e) {

        }
        super.onDestroy();
    }

    private void ganTimeSoSanhCN() {
        if (mTruoc30p == true) {
//            setTime30p();
            setTimeCN(truoc30p);
        } else {
            if (mTruoc15p == true) {
//                setTime15p();
                setTimeCN(truoc15p);
            } else {
                if (mTruoc5p == true) {
//                    setTime5p();
                    setTimeCN(truoc5p);
                } else {
                    if (mTruoc1h == true) {
//                        setTime1hour();
                        setTimeCN(truoc1hour);
                    } else {
                        if (mSleepNow == true) {
//                            setTimeNow();
                            setTimeCN(0);
                        } else {
                        }
                    }
                }
            }
        }
    }

    private void setTimeCN(long mlong) {
        t2 = timesleepMonday + (oneDay - mlong);
        t3 = timesleepTuesday + (twoDay - mlong);
        t4 = timesleepWednesday + (threeDay - mlong);
        t5 = timesleepThursday + (fourDay - mlong);
        t6 = timesleepFriday + (fiveDay - mlong);
        t7 = timesleepSaturday + (sixDay - mlong);
        if (timeSystem < (timesleepSunday - mlong)) {
            cn = timesleepSunday - mlong;
        } else {
            cn = timesleepSunday - mlong + oneWeek;
        }
    }

    private void cancleAlarm(AlarmManager alarmManager, PendingIntent pendingIntent) {
        try {
            alarmManager.cancel(pendingIntent);
        } catch (Exception e) {
        }
    }

}

