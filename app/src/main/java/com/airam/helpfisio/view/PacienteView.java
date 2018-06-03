package com.airam.helpfisio.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.airam.helpfisio.R;
import com.airam.helpfisio.controller.PacienteController;
import com.airam.helpfisio.model.Paciente;
import com.airam.helpfisio.view.cadastro.PacienteCadastro;

import java.util.ArrayList;
import java.util.List;

public class PacienteView extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ListView listView;
    private EditText editText;
    ArrayAdapter<String> adapter;
    List<Paciente> pacienteList;
    private List<String> pacienteListNome = new ArrayList<String>();
    private PacienteController pacienteController;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultalayout);

        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editTextPesquisar);
        imageView = (ImageView) findViewById(R.id.imgViewAdd);

        pacienteController = new PacienteController(this);
        listView.setOnItemClickListener(this);

        imageView.setOnClickListener(this);
        atualizarRegistros();

        //Filtrar a busca no banco
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                PacienteView.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    @Override
    public void onClick(View view) {

        PacienteCadastro pacienteCadastro = new PacienteCadastro(this);
    }

    public void atualizarRegistros() {

        pacienteListNome.clear();

        pacienteList = pacienteController.getAll();

        for (Paciente paciente : pacienteList)
            pacienteListNome.add(paciente.getNome() + " " + paciente.getSobrenome() + " - CPF: " + paciente.getCpf());

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pacienteListNome);
        listView.setAdapter(adapter);

    }

    public void alertDialog(final Paciente paciente){

        final CharSequence[] itens = {"editar","deletar"};

        new AlertDialog.Builder(this).setTitle("Detalhes do contato")
                .setItems(itens, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {

                        if (item == 0){
                            //EDITAR

                            PacienteCadastro pacienteCadastro = new PacienteCadastro(PacienteView.this);
                            pacienteCadastro.loadCalculo(paciente);

                        }else if (item == 1) {
                            //DELETAR
                            boolean isDeletouComSucesso = pacienteController.delete(paciente.getId());

                            if (isDeletouComSucesso){
                                Toast.makeText(PacienteView.this, "Contato deletado.", Toast.LENGTH_SHORT).show();
                                atualizarRegistros();

                            }else{
                                Toast.makeText(PacienteView.this, "Erro ao deletar o contato.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        dialogInterface.dismiss();
                    }
                }).show();

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Paciente paciente = pacienteList.get(i);
        alertDialog(paciente);

    }

}
