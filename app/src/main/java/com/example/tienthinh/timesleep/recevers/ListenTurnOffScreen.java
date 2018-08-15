package com.example.tienthinh.timesleep.recevers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ListenTurnOffScreen extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            // do whatever you need to do here
            context.sendBroadcast(new Intent("turnoffScreen"));
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            // and do whatever you need to do here

        }
    }
    }

