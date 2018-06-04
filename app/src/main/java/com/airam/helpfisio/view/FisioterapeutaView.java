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
import com.airam.helpfisio.controller.FisioterapeutaController;
import com.airam.helpfisio.model.Fisioterapeuta;
import com.airam.helpfisio.view.cadastro.FisioterapeutaCadastro;

import java.util.ArrayList;
import java.util.List;

public class FisioterapeutaView extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ListView listView;
    private EditText editText;
    ArrayAdapter<String> adapter;
    List<Fisioterapeuta> fisioterapeutaList;
    private List<String> fisioListNome = new ArrayList<String>();
    private FisioterapeutaController fisioterapeutaController;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultalayout);

        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editTextPesquisar);
        imageView = (ImageView) findViewById(R.id.imgViewAdd);

        fisioterapeutaController = new FisioterapeutaController(this);
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
                FisioterapeutaView.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    @Override
    public void onClick(View view) {

        FisioterapeutaCadastro fisioterapeutaCadastro = new FisioterapeutaCadastro(this);
    }

    public void atualizarRegistros() {

        fisioListNome.clear();

        fisioterapeutaList = new FisioterapeutaController(this).getAll();

        for (Fisioterapeuta fisioterapeuta : fisioterapeutaList)

            fisioListNome.add("Nome: " + fisioterapeuta.getNome() + " - Crefito: " + fisioterapeuta.getCrefito());

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fisioListNome);
        listView.setAdapter(adapter);
    }

    public void alertDialog(final Fisioterapeuta fisioterapeuta){

        final CharSequence[] itens = {"Editar","Deletar"};

        new AlertDialog.Builder(this).setTitle("Detalhes do contato")
                .setItems(itens, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {

                        if (item == 0){
                            //EDITAR

                            FisioterapeutaCadastro fisioterapeutaCadastro = new FisioterapeutaCadastro(FisioterapeutaView.this);
                            fisioterapeutaCadastro.loadFisio(fisioterapeuta);

                        }else if (item == 1) {
                            //DELETAR
                            boolean isDeletouComSucesso = fisioterapeutaController.delete(fisioterapeuta.getId());

                            if (isDeletouComSucesso){
                                Toast.makeText(FisioterapeutaView.this, "Fisioterapeuta deletado.", Toast.LENGTH_SHORT).show();
                                atualizarRegistros();

                            }else{
                                Toast.makeText(FisioterapeutaView.this, "Erro ao Deletar o Fisioterapeuta.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        dialogInterface.dismiss();
                    }
                }).show();

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Fisioterapeuta fisioterapeuta = fisioterapeutaList.get(i);
        alertDialog(fisioterapeuta);

    }

}
