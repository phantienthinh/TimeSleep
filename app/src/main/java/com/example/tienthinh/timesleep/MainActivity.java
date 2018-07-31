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
    private Context context;
    private long oneWeek = 604800000;
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
    long timesleepMonday;
    long timesleepTuesday;
    long timesleepWednesday;
    long timesleepThursday;
    long timesleepFriday;
    long timesleepSaturday;
    long timesleepSunday;
    long t2;
    long t3;
    long t4;
    long t5;
    long t6;
    long t7;
    long cn;
    private Calendar mCalendarMondaySleep;
    private Calendar mCalendarTuesdaySleep;
    private Calendar mCalendarWednesdaySleep;
    private Calendar mCalendarThursdaySleep;
    private Calendar mCalendarFridaySleep;
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

        PowerManager mgr = (PowerManager)getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyWakeLock");
        wakeLock.acquire();
        wakeLock.release();
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
                        if (mBooleanMonday == true) {
                            setCalendarMonday();
                            //  soSanhT2();
                            soSanh();
                            createCalenda(intent, mPendingIntentMondaySleep, mAlarmManagerMondaySleep, t2, 0);

                        } else {
                            try {
                                mAlarmManagerMondaySleep.cancel(mPendingIntentMondaySleep);
                            }catch (Exception e){}
                        }
                        if (mBooleanTuesday == true) {
                            setCalendarTuesday();
//                            soSanhT3();
                            soSanh();
                            createCalenda(intent, mPendingIntentTuesdaySleep, mAlarmManagerTuesdaySleep, t3, 1);
                        } else {
                            try {
                                mAlarmManagerTuesdaySleep.cancel(mPendingIntentTuesdaySleep);
                            }catch (Exception e){}
                        }
                        if (mBooleanWednesday == true) {
                            setCalendarWednesday();
//                            soSanhT4();
                            soSanh();
                            createCalenda(intent, mPendingIntentWednesdaySleep, mAlarmManagerWednesdaySleep, t4, 2);
                        } else {
                            try {
                                mAlarmManagerWednesdaySleep.cancel(mPendingIntentWednesdaySleep);
                            }catch (Exception e){}
                        }
                        if (mBooleanThursday == true) {
                            setCalendarThursday();
//                            soSanhT5();
                            soSanh();
                            createCalenda(intent, mPendingIntentThursdaySleep, mAlarmManagerThursdaySleep, t5, 3);
                        } else {
                            try {
                                mAlarmManagerThursdaySleep.cancel(mPendingIntentThursdaySleep);
                            }catch (Exception e){}
                        }

                        if (mBooleanFriday == true) {
                            setCalendarFriday();
//                            soSanhT6();
                            soSanh();
                            createCalenda(intent, mPendingIntentFridaySleep, mAlarmManagerFridaySleep, t6, 4);
                        } else {
                            try {
                                mAlarmManagerFridaySleep.cancel(mPendingIntentFridaySleep);
                            }catch (Exception e){}
                        }

                        if (mBooleanSaturday == true) {
                            setCalendarSaturday();
//                            soSanhT7();
                            soSanh();
                            createCalenda(intent, mPendingIntentSaturdaySleep, mAlarmManagerSaturdaySleep, t7, 5);
                        } else {
                            try {
                                mAlarmManagerSaturdaySleep.cancel(mPendingIntentSaturdaySleep);
                            }catch (Exception e){}
                        }

                        if (mBooleanSunday == true) {
                            setCalendarSunday();
//                            soSanhCN();
                            soSanh();
                            createCalenda(intent, mPendingIntentSundaySleep, mAlarmManagerSundaySleep, cn, 6);
                        } else {
                            try {
                                mAlarmManagerSundaySleep.cancel(mPendingIntentSundaySleep);
                            }catch (Exception e){}
                        }
                        Log.e(TAG, "t2:" + t2);
                        Log.e(TAG, "t3:" + t3);
                        Log.e(TAG, "t4:" + t4);
                        Log.e(TAG, "t5:" + t5);
                        Log.e(TAG, "t6:" + t6);
                        Log.e(TAG, "t7:" + t7);
                        Log.e(TAG, "cn:" + cn);
                        Log.e(TAG, "date" + date);
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
                if (SharedPreferencesManager.get30p(MainActivity.this) == true) {
                    setTime30p();
                } else {
                    if (SharedPreferencesManager.get15p(MainActivity.this) == true) {
                        setTime15p();
                    } else {
                        if (SharedPreferencesManager.get5p(MainActivity.this) == true) {
                            setTime5p();
                        } else {
                            if (SharedPreferencesManager.get1hour(MainActivity.this) == true) {
                                setTime1hour();
                            } else {
                                if (SharedPreferencesManager.getSleepNow(MainActivity.this) == true) {
                                    setTimeNow();
                                } else {
                                }
                            }
                        }
                    }
                }
