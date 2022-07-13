package Classes;

import javax.swing.JOptionPane;

import Dao.ClienteDAO;
import Dao.ServicoDAO;
import Dao.TicketBalancaDAO;

public class Servico {
	private int iIdServico;
	private String sDescricaoServico;
	private Float fValorServico;
	private Float fQtdeKgCalculo;
	private String sProdutos;
	public int getiIdServico() {
		return iIdServico;
	}
	public void setiIdServico(int iIdServico) {
		this.iIdServico = iIdServico;
	}
	public String getsDescricaoServico() {
		return sDescricaoServico;
	}
	public void setsDescricaoServico(String sDescricaoServico) {
		this.sDescricaoServico = sDescricaoServico;
	}
	public Float getfValorServico() {
		return fValorServico;
	}
	public void setfValorServico(Float fValorServico) {
		this.fValorServico = fValorServico;
	}
	public Float getfQtdeKgCalculo() {
		return fQtdeKgCalculo;
	}
	public void setfQtdeKgCalculo(Float fQtdeKgCalculo) {
		this.fQtdeKgCalculo = fQtdeKgCalculo;
	}
	public String getsProdutos() {
		return sProdutos;
	}
	public void setsProdutos(String sProdutos) {
		this.sProdutos = sProdutos;
	}
	
	public Boolean cadastrarServico() {
		ConnectionFactory conexao = new ConnectionFactory();
		ServicoDAO servicoDAO = new ServicoDAO(conexao);
		
		try {
			conexao.GetConnection();					
			System.out.println(conexao.statusConnection());
			
			try{
				servicoDAO.CadastrarServico(this);
				JOptionPane.showMessageDialog(null, "Serviço cadastrado sucesso!");
				return true;
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Algo deu errado, que pena!!");
				return false;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.FecharConexao();
		}
		return false;
	}
	public String[] buscarServicoPorProduto(int iIdProduto) {
		ConnectionFactory conexao = new ConnectionFactory();
		ServicoDAO servicoDAO = new ServicoDAO(conexao);
		try{
			conexao.GetConnection();
			System.out.println(conexao.statusConnection());
			
			try {
				return servicoDAO.buscarServicoPorProduto(iIdProduto);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] arrayServicos = {};
		return arrayServicos;
	}
	
	
}
