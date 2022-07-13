package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Classes.Funcoes;
import Classes.Produto;
import Classes.TicketBalanca;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telaListarTicketEntrada extends JFrame {

		
	private JPanel mainPanel;
	private JTable table;
	private DefaultTableModel model_table;
	private JScrollPane scroll_table;
	
	
	private JPanel contentPane;
	private JButton btnAtualizar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnSair; 
	
	TicketBalanca ticBal = new TicketBalanca();
	ArrayList<TicketBalanca> arrayTicketBalanca = ticBal.BuscarTicketsEntrada();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaListarTicketEntrada frame = new telaListarTicketEntrada();
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
	public telaListarTicketEntrada() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				Funcoes.fecharTela();
			}
		});
		
		
		mainPanel   = new JPanel(null);
		setSize(850, 500);
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null); //desativa edição dos campos
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
	
		setLocationRelativeTo(null);
		table.getTableHeader().setReorderingAllowed(false);
		
		model_table = new DefaultTableModel();
		model_table.addColumn("Placa"); 
		model_table.addColumn("Cliente"); 
		model_table.addColumn("Produto");
		model_table.addColumn("Func. Entrada");
		model_table.addColumn("Motorista");
		model_table.addColumn("Peso Entrada");
		model_table.addColumn("Observação");
		model_table.addColumn("Data entrada");
		model_table.addColumn("Hora entrada");
		// table.setAutoResizeMode(table.AUTO_RESIZE_OFF); scroll hori 
		table.setModel(model_table);
	
		for (int i = 0; i < arrayTicketBalanca.size(); i++) {
			String placaVeiculo = arrayTicketBalanca.get(i).getsPlacaVeiculo();
			String idCliente  = arrayTicketBalanca.get(i).getsNomeCliente();
			String idProduto  = arrayTicketBalanca.get(i).getsDescrProduto();
			String idFuncEntrada  = arrayTicketBalanca.get(i).getsNomeFuncEntrada();
			String nomeMotorista  = arrayTicketBalanca.get(i).getsNomeMotorista();
			int pesoEntrada  = arrayTicketBalanca.get(i).getiPesoEntrada(); 
			String observacao  = arrayTicketBalanca.get(i).getsObservacao();
			String dataEntrada  = arrayTicketBalanca.get(i).getdDataEntrada().toString();
			String horaEntrada  = arrayTicketBalanca.get(i).gettHoraEntrada().toString();
					
			
			Object[] data = {placaVeiculo, idCliente,idProduto,idFuncEntrada,nomeMotorista,pesoEntrada,observacao,dataEntrada,horaEntrada};
			model_table.addRow(data);
		} 

		scroll_table = new JScrollPane(table);


		scroll_table.setBounds(10, 96, 814, 354);
		scroll_table.setVisible(true);
		mainPanel.add(scroll_table);

		getContentPane().add(mainPanel);
		
		JLabel lblNewLabel_1 = new JLabel("Tabela Ticket Sa\u00EDda");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_1.setBounds(10, 11, 814, 40);
		mainPanel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Sair");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Funcoes.fecharTela();
			}
		});
		btnNewButton.setBounds(735, 62, 89, 23);
		mainPanel.add(btnNewButton);
	}
}
