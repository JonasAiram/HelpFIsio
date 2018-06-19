package com.airam.helpfisio.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.airam.helpfisio.model.DateUtil;
import com.airam.helpfisio.model.Paciente;

/**
 * Created by jonas on 10/11/2017.
 */

public class PacienteController extends BaseController<Paciente>{

    public PacienteController(Context context){
        //Integração com o Banco de Dados
        super(context);

    }

    //INSERT
    public boolean insert(Paciente paciente){
        boolean isCreate = db.insert(Paciente.TABLE, null, convertToContentValue(paciente)) > 0;
        return isCreate;
    }

    @Override
    protected Paciente convertToObject(Cursor c) {
        Paciente paciente = new Paciente();

        int columnId = c.getColumnIndex(Paciente.COLUMN_ID);
        paciente.setId(c.getInt(columnId));

        columnId = c.getColumnIndex(Paciente.COLUMN_NOME);
        paciente.setNome(c.getString(columnId));

        columnId = c.getColumnIndex(Paciente.COLUMN_RG);
        paciente.setRg(c.getInt(columnId));

        columnId = c.getColumnIndex(Paciente.COLUMN_CPF);
        paciente.setCpf(c.getString(columnId));

        columnId = c.getColumnIndex(Paciente.COLUMN_PESO);
        paciente.setPeso(c.getDouble(columnId));

        columnId = c.getColumnIndex(Paciente.COLUMN_ALTURA);
        paciente.setAltura(c.getDouble(columnId));

        columnId = c.getColumnIndex(Paciente.COLUMN_DATA);
        paciente.setData(DateUtil.stringToDate(c.getString(columnId)));

        columnId = c.getColumnIndex(Paciente.COLUMN_SOBRENOME);
        paciente.setSobrenome(c.getString(columnId));

        columnId = c.getColumnIndex(Paciente.COLUMN_TELEFONE);
        paciente.setTelefone(c.getInt(columnId));

        columnId = c.getColumnIndex(Paciente.COLUMN_ID_LEITO);
        paciente.setId_leito(c.getInt(columnId));

        columnId = c.getColumnIndex(Paciente.COLUMN_IDHOSPITAL);
        paciente.setIdHospital(c.getInt(columnId));

        return paciente;
    }

    @Override
    protected String[] getColumns(){
        return new String[]{Paciente.COLUMN_ID, Paciente.COLUMN_NOME, Paciente.COLUMN_RG,
                Paciente.COLUMN_CPF, Paciente.COLUMN_PESO, Paciente.COLUMN_ALTURA,
                Paciente.COLUMN_DATA, Paciente.COLUMN_SOBRENOME, Paciente.COLUMN_TELEFONE,
                Paciente.COLUMN_ID_LEITO, Paciente.COLUMN_IDHOSPITAL};
    }

    protected ContentValues convertToContentValue(Paciente paciente) {
        ContentValues values = new ContentValues();

        values.put(Paciente.COLUMN_NOME, paciente.getNome());
        values.put(Paciente.COLUMN_RG, paciente.getRg());
        values.put(Paciente.COLUMN_CPF, paciente.getCpf());
        values.put(Paciente.COLUMN_PESO, paciente.getPeso());
        values.put(Paciente.COLUMN_ALTURA, paciente.getAltura());
        values.put(Paciente.COLUMN_DATA, DateUtil.dateToString(paciente.getData()));
        values.put(Paciente.COLUMN_SOBRENOME, paciente.getSobrenome());
        values.put(Paciente.COLUMN_TELEFONE, paciente.getTelefone());
        values.put(Paciente.COLUMN_ID_LEITO, paciente.getId_leito());
        values.put(Paciente.COLUMN_IDHOSPITAL, paciente.getIdHospital());

        return values;
    }

    @Override
    protected String getTable() {
        return Paciente.TABLE;
    }

    @Override
    protected String getColumnId(){
        return Paciente.COLUMN_ID;
    }

}
