package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Classes.Funcionario;
import Classes.Funcoes;

import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class telaListarFuncionario extends JFrame {

	private JPanel contentPane;
	private JPanel mainPanel;
	private JTable table;
	private DefaultTableModel model_table;
	private JScrollPane scroll_table;
	
	Funcionario fun = new Funcionario();
	ArrayList<Funcionario> arrayFuncionario = fun.BuscarFuncionarios();
	private JTextField pesquisaNome;
	
	private JButton btnAtualizar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnSair;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaListarFuncionario frame = new telaListarFuncionario();
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
	public telaListarFuncionario() {
		setResizable(false);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				Funcoes.fecharTela();
			}
		});
		
		mainPanel   = new JPanel(null);
		setSize(550, 500);
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null); //desativa edição dos campos
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
	
		setLocationRelativeTo(null);
		
		model_table = new DefaultTableModel();
		model_table.addColumn("ID"); 
		model_table.addColumn("Nome"); 
		model_table.addColumn("CPF");
		if(Funcoes.admFuncLogado) {
			model_table.addColumn("Usuário"); 
			model_table.addColumn("Administrador"); 
		}		
		table.setModel(model_table);
		table.setAutoCreateRowSorter(true);  //seta que organiza as colum de forma cresc ou decres
	
		//TableRowSorter<TableModel> sort = new TableRowSorter<>(table.getModel());

		for (int i = 0; i < arrayFuncionario.size(); i++) {
			
			int id = arrayFuncionario.get(i).getiIdFuncionario();
			String nome  = arrayFuncionario.get(i).getsNomeFuncionario();
			String cpf  = arrayFuncionario.get(i).getsCpfFuncionario();
			if(Funcoes.admFuncLogado) {
				String usuario = arrayFuncionario.get(i).getsUsuario();
				String administrador = Funcoes.IfThen(arrayFuncionario.get(i).getbAdmin(),"Sim","Não");
				Object[] data = {id, nome, cpf, usuario, administrador};
				model_table.addRow(data);
			}else {
				Object[] data = {id, nome, cpf};
				model_table.addRow(data);
			}
		} 

		scroll_table = new JScrollPane(table);
		table.getColumn("ID").setMaxWidth(36);  //seta width do id


		scroll_table.setBounds(10, 96, 513, 354);
		scroll_table.setVisible(true);
		mainPanel.add(scroll_table);

		getContentPane().add(mainPanel);
		
		table.addMouseListener(new MouseAdapter() {  //detecta campo que foi clicado 2x
	         public void mouseClicked(MouseEvent me) {
	             if (me.getClickCount() == 2) {     // double click
	                JTable target = (JTable)me.getSource();
	                int row = table.getSelectedRow(); // row
	                int column = table.getSelectedColumn(); //olumn
	                JOptionPane.showMessageDialog(null, table.getValueAt(row, column)); // valores selecionados
	             }
	          }
	       });
		
		JLabel lblNewLabel_1 = new JLabel("Tabela Funcionarios");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 534, 54);
		mainPanel.add(lblNewLabel_1);
		
		
		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEditar.setBounds(218, 65, 95, 20);
		mainPanel.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//editar();
			}
		});
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(113, 65, 95, 20);
		mainPanel.add(btnAtualizar);
		btnAtualizar.setFont(new Font("Arial", Font.PLAIN, 14));

		btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Arial", Font.PLAIN, 14));
		btnSair.setBounds(428, 65, 95, 20);
		mainPanel.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.fecharTela();
			}
		});
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Arial", Font.PLAIN, 14));
		btnExcluir.setBounds(323, 65, 95, 20);
		mainPanel.add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (validarSelecao()) {
					int rowSelecionada = (table.getSelectedRow());
					String selected = model_table.getValueAt(rowSelecionada, 0).toString();
					JOptionPane.showMessageDialog(null, selected);
				}

			}
		});
		
		
	}
	

	
	private boolean validarSelecao() {  //verefica se alguma row esta selecionada
		if( table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Nada selecionado!");
			return false;
		}
		return true;
	}
	
}

