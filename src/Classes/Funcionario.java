package Classes;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Dao.ClienteDAO;
import Dao.FuncionarioDAO;
import Dao.ProdutoDAO;

public class Funcionario {
	private int iIdFuncionario;
	private String sNomeFuncionario;
	private String sCpfFuncionario;
	private boolean bAdmin;
	private String sUsuario;
	
	public int getiIdFuncionario() {
		return iIdFuncionario;
	}
	public void setiIdFuncionario(int iIdFuncionario) {
		this.iIdFuncionario = iIdFuncionario;
	}
	public String getsNomeFuncionario() {
		return sNomeFuncionario;
	}
	public void setsNomeFuncionario(String sNomeFuncionario) {
		this.sNomeFuncionario = sNomeFuncionario;
	}
	public String getsCpfFuncionario() {
		return sCpfFuncionario;
	}
	public void setsCpfFuncionario(String sCpfFuncionario) {
		this.sCpfFuncionario = sCpfFuncionario;
	}
	public boolean getbAdmin() {
		return bAdmin;
	}
	public void setbAdmin(boolean bAdmin) {
		this.bAdmin = bAdmin;
	}
	public String getsUsuario() {
		return sUsuario;
	}
	public void setsUsuario(String sUsuario) {
		this.sUsuario = sUsuario;
	}
	
	public void CadastrarFuncionario(Funcionario func, String sUser, String sPassword, Boolean bAdm) {
		if (ValidarDadosCadastro()) {
			ConnectionFactory conexao = new ConnectionFactory();
			FuncionarioDAO funcDAO = new FuncionarioDAO(conexao);
			
			try {
				conexao.GetConnection();					
				System.out.println(conexao.statusConnection());
				
				try{
					funcDAO.CadastrarFuncionario(this, sUser, sPassword, bAdm);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}			
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conexao.FecharConexao();
			}
		}
	}
	
	private boolean ValidarDadosCadastro() {
		if (this.getsNomeFuncionario().equals("") ||
			this.getsCpfFuncionario().equals("")) {
			JOptionPane.showMessageDialog(null, "Algo deu errado! Verifique os dados preenchidos.");
			return false;
		}
		return true;
	}
	
	public String BuscarNomeFunc(int iIdFunc) {
		ConnectionFactory conexao = new ConnectionFactory();
		FuncionarioDAO funcDAO = new FuncionarioDAO(conexao);
		
		try {
			conexao.GetConnection();					
			System.out.println(conexao.statusConnection());
			
			try{
				return funcDAO.BuscarFuncionario(iIdFunc);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			conexao.FecharConexao();
		}
		return "";
	}
	public ArrayList<Funcionario> BuscarFuncionarios() {
		ConnectionFactory conexao = new ConnectionFactory();
		FuncionarioDAO FuncionarioDAO = new FuncionarioDAO(conexao);
		ArrayList<Funcionario> Funcionario = new ArrayList<Funcionario>();
		try{
			conexao.GetConnection();
			System.out.println(conexao.statusConnection());
			
			try {
				return FuncionarioDAO.BuscarFuncionarios();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<Funcionario> arrayFuncionarios = BuscarFuncionarios();
		return BuscarFuncionarios();
	}
	
}
