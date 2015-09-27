package br.ufrn.BD;

import java.util.Date;
import java.util.List;

import br.ufrn.entidades.Alerta;
import br.ufrn.excecoes.BDexception;

public interface BDalertasInterface {

	List<Alerta> listarAlertas(String nome, Date data) throws BDexception;

}