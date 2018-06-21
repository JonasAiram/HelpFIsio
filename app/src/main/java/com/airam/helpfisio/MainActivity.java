package com.airam.helpfisio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    Button btnCalculos, btnCadastro, btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastro = (Button) findViewById(R.id.btnCadastro);
        btnCadastro.setOnClickListener(this);
        btnCalculos = (Button) findViewById(R.id.btnCalculos);
        btnCalculos.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnCalculos:
                intent = new Intent(MainActivity.this, ListaCalculos.class);
                startActivity(intent);
                break;

            case R.id.btnCadastro:
                intent = new Intent(MainActivity.this, ListaCadastros.class);
                startActivity(intent);
                break;

            default:
                finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
