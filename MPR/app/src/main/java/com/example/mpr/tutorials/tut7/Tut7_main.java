package com.example.mpr.tutorials.tut7;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mpr.R;
import com.example.mpr.tutorials.tut7.adapters.NoteAdapter;
import com.example.mpr.tutorials.tut7.db.NoteManager;
import com.example.mpr.tutorials.tut7.models.Note;

import java.util.ArrayList;
import java.util.List;

public class Tut7_main extends AppCompatActivity {
    private List<Note> notes = new ArrayList<>();
    private NoteAdapter noteAdapter;
    private NoteManager noteManager;
    private ActivityResultLauncher<Intent> activityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    notes.clear();
                    notes.addAll(noteManager.all());
                    noteAdapter.notifyDataSetChanged();
                } else if (result.getResultCode() == 204) {
                    NoteManager noteManager = NoteManager.getInstance(this);
                    noteManager.delete(result.getData().getLongExtra("id", 0));
                    notes.clear();
                    notes.addAll(noteManager.all());
                    noteAdapter.notifyDataSetChanged();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tut7_main);

        noteManager = NoteManager.getInstance(this);
        notes = noteManager.all();

        RecyclerView rvNotes = findViewById(R.id.rvNote);
        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(notes, this, activityIntent);
        rvNotes.setAdapter(noteAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.note_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.addNote) {
            Intent intent = new Intent(this, Tut7_add.class);
            activityIntent.launch(intent);
            return true;
        }
        return false;
    }
}