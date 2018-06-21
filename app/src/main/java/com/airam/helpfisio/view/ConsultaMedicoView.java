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
import com.airam.helpfisio.controller.ConsultaMedicoController;
import com.airam.helpfisio.model.ConsultaMedico;
import com.airam.helpfisio.model.DateUtil;
import com.airam.helpfisio.model.Paciente;
import com.airam.helpfisio.view.cadastro.ConsultaMedicoCadastro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas on 31/05/2018.
 */

public class ConsultaMedicoView extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ListView listView;
    private EditText editText;
    ArrayAdapter<String> adapter;
    List<ConsultaMedico> consultaMedicoList;
    private List<String> consListNome = new ArrayList<String>();
    private ConsultaMedicoController consultaMedicoController;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultalayout);

        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editTextPesquisar);
        imageView = (ImageView) findViewById(R.id.imgViewAdd);

        consultaMedicoController = new ConsultaMedicoController(this);
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
                ConsultaMedicoView.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    @Override
    public void onClick(View view) {

        ConsultaMedicoCadastro consultaMedicoCadastro = new ConsultaMedicoCadastro(this);
    }

    public void atualizarRegistros() {

        consListNome.clear();

        consultaMedicoList = new ConsultaMedicoController(this).getAll();

        for (ConsultaMedico consultaMedico : consultaMedicoList)

            consListNome.add("Paciente: " + consultaMedico.getPacienteNome() +" Data: " + DateUtil.dateToString(consultaMedico.getData()));

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, consListNome);
        listView.setAdapter(adapter);
    }

    public void alertDialog(final ConsultaMedico consultaMedico){

        final CharSequence[] itens = {"Editar","Deletar"};

        new AlertDialog.Builder(this).setTitle("Detalhes do contato")
                .setItems(itens, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {

                        if (item == 0){
                            //EDITAR

                            ConsultaMedicoCadastro consultaMedicoCadastro= new ConsultaMedicoCadastro(ConsultaMedicoView.this);
                            consultaMedicoCadastro.loadConsulta(consultaMedico);

                        }else if (item == 1) {
                            //DELETAR
                            boolean isDeletouComSucesso = consultaMedicoController.delete(consultaMedico.getIdMedico());

                            if (isDeletouComSucesso){
                                Toast.makeText(ConsultaMedicoView.this, "Médicio deletado.", Toast.LENGTH_SHORT).show();
                                atualizarRegistros();

                            }else{
                                Toast.makeText(ConsultaMedicoView.this, "Erro ao Deletar o Médico.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        dialogInterface.dismiss();
                    }
                }).show();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        ConsultaMedico consultaMedico = consultaMedicoList.get(i);
        alertDialog(consultaMedico);

    }
}
