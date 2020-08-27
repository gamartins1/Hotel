package persistencia;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
	public static String nomeBanco, usuario, senha, servidor;
	public static int porta;
	
	static {
        nomeBanco = "hotel";
        usuario = "root";
        senha = "";
        servidor = "localhost";
        porta = 3306;
	}

    public static java.sql.Connection conexao = null;
    
    public static void abrir() throws SQLException {
        //mysql
        String url = "jdbc:mysql://" + servidor + ":" + porta + "/" + nomeBanco+"?serverTimezone=UTC";    
        conexao = DriverManager.getConnection(url, usuario, senha);
    }
    
    public static void fechar() throws SQLException {
        conexao.close();
    }
    
    public static java.sql.Connection getConexao() {
        return conexao;
    }
}