//                t2 = timesleepMonday + oneDay;
//                t3 = timesleepTuesday + twoDay;
//                t4 = timesleepWednesday + threeDay;
//                t5 = timesleepThursday + fourDay;
//                t6 = timesleepFriday + fiveDay;
//                t7 = timesleepFriday + sixDay;
//                cn = timesleepSunday + oneWeek;
                Log.e(TAG, "cn");
                break;
            case 2:
                if (SharedPreferencesManager.get30p(MainActivity.this) == true) {
                    setTime30p();
                } else {
                    if (SharedPreferencesManager.get15p(MainActivity.this) == true) {
                        setTime15p();
                    } else {
                        if (SharedPreferencesManager.get5p(MainActivity.this) == true) {
                            setTime5p();
                        } else {
                            if (SharedPreferencesManager.get1hour(MainActivity.this) == true) {
                                setTime1hour();
                            } else {
                                if (SharedPreferencesManager.getSleepNow(MainActivity.this) == true) {
                                    setTimeNow();
                                } else {
                                }
                            }
                        }
                    }
                }
//                t2 = timesleepMonday + oneWeek;
//                t3 = timesleepTuesday + oneDay;
//                t4 = timesleepWednesday + twoDay;
//                t5 = timesleepThursday + threeDay;
//                t6 = timesleepFriday + fourDay;
//                t7 = timesleepFriday + fiveDay;
//                cn = timesleepSunday + sixDay;
                Log.e(TAG, "T2");
                break;
            case 3:
                if (SharedPreferencesManager.get30p(MainActivity.this) == true) {
                    setTime30p();
                } else {
                    if (SharedPreferencesManager.get15p(MainActivity.this) == true) {
                        setTime15p();
                    } else {
                        if (SharedPreferencesManager.get5p(MainActivity.this) == true) {
                            setTime5p();
                        } else {
                            if (SharedPreferencesManager.get1hour(MainActivity.this) == true) {
                                setTime1hour();
                            } else {
                                if (SharedPreferencesManager.getSleepNow(MainActivity.this) == true) {
                                    setTimeNow();
                                } else {
                                }
                            }
                        }
                    }
                }
//                t2 = timesleepMonday + sixDay;
//                t3 = timesleepTuesday + oneWeek;
//                t4 = timesleepWednesday + oneDay;
//                t5 = timesleepThursday + twoDay;
//                t6 = timesleepFriday + threeDay;
//                t7 = timesleepFriday + fourDay;
//                cn = timesleepSunday + fiveDay;
                Log.e(TAG, "T3");
                break;
            case 4:
                if (SharedPreferencesManager.get30p(MainActivity.this) == true) {
                    setTime30p();
                } else {
                    if (SharedPreferencesManager.get15p(MainActivity.this) == true) {
                        setTime15p();
                    } else {
                        if (SharedPreferencesManager.get5p(MainActivity.this) == true) {
                            setTime5p();
                        } else {
                            if (SharedPreferencesManager.get1hour(MainActivity.this) == true) {
                                setTime1hour();
                            } else {
                                if (SharedPreferencesManager.getSleepNow(MainActivity.this) == true) {
                                    setTimeNow();
                                } else {
                                }
                            }
                        }
                    }
                }
