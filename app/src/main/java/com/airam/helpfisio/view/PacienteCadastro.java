package com.airam.helpfisio.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.airam.helpfisio.R;
import com.airam.helpfisio.controller.PacienteController;
import com.airam.helpfisio.model.Paciente;


/**
 * Created by jonas on 10/11/2017.
 */

public class PacienteCadastro {

    private PacienteController pacienteController;

    public PacienteCadastro(View v){

        final Context context = v.getContext();

        pacienteController = new PacienteController(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.pacientecadastro, null, false);

        final EditText editTextNome = (EditText) formElementsView.findViewById(R.id.edtPacienteNome);
        final EditText editTextRG = (EditText) formElementsView.findViewById(R.id.edtPacienteRG);
        final EditText editTextCPF = (EditText) formElementsView.findViewById(R.id.edtPacienteCPF);
        final EditText editTextAltura = (EditText) formElementsView.findViewById(R.id.edtAlturaPaciente);
        final EditText editTextPeso = (EditText) formElementsView.findViewById(R.id.edtPacientePeso);
        final EditText editTextData = (EditText) formElementsView.findViewById(R.id.edtPacienteData);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Cadastrar Paciente")
                .setPositiveButton("Salvar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int id) {
                                // REGRAS DE NEGOCIOS PARA INCLUIR OS NOVOS CONTATOS

                                String pacienteNome = editTextNome.getText().toString();
                                String pacienteRG = editTextRG.getText().toString();
                                String pacienteCPF = editTextCPF.getText().toString();
                                String pacienteAltura = editTextAltura.getText().toString();
                                String pacientePeso = editTextPeso.getText().toString();
                                String pacienteData = editTextData.getText().toString();


                                Paciente paciente= new Paciente();
                                paciente.setNome(pacienteNome);
                                paciente.setRG(pacienteRG);
                                paciente.setCPF(pacienteCPF);
                                paciente.setAltura(pacienteAltura);
                                paciente.setPeso(pacientePeso);
                                paciente.setData(pacienteData);


                                boolean criadoComSucesso = pacienteController.insert(paciente);

                                if (criadoComSucesso)
                                    Toast.makeText(context, "Paciente Cadastrado Com Sucesso.", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(context, "Não Foi Possivel Cadastrar o Paciente.", Toast.LENGTH_SHORT).show();


                                dialogInterface.cancel();
                            }
                        }).setNegativeButton("Voltar",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    editTextNome.setText("");
                                    editTextRG.setText("");
                                    editTextCPF.setText("");
                                    editTextAltura.setText("");
                                    editTextPeso.setText("");
                                    editTextData.setText("");

                                }
                            }).show();

    }
}
