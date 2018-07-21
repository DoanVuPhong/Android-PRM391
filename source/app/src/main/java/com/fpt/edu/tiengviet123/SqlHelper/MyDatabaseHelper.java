package com.fpt.edu.tiengviet123.SqlHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.fpt.edu.tiengviet123.R;
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
            VCharacter a = new VCharacter("a", "Con Cá", R.drawable.a, R.raw.a, R.raw.conca);
            VCharacter a8 = new VCharacter("ă", "Mặt Trăng", R.drawable.a8, R.raw.a8, R.raw.ongtrang);
            VCharacter a6 = new VCharacter("â", "Cái Cân", R.drawable.a6, R.raw.a6, R.raw.caican);
            VCharacter b = new VCharacter("b", "Quả bóng", R.drawable.b, R.raw.b, R.raw.quabong);
            VCharacter c = new VCharacter("c", "Cà chua", R.drawable.c, R.raw.c, R.raw.cachua);
            VCharacter d = new VCharacter("d", "dâu tây", R.drawable.d, R.raw.d, R.raw.dautay);
            VCharacter d9 = new VCharacter("đ", "Đu đủ", R.drawable.d9, R.raw.d9, R.raw.dudu);
            VCharacter e = new VCharacter("e", "Ve sầu", R.drawable.e, R.raw.e, R.raw.vesau);
            VCharacter e6 = new VCharacter("ê", "Con dê", R.drawable.e6, R.raw.e6, R.raw.conde);
            VCharacter g = new VCharacter("g", "Con gà", R.drawable.g, R.raw.g, R.raw.conga);
            VCharacter h = new VCharacter("h", "Hoa", R.drawable.h, R.raw.h, R.raw.hoa);
            VCharacter i = new VCharacter("i", "Tivi", R.drawable.i, R.raw.i, R.raw.tivi);
            VCharacter k = new VCharacter("k", "Kéo", R.drawable.k, R.raw.k, R.raw.caikeo);
            VCharacter l = new VCharacter("l", "Quả lê", R.drawable.l, R.raw.l, R.raw.quale);
            VCharacter m = new VCharacter("m", "Quả me", R.drawable.m, R.raw.m, R.raw.quame);
            VCharacter n = new VCharacter("n", "Cây Núm", R.drawable.n, R.raw.n, R.raw.caynam);
            VCharacter o = new VCharacter("o", "con ong", R.drawable.o, R.raw.o, R.raw.conong);
            VCharacter o6 = new VCharacter("ô", "Ô tô", R.drawable.o6, R.raw.o6, R.raw.oto);
            VCharacter o7 = new VCharacter("ơ", "Cái Nơ", R.drawable.o7, R.raw.o7, R.raw.caino);
            VCharacter p = new VCharacter("p", "Pin", R.drawable.p, R.raw.p, R.raw.pin);
            VCharacter q = new VCharacter("q", "Quà", R.drawable.q, R.raw.q, R.raw.qua);
            VCharacter r = new VCharacter("r", "Con rùa", R.drawable.r, R.raw.r, R.raw.conrua);
            VCharacter s = new VCharacter("s", "Con Sâu", R.drawable.s, R.raw.s, R.raw.consau);
            VCharacter t = new VCharacter("t", "Trái táo", R.drawable.t, R.raw.t, R.raw.quatao);
            VCharacter u = new VCharacter("u", "Cây dù", R.drawable.u, R.raw.u, R.raw.caydu);
            VCharacter u7 = new VCharacter("ư", "Bình mực", R.drawable.u7, R.raw.u7, R.raw.binhmuc);
            VCharacter v = new VCharacter("v", "Con voi", R.drawable.v, R.raw.v, R.raw.convoi);
            VCharacter x = new VCharacter("x", "Xe đạp", R.drawable.x, R.raw.x, R.raw.xedap);
            VCharacter y = new VCharacter("y", "Y tá", R.drawable.y, R.raw.y, R.raw.yta);

            addNote(a);
            addNote(a6);
            addNote(a8);
            addNote(b);
            addNote(c);
            addNote(d);
            addNote(d9);
            addNote(e);
            addNote(e6);
            addNote(g);
            addNote(h);
            addNote(i);
            addNote(k);
            addNote(l);
            addNote(m);
            addNote(n);
            addNote(o);
            addNote(o6);
            addNote(o7);
            addNote(p);
            addNote(q);
            addNote(r);
            addNote(s);
            addNote(t);
            addNote(v);
            addNote(u);
            addNote(u7);
            addNote(x);
            addNote(y);

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
        contentValues.put(COLUMN_CHARACTER_CONTENT, node.getContent());
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
        boolean result = db.update(TABLE_NAME, contentValues, COLUME_ID + " = ?",
                new String[]{String.valueOf(node.getId())}) > 0;
        // updating row
        db.close();
        return result;


    }

    public void deleteNote(VCharacter note) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + note.getFace());

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUME_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }

}
