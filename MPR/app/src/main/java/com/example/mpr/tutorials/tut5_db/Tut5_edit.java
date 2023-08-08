package com.example.mpr.tutorials.tut5_db;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mpr.R;
import com.example.mpr.tutorials.tut5_db.db.FriendManager;
import com.example.mpr.tutorials.tut5_db.models.Friend;

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
                FriendManager friendManager = FriendManager.getInstance(this);
                friendManager.update(getIntent().getLongExtra("id", 0), new Friend(name, phone, email));
                setResult(RESULT_OK);
                finish();
                break;
        }
    }
}