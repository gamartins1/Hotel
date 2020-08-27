package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exceptions.DataInvalidaException;
import ValueObjects.Cliente;
import ValueObjects.Hospedagem;
import ValueObjects.Quarto;
import ValueObjects.Reserva;
import ValueObjects.TipoQuarto;
import persistencia.Banco;

public class ReservaDAO implements DAO<Reserva> {

	@Override
	public boolean adiciona(Reserva dado) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Reserva dado) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean atualiza(Reserva dado) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Reserva busca(Reserva dado) throws SQLException {
		return null;
	}

	@Override
	public List<Reserva> listar(String criterio) throws SQLException {
		List<Reserva> reservas = new ArrayList<Reserva>();
		
		Reserva reserva = new Reserva(null, null, null);
		
		String sql = "SELECT tb_reserva.id_reserva, tb_pessoa.id_pessoa, tb_pessoa.nome_pessoa, \r\n" + 
				"	tb_reserva.data_entrada_reserva, tb_quarto.quarto_disponivel, " +
				"   tb_reserva.data_saida_reserva, tb_cliente.id_cliente, \r\n" + 
				"    tb_tipo_quarto.descricao_quarto, tb_tipo_quarto.quantidade_camas_quarto, tb_quarto.id_quarto, \r\n" + 
				"    tb_reserva.forma_pagamento_reserva, tb_quarto.valor_diaria_quarto, tb_reserva.reserva_utilizada FROM (((( \r\n" + 
				"    tb_reserva \r\n" + 
				"    INNER JOIN tb_cliente ON tb_reserva.id_cliente_reserva = tb_cliente.id_cliente) \r\n" + 
				"    INNER JOIN tb_pessoa ON tb_cliente.id_pessoa = tb_pessoa.id_pessoa) \r\n" + 
				"    INNER JOIN tb_quarto ON tb_quarto.id_quarto = tb_reserva.id_quarto_reserva) \r\n" + 
				"    INNER JOIN tb_tipo_quarto ON tb_tipo_quarto.id_tipo_quarto = tb_quarto.id_tipo_quarto) ";
		
		if(!criterio.isEmpty()) {
			sql += criterio;
		}
		
		Banco.abrir();
		
		PreparedStatement statement = Banco.getConexao().prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			Cliente cliente = new Cliente();
			
			reserva.setId(resultSet.getInt("id_reserva"));
			cliente.setNome(resultSet.getString("nome_pessoa"));
			reserva.setEntrada(resultSet.getDate("data_entrada_reserva"));
			reserva.setSaida(resultSet.getDate("data_saida_reserva"));
			cliente.setId(resultSet.getInt("id_cliente"));
			
			TipoQuarto tipoQuarto = new TipoQuarto();
			tipoQuarto.setDescricao(resultSet.getString("descricao_quarto"));
			tipoQuarto.setQtdCamas(resultSet.getShort("quantidade_camas_quarto"));
			
			reserva.setFormaPagamento(resultSet.getString("forma_pagamento_reserva"));
						
			Quarto quarto = new Quarto(tipoQuarto);
			quarto.setValorDiaria(resultSet.getDouble("valor_diaria_quarto"));
			quarto.setNumero(resultSet.getInt("id_quarto"));
			
			reserva.setUtilizada(resultSet.getBoolean("reserva_utilizada"));
			reserva.setQuarto(quarto);
			reserva.setCliente(cliente);
			
			reservas.add(reserva);
		}
		Banco.fechar();
		return reservas;
	}
	
	public void alterarStatusReserva(Reserva reserva) throws SQLException {
		String sql = "UPDATE tb_reserva SET reserva_utilizada = 1 WHERE id_reserva = ?";
		
		Banco.abrir();
		
		PreparedStatement preparedStatement = Banco.getConexao().prepareStatement(sql);
		preparedStatement.setInt(1, reserva.getId());
		
		int exec = preparedStatement.executeUpdate();
		Banco.fechar();
	}
	
	public List<Reserva> buscaReservas (String criterio) throws SQLException, DataInvalidaException {
		
		List<Reserva> lstReservas = new ArrayList<Reserva>();
		
		String sql = "SELECT tb_reserva.id_reserva, tb_pessoa.id_pessoa, tb_pessoa.nome_pessoa, \r\n" + 
				"	 tb_reserva.data_entrada_reserva, tb_quarto.quarto_disponivel, " +
				"    tb_reserva.data_saida_reserva, tb_cliente.id_cliente, tb_reserva.id_cliente_reserva, \r\n" + 
				"    tb_tipo_quarto.descricao_quarto, tb_tipo_quarto.quantidade_camas_quarto, tb_quarto.id_quarto, \r\n" + 
				"    tb_reserva.forma_pagamento_reserva, tb_quarto.valor_diaria_quarto, tb_reserva.reserva_utilizada FROM (((( \r\n" + 
				"    tb_reserva \r\n" + 
				"    INNER JOIN tb_cliente ON tb_reserva.id_cliente_reserva = tb_cliente.id_cliente) \r\n" + 
				"    INNER JOIN tb_pessoa ON tb_cliente.id_pessoa = tb_pessoa.id_pessoa) \r\n" + 
				"    INNER JOIN tb_quarto ON tb_quarto.id_quarto = tb_reserva.id_quarto_reserva) \r\n" + 
				"    INNER JOIN tb_tipo_quarto ON tb_tipo_quarto.id_tipo_quarto = tb_quarto.id_tipo_quarto) ";
		
		if(!criterio.isEmpty()) {
			sql += criterio;
		}
		
		Banco.abrir();
		
		PreparedStatement statement = Banco.getConexao().prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			Reserva reserva = new Reserva(null, null, null);

			Cliente cliente = new Cliente();
			
			reserva.setId(resultSet.getInt("id_reserva"));
			cliente.setNome(resultSet.getString("nome_pessoa"));
			reserva.setEntrada(resultSet.getDate("data_entrada_reserva"));
			reserva.setSaida(resultSet.getDate("data_saida_reserva"));
			cliente.setId(resultSet.getInt("id_cliente"));
			
			TipoQuarto tipoQuarto = new TipoQuarto();
			tipoQuarto.setDescricao(resultSet.getString("descricao_quarto"));
			tipoQuarto.setQtdCamas(resultSet.getShort("quantidade_camas_quarto"));
			
			reserva.setFormaPagamento(resultSet.getString("forma_pagamento_reserva"));
						
			Quarto quarto = new Quarto(tipoQuarto);
			quarto.setValorDiaria(resultSet.getDouble("valor_diaria_quarto"));
			quarto.setNumero(resultSet.getInt("id_quarto"));
			
			reserva.setUtilizada(resultSet.getBoolean("reserva_utilizada"));
			reserva.setQuarto(quarto);
			reserva.setCliente(cliente);
			
			lstReservas.add(reserva);
		}
		Banco.fechar();
		return lstReservas;
	}
	
	public Reserva listarReservaCliente(String criterio) throws SQLException{

		Reserva reserva = new Reserva(null, null, null);
		
		String sql = "SELECT tb_reserva.id_reserva, tb_pessoa.id_pessoa, tb_pessoa.nome_pessoa, \r\n" + 
				"	tb_reserva.data_entrada_reserva, tb_quarto.quarto_disponivel, " +
				"   tb_reserva.data_saida_reserva, tb_cliente.id_cliente, \r\n" + 
				"    tb_tipo_quarto.descricao_quarto, tb_tipo_quarto.quantidade_camas_quarto, tb_quarto.id_quarto, \r\n" + 
				"    tb_reserva.forma_pagamento_reserva, tb_quarto.valor_diaria_quarto, tb_reserva.reserva_utilizada FROM (((( \r\n" + 
				"    tb_reserva \r\n" + 
				"    INNER JOIN tb_cliente ON tb_reserva.id_cliente_reserva = tb_cliente.id_cliente) \r\n" + 
				"    INNER JOIN tb_pessoa ON tb_cliente.id_pessoa = tb_pessoa.id_pessoa) \r\n" + 
				"    INNER JOIN tb_quarto ON tb_quarto.id_quarto = tb_reserva.id_quarto_reserva) \r\n" + 
				"    INNER JOIN tb_tipo_quarto ON tb_tipo_quarto.id_tipo_quarto = tb_quarto.id_tipo_quarto) ";
		
		if(!criterio.isEmpty()) {
			sql += criterio;
		}
		
		Banco.abrir();
		
		PreparedStatement statement = Banco.getConexao().prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			
			Cliente cliente = new Cliente();
			
			reserva.setId(resultSet.getInt("id_reserva"));
			cliente.setNome(resultSet.getString("nome_pessoa"));
			reserva.setEntrada(resultSet.getDate("data_entrada_reserva"));
			reserva.setSaida(resultSet.getDate("data_saida_reserva"));
			cliente.setId(resultSet.getInt("id_cliente"));
			
			TipoQuarto tipoQuarto = new TipoQuarto();
			tipoQuarto.setDescricao(resultSet.getString("descricao_quarto"));
			tipoQuarto.setQtdCamas(resultSet.getShort("quantidade_camas_quarto"));
			
			reserva.setFormaPagamento(resultSet.getString("forma_pagamento_reserva"));
						
			Quarto quarto = new Quarto(tipoQuarto);
			quarto.setValorDiaria(resultSet.getDouble("valor_diaria_quarto"));
			quarto.setNumero(resultSet.getInt("id_quarto"));
			
			reserva.setUtilizada(resultSet.getBoolean("reserva_utilizada"));
			reserva.setQuarto(quarto);
			reserva.setCliente(cliente);
		}
		Banco.fechar();
		return reserva;
	}

}
