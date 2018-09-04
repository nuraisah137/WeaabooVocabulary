package org.d3ifcool.weeaboovocabulary.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import org.d3ifcool.weeaboovocabulary.data.WeeabooContract.WeeabooEntry;

/**
 * Created by andinu on 08/04/2018.
 */

public class WeeabooProvider extends ContentProvider {

    public static final String LOG_TAG = WeeabooProvider.class.getSimpleName();

    private static final int WEEABOO = 100;
    private static final int WEEABOO_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(WeeabooContract.CONTENT_AUTHORITY, WeeabooContract.PATH_WEEABOO, WEEABOO);

        sUriMatcher.addURI(WeeabooContract.CONTENT_AUTHORITY, WeeabooContract.PATH_WEEABOO + "/#", WEEABOO_ID);
    }

    private WeeabooDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new WeeabooDbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case WEEABOO:
                cursor = database.query(WeeabooEntry.TABLE_NAME_SHEDULE, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case WEEABOO_ID:
                selection = WeeabooEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(WeeabooEntry.TABLE_NAME_SHEDULE, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    private Uri insertSchedule(Uri uri, ContentValues values){
        //check namanya kosong atau engga
        Integer hour = values.getAsInteger(WeeabooEntry.COLUMN_WEEABOO_SCH_HOUR);
        if (hour != null && hour < 0 || hour > 24){
            throw new IllegalArgumentException("Requires valid hour");
        }

        Integer minute = values.getAsInteger(WeeabooEntry.COLUMN_WEEABOO_SCH_MINUTE);
        if (minute != null && minute < 0 || minute > 59){
            throw new IllegalArgumentException("Requires valid minute");
        }


        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        long id = database.insert(WeeabooEntry.TABLE_NAME_SHEDULE, null, values);

        if (id == -1){
            Log.e(LOG_TAG, "Failed insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match){
            case WEEABOO:
                return insertSchedule(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match){
            case WEEABOO:
                return WeeabooEntry.CONTENT_LIST_TYPE;
            case WEEABOO_ID:
                return WeeabooEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        final int match = sUriMatcher.match(uri);
        switch (match){
            case WEEABOO:
                return database.delete(WeeabooEntry.TABLE_NAME_SHEDULE, selection, selectionArgs);
            case WEEABOO_ID:
                selection = WeeabooEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return database.delete(WeeabooEntry.TABLE_NAME_SHEDULE, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
    }

    private int updateSchedule(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        if (values.containsKey(WeeabooEntry.COLUMN_WEEABOO_SCH_HOUR)){
            Integer hour = values.getAsInteger(WeeabooEntry.COLUMN_WEEABOO_SCH_HOUR);
            if (hour != null && hour < 0 || hour > 24){
                throw new IllegalArgumentException("Requires valid hour");
            }
        }

        if (values.containsKey(WeeabooEntry.COLUMN_WEEABOO_SCH_MINUTE)){
            Integer minute = values.getAsInteger(WeeabooEntry.COLUMN_WEEABOO_SCH_MINUTE);
            if (minute != null && minute < 0 || minute > 59){
                throw new IllegalArgumentException("Requires valid minute");
            }
        }

        if (values.size() == 0){
            return 0;
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        return database.update(WeeabooEntry.TABLE_NAME_SHEDULE, values, selection, selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);

        switch (match){
            case WEEABOO:
                return updateSchedule(uri,values, selection,selectionArgs);
            case WEEABOO_ID:
                selection = WeeabooEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri))};
                return updateSchedule(uri,values,selection,selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }
}
