package com.example.mpr.tutorials.tut5_db.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "friends.db";
    private static final int DB_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * @effect if db not exist
     * create tables for 1st run
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE friends (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "email TEXT, " + "phoneNo TEXT)");
        // add dome demo data
        db.execSQL("INSERT INTO friends (name, email, phoneNo) VALUES ('Esther', 'esther@gmail.com', '9999999999')");
        db.execSQL("INSERT INTO friends (name, email, phoneNo) VALUES ('Victor', 'victor@gmail.com', '9999999999')");
        db.execSQL("INSERT INTO friends (name, email, phoneNo) VALUES ('Claire', 'claire@gmail.com', '9999999999')");
        db.execSQL("INSERT INTO friends (name, email, phoneNo) VALUES ('Samuel', 'samuel@gmail.com', '9999999999')");
    }

    /**
     * @effect check db version id != app db version
     * update db (create new tables, alter existing tables)
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS friends");
        onCreate(db);
    }
}
