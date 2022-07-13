package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Classes.Cliente;
import Classes.ConnectionFactory;
import Classes.Funcoes;
import Classes.TicketBalanca;

public class TicketBalancaDAO {
	private ConnectionFactory con;
	
	public TicketBalancaDAO(ConnectionFactory con) {
		this.con = con;
	}

	public void CadastrarTicketEntrada(TicketBalanca ticketBalanca) throws SQLException {
		Date date = new Date();
		java.sql.Date dt = new java.sql.Date(date.getTime());
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String sql = "insert into tbTicketBalanca (idProduto, idCliente, idFuncEntrada, dataEntrada, horaEntrada, placaVeiculo, nomeMotorista, pesoEntrada, observacao) values (?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		p.setInt(1, ticketBalanca.getiIdProduto());
		p.setInt(2, ticketBalanca.getiIdCliente());
		p.setInt(3, ticketBalanca.getiIdFuncEntrada());
		p.setDate(4, dt);
		p.setTime(5, java.sql.Time.valueOf(dateFormat.format(dt.getTime())));
		p.setString(6, ticketBalanca.getsPlacaVeiculo().toUpperCase());
		p.setString(7, ticketBalanca.getsNomeMotorista().toUpperCase());
		p.setInt(8, ticketBalanca.getiPesoEntrada());
		p.setString(9, ticketBalanca.getsObservacao().toUpperCase());
		
		p.executeUpdate();
		p.close();
	}
	
	public String[] BuscarPlacasEntrada() throws SQLException {
		int controle = 0;
		
		String sql = "select count(placaVeiculo) as ct from tbTicketBalanca where pesoSaida is null";		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		ResultSet rs = p.executeQuery();
		rs.next();
		
		int qtdeTickets = rs.getInt("ct");
		String arrayPlacasEntrada[] = new String[qtdeTickets];
		
		if(qtdeTickets > 0) {
			sql = "select placaVeiculo from tbTicketBalanca where pesoSaida is null";
			p = con.GetConnection().prepareStatement(sql);
			rs = p.executeQuery();
			while(rs.next()) {
				arrayPlacasEntrada[controle] = rs.getString("placaVeiculo").toUpperCase();
				controle += 1;
			}
		}
		return arrayPlacasEntrada;
	}
	
	public ArrayList<TicketBalanca> BuscarTicketsEntrada() throws SQLException { //incompleto
		int controle = 0;
		
		String sql = "select count(idTicketBalanca) as ct from tbTicketBalanca where pesoSaida is null";		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		ResultSet rs = p.executeQuery();
		rs.next();
		
		int qtdeTickets = rs.getInt("ct");
		ArrayList<TicketBalanca> arrayTickets = new ArrayList<TicketBalanca>();
		
		if(qtdeTickets > 0) {
			sql = "select idTicketBalanca, placaVeiculo, idProduto, idCliente, idFuncEntrada"
				+ "     , nomeMotorista, pesoEntrada, observacao, dataEntrada, horaEntrada "
				+ "  from tbTicketBalanca "
				+ " where pesoSaida is null";
			
			p = con.GetConnection().prepareStatement(sql);
			rs = p.executeQuery();
			while(rs.next()) {
				TicketBalanca ticketEntrada = new TicketBalanca();
				
				ticketEntrada.setiIdTicketBalanca(rs.getInt("idTicketBalanca"));
				ticketEntrada.setsPlacaVeiculo(rs.getString("placaVeiculo").toUpperCase());
				ticketEntrada.setiIdCliente(rs.getInt("idCliente"));
				ticketEntrada.setiIdProduto(rs.getInt("idProduto"));
				ticketEntrada.setiIdFuncEntrada(rs.getInt("idFuncEntrada"));
				ticketEntrada.setsNomeMotorista(rs.getString("nomeMotorista").toUpperCase());
				ticketEntrada.setiPesoEntrada(rs.getInt("pesoEntrada"));
				ticketEntrada.setsObservacao(rs.getString("observacao").toUpperCase());
				ticketEntrada.setdDataEntrada(rs.getDate("dataEntrada"));
				ticketEntrada.settHoraEntrada(rs.getTime("horaEntrada"));

				
				arrayTickets.add(ticketEntrada);
			}
		}
		return arrayTickets;
	}
	

