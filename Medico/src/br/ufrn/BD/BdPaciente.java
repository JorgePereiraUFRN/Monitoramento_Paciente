package br.ufrn.BD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.entidades.Alerta;

public class BdPaciente {

	private PreparedStatement pstm;
	private ResultSet rs;
	private final acessoBD bd = new acessoBD();

	public BdPaciente() {

	}

	public List<Alerta> listarAlertas() {

		String sql_query = "query selecinar";
		List<Alerta> alertas = new ArrayList<>();
		try {
			pstm = bd.conectar().prepareStatement(sql_query);

			rs = pstm.executeQuery();
			Alerta alerta;
			
			 while (rs.next()) {
				 alerta = new Alerta();
				 
				 alerta.setId(rs.getLong("id"));
				 //TODO recuperar os dados
			
				 alertas.add(alerta);
			 }

			
			pstm.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return alertas;
	}

}
