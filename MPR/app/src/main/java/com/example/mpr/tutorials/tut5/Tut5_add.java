package com.example.mpr.tutorials.tut5;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mpr.R;

public class Tut5_add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tut5_add);
    }

    public void onAddBtn(View view) {
        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.exit:
                new AlertDialog.Builder(this)
                        .setMessage("Do you really want to exit?")
                        .setNegativeButton("Yes", (dialogInterface, i) -> {
                            setResult(RESULT_CANCELED);
                            finish();
                        })
                        .setPositiveButton("No", null)
                        .show();
                break;
            case R.id.addFriend:
                EditText nameField = findViewById(R.id.name);
                String name = nameField.getText().toString();
                EditText phoneField = findViewById(R.id.phone);
                String phone = phoneField.getText().toString();
                EditText emailField = findViewById(R.id.email);
                String email = emailField.getText().toString();
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("phone", phone);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}