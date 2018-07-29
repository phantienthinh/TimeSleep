package com.example.tienthinh.timesleep.Recever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


public class AlarmRecever extends BroadcastReceiver {
    public static String TAG = "Debug";
     boolean monday;
     boolean tuesday;
     boolean wednesday;
     boolean thursday;
     boolean friday;
     boolean saturday;
     boolean sunday;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("aaa"), Toast.LENGTH_SHORT).show();
        monday = intent.getBooleanExtra("monday",false);
        tuesday = intent.getBooleanExtra("tuesday",false);
        wednesday = intent.getBooleanExtra("wednesday",false);
        thursday = intent.getBooleanExtra("thursday",false);
        friday = intent.getBooleanExtra("friday",false);
        saturday = intent.getBooleanExtra("saturday",false);
        sunday = intent.getBooleanExtra("sunday",false);
        Log.e(TAG, "onReceive: " );


    }
}
