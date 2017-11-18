package com.airam.helpfisio.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.airam.helpfisio.adapter.DataBaseAdapter;
import com.airam.helpfisio.model.Paciente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonas on 10/11/2017.
 */

public class PacienteController {

    private DataBaseAdapter adapter;
    protected SQLiteDatabase db;

    public PacienteController(Context context){
        //Integração com o Banco de Dados

        adapter = new DataBaseAdapter(context);
        db = adapter.getWritableDatabase();
    }

    //INSERT

    public boolean insert(Paciente paciente){
        boolean isCreate = db.insert(Paciente.TABLE, null, convertToContentValue(paciente)) > 0;
        return isCreate;
    }

    //LISTAR Pacientes

    public List<Paciente> get(){
        Cursor c = db.query(Paciente.TABLE, getColumns(), null, null, null, null, null);

        List<Paciente> objctlis = new ArrayList<Paciente>();

        if (c.moveToFirst()){
            do {
                Paciente paciente = convertToObject(c);
                objctlis.add(paciente);
            } while (c.moveToFirst());
        }
        c.close();

        return objctlis;
    }

    private Paciente convertToObject(Cursor c) {
        Paciente paciente = new Paciente();

        int columnId = c.getColumnIndex(Paciente.COLUMN_ID);
        paciente.setId(c.getInt(columnId));

        columnId = c.getColumnIndex(Paciente.COLUMN_NOME);
        paciente.setNome(c.getString(columnId));

        columnId = c.getColumnIndex(Paciente.COLUMN_RG);
        paciente.setRG(c.getString(columnId));

        columnId = c.getColumnIndex(Paciente.COLUMN_CPF);
        paciente.setCPF(c.getString(columnId));

        columnId = c.getColumnIndex(Paciente.COLUMN_PESO);
        paciente.setPeso(c.getString(columnId));

        columnId = c.getColumnIndex(Paciente.COLUMN_ALTURA);
        paciente.setAltura(c.getString(columnId));

        columnId = c.getColumnIndex(Paciente.COLUMN_DATA);
        paciente.setData(c.getString(columnId));

        return paciente;
    }

    private String[] getColumns(){
        return new String[]{Paciente.COLUMN_ID, Paciente.COLUMN_NOME, Paciente.COLUMN_RG,
                Paciente.COLUMN_CPF, Paciente.COLUMN_PESO, Paciente.COLUMN_ALTURA,
                Paciente.COLUMN_DATA};
    }

    private ContentValues convertToContentValue(Paciente paciente) {
        ContentValues values = new ContentValues();

        values.put(Paciente.COLUMN_NOME, paciente.getNome());
        values.put(Paciente.COLUMN_RG, paciente.getRG());
        values.put(Paciente.COLUMN_CPF, paciente.getCPF());
        values.put(Paciente.COLUMN_PESO, paciente.getPeso());
        values.put(Paciente.COLUMN_ALTURA, paciente.getAltura());
        values.put(Paciente.COLUMN_DATA, paciente.getData());

        return values;
    }

}
