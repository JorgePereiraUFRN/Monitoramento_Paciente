package br.ufrn.Controle;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import br.ufrn.BD.BDalertasInterface;
import br.ufrn.BD.BdAlertas;
import br.ufrn.callback.MedicoRMI;
import br.ufrn.entidades.Alerta;
import br.ufrn.excecoes.BDexception;
import br.ufrn.gui.GUI;

public class MedicoFachada implements MedicoFachadaInterface {
	
	private final GUI gui;
	private final MedicoRMI medicoRMI;
	private final BDalertasInterface bd = new BdAlertas();
	

	public MedicoFachada(GUI gui) throws RemoteException, MalformedURLException {
		super();
		this.gui = gui;
		medicoRMI = MedicoRMI.getInstance(this);
	}

	@Override
	public void atualizarGUI(String paciente) {
		this.gui.atualizarGUI(paciente);

	}

	@Override
	public List<Alerta> recuperarAlertas(String paciente, Date data) throws BDexception {
		return bd.listarAlertas();
	}

	@Override
	public void enviarMensagemAoPaciente(String paciente, String mensagem) {
		// TODO Auto-generated method stub

	}

}
