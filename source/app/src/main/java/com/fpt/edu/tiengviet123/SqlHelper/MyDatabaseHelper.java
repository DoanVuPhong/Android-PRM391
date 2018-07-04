package com.fpt.edu.tiengviet123.SqlHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.fpt.edu.sqlitedemo.bean.Note;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "NoteDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Notes";
    private static final String COLUME_NOTE_ID = "_id";
    private static final String COLUMN_NOTE_TITLE = "Note_Title";
    private static final String COLUMN_NOTE_CONTENT = "Note_Content";
    final static String TAG = "MYSQLHEPLER";

    private static final String SCRIPT_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + COLUME_NOTE_ID + " INTEGER PRIMARY KEY," + COLUMN_NOTE_TITLE + " TEXT," + COLUMN_NOTE_CONTENT + " TEXT"+")";

    SQLiteDatabase database = this.getReadableDatabase();

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "OnCreate Database");
        Log.i(TAG,SCRIPT_CREATE);
        db.execSQL(SCRIPT_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "On Update Database");
        String script = "DROP TABLE IF EXISTS" + TABLE_NAME;
        onCreate(db);
    }

    public void insertDefaultNote() {
        if (this.getNodteCount() == 0) {
            Note n1 = new Note("TEST1", "this is test for default create note");
            Note n2 = new Note("TEST 2", "This is another test content for create default note");
            addNote(n1);
            addNote(n2);
        }

    }

    public int getNodteCount() {
        Log.i(TAG, "Get note count");

        String query = "SELECT * FROM Notes";
        Cursor cursor = database.rawQuery(query, null);
        return cursor.getCount();
    }

    public void addNote(Note note) {
        Log.i(TAG, "Adding  a note");
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOTE_CONTENT, note.getContent());
        contentValues.put(COLUMN_NOTE_TITLE, note.getTitle());
        database.insert(TABLE_NAME, null, contentValues);
    }

    public Note getNote(int id) {
        Log.i(TAG, "GET note ");
        Cursor cursor = database.query(TABLE_NAME, new String[]{COLUME_NOTE_ID, COLUMN_NOTE_TITLE, COLUMN_NOTE_CONTENT}, COLUME_NOTE_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Note note = new Note(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            return note;
        }
        return null;
    }

    public List<Note> getAllNote() {

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);
        ArrayList<Note> list = new ArrayList<>();
        while (cursor.moveToNext()) {

            Note note = new Note(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            list.add(note);
        }
        return list;
    }

    public boolean update(Note note) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + note.getTitle());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_TITLE, note.getTitle());
        values.put(COLUMN_NOTE_CONTENT, note.getContent());

        // updating row
        return db.update(TABLE_NAME, values, COLUME_NOTE_ID + " = ?",
                new String[]{String.valueOf(note.getNoteId())})>0;


    }
    public void deleteNote(Note note) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + note.getTitle() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUME_NOTE_ID + " = ?",
                new String[] { String.valueOf(note.getNoteId()) });
        db.close();
    }

}
