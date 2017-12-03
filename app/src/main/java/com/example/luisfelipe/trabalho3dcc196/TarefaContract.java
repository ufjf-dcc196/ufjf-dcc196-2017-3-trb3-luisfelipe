package com.example.luisfelipe.trabalho3dcc196;

import android.provider.BaseColumns;

/**
 * Created by luisfelipe on 02/12/17.
 */

public class TarefaContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";
    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + Tarefa.TABLE_NAME + " (" +
            Tarefa._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            Tarefa.COLUMN_NAME_TITULO+TEXT_TYPE +  SEP+
            Tarefa.COLUMN_NAME_DESCRICAO + TEXT_TYPE + SEP +
            Tarefa.COLUMN_NAME_DIFICULDADE + TEXT_TYPE + SEP +
            Tarefa.COLUMN_NAME_ESTADO + TEXT_TYPE +")";

    public static final String SQL_DROP_TAREFA= "DROP TABLE IF EXISTS " + Tarefa.TABLE_NAME;

    public TarefaContract() {
    }

    public static final class Tarefa implements BaseColumns {
        public static final String TABLE_NAME = "livros";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_DESCRICAO = "descricao";
        public static final String COLUMN_NAME_DIFICULDADE = "dificuldade";
        public static final String COLUMN_NAME_ESTADO = "estado";

    }
}
