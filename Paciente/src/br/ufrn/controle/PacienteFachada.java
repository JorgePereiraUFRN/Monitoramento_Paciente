package br.ufrn.controle;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.coach.tracing.service.ntp.TimeStamp;

import br.ufrn.BD.AlertaBD;
import br.ufrn.BD.BDpacienteInterface;
import br.ufrn.BD.Bdpaciente;
import br.ufrn.callback.CallbackPacienteInterface;
import br.ufrn.callback.MedicoInterface;
import br.ufrn.context.PacienteService;
import br.ufrn.entidades.Alerta;
import br.ufrn.entidades.Paciente;
import br.ufrn.entidades.SinalVital;
import br.ufrn.excecoes.BDexception;
import br.ufrn.excecoes.ErroRMIException;
import context.arch.discoverer.Discoverer;
import context.arch.enactor.Enactor;
import context.arch.enactor.EnactorXmlParser;
import context.arch.widget.Widget;
import context.arch.widget.WidgetXmlParser;

public class PacienteFachada extends UnicastRemoteObject implements PacienteFachadaInterface, CallbackPacienteInterface {

	// private MonitoramentoPaciente paciente;
	private final Widget widgetPaciente;
	private final Enactor pacienteEnactor;
	private final PacienteService pacienteService;
	private final Widget widgetSaida;
	
	private final Email email = new Email();
	private final AlertaBD bd = new AlertaBD();
	private BDpacienteInterface BDpaciente = new Bdpaciente();
	private MedicoInterface medico;
	
	private Paciente paciente = new Paciente();
	private PacienteGUI gui;
	
	

	public PacienteFachada(PacienteGUI gui) throws RemoteException {
		this.gui = gui;
		
		
		Discoverer.start();
               
		widgetPaciente = WidgetXmlParser.createWidget("widgets-enactor/sinaisVitais-widget.xml");

		widgetSaida = WidgetXmlParser.createWidget("widgets-enactor/output-widget.xml");

		pacienteService = new PacienteService(widgetSaida, this);
		widgetSaida.addService(pacienteService);

		pacienteEnactor = EnactorXmlParser.createEnactor("widgets-enactor/sinaisVitais-enactor.xml");
		
		widgetPaciente.updateData("nivelGlicose", 80);
		widgetPaciente.updateData("batimentosCardiacos", 80);
		widgetPaciente.updateData("pressao", 10);
	}
	
	private MedicoInterface recuperarReferenciaMedico() throws MalformedURLException, RemoteException, NotBoundException{
		MedicoInterface medico = null;
		
		String registryURL = "rmi://localhost:2000/medico";
		
		medico = (MedicoInterface) Naming
				.lookup(registryURL);
		
		return medico;
	}

        @Override
	public void atualizarGlicose(int nivelGlicose) {
		widgetPaciente.updateData("nivelGlicose", nivelGlicose);
	}

        @Override
	public void atualizarBatimentos(int batimentosCardiacos) {
		widgetPaciente.updateData("batimentosCardiacos", batimentosCardiacos);
	}

        @Override
	public void atualizarPressao(int pressao) {
		widgetPaciente.updateData("pressao", pressao);
  
	}

	@Override
	public void notificarAlerta(String mensagem, String medicacao, int dosagem) throws BDexception, RemoteException, ErroRMIException {	
		
		Alerta alerta = new Alerta();
		alerta.setData(new Timestamp(System.currentTimeMillis()));
		alerta.setDosagem(dosagem);
		alerta.setMedicacao(medicacao);
		alerta.setSinalVitasDesconforme(widgetSaida.getNonConstantAttributeValue("sinalVital"));
		
		Map<String, Integer> sinaisVitais = new HashMap<>();
		
		sinaisVitais.put(SinalVital.BATIMENTOS.toString(), widgetPaciente.getNonConstantAttributeValue("batimentosCardiacos"));
		sinaisVitais.put(SinalVital.GLICOSE.toString(), widgetPaciente.getNonConstantAttributeValue("nivelGlicose"));
		sinaisVitais.put(SinalVital.PRESSAO.toString(), widgetPaciente.getNonConstantAttributeValue("pressao"));
		
	
		alerta.setSinaisVitais(sinaisVitais);
		alerta.setPaciente(BDpaciente.listarPacienteByName(gui.getNomePaciente()).get(0));
		
		
		if(alerta != null){
		
		String mensagemEmail = " Sinais vitais do Paciente em "+ alerta.getData().toLocaleString() +"\n";
		
		for(String s : sinaisVitais.keySet()){
			mensagemEmail += s +" = "+sinaisVitais.get(s)+"\n";
		}
		
		mensagemEmail += "\nMedicação aplicada:" +alerta.getMedicacao() + " dosagem: "+alerta.getDosagem();
			
		
		String assunto= alerta.getSinalVitalDesconforme() +" do Paiciente "+alerta.getPaciente()+" fora dos limites";
		
		//email.enviarEmail(assunto, mensagem, "paciente@topicos.com.br");
		
		if(medico == null){
			try {
				medico = recuperarReferenciaMedico();
				medico.registrarPaciente(this, gui.getNomePaciente());
				
			} catch (MalformedURLException | NotBoundException e) {
				throw new ErroRMIException(e.getMessage());
			}
			
		}
		medico.notificarAlerta(alerta.getPaciente().getNome());
		
		bd.salvarDadosAlerta(alerta);
		
		gui.updateGUI(alerta.getMedicacao(), alerta.getData(), alerta.getDosagem(), mensagem);
		
		}
		
	}

	@Override
	public void enviarMensagem(String mensagem) throws RemoteException {
		
		
	}

	@Override
	public void inserirNomePaciente(String nome) throws BDexception {
		
		if(BDpaciente.listarPacienteByName(nome).size() == 0){
			BDpaciente.casdastrarPaciente(nome);
		}
		
	}

}
