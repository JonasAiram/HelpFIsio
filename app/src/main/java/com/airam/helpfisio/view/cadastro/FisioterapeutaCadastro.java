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
import com.airam.helpfisio.controller.FisioterapeutaController;
import com.airam.helpfisio.model.Fisioterapeuta;
import com.airam.helpfisio.view.FisioterapeutaView;

public class FisioterapeutaCadastro implements DialogInterface.OnShowListener, View.OnClickListener, DialogInterface.OnDismissListener{


    private FisioterapeutaController fisioterapeutaController;
    private AlertDialog dialog;

    private EditText editTextNome, editTextRG, editTextCPF, editTextData, editTextCrefito;
    private EditText editTextTelefone, editTextSobrenome, editTextSalario, editTextCargo;

    Context context;

    public FisioterapeutaCadastro(View v){

        //CRIA O CONTEXT
        context = v.getContext();

        fisioterapeutaController = new FisioterapeutaController(context);

        //CRIA O LAYOUT COMO ALERTDIALOG
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.fisiocadastro, null);
        builder.setView(view);

        //ATRIBUI AS VARIAVEIS AOS ITENS DO LAYOUT
        editTextNome = (EditText) view.findViewById(R.id.edtFisioNome);
        editTextRG = (EditText) view.findViewById(R.id.edtFisioRG);
        editTextCPF = (EditText) view.findViewById(R.id.edtFisioCPF);
        editTextData = (EditText) view.findViewById(R.id.edtFisioData);
        editTextCrefito = (EditText) view.findViewById(R.id.edtFisioCrefito);
        editTextCargo = (EditText) view.findViewById(R.id.edtFisioCargo);
        editTextTelefone = (EditText) view.findViewById(R.id.edtFisioTelefone);
        editTextSobrenome = (EditText) view.findViewById(R.id.edtFisioSobrenome);
        editTextSalario = (EditText) view.findViewById(R.id.edtFisioSalario);

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
        String nome = editTextNome.getText().toString();
        String rg = editTextRG.getText().toString();
        String cpf = editTextCPF.getText().toString();
        String data = editTextData.getText().toString();
        String crefito = editTextCrefito.getText().toString();
        String cargo = editTextCargo.getText().toString();
        String telefone = editTextTelefone.getText().toString();
        String sobrenome = editTextSobrenome.getText().toString();
        String salario = editTextSalario.getText().toString();

        //APRESENTA OS ERROS AO DEIXAR ALGUM ATRIBUTO EM BRANCO

        if (nome.length() == 0)
            editTextNome.setError("Digite o Nome!");
        if (rg.length() == 0)
            editTextRG.setError("Digite o RG!");
        if (cpf.length() == 0)
            editTextCPF.setError("Digite o CPF!");
        if (data.length() == 0)
            editTextData.setError("Digite a Data de Nascimento!");
        if (crefito.length() == 0)
            editTextCrefito.setError("Digite o Crefito!");
        if (cargo.length() == 0)
            editTextCargo.setError("Digite o Cargo do Fisioterapeuta!");
        if (telefone.length() == 0)
            editTextTelefone.setError("Digite o Telefone!");
        if (sobrenome.length() == 0)
            editTextSobrenome.setError("Digite o Sobrenome!");
        if (salario.length() == 0)
            editTextSalario.setError("Digite o Salário do Fisioterapeuta!");

        //SE TODOS OS CAMPOS FOREM PREENCHIDOS SERÁ EXECUTADA ESTÁ AÇÃO
        if (nome.length() != 0 && rg.length() != 0 && cpf.length() != 0 && data.length() != 0
                && crefito.length() != 0 && cargo.length() != 0 && telefone.length() != 0
                && sobrenome.length() != 0 && salario.length() != 0) {

            int rgInt = Integer.parseInt(rg);
            int crefitoInt = Integer.parseInt(crefito);
            int telefoneInt = Integer.parseInt(telefone);
            double salarioDouble = Double.parseDouble(salario);

            //REGRAS PARA ARMAZENAR NO BANCO DE DADOS
            Fisioterapeuta fisioterapeuta = new Fisioterapeuta();
            fisioterapeuta.setNome(nome);
            fisioterapeuta.setRg(rgInt);
            fisioterapeuta.setCpf(cpf);
            fisioterapeuta.setData(data);
            fisioterapeuta.setCrefito(crefitoInt);
            fisioterapeuta.setCargo(cargo);
            fisioterapeuta.setTelefone(telefoneInt);
            fisioterapeuta.setSobrenome(sobrenome);
            fisioterapeuta.setSalario(salarioDouble);

            boolean criadoComSucesso = fisioterapeutaController.insert(fisioterapeuta);

            if (criadoComSucesso) {
                Toast.makeText(context, "Médico Cadastrado Com Sucesso.", Toast.LENGTH_SHORT).show();
                ((FisioterapeutaView) context).atualizarRegistros();
            }
            else
                Toast.makeText(context, "Não Foi Possivel Cadastrar o Médico.", Toast.LENGTH_SHORT).show();

            dialog.dismiss();

        }
    }

        @Override
        public void onDismiss(DialogInterface dialogInterface) {
            fisioterapeutaController.closeDb();
        }
}