package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionFactory {
	static String ServerName = "localhost";
	static String Porta = "3050";
	static String MyDatabase = "E:/Bancos/DBTeste.FDB";
	static String Url = "jdbc:firebirdsql:" + ServerName + "/" + Porta + ":" + MyDatabase;
	static String User = "SYSDBA";
	static String Senha = "masterkey";
	private String Status = "Não Cconectado...";
	
	public java.sql.Connection GetConnection() {
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(Url, User, Senha);
			
			if (connection != null) {
				Status = ("Status -> Conectado com Sucesso!");
			} else {
				Status = ("Status -> Não foi possível Realizar a Conexão");
			}
			return connection;
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados!" + e.getMessage());
			return null;
		}
	}
	
	public String statusConnection() {
		return this.Status;
	}
	
	public boolean FecharConexao() {
		try {
			this.GetConnection().close();
			this.Status = "Status -> Conexão Encerrada!";
			return true;			
		} catch (SQLException e) {
			return false;
		}
	}
	
	public java.sql.Connection ReiniciarConexao() {
		this.FecharConexao();
		return this.GetConnection();
	}
	
	
}
