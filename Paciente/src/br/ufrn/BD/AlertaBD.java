package br.ufrn.BD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufrn.entidades.Alerta;

public class AlertaBD {
	
	private PreparedStatement pstm;
    private ResultSet rs;
    private final acessoBD bd = new acessoBD(); 
    		
	public AlertaBD() {
		
	}
	
	
	public void salvarDadosAlerta(Alerta alerta) {
		
		String sql_query = "Insert Into alerta (sinalVital, valor, data, paciente_nome, medicacao, dosagem) values (?,?,?,?,?,?)";
		
		try {
			pstm = bd.conectar().prepareStatement(sql_query);
			
			
			pstm.setString(1, alerta.getSinalVital().toString());
                        pstm.setInt(2, alerta.getValor());
                        pstm.setDate(3, alerta.getData());
                        pstm.setString(4, alerta.getPaciente());
                        pstm.setString(5, alerta.getMedicacao());
                        pstm.setInt(6, alerta.getDosagem());
			
			pstm.execute();
			pstm.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
