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
import com.airam.helpfisio.view.cadastro.CalculosCadastro;

/**
 * Created by Jonas on 21/06/2018.
 */

public class CapacidadeVitalLenta extends AppCompatActivity{

    EditText editTextAltura, editTextIdade;
    RadioGroup radioGroup;
    RadioButton radioButton;
    int idRadio;
    String resultado;
    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capacidadevitallenta);

        editTextAltura = (EditText) findViewById(R.id.edtCapacidadeAltura);
        editTextIdade = (EditText) findViewById(R.id.edtCapacidadeIdade);

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

            if (editTextAltura.getText().toString().equals("") || editTextIdade.getText().toString().equals("")){
                Toast.makeText(context, "Por favor, Preencha os Campos.", Toast.LENGTH_LONG).show();
                return;
            }

            resultado = "" + radioButton.getText();

            double idade = Double.parseDouble(editTextIdade.getText().toString());
            double altura = Double.parseDouble(editTextAltura.getText().toString());

            if (resultado.equals("Masculino"))
                result = 0.05211 - ((0.22*idade) - (3.6*altura));
            else
                result = 0.04111 - ((0.018*idade)-(2.69*altura));

            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            alertDialogBuilder.setTitle("Resultado!");
            alertDialogBuilder.setMessage("Peso Ideal = " + result);

            alertDialogBuilder.setPositiveButton(" Salvar ", new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialog, int which) {

                    //Regras de negocio para salvar o resultado no banco de dados.
                    CalculosCadastro calculosCadastro = new CalculosCadastro(context);
                    calculosCadastro.saveCalculo("Capacidade Vital Lenta", "ml", "" + result);

                    dialog.dismiss();
                }
            });

            alertDialogBuilder.setNegativeButton(" Limpar ", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {

                    editTextAltura.setText("");
                    editTextIdade.setText("");
                    dialog.dismiss();
                }
            });
            alertDialogBuilder.show();
        }
    };

    private View.OnClickListener btnLimparAction = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            editTextAltura.setText("");
            editTextIdade.setText("");

        }
    };
}
