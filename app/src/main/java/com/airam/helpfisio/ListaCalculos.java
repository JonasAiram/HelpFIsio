package com.airam.helpfisio;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.airam.helpfisio.calculos.CapacidadeVitalLenta;
import com.airam.helpfisio.calculos.PArterialO2;
import com.airam.helpfisio.calculos.PesoIdeal;
import com.airam.helpfisio.calculos.VolumeCorrente;

/**
 * Created by jonas on 31/10/2017.
 */

public class ListaCalculos extends ListActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.funcoes));
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v,int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent;

        switch (position) {
            case 0:
                intent = new Intent(this, VolumeCorrente.class);
                dispararIntent(intent);
                break;

            case 1:
                intent = new Intent(this, PesoIdeal.class);
                dispararIntent(intent);
                break;

            case 2:
                intent = new Intent(this, PArterialO2.class);
                dispararIntent(intent);
                break;

            case 3:
                intent = new Intent(this, CapacidadeVitalLenta.class);
                dispararIntent(intent);
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
