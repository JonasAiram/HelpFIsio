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
import com.airam.helpfisio.controller.MedicoController;
import com.airam.helpfisio.model.Medico;


public class MedicoCadastro implements DialogInterface.OnShowListener, View.OnClickListener, DialogInterface.OnDismissListener {

    private MedicoController medicoController;
    private AlertDialog dialog;

    private EditText editTextNome, editTextRG, editTextCPF, editTextData, editTextCRM, editTextCargo;
    private EditText editTextTelefone, editTextSobrenome, editTextSalario;

    public MedicoCadastro(View v) {

        //CRIA O CONTEXT
        final Context context = v.getContext();

        medicoController = new MedicoController(context);

        //CRIA O LAYOUT COMO ALERTDIALOG
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.medicocadastro, null);
        builder.setView(view);

        //ATRIBUI AS VARIVEIS AOS ITENS DO LAYOUT
        editTextNome = (EditText) view.findViewById(R.id.edtMedicoNome);
        editTextRG = (EditText) view.findViewById(R.id.edtMedicoRG);
        editTextCPF = (EditText) view.findViewById(R.id.edtMedicoCPF);
        editTextData = (EditText) view.findViewById(R.id.edtMedicoData);
        editTextCRM = (EditText) view.findViewById(R.id.edtMedicoCRM);
        editTextCargo = (EditText) view.findViewById(R.id.edtMedicoCargo);
        editTextTelefone = (EditText) view.findViewById(R.id.edtMedicoTelefone);
        editTextSobrenome = (EditText) view.findViewById(R.id.edtMedicoSobrenome);
        editTextSalario = (EditText) view.findViewById(R.id.edtMedicoSalario);

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
        String crm = editTextCRM.getText().toString();
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
        if (crm.length() == 0)
            editTextCRM.setError("Digite o CRM!");
        if (cargo.length() == 0)
            editTextCargo.setError("Digite o Cargo do Médico!");
        if (telefone.length() == 0)
            editTextTelefone.setError("Digite o Telefone!");
        if (sobrenome.length() == 0)
            editTextSobrenome.setError("Digite o Sobrenome!");
        if (salario.length() == 0)
            editTextSalario.setError("Digite o Salário do Médico!");

        //SE TODOS OS CAMPOS FOREM PREENCHIDOS SERÁ EXECUTADA ESTÁ AÇÃO
        if (nome.length() != 0 && rg.length() != 0 && cpf.length() != 0 && data.length() != 0
                && crm.length() != 0 && cargo.length() != 0 && telefone.length() != 0
                && sobrenome.length() != 0 && salario.length() != 0){

            int rgInt = Integer.parseInt(rg);
            int cpfInt = Integer.parseInt(cpf);
            int crmInt = Integer.parseInt(crm);
            double salarioDouble = Double.parseDouble(salario);

            //REGRAS PARA ARMAZENAR NO BANCO DE DADOS
            Context context = v.getContext();

            Medico medico = new Medico();
            medico.setNome(nome);
            medico.setRg(rgInt);
            medico.setCpf(cpfInt);
            medico.setData(data);
            medico.setCrm(crmInt);
            medico.setCargo(cargo);
            medico.setTelefone(telefone);
            medico.setSobrenome(sobrenome);
            medico.setSalario(salarioDouble);

            boolean criadoComSucesso = medicoController.insert(medico);

            if (criadoComSucesso)
                Toast.makeText(context, "Médico Cadastrado Com Sucesso.", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "Não Foi Possivel Cadastrar o Médico.", Toast.LENGTH_SHORT).show();

            dialog.dismiss();

        }
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {

        medicoController.closeDb();
    }
}
