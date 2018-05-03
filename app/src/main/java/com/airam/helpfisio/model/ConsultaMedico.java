package com.airam.helpfisio.model;

/**
 * Created by jonas on 01/11/2017.
 */

public class ConsultaMedico {

    //NOME DA TABELA
    public static final String TABLE = "consultamedico";

    //NOME DAS COLUNAS
    public static final String COLUMN_IDMEDICO = "id_medico";
    public static final String COLUMN_IDPACIENTE = "id_paciente";
    public static final String COLUMN_DESC = "desc";
    public static final String COLUMN_MEDICACAO = "medicacao";
    public static final String COLUMN_TRATAMENTO = "tratamento";
    public static final String COLUMN_DATA = "data";
    public static final String COLUMN_HORA = "hora";
    public static final String COLUMN_ESPECIALIDADE = "especialidade";


    //CRIANDO TABELAS
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE + "( "
            + COLUMN_IDMEDICO       +   " INTEGER,"
            + COLUMN_IDPACIENTE     +   " INTEGER,"
            + COLUMN_DESC           +   " TEXT,"
            + COLUMN_MEDICACAO      +   " TEXT,"
            + COLUMN_TRATAMENTO     +   " TEXT,"
            + COLUMN_DATA           +   " TEXT,"
            + COLUMN_HORA           +   " TEXT,"
            + COLUMN_ESPECIALIDADE  +   " TEXT,"
            + "PRIMARY KEY("+ COLUMN_IDPACIENTE + "," + COLUMN_IDMEDICO + "),"
            + "FOREIGN KEY("+ COLUMN_IDMEDICO +") REFERENCES " + Medico.TABLE + "(" +Medico.COLUMN_ID + "),"
            + "FOREIGN KEY("+ COLUMN_IDPACIENTE +") REFERENCES " + Paciente.TABLE + "(" +Paciente.COLUMN_ID + "))";

    private int idMedico, idPaciente;
    private String descricao, medicacao, tratamento, data, hora, especialidadeConsulta;

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

    public String getEspecialidadeConsulta() {
        return especialidadeConsulta;
    }

    public void setEspecialidadeConsulta(String especialidadeConsulta) {
        this.especialidadeConsulta = especialidadeConsulta;
    }
}
