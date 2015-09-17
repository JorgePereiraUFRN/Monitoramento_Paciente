package br.ufrn.callback;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallbackPacienteInterface extends Remote {
	
	public void enviarMensagem(String mensagem) throws RemoteException;

}
