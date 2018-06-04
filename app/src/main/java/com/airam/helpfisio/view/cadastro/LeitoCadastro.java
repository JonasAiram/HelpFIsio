package com.airam.helpfisio.view.cadastro;

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
import com.airam.helpfisio.view.LeitoView;

/**
 * Created by Jonas on 18/11/2017.
 */

public class LeitoCadastro implements DialogInterface.OnShowListener, View.OnClickListener, DialogInterface.OnDismissListener{

    private LeitoController leitoController;
    private AlertDialog dialog;

    private EditText editTextTipo, editTextQtd, editTextChefe, editTextAndar, editTextIdHospital;

    private Leito leito;

    Context context;

    boolean criadoComSucesso;

    public LeitoCadastro(Context context) {

        //CRIA O CONTEXT
        this.context = context;

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

    public void loadLeito(Leito leito){

        this.leito = leito;
        editTextTipo.setText(leito.getTipo());
        editTextQtd.setText(String.valueOf(leito.getQuantidade()));
        editTextChefe.setText(leito.getChefe());
        editTextAndar.setText(String.valueOf(leito.getAndar()));

    }

    @Override
    public void onShow(DialogInterface dialogInterface) {

        Button b = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        b.setId(DialogInterface.BUTTON_POSITIVE);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        insertLeito();

        if (criadoComSucesso) {
            Toast.makeText(context, "Paciente Armazenado Com Sucesso.", Toast.LENGTH_SHORT).show();
            ((LeitoView) context).atualizarRegistros();
        } else
            Toast.makeText(context, "Não Foi Possivel Armazenar o Paciente.", Toast.LENGTH_SHORT).show();

        dialog.dismiss();

    }

    public  void insertLeito(){

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

            if (leito == null) {


                int qtdLeito = Integer.parseInt(qtd);
                int andarLeito = Integer.parseInt(andar);

                //REGRAS PARA ARMAZENAR NO BANCO DE DADOS
                Leito leito = new Leito();
                leito.setTipo(tipo);
                leito.setQuantidade(qtdLeito);
                leito.setChefe(chefe);
                leito.setAndar(andarLeito);

                criadoComSucesso = leitoController.insert(leito);
            }else {

                int qtdLeito = Integer.parseInt(qtd);
                int andarLeito = Integer.parseInt(andar);

                //REGRAS PARA ARMAZENAR NO BANCO DE DADOS
                leito.setTipo(tipo);
                leito.setQuantidade(qtdLeito);
                leito.setChefe(chefe);
                leito.setAndar(andarLeito);
                leitoController.edit(leito, leito.getId());
                criadoComSucesso = true;

            }
        }
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface){
        leitoController.closeDb();
    }

}
