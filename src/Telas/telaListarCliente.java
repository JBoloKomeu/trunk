package Telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Classes.Cliente;
import Classes.Funcoes;
import Dao.ClienteDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JMenuBar;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowFocusListener;

public class telaListarCliente extends JFrame {

	private JPanel mainPanel;
	private JTable table;
	private DefaultTableModel model_table;
	private JScrollPane scroll_table;
	private Funcoes func = new Funcoes();

	Cliente cli = new Cliente();
	ArrayList<Cliente> arrayClientes = cli.BuscarClientes();

	ClienteDAO cliDAO = new ClienteDAO(null);


	private JButton btnAtualizar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnSair;
	private JTextField pesquisaCPF;
	private JTextField pesquisaTelefone;
	private JTextField pesquisaBairro;
	private JTextField pesquisaCidade;
	private JTextField pesqId;
	private JTextField pesqTelefone;
	private JTextField pesqNome;
	private JTextField pesqBairro;
	private JTextField pesqCpf;
	private JTextField pesqCidade;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaListarCliente frame = new telaListarCliente();
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
	public telaListarCliente() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				atualizar();
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				atualizar();
			}
		});
		setResizable(false);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				Funcoes.fecharTela();
			}
		});
		mainPanel   = new JPanel(null);
		setSize(900, 606);

		table = new JTable();
		table.setDefaultEditor(Object.class, null); //desativa edição dos campos

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Arial", Font.PLAIN, 12));

		setLocationRelativeTo(null);

		model_table = new DefaultTableModel();
		model_table.addColumn("ID"); 
		model_table.addColumn("Nome"); 
		model_table.addColumn("CPF");
		model_table.addColumn("Telefone");
		model_table.addColumn("Bairro");
		model_table.addColumn("Cidade");
		table.setModel(model_table);
		table.setAutoCreateRowSorter(true);  //seta que organiza as colum de forma cresc ou decres
		TableRowSorter<TableModel> sort = new TableRowSorter<>(table.getModel());

		for (int i = 0; i < arrayClientes.size(); i++) {
			int id = arrayClientes.get(i).getiIdCliente();
			String nome  = arrayClientes.get(i).getsNomeCliente();
			String cpf  = arrayClientes.get(i).getsCpfCliente();
			String telefone  = arrayClientes.get(i).getsTelefoneCliente();
			String bairro  = arrayClientes.get(i).getsBairroCliente();
			String cidade  = arrayClientes.get(i).getsCidadeCliente();

			Object[] data = {id, nome, cpf, telefone, bairro, cidade};
			model_table.addRow(data);
		} 

		scroll_table = new JScrollPane(table);
		table.getColumn("ID").setMaxWidth(36);  //seta width do id
		table.getTableHeader().setReorderingAllowed(false); //desativa movimento coluna

		scroll_table.setBounds(10, 154, 864, 402);
		scroll_table.setVisible(true);
		mainPanel.add(scroll_table);

		getContentPane().add(mainPanel);

		table.addMouseListener(new MouseAdapter() {  //detecta campo que foi clicado 2x
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 2) {     // double click
					JTable target = (JTable)me.getSource();
					int row = table.getSelectedRow(); // row
					int column = table.getSelectedColumn(); //olumn
					JOptionPane.showMessageDialog(null, table.getValueAt(row, 0)); // valores selecionados
				}
			}
		});




		JLabel lblNewLabel_1 = new JLabel("Tabela Clientes");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 0, 864, 54);
		mainPanel.add(lblNewLabel_1);


		btnEditar = new JButton("Editar");
		btnEditar.setBounds(674, 65, 95, 20);
		mainPanel.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 14));

		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(569, 65, 95, 20);
		mainPanel.add(btnAtualizar);
		btnAtualizar.setFont(new Font("Arial", Font.PLAIN, 14));

		btnSair = new JButton("Sair");
		btnSair.setBounds(779, 123, 95, 20);
		mainPanel.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.fecharTela();
			}
		});
		btnSair.setFont(new Font("Arial", Font.PLAIN, 14));

		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(779, 65, 95, 20);
		mainPanel.add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (validarSelecao()) {
					int rowSelecionada = (table.getSelectedRow());
					String selected = table.getValueAt(rowSelecionada, 0).toString();
					JOptionPane.showMessageDialog(null, selected);
				}
			}
		});


		btnExcluir.setFont(new Font("Arial", Font.PLAIN, 14));
		
		pesqId = new JTextField();
		pesqId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				pesquisarTudo();
			}
		});
		pesqId.setFont(new Font("Arial", Font.BOLD, 10));
		pesqId.setColumns(10);
		pesqId.setBounds(10, 123, 100, 20);
		mainPanel.add(pesqId);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 102, 81, 20);
		mainPanel.add(lblNewLabel);
		
		pesqTelefone = new JTextField();
		pesqTelefone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarTudo();
			}
		});
		pesqTelefone.setFont(new Font("Arial", Font.BOLD, 10));
		pesqTelefone.setColumns(10);
		pesqTelefone.setBounds(340, 123, 100, 20);
		mainPanel.add(pesqTelefone);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTelefone.setBounds(340, 102, 81, 20);
		mainPanel.add(lblTelefone);
		
		pesqNome = new JTextField();
		pesqNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarTudo();
			}
		});
		pesqNome.setFont(new Font("Arial", Font.BOLD, 10));
		pesqNome.setColumns(10);
		pesqNome.setBounds(120, 123, 100, 20);
		mainPanel.add(pesqNome);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNome.setBounds(120, 102, 81, 20);
		mainPanel.add(lblNome);
		
		pesqBairro = new JTextField();
		pesqBairro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarTudo();
			}
		});
		pesqBairro.setFont(new Font("Arial", Font.BOLD, 10));
		pesqBairro.setColumns(10);
		pesqBairro.setBounds(454, 123, 100, 20);
		mainPanel.add(pesqBairro);
		
		JLabel lblNewLabel_2_1 = new JLabel("Bairro");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_2_1.setBounds(454, 102, 81, 20);
		mainPanel.add(lblNewLabel_2_1);
		
		pesqCpf = new JTextField();
		pesqCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarTudo();
			}
			
		});
		pesqCpf.setFont(new Font("Arial", Font.BOLD, 10));
		pesqCpf.setColumns(10);
		pesqCpf.setBounds(230, 123, 100, 20);
		mainPanel.add(pesqCpf);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCpf.setBounds(230, 102, 81, 20);
		mainPanel.add(lblCpf);
		
		pesqCidade = new JTextField();
		pesqCidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarTudo();
			}
		});
		pesqCidade.setFont(new Font("Arial", Font.BOLD, 10));
		pesqCidade.setColumns(10);
		pesqCidade.setBounds(559, 123, 100, 20);
		mainPanel.add(pesqCidade);
		
		JLabel lblNewLabel_2_2 = new JLabel("Cidade");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_2_2.setBounds(559, 102, 81, 20);
		mainPanel.add(lblNewLabel_2_2);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();
			}
		});



	}	

	private void pesquisar(String textoPesquisa) { //nome


		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model_table);
		TableRowSorter<DefaultTableModel> tr2 = new TableRowSorter<DefaultTableModel>(model_table);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter("(?i)" + textoPesquisa, 1)); //regexFilter("(?i)" + textoPesquisa, 1)) onde 1 qual coluna pesquisar, vazio para todas 
		
	}


	private void editar() {	
		if(validarSelecao()) {
			int rowSelecionada1 = (table.getSelectedRow());

			for(int i=0; i<=5; i++) {
				switch (i) {
				case 0:
					cli.setiIdCliente(Integer.parseInt(table.getValueAt(rowSelecionada1, 0).toString()));
				case 1:
					cli.setsNomeCliente(table.getValueAt(rowSelecionada1, 1).toString());
				case 2:
					cli.setsCpfCliente(table.getValueAt(rowSelecionada1, 2).toString());
				case 3:
					cli.setsTelefoneCliente(table.getValueAt(rowSelecionada1, 3).toString());
				case 4:
					cli.setsBairroCliente(table.getValueAt(rowSelecionada1, 4).toString());
				case 5:
					cli.setsCidadeCliente(table.getValueAt(rowSelecionada1, 5).toString());
				}
			}
			Funcoes.AbrirTela(new telaCliente(cli));
		}
		
	}

	private void atualizar() {
		ArrayList<Cliente> arrayClientes = cli.BuscarClientes();
		model_table.setRowCount(0);
		for (int i = 0; i < arrayClientes.size(); i++) {
			int id = arrayClientes.get(i).getiIdCliente();
			String nome  = arrayClientes.get(i).getsNomeCliente();
			String cpf  = arrayClientes.get(i).getsCpfCliente();
			String telefone  = arrayClientes.get(i).getsTelefoneCliente();
			String bairro  = arrayClientes.get(i).getsBairroCliente();
			String cidade  = arrayClientes.get(i).getsCidadeCliente();

			Object[] data = {id, nome, cpf, telefone, bairro, cidade};
			model_table.addRow(data);
		}
	}
	
	

	private boolean validarSelecao() {  //verefica se alguma row esta selecionada
		if( table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Nada selecionado!");
			return false;
		}
		return true;
	}
	
