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
import com.airam.helpfisio.model.DateUtil;
import com.airam.helpfisio.model.Fisioterapeuta;
import com.airam.helpfisio.view.FisioterapeutaView;

public class FisioterapeutaCadastro implements DialogInterface.OnShowListener, View.OnClickListener, DialogInterface.OnDismissListener{


    private FisioterapeutaController fisioterapeutaController;
    private AlertDialog dialog;

    private EditText editTextNome, editTextRG, editTextCPF, editTextData, editTextCrefito;
    private EditText editTextTelefone, editTextSobrenome, editTextSalario, editTextCargo, editTextPeso;

    private Fisioterapeuta fisioterapeuta;

    Context context;

    boolean criadoComSucesso;

    public FisioterapeutaCadastro(Context context){

        //CRIA O CONTEXT
        this.context = context;

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
        editTextPeso = (EditText) view.findViewById(R.id.edtFisioPeso);

        //CRIA OS BUTTONS DO ALERTDIALOG
        builder.setPositiveButton("Salvar", null);
        builder.setNegativeButton("Voltar", null);

        //START O ALERTDIALOG
        dialog = builder.create();
        dialog.setOnShowListener(this);
        dialog.show();

    }

    public void loadFisio(Fisioterapeuta fisioterapeuta){

        this.fisioterapeuta = fisioterapeuta;
        editTextNome.setText(fisioterapeuta.getNome());
        editTextRG.setText(String.valueOf(fisioterapeuta.getRg()));
        editTextCPF.setText(fisioterapeuta.getCpf());
        editTextData.setText(DateUtil.dateToString(fisioterapeuta.getData()));
        editTextCrefito.setText(String.valueOf(fisioterapeuta.getCrefito()));
        editTextCargo.setText(fisioterapeuta.getCargo());
        editTextTelefone.setText(String.valueOf(fisioterapeuta.getTelefone()));
        editTextSobrenome.setText(fisioterapeuta.getSobrenome());
        editTextSalario.setText(String.valueOf(fisioterapeuta.getSalario()));
        editTextPeso.setText(String.valueOf(fisioterapeuta.getPeso()));

    }

    @Override
    public void onShow(DialogInterface dialogInterface) {

        Button b = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        b.setId(DialogInterface.BUTTON_POSITIVE);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        insertfisio();

        if (criadoComSucesso) {
            Toast.makeText(context, "Fisioterapeuta Armazenado Com Sucesso.", Toast.LENGTH_SHORT).show();
            ((FisioterapeutaView) context).atualizarRegistros();
        } else
            Toast.makeText(context, "Não Foi Possivel Armazenar o Fisioterapeuta.", Toast.LENGTH_SHORT).show();

        dialog.dismiss();

    }

    public void insertfisio(){

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
        String peso = editTextPeso.getText().toString();

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
        if (peso.length() == 0)
            editTextPeso.setError("Digite o Peso(Kg) do Fisioterapeuta");

        //SE TODOS OS CAMPOS FOREM PREENCHIDOS SERÁ EXECUTADA ESTÁ AÇÃO
        if (nome.length() != 0 && rg.length() != 0 && cpf.length() != 0 && data.length() != 0
                && crefito.length() != 0 && cargo.length() != 0 && telefone.length() != 0
                && sobrenome.length() != 0 && salario.length() != 0 && peso.length() != 0) {

            if (fisioterapeuta == null){

                int rgInt = Integer.parseInt(rg);
                int crefitoInt = Integer.parseInt(crefito);
                double salarioDouble = Double.parseDouble(salario);
                double pesoDouble = Double.parseDouble(peso);

                //REGRAS PARA ARMAZENAR NO BANCO DE DADOS
                Fisioterapeuta fisioterapeuta = new Fisioterapeuta();
                fisioterapeuta.setNome(nome);
                fisioterapeuta.setRg(rgInt);
                fisioterapeuta.setCpf(cpf);
                fisioterapeuta.setData(DateUtil.stringToDate(data));
                fisioterapeuta.setCrefito(crefitoInt);
                fisioterapeuta.setCargo(cargo);
                fisioterapeuta.setTelefone(telefone);
                fisioterapeuta.setSobrenome(sobrenome);
                fisioterapeuta.setSalario(salarioDouble);
                fisioterapeuta.setPeso(pesoDouble);

                criadoComSucesso = fisioterapeutaController.insert(fisioterapeuta);

            }else {

                int rgInt = Integer.parseInt(rg);
                int crefitoInt = Integer.parseInt(crefito);
                double salarioDouble = Double.parseDouble(salario);
                double pesoDouble = Double.parseDouble(peso);

                fisioterapeuta.setNome(nome);
                fisioterapeuta.setRg(rgInt);
                fisioterapeuta.setCpf(cpf);
                fisioterapeuta.setData(DateUtil.stringToDate(data));
                fisioterapeuta.setCrefito(crefitoInt);
                fisioterapeuta.setCargo(cargo);
                fisioterapeuta.setTelefone(telefone);
                fisioterapeuta.setSobrenome(sobrenome);
                fisioterapeuta.setSalario(salarioDouble);
                fisioterapeuta.setPeso(pesoDouble);


                fisioterapeutaController.edit(fisioterapeuta, fisioterapeuta.getId());

                criadoComSucesso = true;
            }
        }
    }

        @Override
        public void onDismiss(DialogInterface dialogInterface) {
            fisioterapeutaController.closeDb();
        }
}