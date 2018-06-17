package com.airam.helpfisio.view.cadastro;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.airam.helpfisio.R;
import com.airam.helpfisio.controller.CalculosController;
import com.airam.helpfisio.controller.PacienteController;
import com.airam.helpfisio.model.Calculos;
import com.airam.helpfisio.model.Paciente;
import com.airam.helpfisio.view.CalculosView;

import java.util.ArrayList;
import java.util.List;

public class CalculosCadastro implements DialogInterface.OnShowListener, View.OnClickListener, DialogInterface.OnDismissListener, AdapterView.OnItemSelectedListener{

    private CalculosController calculosController;
    private AlertDialog dialog;

    private EditText editTextNome, editTextResultado;
    private EditText editTextData, editTextHora, editTextObs;
    private Spinner spnIdPaciente;

    ArrayAdapter<String> adapter;

    private List<String> listaNomePaciente = new ArrayList<String>();

    private int pacienteId;

    List<Paciente> listPaciente;
    private PacienteController pacienteController;

    private Calculos calculos;

    Context context;

    boolean criadoComSucesso;

    public CalculosCadastro(Context context){

        //PEGA O CONTEXT
        this.context= context;

        calculosController = new CalculosController(context);

        //CRIA O LAYOUT COMO ALERTDIALOG
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.calculoscadastro, null);
        builder.setView(view);

        //ATRIBUI AS VARIVEIS AOS ITENS DO LAYOUT
        findViewById(view);

        //SPINNER
        pacienteController = new PacienteController(context);
        arrayIdPaciente();

        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listaNomePaciente);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnIdPaciente.setAdapter(adapter);

        spnIdPaciente.setOnItemSelectedListener(this);

        /*int i = spnIdPaciente.getSelectedItemPosition();
        Paciente paciente = listPaciente.get(i);
        pacienteId = paciente.getId();*/

        //CRIA OS BUTTONS DO ALERTDIALOG
        builder.setPositiveButton("Salvar", null);
        builder.setNegativeButton("Voltar", null);

        //START O ALERTDIALOG
        dialog = builder.create();
        dialog.setOnShowListener(this);
        dialog.show();

    }

    private void arrayIdPaciente() {

        listPaciente = pacienteController.getAll();
        for (Paciente paciente : listPaciente){

            listaNomePaciente.add(paciente.getNome() + " CPF: " + paciente.getCpf());

        }
    }

    public void loadCalculo(Calculos calculos){

        this.calculos = calculos;

        spnIdPaciente.setSelection(getIndexPacienteId(calculos.getIdPaciente()));

        editTextNome.setText(calculos.getNome());
        editTextData.setText(calculos.getData());
        editTextHora.setText(calculos.getHora());
        editTextObs.setText(calculos.getObservacoes());
        editTextResultado.setText(String.valueOf(calculos.getResultado()));

    }

    @Override
    public void onShow(DialogInterface dialogInterface) {

        Button b = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        b.setId(DialogInterface.BUTTON_POSITIVE);
        b.setOnClickListener(this);
    }

    private int getIndexPacienteId(int pacienteId){
        for (int index = 0; index < listPaciente.size(); index++){
            Paciente paciente = listPaciente.get(index);
            if (pacienteId == paciente.getId())
                return index;
        }
        return 0;
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

            if (calculos == null) {
                double dbResultado = Double.parseDouble(resultado);

                //REGRAS PARA ARMAZENAR NO BANCO DE DADOS
                Calculos calculos = new Calculos();

                calculos.setIdPaciente(pacienteId);
                calculos.setNome(nome);
                calculos.setResultado(dbResultado);
                calculos.setData(data);
                calculos.setHora(hora);
                calculos.setObservacoes(obs);
                criadoComSucesso = calculosController.insert(calculos);
            }else {
                double dbResultado = Double.parseDouble(resultado);

                calculos.setIdPaciente(pacienteId);
                calculos.setNome(nome);
                calculos.setResultado(dbResultado);
                calculos.setData(data);
                calculos.setHora(hora);
                calculos.setObservacoes(obs);
                calculosController.edit(calculos, calculos.getId());
                criadoComSucesso = true;
            }
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Paciente paciente = listPaciente.get(i);
        pacienteId = paciente.getId();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
