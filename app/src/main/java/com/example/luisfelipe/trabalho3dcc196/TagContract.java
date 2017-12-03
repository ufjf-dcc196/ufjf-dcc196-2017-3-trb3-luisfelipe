package com.example.luisfelipe.trabalho3dcc196;

import android.provider.BaseColumns;

/**
 * Created by luisfelipe on 02/12/17.
 */

public class TagContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";
    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + Tag.TABLE_NAME + " (" +
            Tag._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            Tag.COLUMN_NAME_TAG+TEXT_TYPE +")";

    public static final String SQL_DROP_TAG= "DROP TABLE IF EXISTS " + Tag.TABLE_NAME;

    public TagContract() {
    }

    public static final class Tag implements BaseColumns {
        public static final String TABLE_NAME = "tags";
        public static final String COLUMN_NAME_TAG = "tag";

    }
}
