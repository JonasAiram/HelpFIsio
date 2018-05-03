package com.airam.helpfisio.model;

/**
 * Created by jonas on 01/11/2017.
 */

public class Medico extends Pessoa{

    //NOME DA TABELA
    public static final String TABLE = "medico";

    //NOME DAS COLUNAS
    public static final String COLUMN_CRM = "CRM";
    public static final String COLUMN_CARGO= "cargo";
    public static final String COLUMN_SALARIO = "salario";

    //CRIANDO TABELAS
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE + "( " + SQL_PESSOA
            + COLUMN_CRM        +   " TEXT,"
            + COLUMN_CARGO      +   " TEXT,"
            + COLUMN_SALARIO    +   " REAL)";

    private int crm;
    private String cargo;
    private double salario;

    public int getCrm() {
        return crm;
    }

    public void setCrm(int crm) {
        this.crm = crm;
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
