package com.example.mpr.tutorials.tut7;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mpr.R;
import com.example.mpr.tutorials.tut7.db.NoteManager;
import com.example.mpr.tutorials.tut7.models.Note;

public class Tut7_add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tut7_add);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Do you want to go back?")
                .setNegativeButton("Yes", (dialogInterface, i) -> {
                    EditText contentField = findViewById(R.id.content);
                    String content = contentField.getText().toString();
                    if (!content.trim().equals("")) {
                        NoteManager noteManager = NoteManager.getInstance(this);
                        Note note = new Note(content);
                        noteManager.add(note);
                        setResult(RESULT_OK);
                    } else {
                        setResult(RESULT_CANCELED);
                    }
                    finish();
                    super.onBackPressed();
                })
                .setPositiveButton("No", null)
                .show();
    }
}