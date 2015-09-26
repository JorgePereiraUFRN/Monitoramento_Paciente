package br.ufrn.callback;

import java.rmi.RemoteException;

public interface CallbackMensagem {

	public void enviarMensagemAoPaciente(String paciente, String mensagem) throws RemoteException;
}