package com.airam.helpfisio.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.airam.helpfisio.model.ConsultaMedico;
import com.airam.helpfisio.model.DateUtil;

/**
 * Created by Jonas on 19/04/2018.
 */

public class ConsultaMedicoController extends BaseController<ConsultaMedico>{

    public ConsultaMedicoController(Context context){
        super(context);
    }

    //INSERT
    public boolean insert(ConsultaMedico consultaMedico){
        boolean isCreate = db.insert(ConsultaMedico.TABLE, null, convertToContentValue(consultaMedico)) > 0;
        return isCreate;
    }

    @Override
    protected ConsultaMedico convertToObject(Cursor c){
        ConsultaMedico consultaMedico = new ConsultaMedico();

        int columnId = c.getColumnIndex(ConsultaMedico.COLUMN_ID);
        consultaMedico.setId(c.getInt(columnId));

        columnId = c.getColumnIndex(ConsultaMedico.COLUMN_IDMEDICO);
        consultaMedico.setIdMedico(c.getInt(columnId));

        columnId = c.getColumnIndex(ConsultaMedico.COLUMN_IDPACIENTE);
        consultaMedico.setIdPaciente(c.getInt(columnId));

        columnId = c.getColumnIndex(ConsultaMedico.COLUMN_DESC);
        consultaMedico.setDescricao(c.getString(columnId));

        columnId = c.getColumnIndex(ConsultaMedico.COLUMN_MEDICACAO);
        consultaMedico.setMedicacao(c.getString(columnId));

        columnId = c.getColumnIndex(ConsultaMedico.COLUMN_TRATAMENTO);
        consultaMedico.setTratamento(c.getString(columnId));

        columnId = c.getColumnIndex(ConsultaMedico.COLUMN_DATA);
        consultaMedico.setData(DateUtil.stringToDate(c.getString(columnId)));

        columnId = c.getColumnIndex(ConsultaMedico.COLUMN_HORA);
        consultaMedico.setHora(c.getString(columnId));

        columnId = c.getColumnIndex(ConsultaMedico.COLUMN_ESPECIALIDADE);
        consultaMedico.setEspecialidadeConsulta(c.getString(columnId));

        columnId = c.getColumnIndex(ConsultaMedico.COLUMN_VALOR);
        consultaMedico.setValorConsulta(c.getDouble(columnId));

        columnId = c.getColumnIndex(ConsultaMedico.COLUMN_VALORPAGO);
        consultaMedico.setValorPago(c.getDouble(columnId));

        columnId = c.getColumnIndex(ConsultaMedico.COLUMN_PACIENTENOME);
        consultaMedico.setPacienteNome(c.getString(columnId));


        return consultaMedico;
    }

    @Override
    protected String[] getColumns() {
        return new String[]{ConsultaMedico.COLUMN_ID, ConsultaMedico.COLUMN_IDMEDICO, ConsultaMedico.COLUMN_IDPACIENTE,
                ConsultaMedico.COLUMN_DESC, ConsultaMedico.COLUMN_MEDICACAO, ConsultaMedico.COLUMN_TRATAMENTO,
                ConsultaMedico.COLUMN_DATA, ConsultaMedico.COLUMN_HORA, ConsultaMedico.COLUMN_ESPECIALIDADE, ConsultaMedico.COLUMN_VALOR,
                ConsultaMedico.COLUMN_VALORPAGO, ConsultaMedico.COLUMN_PACIENTENOME};
    }

    protected ContentValues convertToContentValue(ConsultaMedico consultaMedico){
        ContentValues values = new ContentValues();

        values.put(ConsultaMedico.COLUMN_IDPACIENTE, consultaMedico.getIdPaciente());
        values.put(ConsultaMedico.COLUMN_IDMEDICO, consultaMedico.getIdMedico());
        values.put(ConsultaMedico.COLUMN_DESC, consultaMedico.getDescricao());
        values.put(ConsultaMedico.COLUMN_MEDICACAO, consultaMedico.getMedicacao());
        values.put(ConsultaMedico.COLUMN_TRATAMENTO, consultaMedico.getTratamento());
        values.put(ConsultaMedico.COLUMN_ESPECIALIDADE, consultaMedico.getEspecialidadeConsulta());
        values.put(ConsultaMedico.COLUMN_HORA, consultaMedico.getHora());
        values.put(ConsultaMedico.COLUMN_DATA, DateUtil.dateToString(consultaMedico.getData()));
        values.put(ConsultaMedico.COLUMN_VALOR, consultaMedico.getValorConsulta());
        values.put(ConsultaMedico.COLUMN_VALORPAGO, consultaMedico.getValorPago());
        values.put(ConsultaMedico.COLUMN_PACIENTENOME, consultaMedico.getPacienteNome());

        return values;
    }

    @Override
    protected String getTable() {
        return ConsultaMedico.TABLE;
    }

    @Override
    protected String getColumnId(){
        return ConsultaMedico.COLUMN_ID;
    }

}
