package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Exceptions.DataInvalidaException;
import ValueObjects.Cliente;
import ValueObjects.Hospedagem;
import ValueObjects.Quarto;
import ValueObjects.TipoQuarto;
import persistencia.Banco;

public class HospedagemDAO implements DAO<Hospedagem> {

	@Override
	public boolean adiciona(Hospedagem dado) throws SQLException {
		boolean sucesso;
		String sql = "INSERT INTO tb_hospedagem (data_entrada_hospedagem, data_saida_hospedagem, "
				+ "forma_pagamento_hospedagem, id_reserva, id_cliente, id_quarto) VALUES ("
				+ "?, ?, ?, ?, ?, ?);";
		
		Banco.abrir();
		
		PreparedStatement statement = Banco.getConexao().prepareStatement(sql);
		
		statement.setObject(1, dado.getEntrada());
		statement.setObject(2, dado.getSaida());
		statement.setString(3, dado.getFormaPagamento());
		
		if(dado.getReserva().getId() > 0) {	
			statement.setInt(4, dado.getReserva().getId()); 
		} 
		else { 
			statement.setObject(4,  null); 
		}
		
		statement.setInt(5, dado.getCliente().getId());
		statement.setInt(6, dado.getQuarto().getNumero());
		
        int res = statement.executeUpdate();
        Banco.fechar();

        if (res > 0) {
        	ReservaDAO dao = new ReservaDAO();
        	QuartoDAO qDao = new QuartoDAO();
        	dao.alterarStatusReserva(dado.getReserva());
        	qDao.alterarStatusQuarto(dado.getQuarto());
        	sucesso = true;
        }
        else {
        	sucesso = false;
        }
        return sucesso;
	}

	@Override
	public boolean remove(Hospedagem dado) throws SQLException {
		boolean sucesso;
		
		String sql = "DELETE FROM tb_hospedagem WHERE id_hospedagem=" + dado.getId()+";";
		
		Banco.abrir();
		
		PreparedStatement statement = Banco.getConexao().prepareStatement(sql);
		
		int rs = statement.executeUpdate(sql);
		
		if(rs > 0) {
			sucesso = true;
		}
		else {
			sucesso = false;
		}
		return sucesso;
	}

	@Override
	public boolean atualiza(Hospedagem dado) throws SQLException {
		boolean sucesso;
		String sql = "UPDATE tb_hospedagem SET data_entrada_hospedagem = ?, "
				+ "data_saida_hospedagem = ?, forma_pagamento_hospedagem = ?, id_quarto = ? WHERE id_cliente = ?";
		
		Banco.abrir();
		
		PreparedStatement statement = Banco.getConexao().prepareStatement(sql);
		
		statement.setObject(1, dado.getEntrada());
		statement.setObject(2, dado.getSaida());
		statement.setString(3, dado.getFormaPagamento());
		statement.setInt(4, dado.getQuarto().getNumero());
		statement.setInt(5, dado.getCliente().getId());
		
		int rs = statement.executeUpdate();
		
		if (rs > 0) {
			sucesso = true;
		}
		else {
			sucesso = false;
		}
		
		Banco.fechar();
		return sucesso;
	}

	@Override
	public Hospedagem busca(Hospedagem dado) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Hospedagem> listarHospedagens(String criterio) throws SQLException, DataInvalidaException {
		List<Hospedagem> hospList = new ArrayList<>();
    
		String sql = "SELECT tb_hospedagem.id_hospedagem, tb_hospedagem.data_entrada_hospedagem, tb_hospedagem.data_saida_hospedagem, tb_quarto.id_quarto, tb_pessoa.id_pessoa, tb_cliente.id_pessoa, tb_cliente.id_cliente, tb_quarto.valor_diaria_quarto, tb_tipo_quarto.id_tipo_quarto, tb_tipo_quarto.descricao_quarto, tb_hospedagem.hospedagem_utilizada, tb_pessoa.nome_pessoa FROM (((( tb_hospedagem INNER JOIN tb_cliente ON tb_hospedagem.id_cliente = tb_cliente.id_cliente) INNER JOIN tb_pessoa ON tb_cliente.id_pessoa = tb_pessoa.id_pessoa) INNER JOIN tb_quarto ON tb_quarto.id_quarto = tb_hospedagem.id_quarto) INNER JOIN tb_tipo_quarto ON tb_tipo_quarto.id_tipo_quarto = tb_quarto.id_tipo_quarto) ";
		
		if(!criterio.isEmpty()) {
			sql += criterio;
		}
		
		Banco.abrir();
		
		PreparedStatement statement = Banco.getConexao().prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			Hospedagem hospedagem = new Hospedagem(null, null);
			Cliente cliente = new Cliente();
			
			hospedagem.setId(resultSet.getInt("id_hospedagem"));
			cliente.setNome(resultSet.getString("nome_pessoa"));
			hospedagem.setEntrada((Date) resultSet.getObject("data_entrada_hospedagem"));
			hospedagem.setSaida((Date) resultSet.getObject("data_saida_hospedagem"));
			cliente.setId(resultSet.getInt("id_cliente"));
			
			TipoQuarto tipoQuarto = new TipoQuarto();
			tipoQuarto.setDescricao(resultSet.getString("descricao_quarto"));
									
			Quarto quarto = new Quarto(tipoQuarto);
			quarto.setValorDiaria(resultSet.getDouble("valor_diaria_quarto"));
			quarto.setNumero(resultSet.getInt("id_quarto"));
			
			hospedagem.setQuarto(quarto);
			hospedagem.setCliente(cliente);
			
			hospList.add(hospedagem);
		}
		Banco.fechar();
				
