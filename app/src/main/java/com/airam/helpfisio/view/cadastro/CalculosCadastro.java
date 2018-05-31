package com.airam.helpfisio.view.cadastro;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.airam.helpfisio.R;
import com.airam.helpfisio.controller.CalculosController;
import com.airam.helpfisio.model.Calculos;
import com.airam.helpfisio.view.CalculosView;

public class CalculosCadastro implements DialogInterface.OnShowListener, View.OnClickListener, DialogInterface.OnDismissListener {

    private CalculosController calculosController;
    private AlertDialog dialog;

    private EditText editTextNome, editTextResultado;
    private EditText editTextData, editTextHora, editTextObs;
    private Spinner spnIdPaciente;

    Context context;

    boolean criadoComSucesso;


    public CalculosCadastro(View v){

        //CRIA O CONTEXT
        context = v.getContext();

        calculosController = new CalculosController(context);

        //CRIA O LAYOUT COMO ALERTDIALOG
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.calculoscadastro, null);
        builder.setView(view);

        //ATRIBUI AS VARIVEIS AOS ITENS DO LAYOUT
        findViewById(view);

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

        insertCalculo();

            if (criadoComSucesso) {
                Toast.makeText(context, "Cálculo Armazenado Com Sucesso.", Toast.LENGTH_SHORT).show();
                ((CalculosView) context).atualizarRegistros();
            }
            else
                Toast.makeText(context, "Não Foi Possivel Armazenar o Cálculo.", Toast.LENGTH_SHORT).show();

            dialog.dismiss();

        }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        calculosController.closeDb();
    }

    public void insertCalculo() {

        //ATRIBUIÇÃO DAS VARIAVEIS PARA STRINGS PARA FACILITAR NA ESTRUTURA DE CONDIÇÃO IF
        String nome = editTextNome.getText().toString();
        String resultado = editTextResultado.getText().toString();
        String data = editTextData.getText().toString();
        String hora = editTextHora.getText().toString();
        String obs = editTextObs.getText().toString();

        //APRESENTA OS ERROS AO DEIXAR ALGUM ATRIBUTO EM BRANCO
        if (nome.length() == 0)
            editTextNome.setError("Digite o Nome do Cálculo!");
        if (resultado.length() == 0)
            editTextResultado.setError("Digite o Resultado!");
        if (data.length() == 0)
            editTextData.setError("Digite a Data!");
        if (hora.length() == 0)
            editTextHora.setError("Digite a Hora!");


        //SE TODOS OS CAMPOS FOREM PREENCHIDOS SERÁ EXECUTADA ESTÁ AÇÃO
        if (nome.length() != 0 && resultado.length() != 0 && data.length() != 0 && hora.length() != 0) {

            double dbResultado = Double.parseDouble(resultado);

            //REGRAS PARA ARMAZENAR NO BANCO DE DADOS
            Calculos calculos = new Calculos();
            calculos.setNome(nome);
            calculos.setResultado(dbResultado);
            calculos.setData(data);
            calculos.setHora(hora);
            criadoComSucesso = calculosController.insert(calculos);
        }
    }

    public void findViewById(View view){
        //ATRIBUI AS VARIVEIS AOS ITENS DO LAYOUT
        spnIdPaciente = (Spinner) view.findViewById(R.id.spnCalculosIdPaciente);
        editTextNome = (EditText) view.findViewById(R.id.edtCalculo);
        editTextResultado = (EditText) view.findViewById(R.id.edtCalculosResultado);
        editTextData = (EditText) view.findViewById(R.id.edtCalculosData);
        editTextHora = (EditText) view.findViewById(R.id.edtCalculosHora);
        editTextObs = (EditText) view.findViewById(R.id.edtCalculosObs);
    }
}
