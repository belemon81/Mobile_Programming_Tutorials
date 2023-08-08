package com.example.mpr.tutorials.tut5_db;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mpr.R;
import com.example.mpr.tutorials.tut5_db.adapters.FriendAdapter;
import com.example.mpr.tutorials.tut5_db.db.DbHelper;
import com.example.mpr.tutorials.tut5_db.db.FriendManager;
import com.example.mpr.tutorials.tut5_db.models.Friend;

import java.util.ArrayList;
import java.util.List;

public class Tut5_main extends AppCompatActivity {
    private List<Friend> friends = new ArrayList<>();
    private FriendAdapter friendAdapter;
    private FriendManager friendManager;
    private ActivityResultLauncher<Intent> activityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    friends.clear();
                    friends.addAll(friendManager.all());
                    friendAdapter.notifyDataSetChanged();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tut5_main);

        // friends = loadFriends();
        friendManager = FriendManager.getInstance(this);
        friends = friendManager.all();

        RecyclerView rvFriends = findViewById(R.id.rvFriend);
        rvFriends.setLayoutManager(new LinearLayoutManager(this));
        friendAdapter = new FriendAdapter(friends);
        rvFriends.setAdapter(friendAdapter);
    }

    public void addFriend(View view) {
        Intent intent = new Intent(this, Tut5_add.class);
        activityIntent.launch(intent);
    }

    public void onClick(View view) {
        Intent intent;
        Friend current;

        switch (view.getId()) {
            case R.id.call:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + view.getContentDescription().toString()));
                startActivity(intent);
                break;

            case R.id.chat:
                intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:" + view.getContentDescription().toString()));
                startActivity(intent);
                break;

            case R.id.mail:
                intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + Uri.encode(view.getContentDescription().toString())));
                startActivity(intent);
                break;

            case R.id.edit:
                intent = new Intent(this, Tut5_edit.class);
                current = (Friend) view.getTag();
                Log.i("!", "" + current.getId());
                intent.putExtra("id", current.getId());
                intent.putExtra("name", current.getName());
                intent.putExtra("phone", current.getPhoneNo());
                intent.putExtra("email", current.getEmail());
                // startActivityForResult(intent, 201);
                activityIntent.launch(intent);
                break;

            case R.id.del:
                current = (Friend) view.getTag();
                new AlertDialog.Builder(this)
                        .setMessage("Do you really want to delete " + current.getName() + " from your friend list?")
                        .setNegativeButton("Yes", (dialogInterface, i) -> {
                            FriendManager friendManager = FriendManager.getInstance(this);
                            friendManager.delete(current.getId());
                            friends.remove(current);
                            friendAdapter.notifyDataSetChanged();
                        })
                        .setPositiveButton("No", null)
                        .show();
                break;
        }
    }

    public List<Friend> loadFriends() {
        // connect db
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = openOrCreateDatabase("friends", MODE_PRIVATE, null);
        // select all friends
        String sql = "SELECT * FROM friends";
        Cursor cursor = db.rawQuery(sql, null);
        // get corresponding column index
        int idIndex = cursor.getColumnIndex("id");
        int nameIndex = cursor.getColumnIndex("name");
        int emailIndex = cursor.getColumnIndex("email");
        int phoneNoIndex = cursor.getColumnIndex("phoneNo");
        // foreach returned record in cursor
        while (cursor.moveToNext()) {
            // get id, name, email, phoneNo
            long id = cursor.getLong(idIndex);
            String name = cursor.getString(nameIndex);
            String email = cursor.getString(emailIndex);
            String phoneNo = cursor.getString(phoneNoIndex);
            // create new friend object
            Friend friend = new Friend(id, name, phoneNo, email);
            // add into friends
            friends.add(friend);
        }
        // close connection
        db.close();
        return friends;
    }
}