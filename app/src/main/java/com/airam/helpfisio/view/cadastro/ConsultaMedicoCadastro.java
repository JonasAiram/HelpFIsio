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
import com.airam.helpfisio.controller.ConsultaMedicoController;
import com.airam.helpfisio.model.ConsultaMedico;
import com.airam.helpfisio.view.ConsultaMedicoView;

/**
 * Created by Jonas on 19/04/2018.
 */

public class ConsultaMedicoCadastro implements DialogInterface.OnShowListener, View.OnClickListener, DialogInterface.OnDismissListener {

    private ConsultaMedicoController consultaMedicoController;
    private AlertDialog dialog;

    private EditText editTextDescricao, editTextMedicacao, editTextTratamento, editTextData, editTextHora, editTextEspecialidade;

    Context context;

    public ConsultaMedicoCadastro(View v){

        //CRIA CONTEXT
        context = v.getContext();

        consultaMedicoController = new ConsultaMedicoController(context);

        //CRIA O LAYOUT COMO ALERT DIALOG
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.consultamedico, null);
        builder.setView(view);

        //ATRIBUI AS VARIAVEIS AOS ITENS DO LAYOUT
        editTextDescricao = (EditText) view.findViewById(R.id.edtConsMedicoDesc);
        editTextMedicacao = (EditText) view.findViewById(R.id.edtConsMedicoMedicamento);
        editTextTratamento = (EditText) view.findViewById(R.id.edtConsMedicoTratamento);
        editTextData = (EditText) view.findViewById(R.id.edtConsMedicoData);
        editTextHora= (EditText) view.findViewById(R.id.edtConsMedicoHora);
        editTextEspecialidade = (EditText) view.findViewById(R.id.edtConsMedicoEspecialidade);

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
    public void onClick(View v) {

        //ATRIBUIÇÃO DAS VARIAVEIS PARA STRINGS PARA FACILITAR NA ESTRUTURA DE CONDIÇÃO IF
        String descricao = editTextDescricao.getText().toString();
        String medicamento = editTextMedicacao.getText().toString();
        String tratamento = editTextTratamento.getText().toString();
        String data = editTextData.getText().toString();
        String hora = editTextHora.getText().toString();

        //APRESENTA OS ERROS AO DEIXAR ALGUM ATRIBUTO EM BRANCO
        if (descricao.length() == 0)
            editTextDescricao.setError("Digite a Descrição!");
        if (medicamento.length() == 0)
            editTextMedicacao.setError("Digite a Medicação Preescrita!");
        if (tratamento.length() == 0)
            editTextTratamento.setError("Digite o Tratamento!");
        if (data.length() == 0)
            editTextData.setError("Digite a Data da Consulta!");
        if (hora.length() == 0)
            editTextHora.setError("Digite a Hora da Comsulta!");

        //SE TODOS OS CAMPOS FOREM PREENCHIDOS SERÁ EXECUTADA ESTÁ AÇÃO
        if (descricao.length() != 0 && medicamento.length() != 0 && tratamento.length() != 0 &&
                data.length() != 0 && hora.length() != 0){

            //REGRAS PARA ARMAZENAR NO BANCO DE DADOS
            ConsultaMedico consultaMedico = new ConsultaMedico();
            consultaMedico.setDescricao(descricao);
            consultaMedico.setMedicacao(medicamento);
            consultaMedico.setTratamento(tratamento);
            consultaMedico.setData(data);
            consultaMedico.setHora(hora);

            boolean criadoComSucesso = consultaMedicoController.insert(consultaMedico);

            if (criadoComSucesso) {
                Toast.makeText(context, "Consulta Cadastrada Com Sucesso.", Toast.LENGTH_SHORT).show();
                ((ConsultaMedicoView) context).atualizarRegistros();
            }
            else
                Toast.makeText(context, "Não Foi Possivel Cadastrar a Consulta.", Toast.LENGTH_SHORT).show();

            dialog.dismiss();

        }
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        consultaMedicoController.closeDb();

    }
}
