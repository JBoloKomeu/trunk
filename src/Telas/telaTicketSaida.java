package Telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Classes.Funcoes;
import Classes.TicketBalanca;

import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.Window.Type;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class telaTicketSaida extends telaTicket {
	private JTextField textProduto;
	private JTextField textPesoSaida;
	private JTextField textPesoLiquido;
	private JTextField textDataSaida;
	private JTextField textOperacao;
	private JTextField textCliente;
	private TicketBalanca ticket = new TicketBalanca();
	private String arrayPlacas[] = ticket.BuscarPlacasEntradas();
	private JTextField textUsuarioSaida;
	private Funcoes func = new Funcoes();
	private JTextField textPesoEntrada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaTicketSaida frame = new telaTicketSaida();
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
	public telaTicketSaida() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				Funcoes.fecharTela();
			}
		});
		setType(Type.NORMAL);
		
		textUsuario.setLocation(161, 165);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				textMotorista.setEditable(false);
				textCliente.setEditable(false);
			}
		});
		setBounds(100, 100, 611, 575);
		setLocationRelativeTo(null);		
		textProduto = new JTextField();
		textProduto.setEditable(false);
		textProduto.setText("");
		textProduto.setFont(new Font("Arial", Font.PLAIN, 18));
		textProduto.setColumns(10);
		textProduto.setBounds(161, 130, 408, 20);
		getContentPane().add(textProduto);
		
		textPesoEntrada = new JTextField();
		textPesoEntrada.setHorizontalAlignment(SwingConstants.RIGHT);
		textPesoEntrada.setFont(new Font("Arial", Font.PLAIN, 18));
		textPesoEntrada.setEditable(false);
		textPesoEntrada.setColumns(10);
		textPesoEntrada.setBounds(161, 238, 80, 20);
		getContentPane().add(textPesoEntrada);
		
		JLabel lblNewLabel = new JLabel("Ticket Sa\u00EDda");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 28));
		lblNewLabel.setBounds(0, 0, 595, 50);
		getContentPane().add(lblNewLabel);
		
		JLabel lblPesoSada = new JLabel("Peso Sa\u00EDda");
		lblPesoSada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPesoSada.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPesoSada.setBounds(23, 269, 118, 25);
		getContentPane().add(lblPesoSada);
		
		textPesoSaida = new JTextField();
		textPesoSaida.setHorizontalAlignment(SwingConstants.RIGHT);
		textPesoSaida.setFont(new Font("Arial", Font.PLAIN, 18));
		textPesoSaida.setEditable(false);
		textPesoSaida.setColumns(10);
		textPesoSaida.setBounds(161, 271, 80, 20);
		getContentPane().add(textPesoSaida);
		
		JLabel lblPesoLquido = new JLabel("Peso L\u00EDquido");
		lblPesoLquido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPesoLquido.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPesoLquido.setBounds(23, 307, 118, 25);
		getContentPane().add(lblPesoLquido);
		
		textPesoLiquido = new JTextField();
		textPesoLiquido.setHorizontalAlignment(SwingConstants.RIGHT);
		textPesoLiquido.setFont(new Font("Arial", Font.PLAIN, 18));
		textPesoLiquido.setEditable(false);
		textPesoLiquido.setColumns(10);
		textPesoLiquido.setBounds(161, 309, 80, 20);
		getContentPane().add(textPesoLiquido);
		
		JLabel lblHoraSada = new JLabel("Hora Sa\u00EDda");
		lblHoraSada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHoraSada.setFont(new Font("Arial", Font.PLAIN, 18));
		lblHoraSada.setBounds(23, 343, 118, 25);
		getContentPane().add(lblHoraSada);
		
		textDataSaida = new JTextField();
		textDataSaida.setText("00/00/0000 as 00:00");
		textDataSaida.setFont(new Font("Arial", Font.PLAIN, 18));
		textDataSaida.setEditable(false);
		textDataSaida.setColumns(10);
		textDataSaida.setBounds(161, 345, 176, 20);
		getContentPane().add(textDataSaida);
		
		JLabel lblOperao = new JLabel("Opera\u00E7\u00E3o");
		lblOperao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOperao.setFont(new Font("Arial", Font.PLAIN, 18));
		lblOperao.setBounds(23, 407, 118, 25);
		getContentPane().add(lblOperao);
		
		textOperacao = new JTextField();
		textOperacao.setFont(new Font("Arial", Font.PLAIN, 18));
		textOperacao.setEditable(false);
		textOperacao.setColumns(10);
		textOperacao.setBounds(161, 409, 408, 20);
		getContentPane().add(textOperacao);
		
		JLabel lblObservacoes = new JLabel("Observa\u00E7\u00F5es");
		lblObservacoes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblObservacoes.setFont(new Font("Arial", Font.PLAIN, 18));
		lblObservacoes.setBounds(24, 440, 117, 25);
		getContentPane().add(lblObservacoes);
		
		JTextPane textObservacao = new JTextPane();
		textObservacao.setFont(new Font("Arial", Font.PLAIN, 16));
		textObservacao.setBounds(161, 445, 408, 20);
		getContentPane().add(textObservacao);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				ticket.setiIdFuncSaida(func.idFuncLogado);
				ticket.setiPesoSaida(Integer.parseInt(textPesoSaida.getText()));
				ticket.setiPesoLiquido(Integer.parseInt(textPesoLiquido.getText()));
				ticket.setsObservacao(textObservacao.getText());
				ticket.setsOperacao(textOperacao.getText());
				
				ticket.CadastrarTicketSaida();
				
			}
		});
		btnConfirmar.setEnabled(false);
		btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 22));
		btnConfirmar.setBounds(161, 482, 176, 37);
		getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 22));
		btnCancelar.setBounds(390, 482, 176, 37);
		getContentPane().add(btnCancelar);
		
		textCliente = new JTextField();
		textCliente.setText("");
		textCliente.setFont(new Font("Arial", Font.PLAIN, 18));
		textCliente.setEditable(false);
		textCliente.setColumns(10);
		textCliente.setBounds(161, 96, 408, 20);
		getContentPane().add(textCliente);
		
		JLabel lblUsuarioEntrada = new JLabel("Usu\u00E1rio Entrada");
		lblUsuarioEntrada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuarioEntrada.setFont(new Font("Arial", Font.PLAIN, 18));
		lblUsuarioEntrada.setBounds(10, 163, 132, 25);
		getContentPane().add(lblUsuarioEntrada);
		
		JLabel lblUsurioSada = new JLabel("Usu\u00E1rio Sa\u00EDda");
		lblUsurioSada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsurioSada.setFont(new Font("Arial", Font.PLAIN, 18));
		lblUsurioSada.setBounds(10, 379, 128, 25);
		getContentPane().add(lblUsurioSada);
		
		textUsuarioSaida = new JTextField();
		textUsuarioSaida.setFont(new Font("Arial", Font.PLAIN, 18));
		textUsuarioSaida.setEditable(false);
		textUsuarioSaida.setColumns(10);
		textUsuarioSaida.setBounds(161, 378, 408, 20);
		getContentPane().add(textUsuarioSaida);
		
		JComboBox cbPlaca = new JComboBox();
		cbPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbPlaca.getSelectedIndex() >= 0) {
					ticket = ticket.BuscarTicketEntrada(cbPlaca.getEditor().getItem().toString());
					try {
						btnConfirmar.setEnabled(true);
						setarCampos(ticket);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					LimparCampos();
				}
				
			}

			private void setarCampos(TicketBalanca ticket) throws SQLException {
				setarCamposPai(ticket);
				
				textCliente.setText(func.buscaStrBanco("tbCliente", "nome", "idCliente", String.valueOf(ticket.getiIdCliente())));
				textProduto.setText(func.buscaStrBanco("tbProduto", "descricao", "idProduto", String.valueOf(ticket.getiIdProduto())));
				textUsuario.setText(func.buscaStrBanco("tbFuncionario", "nome", "idFuncionario", String.valueOf(ticket.getiIdFuncEntrada())));
				textUsuarioSaida.setText(func.nomeFuncLogado);
				textPesoSaida.setText("2");
				textDataSaida.setText(func.retornaDateTime(false));
				textObservacao.setText(ticket.getsObservacao());
				if(ticket.getiPesoEntrada() > Integer.parseInt(textPesoSaida.getText())) {
					textPesoLiquido.setText(String.valueOf(ticket.getiPesoEntrada() - Integer.parseInt(textPesoSaida.getText())));
					textOperacao.setText("Entrada");
				}else {
					textPesoLiquido.setText(String.valueOf(Integer.parseInt(textPesoSaida.getText()) - ticket.getiPesoEntrada()));
					textOperacao.setText("Saida");
				}				
			}

			private void LimparCampos() {
				LimparCamposPai();
				textCliente.setText("");
				textDataSaida.setText("");
				textOperacao.setText("");
				textPesoEntrada.setText("");
				textPesoSaida.setText("");
				textPesoLiquido.setText("");
				textUsuario.setText("");
				textUsuarioSaida.setText("");
				textProduto.setText("");
				textObservacao.setText("");
			}
		});
		cbPlaca.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent event) {
			}
			public void inputMethodTextChanged(InputMethodEvent event) {
			}
		});
		cbPlaca.setFont(new Font("Arial", Font.PLAIN, 16));
		cbPlaca.setModel(new DefaultComboBoxModel(arrayPlacas));
		cbPlaca.setSelectedIndex(-1);
		cbPlaca.setBounds(161, 64, 101, 20);
		getContentPane().add(cbPlaca);		
		
		int delay = 1000;   // delay de 5 seg.
		int interval = 200;  // intervalo de 0.2 seg.
		Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {
		        public void run() {    	
		        	textPesoSaida.setText(Funcoes.sPesoBalanca);
		        }
		}, delay, interval);
	}
	
	protected void LimparCamposPai() {
		// TODO Auto-generated method stub
		this.textMotorista.setText("");
		this.textUsuario.setText("");
		this.textDataEntrada.setText("");
	}
	
	protected void setarCamposPai(TicketBalanca ticket) throws SQLException {
		this.textMotorista.setText(ticket.getsNomeMotorista());
		this.textUsuario.setText(func.buscaStrBanco("tbFuncionario", "nome", "idFuncionario", String.valueOf(ticket.getiIdFuncEntrada())));
		this.textPesoEntrada.setText(String.valueOf(ticket.getiPesoEntrada()));
		this.textDataEntrada.setText(func.inverterData(ticket.getdDataEntrada().toString())+ "  " +  ticket.gettHoraEntrada().toString());
	}
}
