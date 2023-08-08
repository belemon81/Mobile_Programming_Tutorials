package com.example.mpr.tutorials.tut5_db.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.mpr.tutorials.tut5_db.models.Friend;

import java.util.List;

public class FriendManager {
    private static final String INSERT_STMT = "INSERT INTO " + DbSchema.FriendsTable.NAME + "(name, phoneNo, email) VALUES (?, ?, ?)";
    private static final String UPDATE_STMT = "UPDATE " + DbSchema.FriendsTable.NAME + " SET name = ?, phoneNo = ?, email = ? WHERE id = ?";
    // singleton
    private static FriendManager instance;
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    private FriendManager(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static FriendManager getInstance(Context context) {
        if (instance == null) instance = new FriendManager(context);
        return instance;
    }

    public List<Friend> all() {
        String sql = "SELECT * FROM friends";
        Cursor cursor = db.rawQuery(sql, null);
        FriendCursorWrapper cursorWrapper = new FriendCursorWrapper(cursor);
        return cursorWrapper.getFriends();
    }

    /**
     * @modifies friend
     */
    public boolean add(Friend friend) {
        SQLiteStatement statement = db.compileStatement(INSERT_STMT);
        statement.bindString(1, friend.getName());
        statement.bindString(2, friend.getEmail());
        statement.bindString(3, friend.getPhoneNo());
        long id = statement.executeInsert();
        if (id > 0) {
            friend.setId(id);
            return true;
        }
        return false;
    }

    /**
     * @modifies friend
     */
    public boolean update(long id, Friend friend) {
        SQLiteStatement statement = db.compileStatement(UPDATE_STMT);
        statement.bindString(1, friend.getName());
        statement.bindString(2, friend.getEmail());
        statement.bindString(3, friend.getPhoneNo());
        statement.bindLong(4, id);
        int result = statement.executeUpdateDelete();
        return result > 0;
    }

    /**
     * @modifies friend
     */
    public boolean delete(long id) {
        int result = db.delete(DbSchema.FriendsTable.NAME, "id = ?", new String[]{id + ""});
        return result > 0;
    }
}
