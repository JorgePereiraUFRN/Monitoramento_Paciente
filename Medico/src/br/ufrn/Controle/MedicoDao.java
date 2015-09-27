package br.ufrn.Controle;

import java.util.Date;
import java.util.List;

import br.ufrn.entidades.Alerta;
import br.ufrn.excecoes.BDexception;

public interface MedicoDao {

	
	public List<Alerta> recuperarAlertas(String paciente, Date data) throws BDexception;
}
