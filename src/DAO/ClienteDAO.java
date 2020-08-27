package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ValueObjects.Cliente;
import ValueObjects.Pessoa;
import persistencia.Banco;

public class ClienteDAO implements DAO<Cliente> {

    //variaveis auxiliares
    
    //permite o uso de comandos DML (select, insert, delete e update) para
    //acessar nosso SGBD
    private java.sql.PreparedStatement pst;
    
    //permite armazenar um conjunto de dados vindo do SGBD para ser
    //manipulado
    private java.sql.ResultSet rs;

	private Object cliente;
	
	@Override
	public boolean adiciona(Cliente dado) throws SQLException {
        String sql = "INSERT INTO Cliente (nome, email) "
                + "VALUES (?, ?)"; //o ? indica um parametro
        
        //abre a conexao como banco
        Banco.abrir();
        //prepara o comando pst
        pst = Banco.getConexao().prepareStatement(sql);
        //associa os dados do objeto com o comando INSERT
        pst.setString(1, dado.getNome());
        pst.setString(2, dado.getEmail());
        //executa o comando
        int res = pst.executeUpdate();
        //fecha o banco
        Banco.fechar();
        return res != 0;
	}
	
	//--------------------------------------------------------------------------------------
	@Override
	public boolean remove(Cliente dado) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE nome = ?"; //o ? indica um parametro
        
        //abre a conexao como banco
        Banco.abrir();
        //prepara o comando pst
        pst = Banco.getConexao().prepareStatement(sql);
        //associa os dados do objeto com o comando DELETE
        pst.setString(1, dado.getNome());
        //executa o comando
        int res = pst.executeUpdate();
        //fecha o banco
        Banco.fechar();
        return res != 0;

	}
	
	//--------------------------------------------------------------------------------------
	@Override
	public boolean atualiza(Cliente dado) throws SQLException {
		String sql = "UPDATE Cliente SET email = ? "
                + "WHERE nome = ?"; //o ? indica um parametro
        
        //abre a conexao como banco
        Banco.abrir();
        //prepara o comando pst
        pst = Banco.getConexao().prepareStatement(sql);
        //associa os dados do objeto com o comando UPDATE
        pst.setString(1, dado.getNome());
        pst.setString(2, dado.getEmail());
        //executa o comando
        int res = pst.executeUpdate();
        //fecha o banco
        Banco.fechar();
        return res != 0;
	}
	
	//--------------------------------------------------------------------------------------
	@Override
	public Cliente busca(Cliente dado) throws SQLException {
		String sql = "SELECT * FROM Cliente "
                + "WHERE nome = ?"; //o ? indica um parametro
      
                
        //abre a conexao como banco
        Banco.abrir();
        //prepara o comando pst
        pst = Banco.getConexao().prepareStatement(sql);
        //associa os dados do objeto com o comando SELECT
        pst.setString(1, dado.getNome());
        //executa o comando
        rs = pst.executeQuery();
        //verificar se trouxe algum registro
        //rs.next() faz a leitura do próximo registro, se existir devolve true
        //se nao devolve false
        if(rs.next()) {
            //instancia o objeto para retorno dos dados
            cliente = new Cliente();
            //jogar os dados do banco para o objeto
            ((Pessoa) cliente).setNome(rs.getString("nome"));
            ((Cliente) cliente).setEmail(rs.getString("email"));
        } 
        else {
            //não encontrou nada!!!
            cliente = null;
        }
        //fecha o banco
        Banco.fechar();
        //devolve o objeto com os dados do banco ou não
        return (Cliente) cliente;
	}
	
	public boolean buscaCliente(String criterio) throws SQLException {
		boolean sucesso = false;
		
		String sql = "SELECT tb_cliente.id_cliente, tb_cliente.id_pessoa, tb_pessoa.id_pessoa, tb_pessoa.nome_pessoa\r\n" + 
				"FROM tb_cliente INNER JOIN tb_pessoa ON tb_cliente.id_pessoa = tb_pessoa.id_pessoa";
		
		Banco.abrir();
		
		PreparedStatement statement = Banco.getConexao().prepareStatement(sql);
		
		ResultSet result = statement.executeQuery();
		
		if (result.next()) {
			sucesso = true;
		}
		else {
			sucesso = false;
		}
		
		Banco.fechar();
		return sucesso;
	}
	//--------------------------------------------------------------------------------------
	@Override
	public List listar(String criterio) throws SQLException {
		//cria a coleção para os dados
        List<Cliente> retorno = new ArrayList<>();
        
        String sql = "SELECT * FROM Cliente ";
        
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
            cliente = new Cliente();
            //jogar os dados do banco para o objeto
            ((Pessoa) cliente).setNome(rs.getString("nome"));
            ((Cliente) cliente).setEmail(rs.getString("email"));
            
            //adiciona o registro dentro da Coleção
            retorno.add((Cliente) cliente);
        } 
        //fecha o banco
        Banco.fechar();
        //devolve a lista com todos os registros
        return retorno;
	}


}
