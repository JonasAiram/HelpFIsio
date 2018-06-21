package com.airam.helpfisio.calculos;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airam.helpfisio.R;
import com.airam.helpfisio.view.cadastro.CalculosCadastro;

/**
 * Created by Jonas on 21/06/2018.
 */

public class PArterialO2 extends AppCompatActivity{

    Button btnCalcular, btnLimpar;
    EditText editTextIdade;
    double result;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parterialo2);

        editTextIdade = (EditText) findViewById(R.id.edtArterialO2Idade);

        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnLimpar = (Button) findViewById(R.id.btnLimpar);

        btnCalcular.setOnClickListener(btnCalcularAction);
        btnLimpar.setOnClickListener(btnLimparAction);
    }

    private View.OnClickListener btnLimparAction = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            editTextIdade.setText("");
        }
    };

    private View.OnClickListener btnCalcularAction = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

             context = view.getContext();

            if (editTextIdade.getText().toString().equals("")){
                Toast.makeText(context, "Por favor, Preencha o Campo.", Toast.LENGTH_LONG).show();
                return;
            }

            int pArterialo2 = Integer.parseInt(editTextIdade.getText().toString());

            result = 109 - (pArterialo2 * 0.43);

            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            alertDialogBuilder.setTitle("Resultado!");
            alertDialogBuilder.setMessage("Pressão Arterial O² = " + result);

            alertDialogBuilder.setPositiveButton(" Salvar ", new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialog, int which) {

                    //Regras de negocio para salvar o resultado no banco de dados.
                    CalculosCadastro calculosCadastro = new CalculosCadastro(context);
                    calculosCadastro.saveCalculo("Pressão Arterial O² ", "mmHg", String.valueOf(result));

                    dialog.dismiss();
                }
            });

            alertDialogBuilder.setNegativeButton(" Limpar ", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {

                    editTextIdade.setText("");
                    dialog.dismiss();
                }
            });
            alertDialogBuilder.show();

        }
    };
}
