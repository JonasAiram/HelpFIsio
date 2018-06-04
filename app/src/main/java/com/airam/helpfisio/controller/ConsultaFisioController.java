package com.airam.helpfisio.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.airam.helpfisio.model.ConsultaFisio;

public class ConsultaFisioController extends BaseController<ConsultaFisio>{

    public ConsultaFisioController(Context context){
        super(context);
    }

    //INSERT
    public boolean insert(ConsultaFisio consultaFisio){
        boolean isCreate = db.insert(ConsultaFisio.TABLE, null, convertToContentValue(consultaFisio)) > 0;
        return isCreate;
    }

    @Override
    protected ConsultaFisio convertToObject(Cursor c) {
        ConsultaFisio consultaFisio = new ConsultaFisio();

        int columnId = c.getColumnIndex(ConsultaFisio.COLUMN_IDFISIO);
        consultaFisio.setIdFisio(c.getInt(columnId));

        columnId = c.getColumnIndex(ConsultaFisio.COLUMN_IDPACIENTE);
        consultaFisio.setIdPaciente(c.getInt(columnId));

        columnId = c.getColumnIndex(ConsultaFisio.COLUMN_DESC);
        consultaFisio.setDescricao(c.getString(columnId));

        columnId = c.getColumnIndex(ConsultaFisio.COLUMN_PATOLOGIA);
        consultaFisio.setPatologia(c.getString(columnId));

        columnId = c.getColumnIndex(ConsultaFisio.COLUMN_TRATAMENTO);
        consultaFisio.setTratamento(c.getString(columnId));

        columnId = c.getColumnIndex(ConsultaFisio.COLUMN_DATA);
        consultaFisio.setData(c.getString(columnId));

        columnId = c.getColumnIndex(ConsultaFisio.COLUMN_HORA);
        consultaFisio.setHora(c.getString(columnId));

        return consultaFisio;
    }

    @Override
    protected String[] getColumns() {
        return new String[]{ConsultaFisio.COLUMN_IDFISIO, ConsultaFisio.COLUMN_IDPACIENTE,
                ConsultaFisio.COLUMN_DESC, ConsultaFisio.COLUMN_PATOLOGIA, ConsultaFisio.COLUMN_TRATAMENTO,
                ConsultaFisio.COLUMN_DATA, ConsultaFisio.COLUMN_HORA};
    }

    protected ContentValues convertToContentValue(ConsultaFisio consultaFisio) {
        ContentValues values = new ContentValues();

        values.put(ConsultaFisio.COLUMN_IDPACIENTE, consultaFisio.getIdPaciente());
        values.put(ConsultaFisio.COLUMN_IDFISIO, consultaFisio.getIdFisio());
        values.put(ConsultaFisio.COLUMN_DESC, consultaFisio.getDescricao());
        values.put(ConsultaFisio.COLUMN_PATOLOGIA, consultaFisio.getPatologia());
        values.put(ConsultaFisio.COLUMN_TRATAMENTO, consultaFisio.getTratamento());
        values.put(ConsultaFisio.COLUMN_HORA, consultaFisio.getHora());
        values.put(ConsultaFisio.COLUMN_DATA, consultaFisio.getData());

        return values;
    }

    @Override
    protected String getTable() {
        return ConsultaFisio.TABLE;
    }

    @Override
    protected String getColumnId(){
        return ConsultaFisio.COLUMN_IDFISIO;
    }

}
