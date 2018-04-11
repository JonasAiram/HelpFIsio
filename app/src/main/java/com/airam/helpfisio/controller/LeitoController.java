package com.airam.helpfisio.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.airam.helpfisio.adapter.DataBaseAdapter;
import com.airam.helpfisio.model.Leito;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas on 18/11/2017.
 */

public class LeitoController {
    
    private DataBaseAdapter adapter;
    protected SQLiteDatabase db;
    
    public LeitoController(Context context){
        //Integrando o banco de dados
        
        adapter = new DataBaseAdapter(context);
        db = adapter.getWritableDatabase();
    }
    
    //INSERT
    
    public boolean insert(Leito leito){
        boolean IsCreate = db.insert(Leito.TABLE, null, convertToContentValue(leito)) > 0;
        return IsCreate;
    }

    //Listar tipos de leitos

    public List<Leito> get(){
        Cursor c = db.query(Leito.TABLE, getColumns(), null, null, null, null, null);

        List<Leito> objctlist = new ArrayList<Leito>();

        if (c.moveToFirst()){
            do {
                Leito leito = convertToObject(c);
                objctlist.add(leito);
            }while (c.moveToFirst());
        }

        return objctlist;
    }

    private Leito convertToObject(Cursor c) {
        Leito leito = new Leito();

        int columnId = c.getColumnIndex(Leito.COLUMN_ID);
        leito.setId(c.getInt(columnId));

        columnId = c.getColumnIndex(Leito.COLUMN_TIPO);
        leito.setTipo(c.getString(columnId));

        columnId = c.getColumnIndex(Leito.COLUMN_QUANTIDADE);
        leito.setQuantidade(c.getInt(columnId));

        columnId = c.getColumnIndex(Leito.COLUMN_CHEFE);
        leito.setChefe(c.getString(columnId));

        columnId = c.getColumnIndex(Leito.COLUMN_ANDAR);
        leito.setAndar(c.getInt(columnId));

        columnId = c.getColumnIndex(Leito.COLUMN_ID_HOSPIAL);
        leito.setId_Hospital(c.getInt(columnId));

        return leito;
    }

    private String[] getColumns() {
        return new String[]{Leito.COLUMN_ID, Leito.COLUMN_TIPO, Leito.COLUMN_QUANTIDADE,
                Leito.COLUMN_CHEFE, Leito.COLUMN_ANDAR, Leito.COLUMN_ID_HOSPIAL};
    }


    private ContentValues convertToContentValue(Leito leito) {
        ContentValues values = new ContentValues();

        values.put(Leito.COLUMN_TIPO, leito.getTipo());
        values.put(Leito.COLUMN_QUANTIDADE, leito.getQuantidade());
        values.put(Leito.COLUMN_CHEFE, leito.getChefe());
        values.put(Leito.COLUMN_ANDAR, leito.getAndar());
        values.put(Leito.COLUMN_ID_HOSPIAL, leito.getId_Hospital());

        return values;
    }

}
