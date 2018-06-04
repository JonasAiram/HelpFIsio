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
import com.airam.helpfisio.controller.HospitalController;
import com.airam.helpfisio.model.Hospital;
import com.airam.helpfisio.view.cadastro.HospitalCadastro;

import java.util.ArrayList;
import java.util.List;

public class HospitalView extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ListView listView;
    private EditText editText;
    ArrayAdapter<String> adapter;
    List<Hospital> hospitalList;
    private List<String> hospitalListNome = new ArrayList<String>();
    HospitalController hospitalController;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultalayout);

        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editTextPesquisar);
        imageView = (ImageView) findViewById(R.id.imgViewAdd);

        hospitalController = new HospitalController(this);
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
                HospitalView.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    @Override
    public void onClick(View view) {

        HospitalCadastro hospitalCadastro = new HospitalCadastro(this);
    }

    public void atualizarRegistros() {

        hospitalListNome.clear();

        hospitalList = new HospitalController(this).getAll();

        for (Hospital hospital : hospitalList)

            hospitalListNome.add("Nome: " + hospital.getNome() + " - Fone: " + hospital.getTelefone());

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hospitalListNome);
        listView.setAdapter(adapter);
    }

    public void alertDialog(final Hospital hospital){

        final CharSequence[] itens = {"editar","deletar"};

        new AlertDialog.Builder(this).setTitle("Detalhes do contato")
                .setItems(itens, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {

                        if (item == 0){
                            //EDITAR

                            HospitalCadastro hospitalCadastro = new HospitalCadastro(HospitalView.this);
                            hospitalCadastro.loadHospital(hospital);

                        }else if (item == 1) {
                            //DELETAR
                            boolean isDeletouComSucesso = hospitalController.delete(hospital.getId());

                            if (isDeletouComSucesso){
                                Toast.makeText(HospitalView.this, "Hospital deletado.", Toast.LENGTH_SHORT).show();
                                atualizarRegistros();

                            }else{
                                Toast.makeText(HospitalView.this, "Erro ao Deletar o Hospital.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        dialogInterface.dismiss();
                    }
                }).show();

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Hospital hospital = hospitalList.get(i);
        alertDialog(hospital);

    }

}
