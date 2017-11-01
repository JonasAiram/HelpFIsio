package com.airam.helpfisio.calculos;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.airam.helpfisio.R;

/**
 * Created by jonas on 31/10/2017.
 */

public class PesoIdeal extends AppCompatActivity{

    EditText edtTextAltura;
    RadioGroup radioGroup;
    RadioButton radioButton;
    int idRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pesoideal);

        edtTextAltura = (EditText) findViewById(R.id.edtAltura);

        Button btnCalcular, btnLimpar;

        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnLimpar = (Button) findViewById(R.id.btnLimpar);

        btnCalcular.setOnClickListener(btnCalcularAction);
        btnLimpar.setOnClickListener(btnLimparAction);

    }

    private View.OnClickListener btnCalcularAction = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Context context = view.getContext();

            radioGroup = (RadioGroup) findViewById(R.id.radioGroupId);
            idRadio = radioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(idRadio);

            if (edtTextAltura.getText().toString().equals("")){
                Toast.makeText(context, "Por favor, Preencha os Campos.", Toast.LENGTH_LONG).show();
                return;
            }

            String result = "" + radioButton.getText();

            if (result.equals("Masculino"))
                result = "50";
            else
                result = "45";

            result = String.valueOf(Integer.parseInt(result) + (0.91*(Integer.parseInt(edtTextAltura.getText().toString()) - 152)));

            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            alertDialogBuilder.setTitle("Resultado!");
            alertDialogBuilder.setMessage("Peso Ideal = " + result);

            alertDialogBuilder.setPositiveButton(" Salvar ", new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialog, int which) {

                    //Regras de negocio para salvar o resultado no banco de dados.

                    dialog.dismiss();
                }
            });

            alertDialogBuilder.setNegativeButton(" Limpar ", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {

                    edtTextAltura.setText("");
                    dialog.dismiss();
                }
            });
            alertDialogBuilder.show();
        }
    };

    private View.OnClickListener btnLimparAction = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            edtTextAltura.setText("");

        }
    };

}
