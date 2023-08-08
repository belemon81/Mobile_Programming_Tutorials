package com.example.mpr.tutorials.tut7.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mpr.R;
import com.example.mpr.tutorials.tut7.Tut7_edit;
import com.example.mpr.tutorials.tut7.db.NoteManager;
import com.example.mpr.tutorials.tut7.models.Note;

import java.io.Serializable;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> implements Serializable {
    private List<Note> notes;
    private Context context;
    private ActivityResultLauncher<Intent> activityIntent;

    public NoteAdapter(List<Note> notes, Context context, ActivityResultLauncher<Intent> activityIntent) {
        this.context = context;
        this.notes = notes;
        this.activityIntent = activityIntent;
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.tut7_item_note, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note note = notes.get(position);
        holder.bind(note);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, Tut7_edit.class);
            intent.putExtra("id", note.getId());
            activityIntent.launch(intent);
        });
        holder.itemView.setOnLongClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setMessage("Confirm?")
                    .setNegativeButton("Yes", (dialogInterface, i) -> {
                        NoteManager noteManager = NoteManager.getInstance(context);
                        noteManager.delete(note.getId());
                        notes.remove(note);
                        notifyDataSetChanged();
                    })
                    .setPositiveButton("No", null)
                    .show();
            return true;
        });
    }

    public class NoteHolder extends RecyclerView.ViewHolder {

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Note note) {
            TextView noteContent = itemView.findViewById(R.id.note);
            noteContent.setText(note.getContent());
            ImageView icon = itemView.findViewById(R.id.icon);
            if (note.getId() % 7 == 0) icon.setImageResource(R.drawable.panda);
            if (note.getId() % 7 == 1) icon.setImageResource(R.drawable.rabbit);
            if (note.getId() % 7 == 2) icon.setImageResource(R.drawable.owl);
            if (note.getId() % 7 == 3) icon.setImageResource(R.drawable.rabbit2);
            if (note.getId() % 7 == 4) icon.setImageResource(R.drawable.penguin);
            if (note.getId() % 7 == 6) icon.setImageResource(R.drawable.rabbit3);
        }
    }
}
