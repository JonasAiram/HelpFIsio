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
import com.airam.helpfisio.controller.ConsultaFisioController;
import com.airam.helpfisio.model.ConsultaFisio;
import com.airam.helpfisio.view.cadastro.ConsultaFisioCadastro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas on 31/05/2018.
 */

public class ConsultaFisioView extends Activity implements View.OnClickListener {

    private ListView listView;
    private EditText editText;
    ArrayAdapter<String> adapter;
    List<ConsultaFisio> consultaFisioList;
    private List<String> consListNome = new ArrayList<String>();
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
                ConsultaFisioView.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    @Override
    public void onClick(View view) {

        ConsultaFisioCadastro consultaFisioCadastro= new ConsultaFisioCadastro(view);
    }

    public void atualizarRegistros() {

        consListNome.clear();

        consultaFisioList = new ConsultaFisioController(this).getAll();

        for (ConsultaFisio consultaFisio : consultaFisioList)

            consListNome.add("Data: " + consultaFisio.getData() + " - Descrição: " + consultaFisio.getDescricao());

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, consListNome);
        listView.setAdapter(adapter);
    }

}
