package com.airam.helpfisio.model;

/**
 * Created by jonas on 01/11/2017.
 */

public class Leito {

    //Nome da tabela
    public static final String TABLE = "leito";

    //Nome da Colunas
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TIPO = "tipo";

    //Criando a tabela
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE + "( "
            + COLUMN_ID     +   " INTEGER PRIMARY KEY,"
            + COLUMN_TIPO   +   " TEXT)";

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
