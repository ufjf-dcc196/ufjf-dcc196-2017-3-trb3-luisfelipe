package com.example.luisfelipe.trabalho3dcc196;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdicionarTag extends AppCompatActivity {
    private EditText etiqueta;
    private Button concluir;
    private Button cancelar;
    private TagAdapter tagAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_tag);
        etiqueta = (EditText)findViewById(R.id.etiqueta);
        concluir = (Button)findViewById(R.id.concluir);
        cancelar = (Button)findViewById(R.id.cancelar);
        tagAdapter = new TagAdapter(getBaseContext(),null);

        concluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagAdapter.inserirTag(new Tag(etiqueta.getText().toString()));
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
