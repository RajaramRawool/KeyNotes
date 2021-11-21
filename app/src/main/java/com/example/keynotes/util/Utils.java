package com.example.keynotes.util;

import android.content.Context;
import android.widget.Toast;

import com.example.keynotes.model.Note;

import java.util.ArrayList;

public class Utils {
    private Context context;
    ArrayList<Note> notes;
    public Utils(Context context) {
        this.context = context;
        notes = new ArrayList<>();
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public boolean addNote(Note note) {
        if (this.notes.add(note)) {
            Toast.makeText(this.context, "Note Added Successfully",Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this.context, "Problem Adding Note",Toast.LENGTH_SHORT).show();
            return false;
        }

    }

}
