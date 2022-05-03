package com.example.pipiceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Basket.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "basketTable";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PHONE_NAME = "phone_name";
    private static final String COLUMN_PRICE_EKUPI = "ekupi_price";
    private static final String COLUMN_PRICE_HGSPOT = "hgspot_price";
    private static final String COLUMN_PRICE_INSTAR = "instar_price";

    public DabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PHONE_NAME + " TEXT, " +
                COLUMN_PRICE_EKUPI + " TEXT, " +
                COLUMN_PRICE_HGSPOT + " TEXT, " +
                COLUMN_PRICE_INSTAR + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void dropTable(SQLiteDatabase db){
        //db.execSQL("DELETE FROM " + TABLE_NAME);
        //ContentValues cv = new ContentValues();
        //cv.clear();
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        //db.delete(TABLE_NAME, COLUMN_ID, )
        //db.delete(TABLE_NAME, null, null);
        Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();

    }

    void addPhone(String phoneName, String priceEkupi, String priceHgspot, String priceInstar){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PHONE_NAME, phoneName);
        cv.put(COLUMN_PRICE_EKUPI, priceEkupi);
        cv.put(COLUMN_PRICE_HGSPOT, priceHgspot);
        cv.put(COLUMN_PRICE_INSTAR, priceInstar);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed to add to basket", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context, "Added to basket", Toast.LENGTH_SHORT).show();
        }
    }
}
