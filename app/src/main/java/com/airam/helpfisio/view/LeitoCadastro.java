package com.airam.helpfisio.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.airam.helpfisio.R;
import com.airam.helpfisio.controller.HospitalController;
import com.airam.helpfisio.controller.LeitoController;
import com.airam.helpfisio.model.Leito;
import com.airam.helpfisio.model.Paciente;

/**
 * Created by Jonas on 18/11/2017.
 */

public class LeitoCadastro {

    private LeitoController leitoController;

    public LeitoCadastro(View v){

        final Context context = v.getContext();

        leitoController = new LeitoController(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.leitocadastro, null, false);

        final EditText editTextLeito = (EditText) formElementsView.findViewById(R.id.editTextTipoLeito);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Tipo de Leito")
                .setPositiveButton("Salvar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int id) {
                                // REGRAS DE NEGOCIOS PARA INCLUIR OS NOVOS CONTATOS

                                String tipoLeito = editTextLeito.getText().toString();

                                Leito leito = new Leito();
                                leito.setTipo(tipoLeito);

                                boolean criadoComSucesso = leitoController.insert(leito);

                                if (criadoComSucesso)
                                    Toast.makeText(context, "Leito Cadastrado Com Sucesso.", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(context, "Não Foi Possivel Cadastrar o Leito.", Toast.LENGTH_SHORT).show();


                                dialogInterface.cancel();
                            }
                        }).setNegativeButton("Voltar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        editTextLeito.setText("");

                    }
                }).show();

    }

}
