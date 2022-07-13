package Classes;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Dao.ClienteDAO;
import Dao.ProdutoDAO;

public class Produto {
	
	private int    iIdProduto;
	private String sDescricaoProduto;
	
	public int getiIdProduto() {
		return iIdProduto;
	}

	public void setiIdProduto(int iIdProduto) {
		this.iIdProduto = iIdProduto;
	}
	
	public String getsDescricaoProduto() {
		return sDescricaoProduto;
	}

	public void setsDescricaoProduto(String sDescricaoProduto) {
		this.sDescricaoProduto = sDescricaoProduto;
	}

	public void CadastrarProduto() {
		if (ValidarDadosCadastro()) {
			ConnectionFactory conexao = new ConnectionFactory();
			ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
			
			try {
				conexao.GetConnection();					
				System.out.println(conexao.statusConnection());
				
				try{
					produtoDAO.CadastrarProduto(this);
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
	
	public String[] BuscarDescricaoProdutos(){
		ConnectionFactory conexao = new ConnectionFactory();
		ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
		try{
			conexao.GetConnection();
			System.out.println(conexao.statusConnection());
			
			try {
				return produtoDAO.BuscarDescricaoProdutos();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] arrayProdutos = {};
		return arrayProdutos;
	}
	
	public int BuscarIdProduto() {
		ConnectionFactory conexao = new ConnectionFactory();
		ProdutoDAO prodDAO = new ProdutoDAO(conexao);
		try{
			conexao.GetConnection();
			System.out.println(conexao.statusConnection());
			
			try {
				return prodDAO.BuscarProduto(this.getsDescricaoProduto());
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private boolean ValidarDadosCadastro() {
		if (this.getsDescricaoProduto().equals("")) {
			JOptionPane.showMessageDialog(null, "Algo deu errado! Verifique os dados preenchidos.");
			return false;
		}
		return true;
	}
	
	public ArrayList<Produto> BuscarProdutos(String sIds) {
		ConnectionFactory conexao = new ConnectionFactory();
		ProdutoDAO ProdutoDAO = new ProdutoDAO(conexao);
		ArrayList<Produto> Produto = new ArrayList<Produto>();
		try{
			conexao.GetConnection();
			System.out.println(conexao.statusConnection());
			if(!sIds.equals("")) {
				sIds = " and idProduto in (" + sIds + ")";
			}			
			try {
				return ProdutoDAO.BuscarProdutos(sIds);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
