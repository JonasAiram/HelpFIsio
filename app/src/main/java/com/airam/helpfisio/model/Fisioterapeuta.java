package com.airam.helpfisio.model;

/**
 * Created by jonas on 01/11/2017.
 */

public class Fisioterapeuta extends Pessoa{


    //NOME DA TABELA
    public static final String TABLE = "fisioterapeuta";


    //NOME DAS COLUNAS
    public static final String COLUMN_CREFITO = "crefito";
    public static final String COLUMN_CARGO = "cargo";
    public static final String COLUMN_SALARIO = "salario";

    //CRIANDO TABELAS

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE + "( " + SQL_PESSOA
            + COLUMN_CREFITO    +   " INTEGER,"
            + COLUMN_CARGO      +   " TEXT,"
            + COLUMN_SALARIO    +   " REAL)";

    private int crefito;
    private String cargo;
    private double salario;

    public int getCrefito() {
        return crefito;
    }

    public void setCrefito(int crefito) {
        this.crefito = crefito;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
