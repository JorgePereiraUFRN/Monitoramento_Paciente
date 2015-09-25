package br.ufrn.context;

import java.rmi.RemoteException;
import java.sql.Date;

import javax.swing.JOptionPane;

import br.ufrn.controle.AlertaInterface;
import br.ufrn.entidades.Alerta;
import br.ufrn.excecoes.BDexception;
import context.arch.comm.DataObject;
import context.arch.service.Service;
import context.arch.service.helper.FunctionDescription;
import context.arch.service.helper.FunctionDescriptions;
import context.arch.service.helper.ServiceInput;
import context.arch.widget.Widget;

public class PacienteService extends Service {

	private AlertaInterface alertaInterface;

	public PacienteService(final Widget widget, final AlertaInterface alertaInterface) {

		super(widget, "sinaisVitaisService", new FunctionDescriptions() {
			{
				add(new FunctionDescription("tratarPaciente", "aplica medicacao e alerta ao medico e familiar",
						widget.getNonConstantAttributes()));
			}
		});

		this.alertaInterface = alertaInterface;
	}

	@Override
	public DataObject execute(ServiceInput input) {

		try {

			String mensagem = input.getInput().getAttributeValue("mensagem");
			String medicacao = input.getInput().getAttributeValue("medicacao");
			int dosagem = input.getInput().getAttributeValue("dosagem");

			alertaInterface.notificarAlerta(mensagem, medicacao, dosagem);
		} catch (RemoteException | BDexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
