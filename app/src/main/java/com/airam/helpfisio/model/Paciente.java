package com.airam.helpfisio.model;

/**
 * Created by jonas on 01/11/2017.
 */

public class Paciente {

    // Nome da tabela
    public static final String TABLE = "paciente";

    // Nome das colunas
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_RG = "RG";
    public static final String COLUMN_CPF = "CPF";
    public static final String COLUMN_PESO = "peso";
    public static final String COLUMN_ALTURA = "altura";
    public static final String COLUMN_DATA = "data";

    // Criando Tabela
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE + "( "
            + COLUMN_ID     +   " INTEGER PRIMARY KEY,"
            + COLUMN_NOME   +   " TEXT,"
            + COLUMN_RG     +   " TEXT,"
            + COLUMN_CPF    +   " TEXT,"
            + COLUMN_PESO   +   " TEXT,"
            + COLUMN_ALTURA +   " TEXT,"
            + COLUMN_DATA   +   " TEXT)";

    private int id;
    private String nome, RG, CPF, peso, altura, data;

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}