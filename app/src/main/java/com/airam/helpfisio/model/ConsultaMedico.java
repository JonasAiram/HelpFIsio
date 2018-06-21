package com.airam.helpfisio.model;

import java.util.Date;

/**
 * Created by jonas on 01/11/2017.
 */

public class ConsultaMedico {

    //NOME DA TABELA
    public static final String TABLE = "consultamedico";

    //NOME DAS COLUNAS
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IDMEDICO = "id_medico";
    public static final String COLUMN_IDPACIENTE = "id_paciente";
    public static final String COLUMN_PACIENTENOME = "nomepaciente";
    public static final String COLUMN_DESC = "desc";
    public static final String COLUMN_MEDICACAO = "medicacao";
    public static final String COLUMN_TRATAMENTO = "tratamento";
    public static final String COLUMN_DATA = "data";
    public static final String COLUMN_HORA = "hora";
    public static final String COLUMN_ESPECIALIDADE = "especialidade";
    public static final String COLUMN_VALOR = "valor";
    public static final String COLUMN_VALORPAGO= "valorpago";



    //CRIANDO TABELAS
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE + "( "
            + COLUMN_ID             +   " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_IDMEDICO       +   " INTEGER,"
            + COLUMN_IDPACIENTE     +   " INTEGER,"
            + COLUMN_PACIENTENOME   +   " TEXT,"
            + COLUMN_DESC           +   " TEXT,"
            + COLUMN_MEDICACAO      +   " TEXT,"
            + COLUMN_TRATAMENTO     +   " TEXT,"
            + COLUMN_DATA           +   " TEXT,"
            + COLUMN_HORA           +   " TEXT,"
            + COLUMN_ESPECIALIDADE  +   " TEXT,"
            + COLUMN_VALOR          +   " REAL,"
            + COLUMN_VALORPAGO      +   " REAL,"
            + "FOREIGN KEY("+ COLUMN_IDMEDICO +") REFERENCES " + Medico.TABLE + "(" +Medico.COLUMN_ID + "),"
            + "FOREIGN KEY("+ COLUMN_IDPACIENTE +") REFERENCES " + Paciente.TABLE + "(" +Paciente.COLUMN_ID + "))";

    private int idMedico;
    private int idPaciente;
    private int id;
    private String descricao, medicacao, tratamento, hora, especialidadeConsulta, pacienteNome;

    private double valorPago;

    private Date data;
    private double valorConsulta;
    public String getPacienteNome() {
        return pacienteNome;
    }

    public void setPacienteNome(String pacienteNome) {
        this.pacienteNome = pacienteNome;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(double valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
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

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
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

    public String getMedicacao() {
        return medicacao;
    }

    public void setMedicacao(String medicacao) {
        this.medicacao = medicacao;
    }

    public String getTratamento() {
        return tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    public Date getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEspecialidadeConsulta() {
        return especialidadeConsulta;
    }

    public void setEspecialidadeConsulta(String especialidadeConsulta) {
        this.especialidadeConsulta = especialidadeConsulta;
    }
}
