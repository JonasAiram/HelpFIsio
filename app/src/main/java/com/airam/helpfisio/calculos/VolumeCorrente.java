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

/**
 * Created by jonas on 31/10/2017.
 */

public class VolumeCorrente extends AppCompatActivity{


    EditText edtTextVolMin, edtTextFreRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volcorrente);

        Button btnCalcular, btnLimpar;

        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnLimpar = (Button) findViewById(R.id.btnLimpar);

        btnCalcular.setOnClickListener(btnCalcularAction);
        btnLimpar.setOnClickListener(btnLimparAction);
    }

    private View.OnClickListener btnLimparAction = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            edtTextVolMin.setText("");
            edtTextFreRes.setText("");

        }
    };

    private View.OnClickListener btnCalcularAction = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final Context context = view.getContext();

            edtTextVolMin = (EditText) findViewById(R.id.edtVolMin);
            edtTextFreRes = (EditText) findViewById(R.id.edtFreRes);

            if (edtTextVolMin.getText().toString().equals("") || edtTextFreRes.getText().toString().equals("")){
                Toast.makeText(context, "Por favor, Preencha os Campos.", Toast.LENGTH_LONG).show();
                return;
            }

            double dobVm = Double.parseDouble(edtTextVolMin.getText().toString());
            double dobFr = Double.parseDouble(edtTextFreRes.getText().toString());
            double result = dobVm * dobFr;

            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            alertDialogBuilder.setTitle("Resultado!");
            alertDialogBuilder.setMessage("Volume Corrente = " + result);

            alertDialogBuilder.setPositiveButton(" Salvar ", new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialog, int which) {

                    //Regras de negocio para salvar o resultado no banco de dados.

                    dialog.dismiss();
                }
            });

            alertDialogBuilder.setNegativeButton(" Limpar ", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {

                    edtTextVolMin.setText("");
                    edtTextFreRes.setText("");
                    dialog.dismiss();
                }
            });
            alertDialogBuilder.show();

        }
    };


}