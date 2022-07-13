package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Classes.Cliente;
import Classes.ConnectionFactory;
import Classes.Funcoes;
import Classes.UsuarioSenha;

public class UsuarioDAO {
	
	private ConnectionFactory con;
	private Funcoes func = new Funcoes();
	
	public UsuarioDAO(ConnectionFactory con) {
		this.con = con;
	}
	
	public boolean CadastrarUsuario(int iIdFuncionario, String sUser, String sPassword, Boolean bAdm) throws Exception{
		try{
			String sql = "insert into tbUsuarioSenha (idFuncionario, usuario, senha, administrador ) values (?,?,?,?)";
		
			PreparedStatement p = con.GetConnection().prepareStatement(sql);
			
			sPassword = func.CriptografarSenha(sPassword);
			
			p.setInt(1, iIdFuncionario);
	        p.setString(2, sUser);
	        p.setString(3, sPassword);
	        p.setBoolean(4, bAdm);
			
			p.executeUpdate();
			p.close();
			
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	public void ExcluirUsuario(int iId) throws Exception{
		String sql = "delete from tbUsuarioSenha where id = ?";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		p.setInt(1, iId);
		
		p.executeUpdate();
		p.close();
	}
	
	public void AtualizarSenha(String sUser, String sPassword) throws Exception{
		String sql = "update tbProduto (descricao) values (?) where id = ?";

		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		p.setString(1, sPassword);
		p.setString(2, sUser);
		
		p.executeUpdate();
		p.close();
	}
	
	public ArrayList<UsuarioSenha> UsuarioSenha() throws Exception{
		ArrayList<UsuarioSenha> usuario = new ArrayList<UsuarioSenha>();
		String sql = "select * from tbUsuario";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		ResultSet rs = p.executeQuery();
		
		while (rs.next()) {
			UsuarioSenha user = new UsuarioSenha();
			user.setsUsuario(rs.getString("usuario"));
			user.setiIdFuncionario(rs.getInt("IdFuncionario"));
			usuario.add(user);
		}
		 
		rs.close();
		p.close();
		
		return usuario;
	}
	
	public UsuarioSenha BuscarUsuarioSenha(String sUser, String sSenha) throws Exception{      
		UsuarioSenha user = new UsuarioSenha();
		
		String sql = "select first 1 * from tbUsuarioSenha us where us.usuario = ?";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		p.setString(1, sUser);
		
		ResultSet rs = p.executeQuery();
		
		if(rs.next()) {
			sSenha = func.CriptografarSenha(sSenha);
			if(rs.getString("senha").equals(sSenha)) {			
				user.setiIdFuncionario(rs.getInt("IdFuncionario"));
				user.setbAdm(rs.getBoolean("administrador"));
				
				return user;
			}
		}	
		
		rs.close();
		p.close();
		
		return user;
	}
}
