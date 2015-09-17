package br.ufrn.excecoes;

import java.rmi.RemoteException;

public class PacienteJaExisteException extends RemoteException {

	public PacienteJaExisteException() {
		// TODO Auto-generated constructor stub
	}

	public PacienteJaExisteException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PacienteJaExisteException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	

}
