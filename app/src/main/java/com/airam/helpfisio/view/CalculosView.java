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
import com.airam.helpfisio.controller.CalculosController;
import com.airam.helpfisio.model.Calculos;
import com.airam.helpfisio.view.cadastro.CalculosCadastro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas on 31/05/2018.
 */

public class CalculosView extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ListView listView;
    private EditText editText;
    ArrayAdapter<String> adapter;
    List<Calculos> calculosList;
    private List<String> calcListNome = new ArrayList<String>();
    private CalculosController calculosController;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultalayout);

        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editTextPesquisar);
        imageView = (ImageView) findViewById(R.id.imgViewAdd);

        calculosController = new CalculosController(this);
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
                CalculosView.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    @Override
    public void onClick(View view) {
        CalculosCadastro calculosCadastro = new CalculosCadastro(this);

    }

    public void atualizarRegistros() {

        calcListNome.clear();

        calculosList =  calculosController.getAll();

        for (Calculos calculos : calculosList) {

            //int intID = calculos.getId();
            calcListNome.add("Nome: " + calculos.getNome() + " - Resultado: " + calculos.getResultado());
            //listView.setTag(Integer.toString(intID));
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, calcListNome);
        listView.setAdapter(adapter);

    }

    public void alertDialog(final Calculos calculos){

        final CharSequence[] itens = {"editar","deletar"};

        new AlertDialog.Builder(this).setTitle("Detalhes do contato")
                .setItems(itens, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {

                        if (item == 0){
                            //EDITAR

                            CalculosCadastro calculosCadastro = new CalculosCadastro(CalculosView.this);
                            calculosCadastro.loadCalculo(calculos);

                        }else if (item == 1) {
                            //DELETAR
                            boolean isDeletouComSucesso = calculosController.delete(calculos.getId());

                            if (isDeletouComSucesso){
                                Toast.makeText(CalculosView.this, "Contato deletado.", Toast.LENGTH_SHORT).show();
                                atualizarRegistros();

                            }else{
                                Toast.makeText(CalculosView.this, "Erro ao deletar o contato.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        dialogInterface.dismiss();
                    }
                }).show();

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Calculos calculos = calculosList.get(i);
        alertDialog(calculos);

    }
}