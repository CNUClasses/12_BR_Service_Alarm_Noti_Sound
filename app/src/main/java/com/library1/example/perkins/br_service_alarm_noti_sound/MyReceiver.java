package com.library1.example.perkins.br_service_alarm_noti_sound;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

/**
 * Created by Perkins on 4/7/2016.
 */
public class MyReceiver extends BroadcastReceiver {
    public static final String MY_ACTION = "com.ex.annoy";
    private static final String TAG = "receiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"in receiver onReceive");
        Intent myIntent = new Intent(context,MyService.class);
        context.startService(myIntent);
    }
}
