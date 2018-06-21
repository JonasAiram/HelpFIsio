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
import com.airam.helpfisio.controller.ConsultaMedicoController;
import com.airam.helpfisio.controller.MedicoController;
import com.airam.helpfisio.controller.PacienteController;
import com.airam.helpfisio.model.ConsultaMedico;
import com.airam.helpfisio.model.DateUtil;
import com.airam.helpfisio.model.Medico;
import com.airam.helpfisio.model.Paciente;
import com.airam.helpfisio.view.ConsultaMedicoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas on 19/04/2018.
 */

public class ConsultaMedicoCadastro implements DialogInterface.OnShowListener, View.OnClickListener, DialogInterface.OnDismissListener {

    private ConsultaMedicoController consultaMedicoController;
    private PacienteController pacienteController;
    private MedicoController medicoController;

    private ConsultaMedico consultaMedico;

    private AlertDialog dialog;
    private EditText editTextDescricao, editTextMedicacao, editTextTratamento, editTextData, editTextHora, editTextEspecialidade;
    private EditText editTextValorPago, editTextValorConsulta;

    private Spinner spnIdMedico, spnIdPaciente;

    private int medicoid, pacienteid;
    private String pacienteNome;

    private List<String> listaNomeMedico = new ArrayList<String>();
    private List<String> listaNomePaciente = new ArrayList<String>();
    List<Paciente> listObjPaciente;
    List<Medico> listObjMedico;

    Context context;

    boolean criadoComSucesso;

    public ConsultaMedicoCadastro(Context context){

        //CRIA CONTEXT
        this.context = context;

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
        editTextValorConsulta = (EditText) view.findViewById(R.id.edtConsMedicoValor);
        editTextValorPago = (EditText) view.findViewById(R.id.edtConsMedicoValorPago);

        spnIdMedico = (Spinner) view.findViewById(R.id.spnConsultaMedicoId);
        spnIdPaciente = (Spinner) view.findViewById(R.id.spnConsultaMedicoIdPaciente);

        //SPINNER MEDICO
        medicoController = new MedicoController(context);
        arrayIdMedico();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listaNomeMedico);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnIdMedico.setAdapter(adapter);
        spnIdMedicoItemSelected(adapter);

        //SPINNER PACIENTE
        pacienteController = new PacienteController(context);
        arrayIdPaciente();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listaNomePaciente);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnIdPaciente.setAdapter(adapter2);
        spnIdPacienteItemSelected(adapter2);

        //CRIA OS BUTTONS DO ALERTDIALOG
        builder.setPositiveButton("Salvar", null);
        builder.setNegativeButton("Voltar", null);

        //START O ALERTDIALOG
        dialog = builder.create();
        dialog.setOnShowListener(this);
        dialog.show();

    }

    private void arrayIdMedico() {

        listObjMedico = medicoController.getAll();
        for (Medico medico : listObjMedico)
            listaNomeMedico.add(medico.getNome() + " CRM: " + medico.getCrm());

    }

    private void arrayIdPaciente() {

        listObjPaciente = pacienteController.getAll();
        for (Paciente paciente : listObjPaciente)
            listaNomePaciente.add(paciente.getNome() + " CPF: " + paciente.getCpf());

    }

    public void loadConsulta(ConsultaMedico consultaMedico){

        this.consultaMedico = consultaMedico;

        spnIdMedico.setSelection(getIndexMedicoId(consultaMedico.getIdMedico()));
        spnIdPaciente.setSelection(getIndexPacienteId(consultaMedico.getIdPaciente()));

        editTextDescricao.setText(consultaMedico.getDescricao());
        editTextMedicacao.setText(consultaMedico.getMedicacao());
        editTextTratamento.setText(consultaMedico.getTratamento());
        editTextData.setText(DateUtil.dateToString(consultaMedico.getData()));
        editTextHora.setText(consultaMedico.getHora());
        editTextEspecialidade.setText(consultaMedico.getEspecialidadeConsulta());
        editTextValorConsulta.setText(String.valueOf(consultaMedico.getValorConsulta()));
        editTextValorPago.setText(String.valueOf(consultaMedico.getValorPago()));

    }

    private int getIndexMedicoId(int idMedico) {
        for (int index = 0; index < listObjMedico.size(); index++){
            Medico medico = listObjMedico.get(index);
            if (idMedico == medico.getId())
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
            ((ConsultaMedicoView) context).atualizarRegistros();
        } else
            Toast.makeText(context, "Não Foi Possivel Armazenar a Consulta.", Toast.LENGTH_SHORT).show();

        dialog.dismiss();
    }

    public void insertConsulta(){

        //ATRIBUIÇÃO DAS VARIAVEIS PARA STRINGS PARA FACILITAR NA ESTRUTURA DE CONDIÇÃO IF
        String descricao = editTextDescricao.getText().toString();
        String medicamento = editTextMedicacao.getText().toString();
        String tratamento = editTextTratamento.getText().toString();
        String data = editTextData.getText().toString();
        String hora = editTextHora.getText().toString();
        String especialidade = editTextEspecialidade.getText().toString();
        String valorConsulta = editTextValorConsulta.getText().toString();
        String valorPago = editTextValorPago.getText().toString();

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

            if (consultaMedico == null){

                double valorConsDouble = Double.parseDouble(editTextValorConsulta.getText().toString());
                double valorPagoDouble = Double.parseDouble(editTextValorPago.getText().toString());

                //REGRAS PARA ARMAZENAR NO BANCO DE DADOS
                ConsultaMedico consultaMedico = new ConsultaMedico();
                consultaMedico.setDescricao(descricao);
                consultaMedico.setMedicacao(medicamento);
                consultaMedico.setTratamento(tratamento);
                consultaMedico.setData(DateUtil.stringToDate(data));
                consultaMedico.setHora(hora);
                consultaMedico.setEspecialidadeConsulta(especialidade);
                consultaMedico.setValorConsulta(valorConsDouble);
                consultaMedico.setValorPago(valorPagoDouble);
                consultaMedico.setIdPaciente(pacienteid);
                consultaMedico.setIdMedico(medicoid);
                consultaMedico.setPacienteNome(pacienteNome);

                criadoComSucesso = consultaMedicoController.insert(consultaMedico);
            }else{
                double valorConsDouble = Double.parseDouble(editTextValorConsulta.getText().toString());
                double valorPagoDouble = Double.parseDouble(editTextValorPago.getText().toString());

                consultaMedico.setDescricao(descricao);
                consultaMedico.setMedicacao(medicamento);
                consultaMedico.setTratamento(tratamento);
                consultaMedico.setData(DateUtil.stringToDate(data));
                consultaMedico.setHora(hora);
                consultaMedico.setEspecialidadeConsulta(especialidade);
                consultaMedico.setValorConsulta(valorConsDouble);
                consultaMedico.setValorPago(valorPagoDouble);
                consultaMedico.setIdPaciente(pacienteid);
                consultaMedico.setIdMedico(medicoid);
                consultaMedico.setPacienteNome(pacienteNome);
                Log.e("TESTE", String.valueOf(medicoid));

                consultaMedicoController.edit(consultaMedico, consultaMedico.getId());
                criadoComSucesso = true;
            }
        }
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        consultaMedicoController.closeDb();

    }

    private void spnIdMedicoItemSelected(ArrayAdapter<String> adapter) {

        spnIdMedico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Medico medico = listObjMedico.get(i);
                medicoid = medico.getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void spnIdPacienteItemSelected(ArrayAdapter<String> adapter2) {

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
