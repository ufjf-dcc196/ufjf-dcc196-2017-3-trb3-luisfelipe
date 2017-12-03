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

public class TarefaAdapter extends CursorAdapter {
    private OrganizacaoDbHelper organizacaoDbHelper;
    public TarefaAdapter(Context context, Cursor c) {
        super(context, c,0);
        organizacaoDbHelper = new OrganizacaoDbHelper(context);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.layout_tarefas,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView titulo = view.findViewById(R.id.titulo);
        TextView dificuldade = view.findViewById(R.id.dificuldade);
        TextView estado = view.findViewById(R.id.estado);
        titulo.setText(cursor.getString(cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLUMN_NAME_TITULO)));
        dificuldade.setText(cursor.getString(cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLUMN_NAME_DIFICULDADE)));
        estado.setText(cursor.getString(cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLUMN_NAME_ESTADO)));

    }

    public void atualizar() {
        try {
            SQLiteDatabase db = organizacaoDbHelper.getReadableDatabase();
            String[] visao = {
                    TarefaContract.Tarefa._ID,
                    TarefaContract.Tarefa.COLUMN_NAME_TITULO,
                    TarefaContract.Tarefa.COLUMN_NAME_DESCRICAO,
                    TarefaContract.Tarefa.COLUMN_NAME_DIFICULDADE,
                    TarefaContract.Tarefa.COLUMN_NAME_ESTADO,
            };
            Cursor c = db.query(TarefaContract.Tarefa.TABLE_NAME, visao, null, null, null, null,TarefaContract.Tarefa.COLUMN_NAME_ESTADO+ " ASC");
            this.changeCursor(c);
        } catch (Exception e) {
            Log.e("TAREFA", e.getLocalizedMessage());
            Log.e("TAREFA", e.getStackTrace().toString());
        }
    }
    public void inserirTarefa(Tarefa t){
        try {
            Random rand= new Random();
            SQLiteDatabase db = organizacaoDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put( TarefaContract.Tarefa.COLUMN_NAME_TITULO, t.getTitulo());
            values.put( TarefaContract.Tarefa.COLUMN_NAME_DESCRICAO, t.getDescricao());
            values.put( TarefaContract.Tarefa.COLUMN_NAME_DIFICULDADE, String.valueOf(t.getDificuldade()));
            values.put( TarefaContract.Tarefa.COLUMN_NAME_ESTADO, t.getEstado());


            long id = db.insert(TarefaContract.Tarefa.TABLE_NAME, null, values);
            atualizar();
        } catch (Exception e) {
            Log.e("TAREFA_INSERSAO", e.getLocalizedMessage());
            Log.e("TAREFA_INSERSAO", e.getStackTrace().toString());
        }

    }

    public Tarefa getTarefa(String id){

        try {
            SQLiteDatabase db = organizacaoDbHelper.getReadableDatabase();
            String[] visao = {
                    TarefaContract.Tarefa.COLUMN_NAME_TITULO,
                    TarefaContract.Tarefa.COLUMN_NAME_DESCRICAO,
                    TarefaContract.Tarefa.COLUMN_NAME_DIFICULDADE,
                    TarefaContract.Tarefa.COLUMN_NAME_ESTADO,
            };
            String selecao = TarefaContract.Tarefa._ID+"= "+id;
            Cursor c = db.query(TarefaContract.Tarefa.TABLE_NAME, visao, selecao, null, null, null, null);
            c.moveToFirst();
            Tarefa tarefa = new Tarefa();
            tarefa.setTitulo(c.getString(c.getColumnIndex(TarefaContract.Tarefa.COLUMN_NAME_TITULO)));
            tarefa.setDescricao(c.getString(c.getColumnIndex(TarefaContract.Tarefa.COLUMN_NAME_DESCRICAO)));
            tarefa.setDificuldade(Integer.parseInt(c.getString(c.getColumnIndex(TarefaContract.Tarefa.COLUMN_NAME_DIFICULDADE))));
            tarefa.setEstado(c.getString(c.getColumnIndex(TarefaContract.Tarefa.COLUMN_NAME_ESTADO)));
            return tarefa;
        } catch (Exception e) {
            Log.e("BUSCA_PARTICIPANTE", e.getLocalizedMessage());
            Log.e("BUSCA_PARTICIPANTE", e.getStackTrace().toString());
        }
        return null;
    }
    public String getId(int i){
        Cursor c = getCursor();
        c.moveToPosition(i);
        return c.getString(c.getColumnIndex(TarefaContract.Tarefa._ID));
    }
    public Tarefa getTarefa(int i){
        Tarefa tarefa = new Tarefa();
        Cursor c = getCursor();
        c.moveToPosition(i);
        tarefa.setTitulo(c.getString(c.getColumnIndex(TarefaContract.Tarefa.COLUMN_NAME_TITULO)));
        tarefa.setDescricao(c.getString(c.getColumnIndex(TarefaContract.Tarefa.COLUMN_NAME_DESCRICAO)));
        tarefa.setDificuldade(Integer.parseInt(c.getString(c.getColumnIndex(TarefaContract.Tarefa.COLUMN_NAME_DIFICULDADE))));
        tarefa.setEstado(c.getString(c.getColumnIndex(TarefaContract.Tarefa.COLUMN_NAME_ESTADO)));
        return tarefa;
    }
    public int getUltimoId(){
        try {
            SQLiteDatabase db = organizacaoDbHelper.getReadableDatabase();
            String sql= "SELECT MAX(_id ) as _id FROM "+ TarefaContract.Tarefa.TABLE_NAME;
            Cursor c = db.rawQuery(sql,null);
            c.moveToFirst();
            return c.getInt(c.getColumnIndex("_id"));
        } catch (Exception e) {
            Log.e("BUSCA_ULTIMO_ID", e.getLocalizedMessage());
            Log.e("BUSCA_ULTIMO_ID", e.getStackTrace().toString());
        }
        return -1;
    }
    public void removerTarefa(String id){
        try {
            SQLiteDatabase db = organizacaoDbHelper.getWritableDatabase();
            String sql = "DELETE FROM "+ TarefaContract.Tarefa.TABLE_NAME+ " WHERE "+ TarefaContract.Tarefa._ID+"="+ id;
            db.execSQL(sql);
        } catch (Exception e) {
            Log.e("ERRO_EXCLUSAO", e.getLocalizedMessage());
            Log.e("ERRO_EXCLUSAO", e.getStackTrace().toString());
        }

    }
    public void update(String id, Tarefa t){
        ContentValues newData = new ContentValues();
        newData.put(TarefaContract.Tarefa.COLUMN_NAME_TITULO,t.getTitulo());
        newData.put(TarefaContract.Tarefa.COLUMN_NAME_DESCRICAO,t.getDescricao());
        newData.put(TarefaContract.Tarefa.COLUMN_NAME_DIFICULDADE,String.valueOf(t.getDificuldade()));
        newData.put(TarefaContract.Tarefa.COLUMN_NAME_ESTADO,t.getEstado());

        try {
            SQLiteDatabase db = organizacaoDbHelper.getWritableDatabase();
            db.update(TarefaContract.Tarefa.TABLE_NAME, newData, "_id="+id, null);
            atualizar();
        } catch (Exception e) {
            Log.e("ATUALIZA_TAREFA", e.getLocalizedMessage());
            Log.e("ATUALIZA_TAREFA", e.getStackTrace().toString());
        }
    }
}
