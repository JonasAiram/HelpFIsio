package com.airam.helpfisio.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.airam.helpfisio.adapter.DataBaseAdapter;
import com.airam.helpfisio.model.Calculos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas on 14/04/2018.
 */

public abstract class BaseController<T> {

    private DataBaseAdapter adapter;
    protected SQLiteDatabase db;

    public BaseController(Context context){

        //INTEGRAÇÃO COM O BANCO
        adapter = new DataBaseAdapter(context);
        db = adapter.getWritableDatabase();

    }

    protected abstract String[] getColumns();

    public List<T> getAll(){

            Cursor c = db.query(getTable(), getColumns(), null, null, null, null, null);

            List<T> objctlis = new ArrayList<T>();

            if (c.moveToFirst()){
                do {
                    T obj = convertToObject(c);
                    objctlis.add(obj);
                } while (c.moveToNext());
            }
            c.close();

            return objctlis;
        }

    protected abstract String getTable();

    protected abstract String getColumnId();

    protected abstract ContentValues convertToContentValue(T obj);

    protected abstract T convertToObject(Cursor c);

    public void closeDb(){
        db.close();
    }

    public boolean delete(int id) {
        boolean isDelete = false;
        isDelete = db.delete(getTable(), getColumnId() +" ='" + id + "'", null) > 0;
        return isDelete;
    }

    public void edit(T obj, int id){
        ContentValues contentValues = convertToContentValue(obj);
        db.update(getTable(), contentValues, getColumnId() + " =' " + id + "'",null);
    }

}
