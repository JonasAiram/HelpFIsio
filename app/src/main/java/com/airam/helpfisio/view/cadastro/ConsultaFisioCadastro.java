package com.airam.helpfisio.view.cadastro;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.airam.helpfisio.R;
import com.airam.helpfisio.controller.ConsultaFisioController;
import com.airam.helpfisio.controller.FisioterapeutaController;
import com.airam.helpfisio.controller.PacienteController;
import com.airam.helpfisio.model.ConsultaFisio;
import com.airam.helpfisio.model.DateUtil;
import com.airam.helpfisio.model.Fisioterapeuta;
import com.airam.helpfisio.model.Paciente;
import com.airam.helpfisio.view.ConsultaFisioView;

import java.util.ArrayList;
import java.util.List;

public class ConsultaFisioCadastro implements DialogInterface.OnShowListener, View.OnClickListener, DialogInterface.OnDismissListener{

    private ConsultaFisioController consultaFisioController;
    private PacienteController pacienteController;
    private FisioterapeutaController fisioterapeutaController;

    private ConsultaFisio consultaFisio;

    private AlertDialog dialog;
    private EditText editTextDescricao, editTextPatologia, editTextTratamento, editTextData, editTextHora;
    private EditText editTextEspecialidade, editTextValorPago, editTextValorConsulta;

    Spinner spnFisioId, spnIdPaciente;

    private int pacienteid, fisioid;
    private String pacienteNome;

    private List<String> listaNomeFisio = new ArrayList<String>();
    private List<String> listaNomePaciente = new ArrayList<String>();
    List<Fisioterapeuta> listFisioObj;
    List<Paciente> listObjPaciente;

    Context context;

    boolean criadoComSucesso;

    public ConsultaFisioCadastro(Context context){

        //CRIA O CONTEXT
        this.context = context;

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
        editTextEspecialidade = (EditText) view.findViewById(R.id.edtConsFisioEspecialidade);
        editTextValorConsulta = (EditText) view.findViewById(R.id.edtConsFisioValor);
        editTextValorPago = (EditText) view.findViewById(R.id.edtConsFisioValorPago);

        spnFisioId = (Spinner) view.findViewById(R.id.spnConsultaFisioId);
        spnIdPaciente = (Spinner) view.findViewById(R.id.spnConsultaFisioIdPaciente);

        //SPINNER FISIO
        fisioterapeutaController = new FisioterapeutaController(context);
        arrayIdFisio();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listaNomeFisio);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFisioId.setAdapter(adapter);
        spnIdFisioItemSelected();

