package com.airam.helpfisio.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.airam.helpfisio.model.Calculos;

public class CalculosController extends  BaseController<Calculos>{


    public CalculosController(Context context) {
        super(context);
    }

    //INSERT
    public boolean insert(Calculos calculos){
        boolean isCreate = db.insert(Calculos.TABLE, null, convertToContentValue(calculos)) > 0;
        return isCreate;
    }

    @Override
    protected Calculos convertToObject(Cursor c) {
        Calculos calculos = new Calculos();

        int columnId = c.getColumnIndex(Calculos.COLUMN_ID);
        calculos.setId(c.getInt(columnId));

        columnId = c.getColumnIndex(Calculos.COLUMN_IDPACIENTE);
        calculos.setIdPaciente(c.getInt(columnId));

        columnId = c.getColumnIndex(Calculos.COLUMN_NOME);
        calculos.setNome(c.getString(columnId));

        columnId = c.getColumnIndex(Calculos.COLUMN_RESULTADO);
        calculos.setResultado(c.getDouble(columnId));

        columnId = c.getColumnIndex(Calculos.COLUMN_DATA);
        calculos.setData(c.getString(columnId));

        columnId = c.getColumnIndex(Calculos.COLUMN_HORA);
        calculos.setHora(c.getString(columnId));

        columnId = c.getColumnIndex(Calculos.COLUMN_OBSERVACOES);
        calculos.setObservacoes(c.getString(columnId));

        return calculos;
    }

    @Override
    protected String[] getColumns() {
        return new String[]{Calculos.COLUMN_ID, Calculos.COLUMN_IDPACIENTE, Calculos.COLUMN_NOME,
                    Calculos.COLUMN_RESULTADO, Calculos.COLUMN_DATA, Calculos.COLUMN_HORA,
                    Calculos.COLUMN_OBSERVACOES};
    }

    @Override
    protected ContentValues convertToContentValue(Calculos calculos) {
        ContentValues values = new ContentValues();

        values.put(Calculos.COLUMN_NOME, calculos.getNome());
        values.put(Calculos.COLUMN_RESULTADO, calculos.getResultado());
        values.put(Calculos.COLUMN_DATA, calculos.getData());
        values.put(Calculos.COLUMN_HORA, calculos.getHora());
        values.put(Calculos.COLUMN_OBSERVACOES, calculos.getObservacoes());

        return values;
    }

    @Override
    protected String getTable() {
        return Calculos.TABLE;
    }

    @Override
    protected String getColumnId(){
        return Calculos.COLUMN_ID;
    }

}
