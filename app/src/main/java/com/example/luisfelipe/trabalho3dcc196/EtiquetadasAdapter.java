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
 * Created by luisfelipe on 03/12/17.
 */

public class EtiquetadasAdapter extends CursorAdapter {
    private OrganizacaoDbHelper organizacaoDbHelper;
    public EtiquetadasAdapter(Context context, Cursor c) {
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
    public void atualizar(String idTarefa){
        String rawQuery = EtiqutadasContract.SQL_TAGS_ATRIBUIDAS+" WHERE "+ EtiqutadasContract.Etiqueta.COLUMN_NAME_TAREFA +"="+idTarefa;
        try {
            SQLiteDatabase db = organizacaoDbHelper.getReadableDatabase();
            Cursor c = db.rawQuery(rawQuery,null);
            this.changeCursor(c);
        } catch (Exception e) {
            Log.e("RESERVA", e.getLocalizedMessage());
            Log.e("RESERVA", e.getStackTrace().toString());
        }
    }
    public Cursor obterCursor(String idTag) {
        String rawQuery = EtiqutadasContract.SQL_CONSULTA_ETIQUETAS+" WHERE "+ EtiqutadasContract.Etiqueta.COLUMN_NAME_TAG +"="+idTag+" ORDER BY "+ TarefaContract.Tarefa.COLUMN_NAME_ESTADO;
        try {
            SQLiteDatabase db = organizacaoDbHelper.getReadableDatabase();
            Cursor c = db.rawQuery(rawQuery,null);
            this.changeCursor(c);
            return c;
        } catch (Exception e) {
            Log.e("RESERVA", e.getLocalizedMessage());
            Log.e("RESERVA", e.getStackTrace().toString());
        }
        return null;
    }
    public void inserirEtiqueta(String idTarefa , String idTag){
        try {
            Random rand= new Random();
            SQLiteDatabase db = organizacaoDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(EtiqutadasContract.Etiqueta.COLUMN_NAME_TAREFA, idTarefa);
            values.put(EtiqutadasContract.Etiqueta.COLUMN_NAME_TAG, idTag);
            long id = db.insert(EtiqutadasContract.Etiqueta.TABLE_NAME, null, values);
        } catch (Exception e) {
            Log.e("ETIQETACONSULTA", e.getLocalizedMessage());
            Log.e("ETIQETACONSULTA", e.getStackTrace().toString());
        }

    }
    public String getId(int i){
        Cursor c = getCursor();
        c.moveToPosition(i);
        return c.getString(c.getColumnIndex("_id"));
    }
    public void removerEtiqueta(String idTarefa,String idTag){
        try {
            SQLiteDatabase db = organizacaoDbHelper.getWritableDatabase();
            String sql = "DELETE FROM "+ EtiqutadasContract.Etiqueta.TABLE_NAME+ " WHERE "+ EtiqutadasContract.Etiqueta.COLUMN_NAME_TAG+"="+ idTag +
                                                                                    " AND " + EtiqutadasContract.Etiqueta.COLUMN_NAME_TAREFA+"="+idTarefa;
            db.execSQL(sql);
            atualizar(idTarefa);
        } catch (Exception e) {
            Log.e("ERRO_EXCLUSAO_ETIQUETA", e.getLocalizedMessage());
            Log.e("ERRO_EXCLUSAO_ETIQUETA", e.getStackTrace().toString());
        }
    }
}