		return hospList;
	}

	public Hospedagem buscaHospedagemCliente (String criterio) throws SQLException, DataInvalidaException {
		Cliente cliente = new Cliente();
		TipoQuarto tipoQuarto = new TipoQuarto();
		Quarto quarto = new Quarto(tipoQuarto);
		
		Hospedagem hospedagem = new Hospedagem(cliente, quarto);

		String sql = "SELECT tb_hospedagem.id_hospedagem, tb_pessoa.id_pessoa, tb_pessoa.nome_pessoa, \r\n" + 
				"	tb_hospedagem.data_entrada_hospedagem, tb_quarto.quarto_disponivel, tb_hospedagem.id_cliente, " +
				"   tb_hospedagem.data_saida_hospedagem, tb_cliente.id_cliente, tb_hospedagem.hospedagem_utilizada, \r\n" + 
				"    tb_tipo_quarto.descricao_quarto, tb_tipo_quarto.quantidade_camas_quarto, tb_quarto.id_quarto, \r\n" + 
				"    tb_hospedagem.forma_pagamento_hospedagem, tb_quarto.valor_diaria_quarto FROM (((( \r\n" + 
				"    tb_hospedagem \r\n" + 
				"    INNER JOIN tb_cliente ON tb_hospedagem.id_cliente = tb_cliente.id_cliente) \r\n" + 
				"    INNER JOIN tb_pessoa ON tb_cliente.id_pessoa = tb_pessoa.id_pessoa) \r\n" + 
				"    INNER JOIN tb_quarto ON tb_quarto.id_quarto = tb_hospedagem.id_quarto) \r\n" + 
				"    INNER JOIN tb_tipo_quarto ON tb_tipo_quarto.id_tipo_quarto = tb_quarto.id_tipo_quarto) ";
		
		if(!criterio.isEmpty()) {
			sql += criterio;
		}
		
		Banco.abrir();
		
		PreparedStatement statement = Banco.getConexao().prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			
			cliente.setNome(resultSet.getString("nome_pessoa"));
			cliente.setId(resultSet.getInt("id_cliente"));
			
			tipoQuarto.setDescricao(resultSet.getString("descricao_quarto"));
			tipoQuarto.setQtdCamas(resultSet.getShort("quantidade_camas_quarto"));
			
			hospedagem.setFormaPagamento(resultSet.getString("forma_pagamento_hospedagem"));
			
			quarto.setValorDiaria(resultSet.getDouble("valor_diaria_quarto"));
			quarto.setNumero(resultSet.getInt("id_quarto"));
						
			hospedagem.setEntrada(resultSet.getDate("data_entrada_hospedagem"));
			hospedagem.setSaida(resultSet.getDate("data_saida_hospedagem"));
			hospedagem.setId(resultSet.getInt("id_hospedagem"));
			hospedagem.setHospedagem_utilizada(resultSet.getBoolean("hospedagem_utilizada"));
			hospedagem.setQuarto(quarto);
			hospedagem.setCliente(cliente);
		}
		Banco.fechar();
		return hospedagem;
	}
	public Map<Integer, TipoQuarto> listarTiposQuartos() throws SQLException {
		
		Map<Integer, TipoQuarto> tiposQuartos = new HashMap<Integer, TipoQuarto>();
		
		String sql = "SELECT * FROM tb_tipo_quarto;";
		
		Banco.abrir();
		
		PreparedStatement statement = Banco.getConexao().prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			TipoQuarto quarto = new TipoQuarto();
			
			quarto.setDescricao(resultSet.getString("descricao_quarto"));
			quarto.setQtdCamas(resultSet.getInt("quantidade_camas_quarto"));
			
			tiposQuartos.put(resultSet.getInt("id_tipo_quarto"), quarto);
		}
		
		Banco.fechar();
		return tiposQuartos;
	}

	@Override
	public List<Hospedagem> listar(String criterio) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	//--------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------
	/*
	public List listar(String criterio) throws SQLException {
		//cria a coleção para os dados
        List<Hospedagem> retorno = new ArrayList<>();
        
        String sql = "SELECT * FROM Hospedagem ";
        
        //verifica se tem filtro a fazer
        if(criterio.length() > 0) {
            sql += "WHERE " + criterio;
        }
    
        //abre a conexao como banco
        Banco.abrir();
        //prepara o comando pst
        pst = Banco.getConexao().prepareStatement(sql);
        //executa o comando
        rs = pst.executeQuery();
        //verificar se trouxe algum registro
        //rs.next() faz a leitura do próximo registro, se existir devolve true
        //se nao devolve false
        //enquanto existir registros na tabela, vai lendo até o fim
        while(rs.next()) {
            //instancia o objeto para retorno dos dados
            Hospedagem = new Hospedagem();
            //jogar os dados do banco para o objeto
            ((Pessoa) Hospedagem).setNome(rs.getString("nome"));
            ((Hospedagem) Hospedagem).setEmail(rs.getString("email"));
            
            //adiciona o registro dentro da Coleção
            retorno.add((Hospedagem) Hospedagem);
        } 
        //fecha o banco
        Banco.fechar();
        //devolve a lista com todos os registros
        return retorno;
	}
*/

}