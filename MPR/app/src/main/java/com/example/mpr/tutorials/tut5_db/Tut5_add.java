package com.example.mpr.tutorials.tut5_db;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mpr.R;
import com.example.mpr.tutorials.tut5_db.db.FriendManager;
import com.example.mpr.tutorials.tut5_db.models.Friend;

public class Tut5_add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tut5_add);
    }

    public void onAddBtn(View view) {
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

//                // CONNECT TO DB
//                DbHelper dbHelper = new DbHelper(this);
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                // INSERT FRIEND TO DB
//                // statement
//                String sql = "INSERT INTO friends (name, email, phoneNo) VALUES (?,?,?);";
//                SQLiteStatement sqLiteStatement = db.compileStatement(sql);
//                // bind params
//                sqLiteStatement.bindString(1,name);
//                sqLiteStatement.bindString(2,email);
//                sqLiteStatement.bindString(3,phone);
//                // execute
//                long id = sqLiteStatement.executeInsert();
//                // CLOSE DB
//                db.close();

                FriendManager friendManager = FriendManager.getInstance(this);
                Friend friend = new Friend(name, phone, email);
                friendManager.add(friend);
                setResult(RESULT_OK);
                finish();
                break;
        }
    }
}