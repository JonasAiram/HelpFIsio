package com.airam.helpfisio;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.airam.helpfisio.controller.PacienteController;
import com.airam.helpfisio.model.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteView extends Activity {

    private ListView listView;
    private EditText editText;
    private List<String> pacienteListNome = new ArrayList<String>();
    private ArrayList<String> pesquisa = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultalayout);

        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editTextPesquisar);

        List<Paciente> pacienteList = new PacienteController(this).getAll();

        for (Paciente paciente : pacienteList)

            pacienteListNome.add(paciente.getNome() + " " + paciente.getSobrenome() + " - CPF: " + paciente.getCpf());

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pacienteListNome));

        Pesquisar();

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                Pesquisar();

                listView.setAdapter(new ArrayAdapter<String>(PacienteView.this,android.R.layout.simple_list_item_1, pesquisa));
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void Pesquisar() {

        int textLength = editText.getText().length();
        pesquisa.clear();

        for (int i = 0; i < pacienteListNome.size(); i++){
            if (textLength <= pacienteListNome.get(i).length()){
                if (editText.getText().toString().equalsIgnoreCase((String)pacienteListNome.get(i).subSequence(0, textLength))){
                    pesquisa.add(pacienteListNome.get(i));
                }
            }
        }
    }
}
