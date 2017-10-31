package com.airam.helpfisio.calculos;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.airam.helpfisio.R;

/**
 * Created by jonas on 31/10/2017.
 */

public class VolumeCorrente extends AppCompatActivity implements View.OnClickListener{

    Button btnCalcular, btnLimpar;
    EditText edtTextVolMin, edtTextFreRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volcorrente);

        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(this);

        btnLimpar = (Button) findViewById(R.id.btnLimpar);
        btnLimpar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        edtTextVolMin = (EditText) findViewById(R.id.edtVolMin);
        edtTextFreRes = (EditText) findViewById(R.id.edtFreRes);

        double dobVm = Double.parseDouble(edtTextVolMin.getText().toString());
        double dobFr = Double.parseDouble(edtTextFreRes.getText().toString());
        double result = dobVm * dobFr;

        final Context context = view.getContext();
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
}
