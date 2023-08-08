package com.example.mpr.tutorials.tut5;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mpr.R;

public class Tut5_edit extends AppCompatActivity {
    private EditText nameField;
    private EditText phoneField;
    private EditText emailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tut5_edit);
        nameField = findViewById(R.id.name2);
        phoneField = findViewById(R.id.phone2);
        emailField = findViewById(R.id.email2);

        nameField.setText(getIntent().getStringExtra("name"));
        phoneField.setText(getIntent().getStringExtra("phone"));
        emailField.setText(getIntent().getStringExtra("email"));
    }

    public void onEditBtn(View view) {
        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.exit2:
                new AlertDialog.Builder(this)
                        .setMessage("Do you really want to exit?")
                        .setNegativeButton("Yes", (dialogInterface, i) -> {
                            setResult(RESULT_CANCELED);
                            finish();
                        })
                        .setPositiveButton("No", null)
                        .show();
                break;

            case R.id.editFriend:
                String name = nameField.getText().toString();
                String phone = phoneField.getText().toString();
                String email = emailField.getText().toString();
                intent.putExtra("pos", getIntent().getIntExtra("pos", 0));
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("phone", phone);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}