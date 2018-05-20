package com.airam.helpfisio.view.cadastro;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airam.helpfisio.R;
import com.airam.helpfisio.controller.HospitalController;
import com.airam.helpfisio.model.Hospital;

public class HospitalCadastro implements DialogInterface.OnShowListener, View.OnClickListener, DialogInterface.OnDismissListener {

    private HospitalController hospitalController;
    private AlertDialog dialog;

    private EditText editTextNome, editTextRua, editTextNumero, editTextBairro;
    private EditText editTextCidade, editTextUF, editTextTelefone, editTextDiretor;

    public HospitalCadastro (View v){

        //CRIA O CONTEXT
        Context context = v.getContext();

        hospitalController = new HospitalController(context);

        //CRIA O LAYOUT COMO ALERTDIALOG
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.hospitalcadastro, null);
        builder.setView(view);

        //ATRIBUI AS VARIVEIS AOS ITENS DO LAYOUT
        editTextNome = (EditText)view.findViewById(R.id.edtHospitalNome);
        editTextRua = (EditText)view.findViewById(R.id.edtRuaHospital);
        editTextNumero = (EditText)view.findViewById(R.id.edtNumeroHospital);
        editTextBairro = (EditText)view.findViewById(R.id.edtBairroHospital);
        editTextCidade = (EditText)view.findViewById(R.id.edtCidadeHospital);
        editTextUF = (EditText)view.findViewById(R.id.edtUFHospital);
        editTextTelefone = (EditText)view.findViewById(R.id.edtTelefoneHospital);
        editTextDiretor = (EditText)view.findViewById(R.id.edtDiretorHospital);

        //CRIA OS BUTTONS DO ALERTDIALOG
        builder.setPositiveButton("Salvar",null);
        builder.setNegativeButton("Voltar", null);

        //START O ALERTDIALOG
        dialog = builder.create();
        dialog.setOnShowListener(this);
        dialog.show();
    }

    @Override
    public void onShow(DialogInterface dialogInterface) {

        Button b = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        b.setId(DialogInterface.BUTTON_POSITIVE);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //ATRIBUIÇÃO DAS VARIAVEIS PARA STRINGS PARA FACILITAR NA ESTRUTURA DE CONDIÇÃO IF
        String nome = editTextNome.getText().toString();
        String rua = editTextRua.getText().toString();
        String numero = editTextNumero.getText().toString();
        String bairro = editTextBairro.getText().toString();
        String cidade = editTextCidade.getText().toString();
        String uf = editTextUF.getText().toString();
        String telefone = editTextTelefone.getText().toString();
        String diretor = editTextDiretor.getText().toString();

        //APRESENTA OS ERROS AO DEIXAR ALGUM ATRIBUTO EM BRANCO
        if (nome.length() == 0)
            editTextNome.setError("Digite o Nome!");
        if ( rua.length() == 0)
            editTextRua.setError("Digite a Rua!");
        if (numero.length() == 0)
            editTextNumero.setError("Digite o Número!");
        if (bairro.length() == 0)
            editTextBairro.setError("Digite o Bairro!");
        if ( cidade.length() == 0)
            editTextCidade.setError("Digite a Cidade!");
        if (uf.length() == 0)
            editTextUF.setError("Digite o UF!");
        if (telefone.length() == 0)
            editTextTelefone.setError("Digite o Numero Telefone!");
        if (diretor.length() == 0)
            editTextDiretor.setError("Digite o Nome do Diretor!");

        //SE TODOS OS CAMPOS FOREM PREENCHIDOS SERÁ EXECUTADA ESTÁ AÇÃO
        if (nome.length() != 0 && rua.length() != 0 && numero.length() != 0 && bairro.length() != 0
                && cidade.length() != 0 && uf.length() != 0 && telefone.length() != 0
                && diretor.length() != 0){

            int numeroHospital = Integer.parseInt(numero);
            int telefoneHospital = Integer.parseInt(telefone);

            //REGRAS PARA ARMAZENAR NO BANCO DE DADOS

            Context context = v.getContext();

            Hospital hospital = new Hospital();
            hospital.setNome(nome);
            hospital.setRua(rua);
            hospital.setNumero(numeroHospital);
            hospital.setBairro(bairro);
            hospital.setCidade(cidade);
            hospital.setUF(uf);
            hospital.setTelefone(telefoneHospital);
            hospital.setDiretor(diretor);

            boolean criadoComSucesso = hospitalController.insert(hospital);

            if (criadoComSucesso)
                Toast.makeText(context, "Hospital Cadastrado Com Sucesso.", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "Não Foi Possivel Cadastrar o Hospital.", Toast.LENGTH_SHORT).show();

            dialog.dismiss();


        }
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {

        hospitalController.closeDb();

    }
}
