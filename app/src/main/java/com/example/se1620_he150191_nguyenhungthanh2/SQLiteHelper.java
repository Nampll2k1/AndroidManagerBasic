package com.example.se1620_he150191_nguyenhungthanh2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "PracticeDB";
    public static String TABLE_NAME = "bird";
    public static String ID_COLUMN = "id";
    public static String NAME_COLUMN = "name";
    public static String REGION_COLUMN = "region";
    public static String SPECIES_COLUMN = "species";

    String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME+ "("
            + ID_COLUMN +       " INTEGER PRIMARY KEY, "
            + NAME_COLUMN +   " TEXT NOT NULL, "
            + REGION_COLUMN  +    " TEXT NOT NULL, "
            + SPECIES_COLUMN +  " TEXT NOT NULL)";

    public static int VERSION = 1;
    public SQLiteHelper(@Nullable Context context,
                        @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            String DROP_TABLE = "DROP TABLE if EXISTS " + TABLE_NAME;
            db.execSQL(DROP_TABLE);
            db.execSQL(CREATE_TABLE);
        }
    }

}
