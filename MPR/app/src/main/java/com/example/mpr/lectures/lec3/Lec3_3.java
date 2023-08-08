package com.example.mpr.lectures.lec3;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mpr.R;

public class Lec3_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lec3_3);

        MediaPlayer player = MediaPlayer.create(this, R.raw.laugh);
        player.reset();
            player.seekTo(0);
            player.start();
//            player.pause();
//            player.start();
//            player.stop();
//            player.start();
//            player.release();
    }
}