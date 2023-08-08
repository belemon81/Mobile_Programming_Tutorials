package com.example.mpr.tutorials.tut5;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mpr.R;
import com.example.mpr.tutorials.tut5.adapters.FriendAdapter;
import com.example.mpr.tutorials.tut5.models.Friend;

import java.util.ArrayList;
import java.util.List;

public class Tut5_main extends AppCompatActivity {
    private List<Friend> friends = new ArrayList<>();
    private FriendAdapter friendAdapter;
    private ActivityResultLauncher<Intent> addActivityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    friends.add(new Friend((String) (data != null ? data.getSerializableExtra("name") : null), (String) (data != null ? data.getSerializableExtra("email") : null), (String) (data != null ? data.getSerializableExtra("phone") : null)));
                    friendAdapter.notifyDataSetChanged();
                }
            });
    private ActivityResultLauncher<Intent> editActivityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    friends.set(data != null ? data.getIntExtra("pos", 0) : 0, new Friend((String) (data != null ? data.getSerializableExtra("name") : null), (String) (data != null ? data.getSerializableExtra("email") : null), (String) (data != null ? data.getSerializableExtra("phone") : null)));
                    friendAdapter.notifyDataSetChanged();
                }
            });

    {
        friends.add(new Friend("Esther", "esther@gmail.com", "1234567890"));
        friends.add(new Friend("Vincent", "vincent@gmail.com", "1234567890"));
        friends.add(new Friend("Claire", "claire@gmail.com", "1234567890"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tut5_main);
        // recycle view config
        RecyclerView rvFriends = findViewById(R.id.rvFriend);
        //set layout manager
        rvFriends.setLayoutManager(new LinearLayoutManager(this));
        // bridge dataset -.recycle view = adapter
        friendAdapter = new FriendAdapter(friends);
        rvFriends.setAdapter(friendAdapter);
    }

    public void addFriend(View view) {
        Intent intent = new Intent(this, Tut5_add.class);
        addActivityIntent.launch(intent);
    }

    @SuppressLint("NonConstantResourceId")
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
                intent.putExtra("pos", friends.indexOf(current));
                intent.putExtra("name", current.getName());
                intent.putExtra("phone", current.getPhoneNo());
                intent.putExtra("email", current.getEmail());
                // startActivityForResult(intent, 201);
                editActivityIntent.launch(intent);
                break;

            case R.id.del:
                current = (Friend) view.getTag();
                new AlertDialog.Builder(this)
                        .setMessage("Do you really want to delete " + current.getName() + " from your friend list?")
                        .setNegativeButton("Yes", (dialogInterface, i) -> {
                            friendAdapter.notifyItemRemoved(friends.indexOf(current));
                            friends.remove(current);
                        })
                        .setPositiveButton("No", null)
                        .show();
                break;
        }
    }
}