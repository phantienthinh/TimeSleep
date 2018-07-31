package com.example.tienthinh.timesleep.Recever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.tienthinh.timesleep.services.MyServices;


public class AlarmRecever extends BroadcastReceiver {
    public static String TAG = "Debug";
    boolean mTimeSleep=false;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("aaa"), Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onReceive: " );
        mTimeSleep = intent.getBooleanExtra("TimeSleep",false);
        if (mTimeSleep==true){
            mTimeSleep=false;
            context.startService(new Intent(context, MyServices.class));
            intent=new Intent("sendTimeSleep");
            context.sendBroadcast(intent);
            Log.e(TAG, "mTimeSleep=true");
        }else {
            Log.e(TAG, "nguoc lai");
        }


    }
}
