package com.airam.helpfisio;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.airam.helpfisio.view.CalculosView;
import com.airam.helpfisio.view.ConsultaFisioView;
import com.airam.helpfisio.view.ConsultaMedicoView;
import com.airam.helpfisio.view.FisioterapeutaView;
import com.airam.helpfisio.view.HospitalView;
import com.airam.helpfisio.view.LeitoView;
import com.airam.helpfisio.view.MedicoView;
import com.airam.helpfisio.view.cadastro.CalculosCadastro;
import com.airam.helpfisio.view.cadastro.ConsultaFisioCadastro;
import com.airam.helpfisio.view.cadastro.ConsultaMedicoCadastro;
import com.airam.helpfisio.view.cadastro.FisioterapeutaCadastro;
import com.airam.helpfisio.view.cadastro.HospitalCadastro;
import com.airam.helpfisio.view.cadastro.LeitoCadastro;
import com.airam.helpfisio.view.cadastro.MedicoCadastro;
import com.airam.helpfisio.view.PacienteView;


/**
 * Created by jonas on 31/10/2017.
 */

public class ListaCadastros extends ListActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.cadastro));
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent;

        switch (position) {
            case 0:
                intent = new Intent(ListaCadastros.this, PacienteView.class);
                startActivity(intent);
                break;

            case 1:
                intent = new Intent(ListaCadastros.this, LeitoView.class);
                startActivity(intent);
                // LeitoCadastro leitoCadastro = new LeitoCadastro(v);

                break;

            case 2:
                intent = new Intent(ListaCadastros.this, MedicoView.class);
                startActivity(intent);

                //MedicoCadastro medicoCadastro= new MedicoCadastro(v);

                break;

            case 3:
                intent = new Intent(ListaCadastros.this, HospitalView.class);
                startActivity(intent);

                //HospitalCadastro hospitalCadastro = new HospitalCadastro(v);

                break;

            case 4:
                intent = new Intent(ListaCadastros.this, ConsultaMedicoView.class);
                startActivity(intent);
                //ConsultaMedicoCadastro consultaMedicoCadastro= new ConsultaMedicoCadastro(v);

                break;

            case 5:
                intent = new Intent(ListaCadastros.this, FisioterapeutaView.class);
                startActivity(intent);

                //FisioterapeutaCadastro fisioterapeutaCadastro= new FisioterapeutaCadastro(v);

                break;

            case 6:
                intent = new Intent(ListaCadastros.this, ConsultaFisioView.class);
                startActivity(intent);
                //ConsultaFisioCadastro consultaFisioCadastro= new ConsultaFisioCadastro(v);

                break;

            case 7:
                intent = new Intent(ListaCadastros.this, CalculosView.class);
                startActivity(intent);
                //CalculosCadastro calculosCadastro= new CalculosCadastro(v);

                break;

            default:
                finish();
        }
    }

    private void dispararIntent(Intent intent){
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.erro_intent, Toast.LENGTH_SHORT).show();
        }
    }

}
