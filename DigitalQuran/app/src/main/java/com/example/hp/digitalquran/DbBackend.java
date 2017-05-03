package com.example.hp.digitalquran;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class DbBackend extends DbObject {

    public DbBackend(Context context) {
        super(context);
    }

    public String[] dictionaryWords() {
        String query = "Select * from Surah_Names";
        Cursor cursor = this.getDbConnection().rawQuery(query, null);
        ArrayList<String> wordTerms = new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {
                String word = cursor.getString(cursor.getColumnIndexOrThrow("names"));
                wordTerms.add(word);
            } while (cursor.moveToNext());
        }
        cursor.close();
        String[] dictionaryWords = new String[wordTerms.size()];
        dictionaryWords = wordTerms.toArray(dictionaryWords);
        return dictionaryWords;
    }
}