	public TicketBalanca BuscarTicketEntrada(String sPlaca) throws SQLException {
		TicketBalanca ticketEntrada = new TicketBalanca();
		String sql = "select first 1 * from tbTicketBalanca ti where ti.placaVeiculo = ?";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		p.setString(1, sPlaca);
		
		ResultSet rs = p.executeQuery();
		
		if(rs.next()) {
			ticketEntrada.setiIdTicketBalanca(rs.getInt("idTicketBalanca"));
			ticketEntrada.setsPlacaVeiculo(rs.getString("placaVeiculo").toUpperCase());
	        ticketEntrada.setiIdCliente(rs.getInt("idCliente"));
	        ticketEntrada.setiIdProduto(rs.getInt("idProduto"));
	        ticketEntrada.setiIdFuncEntrada(rs.getInt("idFuncEntrada"));
	        ticketEntrada.setsNomeMotorista(rs.getString("nomeMotorista").toUpperCase());
	        ticketEntrada.setiPesoEntrada(rs.getInt("pesoEntrada"));
	        ticketEntrada.setsObservacao(rs.getString("observacao").toUpperCase());
	        ticketEntrada.setdDataEntrada(rs.getDate("dataEntrada"));
	        ticketEntrada.settHoraEntrada(rs.getTime("horaEntrada"));
		}
		
		rs.close();
		p.close();
		
		return ticketEntrada;
	}
	
	public ArrayList<TicketBalanca> BuscarTicketsSaida(String sFiltros) throws SQLException { 
		int controle = 0;
		
		String sql = "select count(tb.idTicketBalanca) as ct from tbTicketBalanca tb"
				   + " inner join tbProduto prod"
				   + "    on prod.idProduto = tb.idproduto"
				   + " inner join tbCliente cli"
			 	   + "    on cli.idCliente = tb.idcliente"
				   + " where tb.pesoSaida is not null" + sFiltros;		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		ResultSet rs = p.executeQuery();
		rs.next();
		
		int qtdeTickets = rs.getInt("ct");
		ArrayList<TicketBalanca> arrayTickets = new ArrayList<TicketBalanca>();
		
		if(qtdeTickets > 0) {
			sql = "select tb.placaVeiculo, tb.idProduto, tb.idCliente, tb.idFuncEntrada"
			    + "     , tb.nomeMotorista, tb.pesoEntrada, tb.observacao, tb.dataEntrada, tb.horaEntrada"
				+ "     , tb.dataSaida, tb.horaSaida, tb.pesoLiquido, tb.pesoSaida, tb.operacao, tb.idTicketBalanca, tb.idFuncsaida"
				+ "     , prod.descricao as descProduto, cli.nome as nomeCliente"
				+ "     , (select func.nome from tbFuncionario func where func.idfuncionario = tb.idfuncentrada) as nomeFuncEnt"
				+ "     , (select func.nome from tbFuncionario func where func.idfuncionario = tb.idfuncsaida) as nomeFuncSai"
				+ "  from tbTicketBalanca tb"
				+ " inner join tbProduto prod"
				+ "    on prod.idProduto = tb.idproduto"
				+ " inner join tbCliente cli"
				+ "    on cli.idCliente = tb.idcliente"
				+ " where tb.pesoSaida is not null" + sFiltros
				+ " order by tb.idTicketBalanca desc";
			
			p = con.GetConnection().prepareStatement(sql);
			rs = p.executeQuery();
			while(rs.next()) {
				TicketBalanca ticketSaida = new TicketBalanca();
				
				ticketSaida.setsPlacaVeiculo(rs.getString("placaVeiculo").toUpperCase());
				ticketSaida.setiIdCliente(rs.getInt("idCliente"));
				ticketSaida.setiIdProduto(rs.getInt("idProduto"));
				ticketSaida.setiIdFuncEntrada(rs.getInt("idFuncEntrada"));
				ticketSaida.setsNomeMotorista(rs.getString("nomeMotorista").toUpperCase());
				ticketSaida.setiPesoEntrada(rs.getInt("pesoEntrada"));
				ticketSaida.setsObservacao(rs.getString("observacao").toUpperCase());
				ticketSaida.setdDataEntrada(rs.getDate("dataEntrada"));
				ticketSaida.settHoraEntrada(rs.getTime("horaEntrada"));
				ticketSaida.setdDataSaida(rs.getDate("dataSaida"));
				ticketSaida.settHoraSaida(rs.getTime("horaSaida"));
				ticketSaida.setiPesoLiquido(rs.getInt("pesoLiquido"));
				ticketSaida.setiPesoSaida(rs.getInt("pesoSaida"));
				ticketSaida.setsOperacao(rs.getString("operacao").toUpperCase());
				ticketSaida.setiIdTicketBalanca(rs.getInt("idTicketBalanca"));
				ticketSaida.setiIdFuncSaida(rs.getInt("idFuncsaida"));
				ticketSaida.setsDescrProduto(rs.getString("descProduto").toUpperCase());
				ticketSaida.setsNomeCliente(rs.getString("nomeCliente").toUpperCase());
				ticketSaida.setsNomeFuncEntrada(rs.getString("nomeFuncEnt").toUpperCase());
				ticketSaida.setsNomeFuncSaida(rs.getString("nomeFuncSai").toUpperCase());
				
				arrayTickets.add(ticketSaida);
			}
		}
		return arrayTickets;
	}

