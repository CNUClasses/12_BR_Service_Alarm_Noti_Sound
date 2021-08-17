package com.library1.example.perkins.br_service_alarm_noti_sound;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doClick(View view) {
        Log.d(TAG,"in doClick");
        Intent myIntent = new Intent();
        myIntent.setAction(MyReceiver.MY_ACTION);
        sendBroadcast(myIntent);
    }
}
