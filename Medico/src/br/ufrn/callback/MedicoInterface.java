package br.ufrn.callback;

import java.rmi.Remote;
import java.rmi.RemoteException;

import br.ufrn.entidades.Alerta;

public interface MedicoInterface extends Remote{ 
	
	public void notificarAlerta( String nomePaciente) throws RemoteException;
	
	public void registrarPaciente(CallbackPacienteInterface paciente, String nomePaciente) throws RemoteException;
	
	public void desresgistrarPaciente(String nomePaciente) throws RemoteException;

}
