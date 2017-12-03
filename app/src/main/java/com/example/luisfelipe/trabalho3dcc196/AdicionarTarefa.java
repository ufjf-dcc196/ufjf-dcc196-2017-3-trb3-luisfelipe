package com.example.luisfelipe.trabalho3dcc196;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class AdicionarTarefa extends AppCompatActivity {
    private EditText titulo;
    private EditText descricao;
    private Spinner estado;
    private Spinner dificuldade;
    private Button concluir;
    private Button cancelar;
    private TarefaAdapter tarefaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_tarefa);
        titulo = (EditText) findViewById(R.id.titulo);
        descricao = (EditText) findViewById(R.id.descricao);
        estado = (Spinner) findViewById(R.id.estado);
        dificuldade = (Spinner) findViewById(R.id.dificuldade);
        concluir = (Button) findViewById(R.id.concluir);
        cancelar = (Button) findViewById(R.id.cancelar);

        ArrayList<String> niveisDificuldade = new ArrayList();
        niveisDificuldade.add("1");
        niveisDificuldade.add("2");
        niveisDificuldade.add("3");
        niveisDificuldade.add("4");
        niveisDificuldade.add("5");

        ArrayList<String> tiposEstado = new ArrayList();
        tiposEstado.add("A Fazer");
        tiposEstado.add("Em Execução");
        tiposEstado.add("Bloqueada");
        tiposEstado.add("Concluida");

        ArrayAdapter<String> ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, niveisDificuldade);
        ArrayAdapter<String> ad2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tiposEstado);
        dificuldade.setAdapter(ad);
        estado.setAdapter(ad2);

        tarefaAdapter = new TarefaAdapter(getBaseContext(),null);

        concluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tarefa t = new Tarefa();
                t.setTitulo(titulo.getText().toString());
                t.setDescricao(descricao.getText().toString());
                t.setDificuldade(Integer.parseInt(dificuldade.getSelectedItem().toString()));
                t.setEstado(estado.getSelectedItem().toString());
                tarefaAdapter.inserirTarefa(t);
                setResult(RESULT_OK);
                finish();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
