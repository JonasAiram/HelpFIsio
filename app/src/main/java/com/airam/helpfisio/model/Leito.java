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
    public static final String COLUMN_QUANTIDADE = "quantidade";
    public static final String COLUMN_CHEFE = "chefe";
    public static final String COLUMN_ANDAR = "andar";
    public static final String COLUMN_ID_HOSPIAL = "id_hospital";

    //Criando a tabela
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE + "( "
            + COLUMN_ID         +   " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_TIPO       +   " TEXT,"
            + COLUMN_QUANTIDADE +   " INTEGER,"
            + COLUMN_CHEFE      +   " TEXT,"
            + COLUMN_ANDAR      +   " INTEGER,"
            + COLUMN_ID_HOSPIAL +   " INTEGER,"
            + " FOREIGN KEY("+ COLUMN_ID_HOSPIAL +") REFERENCES " + Hospital.TABLE + "(" + Hospital.COLUMN_ID + "))";

    private int id;
    private int quantidade;
    private int andar;
    private int id_Hospital;
    private String tipo, chefe;


    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public int getId_Hospital() {
        return id_Hospital;
    }

    public void setId_Hospital(int id_Hospital) {
        this.id_Hospital = id_Hospital;
    }

    public String getChefe() {
        return chefe;
    }

    public void setChefe(String chefe) {
        this.chefe = chefe;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
