package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ValueObjects.Hospedagem;
import ValueObjects.Quarto;
import ValueObjects.Reserva;
import ValueObjects.TipoQuarto;
import persistencia.Banco;

public class QuartoDAO implements DAO<Hospedagem> {

	@Override
	public boolean adiciona(Hospedagem dado) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Hospedagem dado) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean atualiza(Hospedagem dado) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Hospedagem busca(Hospedagem dado) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hospedagem> listar(String criterio) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void alterarStatusQuarto(Quarto quarto) throws SQLException {
		String sql = "UPDATE tb_quarto SET quarto_disponivel = 0 WHERE id_quarto = ?";
		
		Banco.abrir();
		
		PreparedStatement preparedStatement = Banco.getConexao().prepareStatement(sql);
		preparedStatement.setInt(1, quarto.getNumero());
		
		int exec = preparedStatement.executeUpdate();
		Banco.fechar();
	}
	
	public Quarto listarQuartosDisponiveis (String criterio) throws SQLException {
		TipoQuarto tipoQuarto = new TipoQuarto();
		Quarto quarto = new Quarto(tipoQuarto);
		
		String sql = "SELECT tb_quarto.id_quarto, tb_quarto.valor_diaria_quarto, \r\n" + 
				"tb_quarto.id_tipo_quarto, tb_tipo_quarto.descricao_quarto, tb_quarto.quarto_disponivel \r\n" + 
				"FROM tb_quarto \r\n" + 
				"INNER JOIN tb_tipo_quarto ON tb_quarto.id_tipo_quarto = tb_tipo_quarto.id_tipo_quarto\r\n";
		
		if (!criterio.equals(null)) {
			sql += criterio;
		}
		Banco.abrir();
		
		PreparedStatement statement = Banco.getConexao().prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			quarto.setNumero(resultSet.getInt("id_quarto"));
			quarto.setValorDiaria(resultSet.getDouble("valor_diaria_quarto"));
			quarto.getTipoQuarto().setDescricao(resultSet.getString("descricao_quarto"));
			quarto.setQuartoDisponivel(resultSet.getBoolean("quarto_disponivel"));
		}
		
		Banco.fechar();
		return quarto;
	}

}
