package br.ufrn.Controle;


import java.util.Date;
import java.util.List;

import br.ufrn.entidades.Alerta;
import br.ufrn.excecoes.BDexception;
import br.ufrn.gui.GUI;


public interface MedicoFachadaInterface extends GUI {
	
	public List<Alerta> recuperarAlertas(String paciente, Date data) throws BDexception;
	
	public void enviarMensagemAoPaciente(String paciente, String mensagem);
	
}
