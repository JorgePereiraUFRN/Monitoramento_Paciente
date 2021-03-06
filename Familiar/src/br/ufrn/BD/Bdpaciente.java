package br.ufrn.BD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.PortableServer.ThreadPolicyOperations;

import br.ufrn.entidades.Paciente;
import br.ufrn.excecoes.BDexception;

public class Bdpaciente implements BDpacienteInterface {

	private PreparedStatement pstm;
	private ResultSet rs;
	private final acessoBD bd = new acessoBD();

	public Bdpaciente() {

	}

	/* (non-Javadoc)
	 * @see br.ufrn.BD.BDalertasInterface#listarAlertas()
	 */


	@Override
	public List<Paciente> listarPacientes() throws BDexception {
	
		String sql_query = "Select * from paciente";
		List<Paciente> pacientes = new ArrayList<>();
		
		try {
			pstm = bd.conectar().prepareStatement(sql_query);
			
			
			rs = pstm.executeQuery();
			
			Paciente p = null;
			
			 while (rs.next()) {
				 p = new Paciente();
				 
				 p.setId(rs.getInt("id"));
				 p.setNome(rs.getString("nome"));
				 
				 pacientes.add(p);
			 }
			
		} catch (SQLException e) {
			throw new BDexception(e.getMessage());
		}
		
		
		
		return pacientes;
	}

	@Override
	public void casstrarResponsavel(int pacienteId, String emailREsponsavel) throws BDexception {
		
		String sql_query = "update paciente set emailResponsavel = ? where id = ?";
		
		try {
			pstm = bd.conectar().prepareStatement(sql_query);
			pstm.setString(1, emailREsponsavel);
			pstm.setInt(2, pacienteId);
			
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			throw new BDexception(e.getMessage());
		}
		
		
	}

}
