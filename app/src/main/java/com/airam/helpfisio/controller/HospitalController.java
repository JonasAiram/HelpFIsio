package com.airam.helpfisio.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.airam.helpfisio.model.Hospital;

public class HospitalController extends BaseController<Hospital>{

    public HospitalController(Context context){
        super(context);
    }

    //INSERT
    public boolean insert(Hospital hospital){
        boolean isCreate = db.insert(Hospital.TABLE, null, convertToContentValue(hospital)) > 0;
        return isCreate;
    }

    @Override
    protected Hospital convertToObject(Cursor c) {
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

        columnId = c.getColumnIndex(Hospital.COLUMN_NUMENRO);
        hospital.setNumero(c.getInt(columnId));

        columnId = c.getColumnIndex(Hospital.COLUMN_DTCRIACAO);
        hospital.setdtCriacao(c.getString(columnId));

        columnId = c.getColumnIndex(Hospital.COLUMN_VALOR);
        hospital.setValor(c.getDouble(columnId));

        return hospital;
    }

    @Override
    protected String[] getColumns() {
        return new String[]{Hospital.COLUMN_ID, Hospital.COLUMN_NOME, Hospital.COLUMN_RUA,
                Hospital.COLUMN_BAIRRO, Hospital.COLUMN_CIDADE, Hospital.COLUMN_UF,
                Hospital.COLUMN_TELEFONE, Hospital.COLUMN_DIRETOR, Hospital.COLUMN_NUMENRO,
                Hospital.COLUMN_DTCRIACAO, Hospital.COLUMN_VALOR};
    }

    protected ContentValues convertToContentValue(Hospital hospital) {
        ContentValues values = new ContentValues();

        values.put(Hospital.COLUMN_NOME, hospital.getNome());
        values.put(Hospital.COLUMN_RUA, hospital.getRua());
        values.put(Hospital.COLUMN_BAIRRO, hospital.getBairro());
        values.put(Hospital.COLUMN_CIDADE, hospital.getCidade());
        values.put(Hospital.COLUMN_UF, hospital.getUF());
        values.put(Hospital.COLUMN_TELEFONE, hospital.getTelefone());
        values.put(Hospital.COLUMN_DIRETOR, hospital.getDiretor());
        values.put(Hospital.COLUMN_NUMENRO, hospital.getNumero());
        values.put(Hospital.COLUMN_DTCRIACAO, hospital.getdtCriacao());
        values.put(Hospital.COLUMN_VALOR, hospital.getValor());

        return values;
    }

    @Override
    protected String getTable() {
        return Hospital.TABLE;
    }

    @Override
    protected String getColumnId(){
        return Hospital.COLUMN_ID;
    }

}