private void pesquisarTudo() { 
		
		final RowFilter<TableModel, Object> id = RowFilter.regexFilter(pesqId.getText(), 0);
		final RowFilter<TableModel, Object> nome = RowFilter.regexFilter(pesqNome.getText().toUpperCase(), 1);
		final RowFilter<TableModel, Object> cpf = RowFilter.regexFilter(pesqCpf.getText(), 2);
		final RowFilter<TableModel, Object> tel = RowFilter.regexFilter(pesqTelefone.getText(), 3);
		final RowFilter<TableModel, Object> bairro = RowFilter.regexFilter(pesqBairro.getText().toUpperCase(), 4);
		final RowFilter<TableModel, Object> cidade = RowFilter.regexFilter(pesqCidade.getText().toUpperCase(), 5);


		
		final List<RowFilter<TableModel, Object>> filters = new ArrayList<RowFilter<TableModel, Object>>();
		filters.add(id);
		filters.add(nome);
		filters.add(cpf );
		filters.add(tel);
		filters.add(bairro);
		filters.add(cidade);

			
		final RowFilter<TableModel, Object>  compoundRowFilter = RowFilter.andFilter(filters);
		final TableRowSorter sorter = new TableRowSorter<TableModel>(model_table);
		sorter.setRowFilter(compoundRowFilter);
		table.setRowSorter(sorter);
	}
}


