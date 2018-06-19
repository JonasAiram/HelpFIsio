package com.airam.helpfisio.model;

/**
 * Created by jonas on 01/11/2017.
 */

public class Hospital {

    // Nome da tabela
    public static final String TABLE = "hospital";

    // Nome das colunas
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_RUA = "rua";
    public static final String COLUMN_BAIRRO = "bairro";
    public static final String COLUMN_CIDADE = "cidade";
    public static final String COLUMN_UF = "uf";
    public static final String COLUMN_DIRETOR = "diretor";
    public static final String COLUMN_TELEFONE = "telefone";
    public static final String COLUMN_NUMENRO = "numero";
    public static final String COLUMN_DTCRIACAO = "dtcriacao";
    public static final String COLUMN_VALOR = "valor";

    // Criando Tabela
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE + "( "
            + COLUMN_ID         +   " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NOME       +   " TEXT,"
            + COLUMN_RUA        +   " TEXT,"
            + COLUMN_BAIRRO     +   " TEXT,"
            + COLUMN_CIDADE     +   " TEXT,"
            + COLUMN_UF         +   " TEXT,"
            + COLUMN_DIRETOR    +   " TEXT,"
            + COLUMN_NUMENRO    +   " INTEGER,"
            + COLUMN_DTCRIACAO  +   " TEXT,"
            + COLUMN_VALOR      +   " REAL,"
            + COLUMN_TELEFONE   +   " INTEGER)";

    //DECLARANDO VARIAVEIS
    private int id;
    private int telefone;
    private int numero;

    private String nome;
    private String rua;
    private String bairro;
    private String cidade;
    private String UF;
    private String diretor;
    private String dtCriacao;

    private double valor;

    public String getdtCriacao() {
        return dtCriacao;
    }

    public void setdtCriacao(String data) {
        this.dtCriacao = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
}