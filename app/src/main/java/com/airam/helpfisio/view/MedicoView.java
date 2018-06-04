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
import com.airam.helpfisio.controller.MedicoController;
import com.airam.helpfisio.model.Medico;
import com.airam.helpfisio.view.cadastro.MedicoCadastro;

import java.util.ArrayList;
import java.util.List;

public class MedicoView extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private EditText editText;
    ArrayAdapter<String> adapter;
    List<Medico> medicoList;
    private List<String> medicoListNome = new ArrayList<String>();
    private MedicoController medicoController;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultalayout);

        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editTextPesquisar);
        imageView = (ImageView) findViewById(R.id.imgViewAdd);

        medicoController = new MedicoController(this);
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
                MedicoView.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    public void onClick(View view) {

        MedicoCadastro medicoCadastro = new MedicoCadastro(this);
    }

    public void atualizarRegistros() {

        medicoListNome.clear();

        medicoList = new MedicoController(this).getAll();

        for (Medico medico : medicoList)

            medicoListNome.add(medico.getNome() + " " + medico.getSobrenome() + " - CPF: " + medico.getCpf());

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medicoListNome);
        listView.setAdapter(adapter);

    }

    public void alertDialog(final Medico medico){

        final CharSequence[] itens = {"Editar","Deletar"};

        new AlertDialog.Builder(this).setTitle("Detalhes do contato")
                .setItems(itens, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {

                        if (item == 0){
                            //EDITAR

                            MedicoCadastro medicoCadastro = new MedicoCadastro(MedicoView.this);
                            medicoCadastro.loadMedico(medico);

                        }else if (item == 1) {
                            //DELETAR
                            boolean isDeletouComSucesso = medicoController.delete(medico.getId());

                            if (isDeletouComSucesso){
                                Toast.makeText(MedicoView.this, "Médicio deletado.", Toast.LENGTH_SHORT).show();
                                atualizarRegistros();

                            }else{
                                Toast.makeText(MedicoView.this, "Erro ao Deletar o Médico.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        dialogInterface.dismiss();
                    }
                }).show();

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Medico medico = medicoList.get(i);
        alertDialog(medico);

    }

}
