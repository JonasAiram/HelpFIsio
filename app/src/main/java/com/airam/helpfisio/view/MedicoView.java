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
import com.airam.helpfisio.controller.MedicoController;
import com.airam.helpfisio.model.Medico;
import com.airam.helpfisio.view.cadastro.MedicoCadastro;

import java.util.ArrayList;
import java.util.List;

public class MedicoView extends Activity implements View.OnClickListener {

    private ListView listView;
    private EditText editText;
    ArrayAdapter<String> adapter;
    List<Medico> medicoList;
    private List<String> medicoListNome = new ArrayList<String>();
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
                MedicoView.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    @Override
    public void onClick(View view) {

        MedicoCadastro medicoCadastro = new MedicoCadastro(view);
    }

    public void atualizarRegistros() {

        medicoListNome.clear();

        medicoList = new MedicoController(this).getAll();

        for (Medico medico : medicoList)

            medicoListNome.add(medico.getNome() + " " + medico.getSobrenome() + " - CPF: " + medico.getCpf());

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medicoListNome);
        listView.setAdapter(adapter);

    }

}
