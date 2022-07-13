package Telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Classes.Cliente;
import Classes.Funcoes;
import Classes.Produto;
import Classes.Serial;
import Classes.TicketBalanca;

import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Window.Type;


public class telaTicketEntrada extends telaTicket {
	private JTextField textPlaca;
	private Produto prod = new Produto();
	private Cliente cli = new Cliente();
	String arrayProdutos[] = prod.BuscarDescricaoProdutos();
	String arrayClientes[] = cli.BuscarNomesClientes();
	private Funcoes func = new Funcoes();
	private JTextField textPesoEntrada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaTicketEntrada frame = new telaTicketEntrada();
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
	public telaTicketEntrada() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				Funcoes.fecharTela();
			}
		});
		setType(Type.NORMAL);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				textUsuario.setText(func.nomeFuncLogado);
				textDataEntrada.setText(func.retornaDateTime(false));
				
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textDataEntrada.setText(func.retornaDateTime(false));
			}
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				textDataEntrada.setText(func.retornaDateTime(false));
			}
		});
		setBounds(100, 100, 600, 407);		
		setLocationRelativeTo(null);
		
		setLocationRelativeTo(null); // centralizar tela 
		textPlaca = new JTextField();
		textPlaca.setFont(new Font("Arial", Font.PLAIN, 18));
		textPlaca.setBounds(161, 60, 86, 20);
		getContentPane().add(textPlaca);
		textPlaca.setColumns(10);
		
		JComboBox cbProduto = new JComboBox();
		cbProduto.setEditable(true);
		cbProduto.setModel(new DefaultComboBoxModel(arrayProdutos));
		cbProduto.setFont(new Font("Arial", Font.PLAIN, 18));
		cbProduto.setBounds(161, 130, 405, 22);
		cbProduto.setSelectedIndex(-1);
		getContentPane().add(cbProduto);
		
		JComboBox cbCliente = new JComboBox();
		cbCliente.setModel(new DefaultComboBoxModel(arrayClientes));
		cbCliente.setFont(new Font("Arial", Font.PLAIN, 18));
		cbCliente.setEditable(true);
		cbCliente.setBounds(161, 96, 405, 22);
		cbCliente.setSelectedIndex(-1);
		getContentPane().add(cbCliente);
		
		JTextPane textObservacao = new JTextPane();
		textObservacao.setFont(new Font("Arial", Font.PLAIN, 16));
		textObservacao.setBounds(161, 275, 405, 20);
		getContentPane().add(textObservacao);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((validarProduto()) && (validarCliente()) && (validarCampos())) {
					TicketBalanca ticket = new TicketBalanca();
					
					try {
						ticket.setiIdProduto(func.buscaIntBanco("tbProduto", "idProduto", "descricao", cbProduto.getEditor().getItem().toString()));
						ticket.setiIdCliente(func.buscaIntBanco("tbCliente", "idCliente", "nome", cbCliente.getEditor().getItem().toString()));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
					ticket.setiIdFuncEntrada(func.idFuncLogado);
					ticket.setsDataHoraEntrada(func.retornaDateTime(false));
					ticket.setsPlacaVeiculo(textPlaca.getText());
					ticket.setsNomeMotorista(textMotorista.getText());
					ticket.setiPesoEntrada(getPesoEntrada());
					ticket.setsObservacao(textObservacao.getText());
					
					if(ticket.CadastrarTicketEntrada()) {
						Funcoes.fecharTela();
					}
					
				}else{
				JOptionPane.showMessageDialog(null, "Algo deu errado! Verifique as informações e tente novamente");
				}
			}		

			private boolean validarProduto() {
				int indexComboBox = cbProduto.getSelectedIndex();
				if(indexComboBox > -1) {
					return true;
				}else {
					String textoComboBox = cbProduto.getEditor().getItem().toString();
					if(!textoComboBox.equals("")) {
						prod.setsDescricaoProduto(textoComboBox);
						prod.CadastrarProduto();
						return true;
					}
				}
				return false;
			}
			
			private boolean validarCliente() {
				int indexComboBox = cbCliente.getSelectedIndex();
				if(indexComboBox > -1) {
					return true;
				}
				return false;
			}
		});
		btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 22));
		btnConfirmar.setBounds(161, 320, 176, 37);
		getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 22));
		btnCancelar.setBounds(390, 320, 176, 37);
		getContentPane().add(btnCancelar);
		
		JLabel lblObservacoes = new JLabel("Observa\u00E7\u00F5es");
		lblObservacoes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblObservacoes.setFont(new Font("Arial", Font.PLAIN, 18));
		lblObservacoes.setBounds(23, 275, 117, 25);
		getContentPane().add(lblObservacoes);
		
		JLabel lblNewLabel = new JLabel("Ticket Entrada");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 28));
		lblNewLabel.setBounds(189, 11, 253, 27);
		getContentPane().add(lblNewLabel);
		
		JLabel lblUsuarioEntrada = new JLabel("Usu\u00E1rio Entrada");
		lblUsuarioEntrada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuarioEntrada.setFont(new Font("Arial", Font.PLAIN, 18));
		lblUsuarioEntrada.setBounds(19, 165, 132, 25);
		getContentPane().add(lblUsuarioEntrada);
		
		textPesoEntrada = new JTextField();
		textPesoEntrada.setEnabled(false);
		textPesoEntrada.setBounds(161, 238, 80, 20);
		getContentPane().add(textPesoEntrada);
		textPesoEntrada.setColumns(10);
		
		Serial balanca = new Serial();
    	balanca.iniciarLeitura();
    	
    	int delay = 1000;   // delay de 5 seg.
		int interval = 200;  // intervalo de 0.2 seg.
		Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {
		        public void run() {    	
		        	textPesoEntrada.setText(Funcoes.sPesoBalanca);
		        }
		}, delay, interval);
	}
	
	private boolean validarCampos() {
		if((this.textMotorista.getText().equals("")) || 
		   (this.textPlaca.getText().equals(""))){
			return false;
		}
		return true;
	}
	private int getPesoEntrada() {
		return Integer.parseInt(this.textPesoEntrada.getText());
	}
}
