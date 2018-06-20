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
import com.airam.helpfisio.controller.ConsultaFisioController;
import com.airam.helpfisio.model.ConsultaFisio;
import com.airam.helpfisio.model.DateUtil;
import com.airam.helpfisio.view.cadastro.ConsultaFisioCadastro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas on 31/05/2018.
 */

public class ConsultaFisioView extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private EditText editText;
    ArrayAdapter<String> adapter;
    List<ConsultaFisio> consultaFisioList;
    private List<String> consListNome = new ArrayList<String>();
    private ConsultaFisioController consultaFisioController;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultalayout);

        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editTextPesquisar);
        imageView = (ImageView) findViewById(R.id.imgViewAdd);

        consultaFisioController = new ConsultaFisioController(this);
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
                ConsultaFisioView.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    @Override
    public void onClick(View view) {

        ConsultaFisioCadastro consultaFisioCadastro= new ConsultaFisioCadastro(this);
    }

    public void atualizarRegistros() {

        consListNome.clear();

        consultaFisioList = new ConsultaFisioController(this).getAll();

        for (ConsultaFisio consultaFisio : consultaFisioList)

            consListNome.add("Data: " + DateUtil.dateToString(consultaFisio.getData()) + " - Descrição: " + consultaFisio.getDescricao());

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, consListNome);
        listView.setAdapter(adapter);
    }

    public void alertDialog(final ConsultaFisio consultaFisio){

        final CharSequence[] itens = {"Editar","Deletar"};

        new AlertDialog.Builder(this).setTitle("Detalhes do contato")
                .setItems(itens, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {

                        if (item == 0){
                            //EDITAR

                            ConsultaFisioCadastro consultaFisioCadastro= new ConsultaFisioCadastro(ConsultaFisioView.this);
                            consultaFisioCadastro.loadConsulta(consultaFisio);

                        }else if (item == 1) {
                            //DELETAR
                            boolean isDeletouComSucesso = consultaFisioController.delete(consultaFisio.getIdFisio());

                            if (isDeletouComSucesso){
                                Toast.makeText(ConsultaFisioView.this, "Consulta deletada.", Toast.LENGTH_SHORT).show();
                                atualizarRegistros();

                            }else{
                                Toast.makeText(ConsultaFisioView.this, "Erro ao Deletar a Consulta.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        dialogInterface.dismiss();
                    }
                }).show();

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        ConsultaFisio consultaFisio = consultaFisioList.get(i);
        alertDialog(consultaFisio);

    }

}
