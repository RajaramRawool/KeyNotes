package com.example.keynotes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keynotes.R;
import com.example.keynotes.model.Note;
import com.example.keynotes.util.AppSharedPreferences;
import com.example.keynotes.util.NoteRecViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static com.example.keynotes.util.AppConstants.NOTE_REQUEST_CODE;

public class ActivityHomePage extends AppCompatActivity {
    AppSharedPreferences appSharedPreferences;
    FloatingActionButton fabAdd;
    RecyclerView recViewNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        setValues();
        setViews();
        setRecView();
        setListeners();
    }

    private void setRecView() {
        Note note = appSharedPreferences.getNote();
        NoteRecViewAdapter noteRecViewAdapter = new NoteRecViewAdapter(this);
        if (note != null) {
            noteRecViewAdapter.addNote(note);
        }
        recViewNotes.setAdapter(noteRecViewAdapter);
        recViewNotes.setLayoutManager(new LinearLayoutManager(this));
        noteRecViewAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        NoteRecViewAdapter noteRecViewAdapter = new NoteRecViewAdapter(this);
        noteRecViewAdapter.notifyDataSetChanged();

    }

    private void setListeners() {
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(ActivityHomePage.this,ActivityNote.class));
            }
        });

    }


    private void setViews() {

        fabAdd = findViewById(R.id.activity_homepage_fab_add);
        recViewNotes = findViewById(R.id.rec_view_notes);
    }

    private void setValues() {
        appSharedPreferences = new AppSharedPreferences(ActivityHomePage.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_option_logout :
                appSharedPreferences.setUserSession(false);
                finish();
                startActivity(new Intent(ActivityHomePage.this,ActivityLogin.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
