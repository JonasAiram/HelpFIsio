package com.airam.helpfisio;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.airam.helpfisio.calculos.PesoIdeal;
import com.airam.helpfisio.view.PacienteCadastro;


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
                PacienteCadastro pacienteCadastro = new PacienteCadastro(v);

                break;

            case 1:

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
