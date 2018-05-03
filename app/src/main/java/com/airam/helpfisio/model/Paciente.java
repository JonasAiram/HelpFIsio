package com.airam.helpfisio.model;

/**
 * Created by jonas on 01/11/2017.
 */

public class Paciente extends Pessoa{

    // Nome da tabela
    public static final String TABLE = "paciente";

    // Nome das colunas
    public static final String COLUMN_PESO = "peso";
    public static final String COLUMN_ALTURA = "altura";
    public static final String COLUMN_ID_LEITO = "id_leito";

    // Criando Tabela
    public final static String SQL_CREATE = "CREATE TABLE " + TABLE + "( " + SQL_PESSOA
            + COLUMN_PESO       +   " REAL,"
            + COLUMN_ALTURA     +   " REAL,"
            + COLUMN_ID_LEITO   +   " INTEGER,"
            + "FOREIGN KEY("+ COLUMN_ID_LEITO +") REFERENCES " + Leito.TABLE + "(" +Leito.COLUMN_ID + "))";

    private int id_leito;
    private double peso, altura;

    public int getId_leito() {
        return id_leito;
    }

    public void setId_leito(int id_leito) {
        this.id_leito = id_leito;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

}