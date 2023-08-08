package com.example.mpr.tutorials.tut4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mpr.R;

public class Tut4_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tut4_1);

        EditText link = findViewById(R.id.link);
        ImageButton redirect = findViewById(R.id.redirect);

        redirect.setOnClickListener(view -> {
            String url = link.getText().toString();
            Toast.makeText(this, "URL has been gotten! Redirecting...", Toast.LENGTH_LONG);
            Intent videoIntent = new Intent(this, Tut4_2.class);
            videoIntent.putExtra("url", url);
            //https://www.youtube.com/watch?v=X5qRgCXehLU
            startActivity(videoIntent);
        });

    }
}