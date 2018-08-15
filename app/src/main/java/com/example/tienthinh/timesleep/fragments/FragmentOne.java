package com.example.tienthinh.timesleep.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.tienthinh.timesleep.MainActivity;
import com.example.tienthinh.timesleep.R;
import com.example.tienthinh.timesleep.widgets.CircularSliderRange;
import com.example.tienthinh.timesleep.widgets.ThumbEvent;
import com.example.tienthinh.timesleep.models.SharedPreferencesManager;
import com.example.tienthinh.timesleep.services.MyServices;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class FragmentOne extends Fragment implements View.OnClickListener {
    int minuteSleep;
    int hourSleep;
    private ImageView iv_sleep;
    private ImageView iv_wake_up;
    private float t2, t3, t4, t5, t6, t7, cn;
    BarChart barChart;
    private TextView tv_sleep;
    private TextView tv_wake_up;
    private RelativeLayout relativeLayout, relativeLayoutBarchar;
    private Context context;
    private CircularSliderRange sliderRange;
    private View view;
    // private int minute1;
    private int nguTruoc, thuc, second, tongHourSleep, tongMinuteSleep;
    private ToggleButton toggleOnOff;
    private TextView txt_Tong_Time, txt_Time_Sleep, txt_Time_Weke_up;
    private ImageView iv_barchar;
    private TextView tv_barchar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one, container, false);

        //ánh xạ
        initView();


        //createTouchEvent
        createTouchEvent();
        //click vào clock
        onClickClock();
        //khởi tạo ui
        uiCreate();


        return view;
    }

    private void createTouchEvent() {

        sliderRange.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                v.onTouchEvent(event);
                return true;
            }
        });
    }

    private void uiCreate() {
        relativeLayoutBarchar.setClickable(true);
        relativeLayout.setClickable(true);
        relativeLayout.setAlpha(0.5f);
        tv_wake_up.setAlpha(0.5f);
        tv_sleep.setAlpha(0.5f);
        sliderRange.setAlpha(0.5f);
        txt_Time_Sleep.setAlpha(0.5f);
        txt_Time_Weke_up.setAlpha(0.5f);
        txt_Tong_Time.setAlpha(0.5f);
        iv_sleep.setAlpha(0.5f);
        iv_wake_up.setAlpha(0.5f);
        barChart.setAlpha(0.5f);
    }

    private void onClickClock() {
        sliderRange.setOnSliderRangeMovedListener(new com.example.tienthinh.timesleep.widgets.CircularSliderRange.OnSliderRangeMovedListener() {
            @Override
            public void onStartSliderMoved(double pos) {
                nguTruoc = (int) pos;
                second = (nguTruoc * 240);
                MainActivity.hourSleep = second / 3600;
                MainActivity.minuteSleep = (second % 3600) / 60;

                epKieu1();

                if (MainActivity.minuteSleep < 10) {
                    txt_Time_Sleep.setText(MainActivity.hourSleep + ":" + "0" + MainActivity.minuteSleep);
                } else {
                    txt_Time_Sleep.setText(MainActivity.hourSleep + ":" + MainActivity.minuteSleep);
                }
                String chuoi = txt_Time_Sleep.getText().toString().trim();
                SharedPreferencesManager.setTimeSleep(getContext(), chuoi);
                SharedPreferencesManager.setPositionClockSleep(context, nguTruoc);
                SharedPreferencesManager.setHourSleep(context, MainActivity.hourSleep);
                SharedPreferencesManager.setMinuteSleep(context, MainActivity.minuteSleep);
                MainActivity.hourSleep = SharedPreferencesManager.getHourSleep(context);
                MainActivity.minuteSleep = SharedPreferencesManager.getMinuteSleep(context);
            }

            @Override
            public void onEndSliderMoved(double pos) {
                thuc = (int) pos;
                second = (thuc * 240);
                MainActivity.hourWakeUp = second / 3600;
                MainActivity.minuteWakeUp = (second % 3600) / 60;
                epKieu2();
                if (MainActivity.minuteWakeUp < 10) {
                    txt_Time_Weke_up.setText(MainActivity.hourWakeUp + ":" + "0" + MainActivity.minuteWakeUp);
                } else {
                    txt_Time_Weke_up.setText(MainActivity.hourWakeUp + ":" + MainActivity.minuteWakeUp);

                }
                String chuoi = txt_Time_Weke_up.getText().toString().trim();
                SharedPreferencesManager.setTimeWakeUp(getContext(), chuoi);
                SharedPreferencesManager.setPositionClockWakeUp(context, thuc);
                SharedPreferencesManager.setHourWakeUp(context, MainActivity.hourWakeUp);
                SharedPreferencesManager.setMinuteWakeUp(context, MainActivity.minuteWakeUp);
            }

            @Override
            public void onStartSliderEvent(ThumbEvent event) {
                //là đi ngủ
                TinhTimeNguEnd();
                SharedPreferencesManager.setTongHourSleep(context, tongHourSleep);
                SharedPreferencesManager.setTongMinuteSleep(context, tongMinuteSleep);
                int hS = SharedPreferencesManager.getHourSleep(context);
                int mS = SharedPreferencesManager.getMinuteSleep(context);
                int hW = SharedPreferencesManager.getHourWakeUp(context);
                int mW = SharedPreferencesManager.getMinuteWakeUp(context);

                if (toggleOnOff.isChecked() == true) {
                    if (hS == hW) {
                        if (mS == mW) {
                            Toast.makeText(context, R.string.invalid_data, Toast.LENGTH_SHORT).show();
                        } else {
                            sendBroadcastTimeSleepActivity();
                        }
                    } else {
                        sendBroadcastTimeSleepActivity();
                    }
                }
            }

            @Override
            public void onEndSliderEvent(ThumbEvent event) {
                TinhTimeNguStart();
                SharedPreferencesManager.setTongHourSleep(context, tongHourSleep);
                SharedPreferencesManager.setTongMinuteSleep(context, tongMinuteSleep);

                int hS = SharedPreferencesManager.getHourSleep(context);
                int mS = SharedPreferencesManager.getMinuteSleep(context);
                int hW = SharedPreferencesManager.getHourWakeUp(context);
                int mW = SharedPreferencesManager.getMinuteWakeUp(context);

                if (toggleOnOff.isChecked() == true) {
                    if (hS == hW) {
                        if (mS == mW) {
                            Toast.makeText(context, R.string.invalid_data, Toast.LENGTH_SHORT).show();
                        } else {
                            sendBroadcastTimeWakeUpActivity();
                        }
                    } else {
                        sendBroadcastTimeWakeUpActivity();
                    }
                }
            }
        });

//        sliderRange.setOnSliderRangeMovedListener(new CircularSliderRange.OnSliderRangeMovedListener() {
//            @Override
//            public void onStartSliderMoved(double pos) {
//                nguTruoc = (int) pos;
//                second = (nguTruoc * 240);
//                MainActivity.hourSleep = second / 3600;
//                MainActivity.minuteSleep = (second % 3600) / 60;
//
//                epKieu1();
//
//                if (MainActivity.minuteSleep < 10) {
//                    txt_Time_Sleep.setText(MainActivity.hourSleep + ":" + "0" + MainActivity.minuteSleep);
//                } else {
//                    txt_Time_Sleep.setText(MainActivity.hourSleep + ":" + MainActivity.minuteSleep);
//                }
//
//
//
//                String chuoi = txt_Time_Sleep.getText().toString().trim();
//                SharedPreferencesManager.setTimeSleep(getContext(), chuoi);
//                SharedPreferencesManager.setPositionClockSleep(context, nguTruoc);
//                SharedPreferencesManager.setHourSleep(context, MainActivity.hourSleep);
//                SharedPreferencesManager.setMinuteSleep(context, MainActivity.minuteSleep);
//                MainActivity.hourSleep=SharedPreferencesManager.getHourSleep(context);
//                MainActivity.minuteSleep = SharedPreferencesManager.getMinuteSleep(context);
//
//            }
//
//            @Override
//            public void onEndSliderMoved(double pos) {
//                thuc = (int) pos;
//                second = (thuc * 240);
//                MainActivity.hourWakeUp = second / 3600;
//                MainActivity.minuteWakeUp = (second % 3600) / 60;
//                epKieu2();
//                if (MainActivity.minuteWakeUp < 10) {
//                    txt_Time_Weke_up.setText(MainActivity.hourWakeUp + ":" + "0" + MainActivity.minuteWakeUp);
//                } else {
//                    txt_Time_Weke_up.setText(MainActivity.hourWakeUp + ":" + MainActivity.minuteWakeUp);
//
//                }
//                String chuoi = txt_Time_Weke_up.getText().toString().trim();
//                SharedPreferencesManager.setTimeWakeUp(getContext(), chuoi);
//                SharedPreferencesManager.setPositionClockWakeUp(context, thuc);
//                SharedPreferencesManager.setHourWakeUp(context, MainActivity.hourWakeUp);
//                SharedPreferencesManager.setMinuteWakeUp(context, MainActivity.minuteWakeUp);
//
//            }
//
//            @Override
//            public void onStartSliderEvent(ThumbEvent event) {
//                //là đi ngủ
//                TinhTimeNguEnd();
//
//                if (toggleOnOff.isChecked() == true) {
//                    sendBroadcastTimeSleepActivity();
//                } else {
//                }
//
//            }
//
//            @Override
//            public void onEndSliderEvent(ThumbEvent event) {
//                TinhTimeNguStart();
//                if (toggleOnOff.isChecked() == true) {
//                    sendBroadcastTimeWakeUpActivity();
//                } else {
//                }
//
//            }
//        });
    }


    private void TinhTimeNguStart() {
//        if (thuc > nguTruoc) {
//            tongNgu = thuc - nguTruoc;
//        } else {
//            int so1 = 360 - nguTruoc;
//            tongNgu = so1 + thuc;
//        }
        epKieu();
//        if (MainActivity.hourWakeUp > MainActivity.hourSleep) {
//            tongHourSleep = MainActivity.hourWakeUp - MainActivity.hourSleep;
//        } else {
//            if (MainActivity.hourSleep > MainActivity.hourWakeUp) {
//                int so1 = 24 - MainActivity.hourSleep;
//                tongHourSleep = so1 + MainActivity.hourWakeUp;
//            } else {
//            }
//        }
//
//        if (MainActivity.minuteSleep > MainActivity.minuteWakeUp) {
//            int so2 = 60 - MainActivity.minuteSleep;
//            tongMinuteSleep = so2 + MainActivity.minuteWakeUp;
//        } else {
//            if (MainActivity.minuteWakeUp > MainActivity.minuteSleep) {
//                tongMinuteSleep = MainActivity.minuteWakeUp - MainActivity.minuteSleep;
//            } else {
//            }
//
//            if (MainActivity.hourSleep==0){
//                tongHourSleep --;
//            }else {}
//
//            if (MainActivity.hourWakeUp == MainActivity.hourSleep && MainActivity.minuteWakeUp == MainActivity.minuteSleep) {
//                tongMinuteSleep = 0;
//                tongHourSleep = 24;
//            }

//        }
        thuatToanTinhTimeWU();
        if (tongMinuteSleep < 10) {
            txt_Tong_Time.setText(tongHourSleep + "h" +"\t"+ "0" + tongMinuteSleep+getResources().getString(R.string.m));
        } else {
            txt_Tong_Time.setText(tongHourSleep + "h" +"\t"+ tongMinuteSleep+getResources().getString(R.string.m));
        }

        String chuoi = txt_Tong_Time.getText().toString().trim();
        SharedPreferencesManager.setTongTime(getContext(), chuoi);
    }

    private void thuatToanTinhTimeWU() {
        if (MainActivity.hourSleep > MainActivity.hourWakeUp) {
            hourSleep = 24 - MainActivity.hourSleep;
            minuteSleep = 60 - MainActivity.minuteSleep;
            if (minuteSleep == 60) {
                minuteSleep = 0;
            }
            if (minuteSleep > 0) {
                hourSleep--;
            }
            tongHourSleep = hourSleep + MainActivity.hourWakeUp;
            tongMinuteSleep = minuteSleep + MainActivity.minuteWakeUp;
            if (tongMinuteSleep >= 60) {
                tongMinuteSleep = tongMinuteSleep - 60;
                tongHourSleep++;
            }
        } else if (MainActivity.hourSleep < MainActivity.hourWakeUp) {
            tongHourSleep = MainActivity.hourWakeUp - MainActivity.hourSleep;
            if (MainActivity.minuteWakeUp > MainActivity.minuteSleep) {
                tongMinuteSleep = MainActivity.minuteWakeUp - MainActivity.minuteSleep;
            } else if (MainActivity.minuteWakeUp < MainActivity.minuteSleep) {
                tongHourSleep--;
                int a1 = MainActivity.minuteSleep - MainActivity.minuteWakeUp;
                tongMinuteSleep = 60 - a1;
            } else if (MainActivity.minuteWakeUp == MainActivity.minuteSleep) {
                tongMinuteSleep = 0;
            }

        } else if (MainActivity.hourSleep == MainActivity.hourWakeUp) {
            if (MainActivity.minuteSleep == MainActivity.minuteWakeUp) {
                if (thuc > nguTruoc) {
                    tongHourSleep = 0;
                    tongMinuteSleep = 0;
                } else if (thuc < nguTruoc) {
                    tongMinuteSleep = 0;
                    tongHourSleep = 24;
                } else if (thuc == nguTruoc) {
                    tongMinuteSleep = 0;
                    tongHourSleep = 0;
                }
            }

            if (MainActivity.minuteWakeUp > MainActivity.minuteSleep) {
                tongHourSleep = 0;
                tongMinuteSleep = MainActivity.minuteWakeUp - MainActivity.minuteSleep;
            }
            if (MainActivity.minuteSleep > MainActivity.minuteWakeUp) {
                tongHourSleep = 23;
                if (MainActivity.minuteSleep + MainActivity.minuteWakeUp == 60) {
                    tongMinuteSleep = MainActivity.minuteSleep - MainActivity.minuteWakeUp;
                } else {
                    int a = MainActivity.minuteSleep - MainActivity.minuteWakeUp;
                    tongMinuteSleep = 60 - a;
                }

            }
            if (MainActivity.hourSleep == 0 && MainActivity.hourWakeUp == 0) {
                tongHourSleep = 24;
                tongMinuteSleep = 0;
            }
        }
    }

    private void TinhTimeNguEnd() {
//        if (hour > nguTruoc) {
//            tongNgu = thuc - nguTruoc;
//        } else {
//            int so1 = 360 - nguTruoc;
//            tongNgu = so1 + thuc;
//        }
//
//        second = (tongNgu * 240);
//        hour = second / 3600;
//        minute = (second % 3600) / 60;
//        epKieu();
//        if (minute < 10) {
//            txt_Tong_Time.setText(hour + ":" + "0" + minute);
//        } else {
//            txt_Tong_Time.setText(hour + ":" + minute);
//        }
        epKieu();
//        if (MainActivity.hourWakeUp > MainActivity.hourSleep) {
//            tongHourSleep = MainActivity.hourWakeUp - MainActivity.hourSleep;
//        } else {
//            if (MainActivity.hourSleep > MainActivity.hourWakeUp) {
//                int so1 = 24 - MainActivity.hourSleep;
//                tongHourSleep = so1 + MainActivity.hourWakeUp;
//            } else {
//
//            }
//        }
//
//
//        if (MainActivity.minuteSleep > MainActivity.minuteWakeUp) {
//            int so2 = 60 - MainActivity.minuteSleep;
//            tongMinuteSleep = so2 + MainActivity.minuteWakeUp;
//        } else {
//            if (MainActivity.minuteWakeUp > MainActivity.minuteSleep) {
//                tongMinuteSleep = MainActivity.minuteWakeUp - MainActivity.minuteSleep;
//            } else {
//                if (MainActivity.minuteSleep == MainActivity.minuteWakeUp) {
//                    tongMinuteSleep = 0;
//                } else {
////                    if (minuteSleep>0&&hourSleep<hourWakeUp){
////                        tongHourSleep =tongHourSleep-1;
////                        int so3;
////                        so3 = 60-minuteSleep;
////                        tongMinuteSleep=so3+minuteWakeUp;
////                    }else {
////
////                    }
//                }
//            }
//
//
//            if (MainActivity.hourWakeUp == MainActivity.hourSleep && MainActivity.minuteWakeUp == MainActivity.minuteSleep) {
//                tongMinuteSleep = 0;
//                tongHourSleep = 24;
//            }
//
//        }
        thuatToanTinhTimeSl();

        if (tongMinuteSleep < 10) {
            txt_Tong_Time.setText(tongHourSleep+ "h" +"\t"+ "0" +  tongMinuteSleep+getResources().getString(R.string.m));
        } else {
            txt_Tong_Time.setText(tongHourSleep + "h" +"\t"+ tongMinuteSleep+getResources().getString(R.string.m));
        }


        String chuoi = txt_Tong_Time.getText().toString().trim();
        SharedPreferencesManager.setTongTime(getContext(), chuoi);
    }

    private void thuatToanTinhTimeSl() {
        if (MainActivity.hourSleep > MainActivity.hourWakeUp) {
            hourSleep = 24 - MainActivity.hourSleep;
            minuteSleep = 60 - MainActivity.minuteSleep;
            if (minuteSleep == 60) {
                minuteSleep = 0;
            }
            if (minuteSleep > 0) {
                hourSleep--;
            }
            tongHourSleep = hourSleep + MainActivity.hourWakeUp;
            tongMinuteSleep = minuteSleep + MainActivity.minuteWakeUp;
            if (tongMinuteSleep >= 60) {
                tongMinuteSleep = tongMinuteSleep - 60;
                tongHourSleep++;
            }
        } else if (MainActivity.hourSleep < MainActivity.hourWakeUp) {
            tongHourSleep = MainActivity.hourWakeUp - MainActivity.hourSleep;
            if (MainActivity.minuteWakeUp > MainActivity.minuteSleep) {
                tongMinuteSleep = MainActivity.minuteWakeUp - MainActivity.minuteSleep;
            } else if (MainActivity.minuteWakeUp < MainActivity.minuteSleep) {
                tongHourSleep--;
                int a1 = MainActivity.minuteSleep - MainActivity.minuteWakeUp;
                tongMinuteSleep = 60 - a1;
            } else if (MainActivity.minuteWakeUp == MainActivity.minuteSleep) {
                tongMinuteSleep = 0;
            }

        } else if (MainActivity.hourSleep == MainActivity.hourWakeUp) {
            if (MainActivity.minuteWakeUp == MainActivity.minuteSleep) {
                if (thuc > nguTruoc) {
                    tongHourSleep = 0;
                    tongMinuteSleep = 0;
                } else if (thuc < nguTruoc) {
                    tongMinuteSleep = 0;
                    tongHourSleep = 24;
                } else if (thuc == nguTruoc) {
                    tongHourSleep = 24;
                    tongMinuteSleep = 0;
                }
            }

            if (MainActivity.minuteWakeUp > MainActivity.minuteSleep) {
                tongHourSleep = 0;
                tongMinuteSleep = MainActivity.minuteWakeUp - MainActivity.minuteSleep;
            }
            if (MainActivity.minuteSleep > MainActivity.minuteWakeUp) {
//                tongHourSleep=23;
//                tongMinuteSleep = MainActivity.minuteSleep - MainActivity.minuteWakeUp;
                tongHourSleep = 23;
                if (MainActivity.minuteSleep + MainActivity.minuteWakeUp == 60) {
                    tongMinuteSleep = MainActivity.minuteSleep - MainActivity.minuteWakeUp;
                } else {
                    int a = MainActivity.minuteSleep - MainActivity.minuteWakeUp;
                    tongMinuteSleep = 60 - a;
                }
            }
            if (MainActivity.hourSleep == 0 && MainActivity.hourWakeUp == 0) {
                tongHourSleep = 23;
                if (MainActivity.minuteSleep > MainActivity.minuteWakeUp) {
                    tongMinuteSleep = 60 - MainActivity.minuteSleep;
                } else if (MainActivity.minuteSleep < MainActivity.minuteWakeUp) {
                    tongMinuteSleep = 60 - MainActivity.minuteWakeUp;
                }
            }
        }
    }

    private void epKieu() {
        if (tongMinuteSleep >= 0 && tongMinuteSleep <= 4) {
            tongMinuteSleep = 0;
        } else {
            if (tongMinuteSleep >= 5 && tongMinuteSleep <= 9) {
                tongMinuteSleep = 5;
            } else {
                if (tongMinuteSleep >= 10 && tongMinuteSleep <= 14) {
                    tongMinuteSleep = 10;
                } else {
                    if (tongMinuteSleep >= 15 && tongMinuteSleep <= 19) {
                        tongMinuteSleep = 15;
                    } else {
                        if (tongMinuteSleep >= 20 && tongMinuteSleep <= 24) {
                            tongMinuteSleep = 20;
                        } else {
                            if (tongMinuteSleep >= 25 && tongMinuteSleep <= 29) {
                                tongMinuteSleep = 25;
                            } else {
                                if (tongMinuteSleep >= 30 && tongMinuteSleep <= 34) {
                                    tongMinuteSleep = 30;
                                } else {
                                    if (tongMinuteSleep >= 35 && tongMinuteSleep <= 39) {
                                        tongMinuteSleep = 35;
                                    } else {
                                        if (tongMinuteSleep >= 40 && tongMinuteSleep <= 44) {
                                            tongMinuteSleep = 40;
                                        } else {
                                            if (tongMinuteSleep >= 45 && tongMinuteSleep <= 49) {
                                                tongMinuteSleep = 45;
                                            } else {
                                                if (tongMinuteSleep >= 50 && tongMinuteSleep <= 54) {
                                                    tongMinuteSleep = 50;
                                                } else {
                                                    if (tongMinuteSleep >= 55 && tongMinuteSleep <= 59) {
                                                        tongMinuteSleep = 55;
                                                    } else {

                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void epKieu1() {
        if (MainActivity.minuteSleep >= 0 && MainActivity.minuteSleep <= 4) {
            MainActivity.minuteSleep = 0;
        } else {
            if (MainActivity.minuteSleep >= 5 && MainActivity.minuteSleep <= 9) {
                MainActivity.minuteSleep = 5;
            } else {
                if (MainActivity.minuteSleep >= 10 && MainActivity.minuteSleep <= 14) {
                    MainActivity.minuteSleep = 10;
                } else {
                    if (MainActivity.minuteSleep >= 15 && MainActivity.minuteSleep <= 19) {
                        MainActivity.minuteSleep = 15;
                    } else {
                        if (MainActivity.minuteSleep >= 20 && MainActivity.minuteSleep <= 24) {
                            MainActivity.minuteSleep = 20;
                        } else {
                            if (MainActivity.minuteSleep >= 25 && MainActivity.minuteSleep <= 29) {
                                MainActivity.minuteSleep = 25;
                            } else {
                                if (MainActivity.minuteSleep >= 30 && MainActivity.minuteSleep <= 34) {
                                    MainActivity.minuteSleep = 30;
                                } else {
                                    if (MainActivity.minuteSleep >= 35 && MainActivity.minuteSleep <= 39) {
                                        MainActivity.minuteSleep = 35;
                                    } else {
                                        if (MainActivity.minuteSleep >= 40 && MainActivity.minuteSleep <= 44) {
                                            MainActivity.minuteSleep = 40;
                                        } else {
                                            if (MainActivity.minuteSleep >= 45 && MainActivity.minuteSleep <= 49) {
                                                MainActivity.minuteSleep = 45;
                                            } else {
                                                if (MainActivity.minuteSleep >= 50 && MainActivity.minuteSleep <= 54) {
                                                    MainActivity.minuteSleep = 50;
                                                } else {
                                                    if (MainActivity.minuteSleep >= 55 && MainActivity.minuteSleep <= 59) {
                                                        MainActivity.minuteSleep = 55;
                                                    } else {

                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void epKieu2() {
        if (MainActivity.minuteWakeUp >= 0 && MainActivity.minuteWakeUp <= 4) {
            MainActivity.minuteWakeUp = 0;
        } else {
            if (MainActivity.minuteWakeUp >= 5 && MainActivity.minuteWakeUp <= 9) {
                MainActivity.minuteWakeUp = 5;
            } else {
                if (MainActivity.minuteWakeUp >= 10 && MainActivity.minuteWakeUp <= 14) {
                    MainActivity.minuteWakeUp = 10;
                } else {
                    if (MainActivity.minuteWakeUp >= 15 && MainActivity.minuteWakeUp <= 19) {
                        MainActivity.minuteWakeUp = 15;
                    } else {
                        if (MainActivity.minuteWakeUp >= 20 && MainActivity.minuteWakeUp <= 24) {
                            MainActivity.minuteWakeUp = 20;
                        } else {
                            if (MainActivity.minuteWakeUp >= 25 && MainActivity.minuteWakeUp <= 29) {
                                MainActivity.minuteWakeUp = 25;
                            } else {
                                if (MainActivity.minuteWakeUp >= 30 && MainActivity.minuteWakeUp <= 34) {
                                    MainActivity.minuteWakeUp = 30;
                                } else {
                                    if (MainActivity.minuteWakeUp >= 35 && MainActivity.minuteWakeUp <= 39) {
                                        MainActivity.minuteWakeUp = 35;
                                    } else {
                                        if (MainActivity.minuteWakeUp >= 40 && MainActivity.minuteWakeUp <= 44) {
                                            MainActivity.minuteWakeUp = 40;
                                        } else {
                                            if (MainActivity.minuteWakeUp >= 45 && MainActivity.minuteWakeUp <= 49) {
                                                MainActivity.minuteWakeUp = 45;
                                            } else {
                                                if (MainActivity.minuteWakeUp >= 50 && MainActivity.minuteWakeUp <= 54) {
                                                    MainActivity.minuteWakeUp = 50;
                                                } else {
                                                    if (MainActivity.minuteWakeUp >= 55 && MainActivity.minuteWakeUp <= 59) {
                                                        MainActivity.minuteWakeUp = 55;
                                                    } else {

                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private void createMPAndroidChart() {
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(24);
        barChart.setPinchZoom(false);
        barChart.setBackgroundColor(Color.rgb(0, 0, 0));
        barChart.setDrawGridBackground(false);


        barChart.getAxisLeft().setTextColor(Color.WHITE); // left y-axis
        barChart.getXAxis().setTextColor(Color.WHITE);
        barChart.getLegend().setTextColor(Color.WHITE);

        barChart.getAxisLeft().setAxisMaxValue(24);


        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false);
    }

    private void addArrayList() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, t2));
        entries.add(new BarEntry(1, t3));
        entries.add(new BarEntry(2, t4));
        entries.add(new BarEntry(3, t5));
        entries.add(new BarEntry(4, t6));
        entries.add(new BarEntry(5, t7));
        entries.add(new BarEntry(6, cn));

        BarDataSet barDataSet = new BarDataSet(entries, getResources().getString(R.string.Level_of_sleep_quality));
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.WHITE);
        barChart.animateY(2000);
        barChart.getDescription().setEnabled(false);

        LimitLine line = new LimitLine(10f);
        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.3f);


//        BarData data = new BarData(labels, dataset);
        barChart.setData(data);

        String t2 = context.getResources().getString(R.string.mon);
        String t3 = context.getResources().getString(R.string.tue);
        String t4 = context.getResources().getString(R.string.wed);
        String t5 = context.getResources().getString(R.string.thu);
        String t6 = context.getResources().getString(R.string.fri);
        String t7 = context.getResources().getString(R.string.sat);
        String cn = context.getResources().getString(R.string.sun);

        String[] week = new String[]{t2, t3,t4, t5,t6,t7,cn};

        XAxis xAxis = barChart.getXAxis();
        xAxis.addLimitLine(line);
        xAxis.setValueFormatter(new MyAxisValueFormatter(week));

    }

    private void initView() {
        TextView tv_on_off = view.findViewById(R.id.tv_on_off);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Regular.ttf");
        tv_on_off.setTypeface(typeface);
        tv_barchar = view.findViewById(R.id.tv_barchar);
        iv_barchar = view.findViewById(R.id.iv_barchar);
        relativeLayoutBarchar = view.findViewById(R.id.relativeLayoutBarchar);
        barChart = (BarChart) view.findViewById(R.id.barchart);
        iv_sleep = view.findViewById(R.id.iv_sleep);
        iv_wake_up = view.findViewById(R.id.iv_wake_up);
        tv_sleep = view.findViewById(R.id.tv_sleep);
        tv_wake_up = view.findViewById(R.id.tv_wake_up);
        relativeLayout = view.findViewById(R.id.relativeLayout);
        toggleOnOff = view.findViewById(R.id.btn_on_off);
        txt_Tong_Time = (TextView) view.findViewById(R.id.txt_Tong_Time);
        Typeface typefaceTotal = Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Regular.ttf");
        txt_Tong_Time.setTypeface(typefaceTotal);
        txt_Time_Sleep = (TextView) view.findViewById(R.id.txt_Seep);
        txt_Time_Weke_up = (TextView) view.findViewById(R.id.txt_Time_WakeUp);
        sliderRange = (CircularSliderRange) view.findViewById(R.id.circular);
        sliderRange.setRotation(-90);

        toggleOnOff.setOnClickListener(this);
        relativeLayout.setOnClickListener(this);

        t2 = SharedPreferencesManager.getT2(context);
        t3 = SharedPreferencesManager.getT3(context);
        t4 = SharedPreferencesManager.getT4(context);
        t5 = SharedPreferencesManager.getT5(context);
        t6 = SharedPreferencesManager.getT6(context);
        t7 = SharedPreferencesManager.getT7(context);
        cn = SharedPreferencesManager.getCN(context);
    }

    @Override
    public void onResume() {
        readSharePre();
        if (toggleOnOff.isChecked() == true) {
            setUiViewOn();
        } else {
            setUiViewOff();
        }

        super.onResume();
    }

    private void readSharePre() {
        txt_Time_Sleep.setText(SharedPreferencesManager.getTimeSleep(getContext()));
        txt_Time_Weke_up.setText(SharedPreferencesManager.getTimeWakeUp(getContext()));
        txt_Tong_Time.setText(SharedPreferencesManager.getTongTime(getContext()));
        sliderRange.setEndAngle(SharedPreferencesManager.getPositionClockWakeUp(getContext()));
        sliderRange.setStartAngle(SharedPreferencesManager.getPositionClockSleep(getContext()));
        MainActivity.hourWakeUp = SharedPreferencesManager.getHourWakeUp(getContext());
        MainActivity.minuteWakeUp = SharedPreferencesManager.getMinuteWakeUp(getContext());
        MainActivity.hourSleep = SharedPreferencesManager.getHourSleep(getContext());
        MainActivity.minuteSleep = SharedPreferencesManager.getMinuteSleep(getContext());
        toggleOnOff.setChecked(SharedPreferencesManager.getToggleOnOff(getContext()));

        t2 = SharedPreferencesManager.getT2(context);
        t3 = SharedPreferencesManager.getT3(context);
        t4 = SharedPreferencesManager.getT4(context);
        t5 = SharedPreferencesManager.getT5(context);
        t6 = SharedPreferencesManager.getT6(context);
        t7 = SharedPreferencesManager.getT7(context);
        cn = SharedPreferencesManager.getCN(context);
        //khởi tạo barchart
        createMPAndroidChart();
        //thêm đối tượng arrlist +  BarDataSet
        addArrayList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_on_off:
                if (toggleOnOff.isChecked() == true) {

//                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
//                        context.startForegroundService(new Intent(context, MyServices.class));
//                    }else {
//                        context.startService(new Intent(context,MyServices.class));
//                    }

                    setUiViewOn();
                    SharedPreferencesManager.setToggleOnOff(context, true);
//                    context.startService(new Intent(context, MyServices.class));
                } else {
                    try {
                        context.stopService(new Intent(context, MyServices.class));
                    }catch (Exception e){
                    }

                    setUiViewOff();
                    SharedPreferencesManager.setToggleOnOff(context, false);
//                    context.stopService(new Intent(context, MyServices.class));

                }
                break;
            case R.id.relativeLayout:

                break;
        }
    }

    private void setUiViewOff() {
        relativeLayoutBarchar.setClickable(false);
        txt_Tong_Time.setAlpha(0.1f);
        sliderRange.setAlpha(0.1f);
        relativeLayout.setClickable(true);
        tv_sleep.setAlpha(0.1f);
        tv_wake_up.setAlpha(0.1f);
        txt_Time_Sleep.setAlpha(0.1f);
        txt_Time_Weke_up.setAlpha(0.1f);
        iv_sleep.setAlpha(0.1f);
        iv_wake_up.setAlpha(0.1f);
        barChart.setAlpha(0.1f);
        iv_barchar.setAlpha(0.1f);
        tv_barchar.setAlpha(0.1f);
    }

    private void setUiViewOn() {
        relativeLayoutBarchar.setClickable(false);
        iv_sleep.setAlpha(1f);
        iv_wake_up.setAlpha(1f);
        txt_Tong_Time.setAlpha(1f);
        tv_sleep.setAlpha(1f);
        tv_wake_up.setAlpha(1f);
        relativeLayout.setClickable(false);
        sliderRange.setAlpha(1f);
        txt_Time_Sleep.setAlpha(1f);
        txt_Time_Weke_up.setAlpha(1f);
        barChart.setAlpha(1f);
        iv_barchar.setAlpha(1f);
        tv_barchar.setAlpha(1f);

    }

    private void sendBroadcastTimeSleepActivity() {
        Intent intent = new Intent("TimeSleepActivity");
        context.sendBroadcast(intent);

    }

    private void sendBroadcastTimeWakeUpActivity() {
        Intent intent = new Intent("TimeWakeUpActivity");
        context.sendBroadcast(intent);

    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    public class MyAxisValueFormatter implements IAxisValueFormatter {
        private String[] mValues;

        public MyAxisValueFormatter(String[] values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int) value];
        }
    }

}