package br.ufrn.callback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PacienteRMI extends UnicastRemoteObject implements CallbackPacienteInterface{

	protected PacienteRMI() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void enviarMensagem(String mensagem) throws RemoteException {
		
		
	}

	

}
