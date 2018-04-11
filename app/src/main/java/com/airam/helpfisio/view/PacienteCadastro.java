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
import com.airam.helpfisio.controller.PacienteController;
import com.airam.helpfisio.model.Paciente;

/**
 * Created by jonas on 10/11/2017.
 */

public class PacienteCadastro implements DialogInterface.OnShowListener, View.OnClickListener{

    private PacienteController pacienteController;
    private AlertDialog dialog;

    private EditText editTextNome, editTextRG, editTextCPF, editTextAltura, editTextPeso, editTextData;

    public PacienteCadastro(View v) {

        //CRIA O CONTEXT
        Context context = v.getContext();

        pacienteController = new PacienteController(context);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.pacientecadastro, null);
        builder.setView(view);

        editTextNome = (EditText)view.findViewById(R.id.edtPacienteNome);
        editTextRG = (EditText) view.findViewById(R.id.edtPacienteRG);
        editTextCPF = (EditText) view.findViewById(R.id.edtPacienteCPF);
        editTextAltura = (EditText) view.findViewById(R.id.edtAlturaPaciente);
        editTextPeso = (EditText) view.findViewById(R.id.edtPacientePeso);
        editTextData = (EditText) view.findViewById(R.id.edtPacienteData);

        //CRIA OS BUTTONS DO ALERTDIALOG
        builder.setPositiveButton("Salvar", null);
        builder.setNegativeButton("Voltar", null);

        dialog = builder.create();
        dialog.setOnShowListener(this);
        dialog.show();
    }

    @Override
    public void onShow(DialogInterface d){
        Button b = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        b.setId(DialogInterface.BUTTON_POSITIVE);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        //APRESENTA OS ERROS AO DEIXAR ALGUM ATRIBUTO EM BRANCO
        if(editTextNome.getText().toString().length() == 0)
            editTextNome.setError("Digite o Nome!");
        if (editTextData.getText().toString().length() == 0)
            editTextData.setError("Digite a Data de Nascimento!");
        if (editTextPeso.getText().toString().length() == 0)
            editTextPeso.setError("Digite o Peso!");
        if (editTextAltura.getText().toString().length() == 0)
            editTextAltura.setError("Digite a Altura!");
        if (editTextCPF.getText().toString().length() == 0)
            editTextCPF.setError("Digite o CPF!");
        if (editTextRG.getText().toString().length() == 0)
            editTextRG.setError("Digite o RG!");

        if (editTextNome.getText().toString().length() != 0 && editTextData.getText().toString().length() != 0 && editTextPeso.getText().toString().length() != 0 &&
                editTextAltura.getText().toString().length() != 0 && editTextCPF.getText().toString().length() != 0 && editTextRG.getText().toString().length() != 0){

            //REGRAS PARA ARMAZENAR NO BANCO DE DADOS
            Context context = v.getContext();

            String pacienteNome = editTextNome.getText().toString();
            //String pacienteRG = editTextRG.getText().toString();
            int pacienteRG = Integer.parseInt(editTextRG.getText().toString());
            String pacienteCPF = editTextCPF.getText().toString();
            //String pacienteAltura = editTextAltura.getText().toString();
            double pacienteAltura = Double.parseDouble(editTextAltura.getText().toString());
            double pacientePeso = Double.parseDouble(editTextPeso.getText().toString());
            String pacienteData = editTextData.getText().toString();

            Paciente paciente= new Paciente();
            paciente.setNome(pacienteNome);
            paciente.setRg(pacienteRG);
            paciente.setCpf(pacienteCPF);
            paciente.setAltura(pacienteAltura);
            paciente.setPeso(pacientePeso);
            paciente.setData(pacienteData);

            boolean criadoComSucesso = pacienteController.insert(paciente);

            if (criadoComSucesso)
                Toast.makeText(context, "Paciente Cadastrado Com Sucesso.", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "Não Foi Possivel Cadastrar o Paciente.", Toast.LENGTH_SHORT).show();

            dialog.dismiss();

        }
    }
}
