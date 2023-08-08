package com.example.mpr.lectures.lec3;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mpr.R;

public class Lec3_5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lec3_5);

        // get view ref
        VideoView videoView = findViewById(R.id.videoView);

        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.vid_small);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.start();
    }
}