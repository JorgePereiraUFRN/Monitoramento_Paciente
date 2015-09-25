package br.ufrn.BD;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.ufrn.entidades.Alerta;
import br.ufrn.entidades.SinalVital;

public class AlertaBD {

	private PreparedStatement pstm;
	private final acessoBD bd = new acessoBD();

	public AlertaBD() {

	}

	public void salvarDadosAlerta(Alerta alerta) {

		String sql_query = "Insert Into alertas (sinalVitalDesconforme, data, pacienteId, medicacao, dosagem, batimentos, pressao, glicose) values (?,?,?,?,?,?,?,?)";

		try {
			pstm = bd.conectar().prepareStatement(sql_query);

			pstm.setString(1, alerta.getSinalVitalDesconforme());
			pstm.setTimestamp(2, alerta.getData());
			pstm.setInt(3, alerta.getPaciente().getId());
			pstm.setString(4, alerta.getMedicacao());
			pstm.setInt(5, alerta.getDosagem());
			pstm.setInt(6, alerta.getSinaisVitais().get(SinalVital.BATIMENTOS.toString()));
			pstm.setInt(7, alerta.getSinaisVitais().get(SinalVital.PRESSAO.toString()));
			pstm.setInt(8, alerta.getSinaisVitais().get(SinalVital.GLICOSE.toString()));

			pstm.execute();
			pstm.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
