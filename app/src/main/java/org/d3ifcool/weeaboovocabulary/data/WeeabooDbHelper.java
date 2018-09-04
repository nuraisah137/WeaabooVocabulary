package org.d3ifcool.weeaboovocabulary.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.d3ifcool.weeaboovocabulary.data.WeeabooContract.WeeabooEntry;

/**
 * Created by andinu on 08/04/2018.
 */

public class WeeabooDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shelter.db";
    private static final int DATABASE_VERSION = 1;

    public WeeabooDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_WEEABOO_TABLE_SCH = "CREATE TABLE " + WeeabooEntry.TABLE_NAME_SHEDULE + " (" +
        WeeabooEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        WeeabooEntry.COLUMN_WEEABOO_SCH_HOUR + " TEXT NOT NULL, " +
        WeeabooEntry.COLUMN_WEEABOO_SCH_MINUTE + " TEXT, " +
        WeeabooEntry.COLUMN_WEEABOO_STATUS + " INTEGER);";
        sqLiteDatabase.execSQL(SQL_CREATE_WEEABOO_TABLE_SCH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // The database is still at Version 1,
        // so there's nothing to do here.
    }
}
