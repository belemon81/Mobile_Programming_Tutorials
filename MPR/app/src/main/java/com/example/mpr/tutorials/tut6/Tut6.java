package com.example.mpr.tutorials.tut6;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mpr.R;

public class Tut6 extends AppCompatActivity {
    private TextView language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tut6);
        language = findViewById(R.id.language);
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

        String username = sharedPreferences.getString("username", "guest");

        if (username.equals("guest")) {

            sharedPreferences.edit().putString("username", "Belemon81").apply();

            new AlertDialog.Builder(this)
                    .setTitle("Choose a language:")
                    .setIcon(android.R.drawable.ic_notification_overlay)
                    .setMessage("Which language would you like?")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            language.setText(R.string.engWelcome);
                        }
                    })
                    .setNegativeButton("Vietnamese", (dialogInterface, i) ->
                            language.setText(R.string.vieWelcome)).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.vietnamese:
                language.setText(R.string.vieWelcome);
                return true;
            case R.id.english:
                language.setText(R.string.engWelcome);
                return true;
        }

        return false;
    }
}
