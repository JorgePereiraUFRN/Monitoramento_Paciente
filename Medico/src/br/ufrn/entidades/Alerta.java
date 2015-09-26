package br.ufrn.entidades;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Alerta {

    private long id;
    private String sinalVitalDesconforme;;
    private Timestamp data;
    private Paciente paciente;
    private String medicacao;
    private int dosagem;
    Map<String, Integer> sinaisVitais = new HashMap<>();

    
    
    
    public Map<String, Integer> getSinaisVitais() {
		return sinaisVitais;
	}

	public void setSinaisVitais(Map<String, Integer> sinaisVitais) {
		this.sinaisVitais = sinaisVitais; 
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
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


    public String getSinalVitalDesconforme() {
        return sinalVitalDesconforme;
    }

    public void setSinalVitasDesconforme(String sinalVital) {
        this.sinalVitalDesconforme = sinalVital;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

}
