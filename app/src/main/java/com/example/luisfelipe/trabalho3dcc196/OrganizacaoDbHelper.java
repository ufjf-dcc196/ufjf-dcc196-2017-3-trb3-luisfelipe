package com.example.luisfelipe.trabalho3dcc196;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by luisfelipe on 02/12/17.
 */

public class OrganizacaoDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME = "O8.db";

    public OrganizacaoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TagContract.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(TarefaContract.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(EtiqutadasContract.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(TagContract.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(TarefaContract.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(EtiqutadasContract.SQL_CREATE_TABLE);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
