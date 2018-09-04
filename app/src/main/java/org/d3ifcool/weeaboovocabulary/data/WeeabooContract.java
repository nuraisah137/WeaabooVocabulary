package org.d3ifcool.weeaboovocabulary.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by user on 08/04/2018.
 */

public final class WeeabooContract {
    private WeeabooContract() {
    } //kenapa constructornya menggunakan private? karena kita tidak ingin ada programmer lain yang menginstant contructor ini

    public static final String CONTENT_AUTHORITY = "org.d3ifcool.weeaboovocabulary";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_WEEABOO = "weeaboo";

    public static final class WeeabooEntry implements BaseColumns { //fungsi basecolumns? karena nanti kita akan memanggil databasenya menggunakan cursorAdapter

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_WEEABOO);

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_WEEABOO;

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_WEEABOO;

        public final static String TABLE_NAME_SHEDULE = "schedule"; //nama tabel schedule

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_WEEABOO_SCH_HOUR = "hour";
        public final static String COLUMN_WEEABOO_SCH_MINUTE = "minute";
        public final static String COLUMN_WEEABOO_STATUS = "status";

    }
}
