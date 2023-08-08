package com.example.mpr.tutorials.tut5_db.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.mpr.tutorials.tut5_db.models.Friend;

import java.util.ArrayList;
import java.util.List;

public class FriendCursorWrapper extends CursorWrapper {
    public FriendCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Friend getFriend() {
        long id = getLong(getColumnIndex(DbSchema.FriendsTable.Cols.ID));
        String name = getString(getColumnIndex(DbSchema.FriendsTable.Cols.NAME));
        String phoneNo = getString(getColumnIndex(DbSchema.FriendsTable.Cols.PHONENO));
        String email = getString(getColumnIndex(DbSchema.FriendsTable.Cols.EMAIL));
        Friend friend = new Friend(id, name, email, phoneNo);
        return friend;
    }

    public List<Friend> getFriends() {
        List<Friend> friends = new ArrayList<>();
        moveToFirst();
        while (!isAfterLast()) {
            Friend friend = getFriend();
            friends.add(friend);
            moveToNext();
        }
        return friends;
    }
}
