package com.example.mpr.tutorials.tut10;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.mpr.R;

public class Tut10 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tut10);
        final int[] id = {1};

        // initially display
        ProfileFragment profileFragment = new ProfileFragment();

        // pass id into profile fragment
        Bundle data = new Bundle();
        data.putInt("id", id[0]);
        profileFragment.setArguments(data);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer,profileFragment)
                .commit();

        ImageButton btnPrev = findViewById(R.id.btnPrev);
        ImageButton btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putInt("id", id[0]++);
                System.out.println(id[0]);
                ProfileFragment profileFragment = new ProfileFragment();
                profileFragment.setArguments(data);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer,profileFragment)
                        .commit();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putInt("id", id[0]--);
                System.out.println(id[0]);
                ProfileFragment profileFragment = new ProfileFragment();
                profileFragment.setArguments(data);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer,profileFragment)
                        .commit();
            }
        });
    }
}