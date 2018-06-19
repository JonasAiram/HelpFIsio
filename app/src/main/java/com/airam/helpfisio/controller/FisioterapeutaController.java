package com.airam.helpfisio.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.airam.helpfisio.model.Fisioterapeuta;

public class FisioterapeutaController extends BaseController<Fisioterapeuta>{

    public FisioterapeutaController(Context context){
        super(context);
    }

    //INSERT
    public boolean insert(Fisioterapeuta fisioterapeuta){
        boolean isCreate = db.insert(Fisioterapeuta.TABLE, null, convertToContentValue(fisioterapeuta)) > 0;
        return isCreate;
    }

    @Override
    protected Fisioterapeuta convertToObject(Cursor c){
        Fisioterapeuta fisioterapeuta = new Fisioterapeuta();

        int columnId = c.getColumnIndex(Fisioterapeuta.COLUMN_ID);
        fisioterapeuta.setId(c.getInt(columnId));

        columnId = c.getColumnIndex(Fisioterapeuta.COLUMN_NOME);
        fisioterapeuta.setNome(c.getString(columnId));

        columnId = c.getColumnIndex(Fisioterapeuta.COLUMN_RG);
        fisioterapeuta.setRg(c.getInt(columnId));

        columnId = c.getColumnIndex(Fisioterapeuta.COLUMN_CPF);
        fisioterapeuta.setCpf(c.getString(columnId));

        columnId = c.getColumnIndex(Fisioterapeuta.COLUMN_DATA);
        fisioterapeuta.setData(c.getString(columnId));

        columnId = c.getColumnIndex(Fisioterapeuta.COLUMN_SOBRENOME);
        fisioterapeuta.setSobrenome(c.getString(columnId));

        columnId = c.getColumnIndex(Fisioterapeuta.COLUMN_CREFITO);
        fisioterapeuta.setCrefito(c.getInt(columnId));

        columnId = c.getColumnIndex(Fisioterapeuta.COLUMN_CARGO);
        fisioterapeuta.setCargo(c.getString(columnId));

        columnId = c.getColumnIndex(Fisioterapeuta.COLUMN_TELEFONE);
        fisioterapeuta.setTelefone(c.getInt(columnId));

        columnId = c.getColumnIndex(Fisioterapeuta.COLUMN_SALARIO);
        fisioterapeuta.setSalario(c.getDouble(columnId));

        columnId = c.getColumnIndex(Fisioterapeuta.COLUMN_PESO);
        fisioterapeuta.setPeso(c.getDouble(columnId));

        return fisioterapeuta;
    }

    @Override
    protected String[] getColumns(){
        return new String[]{Fisioterapeuta.COLUMN_ID, Fisioterapeuta.COLUMN_NOME, Fisioterapeuta.COLUMN_RG,
                Fisioterapeuta.COLUMN_CPF, Fisioterapeuta.COLUMN_DATA, Fisioterapeuta.COLUMN_SOBRENOME,
                Fisioterapeuta.COLUMN_CREFITO, Fisioterapeuta.COLUMN_CARGO, Fisioterapeuta.COLUMN_TELEFONE,
                Fisioterapeuta.COLUMN_SALARIO, Fisioterapeuta.COLUMN_PESO};
    }

    protected ContentValues convertToContentValue(Fisioterapeuta fisioterapeuta){
        ContentValues values = new ContentValues();

        values.put(Fisioterapeuta.COLUMN_NOME, fisioterapeuta.getNome());
        values.put(Fisioterapeuta.COLUMN_RG, fisioterapeuta.getRg());
        values.put(Fisioterapeuta.COLUMN_CPF, fisioterapeuta.getCpf());
        values.put(Fisioterapeuta.COLUMN_DATA, fisioterapeuta.getData());
        values.put(Fisioterapeuta.COLUMN_SOBRENOME, fisioterapeuta.getSobrenome());
        values.put(Fisioterapeuta.COLUMN_CREFITO, fisioterapeuta.getCrefito());
        values.put(Fisioterapeuta.COLUMN_CARGO, fisioterapeuta.getCargo());
        values.put(Fisioterapeuta.COLUMN_TELEFONE, fisioterapeuta.getTelefone());
        values.put(Fisioterapeuta.COLUMN_SALARIO, fisioterapeuta.getSalario());
        values.put(Fisioterapeuta.COLUMN_PESO, fisioterapeuta.getPeso());

        return values;
    }

    @Override
    protected String getTable() {
        return Fisioterapeuta.TABLE;
    }

    @Override
    protected String getColumnId(){
        return Fisioterapeuta.COLUMN_ID;
    }


}
