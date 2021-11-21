package com.example.keynotes.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keynotes.R;
import com.example.keynotes.model.Note;

import java.nio.channels.NotYetBoundException;
import java.util.ArrayList;

public class NoteRecViewAdapter extends RecyclerView.Adapter<NoteRecViewAdapter.noteViewHolder> {
    private Context context;
    private ArrayList<Note> notes = new ArrayList<>();


    public NoteRecViewAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public boolean addNote(Note note) {
        if (this.notes.add(note)) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public noteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.note_item,parent,false);
        return new noteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteRecViewAdapter.noteViewHolder holder, int position) {

        holder.tvNoteTitle.setText(this.notes.get(position).getTitle());
        holder.tvNoteContent.setText(this.notes.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class noteViewHolder extends RecyclerView.ViewHolder {
        TextView tvNoteTitle, tvNoteContent;
        public noteViewHolder(View itemView) {
            super(itemView);
            tvNoteTitle = itemView.findViewById(R.id.tv_note_title);
            tvNoteContent = itemView.findViewById(R.id.tv_note_content);
        }
    }

}
