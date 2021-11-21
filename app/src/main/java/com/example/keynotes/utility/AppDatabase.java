package com.example.keynotes.utility;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.keynotes.utility.AppConstants.DB_NAME;
import static com.example.keynotes.utility.AppConstants.DB_VERSION;

public class AppDatabase extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Notes";
    private static final String COLUMN_ID = "Note_id";
    private static final String COLUMN_NAME = "Note_Name";
    private static final String COLUMN_TITLE = "Note_Title";

    public AppDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_TITLE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
