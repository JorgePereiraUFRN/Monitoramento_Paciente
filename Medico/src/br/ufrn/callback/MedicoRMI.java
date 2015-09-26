package br.ufrn.callback;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import br.ufrn.Controle.MedicoFachadaInterface;
import br.ufrn.excecoes.PacienteJaExisteException;
import br.ufrn.gui.GUI;

public class MedicoRMI extends UnicastRemoteObject implements MedicoInterface, CallbackMensagem{
	
	private final Map<String, CallbackPacienteInterface> pacientes = new HashMap<>();
	private static MedicoRMI MEDICO;
	private MedicoFachadaInterface medicoFachada;
	
	private MedicoRMI(MedicoFachadaInterface medicoFachada) throws RemoteException {
		super();
		this.medicoFachada = medicoFachada;
	}
	
	public static synchronized MedicoRMI getInstance(MedicoFachadaInterface medicoFachada) throws RemoteException, MalformedURLException{
		if(MEDICO == null){
			MEDICO = new MedicoRMI(medicoFachada);
			registrarInterface(MEDICO);
		}
		
		return MEDICO;
	}
	
	private static void registrarInterface(MedicoInterface medico) throws RemoteException, MalformedURLException{
		int port = 2000;
		String url = "rmi://localhost:" + port + "/medico";
		
		Registry registry = LocateRegistry.createRegistry(port);
		Naming.rebind(url, medico);
	}
	

	@Override
	public void registrarPaciente(CallbackPacienteInterface paciente, String nomePaciente) throws RemoteException, PacienteJaExisteException{
		
		if(pacientes.get(nomePaciente) == null){
			pacientes.put(nomePaciente, paciente);
		}else{
			throw new PacienteJaExisteException("Ja existe um paciente registrado com o nome: "+nomePaciente);
		}
		
	}

	@Override
	public void desresgistrarPaciente(String nomePaciente) throws RemoteException {	
		pacientes.remove(nomePaciente);
		
	}

	@Override
	public void enviarMensagemAoPaciente(String paciente, String mensagem)throws RemoteException{
		
		CallbackPacienteInterface pacienteCallback = pacientes.get(paciente);
		
		if(pacienteCallback != null){
			pacienteCallback.enviarMensagem(mensagem);
		}
	}

	@Override
	public void notificarAlerta(String nomePaciente) throws RemoteException {
		medicoFachada.atualizarGUI(nomePaciente);
		
	}

	
}
