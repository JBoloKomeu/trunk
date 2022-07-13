package Classes;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Dao.ClienteDAO;
import Dao.ProdutoDAO;

public class Cliente {

	private int    iIdCliente;
	private String sNomeCliente;
	private String sCpfCliente;
	private String sTelefoneCliente;
	private String sBairroCliente;
	private String sCidadeCliente;
	
	public int getiIdCliente() {
		return iIdCliente;
	}
	public void setiIdCliente(int iIdCliente) {
		this.iIdCliente = iIdCliente;
	}
	public String getsNomeCliente() {
		return sNomeCliente;
	}
	public void setsNomeCliente(String sNomeCliente) {
		this.sNomeCliente = sNomeCliente;
	}
	public String getsCpfCliente() {
		return sCpfCliente;
	}
	public void setsCpfCliente(String sCpfCliente) {
		this.sCpfCliente = sCpfCliente;
	}
	public String getsTelefoneCliente() {
		return sTelefoneCliente;
	}
	public void setsTelefoneCliente(String sTelefoneCliente) {
		this.sTelefoneCliente = sTelefoneCliente;
	}
	public String getsBairroCliente() {
		return sBairroCliente;
	}
	public void setsBairroCliente(String sBairroCliente) {
		this.sBairroCliente = sBairroCliente;
	}
	public String getsCidadeCliente() {
		return sCidadeCliente;
	}
	public void setsCidadeCliente(String sCidadeCliente) {
		this.sCidadeCliente = sCidadeCliente;
	}
	
	public void CadastrarCliente() {
		ConnectionFactory conexao = new ConnectionFactory();
		ClienteDAO cliDAO = new ClienteDAO(conexao);
		
		try {
			conexao.GetConnection();					
			System.out.println(conexao.statusConnection());
			
			try{
				cliDAO.CadastrarCliente(this);
				JOptionPane.showMessageDialog(null, "Cliente cadastrado sucesso!");
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Algo deu errado, que pena!!");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.FecharConexao();
		}
	}
	
	public String[] BuscarNomesClientes() {
		ConnectionFactory conexao = new ConnectionFactory();
		ClienteDAO clienteDAO = new ClienteDAO(conexao);
		try{
			conexao.GetConnection();
			System.out.println(conexao.statusConnection());
			
			try {
				return clienteDAO.BuscarNomesClientes();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] arrayClientes = {};
		return arrayClientes;
	}
	
	public ArrayList<Cliente> BuscarClientes() {
		ConnectionFactory conexao = new ConnectionFactory();
		ClienteDAO clienteDAO = new ClienteDAO(conexao);
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try{
			conexao.GetConnection();
			System.out.println(conexao.statusConnection());
			
			try {
				return clienteDAO.ListarClientes();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	public void AtualizarCliente() throws Exception{	
		ConnectionFactory con = new ConnectionFactory();
		ClienteDAO clienteDAO = new ClienteDAO(con);
		
		try {
			con.GetConnection();					
			System.out.println(con.statusConnection());
			
			try{
				clienteDAO.AtualizarCliente(this);
				JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Algo deu errado, que pena!!");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}
	}
	
	public ArrayList<Cliente> BuscarClienteCpf(String cpf) {
		ConnectionFactory conexao = new ConnectionFactory();
		ClienteDAO clienteDAO = new ClienteDAO(conexao);
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try{
			conexao.GetConnection();
			System.out.println(conexao.statusConnection());
			
			try {
				return clienteDAO.BuscarClienteCpf(cpf);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}
	

	
	

	
}
