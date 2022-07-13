package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Classes.Funcoes;
import Classes.OrdemServico;
import Classes.Servico;
import Classes.TicketBalanca;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTree;
import javax.swing.JComboBox;

public class telaOrdem extends JFrame {

	private JPanel mainPanel;
	private JTable table;
	private DefaultTableModel model_table;
	private JScrollPane scroll_table;
	
	
	private JPanel contentPane;
	private JButton btnAtualizar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnSair;
	private JButton btnInserirTicket;
	private JButton btnExcluirTicket;
	private JButton btnConfirmar;
	private JButton btnCancelar;
	private JButton btnBuscar;
	private JButton btnEncerrar;
	private String sIdsTickets = "";
	private JComboBox cbServico;
	private JLabel lblFinalizado;
	private OrdemServico ordem = new OrdemServico();
	
	private String sOperacaoTela = "";
	private static String INCLUIR_NOVA_ORDEM = "1";
	private static String ORDEM_NA_TELA = "2";
	
	private Funcoes func = new Funcoes();
	TicketBalanca ticBal = new TicketBalanca();
	ArrayList<TicketBalanca> arrayTicketBalancaSaida = ticBal.BuscarTicketsSaida(" and 1=2");
	
	private Servico servico = new Servico();
	private String arrayServicos[];
	