	public void CadastrarTicketSaida(TicketBalanca ticketBalanca) throws SQLException {
		Date date = new Date();
		java.sql.Date dt = new java.sql.Date(date.getTime());
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		
		String sql = "update tbTicketBalanca"
				   + "   set idFuncSaida = ?, pesoSaida = ?, pesoLiquido = ?, operacao = ?, dataSaida = ?, horaSaida = ?, observacao = ?"
				   + " where idTicketBalanca = ?";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);
		
		p.setInt(1, ticketBalanca.getiIdFuncSaida());
		p.setInt(2, ticketBalanca.getiPesoSaida());
		p.setInt(3, ticketBalanca.getiPesoLiquido());
		p.setString(4, ticketBalanca.getsOperacao().toUpperCase());
		p.setDate(5, dt);
		p.setTime(6, java.sql.Time.valueOf(dateFormat.format(dt.getTime())));
		p.setString(7, ticketBalanca.getsObservacao().toUpperCase());
		p.setInt(8, ticketBalanca.getiIdTicketBalanca());
		
		p.executeUpdate();
		p.close();
		
	}
	
	public ArrayList<TicketBalanca> BuscarData(String data1, String data2) throws SQLException {
		String sql = "select tb.placaVeiculo, tb.idProduto, tb.idCliente, tb.idFuncEntrada"
				+ "                     , tb.nomeMotorista, tb.pesoEntrada, tb.observacao, tb.dataEntrada, tb.horaEntrada"
				+ "                        , tb.dataSaida, tb.horaSaida, tb.pesoLiquido, tb.pesoSaida, tb.operacao, tb.idTicketBalanca, tb.idFuncsaida"
				+ "                     , prod.descricao, cli.nome"
				+ "   				    , (select cli.nome from tbCliente cli where cli.idCliente = tb.idcliente) as nomeCliente"
				+ "                     , (select func.nome from tbFuncionario func where func.idfuncionario = tb.idfuncentrada) as nomeFuncEnt"
				+ "						, (select prod.descricao from tbProduto prod where prod.idProduto = tb.idproduto) as descProduto"
				+ "                     , (select func.nome from tbFuncionario func where func.idfuncionario = tb.idfuncsaida) as nomeFuncSai"
				+ "                  from tbTicketBalanca tb"
				+ "                 inner join tbProduto prod"
				+ "                    on prod.idProduto = tb.idproduto"
				+ "                 inner join tbCliente cli"
				+ "                    on cli.idCliente = tb.idcliente"
				+ "               	where tb.dataEntrada between '"+data1+"' and '"+data2+"'";
		
		PreparedStatement p = con.GetConnection().prepareStatement(sql);		
		
		ResultSet rs = p.executeQuery();
		ArrayList<TicketBalanca> arrayCli = new ArrayList<TicketBalanca>();
		while(rs.next()) {
			
			
			TicketBalanca ticketSaida = new TicketBalanca();
			
			ticketSaida.setsPlacaVeiculo(rs.getString("placaVeiculo").toUpperCase());
			ticketSaida.setiIdCliente(rs.getInt("idCliente"));
			ticketSaida.setiIdProduto(rs.getInt("idProduto"));
			ticketSaida.setiIdFuncEntrada(rs.getInt("idFuncEntrada"));
			ticketSaida.setsNomeMotorista(rs.getString("nomeMotorista").toUpperCase());
			ticketSaida.setiPesoEntrada(rs.getInt("pesoEntrada"));
			ticketSaida.setsObservacao(rs.getString("observacao").toUpperCase());
			ticketSaida.setdDataEntrada(rs.getDate("dataEntrada"));
			ticketSaida.settHoraEntrada(rs.getTime("horaEntrada"));
			ticketSaida.setdDataSaida(rs.getDate("dataSaida"));
			ticketSaida.settHoraSaida(rs.getTime("horaSaida"));
			ticketSaida.setiPesoLiquido(rs.getInt("pesoLiquido"));
			ticketSaida.setiPesoSaida(rs.getInt("pesoSaida"));
			ticketSaida.setsOperacao(rs.getString("operacao").toUpperCase());
			ticketSaida.setiIdTicketBalanca(rs.getInt("idTicketBalanca"));
			ticketSaida.setiIdFuncSaida(rs.getInt("idFuncsaida"));
			ticketSaida.setsDescrProduto(rs.getString("descProduto").toUpperCase());
			ticketSaida.setsNomeCliente(rs.getString("nomeCliente").toUpperCase());
			ticketSaida.setsNomeFuncEntrada(rs.getString("nomeFuncEnt").toUpperCase());
			ticketSaida.setsNomeFuncSaida(rs.getString("nomeFuncSai").toUpperCase());
			
			arrayCli.add(ticketSaida);
			
		}return arrayCli;
	}
	
	
}
