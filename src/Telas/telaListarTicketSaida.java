package Telas;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Classes.Funcoes;
import Classes.Relatorios;
import Classes.TicketBalanca;
import javafx.scene.control.DatePicker;
import net.sf.jasperreports.engine.JRException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;

public class telaListarTicketSaida extends JFrame {

	private JPanel contentPane;
	private JPanel mainPanel;
	private JTable table;
	private DefaultTableModel model_table;
	private JScrollPane scroll_table;
	
	private JButton btnAtualizar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnSair;
	private String sFiltros;
	
	TicketBalanca ticBal = new TicketBalanca();
	ArrayList<TicketBalanca> arrayTicketBalancaSaida = ticBal.BuscarTicketsSaida("");
	private JTextField pesqId;
	private JTextField pesqPlaca;
	private JTextField pesqProduto;
	private JTextField pesqCliente;
	private JTextField pesqMotorista;
	private JTextField pesqFuncionarioEntrada;
	private JTextField pesqPesoSaida;
	private JTextField pesqPesoEntrada;
	private JTextField pesqDataSaida;
	private JTextField pesqDataEntrada;
	private JTextField pesqObservacao;
	private JTextField pesqHoraEntrada;
	private JTextField pesqHoraSaida;
	private JTextField pesqFuncionarioSaida;
	private JComboBox  comboBoxOperacao;
	private String op = "";
	private Funcoes func = new Funcoes();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaListarTicketSaida frame = new telaListarTicketSaida(null, "");
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
	public telaListarTicketSaida(telaOrdem frame, String sIds) {
		String sIdsTickets = sIds;
		
		setResizable(false);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				Funcoes.fecharTela();
			}
		});
		
		DateFormat format = new SimpleDateFormat("yyyy--MMMM--dd");
		mainPanel   = new JPanel(null);
		setSize(1008, 603);
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null); //desativa edição dos campos
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setAutoCreateRowSorter(true);
	
		setLocationRelativeTo(null);
		
		model_table = new DefaultTableModel();
		model_table.addColumn("ID");	
		model_table.addColumn("Placa"); 
		model_table.addColumn("Cliente"); 
		model_table.addColumn("Produto");
		model_table.addColumn("Func. Entrada");
		model_table.addColumn("Func. Saida");
		model_table.addColumn("Motorista");
		model_table.addColumn("Operação"); //
		model_table.addColumn("Data Entrada");
		model_table.addColumn("Hora Entrada");
		model_table.addColumn("Data Saída");
		model_table.addColumn("Hora Saida");
		model_table.addColumn("Peso Entrada");
		model_table.addColumn("Peso Saída");		
		model_table.addColumn("Observação");//
		table.setModel(model_table);

		popularTabela();
		
		table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
		table.getColumn("ID").setMaxWidth(30);
		table.getColumn("Placa").setMaxWidth(72);
		table.getColumn("Cliente").setMinWidth(100);
		table.getColumn("Data Entrada").setMinWidth(60);
		table.getColumn("Data Saída").setMinWidth(60);
		table.getColumn("Observação").setMinWidth(100);
		table.getColumn("Motorista").setMinWidth(100);
		table.getColumn("Produto").setMinWidth(100);
		table.getColumn("Func. Entrada").setMinWidth(100);
		
		scroll_table = new JScrollPane(table);

		scroll_table.setBounds(10, 199, 972, 354);
		scroll_table.setVisible(true);
		mainPanel.add(scroll_table);

		getContentPane().add(mainPanel);
		
		if(frame == null) {
			JButton btnImprimir = new JButton("Imprimir Ticket");
			btnImprimir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(validarSelecao()) {
						Relatorios rel = new Relatorios();
						try {
							rel.imprimirRelatorioTicketSaida((int) table.getValueAt(table.getSelectedRow(), 0));
						} catch (ClassNotFoundException | JRException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			btnImprimir.setFont(new Font("Arial", Font.PLAIN, 16));
			btnImprimir.setBounds(783, 106, 199, 23);
			mainPanel.add(btnImprimir);
		}else{
			table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			JButton btnConfirmar = new JButton("Confirmar");
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String sIds = sIdsTickets;
                    int[] selecao = table.getSelectedRows();
                    for(int i=0; i < selecao.length; i ++) {
                        if(sIds.equals("")) {
                            sIds = String.valueOf(table.getValueAt(selecao[i], 0));
                        }else {
                            sIds = sIds + "," + String.valueOf(table.getValueAt(selecao[i], 0));
                        }
                    }
                    try {
						frame.setsIdsTickets(sIds);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 16));
			btnConfirmar.setBounds(783, 106, 199, 23);
			mainPanel.add(btnConfirmar);
			
		}
			
		
		JButton btnSair_1 = new JButton("Sair");
		btnSair_1.setFont(new Font("Arial", Font.PLAIN, 16));
		btnSair_1.setBounds(893, 165, 89, 23);
		mainPanel.add(btnSair_1);
		
		JLabel lblNewLabel_1 = new JLabel("Tabela Ticket Sa\u00EDda");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_1.setBounds(10, 11, 972, 40);
		mainPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 89, 81, 20);
		mainPanel.add(lblNewLabel);
		
		pesqId = new JTextField();
		pesqId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				pesquisarTudo();
			}
		});
		pesqId.setFont(new Font("Arial", Font.BOLD, 10));
		pesqId.setBounds(10, 110, 100, 20);
		mainPanel.add(pesqId);
		pesqId.setColumns(10);
		
		pesqPlaca = new JTextField();
		pesqPlaca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarTudo();
			}
		});
		pesqPlaca.setFont(new Font("Arial", Font.PLAIN, 13));
		pesqPlaca.setColumns(10);
		pesqPlaca.setBounds(10, 168, 100, 20);
		mainPanel.add(pesqPlaca);
		
		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPlaca.setBounds(10, 147, 81, 20);
		mainPanel.add(lblPlaca);
		
		pesqProduto = new JTextField();
		pesqProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarTudo();
			}
		});
		pesqProduto.setFont(new Font("Arial", Font.PLAIN, 13));
		pesqProduto.setColumns(10);
		pesqProduto.setBounds(120, 168, 100, 20);
		mainPanel.add(pesqProduto);
		
		pesqCliente = new JTextField();
		pesqCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarTudo();
			}
		});
		pesqCliente.setFont(new Font("Arial", Font.BOLD, 10));
		pesqCliente.setColumns(10);
		pesqCliente.setBounds(120, 110, 100, 20);
		mainPanel.add(pesqCliente);
		
		JLabel lblNomeCliente = new JLabel("Cliente");
		lblNomeCliente.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNomeCliente.setBounds(120, 89, 81, 20);
		mainPanel.add(lblNomeCliente);
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setFont(new Font("Arial", Font.PLAIN, 13));
		lblProduto.setBounds(120, 147, 81, 20);
		mainPanel.add(lblProduto);
		
		pesqMotorista = new JTextField();
		pesqMotorista.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarTudo();
			}
		});
		pesqMotorista.setFont(new Font("Arial", Font.PLAIN, 13));
		pesqMotorista.setColumns(10);
		pesqMotorista.setBounds(670, 168, 100, 20);
		mainPanel.add(pesqMotorista);
		
		pesqFuncionarioEntrada = new JTextField();
		pesqFuncionarioEntrada.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarTudo();
			}
		});
		pesqFuncionarioEntrada.setFont(new Font("Arial", Font.BOLD, 10));
		pesqFuncionarioEntrada.setColumns(10);
		pesqFuncionarioEntrada.setBounds(230, 110, 100, 20);
		mainPanel.add(pesqFuncionarioEntrada);
		
		JLabel lblFuncionario = new JLabel("Func. Entrada");
		lblFuncionario.setFont(new Font("Arial", Font.PLAIN, 13));
		lblFuncionario.setBounds(230, 89, 100, 20);
		mainPanel.add(lblFuncionario);
		
		JLabel lblMotorista = new JLabel("Motorista");
		lblMotorista.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMotorista.setBounds(670, 147, 81, 20);
		mainPanel.add(lblMotorista);
		
		pesqPesoSaida = new JTextField();
		pesqPesoSaida.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarTudo();
			}
		});
		pesqPesoSaida.setFont(new Font("Arial", Font.PLAIN, 13));
		pesqPesoSaida.setColumns(10);
		pesqPesoSaida.setBounds(340, 168, 100, 20);
		mainPanel.add(pesqPesoSaida);
		
		pesqPesoEntrada = new JTextField();
		pesqPesoEntrada.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarTudo();
			}
		});
		pesqPesoEntrada.setFont(new Font("Arial", Font.BOLD, 10));
		pesqPesoEntrada.setColumns(10);
		pesqPesoEntrada.setBounds(340, 110, 100, 20);
		mainPanel.add(pesqPesoEntrada);
		
		JLabel lblPesoEntrada = new JLabel("Peso Entrada");
		lblPesoEntrada.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPesoEntrada.setBounds(340, 89, 100, 20);
		mainPanel.add(lblPesoEntrada);
		
		JLabel lblPesoSada = new JLabel("Peso Sa\u00EDda");
		lblPesoSada.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPesoSada.setBounds(340, 147, 100, 20);
		mainPanel.add(lblPesoSada);
		
		pesqDataSaida = new JTextField();
		pesqDataSaida.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(pesqDataSaida.getText().length() > 10 ) {
					pesqDataSaida.setText(pesqDataSaida.getText().substring(0, 10));
				}else if((pesqDataSaida.getText().length() == 2 || pesqDataSaida.getText().length() == 5) && e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
					pesqDataSaida.setText(pesqDataSaida.getText().concat("-"));
		        }else if(pesqDataSaida.getText().length() == 10 || pesqDataSaida.getText().length() == 0){
		        	pesquisarTudo();
		        }
			}
		});
		pesqDataSaida.setFont(new Font("Arial", Font.PLAIN, 13));
		pesqDataSaida.setColumns(10);
		pesqDataSaida.setBounds(450, 168, 100, 20);
		mainPanel.add(pesqDataSaida);
		
		
		pesqDataEntrada = new JTextField();
		pesqDataEntrada.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(pesqDataEntrada.getText().length() > 10 ) {
					pesqDataEntrada.setText(pesqDataEntrada.getText().substring(0, 10));
				}else if((pesqDataEntrada.getText().length() == 2 || pesqDataEntrada.getText().length() == 5) && e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
					pesqDataEntrada.setText(pesqDataEntrada.getText().concat("-"));
		        }else if(pesqDataEntrada.getText().length() == 10 || pesqDataEntrada.getText().length() == 0){
		        	pesquisarTudo();
		        }			
			}
		});
		pesqDataEntrada.setFont(new Font("Arial", Font.BOLD, 10));
		pesqDataEntrada.setColumns(10);
		pesqDataEntrada.setBounds(450, 110, 100, 20);
		mainPanel.add(pesqDataEntrada);

		
		JLabel lblDataEntrada = new JLabel("Data Entrada");
		lblDataEntrada.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDataEntrada.setBounds(450, 89, 100, 20);
		mainPanel.add(lblDataEntrada);
		
		JLabel lblDataSada = new JLabel("Data Sa\u00EDda");
		lblDataSada.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDataSada.setBounds(450, 147, 81, 20);
		mainPanel.add(lblDataSada);
		
		pesqObservacao = new JTextField();
		pesqObservacao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarTudo();
			}
		});

		pesqObservacao.setFont(new Font("Arial", Font.PLAIN, 13));
		pesqObservacao.setColumns(10);
		pesqObservacao.setBounds(783, 167, 100, 20);
		mainPanel.add(pesqObservacao);
		
		JLabel lblOperacao = new JLabel("Opera\u00E7\u00E3o");
		lblOperacao.setFont(new Font("Arial", Font.PLAIN, 13));
		lblOperacao.setBounds(670, 89, 81, 20);
		mainPanel.add(lblOperacao);
		
		JLabel lblObservao = new JLabel("Observa\u00E7\u00E3o");
		lblObservao.setFont(new Font("Arial", Font.PLAIN, 13));
		lblObservao.setBounds(783, 147, 100, 20);
		mainPanel.add(lblObservao);
		
			
		JComboBox comboBoxOperacao = new JComboBox();
		comboBoxOperacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op = comboBoxOperacao.getEditor().getItem().toString();
				if(op.equals("Todos")){
					op = "";				
				}
				pesquisarTudo();
			}
		});
		comboBoxOperacao.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Entrada", "Saida"}));
		comboBoxOperacao.setFont(new Font("Arial", Font.PLAIN, 13));
		comboBoxOperacao.setBounds(670, 109, 100, 22);
		
		mainPanel.add(comboBoxOperacao);
		
		pesqHoraEntrada = new JTextField();
		pesqHoraEntrada.setFont(new Font("Arial", Font.PLAIN, 13));
		pesqHoraEntrada.setColumns(10);
		pesqHoraEntrada.setBounds(560, 110, 100, 20);
		mainPanel.add(pesqHoraEntrada);
		
		JLabel lblHoraEntrada = new JLabel("Hora Entrada");
		lblHoraEntrada.setFont(new Font("Arial", Font.PLAIN, 13));
		lblHoraEntrada.setBounds(560, 89, 100, 20);
		mainPanel.add(lblHoraEntrada);
		
		JLabel lblHorasaida = new JLabel("Hora Sa\u00EDda");
		lblHorasaida.setFont(new Font("Arial", Font.PLAIN, 13));
		lblHorasaida.setBounds(560, 147, 81, 20);
		mainPanel.add(lblHorasaida);
		
		pesqHoraSaida = new JTextField();
		pesqHoraSaida.setFont(new Font("Arial", Font.PLAIN, 13));
		pesqHoraSaida.setColumns(10);
		pesqHoraSaida.setBounds(560, 168, 100, 20);
		mainPanel.add(pesqHoraSaida);
		
		JLabel lblFuncionarioSada = new JLabel("Func. Sa\u00EDda");
		lblFuncionarioSada.setFont(new Font("Arial", Font.PLAIN, 13));
		lblFuncionarioSada.setBounds(230, 147, 81, 20);
		mainPanel.add(lblFuncionarioSada);
		
		pesqFuncionarioSaida = new JTextField();
		pesqFuncionarioSaida.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarTudo();
			}
		});
		pesqFuncionarioSaida.setFont(new Font("Arial", Font.PLAIN, 13));
		pesqFuncionarioSaida.setColumns(10);
		pesqFuncionarioSaida.setBounds(230, 168, 100, 20);
		mainPanel.add(pesqFuncionarioSaida);
	}
	
	
	private void popularTabela() {
		for (int i = 0; i < arrayTicketBalancaSaida.size(); i++) {
			int idTicBal = arrayTicketBalancaSaida.get(i).getiIdTicketBalanca();
			String placaVeiculo = arrayTicketBalancaSaida.get(i).getsPlacaVeiculo();
			String Cliente  = arrayTicketBalancaSaida.get(i).getsNomeCliente();
			String Produto  = arrayTicketBalancaSaida.get(i).getsDescrProduto();
			String FuncEntrada  = arrayTicketBalancaSaida.get(i).getsNomeFuncEntrada();
			String FuncSaida = arrayTicketBalancaSaida.get(i).getsNomeFuncSaida();
			String nomeMotorista  = arrayTicketBalancaSaida.get(i).getsNomeMotorista(); 
			String observacao  = arrayTicketBalancaSaida.get(i).getsObservacao();
			String dataEntrada  = func.inverterData(arrayTicketBalancaSaida.get(i).getdDataEntrada().toString());
			String horaEntrada  = arrayTicketBalancaSaida.get(i).gettHoraEntrada().toString();
			String dataSaida = func.inverterData(arrayTicketBalancaSaida.get(i).getdDataSaida().toString());
			String horaSaida = arrayTicketBalancaSaida.get(i).gettHoraSaida().toString();
			int pesoSaida = arrayTicketBalancaSaida.get(i).getiPesoSaida();
			int pesoEntrada  = arrayTicketBalancaSaida.get(i).getiPesoEntrada();
			String operacao = arrayTicketBalancaSaida.get(i).getsOperacao();
			int idFuncSaida = arrayTicketBalancaSaida.get(i).getiIdFuncSaida();
			
			Object[] data = { idTicBal, placaVeiculo, Cliente,Produto,FuncEntrada,FuncSaida,nomeMotorista
							,operacao ,dataEntrada,horaEntrada, dataSaida
							, horaSaida,pesoSaida,pesoEntrada, observacao, idFuncSaida 
							};
			model_table.addRow(data);
		} 
	}
	
	private void pesquisarTudo() { 
		
		final RowFilter<TableModel, Object> id = RowFilter.regexFilter(pesqId.getText(), 0);
		final RowFilter<TableModel, Object> placa = RowFilter.regexFilter(pesqPlaca.getText().toUpperCase(), 1);
		final RowFilter<TableModel, Object> nomeCli = RowFilter.regexFilter(pesqCliente.getText().toUpperCase(), 2);
		final RowFilter<TableModel, Object> produto = RowFilter.regexFilter(pesqProduto.getText().toUpperCase(), 3);
		final RowFilter<TableModel, Object> funcEntrada = RowFilter.regexFilter(pesqFuncionarioEntrada.getText().toUpperCase(), 4);
		final RowFilter<TableModel, Object> funcSaida = RowFilter.regexFilter(pesqFuncionarioSaida.getText().toUpperCase(), 5);
		final RowFilter<TableModel, Object> motorista = RowFilter.regexFilter(pesqMotorista.getText().toUpperCase(), 6);
		final RowFilter<TableModel, Object> operacao = RowFilter.regexFilter(op.toUpperCase(), 7);
		final RowFilter<TableModel, Object> dataEntrada = RowFilter.regexFilter(pesqDataEntrada.getText(), 8);
		final RowFilter<TableModel, Object> horaEntrada = RowFilter.regexFilter(pesqHoraEntrada.getText(), 9);
		final RowFilter<TableModel, Object> dataSaida = RowFilter.regexFilter(pesqDataSaida.getText(), 10);
		final RowFilter<TableModel, Object> horaSaida = RowFilter.regexFilter(pesqHoraSaida.getText(), 11);
		final RowFilter<TableModel, Object> pesoEntrada = RowFilter.regexFilter(pesqPesoEntrada.getText(), 12);
		final RowFilter<TableModel, Object> pesoSaida = RowFilter.regexFilter(pesqPesoSaida.getText(), 13);
		final RowFilter<TableModel, Object> obs = RowFilter.regexFilter(pesqObservacao.getText().toUpperCase(), 14);
		
		final List<RowFilter<TableModel, Object>> filters = new ArrayList<RowFilter<TableModel, Object>>();
		filters.add(id);
		filters.add(placa);
		filters.add(nomeCli );
		filters.add(produto);
		filters.add(funcEntrada);
		filters.add(funcSaida);
		filters.add(motorista);
		filters.add(operacao);
		filters.add(dataEntrada);
		filters.add(horaEntrada);
		filters.add(dataSaida);
		filters.add(horaSaida);
		filters.add(pesoEntrada);
		filters.add(pesoSaida);
		filters.add(obs);
		
		
		final RowFilter<TableModel, Object>  compoundRowFilter = RowFilter.andFilter(filters);
		final TableRowSorter sorter = new TableRowSorter<TableModel>(model_table);
		sorter.setRowFilter(compoundRowFilter);
		table.setRowSorter(sorter);
	}
	
	private boolean validarSelecao() {  //verefica se alguma row esta selecionada
		if( table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Nada selecionado!");
			return false;
		}
		return true;
	}
}
