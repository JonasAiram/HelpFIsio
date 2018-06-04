package com.airam.helpfisio.view.cadastro;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airam.helpfisio.view.PacienteView;
import com.airam.helpfisio.R;
import com.airam.helpfisio.controller.PacienteController;
import com.airam.helpfisio.model.Paciente;

/**
 * Created by jonas on 10/11/2017.
 */

public class PacienteCadastro implements DialogInterface.OnShowListener, View.OnClickListener, DialogInterface.OnDismissListener{

    private PacienteController pacienteController;
    private AlertDialog dialog;

    private EditText editTextNome, editTextRG, editTextCPF, editTextAltura, editTextPeso;
    private EditText editTextData, editTextIdLeito, editTextTelefone, editTextSobrenome;

    private Paciente paciente;

    Context context;

    boolean criadoComSucesso;

    public PacienteCadastro(Context context) {

        //CRIA O CONTEXT
        this.context = context;

        pacienteController = new PacienteController(context);

        //ATRIBUI AS VARIVEIS AOS ITENS DO LAYOUT
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.pacientecadastro, null);
        builder.setView(view);

        //ATRIBUI AS VARIVEIS AOS ITENS DO LAYOUT
        editTextNome = (EditText)view.findViewById(R.id.edtPacienteNome);
        editTextRG = (EditText) view.findViewById(R.id.edtPacienteRG);
        editTextCPF = (EditText) view.findViewById(R.id.edtPacienteCPF);
        editTextAltura = (EditText) view.findViewById(R.id.edtAlturaPaciente);
        editTextPeso = (EditText) view.findViewById(R.id.edtPacientePeso);
        editTextData = (EditText) view.findViewById(R.id.edtPacienteData);
        //editTextIdLeito = (EditText) view.findViewById(R.id.spnLeito);
        editTextTelefone = (EditText) view.findViewById(R.id.edtPacienteTelefone);
        editTextSobrenome = (EditText) view.findViewById(R.id.edtPacienteSobrenome);

        //CRIA OS BUTTONS DO ALERTDIALOG
        builder.setPositiveButton("Salvar", null);
        builder.setNegativeButton("Voltar", null);

        dialog = builder.create();
        dialog.setOnShowListener(this);
        dialog.show();
    }

    public void loadPaciente(Paciente paciente){

        this.paciente = paciente;
        editTextNome.setText(paciente.getNome());
        editTextRG.setText(String.valueOf(paciente.getRg()));
        editTextCPF.setText(paciente.getCpf());
        editTextAltura.setText(String.valueOf(paciente.getAltura()));
        editTextPeso.setText(String.valueOf(paciente.getPeso()));
        editTextData.setText(paciente.getData());
        editTextTelefone.setText(String.valueOf(paciente.getTelefone()));
        editTextSobrenome.setText(paciente.getSobrenome());
    }

    @Override
    public void onShow(DialogInterface d){
        Button b = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        b.setId(DialogInterface.BUTTON_POSITIVE);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){


        insertPacient();

        if (criadoComSucesso) {
            Toast.makeText(context, "Paciente Armazenado Com Sucesso.", Toast.LENGTH_SHORT).show();
            ((PacienteView) context).atualizarRegistros();
        }
        else
            Toast.makeText(context, "Não Foi Possivel Armazenar o Paciente.", Toast.LENGTH_SHORT).show();

        dialog.dismiss();

    }

    public void insertPacient() {

        //ATRIBUIÇÃO DAS VARIAVEIS PARA STRINGS PARA FACILITAR NA ESTRUTURA DE CONDIÇÃO IF
        String pacienteNome = editTextNome.getText().toString();
        String pacienteRg = editTextData.getText().toString();
        String pacienteCpf = editTextCPF.getText().toString();
        String pacienteAltura = editTextAltura.getText().toString();
        String pacientePeso = editTextPeso.getText().toString();
        String pacienteData = editTextData.getText().toString();
        //String pacienteIdLeito = editTextIdLeito.getText().toString();
        String pacienteTelefone = editTextTelefone.getText().toString();
        String pacienteSobrenome = editTextSobrenome.getText().toString();

        //APRESENTA OS ERROS AO DEIXAR ALGUM ATRIBUTO EM BRANCO
        if (pacienteNome.length() == 0)
            editTextNome.setError("Digite o Nome!");
        if (pacienteRg.length() == 0)
            editTextRG.setError("Digite o Seu RG!");
        if (pacienteCpf.length() == 0)
            editTextCPF.setError("Digite o Seu CPF!");
        if (pacienteAltura.length() == 0)
            editTextAltura.setError("Digite a Altura!");
        if (pacientePeso.length() == 0)
            editTextPeso.setError("Digite o Seu Peso!");
        if (pacienteData.length() == 0)
            editTextData.setError("Digite a Sua Data de Nascimento!");
        // if (pacienteIdLeito.length() == 0)
        //  editTextIdLeito.setError("Selecione o Leito!");
        if (pacienteTelefone.length() == 0)
            editTextTelefone.setError("Digite o Seu Telefone!");
        if (pacienteSobrenome.length() == 0)
            editTextSobrenome.setError("Digite o Seu Sobrenome!");

        //SE TODOS OS CAMPOS FOREM PREENCHIDOS SERÁ EXECUTADA ESTÁ AÇÃO
        if (pacienteNome.length() != 0 && pacienteRg.length() != 0 && pacienteCpf.length() != 0
                && pacienteAltura.length() != 0 && pacientePeso.length() != 0 //&& pacienteIdLeito.length() != 0
                && pacienteTelefone.length() != 0 && pacienteSobrenome.length() != 0) {

            if (paciente == null){

                //CONVERTER PARA O TIPO DE DADOS QUE SERÁ ARMAZENADOS NO BANCO DE DADOS
                int Rg = Integer.parseInt(editTextRG.getText().toString());
                int telefone = Integer.parseInt(editTextTelefone.getText().toString());
                // int leito = Integer.parseInt(editTextIdLeito.getText().toString());
                double altura = Double.parseDouble(editTextAltura.getText().toString());
                double peso = Double.parseDouble(editTextPeso.getText().toString());

                Paciente paciente = new Paciente();
                paciente.setNome(pacienteNome);
                paciente.setRg(Rg);
                paciente.setCpf(pacienteCpf);
                paciente.setAltura(altura);
                paciente.setPeso(peso);
                paciente.setData(pacienteData);
                //paciente.setId_leito(leito);
                paciente.setTelefone(telefone);
                paciente.setSobrenome(pacienteSobrenome);

                criadoComSucesso = pacienteController.insert(paciente);
            }else{
                //CONVERTER PARA O TIPO DE DADOS QUE SERÁ ARMAZENADOS NO BANCO DE DADOS
                int Rg = Integer.parseInt(editTextRG.getText().toString());
                int telefone = Integer.parseInt(editTextTelefone.getText().toString());
                // int leito = Integer.parseInt(editTextIdLeito.getText().toString());
                double altura = Double.parseDouble(editTextAltura.getText().toString());
                double peso = Double.parseDouble(editTextPeso.getText().toString());

                paciente.setNome(pacienteNome);
                paciente.setRg(Rg);
                paciente.setCpf(pacienteCpf);
                paciente.setAltura(altura);
                paciente.setPeso(peso);
                paciente.setData(pacienteData);
                //paciente.setId_leito(leito);
                paciente.setTelefone(telefone);
                paciente.setSobrenome(pacienteSobrenome);
                pacienteController.edit(paciente, paciente.getId());
                criadoComSucesso = true;
            }
        }
    }
    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        pacienteController.closeDb();
    }
}
