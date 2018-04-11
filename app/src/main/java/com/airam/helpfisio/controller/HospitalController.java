package com.airam.helpfisio.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.airam.helpfisio.adapter.DataBaseAdapter;
import com.airam.helpfisio.model.Hospital;

import java.util.ArrayList;
import java.util.List;

public class HospitalController {

    private DataBaseAdapter adapter;
    protected SQLiteDatabase db;

    public HospitalController(Context context){

        //INTEGRAÇÃO COM O BANCO
        adapter = new DataBaseAdapter(context);
        db = adapter.getWritableDatabase();
    }

    //INSERT
    public boolean insert(Hospital hospital){
        boolean isCreate = db.insert(Hospital.TABLE, null, convertToContentValue(hospital)) > 0;
        return isCreate;
    }

    //LISTAR
    public List<Hospital> get(){
        Cursor c = db.query(Hospital.TABLE, getColumns(), null, null, null, null, null);

        List<Hospital> objctlis = new ArrayList<Hospital>();

        if (c.moveToFirst()){
            do {
                Hospital hospital = convertToObject(c);
                objctlis.add(hospital);
            } while (c.moveToFirst());
        }
        c.close();

        return objctlis;
    }

    private Hospital convertToObject(Cursor c) {
        Hospital hospital = new Hospital();

        int columnId = c.getColumnIndex(Hospital.COLUMN_ID);
        hospital.setId(c.getInt(columnId));

        columnId = c.getColumnIndex(Hospital.COLUMN_NOME);
        hospital.setNome(c.getString(columnId));

        columnId = c.getColumnIndex(Hospital.COLUMN_RUA);
        hospital.setRua(c.getString(columnId));

        columnId = c.getColumnIndex(Hospital.COLUMN_BAIRRO);
        hospital.setBairro(c.getString(columnId));

        columnId = c.getColumnIndex(Hospital.COLUMN_CIDADE);
        hospital.setCidade(c.getString(columnId));

        columnId = c.getColumnIndex(Hospital.COLUMN_UF);
        hospital.setUF(c.getString(columnId));

        columnId = c.getColumnIndex(Hospital.COLUMN_TELEFONE);
        hospital.setTelefone(c.getInt(columnId));

        columnId = c.getColumnIndex(Hospital.COLUMN_DIRETOR);
        hospital.setDiretor(c.getString(columnId));

        return hospital;
    }

    private String[] getColumns() {
        return new String[]{Hospital.COLUMN_ID, Hospital.COLUMN_NOME, Hospital.COLUMN_RUA,
                Hospital.COLUMN_BAIRRO, Hospital.COLUMN_CIDADE, Hospital.COLUMN_UF,
                Hospital.COLUMN_TELEFONE, Hospital.COLUMN_DIRETOR};
    }

    private ContentValues convertToContentValue(Hospital hospital) {
        ContentValues values = new ContentValues();

        values.put(Hospital.COLUMN_NOME, hospital.getNome());
        values.put(Hospital.COLUMN_RUA, hospital.getRua());
        values.put(Hospital.COLUMN_BAIRRO, hospital.getBairro());
        values.put(Hospital.COLUMN_CIDADE, hospital.getCidade());
        values.put(Hospital.COLUMN_UF, hospital.getUF());
        values.put(Hospital.COLUMN_TELEFONE, hospital.getTelefone());
        values.put(Hospital.COLUMN_DIRETOR, hospital.getDiretor());

        return values;
    }

}