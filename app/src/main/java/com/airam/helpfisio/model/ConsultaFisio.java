package com.airam.helpfisio.model;

import java.util.Date;

/**
 * Created by jonas on 01/11/2017.
 */

public class ConsultaFisio {



    //NOME DA TABELA
    public static final String TABLE = "consultafisio";

    //NOME DAS COLUNAS
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IDFISIO = "id_fisio";
    public static final String COLUMN_IDPACIENTE = "id_paciente";
    public static final String COLUMN_DESC = "desc";
    public static final String COLUMN_PATOLOGIA = "patologia";
    public static final String COLUMN_TRATAMENTO = "tratamento";
    public static final String COLUMN_DATA = "data";
    public static final String COLUMN_HORA = "hora";
    public static final String COLUMN_ESPECIALIDADE = "especialidade";
    public static final String COLUMN_VALOR = "valor";
    public static final String COLUMN_VALORPAGO= "valorpago";


    //CRIANDO TABELAS
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE + "( "
            + COLUMN_ID             +   " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_IDFISIO        +   " INTEGER,"
            + COLUMN_IDPACIENTE     +   " INTEGER,"
            + COLUMN_DESC           +   " TEXT,"
            + COLUMN_PATOLOGIA      +   " TEXT,"
            + COLUMN_TRATAMENTO     +   " TEXT,"
            + COLUMN_DATA           +   " TEXT,"
            + COLUMN_HORA           +   " TEXT,"
            + COLUMN_ESPECIALIDADE  +   " TEXT,"
            + COLUMN_VALOR          +   " REAL,"
            + COLUMN_VALORPAGO      +   " REAL,"
            + "FOREIGN KEY("+ COLUMN_IDFISIO +") REFERENCES " + Fisioterapeuta.TABLE + "(" +Fisioterapeuta.COLUMN_ID + "),"
            + "FOREIGN KEY("+ COLUMN_IDPACIENTE +") REFERENCES " + Paciente.TABLE + "(" +Paciente.COLUMN_ID + "))";

    private int id, idFisio, idPaciente;
    private String descricao, patologia, tratamento, hora, especialidade;
    private Date data;
    private double valorPago;
    private double valorConsulta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(double valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    public int getIdFisio() {
        return idFisio;
    }

    public void setIdFisio(int idFisio) {
        this.idFisio = idFisio;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPatologia() {
        return patologia;
    }

    public void setPatologia(String patologia) {
        this.patologia = patologia;
    }

    public String getTratamento() {
        return tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
