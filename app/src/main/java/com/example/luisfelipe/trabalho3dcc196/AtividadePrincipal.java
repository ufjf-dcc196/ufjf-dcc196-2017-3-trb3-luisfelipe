package com.example.luisfelipe.trabalho3dcc196;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class AtividadePrincipal extends AppCompatActivity {
    private OrganizacaoDbHelper organizacaoDbHelper;
    private Button adicionarTag;
    private Button adicionarTarefa;
    private ListView tags;
    private ListView tarefas;
    private TagAdapter tagAdapter;
    private TarefaAdapter tarefaAdapter;

    public static final int ADICIONAR_TAG = 1;
    public static final int ADICIONAR_TAREFA = 2;
    public static final int EDITAR_TAREFA = 3;
    public static final int VISUALIZAR_TAREFAS = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atividade_principal);
        adicionarTag = (Button)findViewById(R.id.criar_teg);
        adicionarTarefa = (Button)findViewById(R.id.adicionar_tarefa);
        tags = (ListView) findViewById(R.id.lista_tags);
        tarefas = (ListView) findViewById(R.id.lista_tarefas);


        organizacaoDbHelper = new OrganizacaoDbHelper(getApplicationContext());
        tagAdapter=new TagAdapter(getBaseContext(),null);
        tarefaAdapter=new TarefaAdapter(getBaseContext(),null);
        tagAdapter.atualizar();
        tarefaAdapter.atualizar();
        tags.setAdapter(tagAdapter);
        tarefas.setAdapter(tarefaAdapter);
        adicionarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nova = new Intent(AtividadePrincipal.this,AdicionarTarefa.class);
                startActivityForResult(nova,ADICIONAR_TAREFA);
            }
        });
        adicionarTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nova = new Intent(AtividadePrincipal.this,AdicionarTag.class);
                startActivityForResult(nova,ADICIONAR_TAG);
            }
        });

        tags.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent novo = new Intent(AtividadePrincipal.this,VizualizarTarefas.class);
                novo.putExtra("id",tagAdapter.getId(position));
                startActivityForResult(novo,VISUALIZAR_TAREFAS);
            }
        });

        tags.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                tagAdapter.removerTeg(tagAdapter.getId(position));
                tagAdapter.atualizar();
                return true;
            }
        });

        tarefas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                tarefaAdapter.removerTarefa(tarefaAdapter.getId(position));
                tarefaAdapter.atualizar();
                return true;
            }
        });
        tarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nova = new Intent(AtividadePrincipal.this,EditarTarefa.class);
                nova.putExtra("id",tarefaAdapter.getId(position));
                startActivityForResult(nova,EDITAR_TAREFA);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode == ADICIONAR_TAG&&resultCode==RESULT_OK){
           tagAdapter.atualizar();
       }
        if(requestCode == ADICIONAR_TAREFA&&resultCode==RESULT_OK){
            tarefaAdapter.atualizar();
        }
        if(requestCode == EDITAR_TAREFA&&resultCode==RESULT_OK){
            tarefaAdapter.atualizar();
        }
        if(requestCode == VISUALIZAR_TAREFAS&&resultCode==RESULT_OK){
            tarefaAdapter.atualizar();
        }
    }


}
