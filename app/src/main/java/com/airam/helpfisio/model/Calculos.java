package com.airam.helpfisio.model;

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
    public static final String COLUMN_IDINDEX = "idindex";

    //CRIANDO TABELAS
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE + "( "
            + COLUMN_ID             +   " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_IDPACIENTE     +   " INTEGER,"
            + COLUMN_NOME           +   " TEXT,"
            + COLUMN_RESULTADO      +   " REAL,"
            + COLUMN_DATA           +   " TEXT,"
            + COLUMN_HORA           +   " TEXT,"
            + COLUMN_OBSERVACOES    +   " TEXT,"
            + COLUMN_IDINDEX        +   " INTEGER,"
            + "FOREIGN KEY("+ COLUMN_IDPACIENTE +") REFERENCES " + Paciente.TABLE + "(" +Paciente.COLUMN_ID+ "))";

    private int id, idPaciente, idIndex;

    public int getIdIndex() {
        return idIndex;
    }

    public void setIdIndex(int idIndex) {
        this.idIndex = idIndex;
    }

    private String nome, data, hora, observacoes;
    private double resultado;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
