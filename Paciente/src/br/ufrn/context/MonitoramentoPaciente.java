package br.ufrn.context;

import context.arch.widget.Widget;

public class MonitoramentoPaciente {
	
	private final Widget widgetPaciente;
	private final PacienteService pacienteService;

	

	public MonitoramentoPaciente(Widget widgetPaciente, PacienteService pacienteService) {
		super();
		this.widgetPaciente = widgetPaciente;
		this.pacienteService = pacienteService;
	}

	public void atualizarGlicose(int nivelGlicose) {
		widgetPaciente.updateData("glicose", nivelGlicose);
	}

	public void atualizarBatimentos(int batimentosCardiacos) {
		widgetPaciente.updateData("batimentosCardiacos", batimentosCardiacos);
	}

	public void atualizarPressao(int pressao) {
		widgetPaciente.updateData("pressao", pressao);
	}

}
