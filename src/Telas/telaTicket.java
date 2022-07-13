package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.Funcoes;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class telaTicket extends JFrame {
	JTextField textMotorista;
	protected JTextField textDataEntrada;
	protected JTextField textUsuario;
	private Funcoes func = new Funcoes();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaTicket frame = new telaTicket();
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
	public telaTicket() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//textDataEntrada.setText(func.retornaDateTime(false));
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//textDataEntrada.setText(func.retornaDateTime(false));
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				//textDataEntrada.setText(func.retornaDateTime(false));
				//textUsuario.setText(func.nomeFuncLogado);
			}
		});
		setResizable(false);
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 16));
		setBounds(100, 100, 604, 403);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Placa\r\n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 19));
		lblNewLabel.setBounds(45, 58, 98, 25);
		getContentPane().add(lblNewLabel);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCliente.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCliente.setBounds(45, 94, 98, 25);
		getContentPane().add(lblCliente);
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProduto.setFont(new Font("Arial", Font.PLAIN, 18));
		lblProduto.setBounds(45, 132, 98, 25);
		getContentPane().add(lblProduto);
		
		JLabel lblPesoEntrada = new JLabel("Peso Entrada");
		lblPesoEntrada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPesoEntrada.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPesoEntrada.setBounds(21, 238, 118, 25);
		getContentPane().add(lblPesoEntrada);
		
		JLabel lblData = new JLabel("Hora Entrada");
		lblData.setHorizontalAlignment(SwingConstants.RIGHT);
		lblData.setFont(new Font("Arial", Font.PLAIN, 18));
		lblData.setBounds(21, 202, 118, 25);
		getContentPane().add(lblData);
		
		JLabel lblPlaca = new JLabel("Motorista");
		lblPlaca.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPlaca.setBounds(269, 59, 80, 25);
		getContentPane().add(lblPlaca);
		
		textMotorista = new JTextField();
		textMotorista.setFont(new Font("Arial", Font.PLAIN, 18));
		textMotorista.setText("");
		textMotorista.setColumns(10);
		textMotorista.setBounds(350, 63, 219, 20);
		getContentPane().add(textMotorista);
		
		textDataEntrada = new JTextField();
		textDataEntrada.setEditable(false);
		textDataEntrada.setFont(new Font("Arial", Font.PLAIN, 18));
		textDataEntrada.setColumns(10);
		textDataEntrada.setBounds(161, 204, 176, 20);
		getContentPane().add(textDataEntrada);
		
		textUsuario = new JTextField();
		textUsuario.setEditable(false);
		textUsuario.setText("");
		textUsuario.setFont(new Font("Arial", Font.PLAIN, 18));
		textUsuario.setColumns(10);
		textUsuario.setBounds(161, 165, 407, 20);
		getContentPane().add(textUsuario);
		
		JLabel lblKg = new JLabel("Kg");
		lblKg.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKg.setFont(new Font("Arial", Font.PLAIN, 18));
		lblKg.setBounds(236, 238, 33, 25);
		getContentPane().add(lblKg);
		
	}
	
	protected boolean validarCampo() {
		if(this.textMotorista.getText().equals("")) {
			return false;
		}else {
			return false;
		}
	}




	
}
