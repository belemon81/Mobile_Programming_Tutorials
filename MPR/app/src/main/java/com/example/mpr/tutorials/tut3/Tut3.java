package com.example.mpr.tutorials.tut3;

import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mpr.R;

public class Tut3 extends AppCompatActivity {
    private ImageView currentCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tut3);

        Button spin = findViewById(R.id.spin);
        Button turn_around = findViewById(R.id.turn_around);
        Button jump = findViewById(R.id.jump);
        Button clap = findViewById(R.id.clap);
        Button revert = findViewById(R.id.revert);
        Button blink = findViewById(R.id.blink);
        Button bounce = findViewById(R.id.bounce);
        Button kiss = findViewById(R.id.kiss);

        ImageView star = findViewById(R.id.star);
        ImageView ball = findViewById(R.id.ball);
        ImageView star_circle = findViewById(R.id.star_circle);
        ImageView ball_circle = findViewById(R.id.ball_circle);
        ImageView kiss_popup = findViewById(R.id.kiss_popup);

        MediaPlayer clapping = MediaPlayer.create(this, R.raw.clapping);
        MediaPlayer moving = MediaPlayer.create(this, R.raw.moving);
        MediaPlayer jumping = MediaPlayer.create(this, R.raw.jumping);
        MediaPlayer X = MediaPlayer.create(this, R.raw.go);
        MediaPlayer kissing = MediaPlayer.create(this, R.raw.kiss);
        MediaPlayer bouncing = MediaPlayer.create(this, R.raw.bounce);
        MediaPlayer blinking = MediaPlayer.create(this, R.raw.blink);

        currentCharacter = star;
        ball.setAlpha(0.0f);
        kiss_popup.setAlpha(0.0f);

        //rotationYBy()
        //rotationBy()
        //rotation()
        //alpha()
        //translationYBy()
        //scaleYBy()

        star_circle.setOnClickListener(view -> {
            X.seekTo(0);
            X.start();
            star.animate().alpha(1).setDuration(1000);
            ball.animate().alpha(0).setDuration(1000);
            currentCharacter = star;
        });

        ball_circle.setOnClickListener(view -> {
            X.seekTo(0);
            X.start();
            ball.animate().alpha(1).setDuration(1000);
            star.animate().alpha(0).setDuration(1000);
            currentCharacter = ball;
        });

        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moving.seekTo(0);
                moving.start();
                currentCharacter.animate().rotationBy(360 * 2).setDuration(2000);
            }
        });

        turn_around.setOnClickListener(view -> {
            moving.seekTo(0);
            moving.start();
            currentCharacter.animate().rotationYBy(360 * 2).setDuration(1000);
        });

        jump.setOnClickListener(view -> {
            jumping.seekTo(0);
            jumping.start();
            synchronized (this) {
                currentCharacter.animate().translationYBy(-200).setDuration(100).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        currentCharacter.animate().translationYBy(200).setDuration(100);
                    }
                });
            }
        });

        clap.setOnClickListener(view -> {
            clapping.seekTo(0);
            clapping.start();
        });

        blink.setOnClickListener(view -> {
            blinking.seekTo(0);
            blinking.start();
            Animation blinkAni = new AlphaAnimation(0.0f, 1.0f);
            blinkAni.setDuration(200);
            blinkAni.setStartOffset(20);
            blinkAni.setRepeatMode(Animation.REVERSE);
            blinkAni.setRepeatCount(5);
            currentCharacter.startAnimation(blinkAni);
        });

        bounce.setOnClickListener(view -> {
            synchronized (this) {
                currentCharacter.animate().translationYBy(-500).setDuration(500).withEndAction(() -> {
                    ObjectAnimator moveAnim = ObjectAnimator.ofFloat(currentCharacter, "Y", 500);
                    moveAnim.setDuration(2000);
                    moveAnim.setInterpolator(new BounceInterpolator());
                    bouncing.seekTo(0);
                    bouncing.start();
                    moveAnim.start();
                });
            }
        });

        revert.setOnClickListener(view -> {
            jumping.seekTo(0);
            jumping.start();
            currentCharacter.animate().rotationYBy(180).setDuration(10);
        });

        kiss.setOnClickListener(view -> {
            synchronized (this) {
                currentCharacter.animate().scaleXBy(1.2f).scaleYBy(1.2f).setDuration(1000).withEndAction(() -> {
                    kissing.seekTo(0);
                    kissing.start();
                    kiss_popup.animate().alpha(1).setDuration(500).withEndAction(() -> {
                        kiss_popup.animate().alpha(0).setDuration(500).withEndAction(() -> {
                            currentCharacter.animate().scaleXBy(-1.2f).scaleYBy(-1.2f).setDuration(1000);
                        });
                    });
                });
            }
        });
    }
}