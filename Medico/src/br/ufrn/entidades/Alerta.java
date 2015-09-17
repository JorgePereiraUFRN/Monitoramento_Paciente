package br.ufrn.entidades;

import java.sql.Date;

public class Alerta {
	
	private long id;
	private SinalVital sinalVital;
	private int valor;
	private Date data;
	private Paciente paciente;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
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
