package com.example.mpr.tutorials.tut7;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mpr.R;
import com.example.mpr.tutorials.tut7.db.NoteManager;
import com.example.mpr.tutorials.tut7.models.Note;

public class Tut7_edit extends AppCompatActivity {
    private EditText contentField;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tut7_edit);
        contentField = findViewById(R.id.content2);
        NoteManager noteManager = NoteManager.getInstance(this);
        note = noteManager.findById(getIntent().getLongExtra("id", 0));
        contentField.setText(note.getContent());
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Do you want to exit?")
                .setNegativeButton("Yes", (dialogInterface, i) -> {
                    String content = contentField.getText().toString();
                    if (!content.trim().isEmpty()) {
                        note.setContent(content);
                        NoteManager noteManager = NoteManager.getInstance(this);
                        noteManager.update(note);
                        setResult(RESULT_OK);
                    } else {
                        setResult(204, new Intent().putExtra("id", note.getId()));
                    }
                    finish();
                    super.onBackPressed();
                })
                .setPositiveButton("No", null)
                .show();
    }
}