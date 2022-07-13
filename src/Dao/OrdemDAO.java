package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

import javax.swing.JOptionPane;

import Classes.Cliente;
import Classes.ConnectionFactory;
import Classes.Funcionario;
import Classes.Funcoes;
import Classes.OrdemServico;

public class OrdemDAO {

	private ConnectionFactory con;
	
	public OrdemDAO(ConnectionFactory con) {
		this.con = con;
	}
	
	public void CadastrarOrdem(OrdemServico ordem, String sIdsTickets) throws Exception{
		Date date = new Date();
		java.sql.Date dt = new java.sql.Date(date.getTime());
		String sql = "insert into tbOrdemServico (idServico, finalizado, pesoEntradaTotal, pesoSaidaTotal, porcentagemQuebra, observacao, datainicio) values (?,?,?,?,?,?,?)";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		p.setInt(1, ordem.getiIdServico());
		p.setBoolean(2, ordem.isbFinalizado());
		p.setInt(3, ordem.getiPesoEntradaTotal());
		p.setInt(4, ordem.getiPesoSaidaTotal());
		p.setFloat(5, ordem.getfPorcQuebra());
		p.setString(6, ordem.getsObservacao());
		p.setDate(7, dt);
		
		p.executeUpdate();
		p.close();
		
		sql = "select first 1 idOrdemServico from tbOrdemServico order by idOrdemServico desc";
		
		p = con.GetConnection().prepareStatement(sql);
		
		ResultSet rs = p.executeQuery();
		
		rs.next();
		
		ordem.setiIdOrdemServico(rs.getInt("idOrdemServico"));
		
		rs.close();
		p.close();
		
		String[] aTickets = sIdsTickets.split("," ,0);
		int qtdeTickets = aTickets.length;
		
		for(String i : aTickets) {
			sql = "insert into tbTicketBalanca_OrdemServico (idTicket, idOrdem) values (?,?)";
			
			p = con.GetConnection().prepareStatement(sql);
			
			p.setInt(1, Integer.parseInt(i));
			p.setInt(2, ordem.getiIdOrdemServico());
			
			p.executeUpdate();
			p.close();
		}
	}
	
	public void FinalizarOrdem(OrdemServico ordem) throws Exception{
		Date date = new Date();
		java.sql.Date dt = new java.sql.Date(date.getTime());
		String sql = "update tbOrdemServico set (finalizado, observacao, dataTermino) values (?,?,?)";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		p.setBoolean(1, true);
		p.setString(2, ordem.getsObservacao());
		p.setDate(3, dt);
		
		p.executeUpdate();
		p.close();
	}
	
	public void ExcluirOrdem(OrdemServico ordem) throws Exception{
		String sql = "delete from tbOrdemServico where idOrdemServico = ?";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		p.setInt(1, ordem.getiIdOrdemServico());
		
		p.executeUpdate();
		p.close();
	}

	public OrdemServico BuscarDadosTickets(OrdemServico ordemServico, String sIdsTickets) throws SQLException {
		OrdemServico ordem = new OrdemServico();
		ordem.setiPesoEntradaTotal(0);
		ordem.setiPesoSaidaTotal(0);
		int iIdCliente = 0;
		int iIdProduto = 0;
		String sql = "select * from tbTicketBalanca tb where tb.idTicketBalanca in (" + sIdsTickets + ")";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		ResultSet rs = p.executeQuery();
		
		while(rs.next()) {
			if(iIdCliente == 0) {
				iIdCliente = rs.getInt("IdCliente");
			}else {
				if(iIdCliente != rs.getInt("idCliente")) {
					JOptionPane.showMessageDialog(null, "Os Tickets selecionados possuem Clientes diferentes!");
					return null;
				}
			}
			if(iIdProduto == 0) {
				iIdProduto = rs.getInt("idProduto");
			}else {
				if(iIdProduto != rs.getInt("idProduto")) {
					JOptionPane.showMessageDialog(null, "Os Tickets selecionados possuem Produtos diferentes!");
					return null;
				}
			}
			if(rs.getString("operacao").equals("ENTRADA")) {
				ordem.setiPesoEntradaTotal(ordem.getiPesoEntradaTotal() + rs.getInt("pesoLiquido"));
			}else {
				ordem.setiPesoSaidaTotal(ordem.getiPesoSaidaTotal() + rs.getInt("pesoLiquido"));
			}		
		}
		
		ordem.setiIdCliente(iIdCliente);
		ordem.setiIdProduto(iIdProduto);
		ordem.setbFinalizado(false);
		
		rs.close();
		p.close();
		
		return ordem;
	}
	
