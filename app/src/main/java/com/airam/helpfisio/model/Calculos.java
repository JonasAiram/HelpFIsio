package com.airam.helpfisio.model;

import java.util.Date;

/**
 * Created by jonas on 01/11/2017.
 */

public class Calculos {


    //NOME DA TABELA
    public static final String TABLE = "calculos";


    //NOME DAS COLUNAS
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_IDPACIENTE = "idpaciente";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_RESULTADO = "resultado";
    public static final String COLUMN_DATA = "data";
    public static final String COLUMN_HORA = "hora";
    public static final String COLUMN_OBSERVACOES = "observacoes";
    public static final String COLUMN_FISIO = "fisio";
    public static final String COLUMN_UNIDADE = "unidade";
    public static final String COLUMN_DESCRICAO= "descricao";
    public static final String COLUMN_MEDICO = "medico";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE + "( "
            + COLUMN_ID             +   " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_IDPACIENTE     +   " INTEGER,"
            + COLUMN_NOME           +   " TEXT,"
            + COLUMN_RESULTADO      +   " REAL,"
            + COLUMN_DATA           +   " TEXT,"
            + COLUMN_HORA           +   " TEXT,"
            + COLUMN_OBSERVACOES    +   " TEXT,"
            + COLUMN_FISIO          +   " TEXT,"
            + COLUMN_UNIDADE        +   " TEXT,"
            + COLUMN_DESCRICAO      +   " TEXT,"
            + COLUMN_MEDICO         +   " TEXT,"
            + "FOREIGN KEY("+ COLUMN_IDPACIENTE +") REFERENCES " + Paciente.TABLE + "(" +Paciente.COLUMN_ID+ "))";

    //CRIANDO TABELAS

    private int id, idPaciente;
    private String nome, hora, observacoes, fisio, unidade, descricao, medico;
    private double resultado;
    private Date data;

    public String getFisio() {
        return fisio;
    }

    public void setFisio(String fisio) {
        this.fisio = fisio;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }
}
