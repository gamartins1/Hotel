package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistencia.Banco;

public class LoginDAO {
	public boolean logar(String user, String senha) throws SQLException {
		boolean loginEncontrado;
		String sql = "SELECT id_recepcionista, senha_recepcionista FROM tb_recepcionista WHERE id_recepcionista = ? AND senha_recepcionista = ?;";
		
		Banco.abrir();
		
		PreparedStatement statement = Banco.getConexao().prepareStatement(sql);
		
		statement.setString(1, user);
		statement.setString(2,senha);
		
		ResultSet rs = statement.executeQuery();
		
		if(rs.next()) {
			loginEncontrado = true;
		}
		else {
			loginEncontrado = false;
		}
		
		Banco.fechar();
		return loginEncontrado;
	}
}