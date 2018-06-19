package com.airam.helpfisio.model;

import java.util.Date;

public class Pessoa {

    // Nome das colunas
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_RG = "RG";
    public static final String COLUMN_CPF = "CPF";
    public static final String COLUMN_DATA = "data";
    public static final String COLUMN_TELEFONE = "telefone";
    public static final String COLUMN_SOBRENOME = "sobrenome";

    // Criando Tabela
    public static final String SQL_PESSOA = COLUMN_ID         +   " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NOME       +   " TEXT,"
            + COLUMN_RG         +   " INTEGER,"
            + COLUMN_CPF        +   " TEXT,"
            + COLUMN_TELEFONE   +   " INTEGER,"
            + COLUMN_SOBRENOME  +   " TEXT,"
            + COLUMN_DATA       +   " TEXT,";

    private int id;
    private int rg;
    private int telefone;
    private String nome, cpf, sobrenome;
    private Date Data;

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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date data) {
        Data = data;
    }
}
