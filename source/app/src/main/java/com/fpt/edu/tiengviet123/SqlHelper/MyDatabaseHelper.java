package com.fpt.edu.tiengviet123.SqlHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.fpt.edu.tiengviet123.bean.VCharacter;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CharacterDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Characters";
    private static final String COLUME_ID = "_id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_CHARACTER_CONTENT = "Content";
    private static final String COLUMN_IMAGE_SOURCE_ID = "ImageScrId";
    private static final String COLUMN_BASE_AUDIO_SOURCE_ID = "BaseAudioScrId";
    private static final String COLUMN_EXTENSION_AUDIO_SOURCE_ID = "ExtensionAudioScrId";

    final static String TAG = "MYSQLHEPLER";

    private static final String SCRIPT_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + COLUME_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT," + COLUMN_CHARACTER_CONTENT + " TEXT," + COLUMN_IMAGE_SOURCE_ID + " INTEGER," + COLUMN_BASE_AUDIO_SOURCE_ID + " INTEGER, " + COLUMN_EXTENSION_AUDIO_SOURCE_ID + " INTEGER )";

    SQLiteDatabase database = this.getReadableDatabase();

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "OnCreate Database");
        Log.i(TAG, SCRIPT_CREATE);
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

        }

    }

    public int getNodteCount() {
        Log.i(TAG, "Get note count");

        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = database.rawQuery(query, null);
        return cursor.getCount();
    }

    public void addNote(VCharacter node) {
        Log.i(TAG, "Adding  a note");
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, node.getFace());
        contentValues.put(COLUMN_IMAGE_SOURCE_ID, node.getContent());
        contentValues.put(COLUMN_IMAGE_SOURCE_ID, node.getImageSrcId());
        contentValues.put(COLUMN_BASE_AUDIO_SOURCE_ID, node.getBaseAudioId());
        contentValues.put(COLUMN_EXTENSION_AUDIO_SOURCE_ID, node.getExtentionAudioID());
        database.insert(TABLE_NAME, null, contentValues);
    }

    public VCharacter getVCharacter(String name) {
        Log.i(TAG, "GET vChar ");
        Cursor cursor = database.query(TABLE_NAME, new String[]{COLUME_ID, COLUMN_NAME, COLUMN_CHARACTER_CONTENT, COLUMN_IMAGE_SOURCE_ID, COLUMN_BASE_AUDIO_SOURCE_ID, COLUMN_EXTENSION_AUDIO_SOURCE_ID}, COLUMN_NAME + "=?", new String[]{String.valueOf(name)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            VCharacter node = new VCharacter(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5));
            return node;
        }
        return null;
    }

    public List<VCharacter> getAllNote() {

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);
        ArrayList<VCharacter> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            VCharacter node = new VCharacter(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5));
            list.add(node);
        }
        return list;
    }

    public boolean update(VCharacter node) {
        Log.i(TAG, "MyDatabaseHelper. Update Character ... " + node.getFace());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, node.getFace());
        contentValues.put(COLUMN_IMAGE_SOURCE_ID, node.getContent());
        contentValues.put(COLUMN_IMAGE_SOURCE_ID, node.getImageSrcId());
        contentValues.put(COLUMN_BASE_AUDIO_SOURCE_ID, node.getBaseAudioId());
        contentValues.put(COLUMN_EXTENSION_AUDIO_SOURCE_ID, node.getExtentionAudioID());

        // updating row
        return db.update(TABLE_NAME, contentValues, COLUME_ID + " = ?",
                new String[]{String.valueOf(node.getId())})>0;

    }

    public void deleteNote(VCharacter note) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + note.getFace() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUME_ID + " = ?",
                new String[] { String.valueOf(note.getId()) });
        db.close();
    }

}
