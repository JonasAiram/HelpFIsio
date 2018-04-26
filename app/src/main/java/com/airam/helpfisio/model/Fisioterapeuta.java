package com.airam.helpfisio.model;

/**
 * Created by jonas on 01/11/2017.
 */

public class Fisioterapeuta {


    //NOME DA TABELA
    public static final String TABLE = "fisioterapeuta";


    //NOME DAS COLUNAS
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_RG = "RG";
    public static final String COLUMN_CPF = "CPF";
    public static final String COLUMN_DATA = "data";
    public static final String COLUMN_SOBRENOME = "sobrenome";
    public static final String COLUMN_CREFITO = "crefito";
    public static final String COLUMN_CARGO = "cargo";
    public static final String COLUMN_TELEFONE = "telefone";
    public static final String COLUMN_SALARIO = "salario";


    //CRIANDO TABELAS

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE + "( "
            + COLUMN_ID         +   " INTEGER PRIMARY KEY,"
            + COLUMN_NOME       +   " TEXT,"
            + COLUMN_RG         +   " INTEGER,"
            + COLUMN_CPF        +   " TEXT,"
            + COLUMN_DATA       +   " TEXT,"
            + COLUMN_SOBRENOME  +   " TEXT,"
            + COLUMN_CREFITO    +   " INTEGER,"
            + COLUMN_CARGO      +   " TEXT,"
            + COLUMN_TELEFONE   +   " INTEGER,"
            + COLUMN_SALARIO    +   " REAL)";

    private int id;

    private int rg;

    private int telefone;
    private int crefito;
    private String nome, cpf, data, sobrenome, cargo;
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

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public int getCrefito() {
        return crefito;
    }

    public void setCrefito(int crefito) {
        this.crefito = crefito;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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
