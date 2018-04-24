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
    public static final String COLUMN_ID_LEITO = "id_leito";
    public static final String COLUMN_TELEFONE = "telefone";
    public static final String COLUMN_SOBRENOME = "sobrenome";


    // Criando Tabela
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE + "( "
            + COLUMN_ID         +   " INTEGER PRIMARY KEY,"
            + COLUMN_NOME       +   " TEXT,"
            + COLUMN_RG         +   " INTEGER,"
            + COLUMN_CPF        +   " TEXT,"
            + COLUMN_PESO       +   " REAL,"
            + COLUMN_ALTURA     +   " REAL,"
            + COLUMN_TELEFONE   +   " INTEGER,"
            + COLUMN_SOBRENOME  +   " TEXT,"
            + COLUMN_ID_LEITO   +   " INTEGER,"
            + COLUMN_DATA       +   " TEXT,"
            + "FOREIGN KEY("+ COLUMN_ID_LEITO +") REFERENCES " + Leito.TABLE + "(" +Leito.COLUMN_ID + "))";

    private int id;
    private int rg;
    private int telefone;
    private int id_leito;
    private String nome, cpf, sobrenome, data;
    private double peso, altura;


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

    public int getId_leito() {
        return id_leito;
    }

    public void setId_leito(int id_leito) {
        this.id_leito = id_leito;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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