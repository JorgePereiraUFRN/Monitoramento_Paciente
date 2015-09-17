package br.ufrn.controle;

import br.ufrn.context.MonitoramentoPaciente;
import br.ufrn.context.PacienteService;
import br.ufrn.entidades.Alerta;
import br.ufrn.excecoes.BDexception;
import context.arch.discoverer.Discoverer;
import context.arch.enactor.Enactor;
import context.arch.enactor.EnactorXmlParser;
import context.arch.widget.Widget;
import context.arch.widget.WidgetXmlParser;

public class PacienteFachada implements PacienteFachadaInterface {

	// private MonitoramentoPaciente paciente;
	private final Widget widgetPaciente;
	private final Enactor pacienteEnactor;
	private final PacienteService pacienteService;
	private final Widget widgetSaida;

	public PacienteFachada() {
		Discoverer.start();
               
		widgetPaciente = WidgetXmlParser.createWidget("widgets-enactor/sinaisVitais-widget.xml");

		widgetSaida = WidgetXmlParser.createWidget("widgets-enactor/output-widget.xml");

		pacienteService = new PacienteService(widgetSaida);
		widgetSaida.addService(pacienteService);

		pacienteEnactor = EnactorXmlParser.createEnactor("widgets-enactor/sinaisVitais-enactor.xml");

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
    public void salvarDadosAlerta(Alerta alerta) throws BDexception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
