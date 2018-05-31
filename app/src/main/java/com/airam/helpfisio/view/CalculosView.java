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
import com.airam.helpfisio.controller.CalculosController;
import com.airam.helpfisio.model.Calculos;
import com.airam.helpfisio.view.cadastro.CalculosCadastro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas on 31/05/2018.
 */

public class CalculosView extends Activity implements View.OnClickListener {

    private ListView listView;
    private EditText editText;
    ArrayAdapter<String> adapter;
    List<Calculos> calculosList;
    private List<String> calcListNome = new ArrayList<String>();
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
                CalculosView.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    @Override
    public void onClick(View view) {

        CalculosCadastro calculosCadastro = new CalculosCadastro(view);
    }

    public void atualizarRegistros() {

        calcListNome.clear();

        calculosList = new CalculosController(this).getAll();

        for (Calculos calculos : calculosList)

            calcListNome.add("Nome: " + calculos.getNome() + " - Resultado: " + calculos.getResultado());

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, calcListNome);
        listView.setAdapter(adapter);
    }

}