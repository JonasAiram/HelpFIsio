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
import com.airam.helpfisio.controller.ConsultaMedicoController;
import com.airam.helpfisio.model.ConsultaMedico;
import com.airam.helpfisio.view.cadastro.ConsultaMedicoCadastro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas on 31/05/2018.
 */

public class ConsultaMedicoView extends Activity implements View.OnClickListener{

    private ListView listView;
    private EditText editText;
    ArrayAdapter<String> adapter;
    List<ConsultaMedico> consultaMedicoList;
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
                ConsultaMedicoView.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    @Override
    public void onClick(View view) {

        ConsultaMedicoCadastro consultaMedicoCadastro = new ConsultaMedicoCadastro(view);
    }

    public void atualizarRegistros() {

        consListNome.clear();

        consultaMedicoList = new ConsultaMedicoController(this).getAll();

        for (ConsultaMedico consultaMedico : consultaMedicoList)

            consListNome.add("Data: " + consultaMedico.getData() + " - Descrição: " + consultaMedico.getDescricao());

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, consListNome);
        listView.setAdapter(adapter);
    }

}