//                t2 = timesleepMonday + sixDay;
//                t3 = timesleepTuesday + oneWeek;
//                t4 = timesleepWednesday + oneDay;
//                t5 = timesleepThursday + twoDay;
//                t6 = timesleepFriday + threeDay;
//                t7 = timesleepFriday + fourDay;
//                cn = timesleepSunday + fiveDay;
                Log.e(TAG, "T4");
                break;
            case 5:
                if (SharedPreferencesManager.get30p(MainActivity.this) == true) {
                    setTime30p();
                } else {
                    if (SharedPreferencesManager.get15p(MainActivity.this) == true) {
                        setTime15p();
                    } else {
                        if (SharedPreferencesManager.get5p(MainActivity.this) == true) {
                            setTime5p();
                        } else {
                            if (SharedPreferencesManager.get1hour(MainActivity.this) == true) {
                                setTime1hour();
                            } else {
                                if (SharedPreferencesManager.getSleepNow(MainActivity.this) == true) {
                                    setTimeNow();
                                } else {
                                }
                            }
                        }
                    }
                }
//                t2 = timesleepMonday + fourDay;
//                t3 = timesleepTuesday + fiveDay;
//                t4 = timesleepWednesday + sixDay;
//                t5 = timesleepThursday + oneWeek;
//                t6 = timesleepFriday + oneDay;
//                t7 = timesleepFriday + twoDay;
//                cn = timesleepSunday + threeDay;
                Log.e(TAG, "T5");
                break;
            case 6:
                if (SharedPreferencesManager.get30p(MainActivity.this) == true) {
                    setTime30p();
                } else {
                    if (SharedPreferencesManager.get15p(MainActivity.this) == true) {
                        setTime15p();
                    } else {
                        if (SharedPreferencesManager.get5p(MainActivity.this) == true) {
                            setTime5p();
                        } else {
                            if (SharedPreferencesManager.get1hour(MainActivity.this) == true) {
                                setTime1hour();
                            } else {
                                if (SharedPreferencesManager.getSleepNow(MainActivity.this) == true) {
                                    setTimeNow();
                                } else {
                                }
                            }
                        }
                    }
                }
//                t2 = timesleepMonday + threeDay;
//                t3 = timesleepTuesday + fourDay;
//                t4 = timesleepWednesday + fiveDay;
//                t5 = timesleepThursday + sixDay;
//                t6 = timesleepFriday + oneWeek;
//                t7 = timesleepFriday + oneDay;
//                cn = timesleepSunday + twoDay;
                Log.e(TAG, "T6");
                break;
            case 7:
                Log.e(TAG, "T7");
//                t2 = timesleepMonday + twoDay;
//                t3 = timesleepTuesday + threeDay;
//                t4 = timesleepWednesday + fourDay;
//                t5 = timesleepThursday + fiveDay;
//                t6 = timesleepFriday + sixDay;
//                t7 = timesleepFriday + oneWeek;
//                cn = timesleepSunday + oneDay;
                if (SharedPreferencesManager.get30p(MainActivity.this) == true) {
                    setTime30p();
                } else {
                    if (SharedPreferencesManager.get15p(MainActivity.this) == true) {
                        setTime15p();
                    } else {
                        if (SharedPreferencesManager.get5p(MainActivity.this) == true) {
                            setTime5p();
                        } else {
                            if (SharedPreferencesManager.get1hour(MainActivity.this) == true) {
                                setTime1hour();
                            } else {
                                if (SharedPreferencesManager.getSleepNow(MainActivity.this) == true) {
                                    setTimeNow();
                                } else {
                                }
                            }
                        }
                    }
                }
                break;
            default:
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                break;


        }

    }

