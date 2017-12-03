package com.example.luisfelipe.trabalho3dcc196;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by luisfelipe on 02/12/17.
 */

public class TagAdapter extends CursorAdapter {
    private OrganizacaoDbHelper organizacaoDbHelper;
    public TagAdapter(Context context, Cursor c) {
        super(context, c,0);
        organizacaoDbHelper = new OrganizacaoDbHelper(context);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.layout_lista,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textNome = view.findViewById(R.id.nome);
        String nome = cursor.getString(cursor.getColumnIndexOrThrow(TagContract.Tag.COLUMN_NAME_TAG));
        textNome.setText(nome);
    }

    public void removerTeg(String id){
        try {
            SQLiteDatabase db = organizacaoDbHelper.getWritableDatabase();
            String sql = "DELETE FROM "+ TagContract.Tag.TABLE_NAME+ " WHERE "+ TagContract.Tag._ID+"="+ id;
            db.execSQL(sql);
        } catch (Exception e) {
            Log.e("ERRO_EXCLUSAO", e.getLocalizedMessage());
            Log.e("ERRO_EXCLUSAO", e.getStackTrace().toString());
        }

    }

    public void atualizar() {
        try {
            SQLiteDatabase db = organizacaoDbHelper.getReadableDatabase();
            String[] visao = {
                    TagContract.Tag._ID,
                    TagContract.Tag.COLUMN_NAME_TAG,
            };
            Cursor c = db.query(TagContract.Tag.TABLE_NAME, visao, null, null, null, null, null);
            this.changeCursor(c);
        } catch (Exception e) {
            Log.e("TAG", e.getLocalizedMessage());
            Log.e("TAG", e.getStackTrace().toString());
        }
    }
    public void inserirTag(Tag t){
        try {
            Random rand= new Random();
            SQLiteDatabase db = organizacaoDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put( TagContract.Tag.COLUMN_NAME_TAG, t.getTag());
            long id = db.insert(TagContract.Tag.TABLE_NAME, null, values);
            atualizar();
        } catch (Exception e) {
            Log.e("TAG_INSERCAO", e.getLocalizedMessage());
            Log.e("TAG_INSERCAO", e.getStackTrace().toString());
        }

    }
    public Tag getTag(String id){

        try {
            SQLiteDatabase db = organizacaoDbHelper.getReadableDatabase();
            String[] visao = {
                    TagContract.Tag.COLUMN_NAME_TAG,
            };
            String selecao = TagContract.Tag._ID+"= "+id;
            Cursor c = db.query(TagContract.Tag.TABLE_NAME, visao, selecao, null, null, null, null);
            c.moveToFirst();
            return new Tag(c.getString((c.getColumnIndex(TagContract.Tag.COLUMN_NAME_TAG))));
        } catch (Exception e) {
            Log.e("BUSCA_TEG", e.getLocalizedMessage());
            Log.e("BUSCA_TEG", e.getStackTrace().toString());
        }
        return null;
    }
    public String getId(int i){
        Cursor c = getCursor();
        c.moveToPosition(i);
        return c.getString(c.getColumnIndex(TagContract.Tag._ID));
    }
    public Tag getTeg(int i){
        Cursor c = getCursor();
        c.moveToPosition(i);
        return new Tag(c.getString(c.getColumnIndex(TagContract.Tag.COLUMN_NAME_TAG)));
    }
}