	public ArrayList<OrdemServico> ListarOrdens() throws Exception{
		ArrayList<OrdemServico> ordens = new ArrayList<OrdemServico>();
		
		String sql = "select ord.idordemservico, ord.pesoentradatotal , ord.pesosaidatotal , ord.porcentagemquebra ,  ord.observacao"
			   	   + "     , (case ord.finalizado"
				   + "        when 1 then 'sim'"
				   + "        when 0 then 'não' end) as finalizado"
				   + "     , (select first 1 cli.nome"
				   + "          from tbcliente cli"
				   + "         inner join tbticketbalanca tb"
				   + "            on tb.idcliente = cli.idcliente"
				   + "         inner join tbticketbalanca_ordemservico tb_ord"
				   + "            on tb_ord.idticket = tb.idticketbalanca"
				   + "         where tb_ord.idordem = ord.idordemservico) as nomecliente"
				   + "     , (select descricao from tbservico where idservico = ord.idservico) as servico"
				   + "     , (select count(tb.idTicketBalanca)"
				   + "          from tbticketbalanca tb"
				   + "         inner join tbticketbalanca_ordemservico tb_ord"
				   + "            on tb_ord.idticket = tb.idticketbalanca"
				   + "         where tb_ord.idordem = ord.idordemservico"
				   + "           and tb.operacao = 'ENTRADA') as qtdeTicketsEntrada"
				   + "     , (select count(tb.idTicketBalanca)"
				   + "          from tbticketbalanca tb"
				   + "         inner join tbticketbalanca_ordemservico tb_ord"
				   + "            on tb_ord.idticket = tb.idticketbalanca"
				   + "         where tb_ord.idordem = ord.idordemservico"
				   + "           and tb.operacao = 'SAIDA') as qtdeTicketsSaida"
				   + "  from tbordemservico ord";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		ResultSet rs = p.executeQuery();
		
		while (rs.next()) {
			OrdemServico ordem = new OrdemServico();
			ordem.setiIdOrdemServico(rs.getInt("IDORDEMSERVICO"));
			ordem.setiPesoEntradaTotal(rs.getInt("PESOENTRADATOTAL")); 			
			ordem.setiPesoSaidaTotal(rs.getInt("PESOSAIDATOTAL"));
			ordem.setfPorcQuebra(rs.getFloat("PORCENTAGEMQUEBRA"));
			ordem.setsObservacao(rs.getString("OBSERVACAO").toUpperCase());
			ordem.setsFinalizado(rs.getString("FINALIZADO"));
			ordem.setsNomeCliente(Funcoes.IfThen(Objects.isNull(rs.getString("NOMECLIENTE")), "", rs.getString("NOMECLIENTE").toUpperCase()));
			ordem.setsServico(rs.getString("SERVICO").toUpperCase());
			ordem.setiQtTicketEntrada(rs.getInt("QTDETICKETSENTRADA"));
			ordem.setiQtTicketSaida(rs.getInt("QTDETICKETSSAIDA"));
			
			ordens.add(ordem);
		}
		 
		rs.close();
		p.close();	
		return ordens;
	}

