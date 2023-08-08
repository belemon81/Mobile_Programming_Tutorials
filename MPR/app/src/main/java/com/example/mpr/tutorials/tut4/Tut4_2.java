package com.example.mpr.tutorials.tut4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mpr.R;

public class Tut4_2 extends AppCompatActivity {
    private VideoView videoView;
    private int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tut4_2);
        // getIntent()
        // putExtra
        // getXExtra
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        videoView = findViewById(R.id.video);
        videoView.setVideoPath(url);
        MediaController mediaController = new MediaController(this);
//        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.jumping);
//        Toast.makeText(Tut4_2.this, "Video starting...", Toast.LENGTH_SHORT);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
    }

    private void log() {
        currentPos = videoView.getCurrentPosition();
        Log.i("Info", "Current playback position: " + currentPos);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("videoState", videoView.getCurrentPosition());
        log();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        videoView.seekTo(savedInstanceState.getInt("videoState"));
        log();
        videoView.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        videoView.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        log();
        videoView.stopPlayback();
    }

    @Override
    protected void onPause() {
        super.onPause();
        log();
        videoView.pause();
    }
}
