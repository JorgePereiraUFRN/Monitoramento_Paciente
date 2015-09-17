package br.ufrn.context;

import javax.swing.JOptionPane;

import context.arch.comm.DataObject;
import context.arch.service.Service;
import context.arch.service.helper.FunctionDescription;
import context.arch.service.helper.FunctionDescriptions;
import context.arch.service.helper.ServiceInput;
import context.arch.widget.Widget;

public class PacienteService extends Service {

	
	public PacienteService(final Widget widget) {
            
		super(widget, "sinaisVitaisService", new FunctionDescriptions() {
			{
				add(new FunctionDescription(
						"tratarPaciente", 
						"aplica medicação e alerta ao medico e familiar", 
						widget.getNonConstantAttributes()));
			}
		});

	}

	@Override
	public DataObject execute(ServiceInput input) {
		
		String mensagem = input.getInput().getAttributeValue("mensagem");
		String medicacao = input.getInput().getAttributeValue("medicacao");
		int dosagem = input.getInput().getAttributeValue("dosagem");
		
		
		JOptionPane.showMessageDialog(null, mensagem+"\nmedicacao: "+medicacao+" dosagem: "+dosagem);
		
		return null;
	}

}