//    private void soSanhT2() {
//        switch (date) {
//            case 1:
//                timesleepMonday = timesleepMonday + 86400;
//                break;
//            case 2:
//                // timesleepMonday = timesleepMonday + 172800;
//                break;
//            case 3:
//                timesleepMonday = timesleepMonday + (6 * 24 * 60 * 60 * 1000);
//                break;
//            case 4:
//                timesleepMonday = timesleepMonday + (5 * 24 * 60 * 60 * 1000);
//                break;
//            case 5:
//                timesleepMonday = timesleepMonday + (4 * 24 * 60 * 60 * 1000);
//                break;
//            case 6:
//                timesleepMonday = timesleepMonday + (3 * 24 * 60 * 60 * 1000);
//                break;
//            case 7:
//                timesleepMonday = timesleepMonday + (2 * 24 * 60 * 60 * 1000);
//                break;
//            default:
//                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
//                break;
//
//
//        }
//
//    }
//
//    private void soSanhT3() {
//        switch (date) {
//            case 1:
//                timesleepTuesday = timesleepTuesday + (5 * 24 * 60 * 60 * 1000);
//                break;
//            case 2:
//                timesleepTuesday = timesleepTuesday + (6 * 24 * 60 * 60 * 1000);
//                break;
//            case 3:
//                timesleepTuesday = mCalendarTuesdaySleep.getTimeInMillis();
//                break;
//            case 4:
//                timesleepTuesday = timesleepTuesday + (1 * 24 * 60 * 60 * 1000);
//                break;
//            case 5:
//                timesleepTuesday = timesleepTuesday + (2 * 24 * 60 * 60 * 1000);
//                break;
//            case 6:
//                timesleepTuesday = timesleepTuesday + (3 * 24 * 60 * 60 * 1000);
//                break;
//            case 7:
//                timesleepTuesday = timesleepTuesday + (4 * 24 * 60 * 60 * 1000);
//                break;
//            default:
//                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
//                break;
//
//
//        }
//
//    }
//
//    private void soSanhT4() {
//        switch (date) {
//            case 1:
//                timesleepWednesday = timesleepWednesday + (4 * 24 * 60 * 60 * 1000);
//                break;
//            case 2:
//                timesleepWednesday = timesleepWednesday + (5 * 24 * 60 * 60 * 1000);
//                break;
//            case 3:
//                timesleepWednesday = timesleepWednesday + (6 * 24 * 60 * 60 * 1000);
//                break;
//            case 4:
//                //timesleepWednesday = timesleepWednesday + (1 * 24 * 60 * 60 * 1000);
//                break;
//            case 5:
//                timesleepWednesday = timesleepWednesday + (1 * 24 * 60 * 60 * 1000);
//                break;
//            case 6:
//                timesleepWednesday = timesleepWednesday + (2 * 24 * 60 * 60 * 1000);
//                break;
//            case 7:
//                timesleepWednesday = timesleepWednesday + (3 * 24 * 60 * 60 * 1000);
//                break;
//            default:
//                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
//                break;
//
//
//        }
//
//    }
//
//    private void soSanhT5() {
//        switch (date) {
//            case 1:
//                timesleepThursday = timesleepThursday + (3 * 24 * 60 * 60 * 1000);
//                break;
//            case 2:
//                timesleepThursday = timesleepThursday + (4 * 24 * 60 * 60 * 1000);
//                break;
//            case 3:
//                timesleepThursday = timesleepThursday + (5 * 24 * 60 * 60 * 1000);
//                break;
//            case 4:
//                timesleepThursday = timesleepThursday + (6 * 24 * 60 * 60 * 1000);
//                break;
//            case 5:
//                //timesleepThursday = timesleepThursday + (1 * 24 * 60 * 60 * 1000);
//                break;
//            case 6:
//                timesleepThursday = timesleepThursday + (1 * 24 * 60 * 60 * 1000);
//                break;
//            case 7:
//                timesleepThursday = timesleepThursday + (2 * 24 * 60 * 60 * 1000);
//                break;
//            default:
//                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
//                break;
//
//
//        }
//
//    }
//
//    private void soSanhT6() {
//        switch (date) {
//            case 1:
//                timesleepFriday = timesleepFriday + (2 * 24 * 60 * 60 * 1000);
//                break;
//            case 2:
//                timesleepFriday = timesleepFriday + (3 * 24 * 60 * 60 * 1000);
//                break;
//            case 3:
//                timesleepFriday = timesleepFriday + (4 * 24 * 60 * 60 * 1000);
//                break;
//            case 4:
//                timesleepFriday = timesleepFriday + (5 * 24 * 60 * 60 * 1000);
//                break;
//            case 5:
//                timesleepFriday = timesleepFriday + (6 * 24 * 60 * 60 * 1000);
//                break;
//            case 6:
//                // timesleepFriday = timesleepFriday + (1 * 24 * 60 * 60 * 1000);
//                break;
//            case 7:
//                timesleepFriday = timesleepFriday + (1 * 24 * 60 * 60 * 1000);
//                break;
//            default:
//                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
//                break;
//
//
//        }
//
//    }
//
//    private void soSanhT7() {
//        switch (date) {
//            case 1:
//                timesleepSaturday = timesleepSaturday + (1 * 24 * 60 * 60 * 1000);
//                break;
//            case 2:
//                timesleepSaturday = timesleepSaturday + (2 * 24 * 60 * 60 * 1000);
//                break;
//            case 3:
//                timesleepSaturday = timesleepSaturday + (3 * 24 * 60 * 60 * 1000);
//                break;
//            case 4:
//                timesleepSaturday = timesleepSaturday + (4 * 24 * 60 * 60 * 1000);
//                break;
//            case 5:
//                timesleepSaturday = timesleepSaturday + (5 * 24 * 60 * 60 * 1000);
//                break;
//            case 6:
//                timesleepSaturday = timesleepSaturday + (6 * 24 * 60 * 60 * 1000);
//                break;
//            case 7:
//                //timesleepSaturday = timesleepSaturday + (1 * 24 * 60 * 60 * 1000);
//                break;
//            default:
//                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
//                break;
//
//
//        }
//
//    }
//
//    private void soSanhCN() {
//        switch (date) {
//            case 1:
//                // timesleepSunday = timesleepSunday + (1 * 24 * 60 * 60 * 1000);
//                break;
//            case 2:
//                timesleepSunday = timesleepSunday + (1 * 24 * 60 * 60 * 1000);
//                break;
//            case 3:
//                timesleepSunday = timesleepSunday + (2 * 24 * 60 * 60 * 1000);
//                break;
//            case 4:
//                timesleepSunday = timesleepSunday + (3 * 24 * 60 * 60 * 1000);
//                break;
//            case 5:
//                timesleepSunday = timesleepSunday + (4 * 24 * 60 * 60 * 1000);
//                break;
//            case 6:
//                timesleepSunday = timesleepSunday + (5 * 24 * 60 * 60 * 1000);
//                break;
//            case 7:
//                timesleepSunday = timesleepSunday + (6 * 24 * 60 * 60 * 1000);
//                break;
//            default:
//                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
//                break;
//
//
//        }
//
//    }

    private void cancleAlarmManager() {
        try {
            mAlarmManagerMondaySleep.cancel(mPendingIntentMondaySleep);
            mAlarmManagerTuesdaySleep.cancel(mPendingIntentTuesdaySleep);
            mAlarmManagerWednesdaySleep.cancel(mPendingIntentWednesdaySleep);
            mAlarmManagerThursdaySleep.cancel(mPendingIntentThursdaySleep);
            mAlarmManagerFridaySleep.cancel(mPendingIntentFridaySleep);
            mAlarmManagerSaturdaySleep.cancel(mPendingIntentSaturdaySleep);
            mAlarmManagerSundaySleep.cancel(mPendingIntentSundaySleep);
        }catch (Exception e){}


    }

    private void readSharedPreDay() {
        mBooleanMonday = SharedPreferencesManager.getMonday(this);
        mBooleanTuesday = SharedPreferencesManager.getTuesday(this);
        mBooleanWednesday = SharedPreferencesManager.getWednesday(this);
        mBooleanThursday = SharedPreferencesManager.getThursday(this);
        mBooleanFriday = SharedPreferencesManager.getFriday(this);
        mBooleanSaturday = SharedPreferencesManager.getSaturday(this);
        mBooleanSunday = SharedPreferencesManager.getSunday(this);

    }

    private void createCalenda(Intent intent, PendingIntent pendingIntent, AlarmManager alarmManager, long time, int id) {
        intent = new Intent(MainActivity.this, AlarmRecever.class);
        intent.putExtra("TimeSleep",true);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, oneWeek, pendingIntent);

        //1*24*60*60*1000
    }

    private void setCalendarMonday() {
        //  mCalendarMondaySleep.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        mCalendarMondaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarMondaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarMondaySleep.set(Calendar.SECOND, 0);
        mCalendarMondaySleep.set(Calendar.MILLISECOND, 0);
        timesleepMonday = mCalendarMondaySleep.getTimeInMillis();

    }

    private void setCalendarTuesday() {
        // mCalendarTuesdaySleep.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        mCalendarTuesdaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarTuesdaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarTuesdaySleep.set(Calendar.SECOND, 0);
        mCalendarTuesdaySleep.set(Calendar.MILLISECOND, 0);
        timesleepTuesday = mCalendarTuesdaySleep.getTimeInMillis();
    }

    private void setCalendarWednesday() {
        //   mCalendarWednesdaySleep.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        mCalendarWednesdaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarWednesdaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarWednesdaySleep.set(Calendar.SECOND, 0);
        mCalendarWednesdaySleep.set(Calendar.MILLISECOND, 0);
        timesleepWednesday = mCalendarWednesdaySleep.getTimeInMillis();

    }

    private void setCalendarThursday() {
        // mCalendarThursdaySleep.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        mCalendarThursdaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarThursdaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarThursdaySleep.set(Calendar.SECOND, 0);
        mCalendarThursdaySleep.set(Calendar.MILLISECOND, 0);
        timesleepThursday = mCalendarThursdaySleep.getTimeInMillis();
    }

    private void setCalendarFriday() {
        // mCalendarFridaySleep.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        mCalendarFridaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarFridaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarFridaySleep.set(Calendar.SECOND, 0);
        mCalendarFridaySleep.set(Calendar.MILLISECOND, 0);
        timesleepFriday = mCalendarFridaySleep.getTimeInMillis();
    }

    private void setCalendarSaturday() {
        //   mCalendarSaturdaySleep.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        mCalendarSaturdaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarSaturdaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarSaturdaySleep.set(Calendar.SECOND, 0);
        mCalendarSaturdaySleep.set(Calendar.MILLISECOND, 0);
        timesleepSaturday = mCalendarSaturdaySleep.getTimeInMillis();
    }

    private void setCalendarSunday() {
        //     mCalendarSundaySleep.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        mCalendarSundaySleep.set(Calendar.HOUR_OF_DAY, hourSleep);
        mCalendarSundaySleep.set(Calendar.MINUTE, minuteSleep);
        mCalendarSundaySleep.set(Calendar.SECOND, 0);
        mCalendarSundaySleep.set(Calendar.MILLISECOND, 0);
        timesleepSunday = mCalendarSundaySleep.getTimeInMillis();
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

    private void setTime5p() {
        t2 = timesleepMonday + (oneDay - truoc5p);
        t3 = timesleepTuesday + (twoDay - truoc5p);
        t4 = timesleepWednesday + (threeDay - truoc5p);
        t5 = timesleepThursday + (fourDay - truoc5p);
        t6 = timesleepFriday + (fiveDay - truoc5p);
        t7 = timesleepFriday + (sixDay - truoc5p);
        cn = timesleepSunday + (oneWeek - truoc5p);
    }

    private void setTime15p() {
        t2 = timesleepMonday + (oneDay - truoc15p);
        t3 = timesleepTuesday + (twoDay - truoc15p);
        t4 = timesleepWednesday + (threeDay - truoc15p);
        t5 = timesleepThursday + (fourDay - truoc15p);
        t6 = timesleepFriday + (fiveDay - truoc15p);
        t7 = timesleepFriday + (sixDay - truoc15p);
        cn = timesleepSunday + (oneWeek - truoc15p);
    }

    private void setTime30p() {
        t2 = timesleepMonday + (oneDay - truoc30p);
        t3 = timesleepTuesday + (twoDay - truoc30p);
        t4 = timesleepWednesday + (threeDay - truoc30p);
        t5 = timesleepThursday + (fourDay - truoc30p);
        t6 = timesleepFriday + (fiveDay - truoc30p);
        t7 = timesleepFriday + (sixDay - truoc30p);
        cn = timesleepSunday + (oneWeek - truoc30p);
    }

    private void setTime1hour() {
        t2 = timesleepMonday + (oneDay - truoc1hour);
        t3 = timesleepTuesday + (twoDay - truoc1hour);
        t4 = timesleepWednesday + (threeDay - truoc1hour);
        t5 = timesleepThursday + (fourDay - truoc1hour);
        t6 = timesleepFriday + (fiveDay - truoc1hour);
        t7 = timesleepFriday + (sixDay - truoc1hour);
        cn = timesleepSunday + (oneWeek - truoc1hour);
    }

    private void setTimeNow() {
        t2 = timesleepMonday + oneDay;
        t3 = timesleepTuesday + twoDay;
        t4 = timesleepWednesday + threeDay;
        t5 = timesleepThursday + fourDay;
        t6 = timesleepFriday + fiveDay;
        t7 = timesleepFriday + sixDay;
        cn = timesleepSunday + oneWeek;
    }
}

