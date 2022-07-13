package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Classes.Cliente;
import Classes.ConnectionFactory;
import Classes.Servico;

public class ServicoDAO {
	private ConnectionFactory con;
	
	public ServicoDAO(ConnectionFactory con) {
		this.con = con;
	}

	public void CadastrarServico(Servico servico) throws SQLException {
		String sql = "insert into tbServico (descricao, valor, quantidadekg) values (?,?,?)";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		p.setString(1, servico.getsDescricaoServico().toUpperCase());
		p.setFloat(2, servico.getfValorServico());
		p.setFloat(3, servico.getfQtdeKgCalculo());
		
		p.executeUpdate();
		p.close();
		
        sql = "select first 1 idServico from tbServico order by idServico desc";
		
		p = con.GetConnection().prepareStatement(sql);
		
		ResultSet rs = p.executeQuery();
		
		rs.next();
		
		servico.setiIdServico(rs.getInt("idServico"));
		
		rs.close();
		p.close();
		
		String[] aProdutos = servico.getsProdutos().split(",",0);
		int qtdeProdutos = aProdutos.length;
		
		for(String i : aProdutos) {
			sql = "insert into tbproduto_servico (idProduto, idServico) values (?,?)";
			
			p = con.GetConnection().prepareStatement(sql);
			
			p.setInt(1, Integer.parseInt(i));
			p.setInt(2, servico.getiIdServico());
			
			p.executeUpdate();
			p.close();
		}
	}

	public String[] buscarServicoPorProduto(int iIdProduto) throws SQLException {
		int controle = 0;
		
		String sql = "select count(ser.descricao) as descr from tbServico ser inner join tbProduto_servico prod_serv on prod_serv.idServico = ser.idServico where prod_serv.idProduto = ?";		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		p.setInt(1, iIdProduto);
		
		ResultSet rs = p.executeQuery();
		rs.next();
		
		int qtdeTickets = rs.getInt("descr");
		String arrayServicos[] = new String[qtdeTickets];
		
		if(qtdeTickets > 0) {
			sql = "select ser.descricao from tbServico ser inner join tbProduto_servico prod_serv on prod_serv.idServico = ser.idServico where prod_serv.idProduto = ?";
			p = con.GetConnection().prepareStatement(sql);
			p.setInt(1, iIdProduto);
			rs = p.executeQuery();
			while(rs.next()) {
				arrayServicos[controle] = rs.getString("descricao").toUpperCase();
				controle += 1;
			}
		}
		return arrayServicos;
	}
}
