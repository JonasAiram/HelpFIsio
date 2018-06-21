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
import com.airam.helpfisio.controller.LeitoController;
import com.airam.helpfisio.model.Hospital;
import com.airam.helpfisio.model.Leito;
import com.airam.helpfisio.view.cadastro.LeitoCadastro;

import java.util.ArrayList;
import java.util.List;

public class LeitoView extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ListView listView;
    private EditText editText;
    ArrayAdapter<String> adapter;
    List<Leito> leitoList;
    private List<String> leitoListNome = new ArrayList<String>();
    private LeitoController leitoController;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultalayout);

        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editTextPesquisar);
        imageView = (ImageView) findViewById(R.id.imgViewAdd);

        leitoController = new LeitoController(this);
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
                LeitoView.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    public void onClick(View view) {

        LeitoCadastro leitoCadastro = new LeitoCadastro(this);
    }

    public void atualizarRegistros() {

        leitoListNome.clear();

        leitoList = new LeitoController(this).getAll();

        Hospital hospital;

        for (Leito leito : leitoList) {

            hospital = new LeitoController(this).buscarNomePeloId(leito.getId_Hospital());
            leitoListNome.add("Tipo: " + leito.getTipo() + " - Qtd: " + leito.getQuantidade() + "Hospital: " + hospital.getNome());
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, leitoListNome);
        listView.setAdapter(adapter);

    }

    public void alertDialog(final Leito leito){

        final CharSequence[] itens = {"editar","deletar"};

        new AlertDialog.Builder(this).setTitle("Detalhes do contato")
                .setItems(itens, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {

                        if (item == 0){
                            //EDITAR

                            LeitoCadastro leitoCadastro = new LeitoCadastro(LeitoView.this);
                            leitoCadastro.loadLeito(leito);

                        }else if (item == 1) {
                            //DELETAR
                            boolean isDeletouComSucesso = leitoController.delete(leito.getId());

                            if (isDeletouComSucesso){
                                Toast.makeText(LeitoView.this, "Leito deletado.", Toast.LENGTH_SHORT).show();
                                atualizarRegistros();

                            }else{
                                Toast.makeText(LeitoView.this, "Erro ao Deletar o Leito.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        dialogInterface.dismiss();
                    }
                }).show();

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Leito leito = leitoList.get(i);
        alertDialog(leito);

    }

}