	public OrdemServico BuscarDadosOrdem(OrdemServico ordem) throws SQLException {
		String sql = "select ord.idordemservico, ord.pesoentradatotal , ord.pesosaidatotal , ord.porcentagemquebra ,  ord.observacao"
				   + "     , (case ord.finalizado"
				   + "        when 1 then 'sim'"
				   + "		  when 0 then 'não' end) as finalizado"
				   + "	   , (select first 1 tb.idcliente"
				   + "		    from tbticketbalanca tb"
				   + "	       inner join tbticketbalanca_ordemservico tb_ord"
				   + "	          on tb_ord.idticket = tb.idticketbalanca"
				   + "	       where tb_ord.idordem = ord.idordemservico) as idcliente"
				   + "     , (select first 1 tb.idproduto"
				   + "		    from tbticketbalanca tb"
				   + "	       inner join tbticketbalanca_ordemservico tb_ord"
				   + "	          on tb_ord.idticket = tb.idticketbalanca"
				   + "	       where tb_ord.idordem = ord.idordemservico) as idproduto"
				   + "	   , (select descricao from tbservico where idservico = ord.idservico) as servico"
				   + "  from tbordemservico ord"
				   + " where ord.idordemservico = ?";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		p.setInt(1, ordem.getiIdOrdemServico());
		
		ResultSet rs = p.executeQuery();
		rs.next();
		
		ordem.setbFinalizado(rs.getBoolean("finalizado"));
		ordem.setdDataInicio(rs.getDate("dataInicio"));
		ordem.setdDataTermino(rs.getDate("dataTermino"));
		ordem.setiIdServico(rs.getInt("idServico"));
		ordem.setfPorcQuebra(rs.getFloat("porcentagemQuebra"));
		ordem.setiPesoEntradaTotal(rs.getInt("PesoEntradaTotal"));
		ordem.setiPesoSaidaTotal(rs.getInt("pesoSaidaTotal"));
		ordem.setsObservacao(rs.getString("observacao"));
		ordem.setiIdCliente(rs.getInt("idCliente"));
		ordem.setiIdProduto(rs.getInt("idproduto"));		
		
		rs.close();
		p.close();
		
		return ordem;
	}
	

	
	/*
	public ArrayList<Cliente> ListarClientes() throws Exception{
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		String sql = "select * from tbcliente";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		ResultSet rs = p.executeQuery();
		
		while (rs.next()) {
			Cliente cliente = new Cliente();
			cliente.setiIdCliente(rs.getInt("idcliente"));
			cliente.setsNomeCliente(rs.getString("nome"));
			cliente.setsCpfCliente(rs.getString("cpf"));
			cliente.setsTelefoneCliente(rs.getString("telefone"));
			cliente.setsBairroCliente(rs.getString("bairro"));
			cliente.setsCidadeCliente(rs.getString("cidade"));
			clientes.add(cliente);
		}
		 
		rs.close();
		p.close();
		
		return clientes;
	}
	
	public Cliente BuscarCliente(String sCpf) throws Exception{                //implementar a busca por id também
		String sql = "select first 1 * from tbCliente cli where cli.cpf = ?";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		p.setString(1, sql);
		
		ResultSet rs = p.executeQuery();
		
		Cliente func = new Cliente();
		func.setiIdCliente(rs.getInt("idCliente"));
		func.setsNomeCliente(rs.getString("nome"));
		func.setsCpfCliente(rs.getString("cpf"));
		
		rs.close();
		p.close();
		
		return func;
	}
	
	public String[] BuscarNomesClientes() throws SQLException {
		int controle = 0;
		String sql = "select count(idcliente) as ct from tbCliente";		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		ResultSet rs = p.executeQuery();
		rs.next();
		int qtdeClientes = rs.getInt("ct");
		String arrayClientes[] = new String[qtdeClientes];
		if(qtdeClientes > 0) {
			sql = "select nome from tbCliente";
			p = con.GetConnection().prepareStatement(sql);
			rs = p.executeQuery();
			while(rs.next()) {
				arrayClientes[controle] = rs.getString("nome");
				controle += 1;
			}
		}
		return arrayClientes;
	}
	
	public ArrayList<Cliente> BuscarClienteCpf(String sCpf) throws Exception{       
		
		String sql = "select * from tbCliente cli where cli.cpf like '"+sCpf+"%'";
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		p.setString(1, sql);
		
		ResultSet rs = p.executeQuery();
	
		while (rs.next()) {
			Cliente cliente = new Cliente();
			cliente.setiIdCliente(rs.getInt("idcliente"));
			cliente.setsNomeCliente(rs.getString("nome"));
			cliente.setsCpfCliente(rs.getString("cpf"));
			cliente.setsTelefoneCliente(rs.getString("telefone"));
			cliente.setsBairroCliente(rs.getString("bairro"));
			cliente.setsCidadeCliente(rs.getString("cidade"));
			clientes.add(cliente);
		}
		
		return clientes;
	}*/

	
}

