package br.ufrn.controle;

import java.rmi.RemoteException;

import br.ufrn.excecoes.BDexception;

public interface AlertaInterface {

	public void notificarAlerta(String mensagem, String medicacao, int dosagem) throws BDexception, RemoteException;

}
