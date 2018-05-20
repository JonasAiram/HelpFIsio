package com.airam.helpfisio.view;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.airam.helpfisio.R;
import com.airam.helpfisio.controller.LeitoController;
import com.airam.helpfisio.model.Leito;
import com.airam.helpfisio.view.cadastro.LeitoCadastro;

import java.util.ArrayList;
import java.util.List;

public class LeitoView extends Activity implements View.OnClickListener {

    private ListView listView;
    private EditText editText;
    ArrayAdapter<String> adapter;
    List<Leito> leitoList;
    private List<String> leitoListNome = new ArrayList<String>();
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultalayout);

        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editTextPesquisar);
        imageView = (ImageView) findViewById(R.id.imgViewAdd);

        imageView.setOnClickListener(this);
        atualizarRegistros();

        //Filtrar a busca no banco
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                LeitoView.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    @Override
    public void onClick(View view) {

        LeitoCadastro leitoCadastro = new LeitoCadastro(view);
    }

    public void atualizarRegistros() {

        leitoListNome.clear();

        leitoList = new LeitoController(this).getAll();

        for (Leito leito : leitoList)

            leitoListNome.add("Tipo: " + leito.getTipo() + " - Qtd: " + leito.getQuantidade());

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, leitoListNome);
        listView.setAdapter(adapter);

    }

}
