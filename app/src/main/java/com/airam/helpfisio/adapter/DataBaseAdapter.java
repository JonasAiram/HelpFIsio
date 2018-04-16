package com.airam.helpfisio.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.airam.helpfisio.model.Hospital;
import com.airam.helpfisio.model.Leito;
import com.airam.helpfisio.model.Medico;
import com.airam.helpfisio.model.Paciente;

/**
 * Created by jonas on 10/11/2017.
 */

public class DataBaseAdapter extends SQLiteOpenHelper {

    public DataBaseAdapter(Context context) {
        super(context, "helpfisio.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Paciente.SQL_CREATE);
        db.execSQL(Hospital.SQL_CREATE);
        db.execSQL(Leito.SQL_CREATE);
        db.execSQL(Medico.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
