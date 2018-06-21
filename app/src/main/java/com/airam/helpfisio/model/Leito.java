package com.airam.helpfisio.model;

import java.util.Date;

/**
 * Created by jonas on 01/11/2017.
 */

public class Leito {

    //Nome da tabela
    public static final String TABLE = "leito";

    //Nome da Colunas
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TIPO = "tipo";
    public static final String COLUMN_QUANTIDADE = "quantidade";
    public static final String COLUMN_CHEFE = "chefe";
    public static final String COLUMN_ANDAR = "andar";
    public static final String COLUMN_ID_HOSPIAL = "id_hospital";
    public static final String COLUMN_DATA = "data";
    public static final String COLUMN_MEDICORESP = "medicoresp";
    public static final String COLUMN_FISIORESP = "fisioresp";
    public static final String COLUMN_ORCAMENTOMENSAL = "orcamentomensal";
    public static final String COLUMN_CUSTOSEMANAL = "custosemanal";

    //Criando a tabela
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE + "( "
            + COLUMN_ID             +   " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_TIPO           +   " TEXT,"
            + COLUMN_QUANTIDADE     +   " INTEGER,"
            + COLUMN_CHEFE          +   " TEXT,"
            + COLUMN_ANDAR          +   " INTEGER,"
            + COLUMN_ID_HOSPIAL     +   " INTEGER,"
            + COLUMN_DATA           +   " TEXT,"
            + COLUMN_MEDICORESP     +   " TEXT,"
            + COLUMN_FISIORESP      +   " TEXT,"
            + COLUMN_ORCAMENTOMENSAL+   " REAL,"
            + COLUMN_CUSTOSEMANAL   +   " REAL,"
            + " FOREIGN KEY("+ COLUMN_ID_HOSPIAL +") REFERENCES " + Hospital.TABLE + "(" + Hospital.COLUMN_ID + "))";

    private int id;
    private int quantidade;
    private int andar;
    private int id_Hospital;
    private String tipo, chefe, medicoResp, fisioResp;
    private Double orcamentoMesal;
    private Date data;

    public String getMedicoResp() {
        return medicoResp;
    }

    public void setMedicoResp(String medicoResp) {
        this.medicoResp = medicoResp;
    }

    public String getFisioResp() {
        return fisioResp;
    }

    public void setFisioResp(String fisioResp) {
        this.fisioResp = fisioResp;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getOrcamentoMesal() {
        return orcamentoMesal;
    }

    public void setOrcamentoMesal(Double orcamentoMesal) {
        this.orcamentoMesal = orcamentoMesal;
    }

    public Double getCustoSemanal() {
        return custoSemanal;
    }

    public void setCustoSemanal(Double custoSemanal) {
        this.custoSemanal = custoSemanal;
    }

    private Double custoSemanal;


    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public int getId_Hospital() {
        return id_Hospital;
    }

    public void setId_Hospital(int id_Hospital) {
        this.id_Hospital = id_Hospital;
    }

    public String getChefe() {
        return chefe;
    }

    public void setChefe(String chefe) {
        this.chefe = chefe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
