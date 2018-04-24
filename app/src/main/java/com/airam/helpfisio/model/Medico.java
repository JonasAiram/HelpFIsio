package com.airam.helpfisio.model;

/**
 * Created by jonas on 01/11/2017.
 */

public class Medico {

    //NOME DA TABELA
    public static final String TABLE = "medico";

    //NOME DAS COLUNAS
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_RG = "RG";
    public static final String COLUMN_CPF = "CPF";
    public static final String COLUMN_DATA = "data";
    public static final String COLUMN_CRM = "CRM";
    public static final String COLUMN_CARGO= "cargo";
    public static final String COLUMN_TELEFONE = "telefone";
    public static final String COLUMN_SOBRENOME = "sobrenome";
    public static final String COLUMN_SALARIO = "salario";

    //CRIANDO TABELAS
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE + "( "
            + COLUMN_ID         +   " INTEGER PRIMARY KEY,"
            + COLUMN_NOME       +   " TEXT,"
            + COLUMN_RG         +   " INTEGER,"
            + COLUMN_CPF        +   " TEXT,"
            + COLUMN_DATA       +   " TEXT,"
            + COLUMN_CRM        +   " TEXT,"
            + COLUMN_CARGO      +   " TEXT,"
            + COLUMN_TELEFONE   +   " INTEGER,"
            + COLUMN_SOBRENOME  +   " TEXT,"
            + COLUMN_SALARIO    +   " REAL)";

    private int id, rg, cpf, crm;
    private String nome, data, cargo, telefone, sobrenome;
    private double salario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getCrm() {
        return crm;
    }

    public void setCrm(int crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
