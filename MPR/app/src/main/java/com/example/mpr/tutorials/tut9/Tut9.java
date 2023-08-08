package com.example.mpr.tutorials.tut9;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.mpr.R;

public class Tut9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tut9);

        BookmarksFragment fragment = new BookmarksFragment();

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}