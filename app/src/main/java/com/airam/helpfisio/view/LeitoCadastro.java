package com.airam.helpfisio.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airam.helpfisio.R;
import com.airam.helpfisio.controller.LeitoController;
import com.airam.helpfisio.model.Leito;

/**
 * Created by Jonas on 18/11/2017.
 */

public class LeitoCadastro implements DialogInterface.OnShowListener, View.OnClickListener, DialogInterface.OnDismissListener{

    private LeitoController leitoController;
    private AlertDialog dialog;

    private EditText editTextTipo, editTextQtd, editTextChefe, editTextAndar, editTextIdHospital;


    public LeitoCadastro(View v) {

        //CRIA O CONTEXT
        final Context context = v.getContext();

        leitoController = new LeitoController(context);

        //CRIA O LAYOUT COMO ALERTDIALOG
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.leitocadastro, null);
        builder.setView(view);

        //ATRIBUI AS VARIVEIS AOS ITENS DO LAYOUT
        editTextTipo = (EditText) view.findViewById(R.id.edtLeitoTipo);
        editTextQtd = (EditText) view.findViewById(R.id.edtLeitoQtd);
        editTextChefe = (EditText) view.findViewById(R.id.edtLeitoChefe);
        editTextAndar = (EditText) view.findViewById(R.id.edtLeitoAndar);

        //CRIA OS BUTTONS DO ALERTDIALOG
        builder.setPositiveButton("Salvar", null);
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
    public void onClick(View v){

        //ATRIBUIÇÃO DAS VARIAVEIS PARA STRINGS PARA FACILITAR NA ESTRUTURA DE CONDIÇÃO IF
        String tipo = editTextTipo.getText().toString();
        String qtd = editTextQtd.getText().toString();
        String chefe = editTextChefe.getText().toString();
        String andar = editTextAndar.getText().toString();

        //APRESENTA OS ERROS AO DEIXAR ALGUM ATRIBUTO EM BRANCO
        if (tipo.length() == 0)
            editTextTipo.setError("Digite o Tipo do Leito!");
        if (qtd.length() == 0)
            editTextQtd.setError("Digite a Quantidade!");
        if (chefe.length() == 0)
            editTextChefe.setError("Digite o Nome do Chefe!");
        if (andar.length() == 0)
            editTextAndar.setError("Digite o Andar do Leito!");

        //SE TODOS OS CAMPOS FOREM PREENCHIDOS SERÁ EXECUTADA ESTÁ AÇÃO
        if (tipo.length() != 0 && qtd.length() != 0 && chefe.length() != 0 && andar.length() != 0){

            int qtdLeito = Integer.parseInt(qtd);
            int andarLeito = Integer.parseInt(andar);

            //REGRAS PARA ARMAZENAR NO BANCO DE DADOS
            Context context = v.getContext();

            Leito leito = new Leito();
            leito.setTipo(tipo);
            leito.setQuantidade(qtdLeito);
            leito.setChefe(chefe);
            leito.setAndar(andarLeito);

            boolean criadoComSucesso = leitoController.insert(leito);

            if (criadoComSucesso)
                Toast.makeText(context, "Hospital Cadastrado Com Sucesso.", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "Não Foi Possivel Cadastrar o Hospital.", Toast.LENGTH_SHORT).show();

            dialog.dismiss();

        }
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface){
        leitoController.closeDb();
    }

}
