package com.library1.example.perkins.br_service_alarm_noti_sound;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    MyReceiver mr;
    IntentFilter myFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //need these to register broadcast receiver
        mr=new MyReceiver();
        myFilter = new IntentFilter(MyReceiver.MY_ACTION);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mr);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mr, myFilter);
    }

    public void doClick(View view) {
        Log.d(TAG,"in doClick");
        Intent myIntent = new Intent(MyReceiver.MY_ACTION);
        sendBroadcast(myIntent);
    }
}
