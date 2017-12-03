package com.example.luisfelipe.trabalho3dcc196;

import android.provider.BaseColumns;

/**
 * Created by luisfelipe on 03/12/17.
 */

public class EtiqutadasContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";
    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + Etiqueta.TABLE_NAME + " (" +
            Etiqueta._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            Etiqueta.COLUMN_NAME_TAREFA + TEXT_TYPE + SEP +
            Etiqueta.COLUMN_NAME_TAG + TEXT_TYPE + SEP +
            " FOREIGN KEY ("+Etiqueta.COLUMN_NAME_TAREFA+") REFERENCES "+ TarefaContract.Tarefa.TABLE_NAME+"("+ TarefaContract.Tarefa._ID+")"+SEP+
            " FOREIGN KEY ("+Etiqueta.COLUMN_NAME_TAG+") REFERENCES "+ TagContract.Tag.TABLE_NAME+"("+ TagContract.Tag._ID+"))";


    public static final String SQL_DROP_TAG= "DROP TABLE IF EXISTS " + Etiqueta.TABLE_NAME;
    public static final String SQL_CONSULTA_ETIQUETAS = "SELECT tarefa._id"+ SEP+
                                                TarefaContract.Tarefa.COLUMN_NAME_TITULO+SEP+
                                                TarefaContract.Tarefa.COLUMN_NAME_DESCRICAO+SEP+
                                                TarefaContract.Tarefa.COLUMN_NAME_DIFICULDADE+SEP+
                                                TarefaContract.Tarefa.COLUMN_NAME_ESTADO+
            " FROM "+Etiqueta.TABLE_NAME+
            " INNER JOIN "+ TarefaContract.Tarefa.TABLE_NAME+
            " tarefa ON ("+Etiqueta.COLUMN_NAME_TAREFA+"=tarefa."+ TarefaContract.Tarefa._ID+")"+
            " INNER JOIN "+ TagContract.Tag.TABLE_NAME+
            " tag ON ("+Etiqueta.COLUMN_NAME_TAG+"= tag."+TagContract.Tag._ID+")";
    public static final String SQL_TAGS_ATRIBUIDAS = "SELECT tag._id"+SEP+ TagContract.Tag.COLUMN_NAME_TAG+" FROM  "+Etiqueta.TABLE_NAME+
            " INNER JOIN "+ TagContract.Tag.TABLE_NAME+
            " tag ON ("+Etiqueta.COLUMN_NAME_TAG+"= tag."+TagContract.Tag._ID+")";

    public EtiqutadasContract() {
    }

    public static final class Etiqueta implements BaseColumns {
        public static final String TABLE_NAME = "etiquetas";
        public static final String COLUMN_NAME_TAG = "id_tag";
        public static final String COLUMN_NAME_TAREFA= "id_tarefa";

    }
}
