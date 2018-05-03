package com.airam.helpfisio.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.airam.helpfisio.model.Medico;

public class MedicoController extends BaseController<Medico>{

    public MedicoController(Context context){
        super(context);
    }

    //INSERT
    public boolean insert(Medico medico){
        boolean IsCreate = db.insert(Medico.TABLE, null, convertToContentValue(medico)) > 0;
        return IsCreate;
    }


    @Override
    protected Medico convertToObject(Cursor c) {
        Medico medico = new Medico();

        int columnId = c.getColumnIndex(Medico.COLUMN_ID);
        medico.setId(c.getInt(columnId));

        columnId = c.getColumnIndex(Medico.COLUMN_NOME);
        medico.setNome(c.getString(columnId));

        columnId = c.getColumnIndex(Medico.COLUMN_RG);
        medico.setRg(c.getInt(columnId));

        columnId = c.getColumnIndex(Medico.COLUMN_CPF);
        medico.setCpf(c.getString(columnId));

        columnId = c.getColumnIndex(Medico.COLUMN_DATA);
        medico.setData(c.getString(columnId));

        columnId = c.getColumnIndex(Medico.COLUMN_CRM);
        medico.setCrm(c.getInt(columnId));

        columnId = c.getColumnIndex(Medico.COLUMN_CARGO);
        medico.setCargo(c.getString(columnId));

        columnId = c.getColumnIndex(Medico.COLUMN_TELEFONE);
        medico.setTelefone(c.getInt(columnId));

        columnId = c.getColumnIndex(Medico.COLUMN_SOBRENOME);
        medico.setSobrenome(c.getString(columnId));

        columnId = c.getColumnIndex(Medico.COLUMN_SALARIO);
        medico.setSalario(c.getDouble(columnId));

        return medico;
    }

    @Override
    protected String[] getColumns() {
        return new String[]{Medico.COLUMN_ID, Medico.COLUMN_NOME, Medico.COLUMN_RG, Medico.COLUMN_CPF,
                Medico.COLUMN_DATA, Medico.COLUMN_CRM, Medico.COLUMN_CARGO, Medico.COLUMN_TELEFONE,
                Medico.COLUMN_SOBRENOME, Medico.COLUMN_SALARIO};
    }

    private ContentValues convertToContentValue(Medico medico) {
        ContentValues values = new ContentValues();

        values.put(Medico.COLUMN_NOME, medico.getNome());
        values.put(Medico.COLUMN_RG, medico.getRg());
        values.put(Medico.COLUMN_CPF, medico.getCpf());
        values.put(Medico.COLUMN_DATA, medico.getData());
        values.put(Medico.COLUMN_CRM, medico.getCrm());
        values.put(Medico.COLUMN_CARGO, medico.getCargo());
        values.put(Medico.COLUMN_TELEFONE, medico.getTelefone());
        values.put(Medico.COLUMN_SOBRENOME, medico.getSobrenome());
        values.put(Medico.COLUMN_SALARIO, medico.getSalario());

        return values;
    }

    @Override
    protected String getTable() {
        return Medico.TABLE;
    }
}