	private JTextField textCliente;
	private JTextField textProduto;
	private JTextField textTotalEntrada;
	private JTextField textTotalSaida;
	private JTextField textPorcentagem;
	private JTextField textObservacao;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaOrdem frame = new telaOrdem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public telaOrdem() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				Funcoes.fecharTela();
			}
		});
		
		
		mainPanel   = new JPanel(null);
		setSize(1008, 500);
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null); //desativa edição dos campos
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
	
		setLocationRelativeTo(null);
		
		model_table = new DefaultTableModel();
		model_table.addColumn("Tic. Bal.");
		model_table.addColumn("Placa"); 
		model_table.addColumn("Cliente"); 
		model_table.addColumn("Produto");
		model_table.addColumn("Func. Entrada");
		model_table.addColumn("Motorista");
		model_table.addColumn("Peso Entrada");
		model_table.addColumn("obs");
		model_table.addColumn("data entrada");
		model_table.addColumn("hora entrada");
		model_table.addColumn("Data saida");
		model_table.addColumn("hora Saida");
		model_table.addColumn("peso saida");
		model_table.addColumn("operacao");	
		
		table.setModel(model_table);

		scroll_table = new JScrollPane(table);


		scroll_table.setBounds(10, 276, 972, 174);
		scroll_table.setVisible(true);
		mainPanel.add(scroll_table);

		getContentPane().add(mainPanel);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Tem certeza que deseja cancelar a operação? Os dados não salvos serão perdidos!", "Cancelar Operação", JOptionPane.YES_NO_OPTION) == 0) {
					limparCampos();
				}
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCancelar.setBounds(812, 217, 170, 23);
		mainPanel.add(btnCancelar);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setEnabled(false);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbServico.getSelectedIndex() != -1) {
					if(sOperacaoTela.equals(INCLUIR_NOVA_ORDEM)) {
						
						try {
							ordem.setiIdServico(func.buscaIntBanco("tbServico", "idServico", "descricao", cbServico.getEditor().getItem().toString()));
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						ordem.setbFinalizado(false);
						ordem.setfPorcQuebra(Float.parseFloat(textPorcentagem.getText().replace("%", "")));
						ordem.setiPesoEntradaTotal(Integer.parseInt(textTotalEntrada.getText()));
						ordem.setiPesoSaidaTotal(Integer.parseInt(textTotalSaida.getText()));
						ordem.setsObservacao(textObservacao.getText());

						ordem.CadastrarOrdem(sIdsTickets);
						System.out.println(ordem.getiIdOrdemServico());
						
						sOperacaoTela = ORDEM_NA_TELA; 
						habilitarComponentes();					
						
					}		
				}else {
					JOptionPane.showMessageDialog(null, "Informe um serviço!");
				}
			}
		});
		btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnConfirmar.setBounds(632, 217, 170, 23);
		mainPanel.add(btnConfirmar);
		
		JButton btnNovaOrdem = new JButton("Incluir nova Ordem");
		btnNovaOrdem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();
				sOperacaoTela = INCLUIR_NOVA_ORDEM;
				incluirTicket();
			}
		});
		btnNovaOrdem.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNovaOrdem.setBounds(10, 74, 170, 23);
		mainPanel.add(btnNovaOrdem);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarOrdem();
			}
		});
		btnBuscar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnBuscar.setBounds(190, 74, 102, 23);
		mainPanel.add(btnBuscar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setFont(new Font("Arial", Font.PLAIN, 16));
		btnExcluir.setBounds(414, 74, 102, 23);
		mainPanel.add(btnExcluir);
		
		btnEncerrar = new JButton("Encerrar");
		btnEncerrar.setEnabled(false);
		btnEncerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEncerrar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnEncerrar.setBounds(302, 74, 102, 23);
		mainPanel.add(btnEncerrar);
		
		JLabel lblNewLabel = new JLabel("Cliente");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 106, 68, 20);
		mainPanel.add(lblNewLabel);
		
		textCliente = new JTextField();
		textCliente.setEditable(false);
		textCliente.setFont(new Font("Arial", Font.PLAIN, 16));
		textCliente.setColumns(10);
		textCliente.setBounds(88, 108, 146, 20);
		mainPanel.add(textCliente);
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProduto.setFont(new Font("Arial", Font.PLAIN, 16));
		lblProduto.setBounds(10, 137, 68, 20);
		mainPanel.add(lblProduto);
		
		textProduto = new JTextField();
		textProduto.setEditable(false);
		textProduto.setFont(new Font("Arial", Font.PLAIN, 16));
		textProduto.setColumns(10);
		textProduto.setBounds(88, 139, 146, 20);
		mainPanel.add(textProduto);
		
		JLabel lblTotalEntrada = new JLabel("Total Entrada");
		lblTotalEntrada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalEntrada.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTotalEntrada.setBounds(268, 108, 102, 20);
		mainPanel.add(lblTotalEntrada);
		
		textTotalEntrada = new JTextField();
		textTotalEntrada.setEditable(false);
		textTotalEntrada.setFont(new Font("Arial", Font.PLAIN, 16));
		textTotalEntrada.setColumns(10);
		textTotalEntrada.setBounds(380, 108, 146, 20);
		mainPanel.add(textTotalEntrada);
		
		JLabel lblTotalSada = new JLabel("Total Sa\u00EDda");
		lblTotalSada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalSada.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTotalSada.setBounds(268, 139, 102, 20);
		mainPanel.add(lblTotalSada);
		
		textTotalSaida = new JTextField();
		textTotalSaida.setEditable(false);
		textTotalSaida.setFont(new Font("Arial", Font.PLAIN, 16));
		textTotalSaida.setColumns(10);
		textTotalSaida.setBounds(380, 139, 146, 20);
		mainPanel.add(textTotalSaida);
		
		JLabel lblPorcentagemDeQuebra = new JLabel("Porcentagem de Quebra");
		lblPorcentagemDeQuebra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPorcentagemDeQuebra.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPorcentagemDeQuebra.setBounds(533, 106, 214, 20);
		mainPanel.add(lblPorcentagemDeQuebra);
		
		textPorcentagem = new JTextField();
		textPorcentagem.setEditable(false);
		textPorcentagem.setFont(new Font("Arial", Font.PLAIN, 16));
		textPorcentagem.setText("999"+"%");
		textPorcentagem.setColumns(10);
		textPorcentagem.setBounds(757, 108, 52, 20);
		mainPanel.add(textPorcentagem);
		
		btnInserirTicket = new JButton("Inserir Ticket");
		btnInserirTicket.setEnabled(false);
		btnInserirTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String x = abrirTelaListarTickets();                                            inserir
				
			}
		});
		btnInserirTicket.setFont(new Font("Arial", Font.PLAIN, 16));
		btnInserirTicket.setBounds(10, 242, 126, 23);
		mainPanel.add(btnInserirTicket);
		
		btnExcluirTicket = new JButton("Excluir Ticket");
		btnExcluirTicket.setEnabled(false);
		btnExcluirTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExcluirTicket.setFont(new Font("Arial", Font.PLAIN, 16));
		btnExcluirTicket.setBounds(146, 242, 126, 23);
		mainPanel.add(btnExcluirTicket);
		
		JLabel lblNewLabel_1 = new JLabel("Ordem de Servi\u00E7o");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_1.setBounds(10, 0, 972, 66);
		mainPanel.add(lblNewLabel_1);
		
		JLabel lblObservacoes = new JLabel("Observa\u00E7\u00F5es");
		lblObservacoes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblObservacoes.setFont(new Font("Arial", Font.PLAIN, 16));
		lblObservacoes.setBounds(616, 137, 131, 20);
		mainPanel.add(lblObservacoes);
		
		textObservacao = new JTextField();
		textObservacao.setFont(new Font("Arial", Font.PLAIN, 16));
		textObservacao.setColumns(10);
		textObservacao.setBounds(757, 139, 225, 20);
		mainPanel.add(textObservacao);
		
		JLabel lblFinalizado = new JLabel("");
		lblFinalizado.setBounds(884, 111, 98, 14);
		mainPanel.add(lblFinalizado);
		
		JLabel lblIdOrdem = new JLabel("Ordem");
		lblIdOrdem.setBounds(839, 111, 46, 14);
		mainPanel.add(lblIdOrdem);
		
		cbServico = new JComboBox();
		cbServico.setFont(new Font("Arial", Font.PLAIN, 14));
		cbServico.setBounds(89, 172, 146, 22);
		mainPanel.add(cbServico);
		
		JLabel lblServio = new JLabel("Servi\u00E7o");
		lblServio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblServio.setFont(new Font("Arial", Font.PLAIN, 16));
		lblServio.setBounds(10, 176, 68, 20);
		mainPanel.add(lblServio);
	}
	
	protected void buscarOrdem() {
		//Funcoes.AbrirTela(new telaListarOrdem(this));
	}

	protected void limparCampos() {
		model_table.setRowCount(0);
		sIdsTickets = "";
		textCliente.setText("");
		textProduto.setText("");
		textTotalEntrada.setText("");
		textTotalSaida.setText("");
		textPorcentagem.setText("");
		textObservacao.setText("");
		cbServico.setSelectedIndex(-1);
		
		habilitarComponentes();	
	}

	protected void incluirTicket() {
		Funcoes.AbrirTela(new telaListarTicketSaida(this, sIdsTickets));
	}
	
	private void preencherCampos(OrdemServico ordem) throws SQLException {
		textCliente.setText(func.buscaStrBanco("tbCliente", "nome", "idCliente", String.valueOf(ordem.getiIdCliente())));
		textProduto.setText(func.buscaStrBanco("tbProduto", "descricao", "idProduto", String.valueOf(ordem.getiIdProduto())));		
		textTotalEntrada.setText(String.valueOf(ordem.getiPesoEntradaTotal()));
		textTotalSaida.setText(String.valueOf(ordem.getiPesoSaidaTotal()));
		textPorcentagem.setText(String.valueOf(100 - (ordem.getiPesoSaidaTotal() * 100 / ordem.getiPesoEntradaTotal())) + "%");
		arrayServicos = servico.buscarServicoPorProduto(func.buscaIntBanco("tbProduto", "idProduto", "descricao", textProduto.getText()));
		cbServico.setModel(new DefaultComboBoxModel(arrayServicos));
		cbServico.setSelectedIndex(-1);
		lblFinalizado.setText(func.IfThen(ordem.isbFinalizado(), "Finalizado", "Aberto"));
	}

	public void setsIdsTickets(String sIds) throws SQLException {
		this.sIdsTickets = this.sIdsTickets + sIds;	
		arrayTicketBalancaSaida = ticBal.BuscarTicketsSaida(" and tb.idTicketBalanca in (" + sIds + ")");
		OrdemServico ordem = new OrdemServico();
		ordem = ordem.BuscarDadosTickets(sIdsTickets);
		if(ordem != null) {
			Funcoes.fecharTela();
			popularTabela();
			habilitarComponentes();
			
			if(ordem.getiIdCliente() > 0) {
				try {
					preencherCampos(ordem);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			preencherCampos(ordem);
		}		
	}

	private void habilitarComponentes() {
		if(sOperacaoTela.equals(INCLUIR_NOVA_ORDEM)) {
			btnConfirmar.setEnabled(true);
			btnCancelar.setEnabled(true);
			btnInserirTicket.setEnabled(true);
			btnExcluirTicket.setEnabled(true);	
			btnEncerrar.setEnabled(false);
			btnExcluir.setEnabled(false);
			btnBuscar.setEnabled(false);
			cbServico.setEnabled(true);
		}else if(sOperacaoTela.equals(ORDEM_NA_TELA)) {
			btnConfirmar.setEnabled(false);
			btnCancelar.setEnabled(false);
			btnInserirTicket.setEnabled(true);
			btnExcluirTicket.setEnabled(true);
			if(!ordem.isbFinalizado()) {btnEncerrar.setEnabled(true);}
			btnExcluir.setEnabled(true);
			btnBuscar.setEnabled(true);
			cbServico.setEnabled(false);
		}
		
	}

	private void popularTabela() {
		for (int i = 0; i < arrayTicketBalancaSaida.size(); i++) {
			String placaVeiculo = arrayTicketBalancaSaida.get(i).getsPlacaVeiculo();
			String Cliente  = arrayTicketBalancaSaida.get(i).getsNomeCliente();
			String Produto  = arrayTicketBalancaSaida.get(i).getsDescrProduto();
			String FuncEntrada  = arrayTicketBalancaSaida.get(i).getsNomeFuncEntrada();
			String nomeMotorista  = arrayTicketBalancaSaida.get(i).getsNomeMotorista();
			int pesoEntrada  = arrayTicketBalancaSaida.get(i).getiPesoEntrada(); 
			String observacao  = arrayTicketBalancaSaida.get(i).getsObservacao();
			String dataEntrada  = arrayTicketBalancaSaida.get(i).getdDataEntrada().toString();
			String horaEntrada  = arrayTicketBalancaSaida.get(i).gettHoraEntrada().toString();
			String dataSaida = arrayTicketBalancaSaida.get(i).getdDataSaida().toString();
			String horaSaida = arrayTicketBalancaSaida.get(i).gettHoraSaida().toString();
			int pesoSaida = arrayTicketBalancaSaida.get(i).getiPesoSaida();
			String operacao = arrayTicketBalancaSaida.get(i).getsOperacao();
			int idTicBal = arrayTicketBalancaSaida.get(i).getiIdTicketBalanca();
			//int idFuncSaida = arrayTicketBalancaSaida.get(i).getiIdFuncSaida();
				
			
			Object[] data = { idTicBal, placaVeiculo, Cliente, Produto, FuncEntrada, nomeMotorista, pesoEntrada
							, observacao, dataEntrada, horaEntrada, dataSaida, horaSaida, pesoSaida, operacao 
							};
			model_table.addRow(data);
		} 
		
	}

	public void retornaIdOrdem(String iIdOrdem) throws SQLException {
		ordem = new OrdemServico();
		ordem.setiIdOrdemServico(Integer.parseInt(iIdOrdem));
		ordem.BuscarOrdemPorID();
		sOperacaoTela = ORDEM_NA_TELA;
		habilitarComponentes();
		preencherCampos(ordem);
		
	}
	
	
}
