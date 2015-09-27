package br.ufrn.BD;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufrn.entidades.Alerta;
import br.ufrn.entidades.Paciente;
import br.ufrn.entidades.SinalVital;
import br.ufrn.excecoes.BDexception;

public class BdAlertas implements BDalertasInterface {

	private PreparedStatement pstm;
	private ResultSet rs;
	private final acessoBD bd = new acessoBD();

	public BdAlertas() {

	}

	/* (non-Javadoc)
	 * @see br.ufrn.BD.BDalertasInterface#listarAlertas()
	 */
	@Override
	public List<Alerta> listarAlertas(String nome, Date data) throws BDexception {

		String sql_query = "Select * from alertas a Inner Join paciente p ON a.pacienteId = p.id "
				+ "where p.nome = ? and data Between ? and ?";
		
		
		List<Alerta> alertas = new ArrayList<>();
		try {
			
			pstm = bd.conectar().prepareStatement(sql_query);
			
			pstm.setString(1, nome);
			
			pstm.setDate(2, new java.sql.Date(data.getTime()));
			
			Calendar c = Calendar.getInstance();
			
			c.setTime(data);
			c.add(Calendar.DAY_OF_MONTH, 1);
			
			pstm.setDate(3, new java.sql.Date(c.getTimeInMillis()));

			rs = pstm.executeQuery();
			
			Alerta alerta;
			
			 while (rs.next()) {
				 alerta = new Alerta();
				 
				 alerta.setId(rs.getLong("id"));
				 alerta.setData(rs.getTimestamp("data"));
				 alerta.setDosagem(rs.getInt("dosagem"));
				 alerta.setMedicacao(rs.getString("medicacao"));
				 
				 Paciente p = new Paciente();
				 p.setId(rs.getInt("pacienteId"));
				 p.setNome(rs.getString("nome"));
				 
				 alerta.setPaciente(p);
				 alerta.setSinalVitasDesconforme(rs.getString("sinalVitalDesconforme"));
				 
				 
				 Map<String, Integer> sinaisVitais = new HashMap<>();
				 
				 sinaisVitais.put(SinalVital.BATIMENTOS.toString(), rs.getInt("batimentos"));
				 sinaisVitais.put(SinalVital.GLICOSE.toString(), rs.getInt("glicose"));
				 sinaisVitais.put(SinalVital.PRESSAO.toString(), rs.getInt("pressao"));
				 
				 alerta.setSinaisVitais(sinaisVitais);
				 alertas.add(alerta);
			 }

			
			pstm.close();

		} catch (SQLException e) {
			throw new BDexception(e.getMessage());
		}

		return alertas;
	}

}
