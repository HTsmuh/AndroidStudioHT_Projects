package com.example.hp.digitalquran;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DbObject {

    private static SurahNameDatabase dbHelper;
    private SQLiteDatabase db;

    public DbObject(Context context) {
        dbHelper = new SurahNameDatabase(context);
        this.db = dbHelper.getReadableDatabase();
    }

    public SQLiteDatabase getDbConnection(){
        return this.db;
    }

    public void closeDbConnection(){
        if(this.db != null){
            this.db.close();
        }
    }
}

