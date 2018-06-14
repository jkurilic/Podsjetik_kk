package com.example.mm.podsjetik_k;


import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperL extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "lijeK";
    public static final String TABLE_NAME = "lijeK_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "IMEL";
    public static final String COL3 = "LISTA";
    public static final String COL4 = "CIJENAL";
    public static final String COL5 = "JEDINICE";//JEDINICE U PAKIRANJU
    public static final String COL6 = "PONAVLJANJE";
    public static final String COL7 = "OBLIK";


    public DatabaseHelperL(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase dbL) {
        String createTableL = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " IMEL TEXT, LISTA TEXT, CIJENAL TEXT, JEDINICE TEXT, PONAVLJANJE TEXT, OBLIK TEXT)";
        dbL.execSQL(createTableL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbL, int oldVersion, int newVersion) {
        dbL.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(dbL);
    }

    public boolean addDataL(String imeL, String lista, String cijenaL, String jedinice, String ponavljanje, String oblik){
        SQLiteDatabase dbL = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, imeL);
        contentValues.put(COL3, lista);
        contentValues.put(COL4, cijenaL);
        contentValues.put(COL5, jedinice);
        contentValues.put(COL6, ponavljanje);
        contentValues.put(COL7, oblik);

        long result  = dbL.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor showDataL(){
        SQLiteDatabase dbL = this.getWritableDatabase();
        Cursor dataL = dbL.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return dataL;
    }

    public boolean updateDataL(String id2, String imeL, String lista, String cijenaL, String jedinice, String ponavljanje, String oblik){
        SQLiteDatabase dbL = this.getWritableDatabase();
        ContentValues contentValuesL = new ContentValues();
        contentValuesL.put(COL1, id2);
        contentValuesL.put(COL2, imeL);
        contentValuesL.put(COL3, lista);
        contentValuesL.put(COL4, cijenaL);
        contentValuesL.put(COL5, jedinice);
        contentValuesL.put(COL6, ponavljanje);
        contentValuesL.put(COL7, oblik);
        dbL.update(TABLE_NAME, contentValuesL, "ID = ?", new String[] {id2});
        return true;
    }

    public Integer deleteDataL(String id2){
        SQLiteDatabase dbL = this.getWritableDatabase();
        return dbL.delete(TABLE_NAME, "ID = ?", new String[] {id2});
    }



}
