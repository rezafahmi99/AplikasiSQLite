package com.example.aplikasisqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "digitaltalent.db";
    private static final String TABLE_SQLIte = "sqlite";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ADDRESS = "address";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SQLIte + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT,"
                + COLUMN_ADDRESS + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLIte);

        // Create tables again
        onCreate(db);
    }
    public ArrayList<HashMap<String, String>> getAllData(){
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = " SELECT * FROM " + TABLE_SQLIte;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String, String>map = new HashMap<String, String>();
                map.put(COLUMN_ID, cursor.getString( 0));
                map.put(COLUMN_NAME, cursor.getString( 1));
                map.put(COLUMN_ADDRESS, cursor.getString( 2));
            }while (cursor.moveToNext());
        }
        Log.e("select sqlite ", "" +wordList);
        database.close();
        return wordList;
    }

    public void insert(String name, String address){
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = " INSERT INTO " + TABLE_SQLIte + " (name, address) " +
                " VALUES ('" + name + "', '" + address + "')";
        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id, String name, String address){
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_SQLIte + " SET "
                + COLUMN_NAME + "='" + name + "', "
                +COLUMN_ADDRESS + "='" + address + "'"
                + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = " DELETE FROM " + TABLE_SQLIte + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
    }
}
