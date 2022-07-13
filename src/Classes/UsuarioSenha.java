package Classes;

import javax.swing.JOptionPane;

import Dao.FuncionarioDAO;
import Dao.UsuarioDAO;

public class UsuarioSenha {
	private String sUsuario;
	private String sSenha;
	private int iIdFuncionario;
	private boolean bAdm;
	
	public String getsUsuario() {
		return sUsuario;
	}
	public void setsUsuario(String sUsuario) {
		this.sUsuario = sUsuario;
	}
	public String getsSenha() {
		return sSenha;
	}
	public void setsSenha(String sSenha) {
		this.sSenha = sSenha;
	}	
	public int getiIdFuncionario() {
		return iIdFuncionario;
	}
	public void setiIdFuncionario(int iIdFuncionario) {
		this.iIdFuncionario = iIdFuncionario;
	}
	public boolean getbAdm() {
		return bAdm;
	}
	public void setbAdm(boolean bAdm) {
		this.bAdm = bAdm;
	}
	public boolean CadastrarUsuario(int iIdFuncionario, String sUser, String sPassword, Boolean bAdm) {
		ConnectionFactory conexao = new ConnectionFactory();
		UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
		
		try {
			conexao.GetConnection();					
			System.out.println(conexao.statusConnection());
			
			try{
				this.setiIdFuncionario(iIdFuncionario);
				
				return usuarioDAO.CadastrarUsuario(iIdFuncionario, sUser, sPassword, bAdm);				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			conexao.FecharConexao();
		}	
		return false;
	}
	
	public UsuarioSenha RealizarLogin(String sUser, String sPassword) {
		ConnectionFactory conexao = new ConnectionFactory();
		UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
		
		try {
			conexao.GetConnection();					
			System.out.println(conexao.statusConnection());
			
			try{
				this.setiIdFuncionario(iIdFuncionario);
				
				return usuarioDAO.BuscarUsuarioSenha(sUser, sPassword);				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			conexao.FecharConexao();
		}	
		return null;
	}
	
}
