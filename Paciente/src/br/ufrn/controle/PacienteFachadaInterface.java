package br.ufrn.controle;

import br.ufrn.entidades.Alerta;
import br.ufrn.excecoes.BDexception;

public interface PacienteFachadaInterface {
	
	public void atualizarGlicose(int nivelGlicose);

	public void atualizarBatimentos(int batimentosCardiacos);

	public void atualizarPressao(int pressao);
        
        public void salvarDadosAlerta(Alerta alerta) throws BDexception;
	

}
