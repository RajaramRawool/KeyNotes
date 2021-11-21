package com.example.keynotes.activity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.keynotes.R;
import com.example.keynotes.model.Note;
import com.example.keynotes.util.AppSharedPreferences;
import com.example.keynotes.util.NoteRecViewAdapter;
import com.example.keynotes.util.Utils;
import com.google.gson.Gson;

public class ActivityNote extends AppCompatActivity {

    Spinner noteCategory;
    EditText etNoteTitle;
    EditText etNoteContent;
    AppSharedPreferences appSharedPreferences;
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setValues();
        setViews();
        setSpinner();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_option_save:
                saveNote();
                Toast.makeText(this,"Note Saved",Toast.LENGTH_SHORT).show();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private void saveNote() {
        String noteTitle = etNoteTitle.getText().toString();
        String noteContent = etNoteContent.getText().toString();
        Note note = new Note(noteTitle,noteContent,"Work");
        utils.addNote(note);
        Gson gson = new Gson();
        appSharedPreferences.setNote(gson.toJson(note));
    }

    private void setSpinner() {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(
                ActivityNote.this,
                R.array.note_categories,
                android.R.layout.simple_spinner_item
        );
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        noteCategory.setAdapter(arrayAdapter);
    }

    private void setValues() {
        appSharedPreferences = new AppSharedPreferences(ActivityNote.this);
        utils = new Utils(ActivityNote.this);
    }

    private void setViews() {
        noteCategory = findViewById(R.id.activity_note_spinner);
        etNoteTitle = findViewById(R.id.activity_note_et_note_title);
        etNoteContent = findViewById(R.id.activity_note_et_note_content);
    }
}
