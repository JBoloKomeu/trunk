package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Classes.Cliente;
import Classes.Funcoes;
import Classes.OrdemServico;
import Classes.Relatorios;
import net.sf.jasperreports.engine.JRException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class telaListarOrdem extends JFrame {

	private JPanel contentPane;
	private JPanel mainPanel;
	private JTable table;
	private DefaultTableModel model_table;
	private JScrollPane scroll_table;
	private Funcoes func = new Funcoes();
	DecimalFormat numberFormat = new DecimalFormat("#.00");
	OrdemServico ord = new OrdemServico();
	ArrayList<OrdemServico> arrayOrdens = ord.ListarOrdens();
	private JButton btnImprimir;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaListarOrdem frame = new telaListarOrdem();
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
	public telaListarOrdem() {
		setResizable(false);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				Funcoes.fecharTela();
			}
		});
		mainPanel   = new JPanel(null);
		setSize(750, 500);

		table = new JTable();
		table.setDefaultEditor(Object.class, null); //desativa edição dos campos

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Arial", Font.PLAIN, 12));

		setLocationRelativeTo(null);

		model_table = new DefaultTableModel();
		model_table.addColumn("ID"); 
		model_table.addColumn("Nome Cli.");
		model_table.addColumn("Peso Ent."); 
		model_table.addColumn("Peso Sai.");
		model_table.addColumn("Quebra");
		model_table.addColumn("Finalizado");
		model_table.addColumn("Serviço");
		model_table.addColumn("Quant. Tic. Ent.");
		model_table.addColumn("Quant. Tic. Sai.");
		model_table.addColumn("Observação");
		table.setModel(model_table);
		table.setAutoCreateRowSorter(true);  //seta que organiza as colum de forma cresc ou decres
		TableRowSorter<TableModel> sort = new TableRowSorter<>(table.getModel());
		
		
		for (int i = 0; i < arrayOrdens.size(); i++) {
			int id = arrayOrdens.get(i).getiIdOrdemServico();
			String nomeCli  = arrayOrdens.get(i).getsNomeCliente();
			int pesoEnt  = arrayOrdens.get(i).getiPesoEntradaTotal();
			int pesoSai  = arrayOrdens.get(i).getiPesoSaidaTotal();
			double porQue  = arrayOrdens.get(i).getfPorcQuebra();
			String finalizado  = arrayOrdens.get(i).getsFinalizado();		
			String servico  = arrayOrdens.get(i).getsServico();
			int qtTicketEntrada  = arrayOrdens.get(i).getiQtTicketEntrada();
			int qtTicketSaida  = arrayOrdens.get(i).getiQtTicketSaida();
			String obs  = arrayOrdens.get(i).getsObservacao();
			
			Object[] data = {id,nomeCli, pesoEnt+"Kg", pesoSai+"Kg", numberFormat.format(porQue)+"%", finalizado, servico, qtTicketEntrada, qtTicketSaida, obs};
			model_table.addRow(data);
		}
		table.addMouseListener(new MouseAdapter() {  //detecta campo que foi clicado 2x
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 2) {     // double click
					JTable target = (JTable)me.getSource();
					int row = table.getSelectedRow(); // row
					int column = table.getSelectedColumn(); //olumn
					Relatorios rel = new Relatorios();
					try {
						rel.imprimirRelatorioOrdemServico((int) table.getValueAt(row, 0));
					} catch (ClassNotFoundException | JRException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});

		scroll_table = new JScrollPane(table);
		table.getColumn("ID").setMaxWidth(36);  //seta width do id
		table.getColumn("Finalizado").setMaxWidth(70);
		table.getColumn("Quebra").setMaxWidth(50);
		table.getColumn("Quant. Tic. Ent.").setMinWidth(85);
		table.getColumn("Quant. Tic. Sai.").setMinWidth(85);
		table.getColumn("Observação").setMinWidth(100);
		table.getTableHeader().setReorderingAllowed(false); //desativa movimento coluna

		scroll_table.setBounds(10, 96, 714, 354);
		scroll_table.setVisible(true);
		mainPanel.add(scroll_table);

		getContentPane().add(mainPanel);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
		});
		btnImprimir.setFont(new Font("Arial", Font.PLAIN, 14));
		btnImprimir.setBounds(490, 62, 89, 23);
		mainPanel.add(btnImprimir);
		
		JLabel lblNewLabel_1 = new JLabel("Tabela Ordem Servi\u00E7o");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_1.setBounds(10, 11, 714, 40);
		mainPanel.add(lblNewLabel_1);
		
		JButton btnSair = new JButton("Sair\r\n");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Funcoes.fecharTela();
			}
		});
		btnSair.setFont(new Font("Arial", Font.PLAIN, 14));
		btnSair.setBounds(635, 62, 89, 23);
		mainPanel.add(btnSair);
	}
}
