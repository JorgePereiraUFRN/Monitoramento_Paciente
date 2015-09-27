package br.ufrn.BD;

import java.util.Date;
import java.util.List;

import br.ufrn.entidades.Paciente;
import br.ufrn.excecoes.BDexception;

public interface BDpacienteInterface {

	List<Paciente> listarPacienteByName(String nome) throws BDexception;
	
	public void casdastrarPaciente(String nome) throws BDexception;

}