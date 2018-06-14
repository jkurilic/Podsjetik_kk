package com.example.mm.podsjetik_k;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperP extends SQLiteOpenHelper {
/*
    public static final String DATABASE_NAME = "podsjetnip";
    public static final String TABLE_NAME = "podsjetnip_table";
    public static final String COL1 = "ID3";
    public static final String COL2 = "LIJEK";
    public static final String COL3 = "DATUMP";//datum pocetka podsjetnika
    public static final String COL4 = "BROJ";//BROJ UZIMANJA
    public static final String COL5 = "VRIJEMEP";//VRIJEME PODSJETNIKA
    public static final String COL6 = "TRAJANJE";
    public static final String COL7 = "DANI";//broj dana podsjetnika


    public DatabaseHelperP(Context contextP) {
        super(contextP, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase dbP) {
        String createTableP = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " LIJEK TEXT, DATUM TEXT, BROJ TEXT, VRIJEME TEXT, TRAJANJE TEXT, DANI TEXT)";
        dbP.execSQL(createTableP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbP, int oldVersion, int newVersion) {
        dbP.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(dbP);
    }

    public boolean addDataP(String lijek, String datumP, String broj, String vrijemeP, String trajanje, String dani){
        SQLiteDatabase dbP = this.getWritableDatabase();
        ContentValues contentValuesP = new ContentValues();
        contentValuesP.put(COL2,lijek);
        contentValuesP.put(COL3,datumP);
        contentValuesP.put(COL4, broj);
        contentValuesP.put(COL5, vrijemeP);
        contentValuesP.put(COL6, trajanje);
        contentValuesP.put(COL7, dani);

        long result  = dbP.insert(TABLE_NAME, null, contentValuesP);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor showDataP(){
        SQLiteDatabase dbP = this.getWritableDatabase();
        Cursor dataP = dbP.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return dataP;
    }

    public boolean updateDataP(String id, String lijek, String datumP, String broj, String vrijemeP, String trajanje, String dani){
        SQLiteDatabase dbP = this.getWritableDatabase();
        ContentValues contentValuesPP = new ContentValues();
        contentValuesPP.put(COL1,id);
        contentValuesPP.put(COL2,lijek);
        contentValuesPP.put(COL3,datumP);
        contentValuesPP.put(COL4, broj);
        contentValuesPP.put(COL5, vrijemeP);
        contentValuesPP.put(COL6, trajanje);
        contentValuesPP.put(COL7, dani);
        dbP.update(TABLE_NAME, contentValuesPP, "ID3 = ?", new String[] {id});
        return true;
    }

    public Integer deleteDataP(String id){
        SQLiteDatabase dbP = this.getWritableDatabase();
        return dbP.delete(TABLE_NAME, "ID3 = ?", new String[] {id});
    }



}*/

 public static final String DATABASE_NAME = "podsjetnikL";
    public static final String TABLE_NAME = "tablica_podsjetnikL";
    public static final String COL1 = "ID";
    public static final String COL2 = "LIJEK";
    public static final String COL3 = "DATUMP";
    public static final String COL4 = "BROJP";
    public static final String COL5 = "VRIJEMEP";//VRIJEME PODSJETNIKA
    public static final String COL6 = "TRAJANJE";
    public static final String COL7 = "DANI";//broj dana podsjetnika

    public DatabaseHelperP(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db3) {
        String createTable3 = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " LIJEK TEXT, DATUMP TEXT, BROJP TEXT, VRIJEMEP TEXT, TRAJANJE TEXT, DANI TEXT)";
        db3.execSQL(createTable3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db3, int oldVersion, int newVersion) {
        db3.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db3);
    }

    public boolean addData3(String lijek, String datump, String brojp, String vrijemep, String trajanjep, String danip){
        SQLiteDatabase db32 = this.getWritableDatabase();
        ContentValues contentValues3 = new ContentValues();
        contentValues3.put(COL2,lijek);
        contentValues3.put(COL3,datump);
        contentValues3.put(COL4,brojp);
        contentValues3.put(COL5,vrijemep);
        contentValues3.put(COL6,trajanjep);
        contentValues3.put(COL7,danip);

        long result2  = db32.insert(TABLE_NAME, null, contentValues3);

        if(result2 == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor showData3(){
        SQLiteDatabase db3 = this.getWritableDatabase();
        Cursor data33 = db3.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data33;
    }

    public boolean updateData3(String id, String lijek, String datumpp, String brojp, String vrijemep, String trajanjep, String danip){
        SQLiteDatabase db34 = this.getWritableDatabase();
        ContentValues contentValues33 = new ContentValues();
        contentValues33.put(COL1,id);
        contentValues33.put(COL2,lijek);
        contentValues33.put(COL3,datumpp);
        contentValues33.put(COL4,brojp);
        contentValues33.put(COL5,vrijemep);
        contentValues33.put(COL6,trajanjep);
        contentValues33.put(COL7, danip);
        db34.update(TABLE_NAME, contentValues33, "ID = ?", new String[] {id});
        return true;
    }

    public Integer deleteData3(String id){
        SQLiteDatabase db35 = this.getWritableDatabase();
        return db35.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }

}


