package com.airam.helpfisio.view.cadastro;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.airam.helpfisio.R;
import com.airam.helpfisio.controller.CalculosController;
import com.airam.helpfisio.controller.FisioterapeutaController;
import com.airam.helpfisio.controller.MedicoController;
import com.airam.helpfisio.controller.PacienteController;
import com.airam.helpfisio.model.Calculos;
import com.airam.helpfisio.model.DateUtil;
import com.airam.helpfisio.model.Fisioterapeuta;
import com.airam.helpfisio.model.Medico;
import com.airam.helpfisio.model.Paciente;
import com.airam.helpfisio.view.CalculosView;

import java.util.ArrayList;
import java.util.List;

public class CalculosCadastro implements DialogInterface.OnShowListener, View.OnClickListener, DialogInterface.OnDismissListener, AdapterView.OnItemSelectedListener{

    private CalculosController calculosController;
    private AlertDialog dialog;

    private EditText editTextNome, editTextResultado, editTextUnidade ;
    private EditText editTextData, editTextHora, editTextObs, editTextDesc;
    private AutoCompleteTextView autoComTexViewMedico, autoComTexViewFisio;
    private Spinner spnIdPaciente;

    private List<String> listaNomePaciente = new ArrayList<String>();
    private List<String> listaNomeFisio = new ArrayList<String>();
    private List<String> listaNomeMedico = new ArrayList<String>();

    private int pacienteId;

    List<Paciente> listPaciente;
    List<Fisioterapeuta> listObjFisio;
    List<Medico> listObjMedico;
    private PacienteController pacienteController;
    private FisioterapeutaController fisioterapeutaController;
    private MedicoController medicoController;

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listaNomePaciente);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnIdPaciente.setAdapter(adapter);

        spnIdPaciente.setOnItemSelectedListener(this);

        //AUTOCOMPLETETEXTVIEW
        fisioterapeutaController = new FisioterapeutaController(context);
        listaNomeFisio();
        autoCompleteFisio();

        medicoController = new MedicoController(context);
        listaNomeMedico();
        autoCompleteMedico();


        //CRIA OS BUTTONS DO ALERTDIALOG
        builder.setPositiveButton("Salvar", null);
        builder.setNegativeButton("Voltar", null);

        //START O ALERTDIALOG
        dialog = builder.create();
        dialog.setOnShowListener(this);
        dialog.show();

    }

    private void listaNomeMedico() {
        listObjMedico = medicoController.getAll();
        for (Medico medico : listObjMedico)
            listaNomeMedico.add(medico.getNome() + " CRM: " + medico.getCrm());
    }

    private void listaNomeFisio(){
        listObjFisio = fisioterapeutaController.getAll();
        for (Fisioterapeuta fisioterapeuta : listObjFisio)
            listaNomeFisio.add(fisioterapeuta.getNome() + " CREFITO: " + fisioterapeuta.getCrefito());
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
        autoComTexViewFisio.setText(calculos.getFisio());
        autoComTexViewMedico.setText(calculos.getMedico());

        editTextNome.setText(calculos.getNome());
        editTextData.setText(DateUtil.dateToString(calculos.getData()));
        editTextHora.setText(calculos.getHora());
        editTextObs.setText(calculos.getObservacoes());
        editTextResultado.setText(String.valueOf(calculos.getResultado()));
        editTextUnidade.setText(calculos.getUnidade());
        editTextDesc.setText(calculos.getDescricao());

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
        String unidade = editTextUnidade.getText().toString();
        String descricao = editTextDesc.getText().toString();
        String fisio = autoComTexViewFisio.getText().toString();
        String medico = autoComTexViewMedico.getText().toString();

        //APRESENTA OS ERROS AO DEIXAR ALGUM ATRIBUTO EM BRANCO
        if (nome.length() == 0)
            editTextNome.setError("Digite o Nome do Cálculo!");
        if (resultado.length() == 0)
            editTextResultado.setError("Digite o Resultado!");
        if (data.length() == 0)
            editTextData.setError("Digite a Data!");
        if (hora.length() == 0)
            editTextHora.setError("Digite a Hora!");
        if (unidade.length() == 0)
            editTextUnidade.setError("Digite a Unidade(Kg, Ml e etc.)");
        if (descricao.length() == 0)
            editTextDesc.setError("Digite ");
        if (fisio.length() == 0)
            autoComTexViewFisio.setError("Digite o Fisioterapeuta!");
        if (medico.length() == 0)
            autoComTexViewMedico.setError("Digite o Médico!");

        //SE TODOS OS CAMPOS FOREM PREENCHIDOS SERÁ EXECUTADA ESTÁ AÇÃO
        if (nome.length() != 0 && resultado.length() != 0 && data.length() != 0 && hora.length() != 0 &&
                unidade.length() != 0 && descricao.length() != 0 && fisio.length() != 0 && medico.length() != 0) {

            if (calculos == null) {
                double dbResultado = Double.parseDouble(resultado);

                //REGRAS PARA ARMAZENAR NO BANCO DE DADOS
                Calculos calculos = new Calculos();
                calculos.setIdPaciente(pacienteId);
                calculos.setNome(nome);
                calculos.setResultado(dbResultado);
                calculos.setData(DateUtil.stringToDate(data));
                calculos.setHora(hora);
                calculos.setObservacoes(obs);
                calculos.setUnidade(unidade);
                calculos.setDescricao(descricao);
                calculos.setMedico(medico);
                calculos.setFisio(fisio);

                criadoComSucesso = calculosController.insert(calculos);
            }else {
                double dbResultado = Double.parseDouble(resultado);

                calculos.setIdPaciente(pacienteId);
                calculos.setNome(nome);
                calculos.setResultado(dbResultado);
                calculos.setData(DateUtil.stringToDate(data));
                calculos.setHora(hora);
                calculos.setUnidade(unidade);
                calculos.setDescricao(descricao);
                calculos.setMedico(medico);
                calculos.setFisio(fisio);
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
        editTextUnidade = (EditText) view.findViewById(R.id.edtCalculosUnidade);
        editTextDesc = (EditText) view.findViewById(R.id.edtCalculosDesc);
        autoComTexViewFisio = (AutoCompleteTextView) view.findViewById(R.id.autoTextViewCalculosFisio);
        autoComTexViewMedico = (AutoCompleteTextView) view.findViewById(R.id.autoTextViewCalculosMedico);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Paciente paciente = listPaciente.get(i);
        pacienteId = paciente.getId();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void autoCompleteFisio(){
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, android.R.layout.select_dialog_item, listaNomeFisio);
        autoComTexViewFisio.setThreshold(1);
        autoComTexViewFisio.setAdapter(adapter2);

    }

    private void autoCompleteMedico() {

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, android.R.layout.select_dialog_item, listaNomeMedico);
        autoComTexViewMedico.setThreshold(1);
        autoComTexViewMedico.setAdapter(adapter2);

    }
}
