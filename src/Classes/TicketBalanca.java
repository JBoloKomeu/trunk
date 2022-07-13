package Classes;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Dao.ClienteDAO;
import Dao.ProdutoDAO;
import Dao.TicketBalancaDAO;

public class TicketBalanca {

	private int iIdTicketBalanca;
	private int iIdProduto;
	private int iIdCliente;
	private int iIdFuncEntrada;
	private int iIdFuncSaida;
	private String sNomeCliente;
	private String sDescrProduto;
	private String sNomeFuncEntrada;
	private String sNomeFuncSaida;
	private String sDataHoraEntrada;
	private String sDataHoraSaida;
	private String sPlacaVeiculo;
	private String sNomeMotorista;
	private int  iPesoEntrada;
	private int  iPesoSaida;
	private int  iPesoLiquido;
	private String sOperacao;
	private String sObservacao;
	private Date dDataEntrada;
	private Time tHoraEntrada;
	private Date dDataSaida;
	private Time tHoraSaida;
	
	public int getiIdProduto() {
		return iIdProduto;
	}
	public void setiIdProduto(int iIdProduto) {
		this.iIdProduto = iIdProduto;
	}
	public int getiIdCliente() {
		return iIdCliente;
	}
	public void setiIdCliente(int iIdCliente) {
		this.iIdCliente = iIdCliente;
	}
	public int getiIdFuncEntrada() {
		return iIdFuncEntrada;
	}
	public void setiIdFuncEntrada(int iIdFuncEntrada) {
		this.iIdFuncEntrada = iIdFuncEntrada;
	}
	public int getiIdFuncSaida() {
		return iIdFuncSaida;
	}
	public void setiIdFuncSaida(int iIdFuncSaida) {
		this.iIdFuncSaida = iIdFuncSaida;
	}
	public String getsDataHoraEntrada() {
		return sDataHoraEntrada;
	}
	public void setsDataHoraEntrada(String sDataHoraEntrada) {
		this.sDataHoraEntrada = sDataHoraEntrada;
	}
	public String getsDataHoraSaida() {
		return sDataHoraSaida;
	}
	public void setsDataHoraSaida(String sDataHoraSaida) {
		this.sDataHoraSaida = sDataHoraSaida;
	}
	public String getsPlacaVeiculo() {
		return sPlacaVeiculo;
	}
	public void setsPlacaVeiculo(String sPlacaVeiculo) {
		this.sPlacaVeiculo = sPlacaVeiculo;
	}
	public String getsNomeMotorista() {
		return sNomeMotorista;
	}
	public void setsNomeMotorista(String sNomeMotorista) {
		this.sNomeMotorista = sNomeMotorista;
	}
	public int getiPesoEntrada() {
		return iPesoEntrada;
	}
	public void setiPesoEntrada(int iPesoEntrada) {
		this.iPesoEntrada = iPesoEntrada;
	}
	public int getiPesoSaida() {
		return iPesoSaida;
	}
	public void setiPesoSaida(int iPesoSaida) {
		this.iPesoSaida = iPesoSaida;
	}
	public int getiPesoLiquido() {
		return iPesoLiquido;
	}
	public void setiPesoLiquido(int iPesoLiquido) {
		this.iPesoLiquido = iPesoLiquido;
	}
	public String getsOperacao() {
		return sOperacao;
	}
	public void setsOperacao(String sOperacao) {
		this.sOperacao = sOperacao;
	}
	public String getsObservacao() {
		return sObservacao;
	}
	public void setsObservacao(String sObservacao) {
		this.sObservacao = sObservacao;
	}
	public Date getdDataEntrada() {
		return dDataEntrada;
	}
	public void setdDataEntrada(Date dDataEntrada) {
		this.dDataEntrada = dDataEntrada;
	}
	public Time gettHoraEntrada() {
		return tHoraEntrada;
	}
	public void settHoraEntrada(Time tHoraEntrada) {
		this.tHoraEntrada = tHoraEntrada;
	}
	public Date getdDataSaida() {
		return dDataSaida;
	}
	public void setdDataSaida(Date dDataSaida) {
		this.dDataSaida = dDataSaida;
	}
	public Time gettHoraSaida() {
		return tHoraSaida;
	}
	public void settHoraSaida(Time tHoraSaida) {
		this.tHoraSaida = tHoraSaida;
	}
	public int getiIdTicketBalanca() {
		return iIdTicketBalanca;
	}
	public void setiIdTicketBalanca(int iIdTicketBalanca) {
		this.iIdTicketBalanca = iIdTicketBalanca;
	}
	public String getsNomeCliente() {
		return sNomeCliente;
	}
	public void setsNomeCliente(String sNomeCliente) {
		this.sNomeCliente = sNomeCliente;
	}
	public String getsDescrProduto() {
		return sDescrProduto;
	}
	public void setsDescrProduto(String sDescrProduto) {
		this.sDescrProduto = sDescrProduto;
	}
	public String getsNomeFuncEntrada() {
		return sNomeFuncEntrada;
	}
	public void setsNomeFuncEntrada(String sNomeFuncEntrada) {
		this.sNomeFuncEntrada = sNomeFuncEntrada;
	}
	public String getsNomeFuncSaida() {
		return sNomeFuncSaida;
	}
	public void setsNomeFuncSaida(String sNomeFuncSaida) {
		this.sNomeFuncSaida = sNomeFuncSaida;
	}
	public boolean CadastrarTicketEntrada() {
		ConnectionFactory conexao = new ConnectionFactory();
		TicketBalancaDAO ticketDAO = new TicketBalancaDAO(conexao);
		
		try {
			conexao.GetConnection();					
			System.out.println(conexao.statusConnection());
			
			try{
				ticketDAO.CadastrarTicketEntrada(this);
				JOptionPane.showMessageDialog(null, "Entrada realizada com sucesso!");
				return true;
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Falha no cadastro!");
				return false;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.FecharConexao();
		}return false;
	}
	
	public String[] BuscarPlacasEntradas(){
		ConnectionFactory conexao = new ConnectionFactory();
		TicketBalancaDAO TicketDAO = new TicketBalancaDAO(conexao);
		try{
			conexao.GetConnection();
			System.out.println(conexao.statusConnection());
			
			try {
				return TicketDAO.BuscarPlacasEntrada();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] arrayPlacas = {};
		return arrayPlacas;
	}
	
	public TicketBalanca BuscarTicketEntrada(String sPlaca) {
		ConnectionFactory conexao = new ConnectionFactory();
		TicketBalancaDAO TicketDAO = new TicketBalancaDAO(conexao);
		try{
			conexao.GetConnection();
			System.out.println(conexao.statusConnection());
			
			try {
				return TicketDAO.BuscarTicketEntrada(sPlaca);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<TicketBalanca> BuscarTicketsEntrada() {
		ConnectionFactory conexao = new ConnectionFactory();
		TicketBalancaDAO TicketBalancaDAO = new TicketBalancaDAO(conexao);
		ArrayList<TicketBalanca> TicketBalanca = new ArrayList<TicketBalanca>();
		try{
			conexao.GetConnection();
			System.out.println(conexao.statusConnection());
			try {
				return TicketBalancaDAO.BuscarTicketsEntrada();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<TicketBalanca> BuscarTicketsSaida(String sFiltros) {
		ConnectionFactory conexao = new ConnectionFactory();
		TicketBalancaDAO TicketBalancaDAO = new TicketBalancaDAO(conexao);
		ArrayList<TicketBalanca> TicketBalanca = new ArrayList<TicketBalanca>();
		try{
			conexao.GetConnection();
			System.out.println(conexao.statusConnection());
			try {
				return TicketBalancaDAO.BuscarTicketsSaida(sFiltros);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public void CadastrarTicketSaida() {
		ConnectionFactory conexao = new ConnectionFactory();
		TicketBalancaDAO ticketDAO = new TicketBalancaDAO(conexao);
		
		try {
			conexao.GetConnection();					
			System.out.println(conexao.statusConnection());
			
			try{
				ticketDAO.CadastrarTicketSaida(this);
				JOptionPane.showMessageDialog(null, "Saida realizada com sucesso!");
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Falha no cadastro!");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.FecharConexao();
		}
		
	}
	
	public ArrayList<TicketBalanca> BuscarPorData(String data1, String data2) {
		ConnectionFactory conexao = new ConnectionFactory();
		TicketBalancaDAO ticketDAO = new TicketBalancaDAO(conexao);
		
		try{
			conexao.GetConnection();
			System.out.println(conexao.statusConnection());
			try {
				return ticketDAO.BuscarData(data1, data2);
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
