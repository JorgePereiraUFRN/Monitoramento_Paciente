package br.ufrn.controle;

import java.rmi.RemoteException;

import br.ufrn.excecoes.BDexception;
import br.ufrn.excecoes.ErroRMIException;

public interface AlertaInterface {

	public void notificarAlerta(String mensagem, String medicacao, int dosagem) throws BDexception, RemoteException, ErroRMIException;

}
