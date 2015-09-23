package br.ufrn.controle;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Map;

import br.ufrn.BD.AlertaBD;
import br.ufrn.callback.CallbackPacienteInterface;
import br.ufrn.callback.MedicoInterface;
import br.ufrn.context.PacienteService;
import br.ufrn.entidades.Alerta;
import br.ufrn.entidades.SinalVital;
import br.ufrn.excecoes.BDexception;
import context.arch.discoverer.Discoverer;
import context.arch.enactor.Enactor;
import context.arch.enactor.EnactorXmlParser;
import context.arch.widget.Widget;
import context.arch.widget.WidgetXmlParser;

public class PacienteFachada implements PacienteFachadaInterface, CallbackPacienteInterface {

	// private MonitoramentoPaciente paciente;
	private final Widget widgetPaciente;
	private final Enactor pacienteEnactor;
	private final PacienteService pacienteService;
	private final Widget widgetSaida;
	
	private final Email email = new Email();
	private final AlertaBD bd = new AlertaBD();
	private MedicoInterface medico;
	
	private String nomePaciente;

	public PacienteFachada(String nomePaciente) throws MalformedURLException, RemoteException, NotBoundException {
		
		//medico = recuperarReferenciaMedico();
		//medico.registrarPaciente(this, nomePaciente);
		Discoverer.start();
               
		widgetPaciente = WidgetXmlParser.createWidget("widgets-enactor/sinaisVitais-widget.xml");

		widgetSaida = WidgetXmlParser.createWidget("widgets-enactor/output-widget.xml");

		pacienteService = new PacienteService(widgetSaida);
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
	public void notificarAlerta(Alerta alerta , Map<String , Integer> sinaisVitais) throws BDexception, RemoteException {
		bd.salvarDadosAlerta(alerta);
		
		if(alerta != null){
		
		String mensagem = " Sinais vitais do Paciente em "+ alerta.getData().toLocalDate() +"\n";
		
		for(String s : sinaisVitais.keySet()){
			mensagem += s +" = "+sinaisVitais.get(s)+"\n";
		}
		
		mensagem += "\nMedicação aplicada:" +alerta.getMedicacao() + " dosagem: "+alerta.getDosagem();
		
				
		String assunto = "";
		
		if(alerta.getSinalVital().equals(SinalVital.BATIMENTOS))
			assunto = "Batimentos cardiíacos fora limites normais";
		else if(alerta.getSinalVital().equals(SinalVital.GLICOSE))
			assunto = "Nível de Glisoce fora limites normais";
		else if(alerta.getSinalVital().equals(SinalVital.GLICOSE))
			assunto = "Pressão arterial fora limites normais";
		
		assunto+= " - Paiciente "+alerta.getPaciente();
		
		email.enviarEmail(assunto, mensagem, "paciente@topicos.com.br");
		
		
		medico.notificarAlerta(alerta.getPaciente());
		
		}
		
	}

	@Override
	public void enviarMensagem(String mensagem) throws RemoteException {
		
		
	}

}
