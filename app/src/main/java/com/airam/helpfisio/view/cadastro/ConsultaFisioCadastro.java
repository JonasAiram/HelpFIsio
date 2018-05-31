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
import com.airam.helpfisio.controller.ConsultaFisioController;
import com.airam.helpfisio.model.ConsultaFisio;
import com.airam.helpfisio.view.ConsultaFisioView;

public class ConsultaFisioCadastro implements DialogInterface.OnShowListener, View.OnClickListener, DialogInterface.OnDismissListener{

    private ConsultaFisioController consultaFisioController;
    private AlertDialog dialog;

    private EditText editTextDescricao, editTextPatologia, editTextTratamento, editTextData, editTextHora;

    Context context;

    public ConsultaFisioCadastro(View v){

        //CRIA O CONTEXT
        context = v.getContext();

        consultaFisioController = new ConsultaFisioController(context);

        //CRIA O LAYOUT COMO ALERTDIALOG
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.consultafisio, null);
        builder.setView(view);

        //ATRIBUI AS VARIAVEIS AOS ITENS DO LAYOUT
        editTextDescricao = (EditText) view.findViewById(R.id.edtConsFisioDesc);
        editTextPatologia= (EditText) view.findViewById(R.id.edtConsFisioPatologia);
        editTextTratamento = (EditText) view.findViewById(R.id.edtConsFisioTratamento);
        editTextData = (EditText) view.findViewById(R.id.edtConsFisioData);
        editTextHora= (EditText) view.findViewById(R.id.edtConsFisioHora);

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
        String descricao = editTextDescricao.getText().toString();
        String patologia = editTextPatologia.getText().toString();
        String tratamento = editTextTratamento.getText().toString();
        String data = editTextData.getText().toString();
        String hora = editTextHora.getText().toString();

        //APRESENTA OS ERROS AO DEIXAR ALGUM ATRIBUTO EM BRANCO
        if (descricao.length() == 0)
            editTextDescricao.setError("Digite a Descrição!");
        if (patologia.length() == 0)
            editTextPatologia.setError("Digite a Patologia do Paciente!");
        if (tratamento.length() == 0)
            editTextTratamento.setError("Digite o Tratamento!");
        if (data.length() == 0)
            editTextData.setError("Digite a Data da Consulta!");
        if (hora.length() == 0)
            editTextHora.setError("Digite a Hora da Comsulta!");

        //SE TODOS OS CAMPOS FOREM PREENCHIDOS SERÁ EXECUTADA ESTÁ AÇÃO
        if (descricao.length() != 0 && patologia.length() != 0 && tratamento.length() != 0 &&
                data.length() != 0 && hora.length() != 0){

            //REGRAS PARA ARMAZENAR NO BANCO DE DADOS
            ConsultaFisio consultaFisio = new ConsultaFisio();
            consultaFisio.setDescricao(descricao);
            consultaFisio.setPatologia(patologia);
            consultaFisio.setTratamento(tratamento);
            consultaFisio.setData(data);
            consultaFisio.setHora(hora);

            boolean criadoComSucesso = consultaFisioController.insert(consultaFisio);

            if (criadoComSucesso) {
                Toast.makeText(context, "Consulta Cadastrada Com Sucesso.", Toast.LENGTH_SHORT).show();
                ((ConsultaFisioView) context).atualizarRegistros();
            }
            else
                Toast.makeText(context, "Não Foi Possivel Cadastrar a Consulta.", Toast.LENGTH_SHORT).show();

            dialog.dismiss();

        }
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        consultaFisioController.closeDb();
    }
}
