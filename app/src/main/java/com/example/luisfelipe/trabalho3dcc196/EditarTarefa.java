package com.example.luisfelipe.trabalho3dcc196;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class EditarTarefa extends AppCompatActivity {
    private EditText titulo;
    private EditText descricao;
    private Spinner estado;
    private Spinner dificuldade;
    private Spinner tags_disponiveis;
    private ListView tags_adicionadas;
    private Button tegzar;
    private Button concluir;
    private Button cancelar;
    private TarefaAdapter tarefaAdapter;
    private TagAdapter tagAdapter;
    private EtiquetadasAdapter etiquetadasAdapter;
    private String idTarefa;
    private Tarefa t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_tarefa);
        titulo = (EditText) findViewById(R.id.titulo);
        descricao = (EditText) findViewById(R.id.descricao);
        estado = (Spinner) findViewById(R.id.estado);
        dificuldade = (Spinner) findViewById(R.id.dificuldade);
        tags_disponiveis = (Spinner) findViewById(R.id.tegs);
        tags_adicionadas = (ListView) findViewById(R.id.tegs_atribuidas);
        tegzar = (Button) findViewById(R.id.adicionar_tag);
        concluir = (Button) findViewById(R.id.concluir);
        cancelar = (Button) findViewById(R.id.cancelar);
        idTarefa =getIntent().getStringExtra("id");

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
        tagAdapter = new TagAdapter(getBaseContext(),null);
        etiquetadasAdapter = new EtiquetadasAdapter(getBaseContext(),null);
        etiquetadasAdapter.atualizar(idTarefa);
        tagAdapter.atualizar();
        t = tarefaAdapter.getTarefa(idTarefa);

        tags_disponiveis.setAdapter(tagAdapter);
        tags_adicionadas.setAdapter(etiquetadasAdapter);

        titulo.setText(t.getTitulo());
        descricao.setText(t.getDescricao());
        if(t.getEstado().equals("A Fazer")){
            estado.setSelection(0);
        }else if(t.getEstado().equals("Em Execução")){
            estado.setSelection(1);
        }else if(t.getEstado().equals("Bloqueada")) {
            estado.setSelection(2);
        }else if(t.getEstado().equals("Concluida")) {
            estado.setSelection(3);
        }
        dificuldade.setSelection(t.getDificuldade()-1);

        concluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.setTitulo(titulo.getText().toString());
                t.setDescricao(descricao.getText().toString());
                t.setDificuldade(Integer.parseInt(dificuldade.getSelectedItem().toString()));
                t.setEstado(estado.getSelectedItem().toString());

                tarefaAdapter.update(idTarefa,t);
                setResult(RESULT_OK);
                finish();
            }
        });

        tegzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etiquetadasAdapter.inserirEtiqueta(idTarefa,tagAdapter.getId(tags_disponiveis.getSelectedItemPosition()));
                etiquetadasAdapter.atualizar(idTarefa);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        tags_adicionadas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                etiquetadasAdapter.removerEtiqueta(idTarefa,tagAdapter.getId(position));
                return true;
            }
        });


    }




}
