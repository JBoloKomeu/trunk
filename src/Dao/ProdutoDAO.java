package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import Classes.Produto;
import Classes.Cliente;
import Classes.ConnectionFactory;
import Classes.OrdemServico;

public class ProdutoDAO {
	
	private ConnectionFactory con;
	
	public ProdutoDAO(ConnectionFactory con) {
		this.con = con;
	}
	
	public void CadastrarProduto(Produto produto) throws Exception{
		String sql = "insert into tbProduto (descricao) values (?)";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		p.setString(1, produto.getsDescricaoProduto().toUpperCase());
		
		p.executeUpdate();
		p.close();
	}
	
	public void ExcluirProduto(Produto produto) throws Exception{
		String sql = "delete from tbProduto where id = ?";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		p.setInt(1, produto.getiIdProduto());
		
		p.executeUpdate();
		p.close();
	}
	
	public void AtualizarProduto(Produto produto) throws Exception{
		String sql = "update tbProduto (descricao) values (?) where id = ?";

		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		p.setString(1, produto.getsDescricaoProduto().toUpperCase());
		p.setInt(2, produto.getiIdProduto());
		
		p.executeUpdate();
		p.close();
	}
	
	public ArrayList<Produto> BuscarProdutos(String sIds) throws Exception{
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		String sql = "select * from tbProduto where 1=1" + sIds;
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		ResultSet rs = p.executeQuery();
		
		while (rs.next()) {
			Produto produto = new Produto();
			produto.setiIdProduto(rs.getInt("idProduto"));
			produto.setsDescricaoProduto(rs.getString("descricao").toUpperCase());
			produtos.add(produto);
		}
		 
		rs.close();
		p.close();
		
		return produtos;
	}
	
	public String[] BuscarDescricaoProdutos() throws SQLException {
		int controle = 0;
		String sql = "select count(descricao) as ct from tbProduto";		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		ResultSet rs = p.executeQuery();
		rs.next();
		int qtdeProdutos = rs.getInt("ct");
		String arrayProdutos[] = new String[qtdeProdutos];
		if(qtdeProdutos > 0) {
			sql = "select descricao from tbProduto";
			p = con.GetConnection().prepareStatement(sql);
			rs = p.executeQuery();
			while(rs.next()) {
				arrayProdutos[controle] = rs.getString("descricao").toUpperCase();
				controle += 1;
			}
		}
		return arrayProdutos;
	}
	
	public int BuscarProduto(String sDescricao) throws Exception{                //implementar a busca por id também
		String sql = "select first 1 * from tbProduto p where p.descricao = ?";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		p.setString(1, sDescricao.toUpperCase());
		
		ResultSet rs = p.executeQuery();
		
		int idProduto = rs.getInt("idProduto");
		
		rs.close();
		p.close();
		
		return idProduto;
	}
}
