package com.example.mpr.lectures.lec3;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mpr.R;

public class Lec3_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lec3_2);
    }

    public void changeImage(View view) {
        // get image ref
        ImageView imageView = findViewById(R.id.imageView);

        // change src
        imageView.setImageResource(R.drawable.cat_2);
    }
}