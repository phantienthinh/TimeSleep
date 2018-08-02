package com.example.tienthinh.timesleep;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
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
import com.example.tienthinh.timesleep.fragment.FragmentOne;
import com.example.tienthinh.timesleep.fragment.FragmentThree;
import com.example.tienthinh.timesleep.fragment.FragmentTwo;
import com.example.tienthinh.timesleep.model.SharedPreferencesManager;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String KEYSLEEP = "KEY";
//    private static final String KEYTIME = "KEYTIME";
    boolean mTruoc5p, mTruoc15p, mTruoc30p, mTruoc1h, mSleepNow;
    private long oneWeek = 604800000;
    private long timeSystem;
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
    private Calendar mCalendarMondaySleep, mCalendarTuesdaySleep, mCalendarWednesdaySleep, mCalendarThursdaySleep, mCalendarFridaySleep;
    private Calendar mCalendarSaturdaySleep;
    private Calendar mCalendarSundaySleep;
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
        Calendar calendar = Calendar.getInstance();
        date = calendar.get(Calendar.DAY_OF_WEEK);


    }

    private void createWakeLock() {

        PowerManager mgr = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyWakeLock");
        wakeLock.acquire();
//        wakeLock.release();
    }

    private void createCalendar() {
        mCalendarMondaySleep = Calendar.getInstance();
        mCalendarTuesdaySleep = Calendar.getInstance();
        mCalendarWednesdaySleep = Calendar.getInstance();
        mCalendarThursdaySleep = Calendar.getInstance();
        mCalendarFridaySleep = Calendar.getInstance();
        mCalendarSaturdaySleep = Calendar.getInstance();
        mCalendarSundaySleep = Calendar.getInstance();
    }

    private void createAlarmService() {
        mAlarmManagerMondaySleep = (AlarmManager) getSystemService(ALARM_SERVICE);
        mAlarmManagerTuesdaySleep = (AlarmManager) getSystemService(ALARM_SERVICE);
        mAlarmManagerWednesdaySleep = (AlarmManager) getSystemService(ALARM_SERVICE);
        mAlarmManagerThursdaySleep = (AlarmManager) getSystemService(ALARM_SERVICE);
        mAlarmManagerFridaySleep = (AlarmManager) getSystemService(ALARM_SERVICE);
        mAlarmManagerSaturdaySleep = (AlarmManager) getSystemService(ALARM_SERVICE);
        mAlarmManagerSundaySleep = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    private void khoiTaoBroadCast() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case "TimeSleepActivity":
                        Log.e(TAG, "đã vào TimeSleepActivity ");
                        cancleAlarmManager();
                        readSharedPreDay();

                        //thứ2
                        if (mBooleanMonday == true) {
                            setCalendarMonday();
                            soSanh();
                            cancleAlarm(mAlarmManagerMondaySleep, mPendingIntentMondaySleep);
                            intent = new Intent(MainActivity.this, AlarmRecever.class);
                            intent.putExtra(KEYSLEEP, 2);
                            intent.putExtra("KEYTIME_T2", t2);
                            createCalenda(intent, mPendingIntentMondaySleep, mAlarmManagerMondaySleep, t2, 2);
                        } else {
                            cancleAlarm(mAlarmManagerMondaySleep, mPendingIntentMondaySleep);
                        }
                        //thứ 3
                        if (mBooleanTuesday == true) {
                            setCalendarTuesday();
                            soSanh();
                            cancleAlarm(mAlarmManagerTuesdaySleep, mPendingIntentTuesdaySleep);
                            intent = new Intent(MainActivity.this, AlarmRecever.class);
                            intent.putExtra(KEYSLEEP, 3);
                            intent.putExtra("KEYTIME_T3", t3);
                            createCalenda(intent, mPendingIntentTuesdaySleep, mAlarmManagerTuesdaySleep, t3, 3);
                        } else {
                            cancleAlarm(mAlarmManagerTuesdaySleep, mPendingIntentTuesdaySleep);
                        }

                        //thứ 4
                        if (mBooleanWednesday == true) {
                            setCalendarWednesday();
                            soSanh();
                            cancleAlarm(mAlarmManagerWednesdaySleep, mPendingIntentWednesdaySleep);
                            intent = new Intent(MainActivity.this, AlarmRecever.class);
                            intent.putExtra(KEYSLEEP, 4);
                            intent.putExtra("KEYTIME_T4", t4);
                            createCalenda(intent, mPendingIntentWednesdaySleep, mAlarmManagerWednesdaySleep, t4, 4);

                        } else {
                            cancleAlarm(mAlarmManagerWednesdaySleep, mPendingIntentWednesdaySleep);
                        }

                        //thứ 5
                        if (mBooleanThursday == true) {
                            setCalendarThursday();
                            soSanh();

                            cancleAlarm(mAlarmManagerThursdaySleep, mPendingIntentThursdaySleep);
                            intent = new Intent(MainActivity.this, AlarmRecever.class);
                            intent.putExtra(KEYSLEEP, 5);
                            intent.putExtra("KEYTIME_T5", t5);
                            createCalenda(intent, mPendingIntentThursdaySleep, mAlarmManagerThursdaySleep, t5, 5);

                        } else {
                            cancleAlarm(mAlarmManagerThursdaySleep, mPendingIntentThursdaySleep);
                        }

                        //thứ 6
                        if (mBooleanFriday == true) {
                            setCalendarFriday();
                            soSanh();
                            cancleAlarm(mAlarmManagerFridaySleep, mPendingIntentFridaySleep);
                            intent = new Intent(MainActivity.this, AlarmRecever.class);
                            intent.putExtra(KEYSLEEP, 6);
                            intent.putExtra("KEYTIME_T6", t6);
                            createCalenda(intent, mPendingIntentFridaySleep, mAlarmManagerFridaySleep, t6, 6);
                        } else {
                            cancleAlarm(mAlarmManagerFridaySleep, mPendingIntentFridaySleep);
                        }

//                        thứ 7
                        if (mBooleanSaturday == true) {
                            setCalendarSaturday();
                            soSanh();
                            cancleAlarm(mAlarmManagerSaturdaySleep, mPendingIntentSaturdaySleep);
                            intent = new Intent(MainActivity.this, AlarmRecever.class);
                            intent.putExtra(KEYSLEEP, 7);
                            intent.putExtra("KEYTIME_T7", t7);
                            createCalenda(intent, mPendingIntentSaturdaySleep, mAlarmManagerSaturdaySleep, t7, 7);

                        } else {
                            cancleAlarm(mAlarmManagerSaturdaySleep, mPendingIntentSaturdaySleep);
                        }


                        //chủ nhật
                        if (mBooleanSunday == true) {
                            setCalendarSunday();
                            soSanh();
                            cancleAlarm(mAlarmManagerSundaySleep, mPendingIntentSundaySleep);
                            intent = new Intent(MainActivity.this, AlarmRecever.class);
                            intent.putExtra(KEYSLEEP, 8);
                            intent.putExtra("KEYTIME_CN", cn);
                            createCalenda(intent, mPendingIntentSundaySleep, mAlarmManagerSundaySleep, cn, 8);

                        } else {
                            cancleAlarm(mAlarmManagerSundaySleep, mPendingIntentSundaySleep);
                        }
                        break;

                    case "TimeWakeUpActivity":
                        break;
                }
            }
        };
        filter = new IntentFilter();
        filter.addAction("TimeSleepActivity");
        filter.addAction("TimeWakeUpActivity");
        MainActivity.this.registerReceiver(receiver, filter);
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
        t6 = timesleepFriday - (sixDay - mlong);
        if (timeSystem<(timesleepSaturday-mlong)){
            t7 = timesleepSaturday - mlong;
        }else {
            t7 = timesleepSaturday - mlong+oneWeek;
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
        if (timeSystem<(timesleepFriday-mlong)){
            t6 = timesleepFriday - mlong;
        }else {
            t6 = timesleepFriday - mlong+oneWeek;
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
        if (timeSystem<(timesleepThursday-mlong)){
            t5 = timesleepThursday - mlong;
        }else {
            t5 = timesleepThursday - mlong +oneWeek;
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
        if (timeSystem<(timesleepWednesday-mlong)){
            t4 = timesleepWednesday - mlong;
        }else {
            t4 = timesleepWednesday -mlong +oneWeek;
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
        if (timeSystem<(timesleepTuesday-mlong)){
            t3 = timesleepTuesday - mlong;
        }else {
            t3 = timesleepTuesday - mlong +oneWeek;
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
        if (timeSystem < (timesleepMonday - mlong)){
            t2 = timesleepMonday-mlong;
        }else {
            t2 = timesleepMonday - mlong+oneWeek;
        }
        t3 = timesleepTuesday + (oneDay - mlong);
        t4 = timesleepWednesday + (twoDay - mlong);
        t5 = timesleepThursday + (threeDay - mlong);
        t6 = timesleepFriday + (fourDay - mlong);
        t7 = timesleepSaturday + (fiveDay - mlong);
        cn = timesleepSunday + (sixDay - mlong);
    }


    private void cancleAlarmManager() {
        try {
            mAlarmManagerMondaySleep.cancel(mPendingIntentMondaySleep);
            mAlarmManagerTuesdaySleep.cancel(mPendingIntentTuesdaySleep);
            mAlarmManagerWednesdaySleep.cancel(mPendingIntentWednesdaySleep);
            mAlarmManagerThursdaySleep.cancel(mPendingIntentThursdaySleep);
            mAlarmManagerFridaySleep.cancel(mPendingIntentFridaySleep);
            mAlarmManagerSaturdaySleep.cancel(mPendingIntentSaturdaySleep);
            mAlarmManagerSundaySleep.cancel(mPendingIntentSundaySleep);
        } catch (Exception e) {
        }


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

        MainActivity.hourWakeUp = SharedPreferencesManager.getHourWakeUp(MainActivity.this);
        MainActivity.minuteWakeUp = SharedPreferencesManager.getMinuteWakeUp(MainActivity.this);
        MainActivity.hourSleep = SharedPreferencesManager.getHourSleep(MainActivity.this);
        MainActivity.minuteSleep = SharedPreferencesManager.getMinuteSleep(MainActivity.this);


    }

    private void createCalenda(Intent intent, PendingIntent pendingIntent, AlarmManager alarmManager, long time, int id) {
        intent.putExtra("TimeSleep", true);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent);

        //1*24*60*60*1000
    }

    private void setCalendarMonday() {
        //  mCalendarMondaySleep.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        mCalendarMondaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarMondaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarMondaySleep.set(Calendar.SECOND, 0);
        mCalendarMondaySleep.set(Calendar.MILLISECOND, 0);
        timesleepMonday = mCalendarMondaySleep.getTimeInMillis();
        timeSystem = System.currentTimeMillis();

    }

    private void setCalendarTuesday() {
        // mCalendarTuesdaySleep.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        mCalendarTuesdaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarTuesdaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarTuesdaySleep.set(Calendar.SECOND, 0);
        mCalendarTuesdaySleep.set(Calendar.MILLISECOND, 0);
        timesleepTuesday = mCalendarTuesdaySleep.getTimeInMillis();
        timeSystem = System.currentTimeMillis();
    }

    private void setCalendarWednesday() {
        //   mCalendarWednesdaySleep.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        mCalendarWednesdaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarWednesdaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarWednesdaySleep.set(Calendar.SECOND, 0);
        mCalendarWednesdaySleep.set(Calendar.MILLISECOND, 0);
        timesleepWednesday = mCalendarWednesdaySleep.getTimeInMillis();
        timeSystem = System.currentTimeMillis();

    }

    private void setCalendarThursday() {
        // mCalendarThursdaySleep.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        mCalendarThursdaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarThursdaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarThursdaySleep.set(Calendar.SECOND, 0);
        mCalendarThursdaySleep.set(Calendar.MILLISECOND, 0);
        timesleepThursday = mCalendarThursdaySleep.getTimeInMillis();
        timeSystem = System.currentTimeMillis();
    }

    private void setCalendarFriday() {
        // mCalendarFridaySleep.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        mCalendarFridaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarFridaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarFridaySleep.set(Calendar.SECOND, 0);
        mCalendarFridaySleep.set(Calendar.MILLISECOND, 0);
        timesleepFriday = mCalendarFridaySleep.getTimeInMillis();
        timeSystem = System.currentTimeMillis();
    }

    private void setCalendarSaturday() {
        //   mCalendarSaturdaySleep.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        mCalendarSaturdaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarSaturdaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarSaturdaySleep.set(Calendar.SECOND, 0);
        mCalendarSaturdaySleep.set(Calendar.MILLISECOND, 0);
        timesleepSaturday = mCalendarSaturdaySleep.getTimeInMillis();
        timeSystem = System.currentTimeMillis();
    }

    private void setCalendarSunday() {
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
                finish();
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

