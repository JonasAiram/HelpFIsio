package com.airam.helpfisio.model;

/**
 * Created by jonas on 01/11/2017.
 */

public class Fisioterapeuta extends Pessoa{


    //NOME DA TABELA
    public static final String TABLE = "fisioterapeuta";


    //NOME DAS COLUNAS
    public static final String COLUMN_PESO = "peso";
    public static final String COLUMN_CREFITO = "crefito";
    public static final String COLUMN_CARGO = "cargo";
    public static final String COLUMN_SALARIO = "salario";

    //CRIANDO TABELAS

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE + "( " + SQL_PESSOA
            + COLUMN_PESO       +   " REAL,"
            + COLUMN_CREFITO    +   " INTEGER,"
            + COLUMN_CARGO      +   " TEXT,"
            + COLUMN_SALARIO    +   " REAL)";

    private int crefito;
    private String cargo;
    private double salario;
    private double peso;

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

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
