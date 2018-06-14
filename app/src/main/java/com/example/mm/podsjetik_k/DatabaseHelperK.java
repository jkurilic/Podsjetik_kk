package com.example.mm.podsjetik_k;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperK extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "kupovin";
    public static final String TABLE_NAME = "kupovin_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "IME";
    public static final String COL3 = "DATUM";
    public static final String COL4 = "VRIJEME";
    public static final String COL5 = "CIJENA";


    public DatabaseHelperK(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " IME TEXT, DATUM TEXT, VRIJEME TEXT, CIJENA TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String ime, String datum, String vrijeme, String cijena){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, ime);
        contentValues.put(COL3, datum);
        contentValues.put(COL4, vrijeme);
        contentValues.put(COL5, cijena);

        long result  = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor showData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public boolean updateData(String id, String ime, String datum, String vrijeme, String cijena){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2,ime);
        contentValues.put(COL3,datum);
        contentValues.put(COL4, vrijeme);
        contentValues.put(COL5, cijena);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }
}