        //SPINNER PACIENTE
        pacienteController = new PacienteController(context);
        arrayIdPaciente();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listaNomePaciente);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnIdPaciente.setAdapter(adapter2);
        spnIdPacienteItemSelected();

        //CRIA OS BUTTONS DO ALERTDIALOG
        builder.setPositiveButton("Salvar", null);
        builder.setNegativeButton("Voltar", null);

        //START O ALERTDIALOG
        dialog = builder.create();
        dialog.setOnShowListener(this);
        dialog.show();

    }

    private void arrayIdFisio() {
        listFisioObj = fisioterapeutaController.getAll();
        for (Fisioterapeuta fisioterapeuta : listFisioObj)
            listaNomeFisio.add(fisioterapeuta.getNome() + " CREFITO: " + fisioterapeuta.getCrefito());
    }

    private void arrayIdPaciente() {
        listObjPaciente = pacienteController.getAll();
        for (Paciente paciente : listObjPaciente)
            listaNomePaciente.add(paciente.getNome() + " CPF: " + paciente.getCpf());
    }

    public void loadConsulta(ConsultaFisio consultaFisio){

        this.consultaFisio = consultaFisio;

        Log.e("LOAD id PACIENTE", "" + consultaFisio.getIdFisio());
        spnIdPaciente.setSelection(getIndexPacienteId(consultaFisio.getIdPaciente()));
        spnFisioId.setSelection(getIndexFisioId(consultaFisio.getIdFisio()));

        editTextDescricao.setText(consultaFisio.getDescricao());
        editTextPatologia.setText(consultaFisio.getPatologia());
        editTextTratamento.setText(consultaFisio.getTratamento());
        editTextData.setText(DateUtil.dateToString(consultaFisio.getData()));
        editTextHora.setText(consultaFisio.getHora());
        editTextEspecialidade.setText(consultaFisio.getEspecialidade());
        editTextValorConsulta.setText(String.valueOf(consultaFisio.getValorConsulta()));
        editTextValorPago.setText(String.valueOf(consultaFisio.getValorPago()));

    }

    private int getIndexFisioId(int idFisio) {
        for (int index = 0; index < listFisioObj.size(); index++){
         Fisioterapeuta fisioterapeuta = listFisioObj.get(index);
         if (idFisio == fisioterapeuta.getId())
             return index;
        }

        return 0;
    }

    private int getIndexPacienteId(int idPaciente) {
        for (int index = 0; index < listObjPaciente.size(); index++){
            Paciente paciente = listObjPaciente.get(index);
            if (idPaciente == paciente.getId())
                return index;
        }
        return 0;
    }

    @Override
    public void onShow(DialogInterface dialogInterface) {

        Button b = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        b.setId(DialogInterface.BUTTON_POSITIVE);
        b.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        insertConsulta();

        if (criadoComSucesso) {
            Toast.makeText(context, "Consulta Armazenada Com Sucesso.", Toast.LENGTH_SHORT).show();
            ((ConsultaFisioView) context).atualizarRegistros();
        } else
            Toast.makeText(context, "Não Foi Possivel Armazenar a Consulta.", Toast.LENGTH_SHORT).show();

        dialog.dismiss();
    }

    public void insertConsulta(){

        //ATRIBUIÇÃO DAS VARIAVEIS PARA STRINGS PARA FACILITAR NA ESTRUTURA DE CONDIÇÃO IF
        String descricao = editTextDescricao.getText().toString();
        String patologia = editTextPatologia.getText().toString();
        String tratamento = editTextTratamento.getText().toString();
        String data = editTextData.getText().toString();
        String hora = editTextHora.getText().toString();
        String especialidade = editTextEspecialidade.getText().toString();

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

            if (consultaFisio == null) {

                double valorConsDouble = Double.parseDouble(editTextValorConsulta.getText().toString());
                double valorPagoDouble = Double.parseDouble(editTextValorPago.getText().toString());

                //REGRAS PARA ARMAZENAR NO BANCO DE DADOS
                ConsultaFisio consultaFisio = new ConsultaFisio();
                consultaFisio.setDescricao(descricao);
                consultaFisio.setPatologia(patologia);
                consultaFisio.setTratamento(tratamento);
                consultaFisio.setData(DateUtil.stringToDate(data));
                consultaFisio.setHora(hora);
                consultaFisio.setEspecialidade(especialidade);
                consultaFisio.setValorConsulta(valorConsDouble);
                consultaFisio.setValorPago(valorPagoDouble);
                consultaFisio.setIdFisio(fisioid);
                consultaFisio.setIdPaciente(pacienteid);
                consultaFisio.setPacienteNome(pacienteNome);

                criadoComSucesso = consultaFisioController.insert(consultaFisio);
            }else {

                double valorConsDouble = Double.parseDouble(editTextValorConsulta.getText().toString());
                double valorPagoDouble = Double.parseDouble(editTextValorPago.getText().toString());

                consultaFisio.setDescricao(descricao);
                consultaFisio.setPatologia(patologia);
                consultaFisio.setTratamento(tratamento);
                consultaFisio.setData(DateUtil.stringToDate(data));
                consultaFisio.setHora(hora);
                consultaFisio.setEspecialidade(especialidade);
                consultaFisio.setValorConsulta(valorConsDouble);
                consultaFisio.setValorPago(valorPagoDouble);
                consultaFisio.setIdFisio(fisioid);
                consultaFisio.setIdPaciente(pacienteid);
                consultaFisio.setPacienteNome(pacienteNome);

                consultaFisioController.edit(consultaFisio, consultaFisio.getId());
                criadoComSucesso = true;
            }
        }
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        consultaFisioController.closeDb();
    }

    private void spnIdFisioItemSelected() {

        spnFisioId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Fisioterapeuta fisioterapeuta = listFisioObj.get(i);
                fisioid = fisioterapeuta.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void spnIdPacienteItemSelected() {

        spnIdPaciente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Paciente paciente = listObjPaciente.get(i);
                pacienteid = paciente.getId();
                pacienteNome = paciente.getNome() + " CPF: " + paciente.getCpf();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}

