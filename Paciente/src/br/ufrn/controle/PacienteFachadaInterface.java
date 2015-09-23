package br.ufrn.controle;

import java.rmi.RemoteException;
import java.util.Map;

import br.ufrn.entidades.Alerta;
import br.ufrn.excecoes.BDexception;

public interface PacienteFachadaInterface {

	public void atualizarGlicose(int nivelGlicose);

	public void atualizarBatimentos(int batimentosCardiacos);

	public void atualizarPressao(int pressao);

	public void notificarAlerta(Alerta alerta,  Map<String , Integer> sinaisVitais) throws BDexception, RemoteException;

}
