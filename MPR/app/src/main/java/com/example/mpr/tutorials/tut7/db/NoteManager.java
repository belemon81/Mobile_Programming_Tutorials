package com.example.mpr.tutorials.tut7.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.mpr.tutorials.tut7.models.Note;

import java.util.List;

public class NoteManager {
    private static final String INSERT_STMT = "INSERT INTO " + DbSchema.NotesTable.NAME + "(content) VALUES (?)";
    private static final String UPDATE_STMT = "UPDATE " + DbSchema.NotesTable.NAME + " SET content = ? WHERE id = ?";
    private static NoteManager instance;
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    private NoteManager(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static NoteManager getInstance(Context context) {
        if (instance == null) {
            instance = new NoteManager(context);
        }
        return instance;
    }

    public List<Note> all() {
        String sql = "SELECT * FROM notes";
        Cursor cursor = db.rawQuery(sql, null);
        NoteCursorWrapper cursorWrapper = new NoteCursorWrapper(cursor);
        return cursorWrapper.getNotes();
    }

    public Note findById(long id) {
        String sql = "SELECT * FROM notes WHERE id = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        NoteCursorWrapper cursorWrapper = new NoteCursorWrapper(cursor);
        cursorWrapper.moveToNext();
        return cursorWrapper.getNote();
    }

    /**
     * @modifies note
     */
    public boolean add(Note note) {
        SQLiteStatement statement = db.compileStatement(INSERT_STMT);
        statement.bindString(1, note.getContent());
        long id = statement.executeInsert();
        if (id > 0) {
            note.setId(id);
            return true;
        }
        return false;
    }

    /**
     * @modifies note
     */
    public boolean update(Note note) {
        SQLiteStatement statement = db.compileStatement(UPDATE_STMT);
        statement.bindString(1, note.getContent());
        statement.bindLong(2, note.getId());
        int result = statement.executeUpdateDelete();
        return result > 0;
    }

    public boolean delete(long id) {
        int result = db.delete(DbSchema.NotesTable.NAME, "id = ?", new String[]{id + ""});
        return result > 0;
    }
}
