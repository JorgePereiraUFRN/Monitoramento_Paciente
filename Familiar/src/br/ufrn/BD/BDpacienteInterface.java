package br.ufrn.BD;

import java.util.Date;
import java.util.List;

import br.ufrn.entidades.Paciente;
import br.ufrn.excecoes.BDexception;

public interface BDpacienteInterface {

	List<Paciente> listarPacientes() throws BDexception;
	
	void casstrarResponsavel(int pacienteId, String emailREsponsavel ) throws BDexception;

}