package com.example.mpr.lectures.lec3;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mpr.R;

public class Lec3_4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lec3_4);

        Log.i("MPR", "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("MPR", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("MPR", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("MPR", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i("MPR", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("MPR", "onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i("MPR", "onRestart()");
    }
}