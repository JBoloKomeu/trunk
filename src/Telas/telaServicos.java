package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Classes.Funcoes;
import Classes.Produto;
import Classes.Servico;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telaServicos extends JFrame {

	private JPanel contentPane;
	private JPanel mainPanel;
	private JTable table;
	private DefaultTableModel model_table;
	private JScrollPane scroll_table;
	
	
	Produto pro = new Produto();
	ArrayList<Produto> arrayProduto = new ArrayList<Produto>();
	private JButton btnAdicionar;
	private JButton btnExcluir;
	private JLabel lblQuantidakg;
	private JTextField textValor;
	private JTextField textDescricao;
	private JLabel lblDescrio;
	private JLabel lblValor;
	private JLabel lblNewLabel;
	private JTextField textQtde;
	private JButton btnSair;
	private JButton btnIncluir;
	private JButton btnConfirmar;
	private String sIdsProdutos;
	
	public void setsIdsProdutos(String sIdsProdutos) {
		this.sIdsProdutos = sIdsProdutos;
		arrayProduto = pro.BuscarProdutos(sIdsProdutos);
		Funcoes.fecharTela();
		popularTabela();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaServicos frame = new telaServicos();
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
	public telaServicos() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				Funcoes.fecharTela();
			}
		});
		
		
		mainPanel   = new JPanel(null);
		setSize(566, 500);
		
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
		table.getTableHeader().setReorderingAllowed(false);
		popularTabela();

		scroll_table = new JScrollPane(table);
		scroll_table.setBounds(10, 191, 410, 259);
		table.getColumn("ID").setMaxWidth(50);
		scroll_table.setVisible(true);
		mainPanel.setLayout(null);
		mainPanel.add(scroll_table);

		getContentPane().add(mainPanel);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setEnabled(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscarProdutos();
			}
		});
		btnAdicionar.setBounds(430, 191, 109, 23);
		btnAdicionar.setFont(new Font("Arial", Font.PLAIN, 16));
		mainPanel.add(btnAdicionar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(430, 225, 109, 23);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, sIdsProdutos);
			}
		});
		btnExcluir.setFont(new Font("Arial", Font.PLAIN, 16));
		mainPanel.add(btnExcluir);
		
		lblQuantidakg = new JLabel("Quantidade (Kg):");
		lblQuantidakg.setBounds(261, 150, 159, 30);
		lblQuantidakg.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuantidakg.setFont(new Font("Arial", Font.PLAIN, 20));
		mainPanel.add(lblQuantidakg);
		
		textValor = new JTextField();
		textValor.setEnabled(false);
		textValor.setBounds(120, 156, 109, 20);
		textValor.setToolTipText("Heyeyy");
		textValor.setFont(new Font("Arial", Font.PLAIN, 16));
		textValor.setColumns(10);
		mainPanel.add(textValor);
		
		textDescricao = new JTextField();
		textDescricao.setEnabled(false);
		textDescricao.setBounds(120, 116, 419, 20);
		textDescricao.setToolTipText("Heyeyy");
		textDescricao.setFont(new Font("Arial", Font.PLAIN, 16));
		textDescricao.setColumns(10);
		mainPanel.add(textDescricao);
		
		lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setBounds(10, 110, 100, 30);
		lblDescrio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescrio.setFont(new Font("Arial", Font.PLAIN, 20));
		mainPanel.add(lblDescrio);
		
		lblValor = new JLabel("Valor R$:");
		lblValor.setBounds(20, 150, 90, 30);
		lblValor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValor.setFont(new Font("Arial", Font.PLAIN, 20));
		mainPanel.add(lblValor);
		
		lblNewLabel = new JLabel("Tela Cadastro Servi\u00E7o");
		lblNewLabel.setBounds(10, 11, 521, 43);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 28));
		mainPanel.add(lblNewLabel);
		
		textQtde = new JTextField();
		textQtde.setEnabled(false);
		textQtde.setBounds(430, 156, 109, 20);
		textQtde.setToolTipText("Heyeyy");
		textQtde.setFont(new Font("Arial", Font.PLAIN, 16));
		textQtde.setColumns(10);
		mainPanel.add(textQtde);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Funcoes.fecharTela();
			}
		});
		btnSair.setBounds(430, 427, 109, 23);
		btnSair.setFont(new Font("Arial", Font.PLAIN, 16));
		mainPanel.add(btnSair);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarCampos();
				
			}
		});
		btnIncluir.setFont(new Font("Arial", Font.PLAIN, 16));
		btnIncluir.setBounds(132, 65, 109, 23);
		mainPanel.add(btnIncluir);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setEnabled(false);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(verificaCamposVazios() && verificaTickets()) {
					Servico servico = new Servico();
					servico.setsDescricaoServico(textDescricao.getText());
					servico.setfValorServico(Float.parseFloat(textValor.getText()));
					servico.setfQtdeKgCalculo(Float.parseFloat(textQtde.getText()));
					servico.setsProdutos(buscarProdutosVinculados());
					
					if(servico.cadastrarServico()) {
						limparCampos();
					}
				}
			}
		});
		btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnConfirmar.setBounds(296, 65, 109, 23);
		mainPanel.add(btnConfirmar);
	}

	protected String buscarProdutosVinculados() { 
		String sIds = "";
		table.selectAll();
		int[] selecao = table.getSelectedRows();
		for(int i=0; i < selecao.length; i ++) {
            if(sIds.equals("")) {
                sIds = String.valueOf(table.getValueAt(selecao[i], 0));
            }else {
                sIds = sIds + "," + String.valueOf(table.getValueAt(selecao[i], 0));
            }
        }
		return sIds;
	}

	protected boolean verificaTickets() {
		if(table.getRowCount() <= 0) {
			JOptionPane.showMessageDialog(null, "É necessário vincular ao menos um produto!");
			return false;
		}
		return true;
	}

	protected boolean verificaCamposVazios() {
		if( textDescricao.getText().equals("") || 
		    textQtde.getText().equals("") ||
		    textValor.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Verifique se todos os campos foram preenchidos corretamente!");
			return false;
		   }
		return true;
		
	}
	
	protected void limparCampos() {
		btnAdicionar.setEnabled(false);
		btnConfirmar.setEnabled(false);
		btnExcluir.setEnabled(false);
		textDescricao.setText("");
		textQtde.setText("");
		textValor.setText("");
		arrayProduto.clear();
		popularTabela();
	}

	protected void habilitarCampos() {
		btnAdicionar.setEnabled(true);
		btnConfirmar.setEnabled(true);
		btnExcluir.setEnabled(true);
		textDescricao.setEnabled(true);
		textQtde.setEnabled(true);
		textValor.setEnabled(true);
		
	}

	private void popularTabela() {
		model_table.setRowCount(0);
		if(!arrayProduto.isEmpty()) {
			for (int i = 0; i < arrayProduto.size(); i++) {
				int id = arrayProduto.get(i).getiIdProduto();
				String desc  = arrayProduto.get(i).getsDescricaoProduto();
				
				Object[] data = {id, desc};
				model_table.addRow(data);
			} 	
		}			
	}

	protected void buscarProdutos() {
		Funcoes.AbrirTela(new telaListarProduto(this));
	}

}
