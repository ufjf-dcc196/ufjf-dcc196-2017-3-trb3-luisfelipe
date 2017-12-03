package com.example.luisfelipe.trabalho3dcc196;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class VizualizarTarefas extends AppCompatActivity {
    private Button voltar;
    private ListView tarefas;
    private EtiquetadasAdapter etiquetadasAdapter;
    private TarefaAdapter tarefaAdapter;
    private String idTag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vizualizar_tarefas);
        voltar =(Button) findViewById(R.id.voltar);
        tarefas = (ListView) findViewById(R.id.tarefas);
        etiquetadasAdapter = new EtiquetadasAdapter(getBaseContext(),null);
        tarefaAdapter = new TarefaAdapter(getBaseContext(),null);
        idTag = getIntent().getStringExtra("id");
        tarefaAdapter.changeCursor(etiquetadasAdapter.obterCursor(idTag));
        tarefas.setAdapter(tarefaAdapter);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

}
