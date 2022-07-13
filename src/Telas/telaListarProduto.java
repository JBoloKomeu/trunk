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
import java.awt.print.PrinterException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Classes.Funcoes;
import Classes.Produto;

public class telaListarProduto extends JFrame {
	
	
	private JPanel mainPanel;
	private JTable table;
	private DefaultTableModel model_table;
	private JScrollPane scroll_table;
	
	
	private JPanel contentPane;
	private JButton btnAtualizar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnSair;
	
	
	Produto pro = new Produto();
	ArrayList<Produto> arrayProduto = pro.BuscarProdutos("");
	private JTable table_1;
	private JTable table_2;
	private JButton btnNewButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaListarProduto frame = new telaListarProduto(null);
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
	public telaListarProduto(telaServicos frame) {
		setResizable(false);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				Funcoes.fecharTela();
			}
		});
		
		
		mainPanel   = new JPanel(null);
		setSize(446, 500);
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null); //desativa edição dos campos
		
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
	
		setLocationRelativeTo(null);
		
		model_table = new DefaultTableModel();
		model_table.addColumn("ID"); 
		model_table.addColumn("Descrição"); 
		table.setModel(model_table);
		table.setAutoCreateRowSorter(true);  //seta que organiza as colum de forma cresc ou decres
		
		//TableRowSorter<TableModel> sort = new TableRowSorter<>(table.getModel());

		for (int i = 0; i < arrayProduto.size(); i++) {
			int id = arrayProduto.get(i).getiIdProduto();
			String desc  = arrayProduto.get(i).getsDescricaoProduto();
			
			Object[] data = {id, desc};
			model_table.addRow(data);
		} 

		scroll_table = new JScrollPane(table);
		table.getColumn("ID").setMaxWidth(50);  //seta width do id]
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);



		scroll_table.setBounds(10, 114, 410, 336);
		scroll_table.setVisible(true);
		mainPanel.add(scroll_table);
		getContentPane().add(mainPanel);
		
		
		JLabel lblNewLabel_1 = new JLabel("Tabela Produtos");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 11, 410, 54);
		mainPanel.add(lblNewLabel_1);
		btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Arial", Font.PLAIN, 16));
		//btnSair.setBounds(326, 83, 94, 20);
		mainPanel.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.fecharTela();
			}
		});
		
		
		if (frame == null) {
		
			btnSair.setBounds(326, 83, 94, 20); //bounds sair
			btnEditar = new JButton("Editar");
			btnEditar.setFont(new Font("Arial", Font.PLAIN, 14));
			btnEditar.setBounds(118, 83, 94, 20);
			mainPanel.add(btnEditar);
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnAtualizar = new JButton("Atualizar");
			btnAtualizar.setBounds(10, 83, 94, 20);
			mainPanel.add(btnAtualizar);
			btnAtualizar.setFont(new Font("Arial", Font.PLAIN, 14));
			
			
			btnExcluir = new JButton("Excluir");
			btnExcluir.setFont(new Font("Arial", Font.PLAIN, 14));
			btnExcluir.setBounds(222, 83, 94, 20);
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
		}else {
			btnSair.setBounds(326, 83, 94, 20);
			btnNewButton = new JButton("Confirmar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {				
					String sIds = "";
                    int[] selecao = table.getSelectedRows();
                    for(int i=0; i < selecao.length; i ++) {
                        if(sIds.equals("")) {
                            sIds = String.valueOf(table.getValueAt(selecao[i], 0));
                        }else {
                            sIds = sIds + "," + String.valueOf(table.getValueAt(selecao[i], 0));
                        }
                    }
                    frame.setsIdsProdutos(sIds);
				}
			});
			btnNewButton.setFont(new Font("Arial", Font.PLAIN, 16));
			btnNewButton.setBounds(10, 83, 124, 20);
			mainPanel.add(btnNewButton);
		}
		
	
	}
	
	private boolean validarSelecao() {  //verefica se alguma row esta selecionada
		if( table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Nada selecionado!");
			return false;
		}
		return true;
	}
}
