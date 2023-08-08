package com.example.mpr.tutorials.tut7.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.mpr.tutorials.tut7.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteCursorWrapper extends CursorWrapper {

    // super inherit constructor
    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Note getNote() {
        long id = getLong(getColumnIndex(DbSchema.NotesTable.Cols.ID));
        String content = getString(getColumnIndex(DbSchema.NotesTable.Cols.CONTENT));
        Note note = new Note(id, content);
        return note;
    }

    public List<Note> getNotes() {
        List<Note> notes = new ArrayList<>();
        moveToFirst();
        while (!isAfterLast()) {
            Note note = getNote();
            notes.add(note);
            moveToNext();
        }
        return notes;
    }
}
