package br.ufrn.entidades;

import java.sql.Date;

public class Alerta {

    private long id;
    private SinalVital sinalVital;
    private int valor;
    private Date data;
    private String paciente;
    private String medicacao;
    private int dosagem;

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getMedicacao() {
        return medicacao;
    }

    public void setMedicacao(String medicacao) {
        this.medicacao = medicacao;
    }

    public int getDosagem() {
        return dosagem;
    }

    public void setDosagem(int dosagem) {
        this.dosagem = dosagem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public SinalVital getSinalVital() {
        return sinalVital;
    }

    public void setSinalVital(SinalVital sinalVital) {
        this.sinalVital = sinalVital;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
