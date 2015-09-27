package br.ufrn.controle;

import java.util.Date;

public interface PacienteGUI {
	
	public void updateGUI(String medicamento, Date horario, Integer dosagem, String mensagem);
	
	public String getNomePaciente();

}
