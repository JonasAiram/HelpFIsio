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

import com.airam.helpfisio.controller.HospitalController;
import com.airam.helpfisio.controller.LeitoController;
import com.airam.helpfisio.model.DateUtil;
import com.airam.helpfisio.model.Hospital;
import com.airam.helpfisio.model.Leito;
import com.airam.helpfisio.view.PacienteView;
import com.airam.helpfisio.R;
import com.airam.helpfisio.controller.PacienteController;
import com.airam.helpfisio.model.Paciente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonas on 10/11/2017.
 */

public class PacienteCadastro implements DialogInterface.OnShowListener, View.OnClickListener, DialogInterface.OnDismissListener{

    private PacienteController pacienteController;
    private HospitalController hospitalController;
    private LeitoController leitoController;

    private Paciente paciente;

    private AlertDialog dialog;
    private EditText editTextNome, editTextRG, editTextCPF, editTextAltura, editTextPeso;
    private EditText editTextData, editTextTelefone, editTextSobrenome;

    private Spinner spnIdHospital, spnIdLeito;

    private int idHospital, idLeito;

    private List<String> listaNomeHospital = new ArrayList<String>();
    private List<String> listaNomeLeito = new ArrayList<String>();
    List<Hospital> listHospital;
    List<Leito> listLeito;

    Context context;

    boolean criadoComSucesso;

    public PacienteCadastro(Context context) {

        //CRIA O CONTEXT
        this.context = context;

        pacienteController = new PacienteController(context);

        //CRIA O LAYOUT COMO ALERTDIALOG
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
        editTextTelefone = (EditText) view.findViewById(R.id.edtPacienteTelefone);
        editTextSobrenome = (EditText) view.findViewById(R.id.edtPacienteSobrenome);

        spnIdHospital = (Spinner) view.findViewById(R.id.spnPacienteHospital);
        spnIdLeito = (Spinner) view.findViewById(R.id.spnPacienteLeito);

        //SPINNER HOSPITAL
        hospitalController = new HospitalController(context);
        arrayIdHospital();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listaNomeHospital);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnIdHospital.setAdapter(adapter);
        spnHospItemSelected(adapter);

        //SPINNER LEITO
        leitoController = new LeitoController(context);
        arrayIdLeito();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listaNomeLeito);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnIdLeito.setAdapter(adapter2);
        spnLeitoItemSelected(adapter2);

        //CRIA OS BUTTONS DO ALERTDIALOG
        builder.setPositiveButton("Salvar", null);
        builder.setNegativeButton("Voltar", null);

        dialog = builder.create();
        dialog.setOnShowListener(this);
        dialog.show();
    }

    private void arrayIdHospital() {
        listHospital = hospitalController.getAll();
        for (Hospital hospital : listHospital){
            listaNomeHospital.add(hospital.getNome() + " Cidade: " + hospital.getCidade());
        }
    }

    private void arrayIdLeito() {

        listLeito = leitoController.getAll();
        for (Leito leito : listLeito){
            listaNomeLeito.add(leito.getTipo() + " Quantidade: " + leito.getQuantidade());
        }
    }

    public void loadPaciente(Paciente paciente){

        this.paciente = paciente;

        spnIdHospital.setSelection(getIndexHospitalId(paciente.getIdHospital()));
        spnIdLeito.setSelection(getIndexLeitoId(paciente.getId_leito()));

        editTextNome.setText(paciente.getNome());
        editTextRG.setText(String.valueOf(paciente.getRg()));
        editTextCPF.setText(paciente.getCpf());
        editTextAltura.setText(String.valueOf(paciente.getAltura()));
        editTextPeso.setText(String.valueOf(paciente.getPeso()));
        editTextData.setText((DateUtil.dateToString(paciente.getData())));
        editTextTelefone.setText(String.valueOf(paciente.getTelefone()));
        editTextSobrenome.setText(paciente.getSobrenome());
    }

    private int getIndexHospitalId(int idHospital){
        for (int index = 0; index < listHospital.size(); index++){
            Hospital hospital = listHospital.get(index);
            if (idHospital == hospital.getId())
                return index;
        }
        return 0;
    }

    private int getIndexLeitoId(int idLeito){
        for (int index = 0; index < listLeito.size(); index++){
            Leito leito = listLeito.get(index);
            if (idLeito == leito.getId())
                return index;
        }
        return 0;
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
                double altura = Double.parseDouble(editTextAltura.getText().toString());
                double peso = Double.parseDouble(editTextPeso.getText().toString());

                Paciente paciente = new Paciente();
                paciente.setNome(pacienteNome);
                paciente.setRg(Rg);
                paciente.setCpf(pacienteCpf);
                paciente.setAltura(altura);
                paciente.setPeso(peso);
                paciente.setData(DateUtil.stringToDate(pacienteData));
                paciente.setTelefone(pacienteTelefone);
                paciente.setSobrenome(pacienteSobrenome);
                paciente.setIdHospital(idHospital);
                paciente.setId_leito(idLeito);

                criadoComSucesso = pacienteController.insert(paciente);
            }else{
                //CONVERTER PARA O TIPO DE DADOS QUE SERÁ ARMAZENADOS NO BANCO DE DADOS
                int Rg = Integer.parseInt(editTextRG.getText().toString());
                double altura = Double.parseDouble(editTextAltura.getText().toString());
                double peso = Double.parseDouble(editTextPeso.getText().toString());

                paciente.setNome(pacienteNome);
                paciente.setRg(Rg);
                paciente.setCpf(pacienteCpf);
                paciente.setAltura(altura);
                paciente.setPeso(peso);
                paciente.setData(DateUtil.stringToDate(pacienteData));
                paciente.setTelefone(pacienteTelefone);
                paciente.setSobrenome(pacienteSobrenome);
                paciente.setIdHospital(idHospital);
                paciente.setId_leito(idLeito);

                pacienteController.edit(paciente, paciente.getId());
                criadoComSucesso = true;
            }
        }
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        pacienteController.closeDb();
    }

    private void spnLeitoItemSelected(ArrayAdapter<String> adapter2) {

        spnIdLeito.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Leito leito = listLeito.get(i);
                idLeito = leito.getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void spnHospItemSelected(ArrayAdapter<String> adapter) {

        spnIdHospital.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Hospital hospital = listHospital.get(i);
                idHospital = hospital.getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}
