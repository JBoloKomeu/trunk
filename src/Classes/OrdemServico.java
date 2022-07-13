package Classes;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Dao.ClienteDAO;
import Dao.FuncionarioDAO;
import Dao.OrdemDAO;

public class OrdemServico {
	private int iIdOrdemServico;
	private int iIdServico;
	private boolean bFinalizado;
	private int iPesoEntradaTotal;
	private int iPesoSaidaTotal;
	private float fPorcQuebra;
	private String sObservacao;
	private int iIdProduto;
	private int iIdCliente;
	private String sFinalizado;
	private String sNomeCliente;
	private String sServico;
	private int iQtTicketSaida;
	private int iQtTicketEntrada;
	private Date dDataInicio;
	private Date dDataTermino;
	
	public int getiIdOrdemServico() {
		return iIdOrdemServico;
	}
	public void setiIdOrdemServico(int iIdOrdemServico) {
		this.iIdOrdemServico = iIdOrdemServico;
	}
	public int getiIdServico() {
		return iIdServico;
	}
	public void setiIdServico(int iIdServico) {
		this.iIdServico = iIdServico;
	}
	public boolean isbFinalizado() {
		return bFinalizado;
	}
	public void setbFinalizado(boolean bFinalizado) {
		this.bFinalizado = bFinalizado;
	}
	public int getiPesoEntradaTotal() {
		return iPesoEntradaTotal;
	}
	public void setiPesoEntradaTotal(int iPesoEntradaTotal) {
		this.iPesoEntradaTotal = iPesoEntradaTotal;
	}
	public int getiPesoSaidaTotal() {
		return iPesoSaidaTotal;
	}
	public void setiPesoSaidaTotal(int iPesoSaidaTotal) {
		this.iPesoSaidaTotal = iPesoSaidaTotal;
	}
	public float getfPorcQuebra() {
		return fPorcQuebra;
	}
	public void setfPorcQuebra(float fPorcQuebra) {
		this.fPorcQuebra = fPorcQuebra;
	}
	public String getsObservacao() {
		return sObservacao;
	}
	public void setsObservacao(String sObservacao) {
		this.sObservacao = sObservacao;
	}
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
	public String getsFinalizado() {
		return sFinalizado;
	}
	public void setsFinalizado(String sFinalizado) {
		this.sFinalizado = sFinalizado;
	}
	public Date getdDataInicio() {
		return dDataInicio;
	}
	public void setdDataInicio(Date dDataInicio) {
		this.dDataInicio = dDataInicio;
	}
	public Date getdDataTermino() {
		return dDataTermino;
	}
	public void setdDataTermino(Date dDataTermino) {
		this.dDataTermino = dDataTermino;
	}
	
	public void CadastrarOrdem(String sIdsTickets) {
		ConnectionFactory conexao = new ConnectionFactory();
		OrdemDAO ordemDAO = new OrdemDAO(conexao);
		
		try {
			conexao.GetConnection();					
			System.out.println(conexao.statusConnection());
			
			try{
				ordemDAO.CadastrarOrdem(this, sIdsTickets);
				JOptionPane.showMessageDialog(null, "Ordem de Serviço cadastrada sucesso!");
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
	public OrdemServico BuscarDadosTickets(String sIdsTickets) {
		ConnectionFactory conexao = new ConnectionFactory();
		OrdemDAO ordemDAO = new OrdemDAO(conexao);
		
		try {
			conexao.GetConnection();					
			System.out.println(conexao.statusConnection());
			
			try{
				return ordemDAO.BuscarDadosTickets(this, sIdsTickets);
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
		return null;
	}
	
	public OrdemServico BuscarOrdemPorID() {
		ConnectionFactory conexao = new ConnectionFactory();
		OrdemDAO ordemDAO = new OrdemDAO(conexao);
		
		try {
			conexao.GetConnection();					
			System.out.println(conexao.statusConnection());
			
			try{
				return ordemDAO.BuscarDadosOrdem(this);
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
		return null;
	}
	
	public ArrayList<OrdemServico> ListarOrdens() {
		ConnectionFactory conexao = new ConnectionFactory();
		OrdemDAO OrdemDao = new OrdemDAO(conexao);
		ArrayList<OrdemServico> ordens = new ArrayList<OrdemServico>();
		try{
			conexao.GetConnection();
			System.out.println(conexao.statusConnection());
			
			try {
				return OrdemDao.ListarOrdens();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordens;
	}
	public String getsNomeCliente() {
		return sNomeCliente;
	}
	public void setsNomeCliente(String sNomeCliente) {
		this.sNomeCliente = sNomeCliente;
	}
	public String getsServico() {
		return sServico;
	}
	public void setsServico(String sServico) {
		this.sServico = sServico;
	}
	public int getiQtTicketSaida() {
		return iQtTicketSaida;
	}
	public void setiQtTicketSaida(int iQtTicketSaida) {
		this.iQtTicketSaida = iQtTicketSaida;
	}
	public int getiQtTicketEntrada() {
		return iQtTicketEntrada;
	}
	public void setiQtTicketEntrada(int iQtTicketEntrada) {
		this.iQtTicketEntrada = iQtTicketEntrada;
	}
	
	
}
